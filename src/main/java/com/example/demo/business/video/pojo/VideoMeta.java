package com.example.demo.business.video.pojo;

import java.io.OutputStream;

/**
 * 视频元数据
 * @author zhushj3
 * @date 2020/05/07
 */
public class VideoMeta {
    private String fileName;
    private String fileType;
    private long duration;
    private int height;
    private int width;
    private long length;
    private String format;
    private OutputStream thumbnailImage;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public OutputStream getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(OutputStream thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }
}
