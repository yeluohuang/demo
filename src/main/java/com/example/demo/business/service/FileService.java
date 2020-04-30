package com.example.demo.business.service;

import com.example.demo.business.pojo.FileRequestModel;
import com.example.demo.exception.TestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件业务接口
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
