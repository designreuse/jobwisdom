package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.CommissionScheme;
/**
 * 
* @author 老王
* @date 2016年7月2日 下午2:07:14
 */
public interface CommissionSchemeMapper {
	/**
	 * 删除
	* @author 老王
	* @date 2016年7月2日 下午2:07:18 
	* @param commissionId commissionId
	* @return int
	 */
    int deleteByPrimaryKey(Integer commissionId);
    /**
     * 
    * @author 老王
    * @date 2016年7月2日 下午2:07:37 
    * @param record record
    * @return int
     */
    int insertSelective(CommissionScheme record);
    /**
     * 
    * @author 老王
    * @date 2016年7月2日 下午2:07:37 
    * @param commissionId commissionId
    * @return int
     */
    CommissionScheme selectByPrimaryKey(Integer commissionId);
    /**
     * 
    * @author 老王
    * @date 2016年7月2日 下午2:07:37 
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(CommissionScheme record);
    
    /**
     * 根据门店标识查询提成也分配
    * @author 老王
    * @date 2016年7月2日 下午2:43:42 
    * @param storeId 门店标识
    * @return List<CommissionScheme>
     */
    List<CommissionScheme> selectByStoreId(Integer storeId);
    /**
     * 业绩提成分配保存
    * @author 骆峰
    * @date 2016年7月4日 上午11:43:24
    * @param commissionScheme commissionScheme
    * @return int
     */ 
    int  updateSave(CommissionScheme commissionScheme);
    
}