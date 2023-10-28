package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.DronePool;
import com.hackathon.dronedelivery.model.OrderPool;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPoolService {

    private final OrderService orderService;

    private final OrderPool orderPool;

    @PostConstruct
    public void fillDronePool() {
        orderPool.getOrderPool().addAll(orderService.findAll());
    }
}
