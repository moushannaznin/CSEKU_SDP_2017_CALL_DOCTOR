package com.cse.calldoctor;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
//android shared prefference
public class Shared extends Application {
//patient login status save (session)
    public void storeData(String data, String storageName, Context act) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(act);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(storageName, data);
        editor.apply();
    }

    public String readData(String storageName) {
        SharedPreferences info = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        return info.getString(storageName, "");
    }

}
