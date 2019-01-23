
package com.internship.ipda3.semicolon.bloodbank.model.general.settings;

import com.google.gson.annotations.SerializedName;


public class SettingsData {

    @SerializedName("about_app")
    private String mAboutApp;
    @SerializedName("created_at")
    private Object mCreatedAt;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("facebook_url")
    private String mFacebookUrl;
    @SerializedName("google_url")
    private String mGoogleUrl;
    @SerializedName("id")
    private Long mId;
    @SerializedName("instagram_url")
    private String mInstagramUrl;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("twitter_url")
    private String mTwitterUrl;
    @SerializedName("updated_at")
    private Object mUpdatedAt;
    @SerializedName("whatsapp")
    private String mWhatsapp;
    @SerializedName("youtube_url")
    private String mYoutubeUrl;

    public String getAboutApp() {
        return mAboutApp;
    }

    public void setAboutApp(String aboutApp) {
        mAboutApp = aboutApp;
    }

    public Object getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Object createdAt) {
        mCreatedAt = createdAt;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFacebookUrl() {
        return mFacebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        mFacebookUrl = facebookUrl;
    }

    public String getGoogleUrl() {
        return mGoogleUrl;
    }

    public void setGoogleUrl(String googleUrl) {
        mGoogleUrl = googleUrl;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getInstagramUrl() {
        return mInstagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        mInstagramUrl = instagramUrl;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getTwitterUrl() {
        return mTwitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        mTwitterUrl = twitterUrl;
    }

    public Object getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getWhatsapp() {
        return mWhatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        mWhatsapp = whatsapp;
    }

    public String getYoutubeUrl() {
        return mYoutubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        mYoutubeUrl = youtubeUrl;
    }

}
