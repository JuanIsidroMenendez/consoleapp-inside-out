package dev.juanim;

import java.util.List;

public interface MomentRepository {
    
    Moment save(Moment moment);

    List<Moment> findAll();

    boolean deleteById(int id);

    Moment findById(int id);
}
