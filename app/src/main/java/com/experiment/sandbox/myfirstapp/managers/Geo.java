package com.experiment.sandbox.myfirstapp.managers;

import java.io.Serializable;

/**
 * Created by Joe on 3/3/2016.
 */
public class Geo implements Serializable {
    private String lat, lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}