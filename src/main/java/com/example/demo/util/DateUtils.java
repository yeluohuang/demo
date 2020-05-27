package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 日期类
 * @author zhushj3
 * @date 2020/05/22
 */
public class DateUtils {
    private static String format = "yyyy-MM-dd HH:mm:ss";

    /**
     * 标准化日期输出：yyyy-MM-dd HH:mm:ss
     * @param date 日期
     * @return  字符串输出
     */
    public static String formatToStandard(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 对将指定日期格式化为指定的格式
     * @param date 日期
     * @param string 日期格式
     * @return 字符串类型
     */
    public static String formatByFormat(Date date,String string) {
        SimpleDateFormat sdf = new SimpleDateFormat(string);
        return sdf.format(date);
    }
}
