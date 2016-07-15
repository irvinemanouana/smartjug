package com.dev.christopher.smartjug.interfaceClient;

import com.dev.christopher.smartjug.result.BottleResult;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Christopher on 12/07/16.
 */
public interface BottleInterfaceClient {
    @POST("/bottle/create")
    void linkBottle(@Body String owner , Callback<BottleResult> resultCallback);
}
