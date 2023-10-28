package com.hackathon.dronedelivery.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GeoCoords {
    private double longitude;
    private double latitude;

    public GeoCoords(String pos){
        this.longitude = Double.parseDouble(pos.substring(0, pos.indexOf(' ')));
        this.latitude = Double.parseDouble(pos.substring(pos.indexOf(' '), pos.length()-1));
    }

    public GeoCoords(double longitude, double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
