package dev.juanim;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class InMemoryMomentRepository implements MomentRepository {
    
    private final Map<Integer, Moment> moments = new HashMap<>();
    private int nextId = 1;

    @Override
    public Moment save (Moment moment) {
        moments.put(moment.getId(), moment);
        return moment;
    } 

    @Override
    public List<Moment> findAll() {
        return new ArrayList<>(moments.values());
    }

    @Override
    public boolean deleteById(int id) {
        return moments.remove(id) != null;
    }

    @Override
    public Moment findById(int id) {
        return moments.get(id);
    }

    public int generateId() {
        return nextId++;
    }

}
