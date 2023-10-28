package com.hackathon.dronedelivery.repository;

import com.hackathon.dronedelivery.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository  extends JpaRepository<Request, Long> {
}
