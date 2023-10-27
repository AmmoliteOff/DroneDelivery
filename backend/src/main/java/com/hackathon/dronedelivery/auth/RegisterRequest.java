package com.hackathon.dronedelivery.auth;

import com.hackathon.dronedelivery.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
    private Role role;
}