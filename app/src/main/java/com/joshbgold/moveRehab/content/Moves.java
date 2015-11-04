package com.joshbgold.moveRehab.content;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.joshbgold.moveRehab.main.AlarmActivity;

import java.util.ArrayList;

public class Moves extends Activity {

    private static Context mAppContext = AlarmActivity.getAppContext();

    public ArrayList<String> movementsArrayList = new ArrayList<String>();

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

    public StringBuilder getMoves(){
        StringBuilder movements = new StringBuilder();

        neckRetraction = loadPrefs("neckRetraction", false);
        neckExtension = loadPrefs("neckExtension", false);
        scapRetraction = loadPrefs("scapRetraction", false);
        chestStretch = loadPrefs("chestStretch", false);
        ceilingReach = loadPrefs("ceilingReach", false);
        walk = loadPrefs("walk", false);
        squats = loadPrefs("squats", false);
        backbends = loadPrefs("backbends", false);
        hamstrings = loadPrefs("hamstrings", false);
        adductor = loadPrefs("adductor", false);
        figure4 = loadPrefs("figure4", false);
        hipFlexor = loadPrefs("hipFlexor", false);
        quadStretch = loadPrefs("quadStretch", false);

        if(adductor){
            movementsArrayList.add("adductor");
        }
        if(backbends){
            movementsArrayList.add("backbends");
        }
        if (ceilingReach){
            movementsArrayList.add("ceiling reach");
        }
        if (chestStretch){
            movementsArrayList.add("chest stretch");
        }
        if(figure4){
            movementsArrayList.add("figure 4");
        }
        if(hamstrings){
            movementsArrayList.add("hamstrings");
        }
        if(hipFlexor){
            movementsArrayList.add("hip flecor stretch");
        }
        if (neckExtension){
            movementsArrayList.add("neck extension");
        }
        if (neckRetraction){
            movementsArrayList.add("neck retraction");
        }
        if(quadStretch){
            movementsArrayList.add("quadriceps stretch");
        }
        if (scapRetraction){
            movementsArrayList.add("scapular retraction");
        }
        if(squats){
            movementsArrayList.add("squats");
        }
        if (walk){
            movementsArrayList.add("walk");
        }

        //here we get the ArrayList content that the user specified in the checkboxes
        if (movementsArrayList.isEmpty()){
            movements.append("Walk now! It's critical for your brain health and physical health.");
        }
        else if (movementsArrayList.size() == 1){
            movements.append(movementsArrayList.get(0));
        }

        //adds the 1st alement of the arrayList to the string. Then for the rest, it adds commas space and the string
        else {
            movements.append(movementsArrayList.get(0).toString());
            for (int i = 1; i < movementsArrayList.size(); i++){
                movements.append(", ").append(movementsArrayList.get(i).toString());
            }
        }

        return movements;
    }

    //get prefs
    private boolean loadPrefs(String key,boolean value) {
        SharedPreferences sharedPreferences = mAppContext.getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, value);
    }
}
