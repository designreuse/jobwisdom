package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.entity.DeptInfo;

/**
 * 关于部门
* @author chendb
* @date 2015年9月8日 上午10:12:59
 */
public interface DeptInfoMapper {
    /**
     * 删除功能
    * @author chendb
    * @date 2015年9月8日 上午10:13:41
    * @param deptId 标识
    * @return int
     */
    int deleteByPrimaryKey(Integer deptId);
    /**
     * 新增功能
    * @author chendb
    * @date 2015年9月8日 上午10:13:41
    * @param record 参数
    * @return int
     */
    int insert(DeptInfo record);

    /**
     * 查询所有的部门根据么电
    * @author 高国藩
    * @date 2016年3月3日 下午2:17:07
    * @param storeId storeId
    * @return storeId
     */
    List<DeptInfo> selectAllDetpByStoreId(Integer storeId);

    /**
     * 修改功能
    * @author chendb
    * @date 2015年9月8日 上午10:13:41
    * @param record 参数
    * @return int
     */
    int updateByPrimaryKeySelective(DeptInfo record);
    
    /**
     * 判断是否已经有编码  或者有部门名称
    * @author chendb
    * @date 2015年9月8日 上午11:36:04
    * @param map 参数
    * @return int
     */
    int getCount(Map<String, Object> map);
    /**
     * 判断部门是否被引用
    * @author chendb
    * @date 2015年9月8日 下午3:43:22
    * @param deptId 部门标识
    * @return int
     */
    int isQuote(Integer deptId);
    
    /**
     * 纯粹根据门店标识获取部门信息
    * @author chendb
    * @date 2015年9月9日 下午3:18:10
    * @param storeId 门店标识
    * @return List<DeptInfoDto>
     */
    List<DeptInfo> getDeptInfo(Integer storeId);
    
    /**
     * 获取相关部门数据
    * @author chendb
    * @date 2015年10月14日 上午9:34:57
    * @param deptInfo 参数
    * @return DeptInfo
     */
    DeptInfo getDeptDetail(DeptInfo deptInfo);
    
    
    /**
     * 
    * @author 骆峰
    * @date 2016年7月26日 下午8:50:23
    * @param deptInfo deptInfo
    * @return List
     */
    List<DeptInfo> getDeptName(DeptInfo deptInfo);
    /**
     * 根据门店编号查询部门编号列表
    * @author 张进军
    * @date Oct 15, 2015 12:16:39 AM
    * @param storeId    门店标识
    * @return   部门编号列表
     */
    List<Integer> selectDeptIdByStoreId(int storeId);
    
    /**
     * 根据门店编号查询部门编号列表,非业绩
    * @author 高国藩
    * @date Oct 15, 2015 12:16:39 AM
    * @param storeId    门店标识
    * @return   部门编号列表
     */
    List<Integer> selectDeptIdByStoreIdIsResults(int storeId);
    /**
     * 判断部门是否被引用
    * @author chendb
    * @date 2015年10月27日 下午1:57:25
    * @param map 参数
    * @return int
     */
    int countProjectDept(Map<String, Object> map);
    /**
     * 
    * @author chendb
    * @date 2015年10月27日 下午1:57:25
    * @param map 参数
    * @return int
     */
    int countGoodsDept(Map<String, Object> map);
    /**
     * 
    * @author chendb
    * @date 2015年10月27日 下午1:57:25
    * @param map 参数
    * @return int
     */
    int countComboDept(Map<String, Object> map);
    /**
     * 
    * @author chendb
    * @date 2015年10月27日 下午1:57:25
    * @param map 参数
    * @return int
     */
    int countCategoryDept(Map<String, Object> map);
    
    /**
     * 根据门店标识查询部门标识和部门名称
     * @param storeId 门店标识
     * @return 部门信息
     */
    List<DeptInfo> getDeptIdAndNameByStoreId(Integer storeId);
    
    
    /**
     * 获取产生业绩的部门
    * @author chendb
    * @date 2015年11月9日 下午4:04:41
    * @param storeId storeId
    * @return List<DeptInfo>
     */
    List<DeptInfo> getResultsDeptInfo(Integer storeId);
    
    /**
     * 根据店铺id查询所有部门(状态没删除的)
     * @param storeId  店铺id
     * @return  该店铺下的所有部门(状态没删除的)
     */
    List<String> selectDeptNamesByStoreId(int storeId);
    
    /**
     * 查询全部部门
     * @return  全部部门信息
     */
    List<DeptInfo> selectAllDetp();
    
    /**
     * 根据店铺id查询产生业绩部门
    * @author DavidLiang
    * @date 2016年1月23日 上午10:46:48
    * @param storeId  店铺id
    * @return  产生业绩部门集
     */
    List<DeptInfo> selectDeptByStoreId(@Param(value = "storeId")int storeId);
    
    /**
     *  查询部门和大项关系
    * @author 高国藩
    * @date 2016年7月13日 下午3:27:54
    * @param storeId storeId
    * @return        List<DeptInfoDto>
     */
    List<DeptInfoDto> selectDeptInfoProjectCategory(int storeId);
    
}