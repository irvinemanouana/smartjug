package com.dev.christopher.smartjug.interfaceClient;

import com.dev.christopher.smartjug.model.LoginModel;
import com.dev.christopher.smartjug.model.User;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Christopher on 17/05/2016.
 */


public interface UserInterfaceClient {

    @POST("/user/login")
    void getUserInfo(@Body LoginModel loginModel, Callback<User> userCallback);

}
