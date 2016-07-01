package com.dev.christopher.smartjug.result;

/**
 * Created by Christopher on 29/06/16.
 */
public class UserResult {
    private String _id;
    private String sex;
    private String name;
    private String lastname;
    private String email;
    private String created_at;
    private String update_at;
    private String pathPicture;
    private int height;
    private int weight;

    public UserResult(int weight, String _id, String sex, String name, String lastname, String email, String created_at, String update_at, String pathPicture, int height) {
        this.weight = weight;
        this._id = _id;
        this.sex = sex;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.created_at = created_at;
        this.update_at = update_at;
        this.pathPicture = pathPicture;
        this.height = height;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "_id='" + _id + '\'' +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", created_at='" + created_at + '\'' +
                ", update_at='" + update_at + '\'' +
                ", pathPicture='" + pathPicture + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
