package com.lives.platform.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.platform.web.dto.DommissionEmployeeDto;
import com.lives.platform.web.entity.Dommission;

public interface DommissionMapper {
    int deleteByPrimaryKey(Integer dommissionId);

    int insert(Dommission record);

    int insertSelective(Dommission record);

    Dommission selectByPrimaryKey(Integer dommissionId);

    int updateByPrimaryKeySelective(Dommission record);

    int updateByPrimaryKey(Dommission record);
    
    List<Dommission> selectByProperty(Dommission dommission);
    
    int insertPositionList(List<Dommission> dommissionList);
    
    List<DommissionEmployeeDto> selectByProjectInfoId(Integer projectInfoId);
    
    List<Dommission> selectByProjectIdList(List<Integer> projectInfoIds);
    
    int deleteDommissionBatch(List<Integer> dommissionIds);
    
    int deleteDommissionByEmpIdBatch(List<Integer> employeeIds);
    
    List<Dommission> selectByProjectIdAndEmpIdList(Map<String, Object> paramMap);
    
    int deleteByProjectInfoId(Integer projectInfoId);//暂无使用
}