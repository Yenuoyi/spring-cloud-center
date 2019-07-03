package com.yb.user.center.service.impl;

import com.yb.common.center.basic.method.BasicServiceImpl;
import com.yb.user.center.common.aop.AopAnnotation;
import com.yb.user.center.dao.UserDao;
import com.yb.user.center.export.UserDTO;
import com.yb.user.center.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yebing
 */
@Service("userService")
public class UserServiceImpl extends BasicServiceImpl<UserDTO, UserDao> implements UserService {
    @Override
    @AopAnnotation
    public UserDTO selectOne() {
        return null;
    }
}
