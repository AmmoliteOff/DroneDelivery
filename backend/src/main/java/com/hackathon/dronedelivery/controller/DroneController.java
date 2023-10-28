package com.hackathon.dronedelivery.controller;

import com.hackathon.dronedelivery.model.Drone;

import com.hackathon.dronedelivery.service.DroneDistributionService;
import com.hackathon.dronedelivery.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DroneController {

    private final DroneService droneService;
    private final DroneDistributionService droneDistributionService;

    @GetMapping("/drones")
    public ResponseEntity<List<Drone>> getDrones() {
        return ResponseEntity.ok(droneService.findAll());
    }

    @PostMapping("/addDrone")
    public ResponseEntity<?> addDrone(@RequestBody Drone drone) throws IOException, URISyntaxException {
        droneService.save(drone);
        droneDistributionService.addDrone(drone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendDrone")
    public ResponseEntity<?> sendDrone(@RequestBody Drone drone){
        droneService.sendDrone(drone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/drones/{id}")
    public ResponseEntity<Drone> updateDrone (@RequestBody Drone drone) {
        Drone updatedDrone = droneService.save(drone);
        return ResponseEntity.ok(updatedDrone);
    }

}
