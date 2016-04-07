package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.AgentFollow;

/**
 * 渠道跟踪映射
* @author DavidLiang
* @date 2016年1月18日 下午5:48:56
 */
public interface AgentFollowMapper {
	/**
	 * 根据主键删除渠道跟踪记录
	* @author DavidLiang
	* @date 2016年1月18日 下午5:49:20
	* @param agentFollowId  主键
	* @return  成功数目
	 */
    int deleteByPrimaryKey(Integer agentFollowId);

    /**
     * 插入渠道跟踪记录
    * @author DavidLiang
    * @date 2016年1月18日 下午5:49:51
    * @param record  渠道跟踪实体
    * @return  成功数目
     */
    int insert(AgentFollow record);

    /**
     * 选择性渠道跟踪记录
    * @author DavidLiang
    * @date 2016年1月18日 下午5:49:55
    * @param record  渠道跟踪记录
    * @return  成功数目
     */
    int insertSelective(AgentFollow record);

    /**
     * 根据主键查询渠道跟踪记录
    * @author DavidLiang
    * @date 2016年1月18日 下午5:49:59
    * @param agentFollowId  主键
    * @return  渠道跟踪实体
     */
    AgentFollow selectByPrimaryKey(Integer agentFollowId);

    /**
     * 根据主键选择性更新渠道跟踪记录
    * @author DavidLiang
    * @date 2016年1月18日 下午5:50:03
    * @param record  渠道跟踪实体
    * @return  成功数目
     */
    int updateByPrimaryKeySelective(AgentFollow record);

    /**
     * 根据主键更新渠道跟踪记录
    * @author DavidLiang
    * @date 2016年1月18日 下午5:50:07
    * @param record  渠道跟踪实体
    * @return  成功数目
     */
    int updateByPrimaryKey(AgentFollow record);
    
    /**
     * 根据店铺id集查询渠道跟踪记录
    * @author DavidLiang
    * @date 2016年1月19日 下午2:00:06
    * @param storeIdList  店铺id集
    * @return  渠道跟踪集合
     */
    List<AgentFollow> selectAgentFollowByStoreIdList(List<Integer> storeIdList);
}