package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.general.cities.Cities;
import com.internship.ipda3.semicolon.bloodbank.model.general.cities.CitiesDatum;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.GavernoratesDatum;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.Governorates;
import com.internship.ipda3.semicolon.bloodbank.model.users.login.Login;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.BIRTH_DATE;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.CITY_ID;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.DONATION_LAST_DATE;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.EMAIL;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.PHONE;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.USER_NAME;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.isNetworkAvailable;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.setError;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.LoadStringData;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isPasswordConfirm;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validateEmail;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validateName;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePassword;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoSettingFragment extends Fragment {


    @BindView(R.id.user_name_edit_text)
    EditText userNameEditText;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.birth_day_edit_text)
    EditText birthDayEditText;
    @BindView(R.id.blood_type_spinner)
    Spinner bloodTypeSpinner;
    @BindView(R.id.last_donate_data_edit_text)
    EditText lastDonateDataEditText;
    @BindView(R.id.governorates_spinner)
    Spinner governoratesSpinner;
    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.repeat_password_edit_text)
    EditText repeatPasswordEditText;
    Unbinder unbinder;

    String apiToken;

    int cityId;
    @BindView(R.id.phone_number_edit_text)
    EditText phoneNumberEditText;

    private ApiEndPoint mEndPoint;

    public InfoSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_setting, container, false);

        unbinder = ButterKnife.bind(this, view);
        mEndPoint = getClient().create(ApiEndPoint.class);

        apiToken = LoadStringData(getActivity(), API_TOKEN);

        displayUserData();


        return view;
    }

    private void displayUserData() {
        String name = LoadStringData(getActivity(), USER_NAME);
        userNameEditText.setText(name);

        String email = LoadStringData(getActivity(), EMAIL);
        emailEditText.setText(email);

        String birthDate = LoadStringData(getActivity(), BIRTH_DATE);
        birthDayEditText.setText(birthDate);

        String lastDonationDate = LoadStringData(getActivity(), DONATION_LAST_DATE);
        lastDonateDataEditText.setText(lastDonationDate);

        String phoneNumber = LoadStringData(getActivity(), PHONE);
        phoneNumberEditText.setText(phoneNumber);


        int cityId = Integer.parseInt(LoadStringData(getActivity(), CITY_ID));

        CitySpinner(cityId);


    }

    private void CitySpinner(final int cityId) {
        mEndPoint.getGovernorates()
                .enqueue(new Callback<Governorates>() {
                    @Override
                    public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                        if (response.code() == 200) {
                            long status = response.body().getStatus();
                            if (status == 1) {
                                List<String> gavernorate = new ArrayList<>();
                                List<Integer> governorateId = new ArrayList<>();
                                governorateId.add(0);
                                gavernorate.add(getString(R.string.state));
                                List<GavernoratesDatum> data = response.body().getData();
                                for (int i = 0; i < data.size(); i++) {
                                    verbose("gavernorates data: " + data.toString());
                                    String gavernorateName = data.get(i).getName();

                                    gavernorate.add(gavernorateName);
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                            android.R.layout.simple_spinner_dropdown_item, gavernorate);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    governoratesSpinner.setAdapter(adapter);


                                    long id = data.get(i).getId();
                                    getCity(id, cityId);

                                }
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Governorates> call, Throwable t) {
                        error("onResponse: city spinner: " + t.getMessage());

                    }
                });
    }

    private void getCity(long id, final int cityId) {
        mEndPoint.getCities(id)
                .enqueue(new Callback<Cities>() {
                    @Override
                    public void onResponse(Call<Cities> call, Response<Cities> response) {
                        if (response.code() == 200) {
                            long status = response.body().getStatus();
                            if (status == 1) {
                                List<CitiesDatum> data = response.body().getData();
                                List<String> cities = new ArrayList<>();
                                List<Integer> ID = new ArrayList<>();
                                cities.add(getString(R.string.city));
                                ID.add(0);
                                for (int i = 0; i < data.size(); i++) {
                                    verbose("cities: " + data.toString());
                                    int id = data.get(i).getId();
                                    if (cityId == id) {

                                        String governorateId = data.get(i).getGovernorateId();

                                        String cityName = data.get(i).getName();
                                        cities.add(cityName);
                                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                                android.R.layout.simple_spinner_dropdown_item, cities);
                                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        citySpinner.setAdapter(adapter);
                                        verbose(String.format("city name and governorate name: %s, %s", cityName, governorateId));

                                    }

                                }

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Cities> call, Throwable t) {
                        error("onResponse: get city: " + t.getMessage());

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.update_info_button)
    public void onViewClicked() {
        updateUserInfo();
    }

    private void updateUserInfo() {
        String name = userNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
    /*    String birthdate = birthDayEditText.getText().toString();
        String lastDonation = lastDonateDataEditText.getText().toString();*/
        String password = passwordEditText.getText().toString();
        String repeatedPassword = repeatPasswordEditText.getText().toString();


        if (!validateName(name)) {
            setError(userNameEditText, R.string.user_name_error, getContext());
            return;

        }
        if (!validateEmail(email)) {
            setError(emailEditText, R.string.email_error, getContext());
            return;
        }

    /*    if (isEmpty(birthdate)) {
            setError(birthDayEditText, R.string.required, getContext());
            return;
        }

        if (isEmpty(lastDonation)) {
            setError(lastDonateDataEditText, R.string.required, getContext());
            return;
        }*/

        if (!validatePassword(password)) {
            setError(passwordEditText, R.string.password_error, getContext());
            return;
        }

        if (!isPasswordConfirm(password, repeatedPassword)) {
            setError(repeatPasswordEditText, R.string.password_confirm_error, getContext());
            return;
        }

        if (isNetworkAvailable(getContext())) {
            updateProfile(name, email, password, repeatedPassword);
        } else {
            showToast(getContext(), "No internet connection.");
        }


    }

    private void updateProfile(String name, String email,
                               String password, String repeatedPassword) {
        mEndPoint.updateProfile(name, email, "1990-01-01", 1, "01222225222",
                "1990-01-01", password, repeatedPassword, "A+", API_TOKEN)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        String msg = response.body().getMsg();
                        if (response.body().getStatus() == 1) {
                            showToast(getContext(), msg);
                        } else {
                            showToast(getContext(), msg);
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        error("onFailure: updateProfile: " + t.getMessage());

                    }
                });
    }
}
