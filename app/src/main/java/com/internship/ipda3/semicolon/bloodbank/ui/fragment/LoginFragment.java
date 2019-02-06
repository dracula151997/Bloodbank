package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod;
import com.internship.ipda3.semicolon.bloodbank.model.users.login.Login;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.internship.ipda3.semicolon.bloodbank.ui.activity.MainActivity;
import com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.BIRTH_DATE;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.BLOOD_TYPE;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.CITY_ID;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.DONATION_LAST_DATE;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.EMAIL;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.PHONE;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.REMEMBER_ME;
import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.USER_NAME;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.intent;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.isNetworkAvailable;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.clearCheckBoxState;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.readCheckBoxState;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.saveCheckBoxState;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.saveStringData;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.LoadIntegerData;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.SaveData;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePassword;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePhone;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    @BindView(R.id.phone_number_edit)
    EditText phoneNumberEdit;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.remember_me_checked_box)
    CheckBox rememberMeCheckedBox;
    Unbinder unbinder;

    ApiEndPoint endPoint;


    private FragmentManager mManager;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mManager = getFragmentManager();

        rememberMe();


        endPoint = getClient().create(ApiEndPoint.class);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void rememberMe() {
        int remember = LoadIntegerData(getActivity(), REMEMBER_ME);
        if (remember == 1) {
            intent(getContext(), MainActivity.class);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.login_button, R.id.new_user_button, R.id.forget_password_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.new_user_button:
                HelperMethod.commitFragment(R.id.frame, new CreateAccountFragment(), mManager);
                break;
            case R.id.forget_password_text:
                HelperMethod.commitFragment(R.id.frame, new ForgetPasswordFragment(), mManager);
                break;

        }
    }

    private void login() {
        String phoneNumber = phoneNumberEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        boolean isRememberMeChecked = rememberMeCheckedBox.isChecked();

        if (isRememberMeChecked) {
            SaveData(getActivity(), REMEMBER_ME, 1);
        } else {
            SaveData(getActivity(), REMEMBER_ME, 0);
        }


        if (!validatePassword(password)) {
            passwordEdit.setError(getString(R.string.password_error));
        }

        if (!validatePhone(phoneNumber)) {
            phoneNumberEdit.setError(getString(R.string.phone_number_error));
            return;
        }

        if (isNetworkAvailable(getContext())) {
            loginRequest(phoneNumber, password);

        } else {
            showToast(getContext(), getString(R.string.no_internet));
        }


    }

    private void loginRequest(String phoneNumber, String password) {
        endPoint.login(phoneNumber, password)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        long status = response.body().getStatus();
                        String msg = response.body().getMsg();
                        if (status == 1) {

                            String apiToken = response.body().getData().getApiToken();
                            String name = response.body().getData().getClient().getName();
                            String email = response.body().getData().getClient().getEmail();
                            String phone = response.body().getData().getClient().getPhone();
                            String birthDate = response.body().getData().getClient().getBirthDate();
                            String lastDonationDate = response.body().getData().getClient().getDonationLastDate();
                            String cityId = response.body().getData().getClient().getCityId();
                            String bloodType = response.body().getData().getClient().getBloodType();

                            saveData(apiToken, name, email, phone, birthDate, lastDonationDate, cityId, bloodType);

                            showToast(getContext(), msg);
                            intent(getContext(), MainActivity.class);
                        } else {
                            showToast(getContext(), msg);
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        error("onLoginFailure: error: " + t.getMessage());

                    }
                });
    }

    private void saveData(String apiToken, String name, String email, String phone,
                          String birthDate, String lastDonationDate, String cityId, String bloodType) {

        SaveData(getActivity(), API_TOKEN, apiToken);
        SaveData(getActivity(), USER_NAME, name);
        SaveData(getActivity(), EMAIL, email);
        SaveData(getActivity(), PHONE, phone);
        SaveData(getActivity(), BIRTH_DATE, birthDate);
        SaveData(getActivity(), DONATION_LAST_DATE, lastDonationDate);
        SaveData(getActivity(), CITY_ID, cityId);
        SaveData(getActivity(), BLOOD_TYPE, bloodType);

    }


}
