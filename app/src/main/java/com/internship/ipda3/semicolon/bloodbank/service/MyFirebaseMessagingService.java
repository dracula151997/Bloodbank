package com.internship.ipda3.semicolon.bloodbank.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.internship.ipda3.semicolon.bloodbank.R;

import java.util.Random;

import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String ADMIN_CHANNEL_ID = "channel_id";
    private NotificationManager notificationManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String message = remoteMessage.getData().get("message");

        String title = remoteMessage.getData().get("title");
        verbose(String.format("notification title and message: %s, %s", message, title));

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupChannel();
        }

        int notficationId = new Random().nextInt(6000);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), ADMIN_CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.drawable.logo)
                .setAutoCancel(true)
                .setContentText(message)
                .setContentTitle(title)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notficationId, notificationBuilder.build());

        super.onMessageReceived(remoteMessage);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannel() {
        CharSequence channelName = getString(R.string.notification_admin_channel_name);
        String channelDescription = getString(R.string.notification_channel_description);

        NotificationChannel notificationChannel = new NotificationChannel(ADMIN_CHANNEL_ID,
                channelName, NotificationManager.IMPORTANCE_LOW);
        notificationChannel.setDescription(channelDescription);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);

        if (notificationManager != null)
            notificationManager.createNotificationChannel(notificationChannel);

    }
}
