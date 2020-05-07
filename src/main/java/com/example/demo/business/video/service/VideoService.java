package com.example.demo.business.video.service;

import com.example.demo.business.video.pojo.Video;
import com.example.demo.business.video.pojo.VideoQueryModel;
import com.example.demo.business.video.pojo.VideoUpdateModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**视频业务逻辑接口
 * @author zhushj3
 * @date 2020/05/07
 */
public interface VideoService {
    boolean saveVideoInfo(Video video, MultipartFile file) throws Exception;
    List<Video> getVideosByCondition(VideoQueryModel model);
    void downloadFile(String url,HttpServletRequest request, HttpServletResponse response) throws Exception;
    boolean updateVideoInfo(VideoUpdateModel model);
}
