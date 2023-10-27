package com.hackathon.dronedelivery.controller;

import com.hackathon.dronedelivery.model.DroneSendRequest;
import com.hackathon.dronedelivery.model.Order;
import com.hackathon.dronedelivery.service.DroneDistributionService;
import com.hackathon.dronedelivery.service.OrderService;
import com.hackathon.dronedelivery.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class OrderController {

    private final OrderService orderService;
    private final DroneDistributionService droneDistributionService;
    private final RequestService requestService;

    @PostMapping("/acceptOrder")
    public ResponseEntity<?> acceptOrder(@RequestBody Order order) throws IOException, URISyntaxException {
        orderService.save(order);
        droneDistributionService.AddOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/requests")
    public ResponseEntity<List<DroneSendRequest>> getOrders() {
        return ResponseEntity.ok(requestService.findAll());
    }

}
