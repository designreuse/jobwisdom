package com.zefun.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zefun.api.dto.ShiftDto;
import com.zefun.api.entity.EmployeeShiftInfo;
import com.zefun.api.entity.Page;
import com.zefun.api.entity.ShiftInfo;

/*import com.zefun.web.dto.ShiftDto;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.EmployeeShiftInfo;*/

/**
 * 关于班次信息
* @author chendb
* @date 2015年8月28日 上午10:02:43
 */
public interface ShiftMapper {

    /**
     * 修改班次
    * @author chendb
    * @date 2015年8月28日 上午10:03:59
    * @param record 参数
    * @return int
     */
    int updateByPrimaryKey(ShiftDto record);
    /**
     * 获取人员班次信息
    * @author chendb
    * @date 2015年8月28日 上午10:24:04
    * @param page 参数
    * @return List<ShiftDto>
     */
    List<ShiftDto> getEmployeeShift(Page<ShiftDto> page);
    
    /**
     * 获取班次信息
    * @author chendb
    * @date 2015年8月28日 上午10:45:15
    * @param deptId 部门 标识
    * @return List<ShiftDto>
     */
    List<EmployeeShiftInfo>getEmployeeShiftInfo(Integer deptId);
    /**
     * 
    * @author chendb
    * @date 2015年8月28日 下午4:38:03
    * @param map 参数
    * @return int
     */
    int employeeShift(Map<String, Object> map);
    /**
     * 删除员工班次信息
    * @author chendb
    * @date 2015年8月28日 下午4:45:57
    * @param map 参数
    * @return int
     */
    int deleteShift(Map<String, Object> map);
    /**
     * 获取员工班次信息
    * @author chendb
    * @date 2015年8月31日 下午3:58:15
    * @param map 参数
    * @return ShiftDto
     */
    ShiftDto queryEmployeeShift(Map<String, Object> map);
    /**
     * 判断是否已经新增过人员班次了
    * @author chendb
    * @date 2015年8月31日 下午5:04:12
    * @param map 参数
    * @return int
     */
    int countIsshift(Map<String, Object> map);
    /**
     * 修改班次信息
    * @author chendb
    * @date 2015年9月1日 上午11:05:10
    * @param map 参数
    * @return int
     */
    int updateShift(EmployeeShiftInfo map);
    
    /**
     * 新增班次信息
    * @author chendb
    * @date 2015年9月1日 上午11:05:10
    * @param map 参数
    * @return int
     */
    int addShift(Map<String, Object> map);
    
    
    /**
     * 根据员工查询班次信息
    * @author 小高
    * @date Oct 19, 2015 3:21:12 PM
    * @param employeeId 员工标识
    * @return   员工班次信息
     */
    Map<Integer, String> selectShiftByEmployeeId(int employeeId);
    /**
     * 
    * @author chendb
    * @date 2015年10月21日 下午2:35:36
    * @param map 参数
    * @return EmployeeShiftInfo
     */
    EmployeeShiftInfo queryEmployeeShiftInfo(Map<String, Object> map);
    /**
     * 
    * @author chendb
    * @date 2015年10月21日 下午5:02:07
    * @param info 参数
    * @return int
     */
    int insertInfo(ShiftDto info);
    /**
     * 
    * @author chendb
    * @date 2015年10月22日 上午10:38:54
    * @param page 参数
    * @return List<ShiftDto>
     */
    List<ShiftDto> queryEmployee(Page<ShiftDto> page);
    /**
     * 新增班次
    * @author chendb
    * @date 2015年11月9日 下午5:45:39
    * @param EmployeeShiftInfo 参数
    * @return int
     */
    int insertEmployeeShiftInfo(EmployeeShiftInfo EmployeeShiftInfo);
    
    /**
     * 根据主键批量查询班次信息
     * @param shiftIds 主键集
     * @return 班次信息集
     */
    List<ShiftInfo> selectShiftsByIds(int[] shiftIds);
    
    /**
     * 根据员工id集查询今天所有要上班员工班次信息
     * @param weekDay  周几
     * @param allEmployeeIdList 员工id集
     * @return weekDay要上班员工班次信息
     */
    List<ShiftInfo> selectShiftByEmployeeIdList(@Param(value="weekDay")Integer weekDay, 
    		@Param(value="allEmployeeIdList")List<String> allEmployeeIdList);
    /**
     * 根据员工id和周几查询他今天班次
     * @param weekDay  周几
     * @param employeeId  员工id
     * @return  员工班次
     */
    ShiftInfo selectShiftByEmployeeIdAndWeekday(@Param(value="weekDay")Integer weekDay, 
    		@Param(value="employeeId")Integer employeeId);
    
}