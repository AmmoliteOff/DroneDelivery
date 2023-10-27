package com.hackathon.dronedelivery.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hackathon.dronedelivery.model.DronePool;
import com.hackathon.dronedelivery.model.GeoCoords;
import com.hackathon.dronedelivery.model.Order;
import com.hackathon.dronedelivery.model.OrderPool;
import com.hackathon.dronedelivery.util.WeightTree;
import org.apache.coyote.Request;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class DroneDistributionService {
    DronePool dronePool;
    OrderPool orderPool;
    public DroneDistributionService(@Autowired DronePool dronePool,
                                    @Autowired OrderPool orderPool){
        this.dronePool = dronePool;
        this.orderPool = orderPool;
    }

    public void AddOrder(Order order) throws IOException {
        orderPool.add(order);
        produceMatch();
    }

    private boolean produceMatch() throws IOException {
        if(!dronePool.isEmpty() && !orderPool.isEmpty()){
            var drone = dronePool.poll();
            var tree = new WeightTree(drone.getMaxWeight());
            for (Order order:
                 orderPool.getOrderPool()) {
                tree.Add(order);
            }

            var ordersToDeliver = tree.getLeft();
            ordersToDeliver = removeOrdersBasedOnDistance(ordersToDeliver);

            if(ordersToDeliver.size() == 0){
                //droneService.setBadDrone(drone);
                produceMatch();
            }
            else {
                drone.setOrders(ordersToDeliver);
            }
            return true;
        }
        else
            return false;
    }

    private List<Order> removeOrdersBasedOnDistance(List<Order> orders) throws IOException {
        List<GeoCoords> coords = new ArrayList<>();

        for (Order order:
             orders) {
            coords.add(getCoords(order.getCustomerAddress()));
        }

        var a = 0;

        return orders;//
    }

    private GeoCoords getCoords(String adress) throws IOException{
        URL url = new URL("https://geocode-maps.yandex.ru/1.x?apikey=96839f5b-3a26-4ecc-9f2d-feb2ba6ab2d3&geocode=\""+adress+"\"&format=json");

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
