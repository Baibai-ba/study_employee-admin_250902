package com.hongbin.controller;


import com.hongbin.pojo.Emp;
import com.hongbin.pojo.Result;
import com.hongbin.service.EmpService;
import com.hongbin.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    public EmpService empService;

    @PostMapping("/login")
    public Result loginCheck(@RequestBody Emp emp) {
        log.info("登陆的员工信息：{}", emp);
        Emp tempEmp = empService.loginCheck(emp);
//        log.info("数量？id？ " + tempEmp);
        log.info("员工登陆信息：{}", tempEmp);

        //登陆成功，生成令牌，下发令牌
        if (tempEmp != null) {
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("id", tempEmp.getId());
            claims.put("name", tempEmp.getName());
            claims.put("username", tempEmp.getUsername());
//            claims.put("password", tempEmp.getPassword());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        //登陆失败，返回错误信息
        return Result.error("用户名或密码错误");

//        return tempEmp != null?Result.success():Result.error("用户名或密码错误");



    }

}
