
package com.internship.ipda3.semicolon.bloodbank.model.users.login;

import com.google.gson.annotations.SerializedName;

public class LoginClient {

    @SerializedName("birth_date")
    private String mBirthDate;
    @SerializedName("blood_type")
    private String mBloodType;
    @SerializedName("city_id")
    private String mCityId;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("donation_last_date")
    private String mDonationLastDate;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_active")
    private String mIsActive;
    @SerializedName("name")
    private String mName;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("pin_code")
    private String mPinCode;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(String birthDate) {
        mBirthDate = birthDate;
    }

    public String getBloodType() {
        return mBloodType;
    }

    public void setBloodType(String bloodType) {
        mBloodType = bloodType;
    }

    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String cityId) {
        mCityId = cityId;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDonationLastDate() {
        return mDonationLastDate;
    }

    public void setDonationLastDate(String donationLastDate) {
        mDonationLastDate = donationLastDate;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getIsActive() {
        return mIsActive;
    }

    public void setIsActive(String isActive) {
        mIsActive = isActive;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getPinCode() {
        return mPinCode;
    }

    public void setPinCode(String pinCode) {
        mPinCode = pinCode;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
