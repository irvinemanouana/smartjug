package com.dev.christopher.smartjug.model;

import java.io.Serializable;

/**
 * Created by Christopher on 19/07/16.
 */
public class BottleDataRequest implements Serializable {
    private String bottle;

    public BottleDataRequest(String bottle) {
        this.bottle = bottle;
    }

    public String getBottle() {
        return bottle;
    }

    public void setBottle(String bottle) {
        this.bottle = bottle;
    }

    @Override
    public String toString() {
        return "BottleDataRequest{" +
                "bottle='" + bottle + '\'' +
                '}';
    }
}
