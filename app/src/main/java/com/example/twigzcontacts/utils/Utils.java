package com.example.twigzcontacts.utils;

import android.util.Base64;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

/**
 * Created by darush on 2/28/18.
 */

public class Utils {


    /**
     * Generate a random 6 digit OTP code
     * @return 6 digit random OTP integer code
     */
    public static int getRandomOtp() {
        Random random = new Random();
        int num = 100000 + random.nextInt(900000);
        return num;
    }

    public static String getBase64EncodedCredentials(String ACCOUNT_SID, String AUTH_TOKEN) {
        byte[] bytes = (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes();
        return "Basic " + Base64.encodeToString(bytes , Base64.NO_WRAP);
    }

    /**
     * Change UTC time to Indian Standard Time
     * @param time UTC Time
     * @return Indian Standard Time
     */
    public static String getIndianTime(String time) {
        SimpleDateFormat givenFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat expectedFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        expectedFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

        Date date;
        try {
            date = givenFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        String indianTime = expectedFormat.format(date);
        return indianTime;
    }

}
