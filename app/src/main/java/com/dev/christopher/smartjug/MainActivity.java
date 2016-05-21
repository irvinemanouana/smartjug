package com.dev.christopher.smartjug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dev.christopher.smartjug.sharedPreferences.SavePreferences;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private HashMap dataUserMap;
    private ListView drawerListView;

    @Override
    protected void onStart() {
        dataUserMap = SavePreferences.newInstance(getApplicationContext()).saveData();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
