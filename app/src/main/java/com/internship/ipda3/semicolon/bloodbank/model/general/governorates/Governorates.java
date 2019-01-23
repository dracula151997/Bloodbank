
package com.internship.ipda3.semicolon.bloodbank.model.general.governorates;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Governorates {

    @SerializedName("data")
    private List<GavernoratesDatum> mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public List<GavernoratesDatum> getData() {
        return mData;
    }

    public void setData(List<GavernoratesDatum> data) {
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
