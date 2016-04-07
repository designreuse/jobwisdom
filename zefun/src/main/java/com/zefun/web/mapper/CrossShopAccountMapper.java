package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.DifferentStoreMemberConsumeDto;
import com.zefun.web.dto.ReconciliationDataDto;
import com.zefun.web.dto.ReconciliationOrderDetailDto;
import com.zefun.web.dto.StoreReconciliation;
import com.zefun.web.entity.CrossShopAccount;

/**
* @author 乐建建
* @date 2016年3月1日 下午2:00:04 
*/
public interface CrossShopAccountMapper {
    
    /**
     * 删除
    * @author 乐建建
    * @date 2016年3月1日 下午2:02:38
    * @param crossShopId 跨店对账表的明细id
    * @return 成功删除的行数
    */
    int deleteByPrimaryKey(Integer crossShopId);
    
    /**
     * 新增
    * @author 乐建建
    * @date 2015年9月10日 下午7:44:05
    * @param record 账户充值记录
    * @return 新增成功的行数
     */
    int insert(CrossShopAccount record);
    
    /**
     * 查询
    * @author 乐建建
    * @date 2015年9月10日 下午7:44:05
    * @param crossShopId 跨店对账表的明细id
    * @return 返回特定的crossShopId对应的一个实体
     */
    CrossShopAccount selectByPrimaryKey(String crossShopId);
    
    /**
     * 修改
    * @author 乐建建
    * @date 2015年9月10日 下午7:44:05
    * @param record 账户充值记录
    * @return 更新成功的行数
     */
    int updateByPrimaryKeySelective(CrossShopAccount record);
    
    /**
    * @author 乐建建
    * @date 2016年3月1日 下午7:46:14
    * @param conditions 查询条件
    * @return 查询同一总店下不同分店相互消费的会员数据
    */
    List<StoreReconciliation> selectDifferentStoreMemberConsumed(Map<String, Object> conditions);
    
    
    /**
    * @author 乐建建
    * @date 2016年3月1日 下午8:50:30
    * @param conditions 输入条件
    * @return 不同分店的会员相互消费的数据
    */
    DifferentStoreMemberConsumeDto getOtherToMeResult(Map<String, Object> conditions);
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 上午10:59:20
    * @param conditions 输入条件
    * @return 跨店消费的明细数据
    */
    List<ReconciliationOrderDetailDto> getCrossShopConsumedDetail(Map<String, Object> conditions);
    
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 下午3:52:40
    * @param conditions 查询条件
    * @return 跨店消费的会员id
    */
    List<Integer> selectMembersByStoreId(Map<String, Object> conditions);
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 下午8:13:13
    * @param conditions 查询条件
    * @return 跨店消费的明细id
    */
    List<String> selectDetailIdByMemberId(Map<String, Object> conditions);
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 下午8:22:09
    * @param detailIds 明细id
    * @return 统计已审核的结果
    */
    ReconciliationDataDto getDetailReconciliationResult(List<String> detailIds);
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 下午4:32:59
    * @param record 一条跨店对账明细
    * @return 成功插入的行数
    */
    int insertIfNotExist(CrossShopAccount record);
    
    /**
    * @author 乐建建
    * @date 2016年3月3日 下午3:41:57
    * @param conditions 查询条件
    * @return 充值会员的数量
    */
    int getMemberChargeCnt(Map<String, Object> conditions);
    
    /**
    * @author 乐建建
    * @date 2016年3月3日 下午3:42:57
    * @param conditions 查询条件
    * @return 得到会员刷卡的数量
    */
    int getMemberCardCnt(Map<String, Object> conditions);
}
