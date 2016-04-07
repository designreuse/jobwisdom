package com.zefun.web.mapper;

import java.util.Map;

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
    * @param accountId 账户标识
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
     * 根据会员信息标识查询会员帐号
    * @author 王大爷
    * @date 2015年9月12日 下午4:03:38
    * @param memberId 会员信息标识
    * @return 会员账户信息
     */
    MemberAccount selectByMemberId(Integer memberId);

    /**
     * 每个月月初将每个会员的次数修改为4
    * @author 高国藩
    * @date 2015年10月7日 下午4:25:43
     */
    void updateWechatCount();
}