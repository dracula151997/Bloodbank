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
import com.internship.ipda3.semicolon.bloodbank.model.login.Login;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient;
import com.internship.ipda3.semicolon.bloodbank.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.intent;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.isNetworkAvailable;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.clear;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.rememberMe;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.retrieveAccountInfo;
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

        endPoint = getClient().create(ApiEndPoint.class);

        boolean isRemembered = retrieveAccountInfo(getContext());
        if (isRemembered) {
            intent(getContext(), MainActivity.class);
        } else {
            verbose("nothing");
        }


        unbinder = ButterKnife.bind(this, view);
        return view;
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
            rememberMe(getContext());
        } else {
            clear(getContext());

        }


        if (!validatePassword(password)) {
            verbose("password: not valid");
            passwordEdit.setError(getString(R.string.password_error));
        }

        if (!validatePhone(phoneNumber)) {
            verbose("phone: not valid");
            phoneNumberEdit.setError(getString(R.string.phone_number_error));
            return;
        }

        if (isNetworkAvailable(getContext())) {
            loginRequest(phoneNumber, password);

        } else {
            error("No internet available.");
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
                            showToast(getContext(), msg);
                            intent(getContext(), MainActivity.class);
                        } else {
                            verbose(msg);
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        error("onLoginFailure: error: " + t.getMessage());

                    }
                });
    }


}
