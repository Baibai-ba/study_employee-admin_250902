package com.hongbin.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hongbin.pojo.Result;
import com.hongbin.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override   //目标资源方法运行前运行，返回true，放行；返回false，不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        System.out.println("preHandle ...");

        // 获取请求的URL
        String url = request.getRequestURL().toString();
        log.info("url:{}", url);

        // 获取请求头中的令牌（token),返回一个字符串
        String jwt = request.getHeader("token");

        // 判断是否存在jwt令牌，不存在则返回错误结果
        if(jwt == null){
            log.info("请求头token为空，返回未登陆信息");
//            Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            response.getWriter().write(notlogin);
            return false;
        }

        // 判断令牌是否合法，认为 只要解析成功，令牌就是对的
        try{
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败，返回未登陆的信息");
            String notlogin = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            response.getWriter().write(notlogin);
            return false;
        }

        // 放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override  //目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("postHandle ...");

    }

    @Override  //视图渲染完成后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        System.out.println("afterCompletion ...");
    }
}
