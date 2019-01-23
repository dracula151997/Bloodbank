package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.util.UserLocationListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BloodRequestActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST = 2000;
    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.age_edit_text)
    EditText ageEditText;
    @BindView(R.id.blood_type_spinner)
    MaterialSpinner bloodTypeSpinner;
    @BindView(R.id.bags_number_edit_text)
    EditText bagsNumberEditText;
    @BindView(R.id.hospital_name_edit_text)
    EditText hospitalNameEditText;
    @BindView(R.id.hospital_address_edit_text)
    EditText hospitalAddressEditText;
    @BindView(R.id.state_spinner)
    MaterialSpinner stateSpinner;
    @BindView(R.id.city_spinner)
    MaterialSpinner citySpinner;
    @BindView(R.id.phone_number_edit_text)
    EditText phoneNumberEditText;
    @BindView(R.id.comments_edit_text)
    EditText commentsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.my_location_button, R.id.send_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_location_button:
                getCurrentLocation();
                break;
            case R.id.send_button:
                break;
        }
    }

    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        UserLocationListener locationListener = new UserLocationListener();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST);
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

}
