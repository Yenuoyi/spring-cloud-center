package com.yb.server.center.dao;

import com.yb.server.center.common.Pager;
import com.yb.server.center.export.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yebing
 */
public interface UserDao {
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
    List<UserDTO> selectList(@Param("record") UserDTO record, @Param("pager") Pager pager);

    /**
     * 可选参数查询统计
     * @param record
     * @return
     */
    Long selectTotal(@Param("record") UserDTO record);
}
