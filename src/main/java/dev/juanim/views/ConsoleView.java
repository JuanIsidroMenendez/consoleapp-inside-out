package dev.juanim.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import dev.juanim.models.Emotion;
import dev.juanim.models.Moment;
// Quito MomentService (refactorización)

public class ConsoleView {

    private final Scanner scanner;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ConsoleView() {
        this.scanner = new Scanner(System.in);   // Se mantiene el Scanner de ConsoleMenu, porque la función de ConsoleView es la de interfaz.
    }

    // Mismo razonamiento.
    public void showMenu() {
        System.out.println("\nMi Diario:");
        System.out.println("1. Añadir momento");
        System.out.println("2. Ver todos los momentos disponibles");
        System.out.println("3. Eliminar un momento");
        System.out.println("4. Filtrar los momentos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public String readOption() {
        return scanner.nextLine();
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número. Inténtalo de nuevo.");
            }
        }
    }

    public LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine(), FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Usa el formato dd/mm/yyyy.");
            }
        }
    }

    public Emotion askEmotion() {
        System.out.println("Selecciona una emoción:");
        Emotion[] emotions = Emotion.values();
        for (int i = 0; i < emotions.length; i++) {
            System.out.println((i + 1) + ". " + emotions[i].getDisplayName());
        }
        while (true) {
            int choice = readInt("Introduce tu opción: ");
            if (choice >= 1 && choice <= emotions.length) {
                return emotions[choice - 1];
            }
            System.out.println("Elige un número entre 1 y " + emotions.length + ".");
        }
    }
    public void showMoments(List<Moment> moments) {
        if (moments.isEmpty()) {
            System.out.println("No hay momentos disponibles.");
            return;
        }
        System.out.println("Lista de momentos vividos:");
        for (Moment moment : moments) {
            System.out.println(format(moment));
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }

    private String format(Moment moment) {
        return moment.getId() + ". Ocurrió el: " + moment.getMomentDate().format(FORMATTER)
                + ". Título: " + moment.getTitle()
                + ". Descripción: " + moment.getDescription()
                + ". Emoción: " + moment.getEmotion().getDisplayName();
    }
}