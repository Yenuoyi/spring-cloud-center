package com.bjucloud.marketcenter.dao;

import com.bjucloud.marketcenter.export.PromotionVoucherInfo;

public interface PromotionVoucherInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PromotionVoucherInfo record);

    int insertSelective(PromotionVoucherInfo record);

    PromotionVoucherInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PromotionVoucherInfo record);

    int updateByPrimaryKey(PromotionVoucherInfo record);
}