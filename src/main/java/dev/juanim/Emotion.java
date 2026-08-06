package dev.juanim;

public enum Emotion {
    JOY("Alegría"),
    SADNESS("Tristeza"),
    ANGER("Ira"),
    DISGUST("Asco"),
    FEAR("Miedo"),
    ENVY("Envidia"),
    EMBARRASSMENT("Vergüenza"),
    BOREDOM("Aburrimiento"),
    NOSTALGIA("Nostalgia");

    private final String displayName;

    Emotion(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
