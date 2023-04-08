package com.redtv.redtvwebserve.vo;

import com.redtv.redtvwebserve.enums.ReturnCodeEnum;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ResponseDetails
 * @Description    这里是对返回数据的统一包装
 * @Author admin
 * @Time 2023/2/7 11:25
 * @Version 1.0
 */
public class ResponseDetails extends HashMap<String,Object> {
    private ResponseDetails(){
        put("status", ReturnCodeEnum.SUCCESS.getCode());
        put("message", ReturnCodeEnum.SUCCESS.getMsg());
        put("timestamp", LocalDateTime.now());
    }


    public static ResponseDetails ok(){
        return new ResponseDetails();
    }

    public static ResponseDetails error(String msg){
        ResponseDetails responseDetails = new ResponseDetails();
        responseDetails.put("status",ReturnCodeEnum.SYSTEM_ERROR.getCode() );
        responseDetails.put("message", msg);
        return responseDetails;
    }
    public static ResponseDetails error(int code){
        ResponseDetails responseDetails = new ResponseDetails();
        responseDetails.put("status",code);
        return responseDetails;
    }
    public static ResponseDetails error(int code,String msg){
        ResponseDetails responseDetails = new ResponseDetails();
        responseDetails.put("status",code );
        responseDetails.put("message", msg);
        return responseDetails;
    }

    public static ResponseDetails ok(Integer code, String msg){
        ResponseDetails responseDetails = new ResponseDetails();
        responseDetails.put("status", code);
        responseDetails.put("message", msg);

        return responseDetails;
    }

    public static ResponseDetails ok(String msg) {
        ResponseDetails r = new ResponseDetails();
        r.put("message", msg);
        return r;
    }

    public static ResponseDetails ok(ReturnCodeEnum codeEnum) {
        ResponseDetails r = new ResponseDetails();
        r.put("status", codeEnum.getCode());
        r.put("message", codeEnum.getMsg());
        return r;
    }

    public static ResponseDetails ok(Map<String, Object> map) {
        ResponseDetails r = new ResponseDetails();
        r.putAll(map);
        return r;
    }

    public static ResponseDetails ok(List<?> data) {
        ResponseDetails r = new ResponseDetails();
        r.put("data", data);
        return r;
    }

    @Override
    public ResponseDetails put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public ResponseDetails data( Object value) {
        super.put("data", value);
        return this;
    }



}
