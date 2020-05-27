package com.example.demo.business.test.service;

import com.example.demo.business.test.pojo.FileRequestModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @description 文件业务接口
 * @author zhushj3
 * @date 2020/04/29
 */
public interface FileService {
    /**
     * 上传文件
     * @param file  文件对象
     */
    String uploadFile(MultipartFile file) throws Exception;
    void downloadFile(FileRequestModel model, HttpServletResponse response) throws Exception;
}
