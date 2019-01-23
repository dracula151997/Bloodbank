
package com.internship.ipda3.semicolon.bloodbank.model.users.password.rest;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class RestPasswordData {

    @SerializedName("email")
    private String mEmail;
    @SerializedName("mail_fails")
    private List<Object> mMailFails;
    @SerializedName("pin_code_for_test")
    private Long mPinCodeForTest;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public List<Object> getMailFails() {
        return mMailFails;
    }

    public void setMailFails(List<Object> mailFails) {
        mMailFails = mailFails;
    }

    public Long getPinCodeForTest() {
        return mPinCodeForTest;
    }

    public void setPinCodeForTest(Long pinCodeForTest) {
        mPinCodeForTest = pinCodeForTest;
    }

}
