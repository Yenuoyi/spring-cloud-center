package com.yb.sso.center;

import com.yb.common.center.util.TokenUtil;
import com.yb.common.center.wrap.WrapMapper;
import com.yb.common.center.wrap.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @author yebing
 */
@Controller("loginController")
@RequestMapping("/sso")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String SSO_COOKIE = "sso-cookie";
    private static String DEFAULT_SSO_LOGIN_SUCCESS_URL = "http://one.com:8002/demo/one/main";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper login(@RequestBody LoginDomain loginDomain, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //获取认证成功后跳转的请求地址
        String redirectURL = request.getParameter("redirect");
        if(redirectURL == null ||StringUtils.isEmpty(redirectURL)){
            redirectURL = DEFAULT_SSO_LOGIN_SUCCESS_URL;
        }
        Cookie cookie = new Cookie(SSO_COOKIE,session.getId());
        session.setAttribute("isLogin",true);
        response.addCookie(cookie);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,60);
        String token = TokenUtil.createToken("123456",calendar.getTime());
        session.setAttribute("token",token);
        response.sendRedirect(redirectURL+"?token="+token);
        return WrapMapper.ok().message("登录成功！");
    }

    /**
     * 检查其他系统是否已经登录
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Boolean isLogin = (Boolean)session.getAttribute("isLogin");
        if(isLogin == null || isLogin == false){
            return WrapMapper.error().message("重定向至登录页");
        }else{
            String token = (String)session.getAttribute("token");
            String redirect = request.getParameter("redirect");
            response.sendRedirect(redirect+"?token="+token);
        }
        return WrapMapper.ok().message("已登录！");
    }


    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getParameter("token");
        if(token == null || StringUtils.isEmpty(token)){
            return WrapMapper.ok().message("Token Check failure，Because token is empty！");
        }
        String parseResult = TokenUtil.parseToken(token);
        if(parseResult == null || StringUtils.isEmpty(parseResult)){
            return WrapMapper.ok().message("Token check failure，maybe token error or token expired！");
        }
        return WrapMapper.ok().message("Token Check OK！");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper test(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return WrapMapper.ok().message("Token Check "+id+" OK！");
    }
}
