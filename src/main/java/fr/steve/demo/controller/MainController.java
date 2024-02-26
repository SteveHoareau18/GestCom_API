package fr.steve.demo.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.steve.demo.entity.User;
import jakarta.persistence.EntityManager;

@RestController
public class MainController {

    private EntityManager entityManager;

    public MainController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping("/user/create")
    public User hello() {
        User user = new User("Clain", "Maxime", LocalDate.now(), null);
        this.entityManager.persist(user);
        this.entityManager.flush();
        return user;
    }

}
