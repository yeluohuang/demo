package com.example.demo.business.test.service.serviceImpl;

import com.example.demo.business.test.pojo.FileRequestModel;
import com.example.demo.business.test.service.FileService;
import com.example.demo.exception.ExceptionEnum;
import com.example.demo.exception.TestException;
import com.example.demo.util.PathUtil;
import com.example.demo.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/**
 * 文件业务实现类
 * @author zhushj3
 * @date 2020/04/29
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName= UUIDUtil.getUUID() +file.getOriginalFilename();
        logger.info("当前文件名"+fileName);
        String filePath = "";
        // 判断文件的类型
        if(fileName.endsWith("jpg") || fileName.endsWith("png") || fileName.endsWith("jpeg") || fileName.endsWith("bmp")){
            filePath = PathUtil.getImgPath()+File.separator+fileName;
        } else {
            filePath = PathUtil.getFilePath()+File.separator+fileName;
        }
        logger.info("当前文件存储路径为"+filePath);
        // 判断目录是否存在
        File temp = new File(filePath);
        if(!temp.exists()){
            if(!temp.getParentFile().exists()){
                temp.getParentFile().mkdirs();
            }
            // 写入文件
            temp.createNewFile();
            file.transferTo(temp);
        }
        return fileName;
    }
    @Override
    public void downloadFile(FileRequestModel model, HttpServletResponse response) throws Exception {
        String fileName = model.getFileName();
        String filePath = "";
        String[] args = fileName.split("\\.");
        String contentType = "";
        switch (model.getFileType()){
            case 0:
                filePath = PathUtil.getFilePath()+File.separator+fileName;
                switch (args[args.length - 1]) {
                    case "pdf": contentType = "application/pdf";break;
                    case "xlsl": contentType = "application/vnd.ms-excel";break;
                    case "pptx": contentType = "application/vnd.ms-powerpoint";break;
                    case "vsdx": contentType = "application/vnd.visio";break;
                    case "docx": contentType = "application/msword";break;
                    case "xml": contentType = "application/xml";break;
                    case "txt": contentType = "text/plain; charset=utf-8";break;
                }
                break;
            case 1:
                filePath = PathUtil.getImgPath()+File.separator+fileName;
                if(!args[args.length-1].equals("bmp")) {
                    contentType = "image/jpeg;charset=utf-8";
                } else{
                    contentType = "image/bmp;charset=utf-8";
                }
                break;
            default: break;
        }
        logger.info("下载地址："+filePath);
        FileInputStream input;
        OutputStream output = null;
        File f = null;
        // 读取文件，如不存在抛出异常
        try {
            f = new File(filePath);
            input = new FileInputStream(f);

        } catch (FileNotFoundException e) {
            throw new TestException(ExceptionEnum.FILE_NOTFOUND);
        }
        // 设置响应头
        response.setContentType(contentType);
        response.setHeader("Content-length", String.valueOf(f.length()));
        // 输出流
        try {
            output = response.getOutputStream();
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = input.read(b)) != -1) {
                output.write(b, 0, length);
            }
        } finally {
            input.close();
        }
    }
}
