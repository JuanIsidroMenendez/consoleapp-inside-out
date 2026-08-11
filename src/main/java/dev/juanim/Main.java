package dev.juanim;

import dev.juanim.repositories.InMemoryMomentRepository;
import dev.juanim.services.MomentService;
import dev.juanim.views.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
        MomentService service = new MomentService(new InMemoryMomentRepository());
        ConsoleMenu menu = new ConsoleMenu(service);
        menu.run();
    }
}