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
            movementsArrayList.add("neck retraction");
        }
        if (neckExtension){
            movementsArrayList.add("neck extension");
        }
        if (scapRetraction){
            movementsArrayList.add("scapular retraction");
        }
        if (chestStretch){
            movementsArrayList.add("chest stretch");
        }
        if (ceilingReach){
            movementsArrayList.add("ceiling reach");
        }
        if (walk){
            movementsArrayList.add("walk");
        }
        if(squats){
            movementsArrayList.add("squats");
        }
        if(backbends){
            movementsArrayList.add("backbends");
        }
        if(hamstrings){
            movementsArrayList.add("hamstrings");
        }
        if(adductor){
            movementsArrayList.add("adductor");
        }
        if(figure4){
            movementsArrayList.add("figure 4");
        }
        if(hipFlexor){
            movementsArrayList.add("hip flecor stretch");
        }
        if(quadStretch){
            movementsArrayList.add("quadriceps stretch");
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
