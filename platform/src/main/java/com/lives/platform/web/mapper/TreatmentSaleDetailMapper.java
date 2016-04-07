package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.TreatmentSaleDetail;

public interface TreatmentSaleDetailMapper {
    int deleteByPrimaryKey(Integer comboSaleDetailId);

    int insert(TreatmentSaleDetail record);

    int insertSelective(TreatmentSaleDetail record);

    TreatmentSaleDetail selectByPrimaryKey(Integer comboSaleDetailId);

    int updateByPrimaryKeySelective(TreatmentSaleDetail record);

    int updateByPrimaryKey(TreatmentSaleDetail record);
}