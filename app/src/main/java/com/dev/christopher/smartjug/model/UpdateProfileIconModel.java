package com.dev.christopher.smartjug.model;

/**
 * Created by Christopher on 29/06/16.
 */
public class UpdateProfileIconModel {
    private String email;
    private String pathPicture;

    public UpdateProfileIconModel(String email, String path) {
        this.email = email;
        this.pathPicture = path;
    }

    @Override
    public String toString() {
        return "UpdateProfileIconModel{" +
                "email='" + email + '\'' +
                ", pathPicture='" + pathPicture + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }
}
