package com.dev.christopher.smartjug.interfaceClient;

import com.dev.christopher.smartjug.model.BottleToUserModel;
import com.dev.christopher.smartjug.model.OwnerModel;
import com.dev.christopher.smartjug.result.BottleResult;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Christopher on 12/07/16.
 */
public interface BottleInterfaceClient {
    @POST("/bottle/update")
    void linkBottle(@Body BottleToUserModel model , Callback<BottleResult> resultCallback);

    @POST("/bottle/get")
    void foudBottle(@Body OwnerModel owner , Callback<BottleResult> resultCallback);
}
