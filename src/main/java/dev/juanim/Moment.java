package dev.juanim;

import java.time.LocalDate;

public class Moment {

    private int id;
    private String title;
    private String description;
    private Emotion emotion;
    private LocalDate momentDate;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    
    public Moment(int id, String title, String description, Emotion emotion, LocalDate momentDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.momentDate = momentDate;
        this.createdAt = LocalDate.now();
        this.modifiedAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Emotion getEmotion() {
        return emotion;
    }
    public LocalDate getMomentDate() {
        return momentDate;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

}
