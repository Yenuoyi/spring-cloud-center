package com.yb.user.center.api;

import com.yb.user.center.export.UserDTO;
import com.yb.user.center.service.UserService;
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
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService userService;

    @GetMapping("/selectByPrimaryKey")
    public UserDTO selectByPrimaryKey(Long id) {
        logger.info("id:{}",id);
        return userService.selectByPrimaryKey(1L);
    }
}
