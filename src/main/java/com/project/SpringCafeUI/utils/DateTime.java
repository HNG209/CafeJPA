package com.project.SpringCafeUI.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(currentTime);
    }

    public static String getDate() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(currentTime);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
