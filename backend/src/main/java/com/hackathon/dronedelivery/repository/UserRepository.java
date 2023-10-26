package com.hackathon.dronedelivery.repository;

import com.hackathon.dronedelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
