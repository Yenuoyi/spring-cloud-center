package com.yb.api.center;

import brave.sampler.Sampler;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableEurekaClient:注册为客户端
 * @EnableDiscoveryClient：能够发现客户端服务
 * @EnableHystrix：断路器
 * @EnableHystrixDashboard：断路器监控 访问地址 http://localhost:8762/actuator/hystrix.stream
 * @author yebing
 */
@SpringBootApplication(scanBasePackages = {"com.yb.api"})
@MapperScan("com.yb.api.center.dao")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ApiCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiCenterApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 服务链调用监控方法
     * @return
     */
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    /**
     * 解决http://localhost:8762/actuator/hystrix.stream无法访问
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);

        registrationBean.setLoadOnStartup(1);

        registrationBean.addUrlMappings("/actuator/hystrix.stream");

        registrationBean.setName("HystrixMetricsStreamServlet");


        return registrationBean;
    }
}
