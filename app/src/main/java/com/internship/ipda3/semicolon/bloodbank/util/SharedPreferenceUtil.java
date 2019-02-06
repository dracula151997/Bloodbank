package com.internship.ipda3.semicolon.bloodbank.util;

import android.content.Context;
import android.content.SharedPreferences;

import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

public class SharedPreferenceUtil {
    private static final String APP_SHARED_PREF = "app_shared_pref";
    private static final String CHECKED_KEY = "remember_me_checked";
    private static final String FAVORITE_KEY = "favorite";
    private static final String API_TOKEN = "api_token";


    public static SharedPreferences sharedPreferences = null;

    public static void initSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            context.getSharedPreferences(APP_SHARED_PREF, Context.MODE_PRIVATE);
        }
    }

    //method to save the state of remember me check box.
    public static void saveCheckBoxState(Context context) {
        initSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(CHECKED_KEY, true);
            editor.apply();

        }


        verbose("remember me: " + (sharedPreferences != null && sharedPreferences.getBoolean(CHECKED_KEY, false)));


    }

    //method to get the state of remember me check box.
    public static boolean readCheckBoxState(Context context) {
        initSharedPreferences(context);
        if (sharedPreferences != null) {
            boolean isRemember = sharedPreferences.getBoolean(CHECKED_KEY, false);
            verbose("check box state: " + isRemember);
            return isRemember;
        }

        return false;


    }

    //method to clearCheckBoxState the state of remember me check box.
    public static void clearCheckBoxState(Context context) {
        initSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(CHECKED_KEY);
            editor.apply();

        }


        verbose("clearCheckBoxState shared preference:");

    }

    public static void saveToggleButtonState(boolean isFavorite, Context context) {
        initSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(FAVORITE_KEY, isFavorite);
            editor.apply();

        }

    }

    public static boolean readToggleButtonState(Context context) {
        initSharedPreferences(context);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(FAVORITE_KEY, true);
        }

        return false;
    }

    public static void saveStringData(String key, String value, Context context) {
        initSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }

    }

    public static String readStringData(Context context, String key) {
        initSharedPreferences(context);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, null);
        }

        return null;


    }

    public static void saveBooleanData(Context context, String key, boolean value) {
        initSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }


}
