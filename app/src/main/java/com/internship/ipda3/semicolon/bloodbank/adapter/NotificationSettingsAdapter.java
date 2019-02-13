package com.internship.ipda3.semicolon.bloodbank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.internship.ipda3.semicolon.bloodbank.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationSettingsAdapter extends
        RecyclerView.Adapter<NotificationSettingsAdapter.ViewHolder> {


    private List<String> dataList = new ArrayList<>();
    private Context context;
    private int recyclerType;


    //String list for saving blood type text.
    private List<String> bloodTypeNotification = new ArrayList<>();
    //Integer list for saving governorate position.
    private List<Integer> governorateNotification = new ArrayList<>();


    public NotificationSettingsAdapter(List<String> dataList, Context context, int recyclerType) {
        this.dataList = dataList;
        this.context = context;
        this.recyclerType = recyclerType;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_notification_setting_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        String data = dataList.get(position);

        setData(viewHolder, data);
        setAction(viewHolder, position, data);


    }

    private void setAction(final ViewHolder viewHolder, final int position, final String data) {
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (recyclerType == 1) {
                    String checkText = buttonView.getText().toString();
                    bloodTypeNotification.add(checkText);
                } else {
                    governorateNotification.add((position + 1));
                }

            }
        });
    }

    private void setData(ViewHolder viewHolder, String data) {
        viewHolder.checkBox.setText(data);

    }


    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public Object[] getBloodArray() {
        return bloodTypeNotification.toArray();
    }

    public Object[] getGovernorateArray() {
        return governorateNotification.toArray();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gavernorate_check_box)
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
