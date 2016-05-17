package com.dev.christopher.smartjug.model;

/**
 * Created by Christopher on 12/05/2016.
 */
public class User  {
    private String _id;
    private String sex;
    private String name;
    private String lastname;
    private String email;
    private String created_at;
    private String update_at;
    private String password;
    private int height;
    private int weight;

    public User() {
    }

    public User(String _id, String sex, String name, String lastname, String email, String created_at, String update_at, int height, int weight) {
        this._id = _id;
        this.sex = sex;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.created_at = created_at;
        this.update_at = update_at;
        this.height = height;
        this.weight = weight;
    }

    public User(String _id, String sex, String name, String lastname, String email, String created_at, String update_at, String password, int height, int weight) {
        this._id = _id;
        this.sex = sex;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.created_at = created_at;
        this.update_at = update_at;
        this.password = password;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", created_at='" + created_at + '\'' +
                ", update_at='" + update_at + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
}
