package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.dto.CustClueDto;
import com.lives.manage.web.entity.CustomerClue;

public interface CustomerClueMapper {
    int deleteByPrimaryKey(Integer clueId);

    int insert(CustomerClue record);

    int insertSelective(CustomerClue record);

    CustomerClue selectByPrimaryKey(Integer clueId);

    int updateByPrimaryKeySelective(CustomerClue record);
    
    int updateClueSs(CustomerClue record);
    
    int updateByPrimaryKey(CustomerClue record);
    
    List<CustClueDto>queryCustClue(Map<String, Object> map);
    
    int queryCount(Map<String, Object> map);
}