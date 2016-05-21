package com.dev.christopher.smartjug.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dev.christopher.smartjug.model.User;

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
    private static final String value_exist="exist";

    public SavePreferences(Context _context) {
        this._context = _context;
        preferences = _context.getSharedPreferences(PREF_NAME,PREF_MODE);
        this.editor = preferences.edit();
    }

    public static SavePreferences newInstance(Context context){
        SavePreferences savePreferences = new SavePreferences(context);
        return savePreferences;
    }

    public  void createUserSession(User user){
        editor.putBoolean(value_exist,true);
        editor.putString(value_id,user.get_id());
        editor.putString(value_email,user.getEmail());
        editor.putString(value_gender,user.getSex());
        editor.putString(value_lastname,user.getLastname());
        editor.putString(value_name,user.getName());
        editor.putInt(value_heigh,user.getHeight());
        editor.putInt(value_weight,user.getWeight());
        editor.commit();
    }


    public  HashMap saveData(){
        HashMap hashMap = new HashMap<>();
        hashMap.put(value_id,preferences.getString(value_id,null));
        hashMap.put(value_email,preferences.getString(value_email,null));
        hashMap.put(value_heigh,preferences.getInt(value_heigh,0));
        hashMap.put(value_weight,preferences.getInt(value_weight,0));
        hashMap.put(value_gender,preferences.getString(value_gender,null));
        hashMap.put(value_name,preferences.getString(value_name,null));
        hashMap.put(value_lastname,preferences.getString(value_lastname,null));
        return hashMap;
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
