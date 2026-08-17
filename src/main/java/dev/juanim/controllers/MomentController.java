package dev.juanim.controllers;

import java.time.LocalDate;

import dev.juanim.contracts.View;
import dev.juanim.models.Emotion;
import dev.juanim.services.MomentService;

public class MomentController {

    private final View view;
    private final MomentService service;

    public MomentController(View view, MomentService service) {
        this.view = view;
        this.service = service;
    }

    // Se utilizaba en ConsoleMenu (refactorizado).
    public void run() {
        boolean running = true;
        while (running) {
            view.showMenu();
            String option = view.readOption();
            switch (option) {
                case "1":
                    handleAdd();
                    break;
                case "2":
                    handleList();
                    break;
                case "3":
                    handleDelete();
                    break;
                case "4":
                    handleFilter();
                    break;
                case "5":
                    running = false;
                    view.showMessage("¡Hasta la próxima!");
                    break;
                default:
                    view.showMessage("Opción no válida. Inténtalo de nuevo.");
            }
        }
        view.close();
    }

    private void handleAdd() {
        String title = view.readLine("Introduce el título: ");
        LocalDate momentDate = view.readDate("Introduce la fecha (dd/mm/yyyy): ");
        String description = view.readLine("Introduce la descripción: ");
        Emotion emotion = view.askEmotion();

        service.addMoment(title, description, emotion, momentDate);
        view.showMessage("Momento vivido añadido correctamente.");
    }

    private void handleList() {
        view.showMoments(service.getAllMoments());
    }

    private void handleDelete() {
        int id = view.readInt("Introduce el identificador del momento: ");
        boolean deleted = service.deleteMoment(id);
        if (deleted) {
            view.showMessage("Momento vivido eliminado correctamente.");
        } else {
            view.showMessage("No se encontró ningún momento con ese identificador.");
        }
    }

    private void handleFilter() {
        view.showMessage("Filtrar por...:");
        view.showMessage("1. Emoción");
        view.showMessage("2. Fecha");
        String option = view.readLine("Introduce una opción: ");

        switch (option) {
            case "1":
                Emotion emotion = view.askEmotion();
                view.showMoments(service.getMomentsByEmotion(emotion));
                break;
            case "2":
                LocalDate date = view.readDate("Introduce la fecha (dd/mm/yyyy): ");
                view.showMoments(service.getMomentsByDate(date));
                break;
            default:
                view.showMessage("Opción no válida.");
        }
    }
}