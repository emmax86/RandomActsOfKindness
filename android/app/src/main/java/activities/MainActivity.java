package activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import adapters.MainActivityPageAdapter;
import data.PreferencesLayer;
import fragments.GlobalInfo;
import fragments.Home;
import fragments.UserInfo;
import me.dstny.activities.R;
import networking.ConnectionHandler;
import util.Util;

public class MainActivity extends Activity {

    private ArrayList<Fragment> fragments;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ViewPager viewPager;
    private MainActivityPageAdapter mainActivityPageAdapter;

    private UserInfo userInfo;
    private Home homeFragment;
    private GlobalInfo globalInfo;

    private Button actButton;
    private Button callButton;
    private Button postButton;
    private Button donateButton;
    private Button mailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInfo = new UserInfo();
        homeFragment = new Home();
        globalInfo = new GlobalInfo();
        fragments = new ArrayList<Fragment>();
        fragments.add(userInfo);
        fragments.add(homeFragment);
        fragments.add(globalInfo);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fm = getFragmentManager();
        mainActivityPageAdapter = new MainActivityPageAdapter(fm, fragments);
        viewPager.setAdapter(mainActivityPageAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        boolean send = getIntent().getBooleanExtra("key",false);
        if(send) {
            viewPager.setCurrentItem(0);
        }
        else {
           viewPager.setCurrentItem(1);
        }
        viewPager.setOffscreenPageLimit(3);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void assembleButtons(Button act, Button call, Button post, Button donate, Button mail) {

        actButton = act;
        callButton = call;
        postButton = post;
        donateButton = donate;
        mailButton = mail;

        PreferencesLayer preferencesLayer = PreferencesLayer.getInstance();
        callButton.setActivated(preferencesLayer.getCallPref());
        postButton.setActivated(preferencesLayer.getPostPref());
        donateButton.setActivated(preferencesLayer.getDonatePref());
        mailButton.setActivated(preferencesLayer.getMailPref());

    }

    public void kindnessButtonPressed(View view) {
        if(callButton.isActivated() || postButton.isActivated() || donateButton.isActivated() || mailButton.isActivated()) {
            ArrayList<Button> activatedButtons = new ArrayList<>();
            if(callButton.isActivated()) {
                activatedButtons.add(callButton);
            }
            if(postButton.isActivated()) {
                activatedButtons.add(postButton);
            }
            if(donateButton.isActivated()) {
                activatedButtons.add(donateButton);
            }
            if(mailButton.isActivated()) {
                activatedButtons.add(mailButton);
            }
            Button selectedButton = activatedButtons.get(randomInt(activatedButtons.size()));

            if(selectedButton.equals(callButton)) {
                randomCall();
            }
            else if (selectedButton.equals(postButton)) {
                randomPost();
            }
            else if (selectedButton.equals(donateButton)) {
                randomDonate();
            }
            else {
                randomMail();
            }
        }
        else {
            Toast.makeText(this, "Please select a category in order to take part in Random Acts of Kindness.", Toast.LENGTH_LONG).show();
        }
    }

    public int randomInt(int n) {
        return new Random().nextInt(n);
    }

    public void randomCall() {
        if(Util.phoneNumbers.size()>0) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + Util.phoneNumbers.get(randomInt(Util.phoneNumbers.size()))));
            startActivity(callIntent);
        }
        else {
            AlertDialog.Builder builder = new   AlertDialog.Builder(MainActivity.this);
            AlertDialog alertDialog = builder.create();
            alertDialog.setMessage("We noticed that you do not have any phone numbers entered. Would you like to add some?");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int whichButton) {
                            Intent intent = new Intent(MainActivity.this, PhoneNumbers.class );
                            startActivity(intent);
                            finish();
                        }
                    });

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });
            alertDialog.show();
        }
    }

    public void randomPost() {
        Toast.makeText(this, "random post selected", Toast.LENGTH_SHORT).show();
        String tag = "@";
        String[] people = getResources().getStringArray(R.array.twitter_names);
        String person = people[randomInt(people.length)];
        String[] messages = getResources().getStringArray(R.array.twitter);
        String message = " " + messages[randomInt(messages.length)];
        String post = tag + person + message;
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text(post);
        builder.show();
    }

    public void randomDonate() {
        Toast.makeText(this, "random donate selected", Toast.LENGTH_SHORT).show();
    }

    public void randomMail() {
        if(Util.emails.size()>0) {
            //send an email
        }
        else {
            AlertDialog.Builder builder = new   AlertDialog.Builder(MainActivity.this);
            AlertDialog alertDialog = builder.create();
            alertDialog.setMessage("We noticed that you do not have any emails entered. Would you like to add some?");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int whichButton) {
                            Intent intent = new Intent(MainActivity.this, EmailAddresses.class );
                            startActivity(intent);
                            finish();
                        }
                    });

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });
            alertDialog.show();
        }
    }

    public void callButtonPressed(View view) {
        callButton.setActivated(!callButton.isActivated());
        PreferencesLayer.getInstance().setCallPref(callButton.isActivated());
    }

    public void postButtonPressed(View view) {
        postButton.setActivated(!postButton.isActivated());
        PreferencesLayer.getInstance().setPostPref(postButton.isActivated());
    }

    public void donateButtonPressed(View view) {
        donateButton.setActivated(!donateButton.isActivated());
        PreferencesLayer.getInstance().setDonatePref(donateButton.isActivated());
    }

    public void mailButtonPressed(View view) {
        mailButton.setActivated(!mailButton.isActivated());
        PreferencesLayer.getInstance().setMailPref(mailButton.isActivated());
    }

    public void settingsButtonPressed(View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        if(viewPager.getCurrentItem()==0) {
            viewPager.setCurrentItem(1);
        }
        else if(viewPager.getCurrentItem()==2) {
            viewPager.setCurrentItem(1);
        }
        else {
            Intent intent = new Intent(MainActivity.this, ExitSplashScreen.class);
            startActivity(intent);
            finish();
        }
    }

    private class GetStatsTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            String id = PreferencesLayer.getInstance().getKey();
            try {
                String response = ConnectionHandler.getUserStats(id);
                if (response.startsWith("200:")) {
                    response = response.replace("200:", "");
                    JSONObject jsonObject = new JSONObject(response);
                    Util.messages = jsonObject.getInt("post");
                    Util.phoneCalls = jsonObject.getInt("call");
                    Util.donations = jsonObject.getInt("donate");
                    Util.ecards = jsonObject.getInt("mail");
                    return true;
                }
                else {
                    return false;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {

            }
        }
    }

    private class UpdatePhone extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

        }

    }

    private class UpdateMessages extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

        }

    }

    private class UpdateDonations extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

        }

    }

    private class UpdateMail extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

        }

    }

}
