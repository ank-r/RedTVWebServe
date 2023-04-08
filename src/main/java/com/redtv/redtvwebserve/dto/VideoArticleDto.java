package com.redtv.redtvwebserve.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName VideoArticleDto
 * @Description
 * @Author admin
 * @Time 2023/2/12 18:49
 * @Version 1.0
 */
@Data
public class VideoArticleDto {
    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "封面图不能为空")
    private String imgUrl;

    @NotNull(message = "视频文件必须上传")
    private String  articleUrl;

    private String describe;

    @NotNull(message = "分区不能为空")
    private Integer category;


}
