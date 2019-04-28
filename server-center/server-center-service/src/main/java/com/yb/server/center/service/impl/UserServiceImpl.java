package com.yb.server.center.service.impl;

import com.yb.server.center.common.Pager;
import com.yb.server.center.dao.UserDao;
import com.yb.server.center.export.UserDTO;
import com.yb.server.center.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yebing
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
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
    public UserDTO selectByPrimaryKey(Long id) {
        System.out.println();
        return userDao.selectByPrimaryKey(1L);
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
}
