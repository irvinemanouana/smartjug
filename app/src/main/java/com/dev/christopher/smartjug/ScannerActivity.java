package com.dev.christopher.smartjug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dev.christopher.smartjug.manager.DataManager;
import com.dev.christopher.smartjug.model.BottleToUserModel;
import com.dev.christopher.smartjug.result.BottleResult;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.wearable.MessageEvent;
import com.google.zxing.Result;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit.RetrofitError;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private static final String TAG = "Scan";
    private static final String INTENT_TAG = "USER" ;
    private static final String FROM_ACCOUNT = "ACCOUNT";
    private String _id;
    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_scanner);
        scannerView = new ZXingScannerView(getApplicationContext());
        setContentView(scannerView);
        Intent intent = getIntent();
        if (intent.getStringExtra(INTENT_TAG)!=null){
            _id = intent.getStringExtra(INTENT_TAG);
        }
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.d("_id",_id);
        DataManager.getInstance().addBottle(new BottleToUserModel(result.getText(),_id));
        Log.v(TAG, result.getText()); // Prints scan results
        Log.v(TAG, result.getBarcodeFormat().toString());

    }

    @Subscribe
    public void onEventMainThread(BottleResult bottleResult) {
        Log.d(bottleResult.toString(),"bottle");
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        intent.putExtra(FROM_ACCOUNT,true);
        startActivity(intent);
        finish();
    }

    @Subscribe
    public void onEventMainThread(RetrofitError result) {
        Log.d("RetrofitError",result.getMessage());
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        intent.putExtra(FROM_ACCOUNT,false);
        startActivity(intent);
        finish();
    }

}
