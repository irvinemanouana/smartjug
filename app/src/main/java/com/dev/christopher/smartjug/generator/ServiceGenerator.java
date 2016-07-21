package com.dev.christopher.smartjug.generator;


import com.dev.christopher.smartjug.utility.Tag;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Christopher on 16/05/2016.
 */
public class ServiceGenerator {

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(Tag.SMART_API_URL_PROD_HEROKU)
            .setClient(new OkClient(new OkHttpClient()));
    public static <S> S createService(Class<S> serviceClass){
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(serviceClass);
    }
}
