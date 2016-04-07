package com.zefun.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.dto.DetailsDto;
import com.zefun.web.entity.EnrollInfo;

/**
 * 报名表操作
* @author 高国藩
* @date 2016年1月9日 上午11:18:13
 */
public interface EnrollInfoMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月9日 上午11:19:22
    * @param personnelId    主键
    * @return               影响行数
     */
    int deleteByPrimaryKey(Integer personnelId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月9日 上午11:19:22
    * @param record    主键
    * @return               影响行数
     */
    int insert(EnrollInfo record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月9日 上午11:19:22
    * @param record    主键
    * @return               影响行数
     */
    int insertSelective(EnrollInfo record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月9日 上午11:19:22
    * @param personnelId    主键
    * @return               影响行数
     */
    EnrollInfo selectByPrimaryKey(Integer personnelId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月9日 上午11:19:22
    * @param record    主键
    * @return               影响行数
     */
    int updateByPrimaryKeySelective(EnrollInfo record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年1月9日 上午11:19:22
    * @param record    主键
    * @return               影响行数
     */
    int updateByPrimaryKey(EnrollInfo record);

    /**
     * 根据openId和会议ID查询该人是否已经报名
    * @author 高国藩
    * @date 2016年1月9日 上午11:19:46
    * @param openId           微信标识
    * @param conferenceId     会议ID
    * @return                 报名信息
     */
    EnrollInfo selectByOpenId(@Param("openId")String openId, @Param("conferenceId")Integer conferenceId);

    /**
     * 查询对应一级推荐人
    * @author 高国藩
    * @date 2016年1月8日 下午8:41:49
    * @param personnelId    主推荐人标识
    * @param conferenceId   会议ID
    * @return               一级推荐人集合
     */
    List<EnrollInfo> selectMainAware(@Param("personnelId")Integer personnelId, @Param("conferenceId")Integer conferenceId);

    /**
     * 查询二级推荐人
    * @author 高国藩
    * @date 2016年1月8日 下午8:42:42
    * @param mainAware      一级推荐人集合
    * @param conferenceId   会议ID
    * @return               二级推荐人集合
     */
    List<EnrollInfo> selectBranchAware(@Param("mainAware")List<EnrollInfo> mainAware, @Param("conferenceId")Integer conferenceId);
    
    /**
     * 查看已经报名并且支付的人员信息
    * @author 高国藩
    * @date 2016年1月9日 下午4:34:07
    * @param conferenceId         会议ID
    * @return                     报名结果
     */
    List<EnrollInfo> selectHasPay(Integer conferenceId);
    
    /**
     * 查看已经报名并且未支付的人员信息
    * @author 高国藩
    * @date 2016年1月9日 下午4:34:07
    * @param conferenceId         会议ID
    * @return                     报名结果
     */
    List<EnrollInfo> selectNoPay(Integer conferenceId);
    
    /**
     * 查询该会议的费用详情,推荐奖励,支出
    * @author 高国藩
    * @date 2016年1月9日 下午7:52:45
    * @param conferenceId   会议ID
    * @return               收支费用明细
     */
    List<DetailsDto> selectConferenceDetails(Integer conferenceId);
    
    /**
     * 查询该会议的报名费总额,收入
    * @author 高国藩
    * @date 2016年1月9日 下午8:01:21
    * @param conferenceId   会议ID
    * @return               报名费用总额
     */
    Integer selectConferenceEarning(Integer conferenceId);
    /**
     * 已到场人员
    * @author 高国藩
    * @date 2016年1月15日 上午11:47:07
    * @param conferenceId    会议ID
    * @return                已到场的人员
     */
    List<EnrollInfo> selectHasAdmission(Integer conferenceId);
    /**
     * 未到场人员
    * @author 高国藩
    * @date 2016年1月15日 上午11:47:07
    * @param conferenceId    会议ID
    * @return                已到场的人员
     */
    List<EnrollInfo> selectNoAdmission(Integer conferenceId);

}