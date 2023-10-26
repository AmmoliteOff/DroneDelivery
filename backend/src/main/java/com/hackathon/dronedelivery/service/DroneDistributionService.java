package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.DronePool;
import com.hackathon.dronedelivery.model.Order;
import com.hackathon.dronedelivery.model.OrderPool;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneDistributionService {
    DronePool dronePool;
    OrderPool orderPool;
    public DroneDistributionService(@Autowired DronePool dronePool,
                                    @Autowired OrderPool orderPool){
        this.dronePool = dronePool;
        this.orderPool = orderPool;
    }

    public void AddOrder(Order order){
        orderPool.add(order);
        produceMatch();
    }

    private boolean produceMatch(){
        if(!dronePool.isEmpty() && !orderPool.isEmpty()){
            return true; //EDIT
        }
        else
            return false;
    }
}
