package dev.juanim.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.juanim.models.Emotion;
import dev.juanim.models.Moment;
import dev.juanim.repositories.InMemoryMomentRepository;

public class MomentService {

    private final InMemoryMomentRepository repository;

    public MomentService(InMemoryMomentRepository repository) {
        this.repository = repository;
    }

    public Moment addMoment(String title, String description, Emotion emotion, LocalDate momentDate) {
        int id = repository.generateId();
        Moment moment = new Moment(id, title, description, emotion, momentDate);
        return repository.save(moment);
    }

    public List<Moment> getAllMoments() {
        return repository.findAll();
    }

    public boolean deleteMoment(int id) {
    return repository.deleteById(id);
}
    public List<Moment> getMomentsByEmotion(Emotion emotion) {
        List<Moment> result = new ArrayList<>();
        for (Moment moment : repository.findAll()) {
        if (moment.getEmotion() == emotion) {
            result.add(moment);
        }
    }
    return result;
}
}