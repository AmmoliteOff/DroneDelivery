package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.*;
import com.hackathon.dronedelivery.repository.DroneRepository;
import com.hackathon.dronedelivery.repository.RequestRepository;
import com.hackathon.dronedelivery.util.WeightTree;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@AllArgsConstructor
public class DroneDistributionService {
    private final DronePool dronePool;
    private final OrderPool orderPool;
    private final RequestRepository requestRepository;

    private final DroneRepository droneRepository;
//    public DroneDistributionService(@Autowired DronePool dronePool,
//                                    @Autowired OrderPool orderPool){
//        this.dronePool = dronePool;
//        this.orderPool = orderPool;
//    }

    public void AddOrder(Order order) throws IOException, URISyntaxException {
        orderPool.add(order);
        produceMatch();
    }

    public void addDrone(Drone drone) throws IOException, URISyntaxException {
        dronePool.add(drone);
        produceMatch();
    }

    private void match(Drone drone, List<Order> orders){
        drone.setOrders(orders);
        droneRepository.save(drone);

        var request = new DroneSendRequest();
        request.setDrone(drone);
        request.setStatus("Создана");
        request.setOrderList(orders);

        requestRepository.save(request);
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
                 //MATH DONE
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
        for (Order order:
             orders) {
            var c = getCoords(order.getCustomerAddress());
            coords.add(c);
            ordersMap.put(c, order);
        }

        coords = getShortestWay(startPoint, coords);

        while(!(drone.isChargeEnoughToDeliver(getCoordsSum(coords))) || !coords.isEmpty()){
            coords.remove(coords.size()-1);
            result.remove(ordersMap.get(coords));
        }

        return result;
    }

    private double getCoordsSum(List<GeoCoords> coords){
        double result = 0;
        for(int i = 1; i<coords.size(); i++){
            result+=getDistance(coords.get(i-1), coords.get(i));
        }
        return result;
    }
    private List<GeoCoords> getShortestWay(GeoCoords startPoint, List<GeoCoords> coords){
        List<GeoCoords> eList = new ArrayList<>(coords);
        List<GeoCoords> result = new LinkedList<>();
        result.add(startPoint);

        var coordToAdd = eList.get(0);
        double min = getDistance(startPoint, coordToAdd);
        for (GeoCoords coord :
                eList) {
            var dist = getDistance(startPoint, coord);

            if(min>dist) {
                min = dist;
                coordToAdd = coord;
            }
        }

        eList.remove(coordToAdd);
        result.add(coordToAdd);
        var prevPoint = coordToAdd;

        for(int i = 0; i < coords.size()-1; i++) {
            coordToAdd = eList.get(0);
            min = getDistance(prevPoint, coordToAdd);
            for (GeoCoords coord :
                    eList) {
                var dist = getDistance(prevPoint, coord);

                if(min>dist){
                    min = dist;
                    coordToAdd = coord;
                }
            }
            eList.remove(coordToAdd);
            result.add(coordToAdd);
            prevPoint = coordToAdd;
        }

        result.add(eList.get(0));
        return result;
    }

    private double getDistance(GeoCoords first, GeoCoords second){
        return Math.sqrt(Math.pow(second.getLatitude() - first.getLatitude(),2) + Math.pow(second.getLongitude() - first.getLongitude(),2));
    }

    private GeoCoords getCoords(String adress) throws IOException, URISyntaxException {
        //URL url = new URL("https://geocode-maps.yandex.ru/1.x?apikey=96839f5b-3a26-4ecc-9f2d-feb2ba6ab2d3&geocode=\""+adress+"\"&format=json");
        String encodedAddress = URLEncoder.encode(adress, "UTF-8");

        URL url = new URL("https://geocode-maps.yandex.ru/1.x?apikey=96839f5b-3a26-4ecc-9f2d-feb2ba6ab2d3&geocode=" + encodedAddress + "&format=json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String jsonStr = response.toString();
        JSONObject jsonObject = new JSONObject(jsonStr);

        return new GeoCoords(jsonObject.getJSONObject("Point").get("pos").toString());
    }
}
