package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.TreatmentSale;

public interface TreatmentSaleMapper {
    int deleteByPrimaryKey(Integer treatmentSaleId);

    int insert(TreatmentSale record);

    int insertSelective(TreatmentSale record);

    TreatmentSale selectByPrimaryKey(Integer treatmentSaleId);

    int updateByPrimaryKeySelective(TreatmentSale record);

    int updateByPrimaryKey(TreatmentSale record);
}