package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.EmployeeRewardDto;
import com.zefun.web.entity.EmployeeReward;
import com.zefun.web.entity.Page;

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
     * 根据员工id和类型分组查询奖惩记录汇总(全查询)
    * @author DavidLiang
    * @date 2016年1月16日 下午8:51:19
    * @param map  参加集
    * @return  奖惩集EmployeeReward
     */
    List<EmployeeRewardDto> selectAllCountReward(Map<String, Object> map);
    
    /**
     * 根据员工id和类型分组查询奖惩记录汇总(分页查询)
     * @param page 页查询条件
     * @return  奖惩集EmployeeRewardDto
     */
    List<EmployeeRewardDto> selectCountRewardByPage(Page<EmployeeRewardDto> page);
    /**
     *  几个员工
    * @author 骆峰
    * @date 2016年8月4日 下午5:44:16
    * @param params params
    * @return List
     */
    List<EmployeeRewardDto> selectCountRewardByGroupBy(Map<String, Object> params);
    
    /**
     * 分页查询奖惩详细
     * @param page  页查询条件
     * @return  奖惩集EmployeeReward
     */
    List<EmployeeReward> selectRewardByPage(Page<EmployeeReward> page);
    
    /**
     * 根据员工标识列表删除员工惩罚记录
    * @author 张进军
    * @date Dec 25, 2015 10:16:28 PM
    * @param employeeIdList	员工标识列表
    * @return	删除数量
     */
    int deleteByEmployeeIdList(List<Integer> employeeIdList);
    
    /**
     * 根据员工id和奖惩类型和奖惩日期查询奖惩记录
    * @author DavidLiang
    * @date 2016年1月20日 下午5:36:32
    * @param employeeId  员工id
    * @param type  奖惩类型
    * @param date  奖惩日期
    * @return  员工奖惩实例
     */
    EmployeeReward selectRewardByEmployeeIdAndTypeAndDate(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "type")String type, @Param(value = "date")String date);
    
    /**
     * 查询员工各类型汇总(根据员工id和日期)
    * @author DavidLiang
    * @date 2016年1月28日 下午8:05:20
    * @param employeeId  员工id
    * @param time  查询日期
    * @return  员工各类型汇总
     */
    List<Map<String, Object>> selectRewardCountGroupByType(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
}