
package com.internship.ipda3.semicolon.bloodbank.model.posts.toggle;

import com.google.gson.annotations.SerializedName;


public class FavoritePostToggle {

    @SerializedName("data")
    private ToggleData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public ToggleData getData() {
        return mData;
    }

    public void setData(ToggleData data) {
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
