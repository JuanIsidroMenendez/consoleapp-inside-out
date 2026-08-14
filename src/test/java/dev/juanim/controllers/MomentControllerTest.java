package dev.juanim.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.juanim.models.Emotion;
import dev.juanim.repositories.InMemoryMomentRepository;
import dev.juanim.services.MomentService;
import dev.juanim.views.FakeView;

class MomentControllerTest {

    private FakeView view;
    private MomentService service;
    private MomentController controller;

    @BeforeEach
    void setUp() {
        view = new FakeView();
        service = new MomentService(new InMemoryMomentRepository());
        controller = new MomentController(view, service);
    }

    @Test
    @DisplayName("Al elegir salir, el controlador se despide y cierra la vista")
    void exitClosesView() {
        view.options.add("5");

        controller.run();

        assertTrue(view.messages.contains("¡Hasta la próxima!"));
        assertTrue(view.closed);
    }

    @Test
    @DisplayName("Añadir un momento lo guarda en el servicio y confirma")
    void addStoresMomentAndConfirms() {
        view.line = "Un título";
        view.date = LocalDate.of(2024, 5, 1);
        view.emotion = Emotion.JOY;
        view.options.add("1");
        view.options.add("5");

        controller.run();

        assertEquals(1, service.getAllMoments().size());
        assertTrue(view.messages.contains("Momento vivido añadido correctamente."));
    }

    @Test
    @DisplayName("Eliminar un id inexistente informa de que no se encontró")
    void deleteNonExistentReportsNotFound() {
        view.intValue = 999;
        view.options.add("3");
        view.options.add("5");

        controller.run();

        assertTrue(view.messages.contains("No se encontró ningún momento con ese identificador."));
    }
}