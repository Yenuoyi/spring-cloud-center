package com.yb.user.center.service;

import com.yb.common.center.basicmethod.BasicService;
import com.yb.user.center.dao.UserDao;
import com.yb.user.center.export.UserDTO;
import java.util.List;

/**
 * @author yebing
 */
public interface UserService extends BasicService<UserDTO,UserDao> {
    UserDTO selectOne();
}
