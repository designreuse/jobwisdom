package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.CodeLibrary;
import com.lives.platform.web.entity.CodeLibraryKey;

public interface CodeLibraryMapper {
    int deleteByPrimaryKey(CodeLibraryKey key);

    int insert(CodeLibrary record);

    int insertSelective(CodeLibrary record);

    CodeLibrary selectByPrimaryKey(CodeLibraryKey key);

    int updateByPrimaryKeySelective(CodeLibrary record);

    int updateByPrimaryKey(CodeLibrary record);
    
    List<CodeLibrary> selectByTypeName(String typeName);
}