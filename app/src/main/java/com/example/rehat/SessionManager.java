package com.example.rehat;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.rehat.model.login.loginData;

import java.util.HashMap;

public class SessionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(loginData user) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        return user;
    }

    public void logoutSession() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
