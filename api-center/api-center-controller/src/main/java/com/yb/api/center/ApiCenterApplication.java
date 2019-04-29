package com.yb.api.center;

import brave.sampler.Sampler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableEurekaClient:注册为客户端
 * @EnableDiscoveryClient：能够发现客户端服务
 * @EnableHystrix：断路器
 * @author yebing
 */
@SpringBootApplication(scanBasePackages = {"com.yb.api"})
@MapperScan("com.yb.api.center.dao")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
public class ApiCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiCenterApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
