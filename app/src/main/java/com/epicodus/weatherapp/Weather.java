package com.epicodus.weatherapp;

/**
 * Created by Guest on 11/29/16.
 */
public class Weather {
    private String mName;
    private String mDescription;
    private float mTemperature;

    public Weather(String name, String description, float temperature) {
        this.mName = name;
        this.mDescription = description;
        this.mTemperature = temperature;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public float getTemperature() {
        return mTemperature;
    }
}
