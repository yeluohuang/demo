package com.example.demo.util;

/**
 * @description 验证数据工具类
 * @author zhushj3
 * @date 2020/04/28
 */
public class ValidateUtils {

    /**
     * 判断是否是空白字符串:不为空，且trim以后长度>0
     * @param data 输入
     * @return true为真，false为假
     */
    public static boolean isBlank(String data){
        if(isNull(data)) {
            return true;
        } else {
            return isEmpty(data.trim());
        }
    }

    /**
     * 判断是否为空：null或者空字符串
     * @param data 输入
     * @return true为真，false为假
     */
    public static boolean isEmpty(String data){
        if(isNull(data)){
            return true;
        } else {
            return "".equals(data);
        }
    }

    /**
     * 判断是否为Null
     * @param data 输入
     * @return true为真，false为假
     */
    public static boolean isNull(String data){
        return data == null;
    }
}
