package com.redtv.redtvwebserve.exception;

/**
 * @ClassName FileNotFoundException
 * @Description
 * @Author admin
 * @Time 2023/2/11 10:38
 * @Version 1.0
 */
public class FileNotFoundException extends Exception{

    public FileNotFoundException(){

    }

    public FileNotFoundException(String msg){

        super(msg);
    }

    public FileNotFoundException(String path, String reason){
        super(path+(reason != null ? reason : ""));
    }


}
