package com.example.demo.business.video.pojo;

import javax.validation.constraints.NotNull;

/**
 * @description 视频信息更新模型
 * @author zhushj3
 * @date 2020/05/07
 */
public class VideoUpdateModel {
    @NotNull(groups = IdNotNull.class)
    private Integer  id;  // 视频的唯一标识
    private String title;
    private String des;
    private String tags;

    @Override
    public String toString() {
        return "VideoUpdateModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", des='" + des + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public interface IdNotNull{};
}
