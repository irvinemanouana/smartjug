package com.dev.christopher.smartjug.manager;

import android.util.Log;

import com.dev.christopher.smartjug.generator.ServiceGenerator;
import com.dev.christopher.smartjug.interfaceClient.BottleInterfaceClient;
import com.dev.christopher.smartjug.interfaceClient.DataInterfaceClient;
import com.dev.christopher.smartjug.interfaceClient.UserInterfaceClient;
import com.dev.christopher.smartjug.model.BottleDataRequest;
import com.dev.christopher.smartjug.model.BottleToUserModel;
import com.dev.christopher.smartjug.model.LoginModel;
import com.dev.christopher.smartjug.model.OwnerModel;
import com.dev.christopher.smartjug.model.RegisterModel;
import com.dev.christopher.smartjug.model.UpdateProfileIconModel;
import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.result.BottleResult;
import com.dev.christopher.smartjug.result.DataResult;
import com.dev.christopher.smartjug.result.ErrorResult;
import com.dev.christopher.smartjug.result.UserResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

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
    private static DataInterfaceClient data;

    public DataManager() {
        this.client = ServiceGenerator.createService(UserInterfaceClient.class);
        this.bottle= ServiceGenerator.createService(BottleInterfaceClient.class);
        this.data = ServiceGenerator.createService(DataInterfaceClient.class);
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
    public void register(RegisterModel model){
        client.createUser(model, new Callback<UserResult>() {
            @Override
            public void success(UserResult userResult, Response response) {
                EventBus.getDefault().post(userResult);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
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

    public void addBottle(BottleToUserModel model){
        bottle.linkBottle(model, new Callback<BottleResult>() {
            @Override
            public void success(BottleResult bottleResult, Response response) {
                EventBus.getDefault().post(bottleResult);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        });
    }

    public void foudBottle(OwnerModel id){
        Log.d(id.getOwner()," :UserID");
        bottle.foudBottle(id, new Callback<BottleResult>() {
            @Override
            public void success(BottleResult bottleResult, Response response) {
               /* if (bottleResult.getError()!=null)
                    EventBus.getDefault().post(new ErrorResult(bottleResult.getError()));
                else*/
                    EventBus.getDefault().post(bottleResult);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        });
    }

    public void getDayData(String idBottle){
        Log.d("getDayData",idBottle);
        data.getWaterData(new BottleDataRequest(idBottle), new Callback<ArrayList<DataResult>>() {
            @Override
            public void success(ArrayList<DataResult> dataResults, Response response) {
                Log.d("ArraySuccess", String.valueOf(dataResults.size()));
                EventBus.getDefault().post(dataResults);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("RetrofitError",error.getMessage());
            }
        });
    }



}
