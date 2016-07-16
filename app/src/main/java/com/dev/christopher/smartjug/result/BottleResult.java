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
    private String error;


    public BottleResult(String _id, String owner, String date) {
        this._id = _id;
        this.owner = owner;
        this.date = date;

    }

    public BottleResult(String _id, String owner) {
        this._id = _id;
        this.owner = owner;
    }

    public BottleResult(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BottleResult{" +
                "_id='" + _id + '\'' +
                ", owner='" + owner + '\'' +
                ", date='" + date + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public String getOwner() {
        return owner;
    }

    public String getDate() {
        return date;
    }

    public String getError() {
        return error;
    }
}
