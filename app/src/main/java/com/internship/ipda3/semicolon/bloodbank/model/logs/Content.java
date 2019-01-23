
package com.internship.ipda3.semicolon.bloodbank.model.logs;

import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("api_token")
    private String mApiToken;
    @SerializedName("message")
    private String mMessage;

    public String getApiToken() {
        return mApiToken;
    }

    public void setApiToken(String apiToken) {
        mApiToken = apiToken;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
