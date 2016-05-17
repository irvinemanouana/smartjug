package com.dev.christopher.smartjug.model;

/**
 * Created by Christopher on 12/05/2016.
 */
public class User  {
    private String _id;
    private String sex;
    private String name;
    private String lastname;
    private String mail;
    private String created_at;
    private String update_at;
    private int height;
    private int weight;

    public User() {
    }

    public User(String _id, String sex, String name, String lastname, String mail, String created_at, String update_at, int height, int weight) {
        this._id = _id;
        this.sex = sex;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.created_at = created_at;
        this.update_at = update_at;
        this.height = height;
        this.weight = weight;
    }
}
