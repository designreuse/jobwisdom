package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.MemberSubAccountDto;
import com.zefun.web.entity.MemberSubAccount;

/**
 * 会员子账户操作类
 * 
 * @author 张进军
 * @date Mar 12, 2016 2:05:34 PM
 */
public interface MemberSubAccountMapper {

	/**
	 * 修改子账户金额
	 * 
	 * @param map
	 *            参数
	 * @return 是否成功
	 */
	int updateCharge(Map<String, Object> map);

	/**
	 * 根据子账户标识删除子账户
	 * 
	 * @author 张进军
	 * @date Mar 12, 2016 2:05:47 PM
	 * @param subAccountId
	 *            子账户标识
	 * @return 0:失败，1:成功
	 */
	int deleteByPrimaryKey(Integer subAccountId);

	/**
	 * 新增子账户
	 * 
	 * @author 张进军
	 * @date Mar 12, 2016 2:06:06 PM
	 * @param record
	 *            子账户
	 * @return 0:失败，1:成功
	 */
	int insert(MemberSubAccount record);

	/**
	 * 根据子账户标识查询子账户信息
	 * 
	 * @author 张进军
	 * @date Mar 12, 2016 2:06:35 PM
	 * @param subAccountId
	 *            子账户标识
	 * @return 子账户信息
	 */
	MemberSubAccount selectByPrimaryKey(Integer subAccountId);

	/**
	 * 修改子账户信息
	 * 
	 * @author 张进军
	 * @date Mar 12, 2016 2:06:22 PM
	 * @param record
	 *            子账户信息
	 * @return 0:失败，1:成功
	 */
	int updateByPrimaryKey(MemberSubAccount record);

	/**
	 * 根据账户ID查询子账户列表
	 * 
	 * @param map
	 *            账户ID
	 * @return 子账户列表
	 */
	List<MemberSubAccountDto> selectSubAccountListByAccountId(Map<String, Integer> map);

	/**
	 * 根据子账户ID查询子账户信息
	 * 
	 * @param subAccountId
	 *            子账户ID
	 * @return 子账户信息
	 */
	MemberSubAccountDto selectSubAccountBySubAccountId(int subAccountId);

	/**
	 * 消费扣款
	 * 
	 * @author 张进军
	 * @date Mar 17, 2016 7:28:54 PM
	 * @param map
	 *            子账户标识、扣款金额、扣款时间
	 * @return 0:失败，1:成功
	 */
	int updateBalanceAmountForConsume(Map<String, Object> map);

	/**
	 * 账户金额增加
	 * 
	 * @param map
	 *            参数
	 * @return 是否成功
	 */
	int updateAdd(Map<String, Object> map);

	/**
	 * 账户金额减少
	 * 
	 * @param map
	 *            参数
	 * @return 是否成功
	 */
	int updateDecrease(Map<String, Object> map);
	
	/**
	 * 根据会员标识查询会员卡中无余额的子账户标识列表
	* @author 张进军
	* @date Mar 21, 2016 12:15:41 AM
	* @param accountId 会员标识
	* @return  无余额的子账户标识列表
	 */
	List<Integer> selectNoMoneyAccountId(Integer accountId);
}