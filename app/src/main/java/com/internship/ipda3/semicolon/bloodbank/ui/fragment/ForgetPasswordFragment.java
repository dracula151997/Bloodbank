package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.users.password.rest.RestPassword;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.commitFragment;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.validatePhone;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment {

    @BindView(R.id.phone_number_edit)
    EditText phoneNumberEdit;
    Unbinder unbinder;
    private FragmentManager mManager;
    private ApiEndPoint mEndPoint;

    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);

        mManager = getFragmentManager();
        mEndPoint = getClient().create(ApiEndPoint.class);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.send_button)
    public void onViewClicked() {
        sendRestPasswordCode();
    }

    private void sendRestPasswordCode() {
        String phoneNumber = phoneNumberEdit.getText().toString();
        if (!validatePhone(phoneNumber)) {
            phoneNumberEdit.setError("Phone number must be 11 digits.");
            return;
        }

        sendCode(phoneNumber);
    }

    private void sendCode(final String phoneNumber) {
        mEndPoint.resetPassword(phoneNumber)
                .enqueue(new Callback<RestPassword>() {
                    @Override
                    public void onResponse(Call<RestPassword> call, Response<RestPassword> response) {
                        if (response.isSuccessful() && response.body().getStatus() == 1) {
                            showToast(getContext(), response.body().getMsg());

                            Bundle bundle = new Bundle();

                            bundle.putString("phone", phoneNumber);
                            verbose("onRestPasswordResponse: pin code = " + phoneNumber);

                            SMSFragment smsFragment = new SMSFragment();
                            smsFragment.setArguments(bundle);
                            commitFragment(R.id.frame, smsFragment, getFragmentManager());

                        } else {
                            showToast(getContext(), response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<RestPassword> call, Throwable t) {
                        error("onRestPasswordFailure: " + t.getMessage());
                    }

                });
    }
}
