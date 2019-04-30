package com.yb.api.center.common.config.spring.security;

import com.yb.api.center.common.encrypt.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 功能：密码认证处理器，继承MessageDigestPasswordEncoder类，重写密码认证方法。
 *       <password-encoder ref="messageDigestPasswordEncoderImpl"></password-encoder>搭配使用
 * @Author: yebing
 * @Date: 2018-8-13 12:32
 * @Version 1.0.0
 */
@Component("passwordEncoder")
public class MessageDigestPasswordEncoderImpl implements PasswordEncoder {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public String encode(CharSequence charSequence) {
        logger.info(charSequence.toString());
        /*return Md5Util.encode(charSequence.toString());*/
        return "password";
    }

    /**
     * @param rawPassword 原密码
     * @param encodedPassword 加密后密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        logger.info("rawPassword:"+rawPassword.toString()+"      encodedPassword:"+encodedPassword);
        return this.encode(rawPassword).equals(encodedPassword);
    }
}
