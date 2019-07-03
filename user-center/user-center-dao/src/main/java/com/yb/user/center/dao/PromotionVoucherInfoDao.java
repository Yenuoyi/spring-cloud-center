package com.yb.user.center.dao;


import com.yb.user.center.export.PromotionVoucherInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yebing
 */
public interface PromotionVoucherInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(PromotionVoucherInfoDTO record);

    int insertSelective(PromotionVoucherInfoDTO record);

    int batchSave(@Param("record") List<PromotionVoucherInfoDTO> list);

    PromotionVoucherInfoDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PromotionVoucherInfoDTO record);

    int updateByPrimaryKey(PromotionVoucherInfoDTO record);
}