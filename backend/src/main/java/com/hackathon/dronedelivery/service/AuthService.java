package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
//    UserRepository repo;
//    public AuthService(@Autowired UserRepository repo) {
//        this.repo = repo;
//    }
//    public String GetRole(String username, String password) throws AuthenticationException {
//        try {
//            var usr = repo.findByUsername(username);
//            User user = usr.get();
//            if(user.getPassword().equals(password)) {
//                return user.getAuthorities();
//            }
//            return null;
//        }
//        catch (AuthenticationException e){
//            return null;
//        }
//    }
}
