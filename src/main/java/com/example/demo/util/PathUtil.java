package com.example.demo.util;

import java.io.File;

/**
 * 项目路径相关工具类
 * @author zhushj3
 * @date 2020/04/29
 */
public class PathUtil {
    // 完整路径：/${root}/file/file /${root}/file/img   /${root}/file/temp
    private static final String FILE_PARENT="file";
    private static final String IMG_PATH="img";   // 图片存储目录
    private static final String FILE_PATH="file"; // 文件存储目录
    private static final String TEMP_PATH="temp";  // 临时目录

    /** 获取项目根路径
     * @return 项目的绝对根路径
     */
    public static String getRootPath(){
        return System.getProperty("user.dir");
    }

    /** 获取项目根路径上级目录
     * @return 项目的绝对根路径上级目录
     */
    public static String getRootParentPath(){
        return  new File(getRootPath()).getAbsolutePath();
    }

    /**
     * 存储文件的根路径
     * @return
     */
    private static String getFileRootPath(){
        return getRootPath()+File.separator+FILE_PARENT;
    }

    /** 获取项目文件目录
     * @return 文件目录路径
     */
    public static String getFilePath(){
        return getFileRootPath()+File.separator+FILE_PATH;
    }

    /** 获取项目文件目录
     * @return 文件目录相对路径
     */
    public static String getFileRelativePath(){
        return FILE_PARENT +File.separator+FILE_PATH+File.separator+FILE_PATH;
    }

    /** 获取项目图片目录
     * @return 图片目录路径
     */
    public static String getImgPath(){
        return getFileRootPath()+File.separator+IMG_PATH;
    }

    /** 获取项目图片目录
     * @return 图片相对路径
     */
    public static String getImgRelativePath(){
        return FILE_PARENT +File.separator+IMG_PATH+File.separator+FILE_PATH;
    }
    /** 获取项目文件临时目录
     * @return 文件目临时录路径
     */
    public static String getTempPath(){
        return getFileRootPath()+File.separator+TEMP_PATH;
    }
}
