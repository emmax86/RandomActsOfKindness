package activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

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

    public void phoneNumberAddPressed(View view) {
        AlertDialog.Builder builder = new   AlertDialog.Builder(PhoneNumbers.this);
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Phone Number");
        alertDialog.setMessage("Enter a phone number for a good friend or family member.");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        alertDialog.setView(input);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        // code that checks to see if the email is valid or used by another account
                        // if it passes checks make it the new email and close alert dialog

                    }

                });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                    }
                });
        alertDialog.show();
    }

    public void onBackPressed() {
        Intent intent = new Intent(PhoneNumbers.this, Settings.class );
        startActivity(intent);
        finish();
    }
}
