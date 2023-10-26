package com.hackathon.dronedelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackathon.dronedelivery.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table("users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String username;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
