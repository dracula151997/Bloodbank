package com.internship.ipda3.semicolon.bloodbank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.internship.ipda3.semicolon.bloodbank.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationSettingsAdapter extends
        RecyclerView.Adapter<NotificationSettingsAdapter.ViewHolder> {


    private List<String> dataList = new ArrayList<>();
    private Context context;

    public NotificationSettingsAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_notification_setting_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String data = dataList.get(position);

        viewHolder.gavernorateCheckBox.setText(data);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gavernorate_check_box)
        CheckBox gavernorateCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
