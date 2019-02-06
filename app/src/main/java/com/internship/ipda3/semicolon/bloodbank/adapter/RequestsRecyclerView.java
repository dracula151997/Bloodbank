package com.internship.ipda3.semicolon.bloodbank.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.donation.requests.DonationRequests;
import com.internship.ipda3.semicolon.bloodbank.model.donation.requests.RequestsDatum;
import com.internship.ipda3.semicolon.bloodbank.ui.activity.RequestDetailsActivity;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.PostDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.intent;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.intentWithExtra;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.intentWithPhoneAction;

public class RequestsRecyclerView extends RecyclerView.Adapter<RequestsRecyclerView.ViewHolder> {

    private List<RequestsDatum> donationRequestsList = new ArrayList<>();
    private Context context;

    public RequestsRecyclerView(List<RequestsDatum> donationRequestsList, Context context) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RequestsDatum requestsData = donationRequestsList.get(position);

        holder.bloodTypeText.setText(requestsData.getBloodType());
        holder.cityNameText.setText(requestsData.getCity().getName());
        holder.hospitalNameText.setText(requestsData.getHospitalName());
        holder.patientNameText.setText(requestsData.getPatientName());

        holder.contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentWithPhoneAction(context, requestsData.getPhone());


            }
        });

        holder.detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String donationID = String.valueOf(requestsData.getId());
                intentWithExtra(context, RequestDetailsActivity.class, "donation_id", donationID);
            }
        });


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
