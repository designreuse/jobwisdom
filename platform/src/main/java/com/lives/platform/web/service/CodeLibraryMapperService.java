package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.CodeLibrary;
import com.lives.platform.web.entity.CodeLibraryKey;
import com.lives.platform.web.entity.Employee;
import com.lives.platform.web.mapper.CodeLibraryMapper;
import com.lives.platform.web.mapper.EmployeeMapper;

/**
 * 员工信息表
* @author 老王
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class CodeLibraryMapperService {
	@Autowired private CodeLibraryMapper codeLibraryMapper;
	
	public int deleteCodeLibrary(CodeLibraryKey projectEmployeeId){
		return codeLibraryMapper.deleteByPrimaryKey(projectEmployeeId);
	}
	
	
	public int insertCodeLibrary(CodeLibrary record){
		return codeLibraryMapper.insertSelective(record);
	}
	
	public CodeLibrary queryCodeLibrary(CodeLibraryKey key){
		return codeLibraryMapper.selectByPrimaryKey(key);
	}
	
	public int updateCodeLibrary(CodeLibrary record){
		return codeLibraryMapper.updateByPrimaryKeySelective(record);
	}
	
	public List<CodeLibrary> selectByTypeName(String typeName){
		return codeLibraryMapper.selectByTypeName(typeName);
	}
}
