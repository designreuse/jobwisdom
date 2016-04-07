package com.lives.platform.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.Post;
import com.lives.platform.web.mapper.PostMapper;


/**
 * 岗位表
* @author 王潇
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class PostMapperService {
	@Autowired 
	private PostMapper postMapper;
	
	public int deletePost(Integer postID){
		return postMapper.deleteByPrimaryKey(postID);
	}
	
	public int insertPost(Post post){
		return postMapper.insertSelective(post);
	}
	
	public Post queryPost(Integer postID){
		return postMapper.selectByPrimaryKey(postID);
	}
	
	public int updatePost(Post post){
		return postMapper.updateByPrimaryKeySelective(post);
	}
	
	public List<Post> selectAllPost(){
		return postMapper.selectAllPost();
	}
	
}
