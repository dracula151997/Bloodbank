package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.ipda3.semicolon.bloodbank.EndlessRecyclerOnScrollListener;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.adapter.RequestsRecyclerView;
import com.internship.ipda3.semicolon.bloodbank.model.donation.requests.DonationRequests;
import com.internship.ipda3.semicolon.bloodbank.model.donation.requests.RequestsDatum;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.LoadStringData;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodRequestFragment extends Fragment {


    @BindView(R.id.requests_blood_recycler)
    RecyclerView requestsBloodRecycler;
    Unbinder unbinder;
    String apiToken;
    private ApiEndPoint mEndPoint;


    public BloodRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blood_request, container, false);

        unbinder = ButterKnife.bind(this, view);

        apiToken = LoadStringData(getActivity(), API_TOKEN);
        setupRecyclerView();
        mEndPoint = getClient().create(ApiEndPoint.class);
        retrieveBloodRequests();

        return view;
    }

    private void retrieveBloodRequests() {
        mEndPoint.getDonationRequests(apiToken)
                .enqueue(new Callback<DonationRequests>() {
                    @Override
                    public void onResponse(Call<DonationRequests> call, Response<DonationRequests> response) {
                        verbose("retrieveBloodRequests: onResponse: " + response.raw());
                        List<RequestsDatum> requestsData = response.body().getData().getData();
                        requestsBloodRecycler.setAdapter(new RequestsRecyclerView(requestsData, getContext()));

                    }

                    @Override
                    public void onFailure(Call<DonationRequests> call, Throwable t) {

                    }
                });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        requestsBloodRecycler.setLayoutManager(manager);
        requestsBloodRecycler.setHasFixedSize(true);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
