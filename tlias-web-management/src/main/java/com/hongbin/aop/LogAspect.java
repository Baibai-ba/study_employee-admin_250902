package com.hongbin.aop;

import com.alibaba.fastjson.JSONObject;
import com.hongbin.mapper.OperateLogMapper;
import com.hongbin.pojo.OperateLog;
import com.hongbin.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;


    @Around("@annotation(com.hongbin.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 操作人的ID - 当前登陆员工ID
        // 获取请求头中的jwt令牌，解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        String methodParams = Arrays.toString(joinPoint.getArgs());

        Long beginTime = System.currentTimeMillis();
        // 调用原始目标方法
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        //操作方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime = endTime - beginTime;




        // 记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insertOperateLog(operateLog);

        log.info("AOP记录日志操作：{}", operateLog);


        return result;
    }



}
