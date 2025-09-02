package com.hongbin.exception;


import com.hongbin.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }

}
