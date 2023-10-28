package com.hackathon.dronedelivery.controller;

import com.hackathon.dronedelivery.dto.DroneDTO;
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

    @PostMapping("/sendDrone")
    public ResponseEntity<?> sendDrone(){
        droneService.sendDrone();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/drones/{id}")
    public ResponseEntity<Drone> updateDrone (@RequestBody Drone drone) {
        Drone updatedDrone = droneService.save(drone);
        return ResponseEntity.ok(updatedDrone);
    }

    @PostMapping("/drones/new")
    public ResponseEntity<Drone> addDrone(@RequestBody DroneDTO droneDTO) throws IOException, URISyntaxException {
        Drone drone = new Drone();
        drone.setCharge(droneDTO.getCharge());
        drone.setFullChargeDistance(droneDTO.getFullChargeDistance());
        drone.setMaxWeight(droneDTO.getMaxWeight());
        Drone savedDrone = droneService.save(drone);
        droneDistributionService.addDrone(drone);
        return ResponseEntity.ok(savedDrone);
    }
}
