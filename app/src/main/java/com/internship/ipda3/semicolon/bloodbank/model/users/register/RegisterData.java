
package com.internship.ipda3.semicolon.bloodbank.model.register;

import com.google.gson.annotations.SerializedName;


public class RegisterData {

    @SerializedName("api_token")
    private String mApiToken;
    @SerializedName("client")
    private RegisterClient mClient;

    public String getApiToken() {
        return mApiToken;
    }

    public void setApiToken(String apiToken) {
        mApiToken = apiToken;
    }

    public RegisterClient getClient() {
        return mClient;
    }

    public void setClient(RegisterClient client) {
        mClient = client;
    }

}
