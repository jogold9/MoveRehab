package com.joshbgold.moveRehab.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.joshbgold.moveRehab.R;
import com.joshbgold.moveRehab.backend.Prefs;
import com.joshbgold.moveRehab.billing.IabHelper;
import com.joshbgold.moveRehab.billing.IabResult;


public class SettingsActivity extends Activity {

    private SeekBar volumeControl = null;
    private float volume = (float) 0.50;
    private String repeatIntervalAsString = "";
    private String customReminderString = "";
    private int repeatIntervalInHours = 0;  //Number of hours that user wants alarm to repeat at (optional)
    private boolean blockWeekendAlarms = false;
    private boolean blockNonWorkHoursAlarms = false;
    private boolean neckRetraction = false;
    private boolean neckExtension = false;
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
    Prefs prefs = new Prefs();
    
    //for in-app billing (IAB). See developer.android.com/training/in-app-billing/preparing-iab-app.html#Connect
    IabHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //for in-app billing... I had this in key stored in separate class but accidentally deleted in Github merge
        String PublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApMimWebquXt5TkEPfgId874JGQrfgaCR9XLawRkZEbbvEQafr75JJK1uT9uAiRAA/nDCNN6VaLxWDhr4TiEwRP6be8B0jz/0xLRWgvu940RMSfpgMAdWz5ecSp0fFXlDRWQtP9mb4/9fj/34JzjCWBhw6dx+eQDyREhDbqyVbbAwT+f9ydnkM1RflJQPAKd76YJaElgjStDL7GhUOX23RfzWygSaBmYu8Si/NlnPbIZxcYT55kx0DaIjQF8NmWqvUXV8MW/OHS0ICYguvlAIOPdP/HG25RCDm7VMUiIPfRi8ycyep7KmLiWNUQsl1JmI/rXNFhNqTb4r3vUd248MBwIDAQAB";

        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(this, (PublicKey));

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    Log.d("In-App Billing Error", "Unfortunately there was a problem setting up In-app Billing: " + result);
                }
                // Hooray, IAB is fully set up!
            }
        });

        final EditText repeatIntervalEditText = (EditText) findViewById(R.id.repeatIntervalInMinutes);
        final EditText CustomReminderEditText = (EditText) findViewById(R.id.AddYourOwn);
        final Button backButton = (Button) findViewById(R.id.backButton);
        final CheckBox blockWeekendsCheckBox = (CheckBox)findViewById(R.id.blockWeekends);
        final CheckBox blockNonWorkHoursCheckBox = (CheckBox)findViewById(R.id.blockNonWorkDayHours);
        final CheckBox neckRetractionCheckBox = (CheckBox)findViewById(R.id.NeckRetraction);
        final CheckBox neckExtensionCheckbox = (CheckBox)findViewById(R.id.NeckExtension);
        final CheckBox scapRetractionCheckBox = (CheckBox)findViewById(R.id.ScapularRetraction);
        final CheckBox chestStretchCheckBox = (CheckBox)findViewById(R.id.ChestStretch);
        final CheckBox ceilingReachCheckbox = (CheckBox)findViewById(R.id.ReachToCeiling);
        final CheckBox WalkCheckBox = (CheckBox)findViewById(R.id.Walk);
        final CheckBox SquatsCheckBox = (CheckBox)findViewById(R.id.Squats);
        final CheckBox BackBendsCheckBox = (CheckBox)findViewById(R.id.StandingBackBends);
        final CheckBox HamstringsCheckBox = (CheckBox)findViewById(R.id.Hamstrings);
        final CheckBox adductorCheckBox = (CheckBox)findViewById(R.id.adductor);
        final CheckBox figure4CheckBox = (CheckBox)findViewById(R.id.figure4);
        final CheckBox hipFlexorCheckBox = (CheckBox)findViewById(R.id.hipFlexor);
        final CheckBox quadStretchCheckBox = (CheckBox)findViewById(R.id.standingQuadStretch);
        final SeekBar volumeControl = (SeekBar)findViewById(R.id.volumeSeekBar);

        repeatIntervalInHours = prefs.loadPrefs("repeatIntervalKey", repeatIntervalInHours);
        customReminderString = prefs.loadPrefs("customReminder", customReminderString);
        blockWeekendAlarms = prefs.loadPrefs("noWeekendsKey", blockWeekendAlarms);
        blockNonWorkHoursAlarms = prefs.loadPrefs("workHoursOnlyKey", blockNonWorkHoursAlarms);
        neckRetraction = prefs.loadPrefs("neckRetraction", neckRetraction);
        neckExtension = prefs.loadPrefs("neckExtension", neckExtension);
        scapRetraction = prefs.loadPrefs("scapRetraction", scapRetraction);
        chestStretch = prefs.loadPrefs("chestStretch", chestStretch);
        ceilingReach = prefs.loadPrefs("ceilingReach", ceilingReach);
        walk = prefs.loadPrefs("walk", walk);
        squats = prefs.loadPrefs("squats", squats);
        backbends = prefs.loadPrefs("backbends", backbends);
        hamstrings = prefs.loadPrefs("hamstrings", hamstrings);
        adductor = prefs.loadPrefs("adductor", adductor);
        figure4 = prefs.loadPrefs("figure4", figure4);
        hipFlexor = prefs.loadPrefs("hipFlexor", hipFlexor);
        quadStretch = prefs.loadPrefs("quadStretch", quadStretch);
        volume = prefs.loadPrefs("volumeKey", volume);

        volumeControl.setProgress((int) (volume * 100));
        repeatIntervalEditText.setText(repeatIntervalInHours + "");

        if(customReminderString != null && !customReminderString.isEmpty()) {
            CustomReminderEditText.setText(customReminderString);
        }

        if (blockWeekendAlarms) {
            blockWeekendsCheckBox.setChecked(true);
        } else {
            blockWeekendsCheckBox.setChecked(false);
        }

        if (blockNonWorkHoursAlarms) {
            blockNonWorkHoursCheckBox.setChecked(true);
        } else {
            blockNonWorkHoursCheckBox.setChecked(false);
        }

        if (neckRetraction){
            neckRetractionCheckBox.setChecked(true);
        } else {
            neckRetractionCheckBox.setChecked(false);
        }

        if (neckExtension){
            neckExtensionCheckbox.setChecked(true);
        } else {
            neckExtensionCheckbox.setChecked(false);
        }

        if (scapRetraction){
            scapRetractionCheckBox.setChecked(true);
        } else {
            scapRetractionCheckBox.setChecked(false);
        }

        if (chestStretch){
            chestStretchCheckBox.setChecked(true);
        } else {
            chestStretchCheckBox.setChecked(false);
        }

        if (ceilingReach) {
            ceilingReachCheckbox.setChecked(true);
        } else {
            ceilingReachCheckbox.setChecked(false);
        }

        if (walk) {
            WalkCheckBox.setChecked(true);
        } else {
            WalkCheckBox.setChecked(false);
        }

        if (squats){
            SquatsCheckBox.setChecked(true);
        } else {
            SquatsCheckBox.setChecked(false);
        }

        if (backbends){
            BackBendsCheckBox.setChecked(true);
        } else {
            BackBendsCheckBox.setChecked(false);
        }

        if (hamstrings){
            HamstringsCheckBox.setChecked(true);
        } else {
            HamstringsCheckBox.setChecked(false );
        }
        if (adductor){
            adductorCheckBox.setChecked(true);
        } else {
            adductorCheckBox.setChecked(false);
        }

        if (figure4){
            figure4CheckBox.setChecked(true);
        } else {
            figure4CheckBox.setChecked(false);
        }

        if(hipFlexor){
            hipFlexorCheckBox.setChecked(true);
        } else {
            hipFlexorCheckBox.setChecked(false);
        }

        if(quadStretch){
            quadStretchCheckBox.setChecked(true);
        } else {
            quadStretchCheckBox.setChecked(false);
        }

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                volume = (float) (((double) (progressChanged)) / 100);  //allows division w/ decimal results instead of integer results
                prefs.savePrefs("volumeKey", volume);
                Toast.makeText(SettingsActivity.this, "Audio volume is set to: " + progressChanged + " %", Toast.LENGTH_SHORT).show();
            }
        });

        View.OnClickListener goBack = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //The following code saves user preferences
                prefs.savePrefs("volumeKey", volume);

                if (blockWeekendsCheckBox.isChecked()){
                    prefs.savePrefs("noWeekendsKey", true);
                } else{
                    prefs.savePrefs("noWeekendsKey", false);
                }

                if (blockNonWorkHoursCheckBox.isChecked()){
                    prefs.savePrefs("workHoursOnlyKey", true);
                } else {
                    prefs.savePrefs("workHoursOnlyKey", false);
                }

                if (neckRetractionCheckBox.isChecked()){
                    prefs.savePrefs("neckRetraction", true);
                } else {
                    prefs.savePrefs("neckRetraction", false);
                }

                if (neckExtensionCheckbox.isChecked()){
                    prefs.savePrefs("neckExtension", true);
                } else {
                    prefs.savePrefs("neckExtension", false);
                }

                if (scapRetractionCheckBox.isChecked()){
                    prefs.savePrefs("scapRetraction", true);
                } else {
                    prefs.savePrefs("scapRetraction", false);
                }

                if (chestStretchCheckBox.isChecked()) {
                    prefs.savePrefs("chestStretch", true);
                } else {
                    prefs.savePrefs("chestStretch", false);
                }

                if (ceilingReachCheckbox.isChecked()) {
                    prefs.savePrefs("ceilingReach", true);
                } else {
                    prefs.savePrefs("ceilingReach", false);
                }

                if (WalkCheckBox.isChecked()){
                    prefs.savePrefs("walk", true);
                } else {
                    prefs.savePrefs("walk", false);
                }

                if (SquatsCheckBox.isChecked()){
                    prefs.savePrefs("squats", true);
                } else {
                    prefs.savePrefs("squats", false);
                }

                if (BackBendsCheckBox.isChecked()){
                    prefs.savePrefs("backbends", true);
                } else {
                    prefs.savePrefs("backbends", false);
                }

                if(HamstringsCheckBox.isChecked()){
                    prefs.savePrefs("hamstrings", true);
                } else {
                    prefs.savePrefs("hamstrings", false);
                }

                if(adductorCheckBox.isChecked()){
                    prefs.savePrefs("adductor", true);
                } else {
                    prefs.savePrefs("adductor", false);
                }

                if(figure4CheckBox.isChecked()){
                    prefs.savePrefs("figure4", true);
                } else {
                    prefs.savePrefs("figure4", false);
                }

                if(hipFlexorCheckBox.isChecked()){
                    prefs.savePrefs("hipFlexor", true);
                } else {
                    prefs.savePrefs("hipFlexor", false);
                }

                if(quadStretchCheckBox.isChecked()){
                    prefs.savePrefs("quadStretch", true);
                } else {
                    prefs.savePrefs("quadStretch", false);
                }

                blockWeekendAlarms = prefs.loadPrefs("noWeekendsKey", blockWeekendAlarms);
                blockNonWorkHoursAlarms = prefs.loadPrefs("workHoursOnlyKey", blockNonWorkHoursAlarms);
                neckRetraction = prefs.loadPrefs("neckRetraction", neckRetraction);
                neckExtension = prefs.loadPrefs("neckExtension", neckExtension);
                scapRetraction = prefs.loadPrefs("scapRetraction", scapRetraction);
                chestStretch = prefs.loadPrefs("chestStretch", chestStretch);
                ceilingReach = prefs.loadPrefs("ceilingReach", ceilingReach);
                walk = prefs.loadPrefs("walk", walk);
                squats = prefs.loadPrefs("squats", squats);
                backbends = prefs.loadPrefs("backbends", backbends);
                hamstrings = prefs.loadPrefs("hamstrings", hamstrings);
                adductor = prefs.loadPrefs("adductor", adductor);
                figure4 = prefs.loadPrefs("figure4", figure4);
                hipFlexor = prefs.loadPrefs("hipFlexor", hipFlexor);
                quadStretch = prefs.loadPrefs("quadStretch", quadStretch);

                customReminderString = CustomReminderEditText.getText() + "";

                repeatIntervalAsString = repeatIntervalEditText.getText() + "";

               prefs.savePrefs("customReminder", customReminderString);

                try {
                    if (repeatIntervalAsString.equals("")){
                        repeatIntervalInHours = 0;
                        prefs.savePrefs("repeatIntervalKey", repeatIntervalInHours);
                        finish();
                    }
                    else {
                        Integer repeatIntervalAsInt = Integer.parseInt(repeatIntervalAsString);
                        if (repeatIntervalAsInt != 0 && repeatIntervalAsInt < 1 || repeatIntervalAsInt > 24){
                            Toast.makeText(SettingsActivity.this, "Please enter a number between 1 and 24 for the repeat interval, or leave blank " +
                                            "for a one-time alarm.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            repeatIntervalInHours = Integer.valueOf(repeatIntervalAsString);
                            prefs.savePrefs("repeatIntervalKey", repeatIntervalInHours);
                            finish();
                        }
                    }
                } catch (NumberFormatException exception) {
                    Toast.makeText(SettingsActivity.this, "Please enter a number between 1 and 24 for the repeat interval, or leave blank for a " +
                            "one-time alarm.", Toast
                            .LENGTH_LONG).show();
                }
            }
        };
        backButton.setOnClickListener(goBack);

    }

/*    //save prefs
    public void prefs.savePrefs(String key, float value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void prefs.savePrefs(String key, int value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void prefs.savePrefs(String key, boolean value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void prefs.savePrefs(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }*/

/*    //get prefs
    private float prefs.loadPrefs(String key,float value) {
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
         return sharedPreferences.getFloat(key, value);
    }

    private int prefs.loadPrefs(String key,int value) {
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, value);
    }

    private boolean prefs.loadPrefs(String key,boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, value);
    }

    private String prefs.loadPrefs(String key,String value) {
        SharedPreferences sharedPreferences = getSharedPreferences("MoveAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, value);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }
}

