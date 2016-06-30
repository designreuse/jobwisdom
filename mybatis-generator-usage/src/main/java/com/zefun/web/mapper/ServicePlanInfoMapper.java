package com.zefun.web.mapper;

import com.zefun.web.entity.ServicePlanInfo;

public interface ServicePlanInfoMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(ServicePlanInfo record);

    int insertSelective(ServicePlanInfo record);

    ServicePlanInfo selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(ServicePlanInfo record);

    int updateByPrimaryKey(ServicePlanInfo record);
}