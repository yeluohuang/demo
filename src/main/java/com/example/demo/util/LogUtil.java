package com.example.demo.util;

import java.util.HashMap;

/**
 * @author zhushj3
 * @description  对当前请求的关键参数进行拼接
 * @date 2020/05/28
 **/
public class LogUtil {
    private static HashMap<String,String> hashmap = new HashMap<>();

    /**
     * 给当前线程添加日志信息
     * @param value 日志信息
     */
    public static void addValue(String value){
        String threadName = Thread.currentThread().getName();
        String temp = hashmap.get(threadName);
        // 将当前的日志信息与已有的信息进行拼接
        if(temp == null){
            hashmap.put(threadName,value);
        } else {
            hashmap.put(threadName,temp+"&"+value);
        }
    }

    /**
     * 获取当前线程对应的日志信息
     * @return 日志信息
     */
    public static String getValue(){
        String result = hashmap.get(Thread.currentThread().getName());
        return result==null?"":result;
    }

    /**
     * 清除当前线程对应的日志信息
     */
    public static void clearValue() {
        hashmap.remove(Thread.currentThread().getName());
    }

    /**
     * 清除所有线程的日志信息
     */
    public static void clearAll() {
        hashmap.clear();
    }
}
