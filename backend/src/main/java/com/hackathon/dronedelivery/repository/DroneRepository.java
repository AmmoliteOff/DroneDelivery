package com.hackathon.dronedelivery.repository;

import com.hackathon.dronedelivery.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
