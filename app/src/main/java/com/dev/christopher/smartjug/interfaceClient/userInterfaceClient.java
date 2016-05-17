package com.dev.christopher.smartjug.interfaceClient;

import com.dev.christopher.smartjug.model.User;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.POST;

/**
 * Created by Christopher on 17/05/2016.
 */


public interface userInterfaceClient {
    @POST("/user/create")
    void getUserInfo(@Field("email")String email, @Field("password")String password, Callback<User> userCallback);

}
