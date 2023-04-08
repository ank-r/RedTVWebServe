package com.redtv.redtvwebserve.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DPlayerConstants
 * @Description
 * @Author admin
 * https://blog.csdn.net/ZPeng_CSDN/article/details/106721975
 * @Time 2023/2/11 15:37
 * @Version 1.0
 */
public class DPlayerConstants {
    public static final int DPLAYER_SUCCESS_CODE = 0;
    public static final int DPLAYER_FAILL_CODE = 1;

    public static final String DPLAYER_BARRAGE_ID = "0";
    public static final String DPLAYER_BARRAGE_AUTHOR = "DPlayer";
    public static final String DPLAYER_BARRAGE_TIME = "0";
    public static final String DPLAYER_BARRAGE_TEXT = "";
    public static final String DPLAYER_BARRAGE_COLOR = "16777215";
    public static final String DPLAYER_BARRAGE_TYPE = "0";

    public static final List<String> barrage_init(List<String> data){
        data = new ArrayList<String>();
        data.add(DPLAYER_BARRAGE_TIME);
        data.add(DPLAYER_BARRAGE_TYPE);
        data.add(DPLAYER_BARRAGE_COLOR);
        data.add(DPLAYER_BARRAGE_AUTHOR);
        data.add(DPLAYER_BARRAGE_TEXT);
        return data;
    }
}
