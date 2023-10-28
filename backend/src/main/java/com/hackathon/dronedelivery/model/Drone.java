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

    private double currentLongitude = 39.20567;

    private double currentLatitude = 51.65646;

    private String imageLink = "https://yns1.ru/attachments/Image/dji-mavic-air.png?template=generic";

    @Enumerated(EnumType.STRING)
    private DroneStatus status = DroneStatus.READY_FOR_DELIVERY;
    public boolean isChargeEnoughToDeliver(double distance){
        return fullChargeDistance*(charge/100) > distance && (fullChargeDistance-distance/fullChargeDistance)*100>=25;
    }
}
