package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.ServicePlanTemp;

/**
 * 服务计划模板
* @author 高国藩
* @date 2016年7月1日 下午3:44:58
 */
public interface ServicePlanTempMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年7月1日 下午3:45:06
    * @param tId tId
    * @return    tId
     */
    int deleteByPrimaryKey(Integer tId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年7月1日 下午3:45:06
    * @param record tId
    * @return    tId
     */
    int insert(ServicePlanTemp record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年7月1日 下午3:45:06
    * @param record tId
    * @return    tId
     */
    int insertSelective(ServicePlanTemp record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年7月1日 下午3:45:06
    * @param tId tId
    * @return    tId
     */
    ServicePlanTemp selectByPrimaryKey(Integer tId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年7月1日 下午3:45:06
    * @param record tId
    * @return    tId
     */
    int updateByPrimaryKeySelective(ServicePlanTemp record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年7月1日 下午3:45:06
    * @param record tId
    * @return    tId
     */
    int updateByPrimaryKey(ServicePlanTemp record);

    /**
     * 根据属性查询
    * @author 高国藩
    * @date 2016年7月1日 下午3:45:36
    * @param servicePlanTemp servicePlanTemp
    * @return                List<ServicePlanTemp>
     */
    List<ServicePlanTemp> selectByProperties(ServicePlanTemp servicePlanTemp);
}