package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.EmployeeServeInfo;
import com.lives.platform.web.mapper.EmployeeServeInfoMapper;

@Service
public class EmployeeServeInfoMapperService {
	
	@Autowired
	private EmployeeServeInfoMapper employeeServeInfoMapper;
	
	public Integer insert(EmployeeServeInfo employeeServeInfo){
		Integer i = employeeServeInfoMapper.insert(employeeServeInfo);
		return i;
	}

}
