package com.hackathon.dronedelivery.controller;

import com.hackathon.dronedelivery.enums.Role;
import com.hackathon.dronedelivery.model.Authority;
import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.repository.UserRepository;
import com.hackathon.dronedelivery.util.generators.Sha256Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AuthController {

    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        user.setPassword(Sha256Generator.generate(user.getPassword()));
        var auth = new Authority(Role.DRONES_SENDER);
        auth.setUser(user);
        user.setAuthorities(List.of(auth));
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
