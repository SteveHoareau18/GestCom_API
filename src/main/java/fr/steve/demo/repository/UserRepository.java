package fr.steve.demo.repository;

import org.springframework.data.repository.CrudRepository;
import fr.steve.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}