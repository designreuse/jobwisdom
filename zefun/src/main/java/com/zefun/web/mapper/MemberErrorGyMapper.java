package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorGy;
import com.zefun.web.entity.Page;

/**
 * 耕宇会员数据,异常数据处理
* @author 高国藩
* @date 2016年1月13日 下午1:54:23
 */
public interface MemberErrorGyMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param id     主键
    * @return       影响行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param record     主键
    * @return       影响行数
     */
    int insert(MemberErrorGy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param record     主键
    * @return       影响行数
     */
    int insertSelective(MemberErrorGy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param id     主键
    * @return       影响行数
     */
    MemberErrorGy selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param record     主键
    * @return       影响行数
     */
    int updateByPrimaryKeySelective(MemberErrorGy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param record     主键
    * @return       影响行数
     */
    int updateByPrimaryKey(MemberErrorGy record);
    /**
     * 批量新增
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param errorGies   数据集合
     */
    void insertList(List<MemberErrorGy> errorGies);
    /**
     * 分页查询
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param page   分页数据
    * @return       影响行数
     */
    List<MemberErrorGy> selectByPage(Page<MemberErrorGy> page);
    /**
     * 统计数据门店
    * @author 高国藩
    * @date 2016年1月13日 下午12:05:13
    * @param storeId   门店
    * @return          数据
     */
    MemberErrorGy selectStoreMemberAmount(Integer storeId);
    /**
     * 根据门店查询所有异常会员,导出
    * @author 高国藩
    * @date 2016年1月13日 下午6:17:10
    * @param storeId            门店
    * @return                   数据集合     
     */
    List<MemberErrorGy> selectAll(Integer storeId);
}