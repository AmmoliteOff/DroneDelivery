package com.hackathon.dronedelivery.controller;

import com.hackathon.dronedelivery.model.Notification;
import com.hackathon.dronedelivery.model.Order;
import com.hackathon.dronedelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            orders.add(Order.builder()
                            .id(i)
                            .customerName("Вася")
                            .customerNumber("234-234")
                            .products(new ArrayList<>())
                            .weight(0.0)
                            .customerAddress("Воронеж")
                            .build());
        }
        return ResponseEntity.ok(orders);
    }
}
