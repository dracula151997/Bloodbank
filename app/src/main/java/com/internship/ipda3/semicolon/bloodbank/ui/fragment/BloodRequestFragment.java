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

import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodRequestFragment extends Fragment {
    private static final String API_TOKEN = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";


    @BindView(R.id.requests_blood_recycler)
    RecyclerView requestsBloodRecycler;
    Unbinder unbinder;

    private ApiEndPoint mEndPoint;

    int max = 0;

    public BloodRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blood_request, container, false);

        unbinder = ButterKnife.bind(this, view);
        setupRecyclerView();
        mEndPoint = getClient().create(ApiEndPoint.class);
        retrieveBloodRequests();

        return view;
    }

    private void retrieveBloodRequests() {
        mEndPoint.getDonationRequests(API_TOKEN)
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
/*
        requestsBloodRecycler.setOnScrollListener(new EndlessRecyclerOnScrollListener(manager) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= max){
                    max = current_page;
                    retrieveBloodRequests(current_page);

                }

            }
        });*/

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
