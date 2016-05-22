package com.dev.christopher.smartjug;

import android.content.Intent;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.sharedPreferences.SavePreferences;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private User user;
    private ListView drawerListView;
    private ActionBarDrawerToggle barDrawerToggle;
    private TextView nameTextView,lastnameTextView,emailTextView;
    String userMail;
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = SavePreferences.newInstance(getApplicationContext()).getUserData();
        userMail = user.getName();
        Log.d("testMain",userMail);
        nameTextView =(TextView) findViewById(R.id.name_area);
        lastnameTextView =(TextView) findViewById(R.id.lastname_area);
        emailTextView =(TextView) findViewById(R.id.email_area);

        nameTextView.setText(userMail);
        lastnameTextView.setText(user.getLastname());
        emailTextView.setText(user.getEmail());

        drawerListView = (ListView) findViewById(R.id.drawer_list);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,getResources().getTextArray(R.array.item_menu));
        drawerListView.setAdapter(adapter);
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                if (item.equals("Mon compte"))
                    Log.d("item",item);
                else if (item.equals("Paramètre"))
                    Log.d("item",item);
                else if (item.equals("Déconnexion")) {
                    SavePreferences.newInstance(getApplicationContext()).DestroyUserSession();
                    Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}
