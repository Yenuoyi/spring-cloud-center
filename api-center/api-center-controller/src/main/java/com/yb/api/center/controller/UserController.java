package com.yb.api.center.controller;

import com.yb.api.center.export.UserDTO;
import com.yb.api.center.service.usercenter.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author yebing
 */
@RestController
@RequestMapping("/user")
@Api(description = "管理员角色拥有的答卷控制器")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService userService;

    @GetMapping(value = "/selectByPrimaryKey",produces = "application/json")
    @ApiOperation(value = "主键查询", httpMethod = "GET", response = UserDTO.class, notes = "主键查询")

    public UserDTO selectByPrimaryKey(Long id) {
        logger.info("id:{}",id);
        return userService.selectByPrimaryKey(1L);
    }
}
