package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.dto.CommentDto;
import com.redtv.redtvwebserve.service.CommentService;
import com.redtv.redtvwebserve.vo.CommentInfo;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CommentController
 * @Description
 * @Author admin
 * @Time 2023/2/8 15:45
 * @Version 1.0
 */
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment/get/list/{id}")
    public ResponseDetails getComment(@PathVariable String id){

        long videoId = Long.parseLong(id);
        List<CommentInfo> commentInfoList  = commentService.getCommentList(videoId);


        return ResponseDetails.ok().data(commentInfoList);
    }

    @PostMapping("/comment/send")
    public ResponseDetails sendComment(@RequestBody CommentDto commentDto){

        System.out.println("收到一条消息 ！ " + commentDto);
         int re = commentService.sendComment(commentDto);
        if (re<0){
            return ResponseDetails.error("评论失败");
        }

        return ResponseDetails.ok();
    }
}
