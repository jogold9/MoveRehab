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
    private boolean scapRetraction = false;
    private boolean chestStretch = false;
    private boolean ceilingReach = false;
    private boolean walk = false;
    private boolean squats = false;
    private boolean backbends = false;
    private boolean hamstrings = false;
    private boolean adductor = false;
    private boolean figure4 = false;
    private boolean hipFlexor = false;
    private boolean quadStretch = false;
    public String neckRetractionString = "";
    public String neckExtensionString = "";
    public String scapRetractionString = "";
    public String chestStretchString = "";
    public String ceilingReachString = "";
    public String walkString = "";
    public String squatsString = "";
    public String backbendsString = "";
    public String hamstringsString = "";
    public String adductorString = "";
    public String figure4String = "";
    public String hipFlexorString = "";
    public String quadStretchString = "";

    public String getMoves(){
        String movements;

        neckRetraction = loadPrefs("neckRetraction", neckRetraction);
        neckExtension = loadPrefs("neckExtension", neckExtension);
        neckRetraction = loadPrefs("neckRetraction", neckRetraction);
        neckExtension = loadPrefs("neckExtension", neckExtension);
        scapRetraction = loadPrefs("scapRetraction", scapRetraction);
        chestStretch = loadPrefs("chestStretch", chestStretch);
        ceilingReach = loadPrefs("ceilingReach", ceilingReach);
        walk = loadPrefs("walk", walk);
        squats = loadPrefs("squats", squats);
        backbends = loadPrefs("backbends", backbends);
        hamstrings = loadPrefs("hamstrings", hamstrings);
        adductor = loadPrefs("adductor", adductor);
        figure4 = loadPrefs("figure4", figure4);
        hipFlexor = loadPrefs("hipFlexor", hipFlexor);
        quadStretch = loadPrefs("quadStretch", quadStretch);

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
