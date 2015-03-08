package activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import data.PreferencesLayer;
import me.dstny.activities.R;
import networking.ConnectionHandler;
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
            new RegisterTask().execute();
        }
    }

    public class RegisterTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
            String deviceId = deviceUuid.toString();

            try {
                String response = ConnectionHandler.register(deviceId);
                if (response.startsWith("201:")) {
                    response = response.replace("201:", "");
                    JSONObject jsonObject = new JSONObject(response);
                    String id = jsonObject.getString("id");
                    PreferencesLayer.getInstance().setKey(id);
                    return true;
                }
                else {
                    return false;
                }
            } catch (IOException | JSONException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                Toast.makeText(SplashScreen.this, "Could not register device. Using debug mode!", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
