package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.dstny.activities.R;


public class DonateSettings extends Activity {

    private TextView donateAmount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_settings);
        donateAmount = (TextView) findViewById(R.id.current_donation_number);





    }

    public void donateBackButtonPressed(View view) {
        Intent intent = new Intent(DonateSettings.this, Settings.class );
        startActivity(intent);
        finish();

    }
}
