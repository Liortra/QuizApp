package com.example.quizapp.utils;

import android.text.TextUtils;
import android.util.Patterns;

public final class Validator {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidUserName(CharSequence target) {
        return (!TextUtils.isEmpty(target));
    }

    public static boolean isValidPhone(CharSequence target){
        return(!TextUtils.isEmpty(target));
    }

    public static boolean isValidPassword(CharSequence target){
        return (!TextUtils.isEmpty(target));
    }

}
