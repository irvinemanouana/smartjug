package com.dev.christopher.smartjug.model;

import java.io.Serializable;

/**
 * Created by Christopher on 16/07/16.
 */
public class OwnerModel implements Serializable {

    String owner;

    public OwnerModel(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
