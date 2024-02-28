package fr.steve.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fr.steve.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByName(String firstName);
}