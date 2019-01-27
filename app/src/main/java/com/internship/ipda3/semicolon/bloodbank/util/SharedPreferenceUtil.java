package com.internship.ipda3.semicolon.bloodbank.util;

import android.content.Context;
import android.content.SharedPreferences;

import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

public class SharedPreferenceUtil {
    private static final String APP_SHARED_PREF = "app_shared_pref";
    private static final String CHECKED_KEY = "remember_me_checked";
    private static final String FAVORITE_KEY = "favorite";


    //method to save the state of remember me check box.
    public static void saveCheckBoxState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SHARED_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(CHECKED_KEY, true);
        editor.apply();

        verbose("remember me: " + sharedPreferences.getBoolean(CHECKED_KEY, false));


    }

    //method to get the state of remember me check box.
    public static boolean readCheckBoxState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SHARED_PREF, Context.MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean(CHECKED_KEY, false);

        verbose("getCheckBoxState: remember me = " + isRemember);

        return isRemember;


    }

    //method to clearCheckBoxState the state of remember me check box.
    public static void clearCheckBoxState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SHARED_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(CHECKED_KEY);
        editor.apply();

        verbose("clearCheckBoxState shared preference:");

    }

    public static void saveToggleButtonState(boolean isFavorite, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(FAVORITE_KEY, isFavorite);
        editor.apply();

    }

    public static boolean readToggleButtonState(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(FAVORITE_KEY, true);
    }


}
