package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.SpecialService;

public interface SpecialServiceMapper {
    
    int deleteByPrimaryKey(Integer sId);

    int insert(SpecialService record);

    int insertSelective(SpecialService record);

    SpecialService selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(SpecialService record);

    int updateByPrimaryKeyWithBLOBs(SpecialService record);

    int updateByPrimaryKey(SpecialService record);

    List<SpecialService> selectByStoreId(Integer storeId);
}