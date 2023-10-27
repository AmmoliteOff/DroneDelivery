package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService {
    private UserRepository userRepository;
    public UsersService(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }

    public User getUser(String pr){
        return userRepository.findByUsername(pr.replaceAll(" ", "")).get();
    }

    public void setSettings(String pr, String name, String surname, String imgLink){
        User user = getUser(pr);
        user.setName(name);
        user.setSurname(surname);
        Update(user);
    }

    public void Update(User user){
        userRepository.save(user);
    }
}
