package com.dev.christopher.smartjug;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       /* Register1Fragment fragment = new Register1Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(fragment,null)
                .addToBackStack(null)
                .commit();*/

    }
}
