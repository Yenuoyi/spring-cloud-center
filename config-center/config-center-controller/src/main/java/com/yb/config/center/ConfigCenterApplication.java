package com.yb.config.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableEurekaClient:注册为客户端
 * @EnableDiscoveryClient：能够发现客户端服务
 * @EnableConfigServer：配置中心
 * @author yebing
 */
@SpringBootApplication(scanBasePackages = {"com.yb.config"})
@MapperScan("com.yb.config.center.dao")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigCenterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ConfigCenterApplication.class, args);
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
