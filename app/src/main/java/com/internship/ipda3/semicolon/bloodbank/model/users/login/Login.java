
package com.internship.ipda3.semicolon.bloodbank.model.users.login;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("data")
    private LoginData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public LoginData getData() {
        return mData;
    }

    public void setData(LoginData data) {
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
