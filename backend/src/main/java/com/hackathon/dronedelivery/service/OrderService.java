package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.Order;
import com.hackathon.dronedelivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    public final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public void add(Order order) {
        orderRepository.save(order);
    }
}
