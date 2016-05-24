package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.entity.StoreInfo;
import com.zefun.wechat.dto.RegionCountDto;

/**
 * 门店信息
 * 
 * @author 洪秋霞
 * @date 2015年8月13日 下午6:39:33
 */
public interface StoreInfoMapper {
	/**
	 * 删除
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月13日 下午6:39:47
	 * @param storeId
	 *            门店id
	 * @return int
	 */
	int deleteByPrimaryKey(Integer storeId);

	/**
	 * 插入
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月13日 下午6:40:38
	 * @param storeInfo
	 *            门店信息
	 * @return int
	 */
	int insert(StoreInfo storeInfo);

	/**
	 * 查询
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月13日 下午6:40:56
	 * @param storeId
	 *            门店id
	 * @return StoreInfo
	 */
	StoreInfo selectByPrimaryKey(@Param(value = "storeId") Integer storeId);

	/**
	 * 更新
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月13日 下午6:41:07
	 * @param storeInfo
	 *            门店信息
	 * @return int
	 */
	int updateByPrimaryKey(StoreInfo storeInfo);

	/**
	 * 根据门店标识查询门店基础信息
	 * 
	 * @author 张进军
	 * @date Nov 1, 2015 12:40:32 AM
	 * @param storeId
	 *            门店标识
	 * @return 门店基础信息
	 */
	StoreInfo selectBaseInfoByStoreId(int storeId);

	/**
	 * 根据门店查询门店介绍
	 * 
	 * @author 张进军
	 * @date Nov 1, 2015 1:19:34 AM
	 * @param storeId
	 *            门店标识
	 * @return 门店介绍
	 */
	String selectDescByStoreId(int storeId);

	/**
	 * 根据门店查询技术展示
	 * 
	 * @author 张进军
	 * @date Nov 1, 2015 1:19:34 AM
	 * @param storeId
	 *            技术展示
	 * @return 技术展示
	 */
	String selectTechnicalByStoreId(int storeId);

	/**
	 * 根据门店查询特色服务
	 * 
	 * @author 张进军
	 * @date Nov 1, 2015 1:19:34 AM
	 * @param storeId
	 *            特色服务
	 * @return 特色服务
	 */
	String selectCharacteristicByStoreId(int storeId);

	/**
	 * 根据总店标识查询所有分店信息，不包括自己
	 * 
	 * @author 张进军
	 * @date Nov 19, 2015 5:46:21 PM
	 * @param mainStoreId
	 *            总店标识
	 * @return 分店列表
	 */
	List<StoreInfo> selectBaseInfoByMainId(int mainStoreId);

	/**
	 * 根据总店id查询旗下的分店数量
	 * 
	 * @param hqStoreId
	 *            总店id
	 * @return 分店数量
	 */
	int countByHQStoreId(Integer hqStoreId);

	/**
	 * 根据总店id查询旗下所有分店
	 * 
	 * @param hqStoreId
	 *            总店id
	 * @return 总店id查询旗下所有分店
	 */
	List<StoreInfo> selectChainsByHQStoreId(Integer hqStoreId);

	/**
	 * 根据多个门店id查询门店账号
	 * 
	 * @author gebing
	 * @date 2015年12月4日
	 * @param storeIds
	 *            多个门店id
	 * @return 多个门店账号
	 */
	List<StoreInfo> selectByStoreIds(List<Integer> storeIds);

	/**
	 * 根据门店标识查询总店，当店/总店返回自身
	 * 
	 * @author 张进军
	 * @date Nov 25, 2015 10:02:17 PM
	 * @param storeId
	 *            门店标识
	 * @return 总店标识
	 */
	int selectMainIdByStoreId(int storeId);

	/**
	 * 查询所有门店标识
	 * 
	 * @author 王大爷
	 * @date 2016年1月19日 上午10:41:00
	 * @return List<Integer>
	 */
	List<Integer> selectStoreIdAll();

	/**
	 * 根据地区统计装店数
	 * 
	 * @author gebing
	 * @date 2016年3月17日
	 * @param params
	 *            参数
	 * @return 各个地区装店数
	 */
	List<RegionCountDto> countByRegion(Map<String, String> params);

	/**
	 * 根据门店id和门店名称查询门店
	 * 
	 * @author gebing
	 * @date 2016年3月18日
	 * @param prams
	 *            参数
	 * @return 门店集合, 根据传入的门店id排序
	 */
	List<StoreInfo> selectOrderedStore(Map<String, Object> prams);

	/**
	 * 根据门店id查询门店的微信会员数
	 * 
	 * @author gebing
	 * @date 2016年3月18日
	 * @param storeIds
	 *            门店id
	 * @return 会员数
	 */
	int countWechatByIds(List<Integer> storeIds);

	/**
	 * 判断门店代号是否存在
	 * 
	 * @param storeAccount
	 *            门店代号
	 * @return 数量
	 */
	int isExitsStoreAccount(String storeAccount);
	
	/**
	 * 根据企业代号查出企业下所有门店
	* @author 老王
	* @date 2016年5月24日 上午12:06:39 
	* @param storeAccount 企业代号
	* @return List<StoreInfo>
	 */
	List<StoreInfo> selectByStoreAccount(String storeAccount);
}
