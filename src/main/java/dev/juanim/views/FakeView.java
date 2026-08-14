package dev.juanim.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.juanim.models.Emotion;
import dev.juanim.models.Moment;

public class FakeView implements View {

    public List<String> options = new ArrayList<>();
    public String line = "";
    public int intValue = 0;
    public LocalDate date = LocalDate.now();
    public Emotion emotion = Emotion.JOY;

    public final List<String> messages = new ArrayList<>();
    public final List<List<Moment>> shownMoments = new ArrayList<>();
    public boolean closed = false;

    private int optionIndex = 0;

    @Override public void showMenu() { }
    @Override public String readOption() { return options.get(optionIndex++); }
    @Override public String readLine(String prompt) { return line; }
    @Override public int readInt(String prompt) { return intValue; }
    @Override public LocalDate readDate(String prompt) { return date; }
    @Override public Emotion askEmotion() { return emotion; }
    @Override public void showMoments(List<Moment> moments) { shownMoments.add(moments); }
    @Override public void showMessage(String message) { messages.add(message); }
    @Override public void close() { closed = true; }
}