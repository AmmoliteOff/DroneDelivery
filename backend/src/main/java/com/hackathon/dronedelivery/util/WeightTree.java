package com.hackathon.dronedelivery.util;

import com.hackathon.dronedelivery.model.Order;

import java.util.ArrayList;
import java.util.List;

public class WeightTree {
    WeightTree parent;
    WeightTree left;
    WeightTree right;
    Order order;

    double initialCapacity;

    double cumulativeValue;

    public List<Order> getLeft(){
        List<Order> result = new ArrayList<>();
        if(this.left!=null)
            result.addAll(this.left.getLeft());
        return result;
    }

    public WeightTree(double initialCapacity){
        this.initialCapacity = initialCapacity;
        cumulativeValue = 0;
    }

    public WeightTree(WeightTree parent, Order order){
        this.parent = parent;
        this.initialCapacity = parent.initialCapacity;
        this.order = order;
        cumulativeValue = parent.cumulativeValue + order.getWeight();
    }

    public void Add(Order order){
        if(cumulativeValue + order.getWeight()> initialCapacity) {
            if (this.right == null)
                this.right = new WeightTree(this, order);
        }
        else{
            if(this.left == null)
                this.left = new WeightTree(this, order);
            else
                this.left.Add(order);
            }
    }
}
