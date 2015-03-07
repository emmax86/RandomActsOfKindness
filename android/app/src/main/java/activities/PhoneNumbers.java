package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.dstny.activities.R;


public class PhoneNumbers extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_numbers);
    }

    public void phoneNumbersBackButtonPressed(View view) {
        Intent intent=new Intent(PhoneNumbers.this,Settings.class);
        startActivity(intent);
        finish();

    }
}
