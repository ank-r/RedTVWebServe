package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.entity.CategoryEntity;
import com.redtv.redtvwebserve.service.CategoryService;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryController
 * @Description
 * @Author admin
 * @Time 2023/2/8 15:43
 * @Version 1.0
 */

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 返回是树形结构
     * @return
     */
    @GetMapping("/category/tree")
    public ResponseDetails getCategoryTree(){

        Map<String, CategoryEntity> re = categoryService.getAllCategoryTree();

        return ResponseDetails.ok().data(re);
    }

    /**
     * 返回是列表
     * @return
     */
    @GetMapping("/category/list")
    public ResponseDetails getCategoryList(){

        List<CategoryEntity> re = categoryService.getAllCategoryList();

        return ResponseDetails.ok().data(re);
    }
}
