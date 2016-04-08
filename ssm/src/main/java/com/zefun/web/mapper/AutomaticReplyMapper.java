package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.AutomaticReply;
/**
 * 回复消息(保留了上传至微信端的素材信息,方便自动回复的设置)
* @author 高国藩
* @date 2015年8月11日 下午3:34:40
 */
public interface AutomaticReplyMapper {
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:35:20
    * @param replyId 主键
    * @return 状态
     */
    int deleteByPrimaryKey(Integer replyId);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:35:46
    * @param record 实体
    * @return 状态
     */
    int insert(AutomaticReply record);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年8月11日 下午3:36:05
    * @param record 实体
    * @return 状态
     */
    int insertSelective(AutomaticReply record);
    /**
     * 查询
    * @author 高国藩
    * @date 2015年8月11日 下午3:36:34
    * @param replyId 主键
    * @return 实体
     */
    AutomaticReply selectByPrimaryKey(Integer replyId);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年8月11日 下午3:36:54
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKeySelective(AutomaticReply record);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:08
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKey(AutomaticReply record);

    /**
     * 批量新增
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:31
    * @param automaticReplies 集合
    * @return 状态
     */
	int insertByList(List<AutomaticReply> automaticReplies);
    /**
     * 查询回复消息
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:36
    * @param mediaId 素材ID
    * @return 回复列表
     */
	List<AutomaticReply> selectByMediaId(String mediaId);
    /**
     * 查询回复消息
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:40
    * @param storeId 门店ID
    * @return 回复列表
     */
	List<AutomaticReply> selectByStoreId(Integer storeId);
}