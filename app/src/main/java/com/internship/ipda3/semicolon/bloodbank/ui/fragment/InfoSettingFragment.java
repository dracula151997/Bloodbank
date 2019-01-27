package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.login.Login;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.Constant.API_TOKEN;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.isNetworkAvailable;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.setError;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isEmpty;
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



        return view;
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
