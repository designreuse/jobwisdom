package com.lives.manage.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.manage.web.dto.CustClueDto;
import com.lives.manage.web.entity.CustomerClue;
import com.lives.manage.web.mapper.CustomerClueMapper;

/**
 * @author 陈端斌
 * @date 2015年6月8日 下午2:06:09
 * 
 */
@Service
public class CustClueService {

	@Autowired
	private CustomerClueMapper customerClueMapper;

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 新增客户线索信息
	 */
	public String addCustClue(CustomerClue customerClue) {
		customerClueMapper.insert(customerClue);
		return null;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 修改用户线索
	 */
	public String updateCustClue(CustomerClue customerClue) {
		customerClueMapper.updateByPrimaryKeySelective(customerClue);
		return null;
	}
	
	public String updateClueSs(CustomerClue customerClue) {
		customerClueMapper.updateClueSs(customerClue);
		return null;
	}
	
	public String deleteClue(Integer clueId) {
		customerClueMapper.deleteByPrimaryKey(clueId);
		return null;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 查询用户线索
	 */
	public List<CustClueDto> queryCustClue(Map<String, Object> map) {
		List<CustClueDto> queryCustInfo = customerClueMapper.queryCustClue(map);
		return queryCustInfo;
	}
	public int queryCount(Map<String, Object> map) {
		int count = customerClueMapper.queryCount(map);
		return count;
	}

}
