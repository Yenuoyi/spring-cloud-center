package com.yb.sso.center;

import com.yb.common.center.util.HttpUtil;
import com.yb.common.center.wrap.WrapMapper;
import com.yb.common.center.wrap.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 子系统使用过滤器
 * @author yebing
 */
public abstract class SsoFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("SSO_CHECK_LOGIN_URL")
    private String SSO_CHECK_LOGIN_URL;
    @Value("SSO_CHECK_TOKEN_URL")
    private String SSO_CHECK_TOKEN_URL;

    private static final String SSO_COOKIE = "sso-cookie";

    private static final String SUCCESS = "SUCCESS";


    /**
     * 判断当前系统是否已经登录
     * @param request
     * @param response
     * @return
     */
    public boolean currentIsLogin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        boolean isLogin = (boolean)session.getAttribute("isLogin");
        return isLogin;
    }


    /**
     * 当前系统已经登录后默认实现转发过滤链路
     * @param var1
     * @param var2
     * @param var3
     */
    public void isLoginAfter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException{
        var3.doFilter(var1,var2);
    }


    /**
     * 系统登录单点登录成功后具体实现
     * @param request
     * @param response
     */
    public Wrapper ssoLoginSuccessAfter(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("isLogin",true);
        return WrapMapper.ok().result("Login success");
    }

    /**
     * 系统登录单点登录失败后具体实现
     * @param request
     * @param response
     */
    public Wrapper ssoLoginFailureAfter(HttpServletRequest request, HttpServletResponse response){
        return WrapMapper.error().result("用户未登录，跳转至登录页面！");
    }

    /**
     * 子系统过滤器
     * @param var1
     * @param var2
     * @param var3
     * @return
     * @throws IOException
     */
    public void filter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)var1;
        HttpServletResponse response = (HttpServletResponse)var2;
        StringBuffer url = request.getRequestURL();
        boolean isLogin = currentIsLogin(request, response);
        /* 判断当前系统是否已经登录，已登录不再进行校验，发送至下一过滤链 */
        if(isLogin == true){
            isLoginAfter(var1,var2,var3);
        }

        /* 判断是否携带token，携带token则请求认证中心认证token */
        String token = request.getParameter("token");
        if(token != null){
            Map<String,String> headerMap = new HashMap<>(16);
            headerMap.put("Content-Type","application/json");
            String tokenSuccess = HttpUtil.doPostJSON(SSO_CHECK_TOKEN_URL, headerMap, null);
            if(tokenSuccess.equals(SUCCESS)){
                ssoLoginSuccessAfter(request,response);
            }else{
                ssoLoginFailureAfter(request,response);
            }
        }

        /* 当前系统无登录且无携带token则重定向请求认证中心判断是否是否在其他系统已登录，并由认证中心作出响应*/
        if(isLogin == false  && token == null){
            response.sendRedirect(SSO_CHECK_LOGIN_URL+"?redirect="+url);
        }
    }

    /**
     * 过滤链
     * @param var1
     * @param var2
     * @param var3
     */
    @Override
    public void doFilter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException{
        filter(var1,var2,var3);
    }
}
