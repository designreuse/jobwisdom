package com.zefun.wechat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.ConferenceInfoDto;
import com.zefun.web.dto.DetailsDto;
import com.zefun.web.entity.ConferenceInfo;
import com.zefun.web.entity.EnrollInfo;
import com.zefun.web.entity.WechatAgent;
import com.zefun.web.mapper.ConferenceInfoMapper;
import com.zefun.web.mapper.EnrollInfoMapper;
import com.zefun.web.mapper.WechatAgentMapper;

/**
 * 渠道相关信息操作类
* @author 小高
* @date 2016年1月9日 下午8:19:40
 */
@Service
public class ConferenceService {
    
    /**会议信息操作*/
    @Autowired
    private ConferenceInfoMapper conferenceInfoMapper;
    /**报名操作*/
    @Autowired
    private EnrollInfoMapper enrollInfoMapper;
    /**渠道商*/
    @Autowired
    private WechatAgentMapper wechatAgentMapper;

    /**
     * 新增会议
    * @author 高国藩
    * @date 2016年1月8日 上午10:29:11
    * @param conferenceInfo 会议数据
    * @return               新增状态
     */
    @Transactional
    public BaseDto saveMetting(ConferenceInfo conferenceInfo) {
        conferenceInfo.setRegistrationStartTime(DateUtil.getCurDate());
        if (conferenceInfoMapper.insertSelective(conferenceInfo)>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "新增成功");
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "新增失败");
        }
    }

    /**
     * 展示会议列表信息
    * @author 高国藩
    * @date 2016年1月8日 上午11:54:34
    * @param openId      微信标识
    * @return            跳转页面
     */
    public ModelAndView conferenceListView(String openId) {
        List<ConferenceInfoDto> conferenceInfoDtos = conferenceInfoMapper.selectConferenceDtos(openId);
        ModelAndView view = new ModelAndView(View.Conference.CONFERENCE_VIEW_LIST);
        view.addObject("conferenceInfoDtos", conferenceInfoDtos);
        return view;
    }

    /**
     * 查看会议详情
    * @author 高国藩
    * @date 2016年1月8日 下午3:20:23
    * @param conferenceId         会议ID
    * @param status               会议状态
    * @return                     跳转页面
     * @throws ParseException     异常处理
     */
    public ModelAndView conferenceInfoView(Integer conferenceId, Integer status) throws ParseException {
        ModelAndView view = new ModelAndView(View.Conference.CONFERENCE_VIEW_INFO);
        ConferenceInfo conferenceInfo = conferenceInfoMapper.selectByPrimaryKey(conferenceId);
        Integer earning = enrollInfoMapper.selectConferenceEarning(conferenceId);                //报名费总收入
        List<DetailsDto> detailsDtos = enrollInfoMapper.selectConferenceDetails(conferenceId);
        List<EnrollInfo> hasPay = enrollInfoMapper.selectHasPay(conferenceId);                   //已经成功报名
        List<EnrollInfo> enrollInfos = enrollInfoMapper.selectHasAdmission(conferenceId);        //已经入场人员
        Integer mainPays = 0;      //一级奖励支出
        Integer branchPays = 0;    //二级奖励支出
        for (DetailsDto detailsDto : detailsDtos) {
            mainPays += detailsDto.getMainAmount();
            branchPays += detailsDto.getBranchAmount();
        }
        view.addObject("conferenceInfo", conferenceInfo);
        view.addObject("detailsDtos", detailsDtos);
        view.addObject("mainPays", mainPays);
        view.addObject("branchPays", branchPays);
        view.addObject("haPays", hasPay.size());
        view.addObject("earning", earning);
        view.addObject("hasAdmission", enrollInfos.size());
        view.addObject("status", status);
        
        String str = conferenceInfo.getHoldTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Date date = sdf.parse(str);
        view.addObject("date", date);
        
        return view;
    }

    /**
     * 进入会议修改页面
    * @author 高国藩
    * @date 2016年1月8日 下午3:45:21
    * @param conferenceId   会议标识
    * @return               页面
     */
    public ModelAndView updateConferenceInfoView(Integer conferenceId) {
        ModelAndView view = new ModelAndView(View.Conference.CONFERENCE_VIEW_UPDATE);
        ConferenceInfo conferenceInfo = conferenceInfoMapper.selectByPrimaryKey(conferenceId);
        view.addObject("conferenceInfo", conferenceInfo);
        return view;
    }
    
    /**
     * 修改会议数据
    * @author 高国藩
    * @date 2016年1月8日 下午3:45:51
    * @param conferenceInfo      会议数据
    * @return                    修改状态
     */
    @Transactional
    public BaseDto updateMetting(ConferenceInfo conferenceInfo) {
        conferenceInfo.setRegistrationStartTime(DateUtil.getCurDate());
        conferenceInfoMapper.updateByPrimaryKeySelective(conferenceInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "修改成功");
    }

    /**
     * 进入分享会议的展示页面,使用占位符传递上级推荐人以及会议id
    * @author 高国藩
    * @date 2016年1月8日 下午3:57:53
    * @param fromUser                       上级推荐人
    * @param conferenceId                   会议ID
    * @param openId                         当前访问页面的微信标识
    * @return                               跳转至分享页面
     * @throws ParseException               异常处理
     */
    public ModelAndView shareConferenceView(Integer conferenceId, Integer fromUser, String openId) throws ParseException {
        ModelAndView view = new ModelAndView(View.Conference.SHARE_CONFERENCE_VIEW);
        ConferenceInfo conferenceInfo = conferenceInfoMapper.selectByPrimaryKey(conferenceId);
        //查看该人员是否已经报名参会
        EnrollInfo enrollInfo = enrollInfoMapper.selectByOpenId(openId, conferenceId);
        List<EnrollInfo> mainAware = null;
        List<EnrollInfo> branchAware = null;
        //查看是否是渠道商,即会议发起人
        WechatAgent agent = wechatAgentMapper.selectByPrimaryKey(openId);
        if (agent!=null&&conferenceInfo.getAgentId()==agent.getAgentId()){
            ConferenceInfoDto conInfo = conferenceInfoMapper.selectConferenceDto(conferenceId);
            return conferenceInfoView(conferenceId, conInfo.getStatus());
        }
        if (enrollInfo!=null){
            mainAware = enrollInfoMapper.selectMainAware(enrollInfo.getPersonnelId(), conferenceId);
            EnrollInfo demo = new EnrollInfo();
            demo.setPersonnelId(-1);
            mainAware.add(demo);
            branchAware = enrollInfoMapper.selectBranchAware(mainAware, conferenceId);
            mainAware.remove(mainAware.size()-1);
            //如果已经付款,那么分享链接中的推荐人就是他
            if (enrollInfo.getIsPay() == 1){
                fromUser = enrollInfo.getPersonnelId();
            }
        }
        view.addObject("conferenceInfo", conferenceInfo);      //会议信息
        view.addObject("fromUser", fromUser);                  //当前页面预备分享着的ID
        view.addObject("enrollInfo", enrollInfo);              //该人员的报名信息(null)
        if (enrollInfo!=null){
            view.addObject("mainAware", mainAware);            //一级奖励人员
            view.addObject("branchAware", branchAware);        //二级奖励人员
            view.addObject("mainCount", mainAware.size());     //一级奖励人员数量
            view.addObject("branchCount", branchAware.size()); //二级奖励人员数量
            view.addObject("amount", mainAware.size()*conferenceInfo.getMainAward()+branchAware.size()*conferenceInfo.getBranchAward());
        }
        String str = conferenceInfo.getHoldTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Date date = sdf.parse(str);
        view.addObject("date", date);
        
        String str1 = conferenceInfo.getRegistrationEndTime();
        Date date2 = sdf.parse(str1);
        view.addObject("date2", date2);
        return view;
    }

    /**
     * 参加报名
    * @author 高国藩
    * @date 2016年1月8日 下午7:27:20
    * @param enrollInfo  报名数据
    * @return            状态
     */
    @Transactional
    public BaseDto actionJoinMetting(EnrollInfo enrollInfo) {
        ConferenceInfo conferenceInfo = conferenceInfoMapper.selectByPrimaryKey(enrollInfo.getConferenceId());
        if (conferenceInfo.getRegistrationAmount().intValue() == 0){
            enrollInfo.setIsPay(1);
        }
        else {
            enrollInfo.setIsPay(0);
        }
        enrollInfo.setCreateTime(DateUtil.getCurTime());
        enrollInfoMapper.insertSelective(enrollInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, enrollInfo);
    }

    /**
     * 报名人数查看
    * @author 高国藩
    * @date 2016年1月9日 下午8:15:46
    * @param conferenceId      会议ID
    * @return                  跳转页面
     */
    public ModelAndView viewConferenceRegist(Integer conferenceId) {
        List<EnrollInfo> hasPay = enrollInfoMapper.selectHasPay(conferenceId);
        List<EnrollInfo> noPay = enrollInfoMapper.selectNoPay(conferenceId);
        ModelAndView view = new ModelAndView(View.Conference.CONFERENCE_VIEW_REGIST);
        view.addObject("hasPay", hasPay);
        view.addObject("noPay", noPay);
        return view;
    }

    /**
     * 查看本次会议的收支明细
    * @author 高国藩
    * @date 2016年1月9日 下午8:16:39
    * @param conferenceId      会议ID
    * @return                  跳转页面
     * @throws ParseException 
     */
    public ModelAndView viewConferenceDetails(Integer conferenceId) throws ParseException {
        ModelAndView view = new ModelAndView(View.Conference.VIEW_CONFERENCE_DETAILS);
        ConferenceInfo conferenceInfo = conferenceInfoMapper.selectByPrimaryKey(conferenceId);
        Integer earning = enrollInfoMapper.selectConferenceEarning(conferenceId);
        List<DetailsDto> detailsDtos = enrollInfoMapper.selectConferenceDetails(conferenceId);
        Integer pays = 0;
        for (DetailsDto detailsDto : detailsDtos) {
            pays += detailsDto.getBranchAmount() + detailsDto.getMainAmount();
        }
        view.addObject("conferenceInfo", conferenceInfo);
        view.addObject("detailsDtos", detailsDtos);
        view.addObject("pays", pays);
        view.addObject("earning", earning);
        String str = conferenceInfo.getHoldTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Date date = sdf.parse(str);
        view.addObject("date", date);
        return view;
    }

    /**
     * 报名支付成功微信回调接口
    * @author 高国藩
    * @date 2016年1月11日 下午2:25:19
    * @param personnelId      报名生成的主键ID
    * @param conferenceId     会议ID
     */
    @Transactional
    public void wechatCallBackConferencePay(Integer personnelId, Integer conferenceId) {
        EnrollInfo enrollInfo = new EnrollInfo();
        enrollInfo.setPersonnelId(personnelId);
        enrollInfo.setConferenceId(conferenceId);
        enrollInfo.setIsPay(1);
        enrollInfoMapper.updateByPrimaryKeySelective(enrollInfo);
    }

    /**
     * 查看本次会议的到场情况
    * @author 高国藩
    * @date 2016年1月15日 上午11:49:25
    * @param conferenceId     会议ID
    * @return                 跳转页面
     */
    public ModelAndView viewConferenceAdmission(Integer conferenceId) {
        List<EnrollInfo> hasAdmission = enrollInfoMapper.selectHasAdmission(conferenceId);
        List<EnrollInfo> noAdmission = enrollInfoMapper.selectNoAdmission(conferenceId);
        ModelAndView view = new ModelAndView(View.Conference.CONFERENCE_VIEW_ADMISSION);
        view.addObject("hasAdmission", hasAdmission);
        view.addObject("noAdmission", noAdmission);
        view.addObject("hasAdmissionLength", hasAdmission.size()+0);
        view.addObject("noAdmissionLength", noAdmission.size()+0);
        return view;
    }

    /**
     * 对人员进行入场签到或者取消签到
    * @author 高国藩
    * @date 2016年1月15日 下午3:36:28
    * @param enrollInfo     人员数据
    * @return               修改状态
     */
    @Transactional
    public BaseDto actionAdmission(EnrollInfo enrollInfo) {
        enrollInfoMapper.updateByPrimaryKeySelective(enrollInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    
    /**
     * 根据会议标识查询会议信息
    * @author 张进军
    * @date Mar 4, 2016 1:59:32 PM
    * @param conferenceId   会议标识
    * @return   会议信息
     */
    public ConferenceInfo getConferenceInfoById(int conferenceId) {
        return conferenceInfoMapper.selectByPrimaryKey(conferenceId);
    }
}
