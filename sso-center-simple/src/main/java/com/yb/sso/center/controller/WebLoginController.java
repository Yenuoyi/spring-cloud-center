package com.yb.sso.center.controller;

import com.yb.common.center.wrap.WrapMapper;
import com.yb.common.center.wrap.Wrapper;
import com.yb.sso.center.domain.LoginDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yebing
 */
@Controller("webLoginController")
@RequestMapping("/web/sso")
public class WebLoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper login(@RequestBody LoginDomain loginDomain, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
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
