package com.hackathon.dronedelivery.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderPool{
    @Getter
    List<Order> orderPool = new LinkedList<>();
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

    public Order poll(){
        var c = orderPool.get(orderPool.size()-1);
        orderPool.remove(c);
        return c;
    }

    public void remove(List<Order> orders){
        for (Order order:
             orders) {
            orderPool.remove(order);
        }
    }
    public Order peek(){
        return orderPool.get(orderPool.size()-1);
    }
}
