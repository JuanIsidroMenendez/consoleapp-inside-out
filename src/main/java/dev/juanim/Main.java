package dev.juanim;

import dev.juanim.repositories.InMemoryMomentRepository;
import dev.juanim.services.MomentService;
import dev.juanim.views.ConsoleView;
import dev.juanim.controllers.MomentController;

public class Main {
    public static void main(String[] args) {
        MomentService service = new MomentService(new InMemoryMomentRepository());
        ConsoleView view = new ConsoleView();
        MomentController controller = new MomentController(view, service);
        controller.run();
    }
}