package com.zefun.web.mapper;

import com.zefun.web.entity.RechargeRecord;

/**
 * 充值记录表
* @author 高国藩
* @date 2016年5月23日 下午8:10:05
 */
public interface RechargeRecordMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月23日 下午8:11:35
    * @param rId rId
    * @return    rId
     */
    int deleteByPrimaryKey(Integer rId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月23日 下午8:11:35
    * @param record rId
    * @return    rId
     */
    int insert(RechargeRecord record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月23日 下午8:11:35
    * @param record rId
    * @return    rId
     */
    int insertSelective(RechargeRecord record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月23日 下午8:11:35
    * @param rId rId
    * @return    rId
     */
    RechargeRecord selectByPrimaryKey(Integer rId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月23日 下午8:11:35
    * @param record rId
    * @return    rId
     */
    int updateByPrimaryKeySelective(RechargeRecord record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月23日 下午8:11:35
    * @param record rId
    * @return    rId
     */
    int updateByPrimaryKey(RechargeRecord record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月23日 下午8:11:35
    * @param record rId
    * @return    rId
     */
    RechargeRecord selectByTradeNo(RechargeRecord record);
}