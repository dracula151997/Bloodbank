
package com.internship.ipda3.semicolon.bloodbank.model.notification.setting;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class SettingData {

    @SerializedName("bloodTypes")
    private List<String> mBloodTypes;
    @SerializedName("cities")
    private List<String> mCities;

    public List<String> getBloodTypes() {
        return mBloodTypes;
    }

    public void setBloodTypes(List<String> bloodTypes) {
        mBloodTypes = bloodTypes;
    }

    public List<String> getCities() {
        return mCities;
    }

    public void setCities(List<String> cities) {
        mCities = cities;
    }

    @Override
    public String toString() {
        return "SettingData{" +
                "mBloodTypes=" + mBloodTypes +
                ", mCities=" + mCities +
                '}';
    }
}
