package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.YzmPageQiniu;

public interface YzmPageQiniuMapper {
	int deleteByPrimaryKey(Integer yzmPageId);

	int insert(YzmPageQiniu record);

	int insertSelective(YzmPageQiniu record);

	YzmPageQiniu selectByPrimaryKey(Integer yzmPageId);

	int updateByPrimaryKeySelective(YzmPageQiniu record);

	int updateByPrimaryKey(YzmPageQiniu record);

	/**
	 * 查询所有验证码信息
	 * 
	 * @return
	 */
	List<YzmPageQiniu> selectByAll();
}