package com.avenjr.me.me.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.avenjr.me.me.R;

public class AppPreferences {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Context context;

    public AppPreferences(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        this.editor = sharedPref.edit();
    }

    public void setEmail(String email) {
        editor.putString(context.getString(R.string.email), email).apply();
    }

    public String getEmail() {
        String defaultValue = context.getResources().getString(R.string.email);
        return sharedPref.getString(context.getString(R.string.email), defaultValue);
    }
}
