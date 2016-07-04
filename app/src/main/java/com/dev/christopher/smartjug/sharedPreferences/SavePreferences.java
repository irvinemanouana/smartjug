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


    private int PREF_MODE =0;
    private static final String PREF_NAME = "session";
    private static final String value_name="name";
    private static final String value_id="id";
    private static final String value_gender="sex";
    private static final String value_lastname="firstname";
    private static final String value_email="email";
    private static final String value_heigh="height";
    private static final String value_weight="weight";
    private static final String value_date_created ="date";
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

    public  void createUserSession(UserResult result){
        editor.putBoolean(value_exist,true);
        editor.putString(value_id,result.get_id());
        editor.putString(value_gender,result.getSex());
        editor.putString(value_name,result.getName());
        editor.putString(value_lastname,result.getLastname());
        editor.putString(value_pathPicture,result.getPathPicture());
        editor.putString(value_date_created,result.getCreated_at());
        editor.putInt(value_heigh,result.getHeight());
        editor.putInt(value_weight,result.getWeight());
        editor.putString(value_email,result.getEmail());
        editor.commit();
    }

    public UserResult getUserInfo(){
        return  new UserResult(preferences.getInt(value_weight,0),
                preferences.getString(value_id,null),
                preferences.getString(value_gender,null),
                preferences.getString(value_name,null),
                preferences.getString(value_lastname,null),
                preferences.getString(value_email,null),
                preferences.getString(value_date_created,null),
                null,
                preferences.getString(value_pathPicture,null),
                preferences.getInt(value_heigh,0));
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
