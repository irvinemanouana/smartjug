package com.dev.christopher.smartjug.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Christopher on 21/07/16.
 */
public class waterPreferences {
    private  static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private Context _context;


    private int PREF_MODE =0;
    private static final String PREF_NAME = "water";

    public waterPreferences(Context _context) {
        this._context = _context;
        preferences = _context.getSharedPreferences(PREF_NAME,PREF_MODE);
        this.editor = preferences.edit();
    }
    public static waterPreferences newInstance(Context _context){
        return new waterPreferences(_context);
    }


}
