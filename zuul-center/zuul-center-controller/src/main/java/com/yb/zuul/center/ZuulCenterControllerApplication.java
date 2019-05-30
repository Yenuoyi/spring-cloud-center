package com.yb.zuul.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yebing
 * @EnableEurekaClient:注册为客户端
 * @EnableDiscoveryClient：能够发现客户端服务
 * @EnableHystrix：断路器
 */
@SpringBootApplication(scanBasePackages = {"com.yb.zuul"})
@EnableZuulProxy
@MapperScan("com.yb.zuul.center.dao")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
public class ZuulCenterControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulCenterControllerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
