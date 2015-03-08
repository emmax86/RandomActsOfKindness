package activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import data.PreferencesLayer;
import me.dstny.activities.R;
import util.Util;

public class EmailAddresses extends Activity {

    private ListView listView;
    private ArrayAdapter listAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emails_setting);
        listView = (ListView) findViewById(R.id.listViewsOfEmails);
        listAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.item_label, Util.emails);
        listView.setAdapter(listAdapter);
    }

    public void emailBackButtonPressed(View view) {
        Intent intent = new Intent(EmailAddresses.this, Settings.class );
        startActivity(intent);
        finish();
    }

    public void emailAddPressed(View view) {
        AlertDialog.Builder builder = new   AlertDialog.Builder(EmailAddresses.this);
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Email");
        alertDialog.setMessage("Enter an email of a good friend or family member.");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        alertDialog.setView(input);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        // code that checks to see if the email is valid or used by another account
                        // if it passes checks make it the new email and close alert dialog
                        String email = input.getText().toString();
                        if (Util.emails.contains(email)) {
                            Toast.makeText(EmailAddresses.this, "Already added email", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Util.emails.add(email);
                            PreferencesLayer.getInstance().setEmails(Util.emails);
                            listAdapter.add(email);
                            listAdapter.notifyDataSetChanged();
                            listView.invalidateViews();
                        }

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
        Intent intent = new Intent(EmailAddresses.this, Settings.class );
        startActivity(intent);
        finish();
    }
}
