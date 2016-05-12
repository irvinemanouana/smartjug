package com.dev.christopher.smartjug;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.christopher.smartjug.utility.Util;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton,registerButton;
    private EditText emailEditText,passwEditText;
    private Util util;
    private String email,password;

    @Override
    protected void onStart() {
        util = new Util();
        super.onStart();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText) findViewById(R.id.ledmail);
        passwEditText = (EditText) findViewById(R.id.ledpass);
        loginButton = (Button) findViewById(R.id.button_login);
        registerButton = (Button) findViewById(R.id.button_register);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEditText.getText().toString();
                password = passwEditText.getText().toString();

                if (util.checkloginForm(email,password)){

                    if (util.checkMailAddress(email))
                        /*
                            call the web service here
                         */
                        Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
                    else
                        Snackbar.make(v,R.string.err_message_invalid_mail,Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(v,R.string.err_message_empty_form,Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
