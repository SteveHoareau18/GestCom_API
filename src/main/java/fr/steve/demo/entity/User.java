package fr.steve.demo.entity;

import java.time.LocalDate;
import java.util.UUID;

public class User {

    private UUID uuid;
    private String name, firstName;
    private LocalDate bornDate;
    private Address address;
}
