package com.dev.christopher.smartjug.manager;

import android.util.Log;

import com.dev.christopher.smartjug.generator.ServiceGenerator;
import com.dev.christopher.smartjug.interfaceClient.BottleInterfaceClient;
import com.dev.christopher.smartjug.interfaceClient.UserInterfaceClient;
import com.dev.christopher.smartjug.model.LoginModel;
import com.dev.christopher.smartjug.model.UpdateProfileIconModel;
import com.dev.christopher.smartjug.result.BottleResult;
import com.dev.christopher.smartjug.result.ErrorResult;
import com.dev.christopher.smartjug.result.UserResult;

import org.greenrobot.eventbus.EventBus;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Christopher on 29/06/16.
 */
public class DataManager {

    private static UserResult userResult;
    private static UserInterfaceClient client;
    private static BottleInterfaceClient bottle;

    public DataManager() {
        this.client = ServiceGenerator.createService(UserInterfaceClient.class);
        this.bottle= ServiceGenerator.createService(BottleInterfaceClient.class);
    }


    public static  DataManager getInstance(){
        return new DataManager();
    }


    public void changeProfilIcon(UpdateProfileIconModel iconModel){
        client.updateProfil(iconModel, new Callback<UserResult>() {
            @Override
            public void success(UserResult userResult, Response response) {
                Log.d("user",userResult.toString());
                EventBus.getDefault().post(userResult);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new ErrorResult(error.getMessage()));
            }
        });
    }

    public void Login(LoginModel loginModel){
        client.getUser(loginModel, new Callback<UserResult>() {
            @Override
            public void success(UserResult result, Response response) {
                EventBus.getDefault().post(result);
            }

            @Override
            public void failure(RetrofitError error) {
                String message = "Un problème est survenu";
                EventBus.getDefault().post(new ErrorResult(message));
            }
        });
    }



    public void setUserResult(UserResult result){
        Log.d("InSetUserResult",result.toString());
        this.userResult = result;
    }

    public UserResult getUserResult() {
        return userResult;
    }

    public void addBottle(String idUser){
        bottle.linkBottle(idUser, new Callback<BottleResult>() {
            @Override
            public void success(BottleResult bottleResult, Response response) {
                EventBus.getDefault().post(bottleResult);
            }

            @Override
            public void failure(RetrofitError error) {
                String message = "Un problème est survenu";
                EventBus.getDefault().post(new ErrorResult(message));
            }
        });
    }
}
