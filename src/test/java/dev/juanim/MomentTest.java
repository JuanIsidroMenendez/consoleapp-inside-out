package dev.juanim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;    

public class MomentTest {

    @Test
    @DisplayName("Un nuevo momento conserva los datos que se le pasan")
    void newMomentKeepsData() {
        LocalDate date = LocalDate.of(2026, 8, 10);
        Moment moment = new Moment(1, "Un día jugando a la Playstation", "Fue divertido", Emotion.JOY, date);

        assertEquals(1, moment.getId());
        assertEquals("Un día jugando a la Playstation", moment.getTitle());
        assertEquals("Fue divertido", moment.getDescription());
        assertEquals(Emotion.JOY, moment.getEmotion());
        assertEquals(date, moment.getMomentDate());
    }
    
    @Test
    @DisplayName("Un nuevo momento establece automáticamente las fechas de creación y modificación")
    void newMomentSetsDates() {
        Moment moment = new Moment(1, "Un día jugando a la Playstation", "Fue divertido", Emotion.JOY, LocalDate.of(2026, 8, 10));
        
        assertNotNull(moment.getCreatedAt());
        assertNotNull(moment.getModifiedAt());
        assertEquals(LocalDate.now(), moment.getCreatedAt());
    }
}
