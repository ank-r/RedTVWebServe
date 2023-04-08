package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.exception.FileNotFoundException;
import com.redtv.redtvwebserve.repository.FileRepository;
import com.redtv.redtvwebserve.utils.FileUtils;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.Map;

/**
 * @ClassName FileController
 * @Description   文件的上传下载 视频 图片
 * @Author admin
 * @Time 2023/2/10 11:54
 * @Version 1.0
 */

@RestController
public class FileController {

    private final FileRepository fileRepository;

    @Autowired
    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping(value = "/upload/video")
    public ResponseDetails uploadVideo(@RequestParam(value = "file[]") MultipartFile[] files,
                                       HttpServletRequest request){
        try {
            Map<String,Object>  data = fileRepository.saveFile(files,FileUtils.VIDEO);
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
            return ResponseDetails.ok().put("data", data);

        }catch (FileNotFoundException fileNotFoundException){
            return   ResponseDetails.error(fileNotFoundException.getMessage());

        }catch (Exception e){
            return ResponseDetails.error(e.getMessage());
        }
    }

    @PostMapping(value = "/upload/image")
    public ResponseDetails uploadImage(@RequestParam(value = "file") MultipartFile file,
                                       HttpServletRequest request){
        MultipartFile[] files = new MultipartFile[1];
        files[0]=file;

        try {
            Map<String,Object>  data = fileRepository.saveFile(files,FileUtils.PHOTO);
            return ResponseDetails.ok().put("data", data);
        }catch (FileNotFoundException fileNotFoundException){
          return   ResponseDetails.error(fileNotFoundException.getMessage());

        }catch (Exception e){
            return ResponseDetails.error(e.getMessage());
        }

    }

    /**
     * 获取文件
     * @param date
     * @param fileName
     * @param request
     * @return
     */
    @GetMapping("/get/file/{date}/{fileName}")
    public ResponseEntity getFile(@PathVariable String date, @PathVariable String fileName,
    HttpServletRequest request){
        try {
            Path path = fileRepository.loadFile(date+"/"+fileName);
            Resource resource = new UrlResource(path.toUri());
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, contentType);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(httpHeaders)
                    .body(resource);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        }

    }

}
