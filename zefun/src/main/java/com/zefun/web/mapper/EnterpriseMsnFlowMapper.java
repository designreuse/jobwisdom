package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.EnterpriseMsnFlow;
/**
 * 
* @author 老王
* @date 2016年5月21日 下午6:53:20
 */
public interface EnterpriseMsnFlowMapper {
	/**
	 * 
	* @author 老王
	* @date 2016年5月21日 下午6:53:25 
	* @param msnFlowId msnFlowId
	* @return int
	 */
    int deleteByPrimaryKey(Integer msnFlowId);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:53:42 
    * @param record record
    * @return int
     */
    int insertSelective(EnterpriseMsnFlow record);
    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:53:45 
    * @param msnFlowId msnFlowId
    * @return EnterpriseMsnFlow
     */
    EnterpriseMsnFlow selectByPrimaryKey(Integer msnFlowId);
    
    /**
     * 查询企业短信充值分配记录
    * @author 老王
    * @date 2016年5月25日 下午3:43:44 
    * @param storeAccount 企业代号
    * @return List<EnterpriseMsnFlow>
     */
    List<EnterpriseMsnFlow> selectByStoreAccount(String storeAccount);
    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:53:47 
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(EnterpriseMsnFlow record);
}