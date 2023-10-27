package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.Order;
import com.hackathon.dronedelivery.model.Product;
import com.hackathon.dronedelivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    public final OrderRepository orderRepository;
    public final ProductService productService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
        for (Product product : order.getProducts()) {
            product.setOrder(order);
            productService.save(product);
        }
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}
