package com.example.restaurant.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("username")!=null){
            return  true;
        }else {
            request.getSession().setAttribute("msg","未登录");
            response.sendRedirect("/login");
            return false;
        }
    }
}
