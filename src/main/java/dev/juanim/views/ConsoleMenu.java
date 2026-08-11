package dev.juanim.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

import dev.juanim.models.Emotion;
import dev.juanim.models.Moment;
import dev.juanim.services.MomentService;

public class ConsoleMenu {

    private final MomentService service;
    private final Scanner scanner;

    public ConsoleMenu(MomentService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            showMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addMoment();
                    break;
                case "2":
                    listMoments();
                    break;
                case "3":
                    deleteMoment();
                    break;
                case "4":
                    filterMoments();
                    break;
                case "5":
                    running = false;
                    System.out.println("¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        scanner.close();
    }

    private void showMenu() {
        System.out.println("\nMi Diario:");
        System.out.println("1. Añadir momento");
        System.out.println("2. Ver todos los momentos disponibles");
        System.out.println("3. Eliminar un momento");
        System.out.println("4. Filtrar los momentos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
    /* Opción 1*/
    private void addMoment() {
        System.out.print("Introduce el título: ");
        String title = scanner.nextLine();

        System.out.print("Introduce la fecha (dd/mm/yyyy): ");
        LocalDate momentDate = LocalDate.parse(scanner.nextLine(),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Introduce la descripción: ");
        String description = scanner.nextLine();

        Emotion emotion = askEmotion();

        service.addMoment(title, description, emotion, momentDate);
        System.out.println("Momento vivido añadido correctamente.");
    }

    private Emotion askEmotion() {
        System.out.println("Selecciona una emoción:");
        Emotion[] emotions = Emotion.values();
        for (int i = 0; i < emotions.length; i++) {
            System.out.println((i + 1) + ". " + emotions[i].getDisplayName());
        }
        System.out.print("Introduce tu opción: ");
        int choice = Integer.parseInt(scanner.nextLine());
        return emotions[choice - 1];
    }
    private String format(Moment moment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return moment.getId() + ". Ocurrió el: " + moment.getMomentDate().format(formatter)
            + ". Título: " + moment.getTitle()
            + ". Descripción: " + moment.getDescription()
            + ". Emoción: " + moment.getEmotion().getDisplayName();
    }
    /* Opción 2*/
    private void listMoments() {
        List<Moment> moments = service.getAllMoments();
        if (moments.isEmpty()) {
            System.out.println("No hay momentos disponibles.");
            return;
        } 
        System.out.println("Lista de momentos disponibles:");
        for (Moment moment : moments) {
                System.out.println(format(moment));
            }
        }
    /* Opción 3 */
    private void deleteMoment() {
        System.out.print("Introduce el ID del momento: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean deleted = service.deleteMoment(id);
        if (deleted) {
            System.out.println("Momento vivido eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún momento con ese ID.");
        }
    }
    /* Opción 4 */
    private void filterMoments() {
        System.out.println("Filtrar por...:");
        System.out.println("1. Emoción");
        System.out.println("2. Fecha");
        System.out.print("Introduce una opción: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                filterByEmotion();
                break;
            case "2":
                filterByDate();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void filterByEmotion() {
        Emotion emotion = askEmotion();
        printResults(service.getMomentsByEmotion(emotion));
    }

    private void filterByDate() {
        System.out.print("Introduce la fecha (dd/mm/yyyy): ");
        LocalDate date = LocalDate.parse(scanner.nextLine(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        printResults(service.getMomentsByDate(date));
    }

    private void printResults(List<Moment> moments) {
        if (moments.isEmpty()) {
            System.out.println("No hay momentos que coincidan.");
            return;
        }
        System.out.println("Lista de momentos vividos:");
        for (Moment moment : moments) {
            System.out.println(format(moment));
        }
    }
}



