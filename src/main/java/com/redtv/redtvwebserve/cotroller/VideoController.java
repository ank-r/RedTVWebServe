package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.dto.VideoArticleDto;
import com.redtv.redtvwebserve.service.ArticleService;
import com.redtv.redtvwebserve.service.LikeService;
import com.redtv.redtvwebserve.vo.ArticleInfo;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName VideoController
 * @Description
 * @Author admin
 * @Time 2023/2/7 11:42
 * @Version 1.0
 */
@RestController
@Slf4j
public class VideoController {

    private final ArticleService articleService;
    private final LikeService likeService;

    public VideoController(ArticleService articleService, LikeService likeService) {
        this.articleService = articleService;
        this.likeService = likeService;
    }


    @GetMapping("/video/getVideoList")
    public ResponseDetails getVideoList(){

        List<ArticleInfo> videoInfoList = articleService.getVideoList();

        return ResponseDetails.ok(videoInfoList);
    }


    @GetMapping("/video/getVideoById/{id}")
    public ResponseDetails getVideoById(@PathVariable String id){
        ArticleInfo videoInfo = articleService.getVideoInfoById(Long.parseLong(id)) ;

      return ResponseDetails.ok().put("data", videoInfo);
    }

    /**
     * 搜索视频
     * @param searchWorld
     * @return
     */

    @GetMapping("/video/getSearchList")
    public ResponseDetails getSearchList(@RequestParam("searchWorld") String searchWorld){
        System.out.println("---------搜索关键词---"+searchWorld);


        List<ArticleInfo> videoInfoList = articleService.getVideoList();

        return ResponseDetails.ok(videoInfoList);
    }

    @GetMapping("/video/getCategoryList")
    public ResponseDetails getCategoryList(@RequestParam("categoryId") int categoryId){
        System.out.println("---------分类查询ID---"+categoryId);

        List<ArticleInfo> videoInfoList = articleService.getVideoList();

        return ResponseDetails.ok(videoInfoList);
    }

    @GetMapping("/video/getExamineList")
    public ResponseDetails getExamineList(){


        List<ArticleInfo> videoInfoList = articleService.getExamineList();

        return ResponseDetails.ok(videoInfoList);
    }


    @PostMapping("/video/publish")
    public ResponseDetails publishVideo(@RequestBody VideoArticleDto videoArticleDto){



        int re = articleService.publishVideo(videoArticleDto);
        if (re <= 0){
            return ResponseDetails.error("上传视频出错");
        }


        return ResponseDetails.ok("上传成功！");
    }

    @GetMapping("/video/isLike/{id}")
    public ResponseDetails isLike(@PathVariable String id){
        int re = likeService.isLike(Long.parseLong(id));

        return ResponseDetails.ok().put("isLike", re);
    }

    @GetMapping("/video/doLike/{id}")
    public ResponseDetails doLike(@PathVariable String id){

        int re = likeService.doLike(Long.parseLong(id));

        if(re == 0){
            return ResponseDetails.error("点赞失败！");
        }

        return ResponseDetails.ok();
    }

    @GetMapping("/video/unLike/{id}")
    public ResponseDetails unLike(@PathVariable String id){

        int re = likeService.unLike(Long.parseLong(id));

        if(re == 0){
            return ResponseDetails.error("点赞失败！");
        }

        return ResponseDetails.ok();
    }




}
