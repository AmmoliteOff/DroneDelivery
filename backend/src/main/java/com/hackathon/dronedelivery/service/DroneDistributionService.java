package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.*;
import com.hackathon.dronedelivery.util.WeightTree;
import com.hackathon.dronedelivery.util.geocoding.Geocoding;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

@Service
@AllArgsConstructor
public class DroneDistributionService {
    private final DronePool dronePool;
    private final OrderPool orderPool;
    private final RequestService requestService;
    private final OrderService orderService;

    private final DroneService droneService;

    public void AddOrder(Order order) throws IOException, URISyntaxException {
        orderPool.add(order);
        produceMatch();
    }

    public void addDrone(Drone drone) throws IOException, URISyntaxException {
        dronePool.add(drone);
        produceMatch();
    }

    private void match(Drone drone, List<Order> orders) {
        Drone persistDrone = droneService.findById(drone.getId()).get();
        List<Order> persistOrders = new ArrayList<>();
        for (Order value : orders) {
            Order order = orderService.findById(value.getId()).get();
            order.setDrone(persistDrone);
            persistOrders.add(order);
        }
        persistDrone.setOrders(persistOrders);
        var request = new DroneSendRequest();
        request.setDrone(persistDrone);
        request.setStatus("Создана");
        requestService.save(request);
    }

    private boolean produceMatch() throws IOException, URISyntaxException {
        if(!dronePool.isEmpty() && !orderPool.isEmpty()){
            var drone = dronePool.poll();
            var tree = new WeightTree(drone.getMaxWeight());
            for (Order order:
                 orderPool.getOrderPool()) {
                tree.Add(order);
            }

            var ordersToDeliver = tree.getLeft();
            ordersToDeliver = removeOrdersBasedOnDistance(drone,
                    ordersToDeliver, new GeoCoords(drone.getCurrentLongitude(),
                            drone.getCurrentLatitude()));

            if(ordersToDeliver.isEmpty()){
                //droneService.setBadDrone(drone);
                produceMatch();
            }
            else {
                match(drone, ordersToDeliver);
                 //MATCH DONE
            }
            return true;
        }
        else
            return false;
    }

    private List<Order> removeOrdersBasedOnDistance(Drone drone, List<Order> orders, GeoCoords startPoint) throws IOException, URISyntaxException {
        List<GeoCoords> coords = new ArrayList<>();
        List<Order> result = new ArrayList<>(orders);
        Map<GeoCoords, Order> ordersMap = new HashMap<>();
        for (Order order: orders) {
            var c = Geocoding.getCoords(order.getCustomerAddress());
            coords.add(c);
            ordersMap.put(c, order);
        }

        coords = Geocoding.getShortestWay(startPoint, coords);

        while(coords.size()-1 >= 0 && !(drone.isChargeEnoughToDeliver(Geocoding.getCoordsSum(coords))) || !coords.isEmpty()){
            coords.remove(coords.size()-1);
            result.remove(ordersMap.get(coords));
        }

        return result;
    }
}
