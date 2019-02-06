
package com.internship.ipda3.semicolon.bloodbank.model.donation.request;

import com.google.gson.annotations.SerializedName;

public class CreateRequest {

    @SerializedName("data")
    private CreateRequestData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public CreateRequestData getData() {
        return mData;
    }

    public void setData(CreateRequestData data) {
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

    @Override
    public String toString() {
        return "CreateRequest{" +
                "mData=" + mData +
                ", mMsg='" + mMsg + '\'' +
                ", mStatus=" + mStatus +
                '}';
    }
}
