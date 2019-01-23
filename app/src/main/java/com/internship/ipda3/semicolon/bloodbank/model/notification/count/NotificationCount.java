
package com.internship.ipda3.semicolon.bloodbank.model.notification.count;

import com.google.gson.annotations.SerializedName;


public class NotificationCount {

    @SerializedName("data")
    private CountData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public CountData getData() {
        return mData;
    }

    public void setData(CountData data) {
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
