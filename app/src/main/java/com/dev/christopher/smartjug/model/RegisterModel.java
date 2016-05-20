package com.dev.christopher.smartjug.model;

/**
 * Created by Christopher on 20/05/2016.
 */
public class RegisterModel {


    private String sex;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private int height;
    private int weight;

    public RegisterModel(String sex, String name, String lastname, String email, String password, int height, int weight) {
        this.sex = sex;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
