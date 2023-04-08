package com.redtv.redtvwebserve.service;

import com.redtv.redtvwebserve.dto.DanmakuDto;
import com.redtv.redtvwebserve.enums.ReturnCodeEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DanmakuService
 * @Description
 * @Author admin
 * @Time 2023/2/11 15:41
 * @Version 1.0
 */
public interface DanmakuService {



    /**
     * 获取弹幕列表
     *
     * @return*/
    List<Object> danmakuList(Long id, Integer max);

    /**
     * 保存弹幕
     * */
    ReturnCodeEnum saveDanmaku(DanmakuDto danmakuDto, HttpServletRequest request);
}
