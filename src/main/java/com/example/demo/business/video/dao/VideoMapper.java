package com.example.demo.business.video.dao;

import com.example.demo.business.video.pojo.Video;
import com.example.demo.business.video.pojo.VideoExample;
import java.util.List;

import com.example.demo.business.video.pojo.VideoQueryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface VideoMapper {
    int countByExample(VideoExample example);

    int deleteByExample(VideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByExample(VideoExample example);

    Video selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExample(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Video> listVideosByOr(VideoQueryModel model); // 按或分页查询视频信息
    List<Video> listVideosByAnd(VideoQueryModel model); // 按与分页查询视频信息
}