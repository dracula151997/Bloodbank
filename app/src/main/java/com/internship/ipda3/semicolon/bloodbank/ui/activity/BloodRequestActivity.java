package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.donation.request.CreateRequest;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.CreateAccountFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.isNetworkAvailable;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isEmpty;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePhone;

public class BloodRequestActivity extends AppCompatActivity {

    private static final String API_TOKEN = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
    private static final int PLACE_PICKER_REQUEST = 1000;
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
//                getCurrentLocation();
                openPlacePicker();
                break;
            case R.id.send_button:
                newBloodRequest();
                break;
        }
    }

    private void openPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
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

        if (!validatePhone(phoneNumber)) {
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
                try {
                    String msg = response.body().getMsg();
                    long status = response.body().getStatus();
                    if (status == 1) {
                        showToast(BloodRequestActivity.this, msg);
                        finish();
                    } else {
                        showToast(BloodRequestActivity.this, msg);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<CreateRequest> call, Throwable t) {
                error("onResponse: createDonationRequest: error: " + t.getMessage());

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                lat = place.getLatLng().latitude;
                lon = place.getLatLng().longitude;

                String placeAddress = String.valueOf(place.getAddress());
                hospitalAddressEditText.setText(placeAddress);


                verbose("location longitude and latitude: " + lon + ", " + lat);
            }
        }

    }
}
