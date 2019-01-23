
package com.internship.ipda3.semicolon.bloodbank.model.donation.request;

import com.google.gson.annotations.SerializedName;


public class City {

    @SerializedName("created_at")
    private Object mCreatedAt;
    @SerializedName("governorate")
    private Governorate mGovernorate;
    @SerializedName("governorate_id")
    private String mGovernorateId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("updated_at")
    private Object mUpdatedAt;

    public Object getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Object createdAt) {
        mCreatedAt = createdAt;
    }

    public Governorate getGovernorate() {
        return mGovernorate;
    }

    public void setGovernorate(Governorate governorate) {
        mGovernorate = governorate;
    }

    public String getGovernorateId() {
        return mGovernorateId;
    }

    public void setGovernorateId(String governorateId) {
        mGovernorateId = governorateId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Object getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
