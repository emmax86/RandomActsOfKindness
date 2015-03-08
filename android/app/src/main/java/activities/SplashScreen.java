package activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import data.PreferencesLayer;
import me.dstny.activities.R;
import util.Util;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 1250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent accountPortalIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(accountPortalIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

        PreferencesLayer.initialize(getSharedPreferences("prefs", Context.MODE_PRIVATE));
        String id = PreferencesLayer.getInstance().getKey();
        Util.phoneNumbers = PreferencesLayer.getInstance().getPhoneNumbers();
        Util.emails = PreferencesLayer.getInstance().getEmails();
        if (id.equals("")) {
            id = "burgle";
            PreferencesLayer.getInstance().setKey(id);
        }
    }


}
