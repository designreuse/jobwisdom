package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorXsl;
import com.zefun.web.entity.Page;

/**
 * 西沙龙
* @author 高国藩
* @date 2016年3月5日 下午4:14:57
 */
public interface MemberErrorXslMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param id 主键
    * @return   影响行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param record 主键
    * @return   影响行数
     */
    int insert(MemberErrorXsl record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param record 主键
    * @return   影响行数
     */
    int insertSelective(MemberErrorXsl record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param id 主键
    * @return   影响行数
     */
    MemberErrorXsl selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param record 主键
    * @return   影响行数
     */
    int updateByPrimaryKeySelective(MemberErrorXsl record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param record 主键
    * @return   影响行数
     */
    int updateByPrimaryKey(MemberErrorXsl record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param errorXsls 主键
     */
    void insertList(List<MemberErrorXsl> errorXsls);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param page 主键
    * @return   影响行数
     */
    List<MemberErrorXsl> selectByPage(Page<MemberErrorXsl> page);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月5日 下午4:15:04
    * @param storeId 主键
    * @return   影响行数
     */
    List<MemberErrorXsl> selectAll(Integer storeId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param storeId  主键
    * @return    影响行数
     */
    MemberErrorXsl selectStoreMemberAmount(Integer storeId);
}