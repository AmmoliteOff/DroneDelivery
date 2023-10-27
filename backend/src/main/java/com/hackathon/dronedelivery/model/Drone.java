package com.hackathon.dronedelivery.model;

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
public abstract class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Order> orders;

    private double charge;

    private double fullChargeDistance;

    private double maxWeight;
    public boolean isChargeEnoughToDeliver(double distance){
        return fullChargeDistance*(charge/100) > distance && (fullChargeDistance-distance/fullChargeDistance)*100>=25;
    }
}
