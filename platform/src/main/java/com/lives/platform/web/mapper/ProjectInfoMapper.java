package com.lives.platform.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.platform.web.dto.ProjectClassDto;
import com.lives.platform.web.entity.ProjectInfo;

public interface ProjectInfoMapper {
    int deleteByPrimaryKey(Integer projectInfoId);

    int insert(ProjectInfo record);

    int insertSelective(ProjectInfo record);

    ProjectInfo selectByPrimaryKey(Integer projectInfoId);

    int updateByPrimaryKeySelective(ProjectInfo record);

    int updateByPrimaryKey(ProjectInfo record);
    
    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年7月3日 下午5:48:10
    * @param projectInfo
    * @return
     */
    List<ProjectInfo> selectByProperty(ProjectInfo projectInfo);
    
    /**
     * 查询项目类型和项目
     */
    List<ProjectClassDto> selectProjectInfoDto(Integer storesId);
    
    /**
     * 根据多个项目id查询数据列表
    * @author 洪秋霞
    * @date 2015年7月6日 下午4:24:24
    * @param projectInfoIds
    * @return
     */
    List<ProjectInfo> selectByProjectIdList(List<Integer> projectInfoIds);
    
    /**
     * 批量删除
    * @author 洪秋霞
    * @date 2015年7月8日 下午8:43:16
    * @param projectInfoIds
    * @return
     */
    int deleteProjectIdBatch(List<Integer> projectInfoIds);
    
    /**
     * 批量新增项目
    * @author 洪秋霞
    * @date 2015年7月10日 下午4:55:37
    * @param projectInfoIdList
    * @return
     */
    int insertProjectInfoList(List<ProjectInfo> projectInfoList);
    
    /**
     * 根据多个门店id和项目编号查找项目列表
    * @author 洪秋霞
    * @date 2015年7月10日 下午7:23:37
    * @param paramMap
    * @return
     */
    List<ProjectInfo> selectByStroesIdAndPno(Map<String, Object> paramMap);
    
    /**
     * 批量更新项目
    * @author 洪秋霞
    * @date 2015年7月10日 下午8:14:15
    * @param projectInfoList
    * @return
     */
    int batchUpdateProject(List<ProjectInfo> projectInfoList);
}