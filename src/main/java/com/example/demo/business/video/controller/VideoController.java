package com.example.demo.business.video.controller;

import com.example.demo.business.test.pojo.TestResponse;
import com.example.demo.business.video.pojo.Video;
import com.example.demo.business.video.pojo.VideoQueryModel;
import com.example.demo.business.video.pojo.VideoUpdateModel;
import com.example.demo.business.video.service.VideoService;
import com.example.demo.exception.ExceptionEnum;
import com.example.demo.exception.TestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视频接口
 * @author zhushj3
 * @date 2020/05/07
 */
@RequestMapping("/video")
@RestController
public class VideoController {
    @Autowired private VideoService videoService;
    private static Logger logger = LoggerFactory.getLogger(VideoController.class);

    /**
     * 上传视频文件，同时也会截取视频的缩略图进行保存
     * @param file
     * @param title
     * @param des
     * @param tags
     * @param author
     * @return
     * @throws Exception
     */
    @PostMapping(value="/video_upload")
    public TestResponse uploadVideoInfo(@RequestParam("file") MultipartFile file,
                                        @RequestParam("title") String title, @RequestParam("des") String des,
                                        @RequestParam("tags") String tags, @RequestParam("author") String author) throws Exception {
        // 做一次简单的映射
        Video video = new Video();
        video.setAuthor(author);
        video.setDes(des);
        video.setTitle(title);
        video.setTags(tags);
        if(videoService.saveVideoInfo(video,file)){
            return TestResponse.ok("保存文件成功",video);
        } else {
            return TestResponse.error("保存文件失败");
        }
    }

    /**
     * 模糊搜索，支持对视频标题、内容、标签、作者进行搜索
     * @param model
     * @param result
     * @return
     */
    @PostMapping(value = "/videos_search")
    public TestResponse queryVideoInfos(@RequestBody @Validated(value = VideoQueryModel.NullParam.class) VideoQueryModel model,
                                        BindingResult result){
        if(result.hasErrors()){
            throw new TestException(ExceptionEnum.EMPTY);
        }
        return TestResponse.ok("查询视频信息成功",videoService.getVideosByCondition(model));
    }

    /**
     * 实时播放视频/下载缩略图
     * @param url 视频/缩略图资源的url
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "file_download/{url}")
    public void downloadFile(@PathVariable("url")  String url, HttpServletRequest request, HttpServletResponse response)throws Exception{
        videoService.downloadFile(url,request,response);
    }

    /**
     * 更新标签
     * @param model
     * @param result
     * @return
     */
    @PostMapping(value = "/video_update")
    public TestResponse updateTags(@RequestBody @Validated(value = VideoUpdateModel.IdNotNull.class) VideoUpdateModel model,
                                   BindingResult result){
        if (result.hasErrors()){
            throw new TestException(ExceptionEnum.EMPTY);
        }
        if(videoService.updateVideoInfo(model)) {
            return TestResponse.ok("更新标签成功");
        } else {
            return TestResponse.error("更新标签失败,不存在该视频");
        }
    }
}
