package com.zefun.web.mapper;

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
    * @date 2016年5月21日 下午6:53:33 
    * @param record record
    * @return int
     */
    int insert(EnterpriseMsnFlow record);
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
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:53:47 
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(EnterpriseMsnFlow record);
    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:53:50 
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(EnterpriseMsnFlow record);
}