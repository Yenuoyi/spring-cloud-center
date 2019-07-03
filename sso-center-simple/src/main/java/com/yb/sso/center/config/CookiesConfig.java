package com.yb.sso.center.config;

import com.yb.sso.center.util.MyCookieSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;

@Configuration
public class CookiesConfig {

    /**
     * Spring session cookie 处理器,默认DefaultCookieSerializer
     * @return
     */
    @Bean
    public CookieSerializer cookieSerializer() {
        return new MyCookieSerializer();
    }
}
