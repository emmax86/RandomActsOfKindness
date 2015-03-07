package activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import adapters.MainActivityPageAdapter;
import data.PreferencesLayer;
import fragments.Home;
import me.dstny.activities.R;

public class MainActivity extends Activity {

    private ArrayList<Fragment> fragments;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ViewPager viewPager;
    private MainActivityPageAdapter mainActivityPageAdapter;
    private Home homeFragment;

    private Button callButton;
    private Button postButton;
    private Button donateButton;
    private Button mailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new Home();
        fragments = new ArrayList<Fragment>();
        fragments.add(homeFragment);
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
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        callButton = (Button) findViewById(R.id.call_button);
        postButton = (Button) findViewById(R.id.post_button);
        donateButton = (Button) findViewById(R.id.donate_button);
        mailButton = (Button) findViewById(R.id.mail_button);
    }

    public void callButtonPressed(View view) {
        PreferencesLayer.getInstance().setCallPref(callButton.isActivated());
    }

    public void postButtonPressed(View view) {
        PreferencesLayer.getInstance().setPostPref(postButton.isActivated());
    }

    public void donateButtonPressed(View view) {
        PreferencesLayer.getInstance().setPostPref(donateButton.isActivated());
    }

    public void mailButtonPressed(View view) {
        PreferencesLayer.getInstance().setMailPref(mailButton.isActivated());
    }

    public void settingsButtonPressed(View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
        finish();
    }
}
