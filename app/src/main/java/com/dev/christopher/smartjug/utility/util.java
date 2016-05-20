package com.dev.christopher.smartjug.utility;

import android.util.Patterns;

/**
 * Created by Christopher on 12/05/2016.
 */
public class Util {


    public static boolean checkMailAddress(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean checkloginForm(String email,String password){
        if (email.equals("") || password.equals(""))
            return false;
        else
            return true;
    }
    public boolean CheckWeightAndHeight(String height,String weigh){
        if ((height.length()>3) || (weigh.length() > 3)){
            return false;
        }
        else
            return true;

    }



}
