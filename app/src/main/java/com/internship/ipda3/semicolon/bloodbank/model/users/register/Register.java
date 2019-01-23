
package com.internship.ipda3.semicolon.bloodbank.model.register;

import com.google.gson.annotations.SerializedName;


public class Register {

    @SerializedName("data")
    private RegisterData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public RegisterData getData() {
        return mData;
    }

    public void setData(RegisterData data) {
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
