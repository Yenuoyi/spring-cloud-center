package com.yb.api.center.common.config.spring.security;

import com.alibaba.fastjson.JSONObject;
import com.yb.api.center.common.WrapMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * 认证失败返回结果处理器
 *
 * @author yebing
 */
@Component("authenticationFailureHandler")
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    private Logger logger = Logger.getAnonymousLogger();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        logger.info("登录失败！");
        PrintWriter out = httpServletResponse.getWriter();
        String jsonString = JSONObject.toJSONString(WrapMapper.error().result("账号或密码错误或账号已被禁用！"));
        out.write(jsonString);
        out.flush();
        out.close();
    }
}
