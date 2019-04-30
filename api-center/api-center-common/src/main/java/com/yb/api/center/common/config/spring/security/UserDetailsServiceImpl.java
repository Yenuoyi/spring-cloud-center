package com.yb.api.center.common.config.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：动态数据库获取用户账号密码认证，不做密码校验
 * @Author Created by yebing
 * @Date 2018/8/12 22:05
 * @Version 1.0.0
 */
@Component("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //GrantedAuthority是security提供的权限类，
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        logger.info("开始获取数据库用户账号密码，处理用户信息！");

        logger.info("获取用户信息完成");

        /*虚假判断，数据库找不到username*/
        if (null == username) {
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }

        setUserRoles(auths);
        //返回包括权限角色的User给security
        return new User("username", "password", true, true, true, true, auths);
    }

    /**
     * 这是用户角色，根据实际情况重写调用
     * @param list
     */
    public void setUserRoles(List<GrantedAuthority> list){
        list.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }
}
