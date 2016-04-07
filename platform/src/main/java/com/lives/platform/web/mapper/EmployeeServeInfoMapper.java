package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.EmployeeServeInfo;

public interface EmployeeServeInfoMapper {
    int deleteByPrimaryKey(Integer employeeServeInfoId);

    int insert(EmployeeServeInfo record);

    int insertSelective(EmployeeServeInfo record);

    EmployeeServeInfo selectByPrimaryKey(Integer employeeServeInfoId);

    int updateByPrimaryKeySelective(EmployeeServeInfo record);

    int updateByPrimaryKey(EmployeeServeInfo record);
}