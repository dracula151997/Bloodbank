
package com.internship.ipda3.semicolon.bloodbank.model.general.governorates;

import com.google.gson.annotations.SerializedName;


public class GavernoratesDatum {

    @SerializedName("created_at")
    private Object mCreatedAt;
    @SerializedName("id")
    private Integer mId;
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

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "GavernoratesDatum{" +
                "mCreatedAt=" + mCreatedAt +
                ", mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mUpdatedAt=" + mUpdatedAt +
                '}';
    }
}
