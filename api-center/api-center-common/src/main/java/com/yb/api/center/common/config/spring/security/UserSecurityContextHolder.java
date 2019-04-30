package com.yb.api.center.common.config.spring.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义工具类，可获取已登录用户信息，根据实际情况编写
 * @author yebing
 */
@Component("userSecurityContextHolder")
public class UserSecurityContextHolder {
    public static UserDetails getUserDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails;
    }
    public static String getUsername(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername().split("_")[1];
        return username;
    }
    public static String getUserRole(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String userRole = userDetails.getUsername().split("_")[0];
        return userRole;
    }

    public static int getUserId(HttpServletRequest httpServletRequest){
        int id = Integer.parseInt(httpServletRequest.getSession().getAttribute("id").toString());
        return id;
    }
}
