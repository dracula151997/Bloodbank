
package com.internship.ipda3.semicolon.bloodbank.model.users.password.rest;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class RestPassword {

    @SerializedName("data")
    private RestPasswordData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public RestPasswordData getData() {
        return mData;
    }

    public void setData(RestPasswordData data) {
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
