package com.zefun.web.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zefun.web.dto.BigMemberDto;
import com.zefun.web.entity.MemberAccount;

/**
 * 会员账户表
* @author 王大爷
* @date 2015年9月9日 下午4:48:16
 */
public interface MemberAccountMapper {
    /**
     * 删除会员账户表
    * @author 王大爷
    * @date 2015年9月9日 下午4:48:09
    * @param accountId 账户标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer accountId);

    /**
     * 新增会员账户
    * @author 王大爷
    * @date 2015年9月9日 下午4:49:08
    * @param record 会员账户表
    * @return 是否成功
     */
    int insert(MemberAccount record);

    /**
     * 根据会员账户标识
    * @author 王大爷
    * @date 2015年9月9日 下午4:49:40
    * @param accountId   账户标识
    * @return MemberAccount
     */
    MemberAccount selectByPrimaryKey(Integer accountId);

    /**
     * 修改会员账户
    * @author 王大爷
    * @date 2015年9月9日 下午4:49:43
    * @param record 会员账户表
    * @return 是否成功
     */
    int updateByPrimaryKey(MemberAccount record);

    /**
     * 充值后修改账户表
    * @author 王大爷
    * @date 2015年9月10日 下午8:03:40
    * @param hashMap hashMap
    * @return 是否成功
     */
    int updateCharge(Map<String, Object> hashMap);

    /**
     * 转账 +
    * @author 王大爷
    * @date 2015年9月11日 下午3:46:49
    * @param hashMap hashMap
    * @return 是否成功
     */
    int updateAdd(Map<String, Object> hashMap);

    /**
     * 转账 -
    * @author 王大爷
    * @date 2015年9月11日 下午3:46:49
    * @param hashMap hashMap
    * @return 是否成功
     */
    int updateDecrease(Map<String, Object> hashMap);

    /**
     * 自助收银
     * @param hashMap hashMap
     * @return 是否成功
     */
    int updateMemberCashier(Map<String, Object> hashMap);

    /**
     * 结账时更新礼金余额
    * @author 张进军
    * @date Nov 21, 2015 7:52:30 PM
    * @param hashMap    更新参数
    * @return   0:失败，1:成功
     */
    int updateMemberGiftMoney(Map<String, Object> hashMap);

    /**
     * 增加礼金账户储值金额
    * @author 王大爷
    * @date 2015年11月21日 下午1:57:09
    * @param hashMap 参数
    * @return 是否成功
     */
    int updateGiftmoney(Map<String, Object> hashMap);

    /**
     * 根据会员标识列表删除会员储值账户
    * @author 张进军
    * @date Dec 25, 2015 9:09:45 PM
    * @param memberIdList	会员标识列表
    * @return	删除数量
     */
    int deleteByMemberIdList(List<Integer> memberIdList);

    /**
     * 根据会员id查询会员账户信息
     * @author gebing
     * @date 2016年1月11日
     * @param accountIds 会员id集合
     * @return 会员账户信息
     */
    List<MemberAccount> selectByAccountIds(List<Integer> accountIds);

    /**
     * 根据门店标识查询剩余挂账金额
    * @author 王大爷
    * @date 2016年1月18日 上午10:46:24
    * @param storeId 门店标识
    * @return BigDecimal
     */
    BigDecimal selectDebtAmountByStoreId(Integer storeId);
    
    /**
     * 根据门店标识查询会员储值情况
    * @author 王大爷
    * @date 2016年1月20日 下午3:50:30
    * @param storeId 门店标识
    * @return Map<String, Object>
     */
    Map<String, Object> selectMemberUserByStoreId(Integer storeId);
    
    /**
     * 大客户分析
    * @author 老王
    * @date 2016年7月29日 下午4:27:01 
    * @param map 门店标识
    * @return List<Map<String, Object>>
     */
    List<BigMemberDto> selectBigMember(Map<String, Object> map);
    
}
