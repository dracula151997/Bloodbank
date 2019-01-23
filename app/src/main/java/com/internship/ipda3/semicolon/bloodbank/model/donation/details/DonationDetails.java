
package com.internship.ipda3.semicolon.bloodbank.model.donation.details;

import com.google.gson.annotations.SerializedName;


public class DonationDetails {

    @SerializedName("data")
    private DonationDetailData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public DonationDetailData getData() {
        return mData;
    }

    public void setData(DonationDetailData data) {
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
