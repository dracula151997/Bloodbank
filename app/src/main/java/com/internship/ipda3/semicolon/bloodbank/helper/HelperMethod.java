package com.internship.ipda3.semicolon.bloodbank.helper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

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

    public static void commitFragmentWithData(int frameId, Fragment targetFragment, FragmentManager fragmentManager) {
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

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getAddressFromCoordinates(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        String address = null;
        List<Address> addressList;
        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1);
            address = addressList.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;


    }

    public static void intentWithPhoneAction(Context context, String phone) {
        Intent in = new Intent(Intent.ACTION_DIAL);
        in.setData(Uri.parse("tel:" + phone));
        context.startActivity(in);

    }

    public static void setError(EditText editText, int errorMessage, Context context) {
        editText.setError(context.getString(errorMessage));
    }

    public static void openUrl(Context context, String url) {
        Intent in = new Intent(Intent.ACTION_VIEW);
        in.setData(Uri.parse(url));
        context.startActivity(in);

    }

    public static String readFileFromAsset(Context context, String fileName) {
        String text = "";

        try {
            InputStream inputStream = context.getAssets().open(fileName);

            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            text = new String(buffer);
            verbose("readFileFromAsset: text: " + text);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;


    }

    public static void showDatePicker(final EditText editText, Activity activity){
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String pattern = "yyyy-MM-dd";
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
                editText.setText(dateFormat.format(calendar.getTime()));



            }
        };

        new DatePickerDialog(activity, datePicker, calendar.get(Calendar.YEAR), Calendar.MONTH, Calendar.DAY_OF_MONTH)
                .show();
    }


}
