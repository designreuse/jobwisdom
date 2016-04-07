package com.lives.platform.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.dto.EmployeeDto;
import com.lives.platform.web.entity.Employee;
import com.lives.platform.web.entity.Page;
import com.lives.platform.web.mapper.EmployeeMapper;

/**
 * 员工信息表
* @author 老王
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class EmployeeMapperService {
	@Autowired private EmployeeMapper employeeMapper;
	
	public int deleteEmployee(Integer employeeId){
		return employeeMapper.deleteByPrimaryKey(employeeId);
	}
	
	
	public int insertEmployee(Employee Employee){
		return employeeMapper.insertSelective(Employee);
	}
	
	public Employee queryEmployee(Integer employeeId){
		return employeeMapper.selectByPrimaryKey(employeeId);
	}
	
	public int updateEmployee(Employee Employee){
		return employeeMapper.updateByPrimaryKeySelective(Employee);
	}
	
	/**查询员工信息 （动态）*/
	public List<Employee> selectByProperty(Employee employee){
		return employeeMapper.selectByProperty(employee);
	}
	public List<Employee> selectByParentId(Integer parentId){
		return employeeMapper.selectByParentId(parentId);
	}
	
	public List<Employee> selectByStoresId(Integer storesId){
		return employeeMapper.selectByStoresId(storesId);
	}

    /**
     * 根据岗位id查找员工列表
    * @author 洪秋霞
    * @date 2015年6月25日 下午3:25:55
    * @param postMessageId
    * @return
     */
    public List<Employee> selectEmployeesByPosition(Integer postMessageId){
    	return employeeMapper.selectEmployeesByPosition(postMessageId);
    }
    
    /**
     * 根据多个职位id查找员工
    * @author 洪秋霞
    * @date 2015年6月30日 上午11:28:08
    * @param positionId
    * @return
     */
    public List<Employee> selectEmployeesByPositionList(Map<String, Object> paramMap){
    	return employeeMapper.selectEmployeesByPositionList(paramMap);
    }
    /**
     * 查询员工及门店等信息
    * @author 老王
    * @date 2015年7月20日 上午9:44:14
    * @param storesId
    * @return
     */
    public List<EmployeeDto> selectBySynthetical(Page<EmployeeDto> page){
    	return employeeMapper.selectBySynthetical(page);
    }
    
    public EmployeeDto selectByEmployeeId(Integer employeeId){
    	return employeeMapper.selectByEmployeeId(employeeId);
    }
}
