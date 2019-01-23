
package com.internship.ipda3.semicolon.bloodbank.model.posts.details;

import com.google.gson.annotations.SerializedName;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.PostsDatum;


public class PostDetials {

    @SerializedName("data")
    private PostsDatum mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public PostsDatum getData() {
        return mData;
    }

    public void setData(PostsDatum data) {
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
