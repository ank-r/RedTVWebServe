package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.dto.DanmakuDto;
import com.redtv.redtvwebserve.enums.ReturnCodeEnum;
import com.redtv.redtvwebserve.service.DanmakuService;
import com.redtv.redtvwebserve.utils.DPlayerConstants;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DanmakuController
 * @Description
 * @Author admin
 * @Time 2023/2/11 15:05
 * @Version 1.0
 */
@RestController
public class DanmakuController {

    private final DanmakuService danmakuService;

    public DanmakuController(DanmakuService danmakuService) {
        this.danmakuService = danmakuService;
    }

    @PostMapping("/danmaku/v3")
    public ResponseDetails postDanmaku(@RequestBody DanmakuDto danmakuDto,
                                       HttpServletRequest request){

        ReturnCodeEnum codeEnum = danmakuService.saveDanmaku(danmakuDto,request);
        System.out.println("****收到一条弹幕"+danmakuDto);
        if (codeEnum == ReturnCodeEnum.NO_LOGIN){
            return ResponseDetails.error(codeEnum.getCode())
                    .put("code", DPlayerConstants.DPLAYER_FAILL_CODE);
        }

        return ResponseDetails.ok(codeEnum)
                .put("code", DPlayerConstants.DPLAYER_SUCCESS_CODE);
    }

    @GetMapping("/danmaku/v3")
    public ResponseDetails getDanmaku(@RequestParam("id") Long id,
                                        @RequestParam("max") Integer max
                                        ){


        System.out.println("获取弹幕列表******");
        List<Object> danmakuEntityList = danmakuService.danmakuList(id, max);



        return ResponseDetails.ok().put("data", danmakuEntityList)
                .put("code", DPlayerConstants.DPLAYER_SUCCESS_CODE);

    }



}
