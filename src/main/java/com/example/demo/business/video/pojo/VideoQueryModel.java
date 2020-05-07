package com.example.demo.business.video.pojo;

import javax.validation.constraints.NotNull;

/**
 * 根据条件分页查询视频信息
 * @author zhushj3
 * @date 2020/05/07
 */
public class VideoQueryModel {
    private String content;  // 模糊搜索使用参数
    private String title;
    private String author;
    private String des;
    private String tags;
    @NotNull(groups = {NullParam.class})
    private Integer num; // 每页的数量
    @NotNull(groups = {NullParam.class})
    private Integer page;
    private int start; // 分页起始位置

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStart() {
        return (page-1)*num;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "VideoQueryModel{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", des='" + des + '\'' +
                ", tags='" + tags + '\'' +
                ", num=" + num +
                ", page=" + page +
                ", start=" + start +
                '}';
    }

    public interface NullParam{};
}
