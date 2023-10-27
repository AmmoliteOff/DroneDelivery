package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.Drone;
import com.hackathon.dronedelivery.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;

    public List<Drone> findAll() {
        return droneRepository.findAll();
    }

    public void save(Drone drone){
        droneRepository.save(drone);
    }

    public Optional<Drone> findById(Long id) {
        return droneRepository.findById(id);
    }

    public boolean sendDrone(Drone drone){
        //Some implementation
        return false; //edit
    }
}
