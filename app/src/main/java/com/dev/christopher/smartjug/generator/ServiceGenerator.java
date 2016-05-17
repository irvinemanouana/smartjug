package com.dev.christopher.smartjug.generator;


import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Christopher on 16/05/2016.
 */
public class ServiceGenerator {

    public static final String SMART_API_URL = "http://192.168.1.13:3000";
    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(SMART_API_URL)
            .setClient(new OkClient(new OkHttpClient()));
    public static <S> S createService(Class<S> serviceClass){
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(serviceClass);
    }
}
