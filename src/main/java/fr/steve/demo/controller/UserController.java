package fr.steve.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.steve.demo.entity.User;
import fr.steve.demo.exceptions.UserNotFoundException;
import fr.steve.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
public class UserController {

    private UserRepository userRepository;
    private EntityManager entityManager;

    public UserController(EntityManager entityManager, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @GetMapping("/user/list")
    public List<User> list() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @PostMapping("/user/create")
    public User create(@RequestBody @NonNull User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/user/get/{id}")
    public User get(@PathVariable(value = "id") @NonNull Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/update/{id}")
    @Transactional
    public User update(@RequestBody @NonNull User user, @PathVariable(value = "id") @NonNull Integer id) {
        User userDb = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userDb.merge(user);
        this.userRepository.save(userDb);
        return userDb;
    }

    @DeleteMapping("/user/delete/{id}")
    public Integer delete(@PathVariable(value = "id") @NonNull Integer id) {
        User userDb = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(userDb);
        return id;
    }
}
