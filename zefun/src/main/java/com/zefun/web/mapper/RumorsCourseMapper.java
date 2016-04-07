package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.Page;
import com.zefun.web.entity.RumorsCourse;

/**
 * 疗程卡盛传
* @author 高国藩
* @date 2015年12月30日 下午4:31:01
 */
public interface RumorsCourseMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param id  主键
    * @return    影响行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param record  主键
    * @return    影响行数
     */
    int insert(RumorsCourse record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param record  主键
    * @return    影响行数
     */
    int insertSelective(RumorsCourse record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param id  主键
    * @return    影响行数
     */
    RumorsCourse selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param record  主键
    * @return    影响行数
     */
    int updateByPrimaryKeySelective(RumorsCourse record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param record  主键
    * @return    影响行数
     */
    int updateByPrimaryKey(RumorsCourse record);
    /**
     * 批量新增
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param courses  新增
     */
    void insertList(List<RumorsCourse> courses);
    /**
     * 分页查询
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param pageRumors  分页
    * @return    数据
     */
    List<RumorsCourse> selectByPage(Page<RumorsCourse> pageRumors);
    /**
     * 统计
    * @author 高国藩
    * @date 2015年12月30日 下午4:31:12
    * @param storeId  门店
    * @return         实体数据
     */
    RumorsCourse selectStoreMemberAmount(Integer storeId);
    
    /**
     * 查询所有的疗程
    * @author 高国藩
    * @date 2016年3月12日 下午4:50:11
    * @param storeId storeId
    * @return        List<RumorsCourse>
     */
    List<RumorsCourse> selectAllByStoreId(Integer storeId);
}