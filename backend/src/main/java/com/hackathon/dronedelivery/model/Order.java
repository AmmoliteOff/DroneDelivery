package com.hackathon.dronedelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackathon.dronedelivery.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerAddress;
    private String customerName;
    private String customerNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double weight;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne
    @JsonIgnore
    private Drone drone;

    public double getWeight(){
        double res = 0;
        for (Product product:
             products) {
            res+=product.getWeight();
        }
        return res;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerAddress='" + customerAddress + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerNumber='" + customerNumber + '\'' +
                ", orderStatus=" + orderStatus +
                ", weight=" + weight +
                ", products=" + products +
                '}';
    }
}
