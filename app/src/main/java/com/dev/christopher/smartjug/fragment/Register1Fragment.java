package com.dev.christopher.smartjug.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dev.christopher.smartjug.R;
import com.dev.christopher.smartjug.dialog.MessageAlertDialog;
import com.dev.christopher.smartjug.generator.ServiceGenerator;
import com.dev.christopher.smartjug.interfaceClient.UserInterfaceClient;
import com.dev.christopher.smartjug.model.RegisterModel;
import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.utility.Tag;
import com.dev.christopher.smartjug.utility.Util;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Christopher on 11/05/2016.
 */
public class Register1Fragment extends android.support.v4.app.Fragment implements View.OnClickListener{
    private RadioGroup genderRadioGroup;
    private RadioButton menRadioButton,womenRadioButton;
    private EditText emailEditText,nameEditText,lastnameEditText,passwordEditText,heightEditText,weightEditText;
    private Button registerButton;
    private String email,name,lastename,password,gender;
    int height,weight;
    private FragmentManager fragmentManager;
    private static final String FRAGMENT_TAG = Tag.REGISTER_TAG;

    @Override
    public void onStart() {
        fragmentManager = getActivity().getSupportFragmentManager();
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     View view =inflater.inflate(R.layout.frag_register,container,false);
        genderRadioGroup = (RadioGroup) view.findViewById(R.id.gender);
        menRadioButton = (RadioButton) view.findViewById(R.id.men);
        womenRadioButton = (RadioButton) view.findViewById(R.id.women);

        emailEditText =(EditText) view.findViewById(R.id.email);
        passwordEditText =(EditText) view.findViewById(R.id.password);
        nameEditText =(EditText) view.findViewById(R.id.name);
        lastnameEditText =(EditText) view.findViewById(R.id.lastname);
        heightEditText = (EditText) view.findViewById(R.id.height);
        weightEditText = (EditText) view.findViewById(R.id.weight);

        registerButton = (Button)view.findViewById(R.id.register);
        registerButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

       if (isValidForm()){
          initString();
           if (Util.checkMailAddress(email)) {
               UserInterfaceClient service = ServiceGenerator.createService(UserInterfaceClient.class);

               RegisterModel model = new RegisterModel(gender,name,lastename,email,password,height,weight);
               Log.d("register",model.toString());
               service.createUser(model, new Callback<User>() {
                   @Override
                   public void success(User user, Response response) {
                       /*MessageAlertDialog.newInstance(getString(R.string.account_created),FRAGMENT_TAG).show(fragmentManager,null);*/
                   }

                   @Override
                   public void failure(RetrofitError error) {
                        Log.d("error",error.getMessage());
                   }
               });

           }
           else {
               Snackbar.make(v,getString(R.string.err_message_invalid_data_2),Snackbar.LENGTH_SHORT).show();
           }
       }
       else {
           Toast.makeText(getActivity(),"no",Toast.LENGTH_SHORT).show();
       }

    }

    public boolean isValidForm(){
        if (emailEditText.getText().toString().equals("") ||passwordEditText.getText().toString().equals("")
                ||nameEditText.getText().toString().equals("")||lastnameEditText.getText().toString().equals("")
                ||heightEditText.getText().toString().equals("")||weightEditText.getText().toString().equals("")
                ||genderRadioGroup.getCheckedRadioButtonId()==-1)
            return false;
        else
            return true;

    }

    public void initString(){
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        name = nameEditText.getText().toString();
        lastename = lastnameEditText.getText().toString();
        height = Integer.parseInt(heightEditText.getText().toString());
        weight = Integer.parseInt(weightEditText.getText().toString());
        if (menRadioButton.isChecked())
            gender = "men";
        else
            gender = "women";
    }
}
