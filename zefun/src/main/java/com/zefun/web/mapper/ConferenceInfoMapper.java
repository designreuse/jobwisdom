package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ConferenceInfoDto;
import com.zefun.web.entity.ConferenceInfo;

/**
 * 会议数据
* @author 高国藩
* @date 2016年1月8日 下午3:21:34
 */
public interface ConferenceInfoMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月8日 下午3:22:10
    * @param conferenceId  主键
    * @return              影响行数
     */
    int deleteByPrimaryKey(Integer conferenceId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月8日 下午3:22:10
    * @param record  主键
    * @return              影响行数
     */
    int insert(ConferenceInfo record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月8日 下午3:22:10
    * @param record  主键
    * @return              影响行数
     */
    int insertSelective(ConferenceInfo record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月8日 下午3:22:10
    * @param conferenceId  主键
    * @return              影响行数
     */
    ConferenceInfo selectByPrimaryKey(Integer conferenceId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月8日 下午3:22:10
    * @param record  主键
    * @return              影响行数
     */
    int updateByPrimaryKeySelective(ConferenceInfo record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月8日 下午3:22:10
    * @param record  主键
    * @return              影响行数
     */
    int updateByPrimaryKey(ConferenceInfo record);
    /**
     * 查询我的会议列表
    * @author 高国藩
    * @date 2016年1月8日 下午3:22:10
    * @param openId        微信标识
    * @return              集合,并说明了会议的状态
     */
    List<ConferenceInfoDto> selectConferenceDtos(String openId);
    
    /**
     * 一个会议详情
    * @author 高国藩
    * @date 2016年1月8日 下午3:22:10
    * @param conferenceId        会议标识
    * @return                    会议详情
     */
    ConferenceInfoDto selectConferenceDto(Integer conferenceId);
}