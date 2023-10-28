package com.hackathon.dronedelivery.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

@Component
public class DronePool {
    @Getter
    Deque<Drone> dronePool = new ArrayDeque<Drone>();
    DronePool instance = null;
    private DronePool(){

    }
    public DronePool getInstance(){
        return Objects.requireNonNullElseGet(instance, DronePool::new);
    }

    public void add(Object drone){
        if(drone instanceof Drone)
            this.dronePool.add((Drone) drone);
    }

    public boolean isEmpty(){
        return dronePool.isEmpty();
    }

    public Drone poll(){
        return dronePool.poll();
    }

    public Drone peek(){
        return dronePool.peek();
    }


}
