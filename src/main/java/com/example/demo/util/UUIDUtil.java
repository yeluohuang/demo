package com.example.demo.util;

import java.util.UUID;

/**
 * 随机数类
 * @author zhushj3
 * @date 2020/04/30
 */
public class UUIDUtil {
    /**
     * 基于JAVA自带的函数随机生成uuid
     * @return UUID字符串(长度32)，主要由日期、时钟序列、系统设备号构成
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }
}
