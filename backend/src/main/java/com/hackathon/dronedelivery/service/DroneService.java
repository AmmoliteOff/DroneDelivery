package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.Drone;
import com.hackathon.dronedelivery.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;

    public List<Drone> findAll() {
        return droneRepository.findAll();
    }
}
