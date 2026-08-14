package dev.juanim.views;

import java.time.LocalDate;
import java.util.List;

import dev.juanim.models.Emotion;
import dev.juanim.models.Moment;

public interface View {

    void showMenu();

    String readOption();

    String readLine(String prompt);

    int readInt(String prompt);

    LocalDate readDate(String prompt);

    Emotion askEmotion();

    void showMoments(List<Moment> moments);

    void showMessage(String message);

    void close();
}