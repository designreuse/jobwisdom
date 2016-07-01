package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.ServicePlanInfo;

/**
 * 服务计划安排
* @author 高国藩
* @date 2016年6月30日 下午5:17:00
 */
public interface ServicePlanInfoMapper {
    
    /**
     * delete
    * @author 高国藩
    * @date 2016年6月30日 下午5:17:07
    * @param sId sId
    * @return    sId
     */
    int deleteByPrimaryKey(Integer sId);
    /**
     * delete
    * @author 高国藩
    * @date 2016年6月30日 下午5:17:07
    * @param record sId
    * @return    sId
     */
    int insert(ServicePlanInfo record);
    /**
     * delete
    * @author 高国藩
    * @date 2016年6月30日 下午5:17:07
    * @param record sId
    * @return    sId
     */
    int insertSelective(ServicePlanInfo record);
    /**
     * delete
    * @author 高国藩
    * @date 2016年6月30日 下午5:17:07
    * @param sId sId
    * @return    sId
     */
    ServicePlanInfo selectByPrimaryKey(Integer sId);
    /**
     * delete
    * @author 高国藩
    * @date 2016年6月30日 下午5:17:07
    * @param record sId
    * @return    sId
     */
    int updateByPrimaryKeySelective(ServicePlanInfo record);
    /**
     * delete
    * @author 高国藩
    * @date 2016年6月30日 下午5:17:07
    * @param record sId
    * @return    sId
     */
    int updateByPrimaryKey(ServicePlanInfo record);
    
    /**
     * 查询门店下的所有服务计划
    * @author 高国藩
    * @date 2016年6月30日 下午5:17:07
    * @param servicePlanInfo servicePlanInfo
    * @return    List<ServicePlanInfo>
     */
    List<ServicePlanInfo> selectByProperites(ServicePlanInfo servicePlanInfo);
}