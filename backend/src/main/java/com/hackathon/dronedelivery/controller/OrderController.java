package com.hackathon.dronedelivery.controller;

import com.hackathon.dronedelivery.model.Notification;
import com.hackathon.dronedelivery.model.Order;
import com.hackathon.dronedelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/acceptOrder")
    public Notification acceptOrder(@RequestBody Order order) {
        return new Notification();
    }

    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
