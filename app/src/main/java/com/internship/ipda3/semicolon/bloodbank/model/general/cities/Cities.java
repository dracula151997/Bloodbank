
package com.internship.ipda3.semicolon.bloodbank.model.general.cities;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Cities {

    @SerializedName("data")
    private List<CitiesDatum> mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public List<CitiesDatum> getData() {
        return mData;
    }

    public void setData(List<CitiesDatum> data) {
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
