package com.yb.config.center.service.usercenter.impl;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yb.config.center.common.Pager;
import com.yb.config.center.export.UserDTO;
import com.yb.config.center.service.usercenter.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author yebing
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RestTemplate restTemplate;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(UserDTO record) {
        return 0;
    }

    @Override
    public int insertSelective(UserDTO record) {
        return 0;
    }

    @Override
    @HystrixCommand(fallbackMethod = "serviceError")
    public UserDTO selectByPrimaryKey(Long id) {
        String forObject = restTemplate.getForObject("http://user-center/user/selectByPrimaryKey", String.class);
        System.out.println(forObject);
        UserDTO userDTO = JSONObject.parseObject(forObject, UserDTO.class);
        return userDTO;
    }

    @Override
    public int updateByPrimaryKeySelective(UserDTO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserDTO record) {
        return 0;
    }

    @Override
    public List<UserDTO> selectList(UserDTO record, Pager pager) {
        return null;
    }

    /**
     * 保持入参、返回参数类型与原方法一致
     *
     * @param name
     * @return
     */
    public UserDTO serviceError(Long name) {
        logger.info("hi," + name + ",sorry,error!");
        return null;
    }
}
