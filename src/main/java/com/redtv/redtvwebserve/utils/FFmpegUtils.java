package com.redtv.redtvwebserve.utils;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @ClassName FFmpegUtils
 * @Description
 * @Author admin
 * @Time 2023/2/12 18:10
 * @Version 1.0
 */
@Slf4j
public class FFmpegUtils {
    /**
     * 生成图片的后缀
     */
    private final static String SUFFIX = ".JPG";

    /**
     * 随机获取视频截图
     * @param videFile 视频文件
     * @param count 输出截图数量
     * @return 截图的访问地址的列表
     * */
    public static List<String> randomGrabberFFmpegImage(File videFile, int count) {
        FFmpegFrameGrabber grabber = null;
        log.info("现在开始生成截图");
        String path = FileUtils.filePath();
        try {
            List<String> images = new ArrayList<>(count);
            grabber = new FFmpegFrameGrabber(videFile);
            grabber.start();
            // 获取视频总帧数
            // int lengthInVideoFrames = grabber.getLengthInVideoFrames();
            // 获取视频时长， / 1000000 将单位转换为秒
            long delayedTime = grabber.getLengthInTime() / 1000000;

            Random random = SecureRandom.getInstanceStrong();
            int[] timeList = new int[count];
            for (int i = 0; i < count; i++) {
                timeList[i] = random.nextInt((int)delayedTime - 1) + 1;
            }
            // 让截图按时间线排列
            Arrays.sort(timeList);
            for (int i : timeList) {
                // 跳转到响应时间
                grabber.setTimestamp(i * 1000000L);
                Frame f = grabber.grabImage();
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage bi = converter.getBufferedImage(f);
                String imageName = FileUtils.newFileName(SUFFIX);
                File out = Paths.get(path, imageName).toFile();
                ImageIO.write(bi, "jpg", out);
                String imgUrl = FileUtils.fileUrl(imageName);
                images.add(imgUrl);
                log.info("生成一张封面{}", imgUrl);
            }
            return images;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (grabber != null) {
                    grabber.stop();
                    grabber.release();
                }
            } catch (FFmpegFrameGrabber.Exception e) {
                log.error("getVideoInfo grabber.release failed 获取文件信息失败：{}", e.getMessage());
            }
        }
    }


}
