package com.yb.sso.center.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 子系统使用过滤器
 * @author yebing
 */
@WebFilter(filterName = "webSsoFilter", urlPatterns = "/*")
public class WebSsoFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 判断当前系统是否已经登录
     * @param request
     * @param response
     * @return
     */
    public boolean currentIsLogin(HttpServletRequest request, HttpServletResponse response){
        logger.info("begin system check is login!");
        HttpSession session = request.getSession();
        Object isLoginObject = session.getAttribute("isLogin");
        if(isLoginObject == null){
            return false;
        }
        boolean isLogin = (boolean)isLoginObject;
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
     * 子系统过滤器
     * @param var1
     * @param var2
     * @param var3
     * @return
     * @throws IOException
     */
    public void filter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException {
        logger.info("begin filter!");
        HttpServletRequest request = (HttpServletRequest)var1;
        HttpServletResponse response = (HttpServletResponse)var2;
        Object jsessionidObject = request.getParameter("SESSION");

        /* 判断是否带jsessionid，带jsessionid则将请求重新转发并将session重新置换 */
        if(jsessionidObject != null){
            boolean takeSession = false;
            String jsessionid = (String)jsessionidObject;
            Cookie[] cookies = request.getCookies();
            if(cookies != null && cookies.length > 0){
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("SESSION")){
                        cookie.setValue(jsessionid);
                        takeSession = true;
                        break;
                    }
                }
            }
            /* 判断cookie不带session则手动添加 */
            if (!takeSession){
                Cookie cookie = new Cookie("SESSION", jsessionid);
                cookie.setPath("/");
                cookie.setDomain("*/");
                response.addCookie(cookie);
            }

            String urlStr = request.getRequestURL().toString();
            response.sendRedirect(urlStr);
            return;
        }
        boolean isLogin = currentIsLogin(request, response);
        /* 判断当前系统是否已经登录，已登录不再进行校验，发送至下一过滤链 */
        if(isLogin == true || request.getRequestURI().contains("login") || request.getRequestURI().contains("failure")){
            isLoginAfter(var1,var2,var3);
        }else{
            response.sendRedirect("http://192.168.195.106:8001/web/sso/failure");
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
