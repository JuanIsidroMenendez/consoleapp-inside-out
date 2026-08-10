package dev.juanim.services;

import java.time.LocalDate;
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
}