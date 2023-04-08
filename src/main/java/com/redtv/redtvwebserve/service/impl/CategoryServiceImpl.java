package com.redtv.redtvwebserve.service.impl;

import com.redtv.redtvwebserve.dao.CategoryDao;
import com.redtv.redtvwebserve.entity.CategoryEntity;
import com.redtv.redtvwebserve.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/12 14:23
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Map<String, CategoryEntity> getAllCategoryTree() {

        // get father
        Map<String,Object> param1 = new HashMap<>(2);
        param1.put("type",1);
        List<CategoryEntity>  categoryEntitiesFather = categoryDao.selectByMap(param1);

        //get child
        Map<String,CategoryEntity> re = new HashMap<>(categoryEntitiesFather.size());
        Map<String,Object> param2 = new HashMap<>(2);
        param2.put("type",2);


        categoryEntitiesFather.forEach(category->{
            param2.put("father_id", category.getId());
            List<CategoryEntity> child = categoryDao.selectByMap(param2);
            category.setChildren(child);
            re.put(String.valueOf(category.getId()), category);
        });

        return re ;
    }

    @Override
    public List<CategoryEntity> getAllCategoryList() {


        // get father
        Map<String,Object> param1 = new HashMap<>(2);
        param1.put("type",1);
        List<CategoryEntity>  categoryEntitiesFather = categoryDao.selectByMap(param1);

        //get child
        List<CategoryEntity> re = new ArrayList<>();

        Map<String,Object> param2 = new HashMap<>(2);
        param2.put("type",2);


        categoryEntitiesFather.forEach(category->{
            param2.put("father_id", category.getId());
            List<CategoryEntity> child = categoryDao.selectByMap(param2);
            category.setChildren(child);

            re.add(category);
        });

        return re ;


    }

    @Override
    public List<CategoryEntity> getCategoryList(HashMap<String, Object> params) {

        List<CategoryEntity>  categoryEntitiesFather = categoryDao.selectByMap(params);



        return categoryEntitiesFather;
    }

    @Override
    public CategoryEntity getCategoryById(int id) {
        return null;
    }
}
