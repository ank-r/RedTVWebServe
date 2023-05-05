package com.redtv.redtvwebserve.dto;

import lombok.Data;

@Data
public class CategoryDto {

    /**
     * 分类Id
     */
    private Integer id;

    /**
     * 分区名
     */
    private String name;

    Integer type;



    /**
     * 父级分区
     */
    private Integer fatherId;

    /**
     * 介绍
     */
    private String describes;
}
