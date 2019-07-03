package com.yb.sso.center.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security配置，引入spring session时默认引入了，所以需要配置
 * @author yebing
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override  public void configure(WebSecurity web) throws Exception {
        web.ignoring().anyRequest();  }

}
