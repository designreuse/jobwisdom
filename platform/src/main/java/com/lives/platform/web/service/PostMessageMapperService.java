package com.lives.platform.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.dto.PostMessagePositionDto;
import com.lives.platform.web.entity.PostMessage;
import com.lives.platform.web.mapper.PostMessageMapper;


/**
 * 岗位信息表
* @author 王潇
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class PostMessageMapperService {
	@Autowired 
	private PostMessageMapper postMessageMapper;
	
	public int deletePost(Integer postMessageID){
		return postMessageMapper.deleteByPrimaryKey(postMessageID);
	}
	
	public int insertPost(PostMessage postMessage){
		return postMessageMapper.insertSelective(postMessage);
	}
	
	public PostMessage queryPost(Integer postMessageID){
		return postMessageMapper.selectByPrimaryKey(postMessageID);
	}
	
	public int updatePost(PostMessage postMessage){
		return postMessageMapper.updateByPrimaryKeySelective(postMessage);
	}
	
	public List<PostMessage> selectAll(){
		return postMessageMapper.selectAll();
	}
	
	/**
	 * 动态查询
	* @author 洪秋霞
	* @date 2015年6月25日 下午2:34:24
	* @param postMessage
	* @return
	 */
	public List<PostMessage> selectByProperty(PostMessage postMessage){
		return postMessageMapper.selectByProperty(postMessage);
	}
	
	public List<PostMessagePositionDto> selectPostMessagePositionDto(Integer storesId){
		return postMessageMapper.selectPostMessagePositionDto(storesId);
	}
}
