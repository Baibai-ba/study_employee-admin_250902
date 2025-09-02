package com.hongbin.filter;

import com.alibaba.fastjson.JSONObject;
import com.hongbin.pojo.Result;
import com.hongbin.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求的URL
        String url = request.getRequestURL().toString();
        log.info("url:{}", url);

        // 如果是login请求，则直接放行
        if(url.contains("/login")){
            log.info("登陆操作，放行");
            filterChain.doFilter(request, response);
            return;
        }

        // 获取请求头中的令牌（token),返回一个字符串
        String jwt = request.getHeader("token");

        // 判断是否存在jwt令牌，不存在则返回错误结果
        if(jwt == null){
            log.info("请求头token为空，返回未登陆信息");
//            Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            response.getWriter().write(notlogin);
            return;
        }

        // 判断令牌是否合法，认为 只要解析成功，令牌就是对的
        try{
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败，返回未登陆的信息");
            String notlogin = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            response.getWriter().write(notlogin);
            return;
        }

        // 放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
