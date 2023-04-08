package com.redtv.redtvwebserve.service;

import com.redtv.redtvwebserve.entity.CategoryEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryService
 * @Description
 * @Author admin
 * @Time 2023/2/12 14:17
 * @Version 1.0
 */
public interface CategoryService {

    /**
     * 获取所有的分类， 返回一个 map树
     * @return
     */
    Map<String, CategoryEntity> getAllCategoryTree();


    /**
     * 获取所有的分类， 返回一个 List 结构  和getAllCategory 作用类似
     * @return
     */
    List<CategoryEntity> getAllCategoryList();

    /**
     * 获取一个父分类的子分类列表，或者是获取一个父分类列表
     *
     * @param params
     * @return
     */
    List<CategoryEntity> getCategoryList(HashMap<String,Object> params);


    /**
     * 根据id获得一个分类信息
     * @param id
     * @return
     */
    CategoryEntity getCategoryById(int id);

}
