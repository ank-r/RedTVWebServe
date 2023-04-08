package com.redtv.redtvwebserve.sqlTest;

import com.redtv.redtvwebserve.dao.CategoryDao;
import com.redtv.redtvwebserve.entity.CategoryEntity;
import com.redtv.redtvwebserve.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryTest
 * @Description
 * @Author admin
 * @Time 2023/2/12 14:43
 * @Version 1.0
 */
@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryDao categoryDao;


    @Test
    public void test1(){
        Map<String, CategoryEntity> map = categoryService.getAllCategoryTree();




        for (Map.Entry<String, CategoryEntity> entry : map.entrySet()) {

            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }



    }



    @Test
    void test2(){

        Map<String, Object> map = new HashMap<>();
        map.put("type", 1);

        List<CategoryEntity>list = categoryDao.selectByMap(map);

        for (CategoryEntity category : list){
            System.out.println(category);
        }
    }
}
