
package com.internship.ipda3.semicolon.bloodbank.model.general.report;

import com.google.gson.annotations.SerializedName;


public class ReportData {

    @SerializedName("client_id")
    private Long mClientId;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public Long getClientId() {
        return mClientId;
    }

    public void setClientId(Long clientId) {
        mClientId = clientId;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
