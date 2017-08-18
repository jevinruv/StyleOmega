package com.example.jevin.styleomega.Others;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jevin on 8/13/2017.
 */

public class CommonMethods {

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isNicValid(String nic) {
        if (nic.length() == 10)
            return true;
        return false;
    }

    public static void displayToast(Context context,int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
