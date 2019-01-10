package com.drelang.smartlock.component;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
  *  认证失败处理器
  * Created by Drelang on 2019/01/10
  */
public class GoAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setStatus(400);
        // 拼凑 Json 字符串
        String body = "{" +
                "\"code\": 400," +
                "\"message\": \"登录失败: " + e.getMessage() + "\"" +
                "}";
        httpServletResponse.getWriter().print(body);
        httpServletResponse.getWriter().flush();
    }
}
