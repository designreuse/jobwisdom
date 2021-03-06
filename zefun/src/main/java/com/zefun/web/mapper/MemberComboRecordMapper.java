package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.MemberComboDto;
import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.entity.Page;

/**
 *  会员疗程记录表
* @author 张进军
* @date Oct 23, 2015 11:14:27 AM
 */
public interface MemberComboRecordMapper {
    /**
     * 根据记录标识删除会员疗程记录
    * @author 张进军
    * @date Oct 23, 2015 11:14:37 AM
    * @param recordId   会员疗程记录
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer recordId);

    /**
     * 新增会员疗程记录
    * @author 张进军
    * @date Oct 23, 2015 11:14:51 AM
    * @param record     会员疗程记录
    * @return   0:失败，1:成功
     */
    int insert(MemberComboRecord record);

    /**
     * 根据记录标识查询会员疗程记录
    * @author 张进军
    * @date Oct 23, 2015 11:15:01 AM
    * @param recordId   会员疗程标识
    * @return   会员疗程记录
     */
    MemberComboRecord selectByPrimaryKey(Integer recordId);

    /**
     * 修改会员疗程记录
    * @author 张进军
    * @date Oct 23, 2015 11:15:16 AM
    * @param record     会员疗程记录
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(MemberComboRecord record);

    /**
     * 根据会员标识查询疗程记录
    * @author 张进军
    * @date Oct 24, 2015 11:02:21 AM
    * @param memberId   会员标识
    * @return   疗程记录
     */
    List<MemberComboDto> selectComboListByMemberId(int memberId);

    /**
     * 根据订单明细标识查询
    * @author 王大爷
    * @date 2015年12月15日 下午4:37:47
    * @param detailId 订单明细标识
    * @return MemberComboDto
     */
    MemberComboDto selectComboListByDetailId(int detailId);
    
    
    /**
     * 新增会员疗程
     * @param map map
     * @return 新增记录数
     */
    int insertSelfCashierComboByComboId(Map<String, Integer> map);

    /**
     * 更新会员疗程信息
     * @param map map
     * @return 更新记录数
     */
    int updateOrderComboAmount(Map<String, Integer> map);

    /**
     * 根据疗程ID查询关联信息
    * @author 高国藩
    * @date 2015年11月21日 下午3:08:09
    * @param comboId 疗程ID
    * @return 实体
     */
    List<MemberComboRecord> selectByComboId(Integer comboId);
    
    /**
     * 会员购买的疗程分页查询
    * @author 王大爷
    * @date 2015年12月1日 上午10:29:02
    * @param page 参数
    * @return List<MemberComboDto>
     */
    List<MemberComboDto> selectComboListByMemberIdPage(Page<MemberComboDto> page);

    /**
     * 根据疗程标识列表删除会员疗程记录(清除测试数据)
    * @author 张进军
    * @date Dec 25, 2015 9:47:00 PM
    * @param comboIdList	疗程标识列表
    * @return	删除数量
     */
    int deleteByComboIdList(List<Integer> comboIdList);
    
    /**
     * 根据疗程标识查询疗程有效个数
    * @author 王大爷
    * @date 2016年1月26日 上午11:32:26
    * @param comboId 疗程标识
    * @return Integer
     */
    Integer selectValidByComboId(Integer comboId);
}