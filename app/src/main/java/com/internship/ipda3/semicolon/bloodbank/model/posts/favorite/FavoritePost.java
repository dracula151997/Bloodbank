
package com.internship.ipda3.semicolon.bloodbank.model.posts.favorite;

import com.google.gson.annotations.SerializedName;

public class FavoritePost {

    @SerializedName("data")
    private FavoritePostData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public FavoritePostData getData() {
        return mData;
    }

    public void setData(FavoritePostData data) {
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
