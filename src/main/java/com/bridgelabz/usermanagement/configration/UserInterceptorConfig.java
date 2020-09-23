//package com.bridgelabz.usermanagement.configration;
//
//import com.bridgelabz.usermanagement.interceptor.UserInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//@Component
//public class UserInterceptorConfig extends WebMvcConfigurationSupport {
//
//    @Autowired
//    UserInterceptor userInterceptor;
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userInterceptor);
//    }
//
//    @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        super.addCorsMappings(registry);
//        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//    }
//}
