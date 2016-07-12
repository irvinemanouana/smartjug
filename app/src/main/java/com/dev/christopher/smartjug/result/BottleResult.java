package com.dev.christopher.smartjug.result;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Christopher on 12/07/16.
 */
public class BottleResult implements Serializable {
    private String _id;
    private String owner;
    private Date date;

    public BottleResult(String _id, String owner, Date date) {
        this._id = _id;
        this.owner = owner;
        this.date = date;
    }


    @Override
    public String toString() {
        return "BottleResult{" +
                "_id='" + _id + '\'' +
                ", owner='" + owner + '\'' +
                ", date=" + date +
                '}';
    }
}
