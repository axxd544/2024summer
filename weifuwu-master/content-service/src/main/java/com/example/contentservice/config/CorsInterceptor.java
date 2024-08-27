package com.example.contentservice.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CorsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String origin = request.getHeader("Origin");

        // 如果没有 Origin 头，则不设置 CORS 头
        if (origin != null) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            // 对于没有 Origin 的请求，你可以选择允许所有来源
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        // 打印日志信息
        System.out.println("Intercepted request to URL: " + request.getRequestURL());
        System.out.println("Request method: " + request.getMethod());
        System.out.println("Request origin: " + request.getHeader("Origin"));

        // 设置 CORS 头
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x_requested_with,x-requested-with,Authorization,Content-Type,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        return true;
    }

}
