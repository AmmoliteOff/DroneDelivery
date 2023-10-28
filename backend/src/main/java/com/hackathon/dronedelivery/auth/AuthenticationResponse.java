package com.hackathon.dronedelivery.auth;

import com.hackathon.dronedelivery.dto.UserDTO;
import com.hackathon.dronedelivery.model.Authority;
import com.hackathon.dronedelivery.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserDTO userDTO;

    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = UserDTO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .userRole(((Authority) user.getAuthorities().toArray()[0]).getUserRole())
                .build();
        return userDTO;
    }

}
