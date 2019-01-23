
package com.internship.ipda3.semicolon.bloodbank.model.donation.requests;

import com.google.gson.annotations.SerializedName;

public class DonationRequests {

    @SerializedName("data")
    private RequestsData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public RequestsData getData() {
        return mData;
    }

    public void setData(RequestsData data) {
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
