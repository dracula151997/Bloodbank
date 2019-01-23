
package com.internship.ipda3.semicolon.bloodbank.model.notification.count;

import com.google.gson.annotations.SerializedName;


public class CountData {

    @SerializedName("notifications_count")
    private Long mNotificationsCount;

    public Long getNotificationsCount() {
        return mNotificationsCount;
    }

    public void setNotificationsCount(Long notificationsCount) {
        mNotificationsCount = notificationsCount;
    }

}
