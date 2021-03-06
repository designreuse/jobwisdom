package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.EmployeeLevelBaseDto;
import com.zefun.web.dto.EmployeeLevelDto;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.entity.Page;
/**
 * 职位信息
* @author 陈端斌
* @date 2015年8月5日 下午1:31:44 
*
 */
public interface EmployeeLevelMapper {
    /**
     * 新增功能
    * @author chendb
    * @date 2015年8月11日 上午10:55:27
    * @param employeeLevel bean
    * @return int
     */
    int insert(EmployeeLevel employeeLevel);
    /**
     * 修改
    * @author chendb
    * @date 2015年8月11日 上午10:55:50
    * @param employeeLevel employeeLevel
    * @return int
     */
    int updateByPrimaryKeySelective(EmployeeLevel employeeLevel);
    /**
     * 
    * @author chendb
    * @date 2015年8月11日 上午10:56:20
    * @param page 参数
    * @return List<EmployeeLevelDto>
     */
    List<EmployeeLevelDto> queryEmployeelevelinfo(Page<EmployeeLevelDto> page);
    /**
     * 删除功能
    * @author chendb
    * @date 2015年8月11日 上午10:56:57
    * @param record bean
    * @return int
     */
    int deleteEmployeelevel(EmployeeLevel record);
    /**
     * 判断职位名称是否已经存在了
    * @author chendb
    * @date 2015年8月11日 上午10:58:14
    * @param employeeLevel bean
    * @return List<EmployeeLevel>
     */
    List<EmployeeLevel> islevelName(EmployeeLevel employeeLevel);
    /**
     * 判断职位是否被员工引用了
    * @author chendb
    * @date 2015年8月11日 上午10:58:39
    * @param record bean
    * @return int
     */
    int islevelIdcount(EmployeeLevel record);
    /**
     * 下拉框公共获取职位信息 
    * @author chendb
    * @date 2015年8月11日 上午11:00:22
    * @param record bean
    * @return List<EmployeeLevel>
     */
    List<EmployeeLevel> queryEmployeeLevel(EmployeeLevel record);
    /**
     * 职位详情
    * @author chendb
    * @date 2015年8月11日 上午11:07:20
    * @param levelId 职位标识
    * @return EmployeeLevelDto
     */
    EmployeeLevelDto leveldetail(Integer levelId);
    
    
    /**
     * 根据级别id，复合查询出员工信息
    * @author laowang
    * @date 2015年8月8日 下午5:43:01
    * @param record bean
    * @return List<EmployeeLevelDto>
     */
    List<EmployeeLevelDto> selectByKey(EmployeeLevel record);
    /**
     * 根据职位标识查询出职位底下的员工
    * @author chendb
    * @date 2015年9月9日 上午10:01:04
    * @param map canshu
    * @return List<EmployeeLevelDto>
     */
    List<EmployeeDto> getlevelemployee(Map<String, Object> map);
    
    /**
     * 根据多个等级Id查询列表
    * @author 洪秋霞
    * @date 2015年9月21日 下午2:06:28
    * @param levelIds 会员等级标识
    * @return List<MemberLevel>
     */
    List<EmployeeLevel> selectByLevelIds(List<Integer> levelIds);
    
    /**
     * 根据岗位标识和职位名称判断是否存在
    * @author chendb
    * @date 2015年10月15日 下午6:13:28
    * @param map 参数
    * @return int
     */
    EmployeeLevel countlevel(Map<String, Object>map);
    
    /**
     * 根据店铺id查询所有等级/职位(状态没删除的)
     * @param storeId  店铺id
     * @return  店铺id查询所有等级/职位(状态没删除的)
     */
    List<String> selectEmployeeLevelsByStoreId(int storeId);
    
    /**
     * 根据岗位集合查询职位集合
    * @author 高国藩
    * @date 2016年1月19日 下午5:09:55
    * @param param          岗位ID集合
    * @return               职位集合
     */
    List<EmployeeLevelBaseDto> selectByPositionIds(Map<String, String> param);
    
    /**
     * 查询门店下所有职位信息
    * @author 高国藩
    * @date 2016年1月19日 下午5:09:55
    * @param storeId          岗位ID集合
    * @return               职位集合
     */
    List<EmployeeLevel> selectAllByStoreId(Integer storeId);
    
    /**
     * 查询职位对应的参考职位
    * @author 老王
    * @date 2016年7月1日 上午10:52:48 
    * @param levelId 职位标识
    * @return Map<String, Integer>
     */
    Map<String, Integer> selectReferencePositionId(Integer levelId);
}