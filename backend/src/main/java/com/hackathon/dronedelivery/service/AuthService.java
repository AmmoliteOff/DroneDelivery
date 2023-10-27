package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.enums.Role;
import com.hackathon.dronedelivery.model.Authority;
import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.repository.UserRepository;
import com.hackathon.dronedelivery.util.generators.Sha256Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    public void register(User user){
        if(!userRepository.findByUsername(user.getUsername()).isPresent()) {
            user.setPassword(Sha256Generator.generate(user.getPassword()));
            var auth = new Authority(Role.DRONES_SENDER);
            auth.setUser(user);
            user.setAuthorities(List.of(auth));
            userRepository.save(user);
        } else {
            // TODO:добавить обработку этого случая
        }
    }
}
