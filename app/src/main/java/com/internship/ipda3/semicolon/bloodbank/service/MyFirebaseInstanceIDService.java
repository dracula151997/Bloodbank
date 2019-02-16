package com.internship.ipda3.semicolon.bloodbank.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        verbose("refreshed token: " + refreshedToken);
    }


}
