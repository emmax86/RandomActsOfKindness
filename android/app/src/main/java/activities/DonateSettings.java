package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import data.PreferencesLayer;
import me.dstny.activities.R;


public class DonateSettings extends Activity {

    private TextView donateAmount;
    private EditText newDonateAmount;
    private DecimalFormat df;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_settings);
        donateAmount = (TextView) findViewById(R.id.current_donation_number);

        donateAmount = (TextView) findViewById(R.id.current_donation_number);
        newDonateAmount = (EditText) findViewById(R.id.donation_edit_text);

        df = new DecimalFormat("#.00");
        PreferencesLayer preferencesLayer = PreferencesLayer.getInstance();

        donateAmount.setText(String.valueOf(df.format(preferencesLayer.getDonationAmountPref())));
        newDonateAmount.setHint(String.valueOf(df.format(preferencesLayer.getDonationAmountPref())));

        newDonateAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    updateDonationFields();
                }
                return false;
            }
        });
    }

    public void donateBackButtonPressed(View view) {
        Intent intent = new Intent(DonateSettings.this, Settings.class );
        startActivity(intent);
        finish();
    }

    public void confirmDonationAmountPressed(View view) {
        updateDonationFields();
    }

    public void updateDonationFields() {
        if(!newDonateAmount.getText().equals("")) {
            PreferencesLayer preferencesLayer = PreferencesLayer.getInstance();
            preferencesLayer.setDonationAmountPref(Double.parseDouble(newDonateAmount.getText().toString()));
            donateAmount.setText(String.valueOf(df.format(preferencesLayer.getDonationAmountPref())));
            newDonateAmount.setText(String.valueOf(df.format(preferencesLayer.getDonationAmountPref())));
            newDonateAmount.setHint(String.valueOf(df.format(preferencesLayer.getDonationAmountPref())));
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(DonateSettings.this, Settings.class );
        startActivity(intent);
        finish();
    }
}
