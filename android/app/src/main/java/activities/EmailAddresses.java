package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.dstny.activities.R;

public class EmailAddresses extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emails_setting);

    }
    public void emailBackButtonPressed(View view) {
        Intent intent = new Intent(EmailAddresses.this, Settings.class );
        startActivity(intent);
        finish();

    }

}
