package com.hackathon.dronedelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Order> orderList;

    @Column
    private String status;
}
