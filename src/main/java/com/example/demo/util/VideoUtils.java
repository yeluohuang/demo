package com.example.demo.util;


import com.example.demo.business.video.pojo.VideoMeta;
import com.example.demo.exception.ExceptionEnum;
import com.example.demo.exception.TestException;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/** 视频工具类
 * @author zhushj3
 * @date 2020/05/07
 */
public class VideoUtils {
    private static Logger logger = LoggerFactory.getLogger(VideoMeta.class);

    /**
     *获取视频的信息
     * @param file  传入的文件
     * @param mod   第几帧
     * @return  视频元数据信息：名称、格式、宽高、缩略图、时长
     */
    public static VideoMeta getVideoMeta(File file, int mod) throws Exception{
        if(file.isFile()){
            // 1、视频基础信息获取
            String filename = file.getName().substring(0, file.getName().lastIndexOf("."));
            String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1,file.getName().length());
            VideoMeta videoMeta = new VideoMeta();
            videoMeta.setFileName(filename);
            videoMeta.setFileType(fileType);
            try {
                // 2、对视频进行解析，获取元数据：宽高、时长、格式
                FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
                ff.start();
                //视频时长，单位为秒
                long duration = ff.getLengthInTime() / (1000 * 1000);
                videoMeta.setDuration(duration);
                videoMeta.setFormat(ff.getFormat());
                videoMeta.setHeight(ff.getImageHeight());
                videoMeta.setWidth(ff.getImageWidth());
                videoMeta.setLength(file.length());

                // 3、截取中间帧图片(具体依实际情况而定)
                int i = 0;
                int length = ff.getLengthInFrames();
                int middleFrame = mod / length;
                Frame frame = null;
                while (i < length) {
                    frame = ff.grabImage();
                    if ((i == middleFrame) && (frame.image != null)) {
                        break;
                    }
                    i++;
                }
                // 截取的帧图片
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage srcImage = converter.getBufferedImage(frame);
                int srcImageWidth = srcImage.getWidth();
                int srcImageHeight = srcImage.getHeight();
                // 4、对截图进行等比例缩放(缩略图)
                int width = 480;
                int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
                BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

                OutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(thumbnailImage, "jpg", outputStream);
                videoMeta.setThumbnailImage(outputStream);

                ff.stop();
            } catch (Exception e) {
                logger.error("解析视频数据异常："+e.getMessage());
                throw new TestException(ExceptionEnum.UNKNOWN);
            }
            return videoMeta;
        }else{
            return null;
        }
    }
}
