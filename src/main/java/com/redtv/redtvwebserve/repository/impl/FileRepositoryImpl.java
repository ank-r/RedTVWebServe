package com.redtv.redtvwebserve.repository.impl;

import com.redtv.redtvwebserve.exception.FileNotFoundException;
import com.redtv.redtvwebserve.repository.FileRepository;
import com.redtv.redtvwebserve.utils.FFmpegUtils;
import com.redtv.redtvwebserve.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FileRepositoryImpl
 * @Description
 * @Author admin
 * @Time 2023/2/10 22:47
 * @Version 1.0
 */
@Repository
@Slf4j
public class FileRepositoryImpl implements FileRepository {
    private final Path location;

    @Autowired
    public FileRepositoryImpl() throws IOException {
        this.location = Paths.get(FileUtils.ROOT);

        if (Files.notExists(this.location)) {
            Files.createDirectories(this.location);
        }
    }

    @Override
    public Map<String,Object> saveFile(MultipartFile[] files, int type)  throws FileNotFoundException{

        Map<String ,Object>  re = new HashMap<>(8);
        for (MultipartFile file : files ){
            //存储的路径
            String path = FileUtils.filePath();
            //文件后缀
            String suffix = FileUtils.getFileSuffix(file.getOriginalFilename());
            int fileType = FileUtils.checkFileType(suffix);
            if (fileType == FileUtils.NOT_SUPPORT){
                throw  new FileNotFoundException("文件格式错误！");
            }
            if (fileType != type){
                throw  new FileNotFoundException("请选择正确格式的文件！");
            }
            String newFileName = FileUtils.newFileName(suffix);
            System.out.println(newFileName);
            File dest = new File(path);
            if (!dest.exists() && !dest.mkdirs()) {
                log.info("出现了一些问题");
                continue;
            }
            try {
                // 把文件存储到对应目录
                Files.copy(file.getInputStream(), Paths.get(path,newFileName));
            }catch (Exception e){
                log.info("文件上传失败，上传文件的用户ID：， 上传的文件名： {}",  file.getOriginalFilename());
            }
            // 获取文件的访问路径
            String fileUrl = FileUtils.fileUrl(newFileName);
            System.out.println(fileUrl);

            // fileUrl  视频或者图片的访问路径
            re.put("fileUrl", fileUrl);
            if(fileType == FileUtils.VIDEO){
                //视频文件生成 截图封面
                List<String > imgs  = FFmpegUtils.randomGrabberFFmpegImage(Paths.get(path, newFileName).toFile(), 6);
                log.info("生成截图{}", imgs.size());
                re.put("imgs", imgs);
            }

        }
        return re;
    }

    @Override
    public Path loadFile(String path) throws FileNotFoundException {

        Path filePath = location.resolve(path);
        if(Files.notExists(filePath)){
            throw new FileNotFoundException("Cannot load file! File " + filePath + " not exists!");

        }
        return filePath;
    }
}
