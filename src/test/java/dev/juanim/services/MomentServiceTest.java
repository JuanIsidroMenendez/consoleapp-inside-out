package dev.juanim.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

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
}