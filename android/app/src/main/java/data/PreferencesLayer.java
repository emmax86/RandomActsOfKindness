package data;

import android.content.SharedPreferences;

public class PreferencesLayer {

    private static PreferencesLayer instance;
    private SharedPreferences sharedPreferences;

    private PreferencesLayer(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static void initialize(SharedPreferences sharedPreferences) {
        instance = new PreferencesLayer(sharedPreferences);
    }

    public static PreferencesLayer getInstance() {
        if (instance == null) {
            throw new IllegalStateException("PreferencesLayer not initialized!");
        }
        else {
            return instance;
        }
    }

    public void setCallPref(boolean flag) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("call", flag);
        editor.apply();
    }

    public void setPostPref(boolean flag) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("post", flag);
        editor.apply();
    }

    public void setDonatePref(boolean flag) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("donate", flag);
        editor.apply();
    }

    public void setMailPref(boolean flag) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("mail", flag);
        editor.apply();
    }

    public void setKey(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
    }

    public boolean getCallPref() {
        return sharedPreferences.getBoolean("call", true);
    }

    public boolean getPostPref() {
        return sharedPreferences.getBoolean("post", true);
    }

    public boolean getDonatePref() {
        return sharedPreferences.getBoolean("donate", true);
    }

    public boolean getMailPref() {
        return sharedPreferences.getBoolean("mail", true);
    }

    public String getKey() {
        return sharedPreferences.getString("id", "");
    }

}
