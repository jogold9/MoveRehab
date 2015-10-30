package com.joshbgold.moveRehab.content;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.joshbgold.moveRehab.main.AlarmActivity;

public class Moves extends Activity {

    private static Context mAppContext = AlarmActivity.getAppContext();

    //these are used in the reminder if user does not make any selections in settings
    public String defaultMovements = "This is your reminder to walk and do squats.";
    public String rehabMovements = ""; //these is where the user's selected rehab movements will be concatenated/added to;

    //http://www.javatpoint.com/string-concatenation-in-java

    public boolean neckRetraction = false;
    public boolean neckExtension = false;

    public String neckRetractionString = "";
    public String neckExtensionString = "";

    public String getMoves(){
        String movements;

        neckRetraction = loadPrefs("neckRetraction", neckRetraction);
        neckExtension = loadPrefs("neckExtension", neckExtension);

        if (neckRetraction){
            neckRetractionString = "neck retraction ";
        }

        if (neckExtension){
            neckExtensionString = "neck extension ";
        }

        rehabMovements = neckRetractionString +  neckExtensionString;

        if(rehabMovements.equals("")) {
            movements = defaultMovements;
        }
        else movements = rehabMovements;

        return movements;
    }

    //get prefs
    private boolean loadPrefs(String key,boolean value) {
        SharedPreferences sharedPreferences = mAppContext.getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, value);
    }
}
