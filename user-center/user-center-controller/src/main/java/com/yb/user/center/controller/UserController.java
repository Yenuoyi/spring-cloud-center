package com.yb.user.center.controller;

import com.yb.common.center.basicmethod.BasicController;
import com.yb.common.center.wrap.ExecuteResult;
import com.yb.common.center.wrap.WrapMapper;
import com.yb.common.center.wrap.Wrapper;
import com.yb.user.center.export.UserDTO;
import com.yb.user.center.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yebing
 */
@RestController
@RequestMapping("/user")
public class UserController extends BasicController<UserDTO,UserService> {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 添加新方法测试
     * @param id
     * @return
     */
    @GetMapping(value = "/selectOne",produces = "application/json")
    public Wrapper<?> selectKey(@RequestParam Long id){
        UserDTO userDTO = basicService.selectOne();
        return WrapMapper.ok().result(userDTO);
    }

    @GetMapping(value = "/setRedis",produces = "application/json")
    public Wrapper<?> setRedis(@RequestParam Long id){
        redisTemplate.opsForValue().set("yebing","1440121130");
        return WrapMapper.error().result("ok");
    }

}
