package com.hackathon.dronedelivery.util.geocoding;

import com.hackathon.dronedelivery.model.GeoCoords;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geocoding {
    public static double getCoordsSum(List<GeoCoords> coords){
        double result = 0;
        for(int i = 1; i<coords.size(); i++){
            result+=getDistance(coords.get(i-1), coords.get(i));
        }
        return result;
    }
    public static List<GeoCoords> getShortestWay(GeoCoords startPoint, List<GeoCoords> coords){
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
        if(eList.size()==1)
            result.add(eList.get(0));
        return result;
    }

    private static double getDistance(GeoCoords first, GeoCoords second){
        return Math.sqrt(Math.pow(second.getLatitude() - first.getLatitude(),2) + Math.pow(second.getLongitude() - first.getLongitude(),2));
    }

    public static GeoCoords getCoords(String adress) throws IOException, URISyntaxException {
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
        var coords = jsonObject.getJSONObject("response")
                .getJSONObject("GeoObjectCollection")
                .getJSONArray("featureMember")
                .getJSONObject(0)
                .getJSONObject("GeoObject")
                .getJSONObject("Point")
                .getString("pos");

        return new GeoCoords(coords);
    }
}
