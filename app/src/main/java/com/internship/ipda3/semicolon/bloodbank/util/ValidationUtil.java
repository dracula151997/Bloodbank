package com.internship.ipda3.semicolon.bloodbank.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

public class ValidationUtil {

    public static boolean validatePhone(String phone) {
        return phone.length() == 11 && TextUtils.isDigitsOnly(phone);


    }

    public static boolean validatePassword(String password) {
        return password.length() >= 3;

    }

    public static boolean validateName(String name) {
        final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
        return name.matches(USERNAME_PATTERN);



    }

    public static boolean validateEmail(String email) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        //username@example.com
    }

    public static boolean isPasswordConfirm(String originalPassword, String repeatedPassword) {
        return originalPassword.equals(repeatedPassword);
    }

    public static boolean checkCode(String originalCode, String code) {
        return originalCode.equals(code);

    }

}
