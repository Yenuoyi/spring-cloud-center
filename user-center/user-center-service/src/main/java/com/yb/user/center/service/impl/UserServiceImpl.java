package com.yb.user.center.service.impl;

import com.yb.common.center.basic.method.BasicServiceImpl;
import com.yb.common.center.thread.pool.ThreadPoolUtil;
import com.yb.user.center.common.aop.AopAnnotation;
import com.yb.user.center.dao.PromotionVoucherInfoDao;
import com.yb.user.center.dao.UserDao;
import com.yb.user.center.export.PromotionVoucherInfoDTO;
import com.yb.user.center.export.UserDTO;
import com.yb.user.center.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yebing
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends BasicServiceImpl<UserDTO, UserDao> implements UserService {
    @Autowired
    private PromotionVoucherInfoDao promotionVoucherInfoDao;
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED,readOnly = false)
    public UserDTO selectOne() {
        PromotionVoucherInfoDTO promotionVoucherInfoDTO = generatorProm(1).get(0);
        promotionVoucherInfoDao.insert(promotionVoucherInfoDTO);
        throw new RuntimeException("Exception");
    }

    @Transactional
    public List<PromotionVoucherInfoDTO> generatorProm(int size) {
        List<PromotionVoucherInfoDTO> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            PromotionVoucherInfoDTO promotionVoucherInfoDTO = new PromotionVoucherInfoDTO();
            promotionVoucherInfoDTO.setCardPrefix("A");
            promotionVoucherInfoDTO.setCreateId(1L);
            promotionVoucherInfoDTO.setCreateName("yebing");
            promotionVoucherInfoDTO.setCreateCode("001");
            promotionVoucherInfoDTO.setCreateTime(new Date());
            promotionVoucherInfoDTO.setActivityStartTime(new Date());
            promotionVoucherInfoDTO.setActivityEndTime(new Date());
            promotionVoucherInfoDTO.setGenerationType(1);
            promotionVoucherInfoDTO.setParticipatePlatform("O2O");
            promotionVoucherInfoDTO.setPeriod(1);
            promotionVoucherInfoDTO.setUseStartTime(new Date());
            promotionVoucherInfoDTO.setUseEndTime(new Date());
            list.add(promotionVoucherInfoDTO);
        }
        return list;
    }

}
