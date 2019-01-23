
package com.internship.ipda3.semicolon.bloodbank.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("api_token")
    private String mApiToken;
    @SerializedName("client")
    private LoginClient mClient;

    public String getApiToken() {
        return mApiToken;
    }

    public void setApiToken(String apiToken) {
        mApiToken = apiToken;
    }

    public LoginClient getClient() {
        return mClient;
    }

    public void setClient(LoginClient client) {
        mClient = client;
    }

}
