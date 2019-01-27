package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.donation.request.CreateRequest;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.CreateAccountFragment;
import com.internship.ipda3.semicolon.bloodbank.util.UserLocationListener;
import com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.getAddressFromCoordinates;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.isNetworkAvailable;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isEmpty;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePhone;

public class BloodRequestActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST = 2000;
    private static final String API_TOKEN = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.age_edit_text)
    EditText ageEditText;
    @BindView(R.id.blood_type_spinner)
    Spinner bloodTypeSpinner;
    @BindView(R.id.bags_number_edit_text)
    EditText bagsNumberEditText;
    @BindView(R.id.hospital_name_edit_text)
    EditText hospitalNameEditText;
    @BindView(R.id.hospital_address_edit_text)
    EditText hospitalAddressEditText;
    @BindView(R.id.state_spinner)
    Spinner stateSpinner;
    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.phone_number_edit_text)
    EditText phoneNumberEditText;
    @BindView(R.id.comments_edit_text)
    EditText commentsEditText;

    String address;
    String bloodType;
    double lon;
    double lat;
    CreateAccountFragment activity;
    private ApiEndPoint mEndPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);
        ButterKnife.bind(this);

        bloodTypeSpinner();

        mEndPoint = getClient().create(ApiEndPoint.class);


    }

    private void bloodTypeSpinner() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.blood_types, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        bloodTypeSpinner.setAdapter(adapter);
        bloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                verbose("onItemSelected: " + parent.getItemAtPosition(position));
                bloodType = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @OnClick({R.id.my_location_button, R.id.send_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_location_button:
                getCurrentLocation();
                break;
            case R.id.send_button:
                newBloodRequest();
                break;
        }
    }

    private void newBloodRequest() {
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String bagsNum = bagsNumberEditText.getText().toString();
        String hospitalAddress = hospitalAddressEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String comment = commentsEditText.getText().toString();
        String hospitalName = hospitalNameEditText.getText().toString();

        if (isEmpty(name)) {
            nameEditText.setError("Required");
            return;
        }

        if (isEmpty(age)) {
            ageEditText.setError("Required");
            return;
        }

        if (isEmpty(bagsNum)) {
            bagsNumberEditText.setError("Required");
            return;
        }

        if (isEmpty(hospitalAddress)) {
            hospitalAddressEditText.setError("Required");
            return;
        }

        if (isEmpty(phoneNumber)) {
            phoneNumberEditText.setError("Required");
            return;
        }

        if (validatePhone(phoneNumber)) {
            phoneNumberEditText.setError("The phone must be 11 digits.");
            return;
        }

        if (isEmpty(hospitalName)) {
            hospitalNameEditText.setError("Required");
            return;
        }

        if (isNetworkAvailable(this)) {
            bloodRequest(name, age, bagsNum, hospitalAddress, phoneNumber, comment, hospitalName);
        } else {
            showToast(this, "No internet connection.");
        }
    }

    private void bloodRequest(String name, String age, String bagsNum,
                              String hospitalAddress, String phoneNumber, String comment, String hospitalName) {

        mEndPoint.createDonationRequest(API_TOKEN, name, age, bloodType, bagsNum, hospitalName
                , hospitalAddress, 1, phoneNumber, comment, lat, lon).enqueue(new Callback<CreateRequest>() {
            @Override
            public void onResponse(Call<CreateRequest> call, Response<CreateRequest> response) {
                String msg = response.body().getMsg();
                if (response.body().getStatus() == 1) {
                    verbose("onCreateDonationRequestResponse: response message: " + msg);
                } else {
                    verbose("response msg");
                }
            }

            @Override
            public void onFailure(Call<CreateRequest> call, Throwable t) {
                error("onResponse: createDonationRequest: error: " + t.getMessage());

            }
        });

    }

    private void getCurrentLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            lon = lastLocation.getLongitude();
            lat = lastLocation.getLatitude();
            address = getAddressFromCoordinates(this, lat, lon);
            hospitalAddressEditText.setText(address);

        }
    }

}
