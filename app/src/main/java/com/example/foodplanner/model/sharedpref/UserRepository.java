package com.example.foodplanner.model.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class UserRepository {

    private SharedPreferences prefs;

    public UserRepository(Context context) {
        prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
    }

    public void saveLoginState(boolean isLoggedIn, boolean isGuest) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("is_logged_in", isLoggedIn);
        editor.putBoolean("is_guest", isGuest);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean("is_logged_in", false);
    }

    public boolean isGuest() {
        return prefs.getBoolean("is_guest", false);
    }
    public void clearLoginState() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("is_logged_in", false);
        editor.putBoolean("is_guest", false);
        editor.apply();
    }

}
