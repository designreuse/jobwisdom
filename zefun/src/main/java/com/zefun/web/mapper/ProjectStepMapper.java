package com.zefun.web.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zefun.web.entity.ProjectStep;

/**
 * 项目步骤
* @author 洪秋霞
* @date 2015年8月11日 上午11:15:24
 */
public interface ProjectStepMapper {
    
    /**
     * 根据项目标识查询可预约的步骤信息
    * @author 张进军
    * @date Jan 29, 2016 5:02:29 PM
    * @param projectIds  项目标识集合
    * @return   步骤信息
     */
    List<ProjectStep> selectAppointStepByProjectId(String[] projectIds);
    
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:15:35
    * @param projectStepId 项目步骤id
    * @return int
     */
    int deleteByPrimaryKey(Integer projectStepId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:16:04
    * @param projectStep 项目步骤
    * @return int
     */
    int insert(ProjectStep projectStep);
  

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:16:27
    * @param projectStepId 项目步骤id
    * @return ProjectStep
     */
    ProjectStep selectByPrimaryKey(Integer projectStepId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:16:40
    * @param projectStep 项目步骤
    * @return int
     */
    int updateByPrimaryKey(ProjectStep projectStep);
    
    /**
     * 根据项目id查询轮牌步骤
    * @author 洪秋霞
    * @date 2015年9月14日 下午2:23:38
    * @param projectId 项目id
    * @return List<ProjectStep>
     */
    List<ProjectStep> queryProjectStepByPIdList(Integer projectId);
    
    /**
     * 根据项目id删除项目步骤
    * @author 张进军
    * @date Oct 16, 2015 8:38:11 PM
    * @param projectId  项目标识
    * @return   删除条数
     */
    int deleteByProjectId(int projectId);
    
    /**
     * 根据轮牌标识查询步骤
    * @author 王大爷
    * @date 2015年10月30日 上午11:43:30
    * @param map 岗位标识
    * @return List<ProjectStep>
     */
    ProjectStep selectByPositionId(Map<String, Integer> map);
    
    /**
     * 查询项目对应的固定业绩值
    * @author 老王
    * @date 2016年6月28日 上午10:13:20 
    * @param projectId 项目标识
    * @return BigDecimal
     */
    BigDecimal selectFixedValue(Integer projectId);
    
    /**
     * 提成新增
    * @author 骆峰
    * @date 2016年6月25日 下午2:45:57
    * @param projectStep projectStep
    * @return int
     */
    int insertStep(ProjectStep projectStep);
    /**
     *  回显
    * @author 骆峰
    * @date 2016年6月25日 下午7:35:40
    * @param projectId projectId
    * @return ProjectStep
     */
    List<ProjectStep> selectShowStep(Integer projectId);
}