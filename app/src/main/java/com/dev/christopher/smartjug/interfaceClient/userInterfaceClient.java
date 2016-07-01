package com.dev.christopher.smartjug.interfaceClient;

import com.dev.christopher.smartjug.model.LoginModel;
import com.dev.christopher.smartjug.model.RegisterModel;
import com.dev.christopher.smartjug.model.UpdateProfileIconModel;
import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.result.UserResult;

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

    @POST("/user/login")
    void getUser(@Body LoginModel loginModel, Callback<UserResult> userCallback);

    @POST("/user/create")
    void createUser(@Body RegisterModel model, Callback<User> userCallback);

    @POST("/user/update/image")
    void updateProfil(@Body UpdateProfileIconModel iconModel, Callback<UserResult> userCallback);

}
