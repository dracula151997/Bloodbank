
package com.internship.ipda3.semicolon.bloodbank.model.general.settings;

import com.google.gson.annotations.SerializedName;
import com.internship.ipda3.semicolon.bloodbank.model.notification.setting.SettingData;


public class Settings {

    @SerializedName("data")
    private SettingData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public SettingData getData() {
        return mData;
    }

    public void setData(SettingData data) {
        mData = data;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
