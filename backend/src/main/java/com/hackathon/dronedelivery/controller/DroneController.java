package com.hackathon.dronedelivery.controller;

import com.hackathon.dronedelivery.model.Drone;

import com.hackathon.dronedelivery.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DroneController {

    private final DroneService droneService;

    @PostMapping("/drones")
    public ResponseEntity<List<Drone>> getDrones() {

        return ResponseEntity.ok(droneService.findAll());
    }

    @PostMapping("/addDrone")
    public ResponseEntity<?> addDrone(@RequestBody Drone drone) {
        droneService.add(drone);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }
}
