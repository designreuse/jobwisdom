package com.zefun.web.mapper;

import com.zefun.web.entity.EnterpriseAccountFlow;

/**
 * 
* @author 老王
* @date 2016年5月21日 下午6:48:56
 */
public interface EnterpriseAccountFlowMapper {
	
	/**
	 * 删除
	* @author 老王
	* @date 2016年5月21日 下午6:49:06 
	* @param accountFlowId accountFlowId
	* @return 是否成功
	 */
    int deleteByPrimaryKey(Integer accountFlowId);
   
    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:49:54 
    * @param record record
    * @return 是否成功
     */
    int insert(EnterpriseAccountFlow record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:50:12 
    * @param record record
    * @return 是否成功
     */
    int insertSelective(EnterpriseAccountFlow record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:50:21 
    * @param accountFlowId accountFlowId
    * @return EnterpriseAccountFlow
     */
    EnterpriseAccountFlow selectByPrimaryKey(Integer accountFlowId);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:50:34 
    * @param record record
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(EnterpriseAccountFlow record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:50:47 
    * @param record record
    * @return 是否成功
     */
    int updateByPrimaryKey(EnterpriseAccountFlow record);
}