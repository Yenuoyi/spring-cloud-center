package com.yb.user.center.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DemoConfig {

    private static final Logger logger = LoggerFactory.getLogger(DemoConfig.class);

    @PostConstruct
    public void afterPropertiesSet() {
        logger.info("No {} found.", DemoConfig.class.getName());
    }
}
