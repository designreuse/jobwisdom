package com.zefun.web.mapper;

import com.zefun.web.entity.EnterpriseStoreAuthority;

/**
 * 
* @author 老王
* @date 2016年5月21日 下午6:54:18
 */
public interface EnterpriseStoreAuthorityMapper {
	/**
	 * 
	* @author 老王
	* @date 2016年5月21日 下午6:54:22 
	* @param storeAuthorityId storeAuthorityId
	* @return int
	 */
    int deleteByPrimaryKey(Integer storeAuthorityId);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:54:25 
    * @param record record
    * @return int
     */
    int insert(EnterpriseStoreAuthority record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:54:29 
    * @param record record
    * @return int
     */
    int insertSelective(EnterpriseStoreAuthority record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:54:32 
    * @param storeAuthorityId storeAuthorityId
    * @return EnterpriseStoreAuthority
     */
    EnterpriseStoreAuthority selectByPrimaryKey(Integer storeAuthorityId);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:54:35 
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(EnterpriseStoreAuthority record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:54:39 
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(EnterpriseStoreAuthority record);
}