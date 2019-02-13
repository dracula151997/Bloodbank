package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.adapter.NotificationSettingsAdapter;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.GavernoratesDatum;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.Governorates;
import com.internship.ipda3.semicolon.bloodbank.model.notification.setting.NotificationSetting;
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
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.LoadStringData;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationSettingFragment extends Fragment {


    @BindView(R.id.gavernorate_recycler)
    RecyclerView gavernorateRecycler;
    Unbinder unbinder;
    @BindView(R.id.blood_type_recycler)
    RecyclerView bloodTypeRecycler;
    String apiToken;
    NotificationSettingsAdapter bloodTypeAdapter, governorateAdapter;
    private ApiEndPoint mEndPoint;


    public NotificationSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_setting, container, false);

        unbinder = ButterKnife.bind(this, view);
        setupGavernorateRecycler();
        setupBloodTypeRecycler();

        apiToken = LoadStringData(getActivity(), API_TOKEN);


        mEndPoint = getClient().create(ApiEndPoint.class);

        loadGavernorate();


        return view;
    }

    private void setupBloodTypeRecycler() {
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 4);
        bloodTypeRecycler.setLayoutManager(manager);
        bloodTypeRecycler.setHasFixedSize(true);

        List<String> bloodTypeList = new ArrayList<>();
        bloodTypeList.add("A+");
        bloodTypeList.add("A-");
        bloodTypeList.add("B+");
        bloodTypeList.add("B-");
        bloodTypeList.add("AB+");
        bloodTypeList.add("AB-");
        bloodTypeList.add("O+");
        bloodTypeList.add("O-");

        bloodTypeAdapter = new NotificationSettingsAdapter(bloodTypeList, getContext(), 1);
        bloodTypeRecycler.setAdapter(bloodTypeAdapter);


    }

    private void loadGavernorate() {
        mEndPoint.getGovernorates()
                .enqueue(new Callback<Governorates>() {
                    @Override
                    public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                        long status = response.body().getStatus();
                        List<String> list = new ArrayList<>();
                        String msg = response.body().getMsg();
                        if (status == 1) {
                            verbose("onResponse: getGovernorates: " + msg);
                            List<GavernoratesDatum> gavernoratesData = response.body().getData();
                            for (int i = 0; i < gavernoratesData.size(); i++) {
                                String gavernorateName = gavernoratesData.get(i).getName();
                                verbose("onResponse: getGavernorate: name: " + gavernorateName);

                                list.add(gavernorateName);
                            }

                            governorateAdapter = new NotificationSettingsAdapter(list, getContext(), 2);
                            gavernorateRecycler.setAdapter(governorateAdapter);


                        } else {
                            error("onResponse: getGovernorates: " + msg);
                        }
                    }

                    @Override
                    public void onFailure(Call<Governorates> call, Throwable t) {
                        error("onFailure: getGovernorates: " + t.getMessage());

                    }
                });
    }

    private void setupGavernorateRecycler() {
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 4);
        gavernorateRecycler.setLayoutManager(manager);
        gavernorateRecycler.setHasFixedSize(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.save_settings_button)
    public void onViewClicked() {
        Object[] bloodArray = bloodTypeAdapter.getBloodArray();
        Object[] governorateArray = governorateAdapter.getGovernorateArray();

        mEndPoint.notificationSettings(apiToken, bloodArray, governorateArray)
                .enqueue(new Callback<NotificationSetting>() {
                    @Override
                    public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {
                        if (response.code() == 200) {
                            long status = response.body().getStatus();
                            if (status == 1) {
                                showToast(getContext(), "response msg: " + response.body().getMsg());
                            }else{
                                showToast(getContext(), "response msg: " + response.body().getMsg());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationSetting> call, Throwable t) {
                        error("onFailure: response error: " + t.getMessage());

                    }
                });


    }
}
