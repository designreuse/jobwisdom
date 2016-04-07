package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorHtmy;
import com.zefun.web.entity.Page;

/**
 * 
* @author 高国藩
* @date 2016年3月15日 下午4:35:42
 */
public interface MemberErrorHtmyMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月15日 下午4:35:26
    * @param id id
    * @return   id
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月15日 下午4:35:26
    * @param record id
    * @return   id
     */
    int insert(MemberErrorHtmy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月15日 下午4:35:26
    * @param record id
    * @return   id
     */
    int insertSelective(MemberErrorHtmy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月15日 下午4:35:26
    * @param id id
    * @return   id
     */
    MemberErrorHtmy selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月15日 下午4:35:26
    * @param record id
    * @return   id
     */
    int updateByPrimaryKeySelective(MemberErrorHtmy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月15日 下午4:35:26
    * @param record id
    * @return   id
     */
    int updateByPrimaryKey(MemberErrorHtmy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月15日 下午4:35:26
    * @param errorHts id
     */
    void insertList(List<MemberErrorHtmy> errorHts);
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月15日 下午4:35:26
    * @param page id
    * @return          集合数据
     */
    List<MemberErrorHtmy> selectByPage(Page<MemberErrorHtmy> page);
    /**
     * 查询所有
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param storeId   门店
    * @return          集合数据
     */
    List<MemberErrorHtmy> selectAll(Integer storeId);
    
    /**
     * 异常会员数据统计
    * @author 高国藩
    * @date 2015年12月21日 上午11:30:06
    * @param storeId    门店信息
    * @return           异常数据
     */
    MemberErrorHtmy selectStoreMemberAmount(Integer storeId);
}