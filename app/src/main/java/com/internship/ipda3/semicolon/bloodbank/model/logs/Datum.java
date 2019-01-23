
package com.internship.ipda3.semicolon.bloodbank.model.logs;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("content")
    private Content mContent;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("service")
    private String mService;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public Content getContent() {
        return mContent;
    }

    public void setContent(Content content) {
        mContent = content;
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

    public String getService() {
        return mService;
    }

    public void setService(String service) {
        mService = service;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
