package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.enums.DroneStatus;
import com.hackathon.dronedelivery.enums.OrderStatus;
import com.hackathon.dronedelivery.enums.RequestStatus;
import com.hackathon.dronedelivery.model.*;
import com.hackathon.dronedelivery.util.RequestToMarketplace;
import com.hackathon.dronedelivery.util.WeightTree;
import com.hackathon.dronedelivery.util.geocoding.Geocoding;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private void match(Drone drone, Map<GeoCoords, Order> orders) {
        Drone persistDrone = droneService.findById(drone.getId()).get();
        Map<GeoCoords, Long> routeMap = new HashMap<>();
        List<Order> persistOrders = new ArrayList<>();
        for (GeoCoords key : orders.keySet()) {
            Order order = orderService.findById(orders.get(key).getId()).get();
            routeMap.put(key, order.getId());
            order.setDrone(persistDrone);
            order.setLongitude(orders.get(key).getLongitude());
            order.setLatitude(orders.get(key).getLatitude());
            order.setOrderStatus(OrderStatus.ASSEMBLES);
            persistOrders.add(order);
            RequestToMarketplace.updateOrder(order);
        }
        persistDrone.setOrders(persistOrders);
        var request = new Request();
        persistDrone.setStatus(DroneStatus.APPOINTED);
        request.setDrone(persistDrone);
        request.setStatus(RequestStatus.CREATED);
        droneService.setRoute(routeMap);
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
            var ordersToDeliverMap = removeOrdersBasedOnDistance(drone,
                    ordersToDeliver, new GeoCoords(drone.getCurrentLongitude(),
                            drone.getCurrentLatitude()));

            if(ordersToDeliverMap.isEmpty()){
                produceMatch();
                dronePool.add(drone);
            }
            else {
                var list = new ArrayList<Order>();
                for (GeoCoords key:
                     ordersToDeliverMap.keySet()) {
                    list.add(ordersToDeliverMap.get(key));
                }

                orderPool.remove(list);

                match(drone, ordersToDeliverMap);
                 //MATCH DONE
            }
            return true;
        }
        else
            return false;
    }

    private Map<GeoCoords, Order> removeOrdersBasedOnDistance(Drone drone, List<Order> orders, GeoCoords startPoint) throws IOException, URISyntaxException {
        List<GeoCoords> coords = new ArrayList<>();
        List<Order> result = new ArrayList<>(orders);
        Map<GeoCoords, Order> ordersMap = new HashMap<>();
        for (Order order: orders) {
            var c = Geocoding.getCoords(order.getCustomerAddress());
            coords.add(c);
            order.setLatitude(c.getLatitude());
            order.setLongitude(c.getLongitude());
            ordersMap.put(c, order);
        }

        coords = Geocoding.getShortestWay(startPoint, coords);

        while(coords.size()-1 >= 0 && !(drone.isChargeEnoughToDeliver(Geocoding.getCoordsSum(coords))) && !coords.isEmpty()){
            ordersMap.remove(coords.get(coords.size()-1));
            coords.remove(coords.size()-1);
        }

        return ordersMap;
    }
}
