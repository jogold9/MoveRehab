package com.joshbgold.moveRehab.backend;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JoshG on 11/9/2015.
 */
public class Prefs extends Activity {

    //get prefs
    public boolean loadPrefs(String key, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, value);
    }

    //get prefs
    public int loadPrefs(String key, int value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getInt(key, value);
    }

    //get prefs
    public float loadPrefs(String key, float value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, value);
    }

    public String loadPrefs(String key,String value) {
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, value);
    }

    //save prefs
    public void savePrefs(String key, float value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void savePrefs(String key, int value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void savePrefs(String key, boolean value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void savePrefs(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
