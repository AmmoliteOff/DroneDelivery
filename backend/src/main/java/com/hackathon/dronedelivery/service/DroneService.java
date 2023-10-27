package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.Drone;
import com.hackathon.dronedelivery.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;

    public List<Drone> findAll() {
        return droneRepository.findAll();
    }
    @Transactional
    public void add(Drone drone){
        droneRepository.save(drone);
    }
}
