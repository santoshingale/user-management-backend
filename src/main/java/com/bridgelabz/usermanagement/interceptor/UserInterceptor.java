package com.bridgelabz.usermanagement.interceptor;

import com.bridgelabz.usermanagement.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    ILoginService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        return (!request.getRequestURL().toString().contains("home")) ? true :
                (userService.verifyJWTToken(request.getHeader("token"))) ? true : false;
    }
}
