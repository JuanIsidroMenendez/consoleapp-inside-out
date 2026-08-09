package dev.juanim.contracts;

import java.util.List;

import dev.juanim.models.Moment;

public interface MomentRepository {
    
    Moment save(Moment moment);

    List<Moment> findAll();

    boolean deleteById(int id);

    Moment findById(int id);
}
