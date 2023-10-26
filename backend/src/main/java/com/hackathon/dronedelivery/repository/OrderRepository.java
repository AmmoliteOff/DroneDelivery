package com.hackathon.dronedelivery.repository;

import com.hackathon.dronedelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
