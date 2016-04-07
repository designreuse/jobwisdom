package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorGi;
import com.zefun.web.entity.Page;

/**
 * 共赢会员数据
* @author 高国藩
* @date 2016年3月2日 下午2:18:45
 */
public interface MemberErrorGiMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月2日 下午2:19:29
    * @param id  主键
    * @return    影响哈数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月2日 下午2:19:29
    * @param record  主键
    * @return    影响哈数
     */
    int insert(MemberErrorGi record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月2日 下午2:19:29
    * @param record  主键
    * @return    影响哈数
     */
    int insertSelective(MemberErrorGi record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月2日 下午2:19:29
    * @param id  主键
    * @return    影响哈数
     */
    MemberErrorGi selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月2日 下午2:19:29
    * @param record  主键
    * @return    影响哈数
     */
    int updateByPrimaryKeySelective(MemberErrorGi record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月2日 下午2:19:29
    * @param record  主键
    * @return    影响哈数
     */
    int updateByPrimaryKey(MemberErrorGi record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月2日 下午2:19:29
    * @param errorGis  主键
     */
    void insertList(List<MemberErrorGi> errorGis);
    
    /**
     * 分页查询
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param page 分页
    * @return     状态行数
     */
    List<MemberErrorGi> selectByPage(Page<MemberErrorGi> page);
    /**
     * 查询所有
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param storeId   门店
    * @return          集合数据
     */
    List<MemberErrorGi> selectAll(Integer storeId);
    
    /**
     * 异常会员数据统计
    * @author 高国藩
    * @date 2015年12月21日 上午11:30:06
    * @param storeId    门店信息
    * @return           异常数据
     */
    MemberErrorGi selectStoreMemberAmount(Integer storeId);
}