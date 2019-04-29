package com.yb.user.center.api;

import com.yb.user.center.common.ExecuteResult;
import com.yb.user.center.common.WrapMapper;
import com.yb.user.center.common.Wrapper;
import com.yb.user.center.common.basicmethod.BasicController;
import com.yb.user.center.dao.UserDao;
import com.yb.user.center.export.UserDTO;
import com.yb.user.center.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author yebing
 */
@RestController
@RequestMapping("/user")
public class UserController extends BasicController<UserDTO,UserService> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 添加新方法测试
     * @param id
     * @return
     */
    @GetMapping(value = "/selectKey",produces = "application/json")
    public Wrapper<?> selectKey(@RequestParam Long id){
        ExecuteResult<UserDTO> executeResult = basicService.selectByPrimaryKey(id);
        if(executeResult.isSuccess()){
            return WrapMapper.ok().result(executeResult);
        }
        return WrapMapper.error().result(executeResult);
    }
}
