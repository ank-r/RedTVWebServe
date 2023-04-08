package com.redtv.redtvwebserve.exception;

/**
 * @ClassName MessageException
 * @Description
 * @Author admin
 * @Time 2023/2/22 19:57
 * @Version 1.0
 */
public class MessageException extends RuntimeException{

    public MessageException(){
        super();
    }
    public MessageException(String msg){
        super(msg);
    }
}
