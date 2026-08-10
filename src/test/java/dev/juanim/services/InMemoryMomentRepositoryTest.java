package dev.juanim.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.juanim.repositories.InMemoryMomentRepository;

import dev.juanim.models.Moment;
import dev.juanim.models.Emotion;

class InMemoryMomentRepositoryTest {

    private InMemoryMomentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryMomentRepository();
    }

    private Moment sampleMoment(int id) {
        return new Moment(id, "Título", "Descripción", Emotion.JOY, LocalDate.of(2024, 5, 1));
    }

    @Test
    @DisplayName("Guardar un momento lo hace recuperable por su id")
    void savingMomentMakesItRetrievable() {
        Moment moment = sampleMoment(1);
        repository.save(moment);
        assertEquals(moment, repository.findById(1));
    }

    @Test
    @DisplayName("findAll devuelve todos los momentos guardados")
    void findAllReturnsAllSavedMoments() {
        repository.save(sampleMoment(1));
        repository.save(sampleMoment(2));
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DisplayName("Eliminar un momento existente devuelve true y lo elimina del repositorio")
    void deletingExistingMomentReturnsTrue() {
        repository.save(sampleMoment(1));
        assertTrue(repository.deleteById(1));
        assertNull(repository.findById(1));
    }

    @Test
    @DisplayName("Eliminar un momento inexistente devuelve false y no afecta al repositorio")
    void deletingNonExistentIdReturnsFalse() {
        assertFalse(repository.deleteById(99));
        assertEquals(0, repository.findAll().size());
    }

    @Test
    @DisplayName("generateId devuelve ids distintos y crecientes")
    void generateIdReturnsDistinctIds() {
        int first = repository.generateId();
        int second = repository.generateId();
        assertEquals(first + 1, second);
    }
}