package com.internship.ipda3.semicolon.bloodbank.util;

import android.app.Activity;
import android.content.SharedPreferences;



public class SharedPreferencesManger {


    private static SharedPreferences sharedPreferences = null;


    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "kanaria", activity.MODE_PRIVATE);

        }
    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        }
    }

    public static void SaveData(Activity activity, String data_Key, int data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(data_Key, data_Value);
            editor.commit();
        }
    }

    public static String LoadStringData(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        }

        return sharedPreferences.getString(data_Key, null);
    }

    public static Integer LoadIntegerData(Activity activity, String data_Key) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getInt(data_Key, 0);
    }


  
}
