package com.hackathon.dronedelivery.model;

import com.hackathon.dronedelivery.enums.DroneStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
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

    @ManyToOne
    private DroneSendRequest droneSendRequest;

    private double charge;

    private double fullChargeDistance;

    private double maxWeight;

    private double currentLongitude;
    private double currentLatitude;

    @Enumerated(EnumType.STRING)
    private DroneStatus status;
    public boolean isChargeEnoughToDeliver(double distance){
        return fullChargeDistance*(charge/100) > distance && (fullChargeDistance-distance/fullChargeDistance)*100>=25;
    }
}
