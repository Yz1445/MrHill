package com.tlp.mrhill.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器实现接口webMvcConfigurer
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    public final static String SESSION_KEY = "user";


    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/MrHill/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/login.html")
                //对登录注册接口不进行拦截
                .excludePathPatterns("/MrHill/logindemo");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("static/**").addResourceLocations("classpath:/static/");
    }
}
