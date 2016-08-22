package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.MemberComboProjectDto;
import com.zefun.web.dto.PaymentOffDto;
import com.zefun.web.entity.MemberComboProject;

/**
 * 会员疗程项目数据操作对象
* @author 张进军
* @date Oct 23, 2015 11:11:27 AM
 */
public interface MemberComboProjectMapper {
    /**
     * 新增会员疗程项目记录
    * @author 张进军
    * @date Oct 23, 2015 11:11:50 AM
    * @param record 会员疗程项目记录
    * @return   0:失败，1:成功
     */
    int insert(MemberComboProject record);

    
    /**
     * 修改会员疗程项目记录
    * @author 张进军
    * @date Oct 23, 2015 11:11:59 AM
    * @param record 会员疗程项目记录
    * @return       0:失败，1:成功
     */
    int updateByPrimaryKey(MemberComboProject record);
    
    /**
     * 插入疗程详情
     * @param map map
     * @return 新增数据条数
     */
    int insertSelfCashierComboDetail(Map<String, Integer> map);
    
    /**
     * 更新会员疗程信息
     * @param map map
     * @return 更新记录数
     */
    int updateSelfCashierCombo(Map<String, Object> map);
    
    /**
     * 增加会员疗程信息
    * @author 王大爷
    * @date 2015年12月15日 下午3:27:51
    * @param map 参数
    * @return 更新记录数
     */
    int updateAddComboNum(Map<String, Object> map);
    
    /**
     * 根据疗程明细标示修改
     * @param record record
     * @return int
     */
    int updateByKey(MemberComboProject record);
    
    /**
     * 根据疗程项目明细标识查询疗程标识
    * @author 张进军
    * @date Dec 4, 2015 11:16:39 AM
    * @param detailId  detailId
    * @return   疗程标识
     */
    Integer selectComboIdByDetailId(Integer detailId);
    
    
    
    /**
     *  疗程报表1
    * @author 骆峰
    * @date 2016年8月22日 上午10:41:30
    * @param map map
    * @return List<MemberComboProjectDto>
     */
    List<MemberComboProjectDto> selectProjectListByDto(Map<String, Object> map);
    
    /**
     * 根据会员标识跟项目标识查询疗程优惠列表
    * @author 张进军
    * @date Nov 10, 2015 5:32:07 PM
    * @param map    会员标识、项目标识
    * @return   疗程优惠列表
     */
    List<PaymentOffDto> selectPaymentOffListByMemberIdAndProjectId(Map<String, Integer> map);
    
    /**
     * 根据疗程标识列表删除会员疗程记录(清除测试数据)
    * @author 张进军
    * @date Dec 25, 2015 9:47:00 PM
    * @param comboIdList	疗程标识列表
    * @return	删除数量
     */
    int deleteByComboIdList(List<Integer> comboIdList);
}