package com.dev.christopher.smartjug.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.dev.christopher.smartjug.result.ErrorResult;
import com.dev.christopher.smartjug.result.NetworkResult;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Christopher on 04/07/16.
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info !=null) {

            Log.d("Network Available ", "Flag No 1");
        }else  {
            Log.d("Network not Available ", "Flag No 2");
            NetworkResult result = new NetworkResult("Network","Network not Available ");
            EventBus.getDefault().post(result);
        }
    }
}
