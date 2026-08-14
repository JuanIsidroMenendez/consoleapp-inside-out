package dev.juanim.views;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.juanim.models.Emotion;
import dev.juanim.models.Moment;

class ConsoleViewTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream captured;

    @BeforeEach
    void redirectOutput() {
        captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void simulateInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private String output() {
        return captured.toString();
    }

    @Test
    @DisplayName("showMenu imprime las cinco opciones del menú")
    void showMenuPrintsAllOptions() {
        new ConsoleView().showMenu();
        assertTrue(output().contains("Mi Diario:"));
        assertTrue(output().contains("1. Añadir momento"));
        assertTrue(output().contains("5. Salir"));
    }

    @Test
    @DisplayName("showMessage imprime el mensaje recibido")
    void showMessagePrintsMessage() {
        new ConsoleView().showMessage("Momento vivido añadido correctamente.");
        assertTrue(output().contains("Momento vivido añadido correctamente."));
    }

    @Test
    @DisplayName("readInt reintenta ante texto no numérico y acepta el número siguiente")
    void readIntRetriesOnInvalidInput() {
        simulateInput("abc\n7\n");
        int result = new ConsoleView().readInt("Introduce un número: ");
        assertEquals(7, result);
        assertTrue(output().contains("Debe ser un número"));
    }

    @Test
    @DisplayName("readDate reintenta ante fecha mal formada y acepta la fecha siguiente")
    void readDateRetriesOnInvalidInput() {
        simulateInput("no-es-fecha\n01/05/2024\n");
        LocalDate result = new ConsoleView().readDate("Introduce la fecha: ");
        assertEquals(LocalDate.of(2024, 5, 1), result);
        assertTrue(output().contains("Fecha no válida"));
    }

    @Test
    @DisplayName("askEmotion reintenta ante una opción fuera de rango")
    void askEmotionRetriesOnOutOfRange() {
        simulateInput("99\n1\n");
        Emotion result = new ConsoleView().askEmotion();
        assertEquals(Emotion.values()[0], result);
        assertTrue(output().contains("Elige un número entre 1 y"));
    }

    @Test
    @DisplayName("showMoments avisa cuando la lista está vacía")
    void showMomentsWarnsWhenEmpty() {
        new ConsoleView().showMoments(new ArrayList<>());
        assertTrue(output().contains("No hay momentos disponibles."));
    }

    @Test
    @DisplayName("showMoments lista los momentos cuando hay")
    void showMomentsListsMoments() {
        List<Moment> moments = new ArrayList<>();
        moments.add(new Moment(1, "Título", "Descripción", Emotion.JOY, LocalDate.of(2024, 5, 1)));
        new ConsoleView().showMoments(moments);
        assertTrue(output().contains("Lista de momentos vividos:"));
        assertTrue(output().contains("Título"));
    }
}