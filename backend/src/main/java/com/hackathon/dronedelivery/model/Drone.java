package com.hackathon.dronedelivery.model;

import com.hackathon.dronedelivery.enums.DroneStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<Order> orders;

    private double charge;

    private double fullChargeDistance;

    private double maxWeight;

    private double currentLongitude;

    private double currentLatitude;

    private String imageLink;

    @Enumerated(EnumType.STRING)
    private DroneStatus status = DroneStatus.READY_FOR_DELIVERY;
    public boolean isChargeEnoughToDeliver(double distance){
        return fullChargeDistance*(charge/100) > distance && (fullChargeDistance-distance/fullChargeDistance)*100>=25;
    }
}
