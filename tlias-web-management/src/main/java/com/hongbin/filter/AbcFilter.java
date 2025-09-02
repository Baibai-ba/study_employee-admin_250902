package com.hongbin.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;


//@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("in AbcFilter doFilter, 放行前");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("in AbcFilter doFilter, 放行后");
    }
}
