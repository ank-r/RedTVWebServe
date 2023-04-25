package com.redtv.redtvwebserve.dto;

import lombok.Data;

/**
 * @ClassName ExamineVideoDto
 * @Description
 * @Author admin
 * @Time 2023/4/20 21:49
 * @Version 1.0
 */
@Data
public class ExamineVideoDto {
    private Integer videoId;
    private boolean result;
    private String message;
}
