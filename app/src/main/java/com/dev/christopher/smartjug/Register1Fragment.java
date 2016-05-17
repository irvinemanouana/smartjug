package com.dev.christopher.smartjug;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Christopher on 11/05/2016.
 */
public class Register1Fragment extends android.support.v4.app.Fragment implements View.OnClickListener{
    private RadioGroup genderRadioGroup;
    private RadioButton menRadioButton,womenRadioButton;
    private EditText emailEditText,nameEditText,lastnameEditText,passwordEditText,heightEditText,weightEditText;
    private Button registerButton;
    private String email,name,lastename,password,height,weight;

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
        Toast.makeText(getActivity(),"test",Toast.LENGTH_SHORT).show();
    }
}
