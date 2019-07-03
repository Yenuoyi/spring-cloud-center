package com.yb.sso.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.annotation.WebFilter;

@SpringBootApplication
@EnableRedisHttpSession
public class SsoCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoCenterApplication.class, args);
    }

}
