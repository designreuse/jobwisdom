package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorMb;
import com.zefun.web.entity.Page;

/**
 * 模板会员导出数据
* @author 高国藩
* @date 2016年3月4日 下午2:29:30
 */
public interface MemberErrorMbMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param id  主键
    * @return    影响行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param record  主键
    * @return    影响行数
     */
    int insert(MemberErrorMb record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param record  主键
    * @return    影响行数
     */
    int insertSelective(MemberErrorMb record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param id  主键
    * @return    影响行数
     */
    MemberErrorMb selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param record  主键
    * @return    影响行数
     */
    int updateByPrimaryKeySelective(MemberErrorMb record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param record  主键
    * @return    影响行数
     */
    int updateByPrimaryKey(MemberErrorMb record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param errorMbs  主键
     */
    void insertList(List<MemberErrorMb> errorMbs);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param page  主键
    * @return    影响行数
     */
    List<MemberErrorMb> selectByPage(Page<MemberErrorMb> page);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年3月4日 下午2:36:55
    * @param storeId  主键
    * @return    影响行数
     */
    MemberErrorMb selectStoreMemberAmount(Integer storeId);
}