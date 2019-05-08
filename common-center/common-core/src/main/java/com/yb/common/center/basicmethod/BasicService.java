package com.yb.common.center.basicmethod;

import com.yb.common.center.wrap.DataUtil;
import com.yb.common.center.wrap.ExecuteResult;
import com.yb.common.center.Pager;

import java.util.List;

/**
 * service基础增删查改
 * @author yebing
 * @param <T>
 */
public interface BasicService<T extends BasicDTO,D extends BasicDao<T>> {
    /**
     * 根据主键删除实体
     * @param id
     * @return
     */
    ExecuteResult<Integer> deleteByPrimaryKey(Long id);

    /**
     * 插入实体
     * @param record
     * @return
     */
    ExecuteResult<Integer> insert(T record);

    /**
     * 可选参数插入实体
     * @param record
     * @return
     */
    ExecuteResult<Integer> insertSelective(T record);

    /**
     * 批量插入实体
     * @param records
     * @return
     */
    ExecuteResult<Integer> batchSave(List<T> records);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    ExecuteResult<T> selectByPrimaryKey(Long id);

    /**
     * 根据参数查询列表
     * @param record
     * @param pager
     * @return
     */
    ExecuteResult<DataUtil<T>> selectList(T record, Pager pager);

    /**
     * 根据主键更新实体指定参数
     * @param record
     * @return
     */
    ExecuteResult<Integer> updateByPrimaryKeySelective(T record);

    /**
     * 根据主键更新实体
     * @param record
     * @return
     */
    ExecuteResult<Integer> updateByPrimaryKey(T record);

}
