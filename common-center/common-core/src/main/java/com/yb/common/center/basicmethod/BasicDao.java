package com.yb.common.center.basicmethod;

import com.yb.common.center.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层增删查改基础服务
 * @author yebing
 * @param <T>
 */
@Repository
public interface BasicDao<T extends BasicDTO> {
    /**
     * 根据主键删除实体
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Long id);

    /**
     * 插入实体
     * @param record
     * @return
     */
    Integer insert(T record);

    /**
     * 可选参数插入实体
     * @param record
     * @return
     */
    Integer insertSelective(T record);

    /**
     * 批量插入实体
     * @param records
     * @return
     */
    Integer batchSave(@Param("records") List<T> records);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 根据参数查询列表
     * @param record
     * @param pager
     * @return
     */
    List<T> selectList(@Param("record") T record, @Param("pager") Pager pager);

    /**
     * 根据参数查询列表统计
     * @param record
     * @return
     */
    Long countTotal(@Param("record") T record);

    /**
     * 根据主键更新实体指定参数
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(T record);

    /**
     * 根据主键更新实体
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(T record);
}
