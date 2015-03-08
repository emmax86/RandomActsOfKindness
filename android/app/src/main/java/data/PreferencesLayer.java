package data;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    public void setDonationAmountPref(double amount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("donationAmount", Double.doubleToLongBits(amount));
        editor.apply();
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
        editor.apply();
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        Set<String> set = new HashSet<>(phoneNumbers);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("phoneNumbers", set);
        editor.apply();
    }

    public void setEmails(ArrayList<String> emails) {
        Set<String> set = new HashSet<>(emails);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("emails", set);
        editor.apply();
    }

    public double getDonationAmountPref() {
        return Double.longBitsToDouble(sharedPreferences.getLong("donationAmount", Double.doubleToLongBits(1)));
    }

    public boolean getCallPref() {
        return sharedPreferences.getBoolean("call", true);
    }

    public boolean getPostPref() {
        return sharedPreferences.getBoolean("post", true);
    }

    public boolean getDonatePref() {
        return sharedPreferences.getBoolean("donate", false);
    }

    public boolean getMailPref() {
        return sharedPreferences.getBoolean("mail", false);
    }

    public String getKey() {
        return sharedPreferences.getString("id", "");
    }

    public ArrayList<String> getPhoneNumbers() {
        Set<String> set = sharedPreferences.getStringSet("phoneNumbers", new HashSet<String>());
        return new ArrayList<>(set);
    }

    public ArrayList<String> getEmails() {
        Set<String> set = sharedPreferences.getStringSet("emails", new HashSet<String>());
        return new ArrayList<>(set);
    }
}
