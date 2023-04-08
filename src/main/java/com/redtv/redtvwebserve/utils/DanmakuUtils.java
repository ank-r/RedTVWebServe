package com.redtv.redtvwebserve.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName DanmakuUtils
 * @Description   弹幕需要有特定的格式，返回，他才能正确显示
 * @Author admin
 * @Time 2023/2/12 0:43
 * @Version 1.0
 */
public class DanmakuUtils {
    public static List<Object> createDanmaku(Double time,
                                             Integer type,
                                             Long colorNumber,
                                             String colorHex,
                                             String text) {
        List<Object> list = new LinkedList<>();
        list.add(time);
        list.add(type);
        list.add(colorNumber);
        list.add(colorHex);
        list.add(text);
        return list;
    }

}
