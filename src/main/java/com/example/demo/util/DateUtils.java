package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static String format = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
