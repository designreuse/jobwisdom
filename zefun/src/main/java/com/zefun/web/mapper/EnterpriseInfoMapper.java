package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.EnterpriseInfoDto;
import com.zefun.web.entity.EnterpriseInfo;
import com.zefun.web.entity.Page;

/**
 * 
* @author 老王
* @date 2016年5月21日 下午6:52:00
 */
public interface EnterpriseInfoMapper {
	/**
	 * 
	* @author 老王
	* @date 2016年5月21日 下午6:52:04 
	* @param enterpriseInfoId enterpriseInfoId
	* @return  int
	 */
    int deleteByPrimaryKey(Integer enterpriseInfoId);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:52:22 
    * @param record record
    * @return int
     */
    int insertSelective(EnterpriseInfo record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:52:30 
    * @param enterpriseInfoId enterpriseInfoId
    * @return EnterpriseInfo
     */
    EnterpriseInfo selectByPrimaryKey(Integer enterpriseInfoId);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:52:38 
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(EnterpriseInfo record);

    /**
     * 
    * @author 老王
    * @date 2016年5月21日 下午6:52:46 
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(EnterpriseInfo record);

    /**
     * 根据属性查询信息
    * @author 高国藩
    * @date 2016年5月23日 下午5:59:21
    * @param enterpriseInfo enterpriseInfo
    * @return               enterpriseInfo
     */
    EnterpriseInfoDto selectByProperties(EnterpriseInfo enterpriseInfo);
    
    /**
     * 分页查询门店信息
    * @author 老王
    * @date 2016年6月6日 下午2:12:19 
    * @param page 页数信息
    * @return List<EnterpriseInfoDto>
     */
    List<EnterpriseInfoDto> selectByPage(Page<EnterpriseInfoDto> page);
}