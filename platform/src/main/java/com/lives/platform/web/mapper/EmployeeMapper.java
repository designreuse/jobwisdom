package com.lives.platform.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.platform.web.dto.EmployeeDto;
import com.lives.platform.web.entity.Employee;
import com.lives.platform.web.entity.Page;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer employeeId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer employeeId);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    List<Employee> selectByProperty(Employee record);
    
    List<Employee> selectEmployeesByPosition(Integer postMessageId);
    
    List<Employee> selectByParentId(Integer parentId);
    
    List<Employee> selectByStoresId(Integer storesId);
    
    List<Employee> selectEmployeesByPositionList(Map<String, Object> paramMap);
    
    List<EmployeeDto> selectBySynthetical(Page<EmployeeDto> page);
    
    EmployeeDto selectByEmployeeId(Integer employeeId);
}