package com.dev.christopher.smartjug.interfaceClient;

import com.dev.christopher.smartjug.model.BottleDataRequest;
import com.dev.christopher.smartjug.result.DataResult;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Christopher on 19/07/16.
 */
public interface DataInterfaceClient {
    @POST("/data/all")
    void getWaterData(@Body BottleDataRequest bottle, Callback<ArrayList<DataResult>> listCallback);
}
