package com.hackathon.dronedelivery.dto;

import com.hackathon.dronedelivery.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private UserRole userRole;
}
