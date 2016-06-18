package com.dev.christopher.smartjug;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.sharedPreferences.SavePreferences;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private User user;
    private ListView drawerListView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private TextView nameTextView, lastnameTextView, emailTextView;
    private Toolbar toolbar;
    String userMail;
    private GoogleApiClient googleBuilder;
    private Location lastLocation;

    @Override
    protected void onStart() {
        googleBuilder.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleBuilder.disconnect();
        super.onStop();
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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        user = SavePreferences.newInstance(getApplicationContext()).getUserData();
        userMail = user.getName();


       /* nameTextView = (TextView) findViewById(R.id.name_area);
        lastnameTextView = (TextView) findViewById(R.id.lastname_area);
        emailTextView = (TextView) findViewById(R.id.email_area);

        nameTextView.setText(userMail);
        lastnameTextView.setText(user.getLastname());
        emailTextView.setText(user.getEmail());
*/
        drawerListView = (ListView) findViewById(R.id.drawer_list);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getTextArray(R.array.item_menu));
        drawerListView.setAdapter(adapter);
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                if (item.equals("Mon compte")){
                    Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                    startActivity(intent);
                }
                else if (item.equals("Paramètre"))
                    Log.d("item", item);
                else if (item.equals("Déconnexion")) {
                    SavePreferences.newInstance(getApplicationContext()).DestroyUserSession();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
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
            Log.d("longitude", String.valueOf(lastLocation.getLatitude()));
        }else {
            Log.d("longitude", String.valueOf(lastLocation.getLatitude()));
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
