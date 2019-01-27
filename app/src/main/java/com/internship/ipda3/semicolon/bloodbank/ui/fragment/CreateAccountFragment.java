package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.general.cities.Cities;
import com.internship.ipda3.semicolon.bloodbank.model.general.cities.CitiesDatum;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.GavernoratesDatum;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.Governorates;
import com.internship.ipda3.semicolon.bloodbank.model.register.Register;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
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
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isEmpty;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isPasswordConfirm;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validateEmail;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validateName;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePassword;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePhone;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment
        implements DatePickerDialog.OnDateSetListener {


    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.blood_type_spinner)
    Spinner bloodTypeSpinner;
    @BindView(R.id.last_donate_data_edit)
    EditText lastDonateDataEdit;
    @BindView(R.id.state_spinner)
    Spinner stateSpinner;
    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.phone_number_edit)
    EditText phoneNumberEdit;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.repeat_password_edit)
    EditText repeatPasswordEdit;
    Unbinder unbinder;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.birth_day_edit_text)
    EditText birthDayEditText;

    String date = null;
    String bloodType;


    private int mCityId = 0;


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
        unbinder = ButterKnife.bind(this, view);

        getGovernoratesFromServer();
        lastDonateDataEdit.setClickable(true);
        bloodTypeSpinner();


        return view;
    }

    public void bloodTypeSpinner() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(getContext(), R.array.blood_types, android.R.layout.simple_spinner_item);

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


    private void getGovernoratesFromServer() {
        mEndPoints.getGovernorates()
                .enqueue(new Callback<Governorates>() {
                    @Override
                    public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                        List<GavernoratesDatum> gavernoratesData = response.body().getData();
                        ArrayList<String> gaverorates = new ArrayList<>();
                        final ArrayList<Integer> ids = new ArrayList<>();

                        gaverorates.add(getString(R.string.country));
                        ids.add(0);

                        for (int i = 0; i < gavernoratesData.size(); i++) {
                            String gavernarateName = gavernoratesData.get(i).getName();
                            Integer gavernoratesId = gavernoratesData.get(i).getId();
                            gaverorates.add(gavernarateName);
                            ids.add(gavernoratesId);


                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_item, gaverorates);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stateSpinner.setAdapter(adapter);

                        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    setCitySpinner(ids.get(position));
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<Governorates> call, Throwable t) {
                        error("onGavernoratesResponse: failure: " + t.getMessage());

                    }
                });


    }

    private void setCitySpinner(Integer gavernoratesId) {
        mEndPoints.getCities(gavernoratesId)
                .enqueue(new Callback<Cities>() {
                    @Override
                    public void onResponse(Call<Cities> call, Response<Cities> response) {
                        List<CitiesDatum> citiesData = response.body().getData();
                        ArrayList<String> cities = new ArrayList<>();
                        final ArrayList<Integer> citiesIds = new ArrayList<>();

                        cities.add(getString(R.string.cities));
                        citiesIds.add(0);

                        for (int i = 0; i < citiesData.size(); i++) {
                            String cityName = citiesData.get(i).getName();
                            Integer cityIds = citiesData.get(i).getId();

                            cities.add(cityName);
                            citiesIds.add(cityIds);
                        }

                        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_spinner_item, cities);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        citySpinner.setAdapter(adapter);

                        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    verbose("onCityItemSelected: " + citiesIds.get(position));
                                    mCityId = citiesIds.get(position);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

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

    @OnClick({R.id.register_button, R.id.last_donate_data_edit, R.id.birth_day_edit_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_button:
                register();
                break;
            case R.id.last_donate_data_edit:
                showDatePicker();
                lastDonateDataEdit.setText(date);
                break;
            case R.id.birth_day_edit_text:
                showDatePicker();
                birthDayEditText.setText(date);
                break;


        }


    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);

        datePickerDialog.show();

    }


    private void register() {
        String username = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phoneNumber = phoneNumberEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String repeatPassword = repeatPasswordEdit.getText().toString();
        String lastDonationDate = lastDonateDataEdit.getText().toString();
        String birthDate = birthDayEditText.getText().toString();

        if (!validateName(username)) {
            nameEditText.setError(getString(R.string.user_name_error));
            return;
        }
        if (!validateEmail(email)) {
            emailEditText.setError(getString(R.string.email_error));
            return;
        }
        if (!validatePassword(password)) {
            passwordEdit.setError(getString(R.string.password_error));
            return;
        }
        if (!isPasswordConfirm(password, repeatPassword)) {
            repeatPasswordEdit.setError(getString(R.string.password_confirm_error));
            return;
        }
        if (!validatePhone(phoneNumber)) {
            phoneNumberEdit.setError(getString(R.string.phone_number_error));
            return;
        }

        if (isEmpty(lastDonationDate)) {
            lastDonateDataEdit.setError(getString(R.string.last_donation_date_error));
            return;
        }

        if (isEmpty(birthDate)) {
            birthDayEditText.setError(getString(R.string.birth_date_error));
            return;
        }


        if (isNetworkAvailable(getContext())) {
            newAccountRegister(username, email, phoneNumber, password, lastDonationDate, birthDate);


        }
    }

    private void newAccountRegister(String lastDonateDate, String username, String email,
                                    String phoneNumber, String password, String birthDate) {

        mEndPoints.register(username, email, birthDate, mCityId, phoneNumber,
                lastDonateDate, password, bloodType).enqueue(new Callback<Register>() {
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = String.valueOf(year + "-" + (month + 1) + "-" + dayOfMonth);


    }
}
