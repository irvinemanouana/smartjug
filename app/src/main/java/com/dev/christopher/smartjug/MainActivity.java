package com.dev.christopher.smartjug;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.christopher.smartjug.adapter.DataAdapter;
import com.dev.christopher.smartjug.dialog.CongratDialogFragment;
import com.dev.christopher.smartjug.dialog.LoaderDialog;
import com.dev.christopher.smartjug.gcm.RegistrationIntentService;
import com.dev.christopher.smartjug.manager.DataManager;
import com.dev.christopher.smartjug.result.BottleResult;
import com.dev.christopher.smartjug.result.DataResult;
import com.dev.christopher.smartjug.result.NetworkResult;
import com.dev.christopher.smartjug.result.UserResult;
import com.dev.christopher.smartjug.sharedPreferences.SavePreferences;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;



import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.ArrayList;
import java.util.Date;

import az.plainpie.PieView;
import io.socket.engineio.client.Socket;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private UserResult user;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private TextView name, lastname, email;
    private Toolbar toolbar;
    private GoogleApiClient googleBuilder;
    private Location lastLocation;
    private BottleResult bottleResult;
    private static final int WOMAN_TARGET_LITER = 220;
    private static final int MAN_TARGET_LITER = 300;
    private static final int DEFAULT_TARGET_LITER = 150;
    private Date dateOfDay;
    private LoaderDialog loaderDialog;
    private ListView listView;
    private PieView pieView;
    private DataAdapter dataAdapter;

    @Override
    protected void onStart() {
        googleBuilder.connect();
        dateOfDay = new Date();
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        googleBuilder.disconnect();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (googleBuilder == null) {
            googleBuilder = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        Intent gcmIntent = new Intent(getApplicationContext(), RegistrationIntentService.class);
        startService(gcmIntent);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        user = SavePreferences.newInstance(this).getUserInfo();
        bottleResult = SavePreferences.newInstance(this).getBottle();

        Log.d("Bottle _ID"+bottleResult.toString(), " :MAIN_BOTTLE");
        loaderDialog = LoaderDialog.newInstance();
        loaderDialog.show(getFragmentManager(),null);
        DataManager.getInstance().getDayData(bottleResult.get_id());

        listView =(ListView) findViewById(R.id.list_water_item);

        navigationView = (NavigationView) findViewById(R.id.menu_navigation);
        View headerDrawer = navigationView.inflateHeaderView(R.layout.header_drawer);
        name = (TextView) headerDrawer.findViewById(R.id.name);
        lastname = (TextView) headerDrawer.findViewById(R.id.lastname);
        email = (TextView) headerDrawer.findViewById(R.id.email);
        ImageView profilicon= (ImageView) headerDrawer.findViewById(R.id.profilicon);

        if (user !=null) {
            Log.d("testUser",user.toString());
            name.setText(user.getName());
            lastname.setText(user.getLastname());
            email.setText(user.getEmail());
            if (user.getPathPicture()!=null)
                profilicon.setImageBitmap(BitmapFactory
                        .decodeFile(user.getPathPicture()));
        }
        else {
            Toast.makeText(getApplicationContext(),"aie",Toast.LENGTH_SHORT).show();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.log_out:
                        SavePreferences.newInstance(getApplicationContext()).DestroyUserSession();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.my_account:
                        Intent accountIntent = new Intent(getApplicationContext(), AccountActivity.class);
                        startActivity(accountIntent);
                        break;
                    case R.id.settings:
                        Intent settingsIntent = new Intent(getApplicationContext(),SettingsActivity.class);
                        startActivity(settingsIntent);

                }
                return false;
            }
        });

        pieView = (PieView) findViewById(R.id.pieView);
        pieView.setPercentageBackgroundColor(getResources().getColor(R.color.colorPrimary));
        pieView.setInnerTextVisibility(View.VISIBLE);
        pieView.setInnerText("0%");
        pieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();
            }
        });
        pieView.setPercentageTextSize(40);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleBuilder);
        if (lastLocation !=null){
            Log.d("longitude:", String.valueOf(lastLocation.getLongitude()));
            Log.d("latitude:", String.valueOf(lastLocation.getLatitude()));
        }else {
            Log.d("longitude", "null");
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NetworkResult result){
        Log.d("onEvent",result.getContent());

        Toast.makeText(getApplicationContext(),result.getContent(),Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ArrayList<DataResult> dataResults){
        double liter;
        ArrayList<Double> doubles = new ArrayList<>();
        if (dataResults!=null || dataResults.size()>0){
            for (DataResult result:dataResults) {
                liter = result.getLiter();
                Log.d(String.valueOf(liter),"value");
                doubles.add(liter);
            }
            double sum =0;
            for (Double aDouble:doubles) {
                sum +=aDouble;
            }
            pieView.setmPercentage((float) sum*10);
            dataAdapter = new DataAdapter(getApplicationContext(),dataResults);
            listView.setAdapter(dataAdapter);
            Log.d("test sum",String.valueOf(sum));

        }
        loaderDialog.dismiss();
        Log.d("onEventThreadMode.MAIN", String.valueOf(dataResults.size()));

    }

}
