package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.donation.details.DonationDetails;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.Constant.API_TOKEN;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

public class RequestDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.person_name_text_view)
    TextView personNameTextView;
    @BindView(R.id.person_age_text_view)
    TextView personAgeTextView;
    @BindView(R.id.person_blood_type_text_view)
    TextView personBloodTypeTextView;
    @BindView(R.id.bags_number_text_view)
    TextView bagsNumberTextView;
    @BindView(R.id.hospital_name_text_view)
    TextView hospitalNameTextView;
    @BindView(R.id.hospital_address_text_view)
    TextView hospitalAddressTextView;
    @BindView(R.id.person_phone_number_text_view)
    TextView personPhoneNumberTextView;
    private String donationId;
    private ApiEndPoint endPoint;

    private GoogleMap mMap;
    private double mLat;
    private double mLong;
    private LatLng latLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            donationId = getIntent().getStringExtra("donation_id");
        }

        endPoint = getClient().create(ApiEndPoint.class);
        getDonationDetails(donationId);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


    }

    private void getDonationDetails(String donationId) {
        endPoint.getDonationDetails(API_TOKEN, donationId)
                .enqueue(new Callback<DonationDetails>() {
                    @Override
                    public void onResponse(Call<DonationDetails> call, Response<DonationDetails> response) {
                        String msg = response.body().getMsg();
                        if (response.body().getStatus() == 1) {
                            verbose("onResponse: getDonationDetails: " + msg);
                            personNameTextView.setText(response.body().getData().getPatientName());
                            personAgeTextView.setText(response.body().getData().getPatientAge());
                            personBloodTypeTextView.setText(response.body().getData().getBloodType());
                            bagsNumberTextView.setText(response.body().getData().getBagsNum());
                            hospitalNameTextView.setText(response.body().getData().getHospitalName());
                            personPhoneNumberTextView.setText(response.body().getData().getPhone());

                            mLat = Double.parseDouble(response.body().getData().getLatitude());
                            mLong = Double.parseDouble(response.body().getData().getLongitude());

                            addMarker(mLat, mLong);
                            verbose("onResponse: getDonationDetails: latitude and longitude: " + mLat + ", " + mLong);
                        } else {
                            verbose("onResponse: getDonationDetails: " + msg);
                        }
                    }

                    @Override
                    public void onFailure(Call<DonationDetails> call, Throwable t) {
                        error("onFailure: getDonationDetails: error: " + t.getMessage());

                    }
                });

    }

    private void addMarker(double mLat, double mLong) {
        LatLng latLng = new LatLng(mLat, mLong);
        mMap.addMarker(new MarkerOptions().position(latLng).title("موقع المريض"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this);
        this.mMap = googleMap;





    }
}
