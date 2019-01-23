package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.general.cities.Cities;
import com.internship.ipda3.semicolon.bloodbank.model.general.cities.CitiesDatum;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.GavernoratesDatum;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.Governorates;
import com.internship.ipda3.semicolon.bloodbank.model.register.Register;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.isNetworkAvailable;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isPasswordConfirm;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validateEmail;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validateName;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePassword;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePhone;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {


    private static final String TAG = CreateAccountFragment.class.getSimpleName();
    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.blood_type_spinner)
    MaterialSpinner bloodTypeSpinner;
    @BindView(R.id.last_donate_data_edit)
    EditText lastDonateDataEdit;
    @BindView(R.id.state_spinner)
    MaterialSpinner stateSpinner;
    @BindView(R.id.city_spinner)
    MaterialSpinner citySpinner;
    @BindView(R.id.phone_number_edit)
    EditText phoneNumberEdit;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.repeat_password_edit)
    EditText repeatPasswordEdit;
    Unbinder unbinder;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.year_spinner)
    MaterialSpinner yearSpinner;
    @BindView(R.id.month_spinner)
    MaterialSpinner monthSpinner;
    @BindView(R.id.day_spinners)
    MaterialSpinner daySpinners;


    private int mCityId = 0;

    MaterialSpinner.OnItemSelectedListener onCityItemSelected = new MaterialSpinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
            mCityId = (position + 1);


        }
    };

    private ApiEndPoint mEndPoints;

    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        mEndPoints = getClient().create(ApiEndPoint.class);
        getGovernoratesFromServer();
//        spinnersData();
        unbinder = ButterKnife.bind(this, view);
        citySpinner.setOnItemSelectedListener(onCityItemSelected);

        return view;
    }
/*

    private void spinnersData() {
        List<String> years = new ArrayList<>();
        for (int i = 2019; i >= 1970; i--) {
            years.add(String.valueOf(i));
            yearSpinner.setItems(years);
        }

        monthSpinner.setItems(Arrays.asList(getResources().getStringArray(R.array.months)));
        daySpinners.setItems(Arrays.asList(getResources().getStringArray(R.array.days)));


    }
*/


    private void getGovernoratesFromServer() {
        mEndPoints.getGovernorates()
                .enqueue(new Callback<Governorates>() {
                    @Override
                    public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                        List<GavernoratesDatum> gavernoratesData = response.body().getData();
                        ArrayList<String> gaverorates = new ArrayList<>();
                        for (int i = 0; i < gavernoratesData.size(); i++) {
                            String gavernarateName = gavernoratesData.get(i).getName();
                            Long gavernoratesId = gavernoratesData.get(i).getId();
                            gaverorates.add(gavernarateName);
                            stateSpinner.setItems(gaverorates);
                            setCitySpinner(gavernoratesId);


                        }

                    }

                    @Override
                    public void onFailure(Call<Governorates> call, Throwable t) {
                        error("onGavernoratesResponse: failure: " + t.getMessage());

                    }
                });


    }
    private void setCitySpinner(Long gavernoratesId) {
        mEndPoints.getCities(gavernoratesId)
                .enqueue(new Callback<Cities>() {
                    @Override
                    public void onResponse(Call<Cities> call, Response<Cities> response) {
                        List<CitiesDatum> citiesData = response.body().getData();
                        ArrayList<String> cities = new ArrayList<>();
                        for (int i = 0; i < citiesData.size(); i++) {
                            String cityName = citiesData.get(i).getName();
                            cities.add(cityName);
                        }
                        citySpinner.setItems(cities);

                    }

                    @Override
                    public void onFailure(Call<Cities> call, Throwable t) {
                        error("onCitiesResponse: failure: " + t.getMessage());

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_button:
                register();
                break;

        }


    }


    private void register() {
        String username = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phoneNumber = phoneNumberEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String repeatPassword = repeatPasswordEdit.getText().toString();

        if (!validateName(username)) {
            nameEditText.setError("username must contains characters from a-z and symbols.");
            return;
        }
        if (!validateEmail(email)) {
            emailEditText.setError("Email doesn't valid.");
            return;
        }
        if (!validatePassword(password)) {
            passwordEdit.setError("at least 6 characters.");
            return;
        }
        if (!isPasswordConfirm(password, repeatPassword)) {
            repeatPasswordEdit.setError("Password doesn't confirm.");
            return;
        }
        if (!validatePhone(phoneNumber)) {
            phoneNumberEdit.setError("Phone number must be 11 digits.");
            return;
        }


        if (isNetworkAvailable(getContext())) {
            newAccountRegister(username, email, phoneNumber, password);


        }
    }

    private void newAccountRegister(String username, String email,
                                    String phoneNumber, String password) {

        mEndPoints.register(username, email, "0", mCityId, phoneNumber,
                "0", password, "A+").enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                verbose("onRegisterResponse: " + response.body().getMsg());

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                verbose("onRegisterFailure: " + t.getMessage());

            }
        });
    }

}
