package com.dev.christopher.smartjug.model;

/**
 * Created by Christopher on 16/07/16.
 */
public class BottleToUserModel {
    private String id;
    private String owner;

    public BottleToUserModel(String id, String owner) {
        this.id = id;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "BottleToUserModel{" +
                "bottle='" + id + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
