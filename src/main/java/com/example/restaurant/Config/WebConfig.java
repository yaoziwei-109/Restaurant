package com.example.restaurant.Config;

import com.example.restaurant.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new WebInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/reset","/","/login","/user/login","/register",
                        "/user/register","/forgotpassword","/user/getUser")
                //静态资源
                .excludePathPatterns("/css/**","/img/**","/js/**","/scss/**","/vendor/**","/food/**");



    }




}
