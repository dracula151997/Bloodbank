package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.general.contact.ContactUs;
import com.internship.ipda3.semicolon.bloodbank.model.general.settings.Settings;
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
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.ValidationUtil.isEmpty;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {


    @BindView(R.id.phone_number_text)
    TextView phoneNumberText;
    @BindView(R.id.email_text)
    TextView emailText;
    @BindView(R.id.facebook_image_view)
    ImageView facebookImageView;
    @BindView(R.id.twitter_image_view)
    ImageView twitterImageView;
    @BindView(R.id.whatsapp_image_view)
    ImageView whatsappImageView;
    @BindView(R.id.google_plus_image_view)
    ImageView googlePlusImageView;
    @BindView(R.id.instagram_image_view)
    ImageView instagramImageView;
    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.message_title_edit_text)
    EditText messageTitleEditText;
    @BindView(R.id.message_content_edit_text)
    EditText messageContentEditText;
    Unbinder unbinder;


    private ApiEndPoint mEndPoint;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        unbinder = ButterKnife.bind(this, view);

        mEndPoint = getClient().create(ApiEndPoint.class);
        contactUs();

        return view;
    }

    private void contactUs() {
        mEndPoint.settings(API_TOKEN)
                .enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        String msg = response.body().getMsg();
                        if (response.body().getStatus() == 1) {
                            verbose("onResponse: settings: " + msg);
                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {
                        error("onFailure: settings: " + t.getMessage());

                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.send_message_button)
    public void onViewClicked() {
        String title = messageTitleEditText.getText().toString();
        String message = messageContentEditText.getText().toString();

        if (isEmpty(title)) {
            setError(messageTitleEditText, R.string.required, getContext());
            return;
        }

        if (isEmpty(message)) {
            setError(messageContentEditText, R.string.required, getContext());
            return;
        }


        if (isNetworkAvailable(getContext())) {
            sendMessage(title, message);
        }
    }

    private void sendMessage(String title, final String message) {
        mEndPoint.contactUs(API_TOKEN, title, message)
                .enqueue(new Callback<ContactUs>() {
                    @Override
                    public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                        String msg = response.body().getMsg();
                        long status = response.body().getStatus();
                        if (status == 1) {
                            showToast(getContext(), msg);
                        } else {
                            showToast(getContext(), msg);
                        }
                    }

                    @Override
                    public void onFailure(Call<ContactUs> call, Throwable t) {
                        error("onFailure: contact us: " + t.getMessage());

                    }
                });
    }
}
