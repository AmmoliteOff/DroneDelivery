package com.hackathon.dronedelivery.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DroneSendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long droneSendRequest;

    @OneToOne(cascade = CascadeType.ALL)
    private Drone drone;

    @Transient
    @OneToMany
    private List<Order> orderList;

    @Column
    private String status;
}
