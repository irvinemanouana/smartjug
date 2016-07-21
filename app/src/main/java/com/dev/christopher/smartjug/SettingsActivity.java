package com.dev.christopher.smartjug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.christopher.smartjug.result.BottleResult;
import com.dev.christopher.smartjug.result.UserResult;
import com.dev.christopher.smartjug.sharedPreferences.SavePreferences;

public class SettingsActivity extends AppCompatActivity {
    TextView emailTextView,botlle_idTextView;
    private ListView menuListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle(getString(R.string.settings_title));
        menuListView = (ListView) findViewById(R.id.settings_list);
        SavePreferences preferences = SavePreferences.newInstance(getApplicationContext());
        BottleResult bottleResult = preferences.getBottle();
        UserResult userResult = preferences.getUserInfo();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.settings_menu));
        menuListView.setAdapter(adapter);
        emailTextView =(TextView) findViewById(R.id.email_user);
        botlle_idTextView =(TextView) findViewById(R.id.bottle_user);
        emailTextView.setText(userResult.getEmail());
        botlle_idTextView.setText(bottleResult.get_id());



    }
}
