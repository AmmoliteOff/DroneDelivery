package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    public User save(User user) {
       return userRepository.save(user);
    }
}
