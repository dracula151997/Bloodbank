
package com.internship.ipda3.semicolon.bloodbank.model.posts.post;

import com.google.gson.annotations.SerializedName;


public class Posts {

    @SerializedName("data")
    private PostsData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public PostsData getData() {
        return mData;
    }

    public void setData(PostsData data) {
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
