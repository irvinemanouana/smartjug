package com.dev.christopher.smartjug.result;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Christopher on 12/07/16.
 */
public class BottleResult implements Serializable {
    private String _id;
    private String owner;
    private String date;

    public BottleResult(String _id, String owner, String date) {
        this._id = _id;
        this.owner = owner;
        this.date = date;
    }

    @Override
    public String toString() {
        return "BottleResult{" +
                "_id='" + _id + '\'' +
                ", owner='" + owner + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
