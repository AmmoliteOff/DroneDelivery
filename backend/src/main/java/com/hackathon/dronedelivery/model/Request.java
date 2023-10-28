package com.hackathon.dronedelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackathon.dronedelivery.enums.RequestStatus;
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
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long droneSendRequest;

    @OneToOne(cascade = CascadeType.ALL)
    private Drone drone;

    @Transient
    @OneToMany
    @JsonIgnore
    private List<Order> orderList;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
