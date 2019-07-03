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
@Controller("webLoginController")
@RequestMapping("/web/sso")
public class WebLoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String DEFAULT_SSO_LOGIN_SUCCESS_URL = "http://one.com:8002/demo/one/main";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper login(@RequestBody LoginDomain loginDomain, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //获取认证成功后跳转的请求地址
        String redirectURL = request.getParameter("redirect");
        if (redirectURL == null || StringUtils.isEmpty(redirectURL)) {
            redirectURL = DEFAULT_SSO_LOGIN_SUCCESS_URL;
        }
        session.setAttribute("isLogin", true);
        return WrapMapper.ok().message("登录成功！");
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper test(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return WrapMapper.ok().message(id + " is Login！");
    }


    @RequestMapping(value = "/failure", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper failure(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return WrapMapper.ok().message(" No Login！");
    }
}
