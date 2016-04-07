package com.zefun.api.mapper;

import java.util.Map;

import com.zefun.api.entity.EmployeeReward;

/**
 * 员工奖惩映射
 * @author lzc
 *
 */
public interface EmployeeRewardMapper {
	/**
	 * 删除根据主键
	 * @param rewardId 主键
	 * @return 成功数目
	 */
    int deleteByPrimaryKey(Integer rewardId);

    /**
     * 插入
     * @param record 员工奖惩实体
     * @return 成功数目
     */
    int insert(EmployeeReward record);

    /**
     * 选择性插入
     * @param record 员工奖惩实体
     * @return 成功数目
     */
    int insertSelective(EmployeeReward record);

    /**
     * 根据主键查询
     * @param rewardId 主键
     * @return 员工奖惩实体
     */
    EmployeeReward selectByPrimaryKey(Integer rewardId);

    /**
     * 根据主键选择性更新
     * @param record 员工奖惩实体
     * @return 成功数目
     */
    int updateByPrimaryKeySelective(EmployeeReward record);

    /**
     * 根据主键更新
     * @param record 员工奖惩实体
     * @return 成功数目
     */
    int updateByPrimaryKey(EmployeeReward record);
    
    /**
     * 根据员工id, 时间, 类型查询员工奖惩记录
    * @author DavidLiang
    * @date 2016年1月18日 下午2:57:53
    * @param map  员工id、类型、时间
    * @return  员工奖惩记录
     */
    EmployeeReward selectRewardByEmployeeIdAndTimeAndType(Map<String, Object> map);
}