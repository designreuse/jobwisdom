package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.YzmPageQiniu;
/**
 * 
* @author 老王
* @date 2016年4月28日 下午12:04:49
 */
public interface YzmPageQiniuMapper {
	/**
	 * 删除
	* @author 老王
	* @date 2016年4月28日 下午12:03:02 
	* @param yzmPageId 验证码id
	* @return int
	 */
	int deleteByPrimaryKey(Integer yzmPageId);

	/**
	 * 
	* @author 老王
	* @date 2016年4月28日 下午12:03:26 
	* @param record YzmPageQiniu
	* @return int
	 */
	int insert(YzmPageQiniu record);

	/**
	 * 
	* @author 老王
	* @date 2016年4月28日 下午12:03:30 
	* @param record YzmPageQiniu
	* @return int
	 */
	int insertSelective(YzmPageQiniu record);

	/**
	 * 
	* @author 老王
	* @date 2016年4月28日 下午12:03:33 
	* @param yzmPageId 验证码id
	* @return YzmPageQiniu
	 */
	YzmPageQiniu selectByPrimaryKey(Integer yzmPageId);

	/**
	 * 
	* @author 老王
	* @date 2016年4月28日 下午12:03:37 
	* @param record YzmPageQiniu
	* @return int
	 */
	int updateByPrimaryKeySelective(YzmPageQiniu record);

	/**
	 * 修改
	* @author 老王
	* @date 2016年4月28日 下午12:03:40 
	* @param record YzmPageQiniu
	* @return int
	 */
	int updateByPrimaryKey(YzmPageQiniu record);

	/**
	 *  查询所有验证码信息
	* @author 老王
	* @date 2016年4月28日 下午12:03:45 
	* @return List<YzmPageQiniu>
	 */
	List<YzmPageQiniu> selectByAll();
}