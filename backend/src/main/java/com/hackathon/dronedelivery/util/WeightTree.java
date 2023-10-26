package com.hackathon.dronedelivery.util;

import com.hackathon.dronedelivery.model.Order;

public class WeightTree {
    WeightTree parent;
    WeightTree left;
    WeightTree right;
    Order order;

    double initialCapacity;

    double cumulativeValue;

    public WeightTree(int initialCapacity){
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
