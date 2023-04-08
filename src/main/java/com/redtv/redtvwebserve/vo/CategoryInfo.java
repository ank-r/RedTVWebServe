package com.redtv.redtvwebserve.vo;

import com.redtv.redtvwebserve.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryInfo
 * @Description
 * @Author admin
 * @Time 2023/2/8 14:26
 * @Version 1.0
 */
public class CategoryInfo extends CategoryEntity {


    public static CategoryInfo getTest(){
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setId(12);
        categoryInfo.setName("电视剧");
        categoryInfo.setType(1);
        categoryInfo.setFatherId(0);
        categoryInfo.setDescribes("测试的一个分类而已");
        categoryInfo.setIcon("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        categoryInfo.setSort(2);
        categoryInfo.setCreateTime(System.currentTimeMillis());
        List<CategoryInfo> list = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++){
            list.add(categoryInfo);
        }

        return categoryInfo;
    }
}
