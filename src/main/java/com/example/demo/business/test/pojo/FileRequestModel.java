package com.example.demo.business.test.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 文件请求接口的请求参数类
 * @author zhushj3
 * @date 2020/04/29
 */
public class FileRequestModel {
    @NotEmpty(message = "请求参数为空或包含非法字符",groups = FileRequestModel.EmptyDefault.class)
    private String fileName;
    @NotNull(message = "请求参数为空或包含非法字符",groups = FileRequestModel.EmptyDefault.class)
    private Integer fileType;  // 0 表示文件，1表示图片
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileRequestModel{" +
                "fileName='" + fileName + '\'' +
                ", fileType=" + fileType +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }

    public interface EmptyDefault{};
}
