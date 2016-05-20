package com.dev.christopher.smartjug;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.dev.christopher.smartjug.generator.ServiceGenerator;
import com.dev.christopher.smartjug.interfaceClient.UserInterfaceClient;
import com.dev.christopher.smartjug.model.LoginModel;
import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.utility.Util;



import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton,registerButton;
    private EditText emailEditText,passwEditText;
    private String email,password;


    @Override
    protected void onStart() {
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
                final View view = v;
                email = emailEditText.getText().toString();
                password = passwEditText.getText().toString();

                if (Util.checkloginForm(email,password)){

                    if (Util.checkMailAddress(email)){
                        UserInterfaceClient client = ServiceGenerator.createService(UserInterfaceClient.class);
                        LoginModel loginModel = new LoginModel(email,password);
                        client.getUserInfo(loginModel, new Callback<User>() {
                            @Override
                            public void success(User user, Response response) {
                                Log.d("user",user.toString());
                                Log.d("Response",String.valueOf(response));
                                if (user.get_id() ==null)
                                    Snackbar.make(view,R.string.err_message_invalid_data,Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.d("error",String.valueOf(error));
                            }
                        });
                    }
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
