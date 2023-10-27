package com.hackathon.dronedelivery.repository;

import com.hackathon.dronedelivery.model.DroneSendRequest;
import com.hackathon.dronedelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository  extends JpaRepository<DroneSendRequest, Long> {
}
