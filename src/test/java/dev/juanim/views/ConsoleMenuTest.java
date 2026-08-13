package dev.juanim.views;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.juanim.repositories.InMemoryMomentRepository;
import dev.juanim.services.MomentService;

class ConsoleMenuTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("El menú muestra las opciones y se despide al salir")
    void menuShowsOptionsAndExits() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));

        MomentService service = new MomentService(new InMemoryMomentRepository());
        new ConsoleMenu(service).run();

        String output = captured.toString();
        assertTrue(output.contains("Mi Diario:"));
        assertTrue(output.contains("¡Hasta la próxima!"));
    }
}