package com.zefun.web.mapper;

import com.zefun.web.entity.EnterpriseAccount;

/**
 * 
* @author 老王
* @date 2016年5月21日 下午6:51:00
 */
public interface EnterpriseAccountMapper {
	/**
	 * 
	* @author 老王
	* @date 2016年5月21日 下午6:51:03 
	* @param enterpriseAccountId enterpriseAccountId
	* @return int
	 */
    int deleteByPrimaryKey(Integer enterpriseAccountId);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:51:15 
    * @param record record
    * @return int
     */
    int insertSelective(EnterpriseAccount record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:51:22 
    * @param enterpriseAccountId enterpriseAccountId
    * @return EnterpriseAccount
     */
    EnterpriseAccount selectByPrimaryKey(Integer enterpriseAccountId);

    /**
     * 根据企业代号查询企业账户信息
    * @author 老王
    * @date 2016年5月24日 下午12:11:28 
    * @param storeAccount 企业代号
    * @return List<EnterpriseAccount>
     */
    EnterpriseAccount selectByStoreAccount(String storeAccount);
    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:51:25 
    * @param record record
    * @return int
     */ 
    int updateByPrimaryKeySelective(EnterpriseAccount record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:51:28 
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(EnterpriseAccount record);
}