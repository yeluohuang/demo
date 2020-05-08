package com.example.demo.business.video.service.serviceImpl;

import com.example.demo.business.test.service.FileService;
import com.example.demo.business.video.dao.VideoMapper;
import com.example.demo.business.video.pojo.Video;
import com.example.demo.business.video.pojo.VideoMeta;
import com.example.demo.business.video.pojo.VideoQueryModel;
import com.example.demo.business.video.pojo.VideoUpdateModel;
import com.example.demo.business.video.service.VideoService;
import com.example.demo.util.PathUtil;
import com.example.demo.util.UUIDUtil;
import com.example.demo.util.VideoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**视频业务逻辑实现类
 * @author zhushj3
 * @date 2020/05/07
 */
@Service
public class VideoServiceImpl implements VideoService {
    private static Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);
    @Autowired
    private FileService fileService;
    @Autowired
    private VideoMapper videoMapper;
    @Override
    public boolean saveVideoInfo(Video video, MultipartFile file) throws Exception {
        // 保存文件至文件目录
        String fileName = fileService.uploadFile(file);
        // 解析并获取视频元数据信息、缩略图等
        File localFile = new File(PathUtil.getFilePath()+File.separator+fileName);
        VideoMeta  meta = VideoUtils.getVideoMeta(localFile,10);
        // 保存缩略图至本地
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) meta.getThumbnailImage();
        String thumbName = UUIDUtil.getUUID()+".jpg";
        String imgPath = PathUtil.getImgPath()+File.separator+thumbName;
        File temp = new File(imgPath);
        if(!temp.exists()){
            if(!temp.getParentFile().exists()){
                temp.getParentFile().mkdirs();
            }
            // 写入文件
            temp.createNewFile();
        }
        FileOutputStream fileOutputStream =new FileOutputStream(temp);
        fileOutputStream.write(outputStream.toByteArray());
        // 记录视频和缩略图的名称
        video.setMimeType(meta.getFileType());
        video.setDuration((int) meta.getDuration());  // 单位为s
        video.setPicUrl(thumbName);
        video.setUrl(fileName);
        video.setCreateTime(new Date());
        // 将视频相关的信息保存至数据库
        videoMapper.insertSelective(video);
        return true;
    }

    /**
     * 模糊搜索视频内容
     * @param model
     * @return
     */
    @Override
    public List<Video> getVideosByCondition(VideoQueryModel model) {
        return videoMapper.listVideosByOr(model);
    }

    /**
     * 播放/下载视频
     * @param request
     * @param response
     */
    @Override
    public void downloadFile(String url,HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 切分resourceId,区别是图片还是视频
        String[] temp = url.split("&");
        //从前端获取需要下载的文件ID
        String filename = temp[1];
        String filePath = "";
        if(temp[0].equals("img")) {
            filePath = PathUtil.getImgPath()+ File.separator+filename;
        } else {
            filePath = PathUtil.getFilePath()+filePath+File.separator+filename;
        }
        logger.info("当前请求的资源地址为："+filePath);
        // 开始下载
        File file1 =new File(filePath);
        RandomAccessFile randomFile = new RandomAccessFile(file1, "r");//只读模式
        long contentLength = randomFile.length();
        String range = request.getHeader("Range");
        int start = 0, end = 0;
        if(range != null && range.startsWith("bytes=")){
            String[] values = range.split("=")[1].split("-");
            start = Integer.parseInt(values[0]);
            if(values.length > 1){
                end = Integer.parseInt(values[1]);
            }
        }
        int requestSize = 0;
        if(end != 0 && end > start){
            requestSize = end - start + 1;
        } else {
            requestSize = Integer.MAX_VALUE;
        }

        byte[] buffer = new byte[4096];
        if(temp[0].equals("img")){
            response.setContentType("image/jpeg;charset=utf-8");
        } else {
            response.setContentType("audio/mp4;charset=utf-8");
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("ETag", filename);
            response.setHeader("Last-Modified", new Date().toString());
        }
        //第一次请求只返回content length来让客户端请求多次实际数据
        if(range == null){
            response.setHeader("Content-length", contentLength + "");
        }else{
            //以后的多次以断点续传的方式来返回视频数据
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);//206
            long requestStart = 0, requestEnd = 0;
            String[] ranges = range.split("=");
            if(ranges.length > 1){
                String[] rangeDatas = ranges[1].split("-");
                requestStart = Integer.parseInt(rangeDatas[0]);
                if(rangeDatas.length > 1){
                    requestEnd = Integer.parseInt(rangeDatas[1]);
                }
            }
            long length = 0;
            if(requestEnd > 0){
                length = requestEnd - requestStart + 1;
                response.setHeader("Content-length", "" + length);
                response.setHeader("Content-Range", "bytes " + requestStart + "-" + requestEnd + "/" + contentLength);
            }else{
                length = contentLength - requestStart;
                response.setHeader("Content-length", "" + length);
                response.setHeader("Content-Range", "bytes "+ requestStart + "-" + (contentLength - 1) + "/" + contentLength);
            }
        }
        ServletOutputStream out = response.getOutputStream();
        int needSize = requestSize;
        randomFile.seek(start);
        while(needSize > 0){
            int len = randomFile.read(buffer);
            if(needSize < buffer.length){
                out.write(buffer, 0, needSize);
            } else {
                out.write(buffer, 0, len);
                if(len < buffer.length){
                    break;
                }
            }
            needSize -= buffer.length;
        }
        randomFile.close();
        out.close();
    }

    /**
     * 更新视频的相关信息
     * @param model
     * @return
     */
    @Override
    public boolean updateVideoInfo(VideoUpdateModel model) {
        Video video = new Video();
        video.setId(model.getId());
        video.setTitle(model.getTitle());
        video.setDes(model.getDes());
        video.setTags(model.getTags());
        if(videoMapper.updateByPrimaryKeySelective(video) >0) {
            return true;
        } else {
            return false;
        }
    }
}
