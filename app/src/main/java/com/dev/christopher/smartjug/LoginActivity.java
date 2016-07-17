package com.dev.christopher.smartjug;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.dev.christopher.smartjug.dialog.CongratDialogFragment;
import com.dev.christopher.smartjug.dialog.LoaderDialog;
import com.dev.christopher.smartjug.generator.ServiceGenerator;
import com.dev.christopher.smartjug.interfaceClient.UserInterfaceClient;
import com.dev.christopher.smartjug.manager.DataManager;
import com.dev.christopher.smartjug.model.LoginModel;
import com.dev.christopher.smartjug.model.OwnerModel;
import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.result.BottleResult;
import com.dev.christopher.smartjug.result.ErrorResult;
import com.dev.christopher.smartjug.result.UserResult;
import com.dev.christopher.smartjug.sharedPreferences.SavePreferences;
import com.dev.christopher.smartjug.utility.Util;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton,registerButton;
    private EditText emailEditText,passwEditText;
    private String email,password;
    private DataManager dataManager;
    private LoaderDialog loaderDialog;
    private static final String FROM_ACCOUNT = "ACCOUNT";
    private UserResult userResult;

    @Override
    protected void onStart() {
        EventBus.clearCaches();
        EventBus.getDefault().register(this);
        loaderDialog = LoaderDialog.newInstance();
        boolean userStatu = SavePreferences.newInstance(getApplicationContext()).checkLogin();
        if (userStatu){
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        emailEditText.setText("");
        passwEditText.setText("");
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dataManager = DataManager.getInstance();
        emailEditText = (EditText) findViewById(R.id.ledmail);
        passwEditText = (EditText) findViewById(R.id.ledpass);
        loginButton = (Button) findViewById(R.id.button_login);
        registerButton = (Button) findViewById(R.id.button_register);
        Intent intent = getIntent();

        if (intent.hasExtra(FROM_ACCOUNT)){
            Boolean link_success= intent.getBooleanExtra(FROM_ACCOUNT,true);
            if (link_success){
                CongratDialogFragment.newInstance().show(getFragmentManager(),null);
            }
            else{

            }

        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                email = emailEditText.getText().toString();
                password = passwEditText.getText().toString();

                if (Util.checkloginForm(email,password)){

                    if (Util.checkMailAddress(email)){
                        loaderDialog.show(getFragmentManager(),null);
                        LoginModel loginModel = new LoginModel(email,password);
                        dataManager.Login(loginModel);

                    }
                    else {
                        Snackbar.make(v, R.string.err_message_invalid_mail, Snackbar.LENGTH_SHORT).show();
                    }
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
    @Subscribe
    public void onEventMainThread(UserResult result){
        this.userResult = result;
        Log.d(result.toString(), ":UserMain");
        DataManager.getInstance().foudBottle(new OwnerModel(result.get_id()));
    }
    @Subscribe
    public void onEvent(BottleResult bottleResult){
        Log.d(bottleResult.toString()," bottleMain");
        loaderDialog.dismiss();
        SavePreferences.newInstance(getApplicationContext()).createUserSession(userResult,bottleResult);
        dataManager.setUserResult(userResult);
        EventBus.getDefault().unregister(this);
        loaderDialog.dismiss();
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Subscribe
    public void onEventMainThread(RetrofitError error){
        Log.d(error.getMessage()," :Retrofit");
        loaderDialog.dismiss();
    }


    @Subscribe
    public void onEvent(ErrorResult result){
        loaderDialog.dismiss();
        Toast.makeText(getApplicationContext(),R.string.wrong,Toast.LENGTH_SHORT).show();
    }
}
