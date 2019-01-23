
package com.internship.ipda3.semicolon.bloodbank.model.posts.favorite;

import com.google.gson.annotations.SerializedName;

public class FavoritePostPivot {

    @SerializedName("client_id")
    private String mClientId;
    @SerializedName("post_id")
    private String mPostId;

    public String getClientId() {
        return mClientId;
    }

    public void setClientId(String clientId) {
        mClientId = clientId;
    }

    public String getPostId() {
        return mPostId;
    }

    public void setPostId(String postId) {
        mPostId = postId;
    }

}
