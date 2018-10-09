package com.studentapp.utils;

import java.util.Random;

public class TestUtils {
    public static  String getRandomValue(){
        Random ramdom = new Random();
        int randomInt = ramdom.nextInt(100000);
        return Integer.toString(randomInt);
    }
}
