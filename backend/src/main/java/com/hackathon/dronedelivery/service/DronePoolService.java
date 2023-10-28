package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.DronePool;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DronePoolService {

    private final DroneService droneService;

    private final DronePool dronePool;

    @PostConstruct
    public void fillDronePool() {
        dronePool.getDronePool().addAll(droneService.findAll());
    }
}
