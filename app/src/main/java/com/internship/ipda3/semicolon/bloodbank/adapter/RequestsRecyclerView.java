package com.internship.ipda3.semicolon.bloodbank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.donation.requests.DonationRequests;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestsRecyclerView extends RecyclerView.Adapter<RequestsRecyclerView.ViewHolder> {

    private List<DonationRequests> donationRequestsList = new ArrayList<>();
    private Context context;

    public RequestsRecyclerView(List<DonationRequests> donationRequestsList, Context context) {
        this.donationRequestsList = donationRequestsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.requests_blood_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return donationRequestsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.blood_type_text)
        TextView bloodTypeText;
        @BindView(R.id.patient_name_text)
        TextView patientNameText;
        @BindView(R.id.hospital_name_text)
        TextView hospitalNameText;
        @BindView(R.id.city_name_text)
        TextView cityNameText;
        @BindView(R.id.detail_button)
        Button detailButton;
        @BindView(R.id.contact_button)
        Button contactButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
