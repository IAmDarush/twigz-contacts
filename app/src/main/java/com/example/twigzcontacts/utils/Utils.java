package com.example.twigzcontacts.utils;

import java.util.Random;

/**
 * Created by darush on 2/28/18.
 */

public class Utils {


    public static int getRandomOtp() {
        Random random = new Random();
        int num = 100000 + random.nextInt(900000);
        return num;
    }

}
