package com.example.lenovo.smartpowerdemo;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lenovo.smartpowerdemo.model.ManagedContext;

/**
 * Created by lenovo on 5/28/2018.
 */

public class SharedPreferencesUtilities {
    private Context context;

    public SharedPreferencesUtilities(Context context) {
        this.context = context;
    }


    public  void saveDemoModeFlag(boolean flag) {

        SharedPreferences settings = context.getSharedPreferences(ManagedContext.APP_DEMO_MODE_FLAG, Context.MODE_PRIVATE); //1
        SharedPreferences.Editor editor  = settings.edit(); //2
        editor.clear();
        editor.putBoolean(ManagedContext.APP_DEMO_MODE_FLAG,flag); //3
        editor.apply(); //4
    }

    public boolean getDemoModeFlag() {
        SharedPreferences   settings = context.getSharedPreferences(ManagedContext.APP_DEMO_MODE_FLAG, Context.MODE_PRIVATE); //1
        return settings.getBoolean(ManagedContext.APP_DEMO_MODE_FLAG, false);
    }

}
