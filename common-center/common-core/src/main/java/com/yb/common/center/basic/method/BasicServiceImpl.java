package com.yb.common.center.basic.method;

import com.yb.common.center.wrap.DataUtil;
import com.yb.common.center.wrap.ExecuteResult;
import com.yb.common.center.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * service基础增删查改
 *
 * @param <T>
 * @author yebing
 */
public abstract class BasicServiceImpl<T extends BasicDTO, D extends BasicDao<T>> implements BasicService<T, D> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected D basicDao;

    @Override
    public ExecuteResult<Integer> deleteByPrimaryKey(Long id) {
        ExecuteResult<Integer> executeResult = new ExecuteResult<Integer>();
        try {
            if (StringUtils.isEmpty(id)) {
                throw new RuntimeException("参数错误：ID非空");
            }
            Integer result = basicDao.deleteByPrimaryKey(id);
            executeResult.setResult(result);
            executeResult.setResultMessage("成功！");
        } catch (Exception e) {
            executeResult.setResultMessage("异常错误！");
            executeResult.getErrorMessages().add(e.getMessage());
            logger.error("异常错误", e);
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<Integer> insert(T record) {
        ExecuteResult<Integer> executeResult = new ExecuteResult<Integer>();
        try {
            if (StringUtils.isEmpty(record)) {
                throw new RuntimeException("参数错误：对象非空");
            }
            Integer result = basicDao.insert(record);
            executeResult.setResult(result);
            executeResult.setResultMessage("成功！");
        } catch (Exception e) {
            executeResult.setResultMessage("异常错误！");
            executeResult.getErrorMessages().add(e.getMessage());
            logger.error(e.getMessage());
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<Integer> insertSelective(T record) {
        ExecuteResult<Integer> executeResult = new ExecuteResult<Integer>();
        try {
            if (StringUtils.isEmpty(record)) {
                throw new RuntimeException("参数错误：对象非空");
            }
            Integer result = basicDao.insertSelective(record);
            executeResult.setResult(result);
            executeResult.setResultMessage("成功！");
        } catch (Exception e) {
            executeResult.setResultMessage("异常错误！");
            executeResult.getErrorMessages().add(e.getMessage());
            logger.error(e.getMessage());
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<Integer> batchSave(List<T> record) {
        ExecuteResult<Integer> executeResult = new ExecuteResult<Integer>();
        try {
            if (StringUtils.isEmpty(record)) {
                throw new RuntimeException("参数错误：对象非空");
            }
            Integer result = basicDao.batchSave(record);
            executeResult.setResult(result);
            executeResult.setResultMessage("成功！");
        } catch (Exception e) {
            executeResult.setResultMessage("异常错误！");
            executeResult.getErrorMessages().add(e.getMessage());
            logger.error(e.getMessage());
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<T> selectByPrimaryKey(Long id) {
        ExecuteResult<T> executeResult = new ExecuteResult<T>();
        try {
            if (StringUtils.isEmpty(id)) {
                throw new RuntimeException("参数错误：ID非空");
            }
            T result = basicDao.selectByPrimaryKey(id);
            executeResult.setResult(result);
            executeResult.setResultMessage("成功！");
        } catch (Exception e) {
            executeResult.setResultMessage("异常错误！");
            executeResult.getErrorMessages().add(e.getMessage());
            logger.error(e.getMessage());
        }
        return executeResult;
    }


    @Override
    public ExecuteResult<DataUtil<T>> selectList(T record, Pager pager) {
        ExecuteResult<DataUtil<T>> executeResult = new ExecuteResult<DataUtil<T>>();
        try {
            if (StringUtils.isEmpty(record)) {
                throw new RuntimeException("参数错误：对象非空");
            }
            List<T> result = basicDao.selectList(record, pager);
            Integer total = basicDao.countTotal(record).intValue();
            DataUtil<T> dtoDataUtil = new DataUtil<T>();
            dtoDataUtil.setList(result);
            dtoDataUtil.getPager().setTotalCount(total);
            executeResult.setResult(dtoDataUtil);
            executeResult.setResultMessage("成功！");
        } catch (Exception e) {
            executeResult.setResultMessage("异常错误！");
            executeResult.getErrorMessages().add(e.getMessage());
            logger.error("异常错误", e);
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<Integer> updateByPrimaryKeySelective(T record) {
        ExecuteResult<Integer> executeResult = new ExecuteResult<Integer>();
        try {
            if (StringUtils.isEmpty(record.getId())) {
                throw new RuntimeException("参数错误：ID非空");
            }
            Integer result = basicDao.updateByPrimaryKeySelective(record);
            executeResult.setResult(result);
            executeResult.setResultMessage("成功！");
        } catch (Exception e) {
            executeResult.setResultMessage("异常错误！");
            executeResult.getErrorMessages().add(e.getMessage());
            logger.error("异常错误", e);
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<Integer> updateByPrimaryKey(T record) {
        ExecuteResult<Integer> executeResult = new ExecuteResult<Integer>();
        try {
            if (StringUtils.isEmpty(record.getId())) {
                throw new RuntimeException("参数错误：ID非空");
            }
            Integer result = basicDao.updateByPrimaryKey(record);
            executeResult.setResult(result);
            executeResult.setResultMessage("成功！");
        } catch (Exception e) {
            executeResult.setResultMessage("异常错误！");
            executeResult.getErrorMessages().add(e.getMessage());
            logger.error("异常错误", e);
        }
        return executeResult;
    }
}
