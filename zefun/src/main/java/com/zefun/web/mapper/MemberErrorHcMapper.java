package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorHc;
import com.zefun.web.entity.Page;

/**
 * 华彩数据
* @author 高国藩
* @date 2016年3月16日 下午2:52:51
 */
public interface MemberErrorHcMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月16日 下午2:52:59
    * @param id id
    * @return id
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月16日 下午2:52:59
    * @param record id
    * @return id
     */
    int insert(MemberErrorHc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月16日 下午2:52:59
    * @param record id
    * @return id
     */
    int insertSelective(MemberErrorHc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月16日 下午2:52:59
    * @param id id
    * @return id
     */
    MemberErrorHc selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月16日 下午2:52:59
    * @param record id
    * @return id
     */
    int updateByPrimaryKeySelective(MemberErrorHc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月16日 下午2:52:59
    * @param record id
    * @return id
     */
    int updateByPrimaryKey(MemberErrorHc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月16日 下午2:52:59
    * @param errorHcs id
     */
    void insertList(List<MemberErrorHc> errorHcs);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月16日 下午2:52:59
    * @param page id
    * @return id
     */
    List<MemberErrorHc> selectByPage(Page<MemberErrorHc> page);
    /**
     * 查询所有
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param storeId   门店
    * @return          集合数据
     */
    List<MemberErrorHc> selectAll(Integer storeId);
    
    /**
     * 异常会员数据统计
    * @author 高国藩
    * @date 2015年12月21日 上午11:30:06
    * @param storeId    门店信息
    * @return           异常数据
     */
    MemberErrorHc selectStoreMemberAmount(Integer storeId);
}