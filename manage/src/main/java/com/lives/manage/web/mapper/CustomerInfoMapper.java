package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.dto.CustInfoDto;
import com.lives.manage.web.dto.HchartDto;
import com.lives.manage.web.entity.CustomerInfo;

public interface CustomerInfoMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(CustomerInfo record);

    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);
    
    List<Map<String ,Object>>queryCustInfo(Map<String ,Object> map);
    
    int queryCustCount(Map<String ,Object> map);
    
    
    //----------------------统计图相关参数--------------------------
    List<HchartDto> eachMonthSales(HchartDto hchartDto);
    
    
    List<HchartDto> queryCount(Map<String, Object> map);
    
    List<HchartDto> queryCountNum(HchartDto hchartDto);
  //----------------------首页统计图相关参数--------------------------
    List<Map<String,Object>> querySale(CustomerInfo record);
    
    List<Map<String,Object>> queryStatistics();
    
    //获取所有在用客户名称
    List<Map<String,Object>> getcustomerName();
    
  //获取所有在用客户id
    int getcustomerId(Map<String,Object> map);
    int getcustomerIdCount(Map<String,Object> map);
    //获取客户名是否存在
    int getcustNameCount(CustomerInfo record);
    //获取公司名是否存在
    int getCompanyNameCount(CustomerInfo record);
    
  //获取所有在用客户公司名称
    List<Map<String,Object>> getCompanyName();
  //获取id
    int queryCustomerId(CustomerInfo record);
    
    //根据id获取客户信息
    Map<String,Object> getCustomerInfo(Map<String,Object> map);
    
    
    
    
    
    
    

    
    
}