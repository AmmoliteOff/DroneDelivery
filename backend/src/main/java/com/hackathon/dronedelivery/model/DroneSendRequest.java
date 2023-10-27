package com.hackathon.dronedelivery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drone_send_request")
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
