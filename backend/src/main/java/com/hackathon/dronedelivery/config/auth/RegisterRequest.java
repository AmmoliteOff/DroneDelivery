package com.hackathon.dronedelivery.config.auth;

import com.hackathon.dronedelivery.enums.UserRole;
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
    private UserRole userRole;
}
