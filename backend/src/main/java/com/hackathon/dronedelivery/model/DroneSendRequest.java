package com.hackathon.dronedelivery.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DroneSendRequest {
    @Id
    long droneSendRequest;

    @ManyToOne
    Drone drone;

    @OneToMany
    List<Order> orderList;

    @Column
    String status;
}
