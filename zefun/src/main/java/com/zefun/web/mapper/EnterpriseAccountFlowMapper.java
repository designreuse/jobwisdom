package com.zefun.web.mapper;

import java.util.List;

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
    * @date 2016年5月24日 下午4:27:36 
    * @param storeAccount 企业代号
    * @return  List<EnterpriseAccountFlow>
     */
    List<EnterpriseAccountFlow> selectByStoreAccount(String storeAccount);
    
    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:50:34 
    * @param record record
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(EnterpriseAccountFlow record);

}