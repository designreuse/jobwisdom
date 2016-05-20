package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.SpecialService;

/**
 * 特色服务
* @author 高国藩
* @date 2016年5月19日 下午8:27:35
 */
public interface SpecialServiceMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:43
    * @param sId sId
    * @return    sId
     */
    int deleteByPrimaryKey(Integer sId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:43
    * @param record sId
    * @return    sId
     */
    int insert(SpecialService record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:43
    * @param record sId
    * @return    sId
     */
    int insertSelective(SpecialService record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:43
    * @param sId sId
    * @return    sId
     */
    SpecialService selectByPrimaryKey(Integer sId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:43
    * @param record sId
    * @return    sId
     */
    int updateByPrimaryKeySelective(SpecialService record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:43
    * @param record sId
    * @return    sId
     */
    int updateByPrimaryKeyWithBLOBs(SpecialService record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:43
    * @param record sId
    * @return    sId
     */
    int updateByPrimaryKey(SpecialService record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:43
    * @param storeId sId
    * @return    sId
     */
    List<SpecialService> selectByStoreId(Integer storeId);
}