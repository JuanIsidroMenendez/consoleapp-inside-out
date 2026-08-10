package dev.juanim.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.juanim.models.Emotion;
import dev.juanim.models.Moment;
import dev.juanim.repositories.InMemoryMomentRepository;

class MomentServiceTest {

    private MomentService service;

    @BeforeEach
    void setUp() {
        service = new MomentService(new InMemoryMomentRepository());
    }

    @Test
    @DisplayName("Añadir un momento lo guarda y le asigna un id")
    void addingMomentStoresItWithId() {
        Moment moment = service.addMoment("Un día en Jurassic Park", "Fue terrorífico",
        Emotion.FEAR, LocalDate.of(2026, 9, 11));

        assertNotNull(moment);
        assertEquals(1, moment.getId());
    }

    @Test
    @DisplayName("Un momento añadido conserva los datos indicados")
    void addedMomentKeepsItsData() {
        Moment moment = service.addMoment("Title", "Description",
                Emotion.SADNESS, LocalDate.of(2026, 7, 26));

        assertEquals("Title", moment.getTitle());
        assertEquals("Description", moment.getDescription());
        assertEquals(Emotion.SADNESS, moment.getEmotion());
        assertEquals(LocalDate.of(2026, 7, 26), moment.getMomentDate());
    }

    @Test
    @DisplayName("Añadir un momento provoca un incremento de uno en el número de momentos almacenados")
    void addingMomentIncreasesCount() {
    int before = service.getAllMoments().size();
        service.addMoment("Título", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 1));

        assertEquals(before + 1, service.getAllMoments().size());
    }

    @Test
    @DisplayName("Eliminar un momento existente lo suprime y devuelve true")
    void deletingExistingMomentReturnsTrue() {
        Moment moment = service.addMoment("Título", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 1));

        assertTrue(service.deleteMoment(moment.getId()));
        assertEquals(0, service.getAllMoments().size());
    }

    @Test
    @DisplayName("Eliminar un id inexistente devuelve false y no altera la lista")
    void deletingNonExistentIdReturnsFalse() {
        service.addMoment("Título", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 1));

        assertFalse(service.deleteMoment(999));
        assertEquals(1, service.getAllMoments().size());
    }

    @Test
    @DisplayName("Filtrar momentos por emoción devuelve solo los que coinciden")
    void filteringByEmotionReturnsMatchingMoments() {
        service.addMoment("Feliz 1", "Descripción", Emotion.JOY, LocalDate.of(2026, 1, 1));
        service.addMoment("Triste", "Descripción", Emotion.SADNESS, LocalDate.of(2026, 2, 2));
        service.addMoment("Feliz 2", "Descripción", Emotion.JOY, LocalDate.of(2026, 3, 3));

        List<Moment> joyful = service.getMomentsByEmotion(Emotion.JOY);

        assertEquals(2, joyful.size());
    }
    @Test
    @DisplayName("Filtrar por una emoción sin coincidencias devuelve una lista vacía")
    void filterByEmotionWithNoMatchesReturnsEmpty() {
        service.addMoment("Feliz", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 1));

        List<Moment> angry = service.getMomentsByEmotion(Emotion.ANGER);

        assertEquals(0, angry.size());
    }
    @Test
    @DisplayName("Filtrar por fecha exacta devuelve solo los momentos de ese día")
    void filterByExactDateReturnsMatchingMoments() {
        service.addMoment("Día 1", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 1));
        service.addMoment("Día 2", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 2));

        List<Moment> result = service.getMomentsByDate(LocalDate.of(2024, 1, 1));

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Filtrar por una fecha sin coincidencias devuelve una lista vacía")
    void filterByDateWithNoMatchesReturnsEmpty() {
        service.addMoment("Día", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 1));

        List<Moment> result = service.getMomentsByDate(LocalDate.of(2024, 12, 31));

        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Filtrar por mes devuelve todos los momentos de ese mes")
    void filterByMonthReturnsAllMomentsInMonth() {
        service.addMoment("Enero 1", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 1));
        service.addMoment("Enero 15", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 15));
        service.addMoment("Febrero", "Descripción", Emotion.JOY, LocalDate.of(2024, 2, 1));

        List<Moment> result = service.getMomentsByMonth(YearMonth.of(2024, 1));

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Filtrar por un mes sin coincidencias devuelve una lista vacía")
    void filterByMonthWithNoMatchesReturnsEmpty() {
        service.addMoment("Enero", "Descripción", Emotion.JOY, LocalDate.of(2024, 1, 1));

        List<Moment> result = service.getMomentsByMonth(YearMonth.of(2024, 6));

        assertEquals(0, result.size());
    }
    }