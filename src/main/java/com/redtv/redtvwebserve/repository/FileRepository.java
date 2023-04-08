package com.redtv.redtvwebserve.repository;

import com.redtv.redtvwebserve.exception.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Map;

/**
 * @ClassName FileRepository
 * @Description
 * @Author admin
 * @Time 2023/2/10 22:42
 * @Version 1.0
 */
public interface FileRepository {

    /**
     * 存储各种文件, 暂时仅仅支持 视频或者 图片文件
     * @param files
     */

     Map<String,Object> saveFile(MultipartFile[] files, int type) throws FileNotFoundException;


    /**
     * 根据给的路径加载文件， 如无责抛出错误
     * @param path
     * @return
     * @throws FileNotFoundException
     */
     Path loadFile(String path) throws FileNotFoundException ;

}
