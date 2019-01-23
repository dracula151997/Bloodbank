
package com.internship.ipda3.semicolon.bloodbank.model.general.report;

import com.google.gson.annotations.SerializedName;


public class Report {

    @SerializedName("data")
    private ReportData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public ReportData getData() {
        return mData;
    }

    public void setData(ReportData data) {
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
