package com.yb.common.center.basic.method;

import com.yb.common.center.wrap.Wrapper;
import com.yb.common.center.wrap.DataUtil;
import com.yb.common.center.wrap.ExecuteResult;
import com.yb.common.center.wrap.WrapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础controller
 * @author yebing
 */
public class BasicController<T extends BasicDTO,S extends BasicService> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected  S basicService;
    @RequestMapping(value = "/deleteByPrimaryKey",method={RequestMethod.POST})
    public Wrapper<?> deleteByPrimaryKey(@RequestBody T record){
        ExecuteResult<Integer> executeResult = basicService.deleteByPrimaryKey(record.getId());
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }

    @RequestMapping(value = "/insert",method={RequestMethod.POST})
    public Wrapper<?> insert(@RequestBody T record){
        ExecuteResult<Integer> executeResult = basicService.insert(record);
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }

    @RequestMapping(value = "/insertSelective",method={RequestMethod.POST})
    public Wrapper<?> insertSelective(@RequestBody T record){
        ExecuteResult<Integer> executeResult = basicService.insertSelective(record);
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }

    @RequestMapping(value = "/batchSave",method={RequestMethod.POST})
    public Wrapper<?> insertSelective(@RequestBody List<T> record){
        ExecuteResult<Integer> executeResult = basicService.batchSave(record);
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }

    @GetMapping(value = "/selectByPrimaryKey",produces = "application/json")
    public Wrapper<?> selectByPrimaryKey(@RequestParam Long id){
        ExecuteResult<T> executeResult = basicService.selectByPrimaryKey(id);
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }

    @RequestMapping(value = "/selectList",method={RequestMethod.POST})
    public Wrapper<?> selectList(@RequestBody T record){
        ExecuteResult<DataUtil<T>> executeResult = basicService.selectList(record,record.getPager());
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective",method={RequestMethod.POST})
    public Wrapper<?> updateByPrimaryKeySelective(@RequestBody T record){
        ExecuteResult<Integer> executeResult = basicService.updateByPrimaryKeySelective(record);
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }

    @RequestMapping(value = "/updateByPrimaryKey",method={RequestMethod.POST})
    public Wrapper<?> updateByPrimaryKey(@RequestBody T record){
        ExecuteResult<Integer> executeResult = basicService.updateByPrimaryKey(record);
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }


}
