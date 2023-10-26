package com.hackathon.dronedelivery.model;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

@Component
public class OrderPool{
    Deque<Order> orderPool = new ArrayDeque<Order>();
    OrderPool instance = null;
    private OrderPool(){

    }
    public OrderPool getInstance(){
        return Objects.requireNonNullElseGet(instance, OrderPool::new);
    }

    public void add(Object order){
        if(order instanceof Order)
            this.orderPool.add((Order) order);
    }

    public boolean isEmpty(){
        return orderPool.isEmpty();
    }

    public Order get(){
        return orderPool.poll();
    }
}
