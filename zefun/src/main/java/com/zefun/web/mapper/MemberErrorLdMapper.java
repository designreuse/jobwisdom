package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorLd;
import com.zefun.web.entity.Page;

/**
 * 蓝蝶会员导入处理项
* @author 高国藩
* @date 2016年2月22日 下午3:48:38
 */
public interface MemberErrorLdMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param id    主键
    * @return      影响行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param record    主键
    * @return      影响行数
     */
    int insert(MemberErrorLd record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param record    主键
    * @return      影响行数
     */
    int insertSelective(MemberErrorLd record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param id    主键
    * @return      影响行数
     */
    MemberErrorLd selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param record    主键
    * @return      影响行数
     */
    int updateByPrimaryKeySelective(MemberErrorLd record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param record    主键
    * @return      影响行数
     */
    int updateByPrimaryKey(MemberErrorLd record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param errorLds    主键
     */
    void insertList(List<MemberErrorLd> errorLds);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param page    主键
    * @return      影响行数
     */
    List<MemberErrorLd> selectByPage(Page<MemberErrorLd> page);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年2月22日 下午3:48:32
    * @param storeId    主键
    * @return      影响行数
     */
    MemberErrorLd selectStoreMemberAmount(Integer storeId);
    /**
     * 查看
    * @author 高国藩
    * @date 2016年2月22日 下午5:30:17
    * @param storeId   门店
    * @return          集合
     */
    List<MemberErrorLd> selectAll(Integer storeId);
}