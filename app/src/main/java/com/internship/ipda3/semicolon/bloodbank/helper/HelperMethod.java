package com.internship.ipda3.semicolon.bloodbank.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.internship.ipda3.semicolon.bloodbank.R;

public class HelperMethod {

    public static void intent(Context context, Class<?> cls) {
        Intent in = new Intent(context, cls);
        context.startActivity(in);
    }

    public static void commitFragment(int frameId, Fragment targetFragment, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, targetFragment)
                .commit();

    }

    public static void commitFragmentWithData(int frameId, Fragment targetFragment, FragmentManager fragmentManager){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, targetFragment)
                .commit();


    }

    public static void intentWithExtra(Context context, Class<?> cls, String extraKey, String extraValue) {
        Intent in = new Intent(context, cls);
        in.putExtra(extraKey, extraValue);
        context.startActivity(in);

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;

        else {
            NetworkInfo[] networkInfo = cm.getAllNetworkInfo();
            if (networkInfo != null) {
                for (int i = 0; i < networkInfo.length; i++) {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, String message, int type){
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        switch (type){
            case 0:
                //fail
                break;
            case 1:
                //success
                break;
            case 2:
                //warning
                break;
        }
    }
}
