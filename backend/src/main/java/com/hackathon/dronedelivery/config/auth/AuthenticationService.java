package com.hackathon.dronedelivery.config.auth;

import com.hackathon.dronedelivery.model.Authority;
import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.service.UserService;
import com.hackathon.dronedelivery.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        Authority authority = new Authority(request.getUserRole());
        if(userService.findByUsername(request.getUsername()).isEmpty()) {
            var user = User.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .authorities(List.of(authority))
                    .build();

            authority.setUser(user);
            userService.save(user);

            var jwtToken = jwtUtil.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .userDTO(AuthenticationResponse.userToUserDTO(user))
                    .build();
        } else {
            return new AuthenticationResponse();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userService.findByUsername(request.getUsername()).get();


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userDTO(AuthenticationResponse.userToUserDTO(user))
                .build();

    }
}
