package com.hongbin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code; //响应码， 1 成功， -1 失败
    private String msg; // 响应信息，描述自负床
    private Object data; //返回的数据

    //增删改 响应成功
    public static Result success(){
        return new Result(1, "success", null);
    }

    //查询 响应成功
    public static Result success(Object object){
        return new Result(1, "success", object);
    }

    // 失败响应
    public static Result error(String msg){
        return new Result(0, msg, null);
    }

    public static Result error(String msg, Object object){
        return new Result(0, msg, object);
    }


    public static Result error(Integer code, String msg){
        return new Result(code, msg, null);
    }


}
