package data;

import android.content.SharedPreferences;

import java.util.ArrayList;

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
        int size = phoneNumbers.size();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("phoneNumbersCount", size);
        for (int i = 0; i < size; i++) {
            editor.putString("phoneNumber" + i, phoneNumbers.get(i));
        }
        editor.apply();
    }

    public void setEmails(ArrayList<String> emails) {
        int size = emails.size();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("emailsCount", size);
        for (int i = 0; i < size; i++) {
            editor.putString("email" + i, emails.get(i));
        }
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
        ArrayList<String> phoneNumbers = new ArrayList<>();
        int size = sharedPreferences.getInt("phoneNumbersCount", 0);
        for (int i = 0; i < size; i++) {
            phoneNumbers.add(sharedPreferences.getString("email" + i, ""));
        }
        return phoneNumbers;
    }

    public ArrayList<String> getEmails() {
        ArrayList<String> emails = new ArrayList<>();
        int size = sharedPreferences.getInt("emailsCount", 0);
        for (int i = 0; i < size; i++) {
            emails.add(sharedPreferences.getString("phoneNumber" + i, ""));
        }
        return emails;
    }

}
