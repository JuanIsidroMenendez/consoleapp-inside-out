package dev.juanim.views;

import java.util.Scanner;

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
}