
package com.internship.ipda3.semicolon.bloodbank.model.general.contact;

import com.google.gson.annotations.SerializedName;

public class ContactUs {

    @SerializedName("data")
    private ContactUsData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public ContactUsData getData() {
        return mData;
    }

    public void setData(ContactUsData data) {
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
