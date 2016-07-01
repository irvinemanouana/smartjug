package com.dev.christopher.smartjug.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.result.UserResult;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Christopher on 21/05/2016.
 */
public class SavePreferences {
    private  static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private Context _context;

    public   User userSave;

    private int PREF_MODE =0;
    private static final String PREF_NAME = "session";
    private static final String value_name="name";
    private static final String value_id="id";
    private static final String value_gender="sex";
    private static final String value_lastname="firstname";
    private static final String value_email="email";
    private static final String value_heigh="height";
    private static final String value_weight="weight";
    private static final String value_exist="exist";
    private static final String value_pathPicture = "pathPicture";

    public SavePreferences(Context _context) {
        this._context = _context;
        preferences = _context.getSharedPreferences(PREF_NAME,PREF_MODE);
        this.editor = preferences.edit();
    }

    public static SavePreferences newInstance(Context context){
        SavePreferences savePreferences = new SavePreferences(context);
        return savePreferences;
    }

    public  void createUserSession(){
        editor.putBoolean(value_exist,true);
        editor.commit();
    }


    public  void DestroyUserSession(){
        editor.clear();
        editor.commit();
    }

    public  boolean checkLogin(){
        // Check login status

        if(preferences.getBoolean(value_exist,false)==true){
            Log.d("session","true");
            return true;
        }else {
            Log.d("session","false");
            return false;
        }

    }
}
