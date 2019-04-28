package com.yb.user.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yebing
 */
@SpringBootApplication(scanBasePackages = {"com.yb.user"})
@MapperScan("com.yb.user.center.dao")
@EnableEurekaClient
public class UserCenterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(UserCenterApplication.class, args);
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

}
