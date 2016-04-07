package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberRecommend;

/**
 * 会员推荐信息操作类
* @author 张进军
* @date Jan 5, 2016 8:09:40 PM
 */
public interface MemberRecommendMapper {
	
	/**
	 * 根据会员标识、被推荐会员标识删除推荐记录
	* @author 张进军
	* @date Jan 5, 2016 8:09:55 PM
	* @param key	会员标识、被推荐会员标识
	* @return	0:失败，1:成功
	 */
    int deleteByPrimaryKey(MemberRecommend key);

    /**
	 * 新增推荐记录
	* @author 张进军
	* @date Jan 5, 2016 8:09:55 PM
	* @param record	推荐记录
	* @return	0:失败，1:成功
	 */
    int insert(MemberRecommend record);

    /**
	 * 根据会员标识、被推荐会员标识查询推荐记录
	* @author 张进军
	* @date Jan 5, 2016 8:09:55 PM
	* @param key	会员标识、被推荐会员标识
	* @return	推荐记录
	 */
    MemberRecommend selectByPrimaryKey(MemberRecommend key);

    /**
	 * 根据会员标识、被推荐会员标识修改推荐记录
	* @author 张进军
	* @date Jan 5, 2016 8:09:55 PM
	* @param record	记录信息
	* @return	0:失败，1:成功
	 */
    int updateByPrimaryKey(MemberRecommend record);
    
    /**
     * 根据会员标识查询所推荐的会员标识列表
    * @author 张进军
    * @date Jan 7, 2016 12:13:11 PM
    * @param memberId	会员标识
    * @return	会员标识列表
     */
    List<Integer> selectRecommendIdByMemberId(int memberId);
    
    /**
     * 根据被推荐会员查询推荐者标识
    * @author 张进军
    * @date Jan 8, 2016 8:11:33 PM
    * @param recommendId	被推荐会员
    * @return	推荐者标识
     */
    Integer selectMemberIdByRecommendId(int recommendId);
}