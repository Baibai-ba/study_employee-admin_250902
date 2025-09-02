package com.hongbin.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BusinessException extends RuntimeException{
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}



