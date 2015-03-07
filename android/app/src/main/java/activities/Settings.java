package activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import me.dstny.activities.R;

public class Settings extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void settingsBackButtonPressed(View view) {
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void familyAndFriendsPressed(View view) {
        Intent intent = new Intent(Settings.this,PhoneNumbers.class);
        startActivity(intent);
        finish();
    }
    public void emailsPressed(View view) {
        Intent intent = new Intent(Settings.this,EmailAddresses.class);
        startActivity(intent);
        finish();
    }
    public void donationOptionsPressed(View view) {
        Intent intent = new Intent(Settings.this, DonateSettings.class );
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        Intent intent = new Intent(Settings.this, MainActivity.class );
        startActivity(intent);
        finish();
    }

    /*
    public void confirmButtonPressed(View view) {
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void cancelButtonPressed(View view) {
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    */
}
