package com.zefun.web.mapper;

import java.util.List;

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
     * 根据企业代号查出所有授权码集合
    * @author 老王
    * @date 2016年5月24日 上午12:45:17 
    * @param storeAccount 企业代号
    * @return List<EnterpriseStoreAuthority>
     */
    List<EnterpriseStoreAuthority> selectAuthorityByStoreAccount(String storeAccount); 
    
    /**
     * 
    * @author 老王
    * @date 2016年5月25日 下午7:59:29 
    * @param record 授权对象
    * @return List<EnterpriseStoreAuthority>
     */
    List<EnterpriseStoreAuthority> selectByProperties(EnterpriseStoreAuthority record);
    
    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:54:35 
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(EnterpriseStoreAuthority record);

}