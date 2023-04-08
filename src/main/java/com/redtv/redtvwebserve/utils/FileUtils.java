package com.redtv.redtvwebserve.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName FileUtils
 * @Description
 * @Author admin
 * @Time 2023/2/10 22:43
 * @Version 1.0
 */
public class FileUtils {


    /**
     * 文件类型
     */

    /**
     * 视频文件
     */
    public final static int VIDEO = 0;
    /**
     * 图片
     */
    public final static int PHOTO = 1;
    /**
     * 文章内容
     */
    public final static int ARTICLE = 2;

    /**
     * 暂时不支持的文件
     */
    public final static int NOT_SUPPORT = 3;
    /**
     * 用户头像
     */
    public final static int AVATAR = 3;
    /**
     * 用户顶部图片
     */
    public final static int TOP_IMAGE = 4;
    /**
     * 视频封面
     */
    public final static  int VIDEO_PHOTO = 5;

    /**
     * 常见的视频，图片的格式，方便检查
     * public static final String[] videoType 的写法不是很好，没有封装性
     */
    public final static  String[] videoType = {
            ".MP4", ".MKV", ".OGM", ".WMV", ".MPG", ".WEBM", ".OGV", ".MOV", ".ASX", ".MPEG", "M4V", ".AVI", ".FLV"
    };

    public final static  String[] photoType = {
            ".JPEG", ".JPG", ".GIF", ".PNG", ".TIFF", ".PJP", ".JFIF", ".SVG", ".BMP", ".SVGZ", ".WEBP", ".ICO"
            , ".XBM", ".DIB", ".TIF", ".PJEPG", ".AVIF"

    };

    /**
     * 获取文件后缀名,并且都是大写
     */
    public static String getFileSuffix(String filename) {
        int number = filename.lastIndexOf(".");
        if (number <= 0) {
            return "";
        }
        return filename.substring(number).toUpperCase();
    }

    /**
     * 检查文件类型，
     */
    public static int checkFileType(String suffix) {
        for (String v : videoType) {
            if (v.equals(suffix)) {
                return FileUtils.VIDEO;
            }
        }
        for (String p : photoType) {
            if (p.equals(suffix)) {
                return FileUtils.PHOTO;
            }
        }
        return FileUtils.NOT_SUPPORT;
    }

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public final static String ROOT = "file";

    public static String filePath() {
        return ROOT + "/" + DATE_FORMAT.format(new Date());
    }

    public static String fileUrl(String fileName){

        return "/api/get/file/" + DATE_FORMAT.format(new Date()) +"/" +fileName;
    }



    public static String newFileName(String suffix){
        return UUID.randomUUID().toString().replace("-", "")+suffix;
    }



}
