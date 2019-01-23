
package com.internship.ipda3.semicolon.bloodbank.model.donation.request;

import com.google.gson.annotations.SerializedName;


public class CreateRequestData {

    @SerializedName("bags_num")
    private String mBagsNum;
    @SerializedName("blood_type")
    private String mBloodType;
    @SerializedName("city")
    private City mCity;
    @SerializedName("city_id")
    private String mCityId;
    @SerializedName("client_id")
    private Long mClientId;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("hospital_address")
    private String mHospitalAddress;
    @SerializedName("hospital_name")
    private String mHospitalName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("notes")
    private String mNotes;
    @SerializedName("patient_age")
    private String mPatientAge;
    @SerializedName("patient_name")
    private String mPatientName;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getBagsNum() {
        return mBagsNum;
    }

    public void setBagsNum(String bagsNum) {
        mBagsNum = bagsNum;
    }

    public String getBloodType() {
        return mBloodType;
    }

    public void setBloodType(String bloodType) {
        mBloodType = bloodType;
    }

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        mCity = city;
    }

    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String cityId) {
        mCityId = cityId;
    }

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

    public String getHospitalAddress() {
        return mHospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        mHospitalAddress = hospitalAddress;
    }

    public String getHospitalName() {
        return mHospitalName;
    }

    public void setHospitalName(String hospitalName) {
        mHospitalName = hospitalName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public String getPatientAge() {
        return mPatientAge;
    }

    public void setPatientAge(String patientAge) {
        mPatientAge = patientAge;
    }

    public String getPatientName() {
        return mPatientName;
    }

    public void setPatientName(String patientName) {
        mPatientName = patientName;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
