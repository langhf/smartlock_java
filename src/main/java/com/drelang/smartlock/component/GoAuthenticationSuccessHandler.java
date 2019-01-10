package com.drelang.smartlock.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type:", "application/json;charset=utf-8");
        String body = "{" +
                "\"code\": 200," +
                "\"message\": \"登录成功\"" +
                "}";
        httpServletResponse.getWriter().print(body);
        httpServletResponse.getWriter().flush();
    }
}
