package com.example.demo.business.test.controller;

import com.example.demo.business.test.pojo.FileRequestModel;
import com.example.demo.business.test.pojo.TestResponse;
import com.example.demo.business.test.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @description 文件上传/下载接口
 * @author zhushj3
 * @date 2020/04/29
 */
@RestController
@RequestMapping("/file_download")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;

    /**
     * 上传单个文件
     * @param file  文件
     * @return
             * @throws Exception
     */
    @PostMapping(value="/file_upload")
    public TestResponse upLoadPicture(@RequestParam("file") MultipartFile file) throws Exception{
        String fileName = fileService.uploadFile(file);
        return TestResponse.ok("上传文件成功",fileName);
    }

    /**
     * 下载单个文件
     * @param model 请求参数
     * @param result 参数校验结果
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/file_download")
    public TestResponse downloadPicture(@RequestBody @Validated(value = FileRequestModel.EmptyDefault.class) FileRequestModel model, BindingResult result, HttpServletResponse response) throws Exception{
        if(result.hasErrors()){
            return TestResponse.error(result.getFieldError().getDefaultMessage());
        }
        fileService.downloadFile(model,response);
        return null;
    }
}
