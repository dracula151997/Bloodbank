
package com.internship.ipda3.semicolon.bloodbank.model.notification.notifications;

import com.google.gson.annotations.SerializedName;


public class NotificationPivot {

    @SerializedName("client_id")
    private String mClientId;
    @SerializedName("is_read")
    private String mIsRead;
    @SerializedName("notification_id")
    private String mNotificationId;

    public String getClientId() {
        return mClientId;
    }

    public void setClientId(String clientId) {
        mClientId = clientId;
    }

    public String getIsRead() {
        return mIsRead;
    }

    public void setIsRead(String isRead) {
        mIsRead = isRead;
    }

    public String getNotificationId() {
        return mNotificationId;
    }

    public void setNotificationId(String notificationId) {
        mNotificationId = notificationId;
    }

}
