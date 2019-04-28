package com.yb.api.center;

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
        ConfigurableApplicationContext run = SpringApplication.run(ApiCenterApplication.class, args);
        /*Object userController = run.getBean("userController");
        Object userService = run.getBean("userService");
        UserDao userDao = (UserDao)run.getBean("userDao");
        UserDTO userDTO = userDao.selectByPrimaryKey(1L);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for(int i=0;i<beanDefinitionNames.length;i++){
            System.out.println(beanDefinitionNames[i]);
        }
        System.out.println(userController.getClass().getName()+ userDTO.toString());*/
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
