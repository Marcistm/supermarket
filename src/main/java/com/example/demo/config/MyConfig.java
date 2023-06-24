package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //请求拦截 并 转发
        //如收到/ 转发到 index.html(html后缀是thymeleaf加的)
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/list.html").setViewName("list");


        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/main.html").setViewName("dashboard");
//        registry.addViewController("/main.html").setViewName("home");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")//先所有都拦截
                .excludePathPatterns("/user/login", "/", "/index.html", //首页，即登录界面
                        "/user/register", "/register",                 //注册页
                        "/user/findPW","/user/userSendVer",               //找回密码
                        "/css/**", "/img/**", "/js/**");
    }

}
