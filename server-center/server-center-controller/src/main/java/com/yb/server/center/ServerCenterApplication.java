package com.yb.server.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yebing
 */
@SpringBootApplication(scanBasePackages = {"com.yb.server"})
@MapperScan("com.yb.server.center.dao")
@EnableEurekaServer
public class ServerCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerCenterApplication.class, args);
    }

}
