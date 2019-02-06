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
import com.internship.ipda3.semicolon.bloodbank.model.CheckBoxModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

public class NotificationSettingsAdapter extends
        RecyclerView.Adapter<NotificationSettingsAdapter.ViewHolder> {


    private List<String> dataList = new ArrayList<>();
    private Context context;

    private List<CheckBoxModel> bloodTypesSetting = new ArrayList<>();

    public NotificationSettingsAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
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

        viewHolder.checkBox.setText(data);


        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    bloodTypesSetting.add(new CheckBoxModel(dataList.get(position), position, isChecked));
                    verbose("check box list: " + bloodTypesSetting.toString());



            }
        });



    }


    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gavernorate_check_box)
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

    public List<CheckBoxModel> getList(){
        return bloodTypesSetting;
    }
}
