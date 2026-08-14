package dev.juanim.views;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConsoleViewTest {

    private final PrintStream originalOut = System.out;

    @AfterEach
    void restoreOut() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("showMenu imprime las cinco opciones del menú")
    void showMenuPrintsAllOptions() {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));

        new ConsoleView().showMenu();

        String output = captured.toString();
        assertTrue(output.contains("Mi Diario:"));
        assertTrue(output.contains("1. Añadir momento"));
        assertTrue(output.contains("5. Salir"));
    }

    @Test
    @DisplayName("showMessage imprime el mensaje recibido")
    void showMessagePrintsMessage() {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));

        new ConsoleView().showMessage("Momento vivido añadido correctamente.");

        assertTrue(captured.toString().contains("Momento vivido añadido correctamente."));
    }
}