package com.internship.ipda3.semicolon.bloodbank.util;

import android.content.Context;
import android.content.SharedPreferences;

import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

public class SharedPreferenceUtil {
    private static final String SHARED_PRE_LOGIN_NAME = "remember_me_shared_pref";
    private static final String CHECKED_KEY = "remember_me_checked";


    public static void rememberMe(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRE_LOGIN_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(CHECKED_KEY, true);
        editor.apply();

        verbose("remember me: " + sharedPreferences.getBoolean(CHECKED_KEY, false));


    }

    public static boolean retrieveAccountInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRE_LOGIN_NAME, Context.MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean(CHECKED_KEY, false);

        verbose("retrieveAccountInfo: remember me? = " + isRemember);

        return isRemember;


    }

    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRE_LOGIN_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(CHECKED_KEY);
        editor.apply();

        verbose("clear shared preference:");

    }
}
