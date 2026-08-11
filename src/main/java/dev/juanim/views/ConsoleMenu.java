package dev.juanim.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dev.juanim.models.Emotion;
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
}