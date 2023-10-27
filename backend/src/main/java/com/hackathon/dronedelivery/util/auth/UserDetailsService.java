package com.hackathon.dronedelivery.util.auth;

import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getUser(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User();
    }
}