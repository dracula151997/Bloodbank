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
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationSettingFragment extends Fragment {


    @BindView(R.id.gavernorate_recycler)
    RecyclerView gavernorateRecycler;
    Unbinder unbinder;
    @BindView(R.id.blood_type_recycler)
    RecyclerView bloodTypeRecycler;


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

        bloodTypeRecycler.setAdapter(new NotificationSettingsAdapter(bloodTypeList, getContext()));

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
                            gavernorateRecycler.setAdapter(new NotificationSettingsAdapter(list, getContext()));
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
}
