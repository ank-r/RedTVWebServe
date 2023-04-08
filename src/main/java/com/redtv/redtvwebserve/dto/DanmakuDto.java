package com.redtv.redtvwebserve.dto;

import lombok.Data;

/**
 * @ClassName DanmakuDeatils
 * @Description
 * @Author admin
 * @Time 2023/2/11 15:06
 * @Version 1.0
 */
@Data
public class DanmakuDto {
    /**
     * 弹幕作者
     * */
    private String author;

    /**
     * 弹幕颜色
     * */
    private Long color;

    /**
     * 弹幕所属视频文件的 ID
     * */
    private Long id;

    /**
     * 正文
     * */
    private String text;

    /**
     * 时间
     * */
    private Double time;

    /**
     * TOKEN
     * */
    private String token;

    /**
     * 类型
     * */
    private Integer type;
}
