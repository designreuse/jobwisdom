package com.zefun.web.mapper;

import com.zefun.web.entity.UboxMachineInfo;

/**
 * 友宝机器信息操作类
* @author 张进军
* @date Mar 4, 2016 10:40:42 PM
 */
public interface UboxMachineInfoMapper {
    
    /**
     * 新增友宝机器信息
    * @author 张进军
    * @date Mar 4, 2016 10:40:51 PM
    * @param record 友宝机器信息
    * @return   0:失败，1:成功
     */
    int insert(UboxMachineInfo record);

    /**
     * 根据机器标识查询机器信息
    * @author 张进军
    * @date Mar 4, 2016 10:41:23 PM
    * @param id 机器标识
    * @return   机器信息
     */
    UboxMachineInfo selectByPrimaryKey(String id);

    /**
     * 修改友宝机器信息
    * @author 张进军
    * @date Mar 4, 2016 10:41:36 PM
    * @param record 友宝机器信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(UboxMachineInfo record);
    
    /**
     * 根据门店标识查询售货机标识
    * @author 张进军
    * @date Mar 4, 2016 10:57:51 PM
    * @param storeId    门店标识
    * @return   售货机标识
     */
    String selectVmidByStoreId(int storeId);
    
    /**
     * 根据门店标识查询机器信息
    * @author 张进军
    * @date Mar 17, 2016 8:35:11 PM
    * @param storeId    门店标识
    * @return   机器信息
     */
    UboxMachineInfo selectMachineInfoByStoreId(int storeId);
}