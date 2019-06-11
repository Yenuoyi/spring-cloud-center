package com.yb.user.center.service;

import com.yb.common.center.basic.method.BasicService;
import com.yb.user.center.dao.UserDao;
import com.yb.user.center.export.UserDTO;

/**
 * @author yebing
 */
public interface UserService extends BasicService<UserDTO,UserDao> {
    UserDTO selectOne();
}
