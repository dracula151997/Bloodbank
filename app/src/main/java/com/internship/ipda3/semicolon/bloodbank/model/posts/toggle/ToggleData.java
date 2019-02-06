
package com.internship.ipda3.semicolon.bloodbank.model.posts.toggle;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ToggleData {

    @SerializedName("attached")
    private List<Object> mAttached;
    @SerializedName("detached")
    private List<Long> mDetached;

    public List<Object> getAttached() {
        return mAttached;
    }

    public void setAttached(List<Object> attached) {
        mAttached = attached;
    }

    public List<Long> getDetached() {
        return mDetached;
    }

    public void setDetached(List<Long> detached) {
        mDetached = detached;
    }

    @Override
    public String toString() {
        return "ToggleData{" +
                "mAttached=" + mAttached +
                ", mDetached=" + mDetached +
                '}';
    }
}
