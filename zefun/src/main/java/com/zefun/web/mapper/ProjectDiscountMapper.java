package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.ProjectDiscount;

/**
 * 项目会员折扣
* @author 洪秋霞
* @date 2015年8月11日 上午11:03:58
 */
public interface ProjectDiscountMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:04:07
    * @param discountId 折扣id
    * @return int
     */
    int deleteByPrimaryKey(Integer discountId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:04:34
    * @param projectDiscount 项目会员折扣
    * @return int
     */
    int insertSelective(ProjectDiscount projectDiscount);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:04:53
    * @param discountId 折扣id
    * @return ProjectDiscount
     */
    ProjectDiscount selectByPrimaryKey(Integer discountId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:05:06
    * @param projectDiscount 项目会员折扣
    * @return int
     */
    int updateByPrimaryKeySelective(ProjectDiscount projectDiscount);

    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:05:22
    * @param projectDiscount 项目会员折扣
    * @return List<ProjectDiscount>
     */
    List<ProjectDiscount> selectByProperty(ProjectDiscount projectDiscount);
    
    /**
     * 批量插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:05:38
    * @param projectDiscountList 项目会员折扣列表
    * @return int
     */
    int insertProjectDiscountList(List<ProjectDiscount> projectDiscountList);
    
    /**
     * 根据项目标识、会员等级标识查询项目的会员特定价格
     * @param map	项目标识、会员等级标识
     * @return	会员特定价格
     */
    ProjectDiscount selectDiscountPorjectIdAndLevelId(Map<String, Integer> map);
}