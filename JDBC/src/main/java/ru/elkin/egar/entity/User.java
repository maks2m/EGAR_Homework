package ru.elkin.egar.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class User {

    private long id;
    private String username;
    private String password;
    private boolean active;
    private String firstName;
    private String lastName;
    private String fullName;
    private Set<Role> roles = new HashSet<>();



}
