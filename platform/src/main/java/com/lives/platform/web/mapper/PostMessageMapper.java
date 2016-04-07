package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.dto.PostMessagePositionDto;
import com.lives.platform.web.entity.PostMessage;

public interface PostMessageMapper {
    int deleteByPrimaryKey(Integer postMessageId);

    int insert(PostMessage record);

    int insertSelective(PostMessage record);

    PostMessage selectByPrimaryKey(Integer postMessageId);

    int updateByPrimaryKeySelective(PostMessage record);

    int updateByPrimaryKey(PostMessage record);
    
    List<PostMessage> selectAll();
    
    List<PostMessage> selectByProperty(PostMessage record);
    
    List<PostMessagePositionDto> selectPostMessagePositionDto(Integer storesId);
    
}