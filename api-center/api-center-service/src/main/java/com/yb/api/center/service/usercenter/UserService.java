package com.yb.api.center.service.usercenter;

import com.yb.api.center.common.Pager;
import com.yb.api.center.export.UserDTO;
import java.util.List;

/**
 * @author yebing
 */
public interface UserService {
    /**
     * 通用根据主键删除实体
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入实体
     * @param record
     * @return
     */
    int insert(UserDTO record);

    /**
     * 插入实体可选
     * @param record
     * @return
     */
    int insertSelective(UserDTO record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    UserDTO selectByPrimaryKey(Long id);

    /**
     * 根据主键可选择参数更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserDTO record);

    /**
     * 根据主键更新实体
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserDTO record);

    /**
     * 可选参数查询
     * @param record
     * @param pager
     * @return
     */
    List<UserDTO> selectList(UserDTO record, Pager pager);
}
