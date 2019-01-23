
package com.internship.ipda3.semicolon.bloodbank.model.notification.setting;

import com.google.gson.annotations.SerializedName;


public class NotificationSetting {

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
