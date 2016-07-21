package com.dev.christopher.smartjug.result;

import java.io.Serializable;

/**
 * Created by Christopher on 17/07/16.
 */
public class DataResult implements Serializable {
    private double liter;
    private int temperature;
    private String bottle;
    private String date;


    public DataResult(double liter, int temperature, String bottle, String date) {
        this.liter = liter;
        this.temperature = temperature;
        this.bottle = bottle;
        this.date = date;
    }

    public double getLiter() {
        return liter;
    }

    public void setLiter(double liter) {
        this.liter = liter;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getBottle() {
        return bottle;
    }

    public void setBottle(String bottle) {
        this.bottle = bottle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DataResult{" +
                "liter=" + liter +
                ", temperature=" + temperature +
                ", bottle='" + bottle + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
