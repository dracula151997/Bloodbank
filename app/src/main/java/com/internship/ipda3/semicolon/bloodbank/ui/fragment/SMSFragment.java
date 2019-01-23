package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.users.password.change.NewPassword;
import com.internship.ipda3.semicolon.bloodbank.model.users.password.rest.RestPassword;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.internship.ipda3.semicolon.bloodbank.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.intent;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.checkCode;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isPasswordConfirm;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePassword;

/**
 * A simple {@link Fragment} subclass.
 */
public class SMSFragment extends Fragment {
    @BindView(R.id.verify_code_edit)
    EditText verifyCodeEdit;
    @BindView(R.id.new_password_edit)
    EditText newPasswordEdit;
    @BindView(R.id.repeat_password_edit)
    EditText repeatPasswordEdit;
    Unbinder unbinder;
    private FragmentManager mManager;
    private String mPhoneNumber;

    private ApiEndPoint mEndPoint;

    public SMSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sm, container, false);


        if (getArguments() != null) {
            mPhoneNumber = getArguments().getString("phone");
        }
        verbose("onCreateView:SMS Fragment: phone number: " + mPhoneNumber);

        mEndPoint = getClient().create(ApiEndPoint.class);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.change_password_button)
    public void onViewClicked() {
        String pinCode = verifyCodeEdit.getText().toString();
        String password = newPasswordEdit.getText().toString();
        String repeatedPassword = repeatPasswordEdit.getText().toString();
        changePassword(pinCode, password, repeatedPassword);
    }

    private void changePassword(String pinCode, String password, String repeatedPassword) {
        if (!validatePassword(password)) {
            newPasswordEdit.setError(getString(R.string.password_error));
            return;
        }

        if (!isPasswordConfirm(password, repeatedPassword)) {
            repeatPasswordEdit.setError("The password is wrong.");
            return;
        }

        newPassword(password, repeatedPassword, pinCode, mPhoneNumber);

    }

    private void newPassword(String password, String repeatedPassword, String pinCode, String mPhoneNumber) {
        mEndPoint.newPassword(password, repeatedPassword, pinCode, mPhoneNumber)
                .enqueue(new Callback<NewPassword>() {
                    @Override
                    public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
                        if (response.isSuccessful() && response.body().getStatus() == 1) {
                            showToast(getContext(), response.body().getMsg());
                            intent(getContext(), MainActivity.class);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewPassword> call, Throwable t) {
                        verbose("onNewPasswordResponseFailure: " + t.getMessage());

                    }
                });

    }
}
