package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.UserAccountDto;
import com.zefun.web.entity.UserAccount;

/**
 * 用户信息
 *
 * @author 洪秋霞
 * @date 2015年8月13日 下午6:45:25
 */
public interface UserAccountMapper {
	/**
	 * 删除
	 *
	 * @author 洪秋霞
	 * @date 2015年8月13日 下午6:45:34
	 * @param userId
	 *            用户id
	 * @return int
	 */
	int deleteByPrimaryKey(Integer userId);

	/**
	 * 插入
	 *
	 * @author 洪秋霞
	 * @date 2015年8月13日 下午6:45:46
	 * @param userAccount
	 *            用户信息
	 * @return int
	 */
	int insert(UserAccount userAccount);

	/**
	 * 查询
	 *
	 * @author 洪秋霞
	 * @date 2015年8月13日 下午6:45:58
	 * @param userId
	 *            用户id
	 * @return UserInfo
	 */
	UserAccount selectByPrimaryKey(Integer userId);

	/**
	 * 根据用户账号查询用户账户信息
	 *
	 * @author 张进军
	 * @date Sep 18, 2015 7:35:11 PM
	 * @param mapUser
	 *            用户账号
	 * @return 用户账户信息
	 */
	UserAccount selectByUserName(Map<String, String> mapUser);
	
	/**
	 * 查询该企业下的管理员
	* @author 骆峰
	* @date 2016年7月25日 上午11:39:26
	* @param mapUser mapUser
	* @return List
	 */
	List<UserAccountDto> selectByAccout(Map<String, Object> mapUser);

	/**
	 * 更新
	 *
	 * @author 洪秋霞
	 * @date 2015年8月13日 下午6:46:12
	 * @param userInfo
	 *            用户信息
	 * @return int
	 */
	int updateByPrimaryKey(UserAccount userInfo);

	/**
	 * 判断账号是否已经被引用了
	 *
	 * @author chendb
	 * @date 2015年10月9日 下午4:08:39
	 * @param userInfo
	 *            参数
	 * @return int
	 */
	int countUserName(UserAccount userInfo);

	/**
	 * 修改账户角色
	 *
	 * @author chendb
	 * @date 2015年10月9日 下午5:29:09
	 * @param userInfo
	 *            参数
	 * @return int
	 */
	int updateUserRole(UserAccount userInfo);

	/**
	 * 获取员工角色id
	 *
	 * @author chendb
	 * @date 2015年10月9日 下午5:41:47
	 * @param userId
	 *            标识
	 * @return int
	 */
	UserAccount queryRoleId(Integer userId);

	/**
	 * 批量导入
	 *
	 * @author chendb
	 * @date 2015年10月17日 下午1:45:34
	 * @param list
	 *            参数
	 * @return int
	 */
	int insertlist(EmployeeDto list);

	/**
	 * 根据门店id查询门店操作员账号
	 *
	 * @author gebing
	 * @date 2015年12月4日
	 * @param storeId
	 *            门店id
	 * @return 用户账号
	 */
	UserAccount selectSingleStoreAccount(Integer storeId);

	/**
	 * 
	 * getSingleStoreAccount:(这里用一句话描述这个方法的作用).
	 * 
	 * @author michael
	 * @param storeId
	 *            商店id
	 * @return UserAccount list
	 * @since JDK 1.8
	 */
	List<UserAccount> getSingleStoreAccount(Integer storeId);

	/**
	 * 根据门店id查询分店的用户账号
	 * 
	 * @author gebing
	 * @date 2015年12月4日
	 * @param storeId
	 *            门店id
	 * @return 用户账号
	 */
	UserAccount selectChainStoreAccount(Integer storeId);

	/**
	 * 根据门店id查询总店的用户账号
	 * 
	 * @author gebing
	 * @date 2015年12月4日
	 * @param storeId
	 *            门店id
	 * @return 用户账号
	 */
	UserAccount selectChainHQStoreAccount(Integer storeId);

	/**
	 * 根据门店id查询该门店下用户账号数
	 * 
	 * @param storeId
	 *            门店id
	 * @return 门店下用户账号数
	 */
	int countByStoreId(Integer storeId);

	/**
	 * 给定账户,查询微信openId
	 * 
	 * @author 高国藩
	 * @date 2015年12月21日 下午7:13:54
	 * @param userName
	 *            账户
	 * @return openId
	 */
	String selectTouserByUserName(String userName);

	/**
	 * 根据用户账户查询存在的账户数量
	 * 
	 * @author 张进军
	 * @date Feb 22, 2016 2:08:38 PM
	 * @param userName
	 *            账户名
	 * @return 账户数量
	 */
	int selectUserCountByUserName(String userName);

	/**
	 * 根据门店标识、角色标识查询账户用户名
	 * 
	 * @author 张进军
	 * @date Feb 23, 2016 11:34:19 AM
	 * @param map
	 *            门店标识、角色标识
	 * @return 账户用户名
	 */
	String selectUserNameByStoreIdAndRoleId(Map<String, Integer> map);
	
	/**
	 *  企业下的角色引用
	* @author 骆峰
	* @date 2016年7月26日 下午7:34:05
	* @param map map
	* @return UserAccount
	 */
	List<UserAccount> selectStoreAccountRole (Map<String, Object> map);
	
	/**
	 *    根据企业代号查询出该企业的数据
	* @author 骆峰
	* @date 2016年6月28日 下午6:59:59
	* @param userAccount userAccount
	* @return UserAccount
	 */
	UserAccount selectTouserByUserAccount(UserAccount userAccount);
	
	/**
	 *  修改状态
	* @author 骆峰
	* @date 2016年6月28日 下午8:10:09
	* @param userAccount userAccount
	* @return  int
	 */
	int updateDisableAndStart(UserAccount userAccount);
}
