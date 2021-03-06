package com.zefun.wechat.service;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.app.common.param.StaffAppointParam;
import com.zefun.app.common.param.UpdatePwdParam;
import com.zefun.common.consts.App;
import com.zefun.common.consts.AppConfig;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.EmployeeAttendanceDateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.AppointmentBaseDto;
import com.zefun.web.dto.AttendanceRecordDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.EmployeeCommissionDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EmployeeObjective;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.EmployeeAttendanceMapper;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;
import com.zefun.web.mapper.MemberAppointmentMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.ShiftMahjongMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.RabbitService;
import com.zefun.web.service.RedisService;
import com.zefun.web.service.ShiftMahjongService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 移动端员工的订单服务逻辑类
* @author 王大爷
* @date Oct 13, 2015 9:29:53 PM 
*/
@Service
public class StaffCentreService {
	
	/** 员工考勤映射 */
	@Autowired
	private EmployeeAttendanceMapper employeeAttendanceMapper;
    
    /** 员工*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /**员工提成业绩操作对象*/
    @Autowired
    private EmployeeCommissionMapper employeeCommissionMapper;
    
    /**redis操作对象*/
    @Autowired
    private RedisService redisService;
    
    /**会员信息服务对象*/
    @Autowired
    private MemberInfoService memberInfoService;
    
    /**消费队列服务对象*/
    @Autowired
    private RabbitService rabbitService;
    
    /** 会员基本信息*/
    @Autowired MemberInfoMapper memberInfoMapper;
    
    /**会员预约操作对象*/
    @Autowired
    private MemberAppointmentMapper memberAppointmentMapper;
    
    /** */
    /*@Autowired private StaffService staffService;*/
    
    /** 轮派员工信息*/
    @Autowired private ShiftMahjongService shiftMahjongService;
    
    /** 项目*/
    /*@Autowired private ProjectInfoMapper projectInfoMapper;*/
    
    /** 员工目标*/
    @Autowired private EmployeeObjectiveMapper employeeObjectiveMapper;
    
    /** 轮牌信息*/
    @Autowired private ShiftMahjongMapper shiftMahjongMapper;
    
    /** 员工账号操作对象 */
    @Autowired private UserAccountMapper userAccountMapper;
    
    
    /**日志记录对象*/
//    private Logger logger = Logger.getLogger(StaffCentreService.class);
    
    /**
     * 个人中心页面
    * @author 王大爷
    * @date 2015年10月18日 下午2:18:30
    * @param employeeId 员工标识
    * @return ModelAndView
     */
    public ModelAndView staffCenter(Integer employeeId){
        ModelAndView mav = new ModelAndView(View.StaffPage.STAFF_CENTER);
        EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeId);
        mav.addObject("employeeInfo", employeeInfo);
        mav.addObject("signStatus", redisService.hget(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, employeeId));
        return mav;
    }
    
    
    /**
     * 员工个人信息页面
    * @author 张进军
    * @date Dec 11, 2015 9:42:54 PM
    * @param employeeId 员工标识
    * @return   员工个人信息页面
     */
    public ModelAndView staffInfo(int employeeId){
        ModelAndView mav = new ModelAndView(View.StaffPage.STAFF_INFO);
        EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeId);
        mav.addObject("employeeInfo", employeeInfo);
        return mav;
    }
    
    
    /**
     * 修改员工密码
    * @author 张进军
    * @date Dec 11, 2015 9:55:10 PM
    * @param employeeId 员工标识
    * @param oldPwd     旧密码
    * @param newPwd     新密码
    * @return   成功返回码为0，失败为其他错误码
     */
    public BaseDto updatePwd(Integer employeeId, String oldPwd, String newPwd){
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(employeeId);
        
        //检查用户密码
        if (!StringUtil.md5(oldPwd + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }
        
        String hash = StringUtil.encryptPwd(newPwd);
        newPwd = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        userAccount.setUserPwd(newPwd);
        userAccount.setPwdSalt(passwordSalt);
        userAccount.setUpdateTime(DateUtil.getCurTime());
        userAccountMapper.updateByPrimaryKey(userAccount);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 
     * updateEmpPwd:(用于app端修改员工密码的接口实现).   
     * @author michael  
     * @param param UpdatePwdParam
     * @return  BaseDto
     * @since JDK 1.8
     */
    public BaseDto updateEmpPwd(UpdatePwdParam param){
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(param.getEmployeeId());
        if (userAccount==null){
        	return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL , "员工id错误");
        }
        //检查用户密码
        if (!StringUtil.md5(param.getOldPwd() + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }
        
        String hash = StringUtil.encryptPwd(param.getNewPwd());
        String newPrecessedPwd = param.getNewPwd();
        newPrecessedPwd = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        userAccount.setUserPwd(newPrecessedPwd);
        userAccount.setPwdSalt(passwordSalt);
        userAccount.setUpdateTime(DateUtil.getCurTime());
        userAccountMapper.updateByPrimaryKey(userAccount);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES , "密码修改成功");
    }
    
    
    /**
     * 查询员工个人预约列表
    * @author 张进军
    * @date Oct 28, 2015 9:11:09 PM
    * @param employeeId 员工标识
    * @param type       预约类型(1:预约中，2:已确认，3:已取消)
    * @return   员工预约列表页面
     */
    public ModelAndView staffAppoint(int employeeId, int type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("employeeId", employeeId);
        map.put("appointType", "1");
        List<AppointmentBaseDto> appointList1 = memberAppointmentMapper.selectAppointmentByEmployeeId(map);
        map.put("appointType", "2");
        List<AppointmentBaseDto> appointList2 = memberAppointmentMapper.selectAppointmentByEmployeeId(map);
        map.put("appointType", "3");
        List<AppointmentBaseDto> appointList3 = memberAppointmentMapper.selectAppointmentByEmployeeId(map);
        
        if (!appointList1.isEmpty()) {
            for (AppointmentBaseDto appointment : appointList1) {
                appointment.setMemberInfo(memberInfoService.getMemberBaseInfo(appointment.getMemberId(), true));
            }
        }
        
        if (!appointList2.isEmpty()) {
            for (AppointmentBaseDto appointment : appointList2) {
                appointment.setMemberInfo(memberInfoService.getMemberBaseInfo(appointment.getMemberId(), true));
            }
        }
        
        if (!appointList3.isEmpty()) {
            for (AppointmentBaseDto appointment : appointList3) {
                appointment.setMemberInfo(memberInfoService.getMemberBaseInfo(appointment.getMemberId(), true));
            }
        }
        
        ModelAndView mav = new ModelAndView(View.StaffPage.STAFF_APPOINT);
        mav.addObject("type", type);
        mav.addObject("appointmentList1", appointList1);
        mav.addObject("appointmentList2", appointList2);
        mav.addObject("appointmentList3", appointList3);
        return mav;
    }
    
    /**
     * 
     * staffAppAppoint:(实现员工预约接口实现).   
     * @author michael 
     * @param param StaffAppointParam
     * @return  BaseDto
     */
    public BaseDto staffAppAppoint(StaffAppointParam param) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("employeeId", param.getEmployeeId());
        /**定义默认值为预约中*/
        map.put("appointType", AppConfig.APP_APPOINT_STATUS_APPOINTING);
        if (param.getAppointType() == AppConfig.APP_APPOINT_TYPE_APPOINTED) {
            map.put("appointType", AppConfig.APP_APPOINT_STATUS_CONFIRED);
        } 
        else if (param.getAppointType() == AppConfig.APP_APPOINT_TYPE_CANCEL) {
        	/**
        	 * APP_APPOINT_STATUS_CANCELED = 4;
        	 * APP_APPOINT_STATUS_REJECTED = 5;
        	 */
            map.put("appointType", "3");
        }
        List<AppointmentBaseDto> appointList = memberAppointmentMapper.selectAppointmentByEmployeeId(map);
        if (!appointList.isEmpty()) {
            for (AppointmentBaseDto appointment : appointList) {
                appointment.setMemberInfo(memberInfoService.getMemberBaseInfo(appointment.getMemberId(), true));
            }
        }
        BaseDto bto = new BaseDto();
        bto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
        bto.setMsg(appointList);
        return bto;
    }
    
    
    /**
     * 同意/拒绝预约
    * @author 张进军
    * @date Nov 4, 2015 10:38:27 AM
    * @param type           操作类型(1:同意，2:拒绝)
    * @param storeId        门店标识
    * @param employeeId     员工标识
    * @param appointmentId  预约标识
    * @param memberId       会员标识
    * @param reason         取消原因
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto appointOperate(int type, int storeId, int employeeId, int appointmentId, int memberId, String reason){
        String curTime = DateUtil.getCurTime();
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setAppointmentId(appointmentId);
        memberAppointment.setUpdateTime(curTime);
        memberAppointment.setAppointmentStatus(2);
        if (type == 2) {
            memberAppointment.setCancelReason(reason);
            memberAppointment.setAppointmentStatus(5);
        }
        memberAppointmentMapper.updateByPrimaryKey(memberAppointment);
        
        //发送同意预约通知给会员
        String openId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
        if (StringUtils.isNotBlank(openId)) {
            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
            String url = App.System.SERVER_BASE_URL 
                    + Url.MemberCenter.VIEW_APPOINTMENT_LIST.replace("{storeId}", storeId + "").replace("{businessType}", "1");
            if (type == 2) {
                url = App.System.SERVER_BASE_URL 
                        + Url.MemberCenter.VIEW_ORDER_APPOINTMENT.replace("{storeId}", storeId + "").replace("{businessType}", "1")
                        + "?selectStoreId=" + memberInfo.getStoreId();
            }
            AppointmentBaseDto obj = memberAppointmentMapper.selectAppointmentByAppointmentId(appointmentId);
            rabbitService.sendAppointmentResultNotice(type, storeId, url, openId, 
                    memberInfo.getName(), memberInfo.getLevelName(), obj.getCategory().getCategoryName(), obj.getAppointmentTime(), reason);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 启动预约
    * @author 王大爷
    * @date 2015年11月4日 上午11:30:59
    * @param appointmentId 预约标识
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     */
    /*public BaseDto startAppoint(Integer appointmentId, Integer storeId, Integer lastOperatorId){
        
        MemberAppointment memberAppointment = memberAppointmentMapper.selectByPrimaryKey(appointmentId);
        
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(memberAppointment.getProjectId());
        
        //查询预约员工的轮牌员工标识
        Map<String, Integer> shiftMap = new HashMap<String, Integer>();
        shiftMap.put("projectId", memberAppointment.getProjectId());
        shiftMap.put("num", memberAppointment.getProjectStepOrder());
        shiftMap.put("employeesId", memberAppointment.getEmployeeId());
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftEmployeeListByEmployeeId(shiftMap);
        
        //组装预约员工
        List<Map<String, Object>> alist = new ArrayList<Map<String, Object>>();
        Map<String, Object> amap = new HashMap<String, Object>();
        amap.put("projectNum", 0);
        amap.put("orderNum", memberAppointment.getProjectStepOrder());
        amap.put("projectId", memberAppointment.getProjectId());
        amap.put("shiftMahjongEmployeeId", shiftMahjongEmployee.getShiftMahjongEmployeeId());
        amap.put("employeeId", memberAppointment.getEmployeeId());
        amap.put("isType", 1);
        alist.add(amap);
        
        //组装预约项目
        List<Map<String, Object>> blist = new ArrayList<Map<String, Object>>();
        Map<String, Object> bmap = new HashMap<String, Object>();
        bmap.put("orderType", 1);
        bmap.put("projectId", projectInfo.getProjectId());
        bmap.put("projectName", projectInfo.getProjectName());
        bmap.put("projectPriceStr", projectInfo.getProjectPrice());
        bmap.put("projectCount", 1);
        bmap.put("projectImage", projectInfo.getProjectImage());
        blist.add(bmap);
        
        List<Map<String, Object>> clist = new ArrayList<Map<String, Object>>();
        Map<String, Object> cmap = new HashMap<String, Object>();
        cmap.put("projectNum", 0);
        cmap.put("orderNum", 1);
        cmap.put("projectId", memberAppointment.getProjectId());
        clist.add(cmap);
        //开单
        String sex = memberInfoService.getMemberBaseInfo(memberAppointment.getMemberId(), false).getSex();
        BaseDto dto = staffService.addOrder(JSONArray.fromObject(blist).toString(), JSONArray.fromObject(alist).toString(), 
                JSONArray.fromObject(clist).toString(), memberAppointment.getMemberId(), sex, storeId, lastOperatorId, 1, null);
        //修改预约单状态
        MemberAppointment record = new MemberAppointment();
        record.setAppointmentId(appointmentId);
        record.setServiceTime(DateUtil.getCurTime());
        record.setAppointmentStatus(3);
        memberAppointmentMapper.updateByPrimaryKey(record);
        
        return dto;
    }*/
    
    
    /**
     * 我的业绩
    * @author 王大爷
    * @date 2015年11月16日 下午7:49:25
    * @param employeeId 员工标识
    * @return ModelAndView
     */
    public ModelAndView staffEarning(Integer employeeId) {
        
        ModelAndView mav = new ModelAndView(View.StaffPage.STAFF_ERANING);
        
        String beginTime = null;
        String endTime = null;
        
        beginTime = DateUtil.getCurDate();
        endTime = DateUtil.getCurDate();

        
        Map<String, Object> commissionMap = new HashMap<String, Object>();
        commissionMap.put("employeeId", employeeId);
        commissionMap.put("beginTime", beginTime);
        commissionMap.put("endTime", endTime);
        
        //计算今日提成金额
        BigDecimal commissionValue = employeeCommissionMapper.selectBySectionDayCommission(commissionMap);
        
        mav.addObject("commissionValue", commissionValue);
        
        //今天
        List<Integer> projectOrderTypeList = new ArrayList<Integer>();
        projectOrderTypeList.add(1);
        Map<String, Object> projectCalculateMap = commissionMap;
        projectCalculateMap.put("orderTypeList", projectOrderTypeList);
        //劳动业绩
        BigDecimal toDayProjectCalculate = employeeCommissionMapper.selectBySectionDayCalculate(projectCalculateMap);
        
        mav.addObject("toDayProjectCalculate", toDayProjectCalculate);
        
        //指定业绩
        BigDecimal toDayAssignProjectCalculate = employeeCommissionMapper.selectBySectionAssignProjectCalculate(commissionMap);
        
        mav.addObject("toDayAssignProjectCalculate", toDayAssignProjectCalculate);
        
        List<Integer> comboOrderTypeList = new ArrayList<Integer>();
        comboOrderTypeList.add(3);
        Map<String, Object> comboCalculateMap = commissionMap;
        comboCalculateMap.put("orderTypeList", comboOrderTypeList);
        //疗程业绩
        BigDecimal toDayComboCalculate = employeeCommissionMapper.selectBySectionDayCalculate(comboCalculateMap);
        
        mav.addObject("toDayComboCalculate", toDayComboCalculate);
        
        List<Integer> goodsOrderTypeList = new ArrayList<Integer>();
        goodsOrderTypeList.add(2);
        Map<String, Object> goodsCalculateMap = commissionMap;
        goodsCalculateMap.put("orderTypeList", goodsOrderTypeList);
        //商品业绩
        BigDecimal toDayGoodsCalculate = employeeCommissionMapper.selectBySectionDayCalculate(goodsCalculateMap);
        
        mav.addObject("toDayGoodsCalculate", toDayGoodsCalculate);
        
        List<Integer> chargeOrderTypeList = new ArrayList<Integer>();
        chargeOrderTypeList.add(4);
        chargeOrderTypeList.add(5);
        chargeOrderTypeList.add(6);
        Map<String, Object> chargeCalculateMap = commissionMap;
        chargeCalculateMap.put("orderTypeList", chargeOrderTypeList);
        //开卡充值业绩
        BigDecimal toDayChargeCalculate = employeeCommissionMapper.selectBySectionDayCalculate(chargeCalculateMap);
        
        mav.addObject("toDayChargeCalculate", toDayChargeCalculate);
        
        //昨天
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dates = df.format(DateUtil.getDateDaysBefore(new Date(), 1));
        beginTime = dates;
        endTime = dates;
        
        Map<String, Object> yesterdayCommissionMap = new HashMap<String, Object>();
        yesterdayCommissionMap.put("employeeId", employeeId);
        yesterdayCommissionMap.put("beginTime", beginTime);
        yesterdayCommissionMap.put("endTime", endTime);
        
        List<Integer> yesterdayProjectOrderTypeList = new ArrayList<Integer>();
        yesterdayProjectOrderTypeList.add(1);
        Map<String, Object> yesterdayProjectCalculateMap = yesterdayCommissionMap;
        yesterdayProjectCalculateMap.put("orderTypeList", yesterdayProjectOrderTypeList);
        //劳动业绩
        BigDecimal yesterdayProjectCalculate = employeeCommissionMapper.selectBySectionDayCalculate(yesterdayProjectCalculateMap);
        
        mav.addObject("yesterdayProjectCalculate", yesterdayProjectCalculate);
        
        //指定业绩
        BigDecimal yesterdayAssignProjectCalculate = employeeCommissionMapper.selectBySectionAssignProjectCalculate(yesterdayCommissionMap);
        
        mav.addObject("yesterdayAssignProjectCalculate", yesterdayAssignProjectCalculate);
        
        List<Integer> yesterdayComboOrderTypeList = new ArrayList<Integer>();
        yesterdayComboOrderTypeList.add(3);
        Map<String, Object> yesterdayComboCalculateMap = yesterdayCommissionMap;
        yesterdayComboCalculateMap.put("orderTypeList", yesterdayComboOrderTypeList);
        //疗程业绩
        BigDecimal yesterdayComboCalculate = employeeCommissionMapper.selectBySectionDayCalculate(yesterdayComboCalculateMap);
        
        mav.addObject("yesterdayComboCalculate", yesterdayComboCalculate);
        
        List<Integer> yesterdayGoodsOrderTypeList = new ArrayList<Integer>();
        yesterdayGoodsOrderTypeList.add(2);
        Map<String, Object> yesterdayGoodsCalculateMap = yesterdayCommissionMap;
        yesterdayGoodsCalculateMap.put("orderTypeList", yesterdayGoodsOrderTypeList);
        //商品业绩
        BigDecimal yesterdayGoodsCalculate = employeeCommissionMapper.selectBySectionDayCalculate(yesterdayGoodsCalculateMap);
        
        mav.addObject("yesterdayGoodsCalculate", yesterdayGoodsCalculate);
        
        List<Integer> yesterdayChargeOrderTypeList = new ArrayList<Integer>();
        yesterdayChargeOrderTypeList.add(4);
        yesterdayChargeOrderTypeList.add(5);
        yesterdayChargeOrderTypeList.add(6);
        Map<String, Object> yesterdayChargeCalculateMap = yesterdayCommissionMap;
        yesterdayChargeCalculateMap.put("orderTypeList", yesterdayChargeOrderTypeList);
        
        //开卡充值业绩
        BigDecimal yesterdayChargeCalculate = employeeCommissionMapper.selectBySectionDayCalculate(yesterdayChargeCalculateMap);
        
        mav.addObject("yesterdayChargeCalculate", yesterdayChargeCalculate);
        
        //本月
        beginTime = DateUtil.getMinMonthDateStr();
        endTime = DateUtil.getMaxMonthDateStr();
        
        Map<String, Object> monthCommissionMap = new HashMap<String, Object>();
        monthCommissionMap.put("employeeId", employeeId);
        monthCommissionMap.put("beginTime", beginTime);
        monthCommissionMap.put("endTime", endTime);
        
        //计算本月提成金额
        BigDecimal monthCommissionValue = employeeCommissionMapper.selectBySectionDayCommission(monthCommissionMap);
        
        mav.addObject("monthCommissionValue", monthCommissionValue);
        
        List<Integer> monthProjectOrderTypeList = new ArrayList<Integer>();
        monthProjectOrderTypeList.add(1);
        Map<String, Object> monthProjectCalculateMap = monthCommissionMap;
        monthProjectCalculateMap.put("orderTypeList", monthProjectOrderTypeList);
        //劳动业绩
        BigDecimal monthProjectCalculate = employeeCommissionMapper.selectBySectionDayCalculate(monthProjectCalculateMap);
        
        mav.addObject("monthProjectCalculate", monthProjectCalculate);
        
        //指定业绩
        BigDecimal monthAssignProjectCalculate = employeeCommissionMapper.selectBySectionAssignProjectCalculate(monthCommissionMap);
        
        mav.addObject("monthAssignProjectCalculate", monthAssignProjectCalculate);
        
        List<Integer> monthComboOrderTypeList = new ArrayList<Integer>();
        monthComboOrderTypeList.add(3);
        Map<String, Object> monthComboCalculateMap = monthCommissionMap;
        monthComboCalculateMap.put("orderTypeList", monthComboOrderTypeList);
        //疗程业绩
        BigDecimal monthComboCalculate = employeeCommissionMapper.selectBySectionDayCalculate(monthComboCalculateMap);
        
        mav.addObject("monthComboCalculate", monthComboCalculate);
        
        List<Integer> monthGoodsOrderTypeList = new ArrayList<Integer>();
        monthGoodsOrderTypeList.add(2);
        Map<String, Object> monthGoodsCalculateMap = monthCommissionMap;
        monthGoodsCalculateMap.put("orderTypeList", monthGoodsOrderTypeList);
        //商品业绩
        BigDecimal monthGoodsCalculate = employeeCommissionMapper.selectBySectionDayCalculate(monthGoodsCalculateMap);
        
        mav.addObject("monthGoodsCalculate", monthGoodsCalculate);
        
        List<Integer> monthChargeOrderTypeList = new ArrayList<Integer>();
        monthChargeOrderTypeList.add(4);
        monthChargeOrderTypeList.add(5);
        monthChargeOrderTypeList.add(6);
        Map<String, Object> monthChargeCalculateMap = monthCommissionMap;
        monthChargeCalculateMap.put("orderTypeList", monthChargeOrderTypeList);
        
        //开卡充值业绩
        BigDecimal monthChargeCalculate = employeeCommissionMapper.selectBySectionDayCalculate(monthChargeCalculateMap);
        
        mav.addObject("monthChargeCalculate", monthChargeCalculate);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        EmployeeObjective selectObjective = new EmployeeObjective();
        selectObjective.setEmployeeId(employeeId);
        selectObjective.setObjectiveMonth(dateFormat.format(new Date()));
        //查询单月目标
        EmployeeObjective employeeObjective = employeeObjectiveMapper.getObjectiveInfo(selectObjective);
        if (employeeObjective != null) {
            BigDecimal totalProjectCalculate = employeeObjective.getTotalProjectSales();
            mav.addObject("totalProjectCalculate", totalProjectCalculate);
            BigDecimal totalAssignProjectCalculate = employeeObjective.getAssignProjectSales();
            mav.addObject("totalAssignProjectCalculate", totalAssignProjectCalculate);
            BigDecimal totalComboCalculate = employeeObjective.getComboSales();
            mav.addObject("totalComboCalculate", totalComboCalculate);
            BigDecimal totalGoodsCalculate = employeeObjective.getGoodsSales();
            mav.addObject("totalGoodsCalculate", totalGoodsCalculate);
            BigDecimal totalChargeCalculate = employeeObjective.getChargeSales();
            mav.addObject("totalChargeCalculate", totalChargeCalculate);
        }
        else {
            mav.addObject("totalProjectCalculate", 0);
            mav.addObject("totalAssignProjectCalculate", 0);
            mav.addObject("totalComboCalculate", 0);
            mav.addObject("totalGoodsCalculate", 0);
            mav.addObject("totalChargeCalculate", 0);
        }
        
        List<EmployeeObjective> employeeObjectiveList = employeeObjectiveMapper.selectObjectiveByEmployeeId(employeeId);
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        for (int i = 0; i < employeeObjectiveList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            EmployeeObjective obj = employeeObjectiveList.get(i);
            //劳动业绩
            map.put("totalProjectSales", obj.getTotalProjectSales());
            map.put("actualTotalProjectSales", obj.getActualTotalProjectSales());
            
            BigDecimal zore = new BigDecimal(0);
            
            if (obj.getTotalProjectSales().compareTo(zore) <= 0) {
                map.put("projectScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualTotalProjectSales().divide(obj.getTotalProjectSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            //指定业绩
            map.put("assignProjectSales", obj.getAssignProjectSales());
            map.put("actualAssignProjectSales", obj.getActualAssignProjectSales());
            if (obj.getAssignProjectSales().compareTo(zore) <= 0)  {
                map.put("assignProjectScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualAssignProjectSales().divide(obj.getAssignProjectSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            //疗程销售业绩
            map.put("comboSales", obj.getComboSales());
            map.put("actualComboSales", obj.getActualComboSales());
            if (obj.getComboSales().compareTo(zore) <= 0) {
                map.put("comboScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualComboSales().divide(obj.getComboSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            //商品销售业绩
            map.put("goodsSales", obj.getGoodsSales());
            map.put("actualGoodsSales", obj.getActualGoodsSales());
            if (obj.getGoodsSales().compareTo(zore) <= 0) {
                map.put("goodsScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualGoodsSales().divide(obj.getGoodsSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            //卡项销售业绩
            map.put("chargeSales", obj.getChargeSales());
            map.put("actualChargeSales", obj.getActualChargeSales());
            if (obj.getChargeSales().compareTo(zore) <= 0) {
                map.put("chargeScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualChargeSales().divide(obj.getChargeSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            map.put("dates", obj.getObjectiveMonth());
            list.add(map);
        }
        
        mav.addObject("listStr", JSONArray.fromObject(list).toString());
        
        return mav;
    }
    
    /**
     * 业绩排行
    * @author 王大爷
    * @date 2015年8月19日 下午2:31:48
    * @param employeeId 员工id
    * @param chooseType 时间类型
    * @return ModelAndView
     */
    public ModelAndView allEarning(Integer employeeId, Integer chooseType){
        ModelAndView mav = new ModelAndView();
        if (chooseType == null) {
        	chooseType = 1;
        }
        Map<String, Object> map = selectEmployeeData(employeeId, chooseType);
        mav.addObject("hashMap", map);
        mav.addObject("chooseType", chooseType);
        mav.setViewName(View.StaffPage.ALL_ERANING);
        
        return mav;
    }
    
    /**
     * 
     * @param employeeId 员工标识
     * @param chooseType 选择时间
     * @return Map<String, Object>
     */
    public Map<String, Object> selectEmployeeData(Integer employeeId, Integer chooseType) {
    	
    	Map<String, Object> objMap = new HashMap<String, Object>();
    	String endTime = "";
        String beginTime = "";
        
        String nowDay = DateUtil.getCurDate();
        
        if (chooseType == 1) {
        	endTime = nowDay;
        	beginTime = nowDay;
        }
        else if (chooseType == 2) {
        	endTime = DateUtil.getPreviousSundayStr();
        	beginTime = DateUtil.getCurrentMondayStr();
        }
        else {
        	endTime = DateUtil.getMaxMonthDateStr();
        	beginTime = DateUtil.getMinMonthDateStr();
        }
        
        List<Integer> idList = employeeInfoMapper.selectEmployeeInfoByEmployeeIdPositionId(employeeId);
        
        List<Map<String, Object>> dataList = selectListMap(idList, beginTime, endTime);
        objMap.put("dataList", dataList);
        
        List<Map<String, Object>> assignProjectList = againReorder(dataList, 1);
        objMap.put("assignProjectList", assignProjectList);
        
        List<Map<String, Object>> scaleList = againReorder(dataList, 2);
        objMap.put("scaleList", scaleList);
        
        List<Map<String, Object>> comboList = againReorder(dataList, 3);
        objMap.put("comboList", comboList);
        
        List<Map<String, Object>> goodsList = againReorder(dataList, 4);
        objMap.put("goodsList", goodsList);
        
        List<Map<String, Object>> chargeList = againReorder(dataList, 5);
        objMap.put("chargeList", chargeList);
        
        Map<String, Object> toDayMap = selectTotalValue(idList, beginTime, endTime);
        
        objMap.put("totalProjectScale", toDayMap.get("totalProjectScale"));
        objMap.put("totalAssignProjectScale", toDayMap.get("totalAssignProjectScale"));
        objMap.put("totalComboScale", toDayMap.get("totalComboScale"));
        objMap.put("totalGoodsScale", toDayMap.get("totalGoodsScale"));
        objMap.put("totalChargeScale", toDayMap.get("totalChargeScale"));
        objMap.put("totalScale", toDayMap.get("totalScale"));
        
        return objMap;
    }
    
    /**
     * 重新排序
    * @author 老王
    * @date 2016年7月14日 下午2:48:51 
    * @param dataList 数据集
    * @param type 比较类型
    * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> againReorder (List<Map<String, Object>> dataList, Integer type) {
    	List<Map<String, Object>> projectList = new ArrayList<Map<String, Object>>(dataList);
    	
    	Collections.sort(projectList, new Comparator<Map<String, Object>>() {
    		 
            
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
               
            	BigDecimal map1value = new BigDecimal(0);
            	BigDecimal map2value = new BigDecimal(0);
            	if (type == 1) {
            		map1value = new BigDecimal(o1.get("assignProjectValue").toString());
            		map2value = new BigDecimal(o2.get("assignProjectValue").toString());
            	}
            	else if (type == 2) {
            		map1value = new BigDecimal(o1.get("scale").toString());
            		map2value = new BigDecimal(o2.get("scale").toString());
            	}
            	else if (type == 3) {
            		map1value = new BigDecimal(o1.get("comboValue").toString());
            		map2value = new BigDecimal(o2.get("comboValue").toString());
            	}
            	else if (type == 4) {
            		map1value = new BigDecimal(o1.get("goodsValue").toString());
            		map2value = new BigDecimal(o2.get("goodsValue").toString());
            	}
            	else if (type == 5) {
            		map1value = new BigDecimal(o1.get("chargeValue").toString());
            		map2value = new BigDecimal(o2.get("chargeValue").toString());
            	}
                return map2value.compareTo(map1value);
            }
        });
    	
    	return projectList;
    }
    
    /**
     * 查询员工对应业绩集合
    * @author 王大爷
    * @date 2015年11月19日 下午5:11:59
    * @param idList 员工标识集合
    * @param beginTime 开始时间
    * @param endTime 结束时间
    * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> selectListMap (List<Integer> idList, String beginTime, String endTime) {
        
        Map<String, Object> commissionMap = new HashMap<String, Object>();
        commissionMap.put("employeeIdList", idList);
        commissionMap.put("beginTime", beginTime);
        commissionMap.put("endTime", endTime);
        
        List<Integer> projectOrderTypeList = new ArrayList<Integer>();
        projectOrderTypeList.add(1);
        Map<String, Object> projectCalculateMap = commissionMap;
        projectCalculateMap.put("orderTypeList", projectOrderTypeList);
        
        List<Map<String, Object>> projectCommission = employeeCommissionMapper.selectByEmployeeIdList(projectCalculateMap);
        
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        List<Integer> projectList = new ArrayList<Integer>(idList);
        
        for (int i = 0; i < projectCommission.size(); i++) {
            Map<String, Object> objMap = projectCommission.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            String employeeId = objMap.get("employeeId").toString();
            String projectValue = objMap.get("commissionCalculate").toString();
            
            map.put("employeeId", employeeId);
            map.put("projectValue", projectValue);
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("employeeId", employeeId);
            parameterMap.put("beginTime", beginTime);
            parameterMap.put("endTime", endTime);
            
            
            map = selectValue(parameterMap, map);
            
            projectList.remove(Integer.valueOf(employeeId));
            
            list.add(map);
        }
        
        //当没有查询出数据，赋值为0.00
        for (int i = 0; i < projectList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            
            map.put("employeeId", projectList.get(i));
            map.put("projectValue", "0.00");
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("employeeId", projectList.get(i));
            parameterMap.put("beginTime", beginTime);
            parameterMap.put("endTime", endTime);
            
            
            map = selectValue(parameterMap, map);
            
            list.add(map);
        }
        return list;
    }
    
    /**
     * 查询员工对应业绩平均值
    * @author 王大爷
    * @date 2015年11月19日 下午5:11:59
    * @param idList 员工标识集合
    * @param beginTime 开始时间
    * @param endTime 结束时间
    * @return List<Map<String, Object>>
     */
    public Map<String, Object> selectTotalValue(List<Integer> idList, String beginTime, String endTime) {
        Integer num = idList.size();
        BigDecimal numDecimal = new BigDecimal(num);
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        Map<String, Object> commissionMap = new HashMap<String, Object>();
        commissionMap.put("employeeIdList", idList);
        commissionMap.put("beginTime", beginTime);
        commissionMap.put("endTime", endTime);
        
        Map<String, BigDecimal> tatalAverage = employeeCommissionMapper.selectByTotalAverageList(commissionMap);
        
        BigDecimal totalProject = tatalAverage.get("totalProject");
        
        BigDecimal totalProjectScale = totalProject.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalProjectScale", totalProjectScale);
        
        BigDecimal totalAssignProject = tatalAverage.get("totalAssignProject");
        
        BigDecimal totalAssignProjectScale = totalAssignProject.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalAssignProjectScale", totalAssignProjectScale);
        
        if (totalProjectScale.compareTo(BigDecimal.ZERO) <= 0) {
            map.put("totalScale", "0.00");
        }
        else {
        	BigDecimal totalScale = employeeCommissionMapper.selectAverageScaleByEmployeeId(commissionMap);
            map.put("totalScale", totalScale);
        }
        
        
        BigDecimal totalCombo = tatalAverage.get("totalCombo");
        
        BigDecimal totalComboScale = totalCombo.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalComboScale", totalComboScale);
        
        BigDecimal totalGoods = tatalAverage.get("totalGoods");
        
        BigDecimal totalGoodsScale = totalGoods.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalGoodsScale", totalGoodsScale);
        
        BigDecimal totalCharge = tatalAverage.get("totalCharge");
        
        BigDecimal totalChargeScale = totalCharge.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalChargeScale", totalChargeScale);
        
        return map;        
    }
    
    /**
     * 查询除劳动业绩外所有业绩
    * @author 王大爷
    * @date 2015年11月19日 下午4:43:08
    * @param commissionMap 时间人员标识
    * @param map map
    * @return Map<String, Object>
     */
    public Map<String, Object> selectValue(Map<String, Object> commissionMap, Map<String, Object> map) {
    	Map<String, Object> calculateObjMap = employeeCommissionMapper.selectByTotalCalculate(commissionMap);
    	
        
        map.put("name", calculateObjMap.get("name"));
        map.put("employeeCode", calculateObjMap.get("employeeCode"));
        map.put("headImage", calculateObjMap.get("headImage"));
    	
        //指定业绩
        BigDecimal assignProjectCalculate = new BigDecimal(calculateObjMap.get("assignProjectCalculate").toString());
        
        map.put("assignProjectValue", assignProjectCalculate);
        
        BigDecimal projectValue = new BigDecimal(map.get("projectValue").toString());
        
        if (projectValue.compareTo(BigDecimal.ZERO) <= 0) {
            map.put("scale", "0.00");
        }
        else {
        	BigDecimal scale = employeeCommissionMapper.selectScaleByEmployeeId(commissionMap);
            map.put("scale", scale);
        }
        
        //疗程业绩
        BigDecimal comboCalculate = new BigDecimal(calculateObjMap.get("comboCalculate").toString());
        
        map.put("comboValue", comboCalculate);
                
        //商品业绩
        BigDecimal goodsCalculate = new BigDecimal(calculateObjMap.get("goodsCalculate").toString());
        
        map.put("goodsValue", goodsCalculate);
                
        //开卡充值业绩
        BigDecimal chargeCalculate = new BigDecimal(calculateObjMap.get("chargeCalculate").toString());
        
        map.put("chargeValue", chargeCalculate);
        
        return map;
    }
    
    /**
     * 我的轮牌
    * @author 王大爷
    * @date 2015年11月23日 上午11:13:03
    * @param employeeId 员工标识
    * @return ModelAndView
     */
    public ModelAndView myShiftMahjong(Integer employeeId){
        ModelAndView mav =  new ModelAndView(View.StaffPage.MY_SHIFTMAHJONG);
        List<ShiftMahjongDto> shiftMahjongDtoList = shiftMahjongMapper.selectByEmployeeId(employeeId);
        
        mav.addObject("shiftMahjongDtoList", shiftMahjongDtoList);
        mav.addObject("employeeId", employeeId);
        return mav;
    }
    
    /**
     * 员工上下牌
    * @author 老王
    * @date 2016年7月26日 下午8:37:32 
    * @param shiftMahjongId 轮牌标识
    * @param employeeId 员工标识
    * @param type 上下排类型
    * @return BaseDto
     */
    public BaseDto upShiftMahjong (Integer shiftMahjongId, Integer employeeId, Integer type) {
    	shiftMahjongService.upOrDownShiftMahjong(shiftMahjongId, employeeId, type);
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 我的提成
    * @author 王大爷
    * @date 2015年12月30日 下午4:11:54
    * @param storeId 门店标识
    * @param employeeId 员工标识
    * @return ModelAndView
     */
    public ModelAndView selectCommissionInfo(Integer storeId, Integer employeeId) {
        ModelAndView mav =  new ModelAndView(View.StaffPage.SELECT_COMMISSION);
                
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        DateFormat dayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowMonth = dateFormat.format(new Date());
        String nowDay = dayDateFormat.format(new Date());
        
        Map<String, Object> monthMap = packageMonth(employeeId, nowMonth);
        Map<String, Object> dayMap = packageDetail(employeeId, nowDay);
        
        mav.addObject("dayMapStr", JSONObject.fromObject(dayMap).toString());
        mav.addObject("monthMapStr", JSONObject.fromObject(monthMap).toString());
        mav.addObject("nowMonth", nowMonth);
        mav.addObject("nowDay", nowDay);
        return mav;
    }
    
    /**
     * 根据时间查询员工提成
     * @param storeId 门店标识
     * @param employeeId 员工标识
     * @param dateType 查询类型
     * @param dateTime 查询时间
     * @return BaseDto
     */
    public BaseDto selectCommissionDateType(Integer storeId, Integer employeeId, Integer dateType, String dateTime) {
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	
    	if (dateType == 2) {
    		hashMap = packageMonth(employeeId, dateTime);
    	}
    	else {
    		hashMap = packageDetail(employeeId, dateTime);
    	}
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, hashMap);
    }
    
    /**
     * 组装员工当月提成业绩
     * @param employeeId 员工标识
     * @param dayMonth 月时间 
     * @return Map<String, Object>
     */
    public Map<String, Object> packageMonth(Integer employeeId, String dayMonth) {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    	
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("employeeId", employeeId);       
        
            
        map.put("dateTime", dayMonth);
        List<Map<String, Object>> commissionList = employeeCommissionMapper.selectCommissionByMonth(map);
        
        for (Map<String, Object> commission : commissionList) {
            String dayTime = commission.get("chargeTime").toString();
            Map<String, Object> dayMap = packageDetail(employeeId, dayTime);
            commission.put("dayMap", dayMap);
        }
        
        BigDecimal sumCommissionMonth = employeeCommissionMapper.selectSumCommissionByMonth(map);
        
        List<Integer> plist = new ArrayList<Integer>(1);
        plist.add(1);
        map.put("orderTypeList", plist);
        Map<String, Object> pMap = employeeCommissionMapper.selectSumByMonthOrderType(map);
        
        BigDecimal monthProjectCalculate = new BigDecimal(pMap.get("sumCommissionCalculate").toString());
        BigDecimal monthProjectAmount = new BigDecimal(pMap.get("sumCommissionAmount").toString());
        
        List<Integer> glist = new ArrayList<Integer>(1);
        glist.add(2);
        map.put("orderTypeList", glist);
        Map<String, Object> gMap = employeeCommissionMapper.selectSumByMonthOrderType(map);
        
        BigDecimal monthGoodsCalculate = new BigDecimal(gMap.get("sumCommissionCalculate").toString());
        BigDecimal monthGoodsAmount = new BigDecimal(gMap.get("sumCommissionAmount").toString());
        
        List<Integer> clist = new ArrayList<Integer>(1);
        clist.add(3);
        map.put("orderTypeList", clist);
        Map<String, Object> cMap = employeeCommissionMapper.selectSumByMonthOrderType(map);
        
        BigDecimal monthComboCalculate = new BigDecimal(cMap.get("sumCommissionCalculate").toString());
        BigDecimal monthComboAmount = new BigDecimal(cMap.get("sumCommissionAmount").toString());
        
        List<Integer> cglist = new ArrayList<Integer>(3);
        cglist.add(4);
        cglist.add(5);
        cglist.add(6);
        map.put("orderTypeList", cglist);
        Map<String, Object> cgMap = employeeCommissionMapper.selectSumByMonthOrderType(map);
        
        BigDecimal monthChargeCalculate = new BigDecimal(cgMap.get("sumCommissionCalculate").toString());
        BigDecimal monthChargeAmount = new BigDecimal(cgMap.get("sumCommissionAmount").toString());
        
        List<BigDecimal> commissionMonthList = new ArrayList<BigDecimal>(); 
        
        //往后推12个月提成
        for (int i = 0; i < 12; i++) {
            try {
                Date date = dateFormat.parse(dayMonth);
				Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.MONTH, -i);
                
                String monthDate = dateFormat.format(cal.getTime());
                map.put("dateTime", monthDate);
                BigDecimal commissionMonth = employeeCommissionMapper.selectSumCommissionByMonth(map);
                commissionMonthList.add(commissionMonth);
			} 
            catch (ParseException e) {
				e.printStackTrace();
			}
        }
        
        Map<String, Object> commissionMap = new HashMap<String, Object>();
        commissionMap.put("monthDate", dayMonth);
        commissionMap.put("commissionList", commissionList);
        commissionMap.put("sumCommissionMonth", sumCommissionMonth);
        
        commissionMap.put("monthProjectCalculate", monthProjectCalculate);
        commissionMap.put("monthProjectAmount", monthProjectAmount);
        commissionMap.put("monthGoodsCalculate", monthGoodsCalculate);
        commissionMap.put("monthGoodsAmount", monthGoodsAmount);
        commissionMap.put("monthComboCalculate", monthComboCalculate);
        commissionMap.put("monthComboAmount", monthComboAmount);
        commissionMap.put("monthChargeCalculate", monthChargeCalculate);
        commissionMap.put("monthChargeAmount", monthChargeAmount);
        
        commissionMap.put("commissionMonthList", commissionMonthList);
        
        return commissionMap;
    }
    
    /**
     * 组装每日详情信息
    * @author 王大爷
    * @date 2016年1月4日 下午8:52:02
    * @param employeeId 员工标识
    * @param dayTime yyyy-mm-dd
    * @return Map<String, Object>
     */
    public Map<String, Object> packageDetail(Integer employeeId, String dayTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dateTime", dayTime);
        map.put("employeeId", employeeId);
        
        List<EmployeeCommissionDto> dtoList = employeeCommissionMapper.selectCommissionByDay(map);
        
        BigDecimal projectCalculate = new BigDecimal(0);
        BigDecimal projectAmount = new BigDecimal(0);
        
        BigDecimal goodsCalculate = new BigDecimal(0);
        BigDecimal goodsAmount = new BigDecimal(0);
        
        BigDecimal comboCalculate = new BigDecimal(0);
        BigDecimal comboAmount = new BigDecimal(0);
        
        BigDecimal chargeCalculate = new BigDecimal(0);
        BigDecimal chargeAmount = new BigDecimal(0);
        
        List<Map<String, Object>> orderList =  new ArrayList<>();
        
        for (EmployeeCommissionDto employeeCommissionDto : dtoList) {
            if (employeeCommissionDto.getOrderType() == 1) {
                projectAmount = projectAmount.add(employeeCommissionDto.getCommissionAmount());
                projectCalculate = projectCalculate.add(employeeCommissionDto.getCommissionCalculate());
            }
            else if (employeeCommissionDto.getOrderType() == 2){
                goodsCalculate = goodsCalculate.add(employeeCommissionDto.getCommissionCalculate());
                goodsAmount = goodsAmount.add(employeeCommissionDto.getCommissionAmount());
            }
            else if (employeeCommissionDto.getOrderType() == 3){
                comboCalculate = comboCalculate.add(employeeCommissionDto.getCommissionCalculate());
                comboAmount = comboAmount.add(employeeCommissionDto.getCommissionAmount());
            }
            else {
                chargeCalculate = chargeCalculate.add(employeeCommissionDto.getCommissionCalculate());
                chargeAmount = chargeAmount.add(employeeCommissionDto.getCommissionAmount());
            }
            
            if (orderList.size() > 0) {
            	Integer type = 0;
            	for (Map<String, Object> orderMap : orderList) {
            		Integer orderId = Integer.valueOf(orderMap.get("orderId").toString());
					if (orderId.intValue() == employeeCommissionDto.getOrderId().intValue()) {
						@SuppressWarnings("unchecked")
                        List<Map<String, Object>> detailList = (List<Map<String, Object>>) orderMap.get("detailList");
						Map<String, Object> detailMap = new HashMap<>();
						detailMap.put("projectName", employeeCommissionDto.getProjectName());
						detailMap.put("commissionCalculate", employeeCommissionDto.getCommissionCalculate());
						detailMap.put("commissionAmount", employeeCommissionDto.getCommissionAmount());
						detailMap.put("orderType", employeeCommissionDto.getOrderType());
						detailList.add(detailMap);
						type = 1;
					}
				}
            	if (type == 0) {
            		Map<String, Object> orderMap = new HashMap<>();
            		orderMap.put("orderId", employeeCommissionDto.getOrderId());
            		orderMap.put("orderCode", employeeCommissionDto.getOrderCode());
            		List<Map<String, Object>> detailList = new ArrayList<>();
            		Map<String, Object> detailMap = new HashMap<>();
					detailMap.put("projectName", employeeCommissionDto.getProjectName());
					detailMap.put("commissionCalculate", employeeCommissionDto.getCommissionCalculate());
					detailMap.put("commissionAmount", employeeCommissionDto.getCommissionAmount());
					detailMap.put("orderType", employeeCommissionDto.getOrderType());
					detailList.add(detailMap);
					
					orderMap.put("detailList", detailList);
					
					orderList.add(orderMap);
            	}
            }
            else {
            	Map<String, Object> orderMap = new HashMap<>();
        		orderMap.put("orderId", employeeCommissionDto.getOrderId());
        		orderMap.put("orderCode", employeeCommissionDto.getOrderCode());
        		List<Map<String, Object>> detailList = new ArrayList<>();
        		Map<String, Object> detailMap = new HashMap<>();
				detailMap.put("projectName", employeeCommissionDto.getProjectName());
				detailMap.put("commissionCalculate", employeeCommissionDto.getCommissionCalculate());
				detailMap.put("commissionAmount", employeeCommissionDto.getCommissionAmount());
				detailMap.put("orderType", employeeCommissionDto.getOrderType());
				detailList.add(detailMap);
				
				orderMap.put("detailList", detailList);
				
				orderList.add(orderMap);
            }
        }
        
        Map<String, Object> dtoMap = new HashMap<String, Object>();
        dtoMap.put("projectCalculate", projectCalculate);
        dtoMap.put("projectAmount", projectAmount);
        
        dtoMap.put("goodsCalculate", goodsCalculate);
        dtoMap.put("goodsAmount", goodsAmount);
        
        dtoMap.put("comboCalculate", comboCalculate);
        dtoMap.put("comboAmount", comboAmount);
        
        dtoMap.put("chargeCalculate", chargeCalculate);
        dtoMap.put("chargeAmount", chargeAmount);
        
        dtoMap.put("orderList", orderList);
        
        dtoMap.put("dateTime", dayTime);
        return dtoMap;
    }
    
    /**
     * 员工分页查询我的考勤
    * @author DavidLiang
    * @date 2016年3月23日 下午2:22:59
    * @param pageNo  页码
    * @param pageSize  页容量
    * @param employeeId  员工id
    * @return  考勤分页信息
     */
    public Page<AttendanceRecordDto> myAttendancdFindByPage(int pageNo, int pageSize, Integer employeeId) {
    	Page<AttendanceRecordDto> page = new Page<AttendanceRecordDto>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", employeeId);
		page.setParams(params);
		List<AttendanceRecordDto> list = employeeAttendanceMapper.selectAttendanceRecordSelective(page);
		for (int i=0; i<list.size(); i++) {
			AttendanceRecordDto ard = list.get(i);
			if (ard.getSignInOffset() != null) {
				if (ard.getSignInOffset() >= 0) {
					ard.setSignInOffsetToTimeDetail("0");
				}
				else if (ard.getSignInOffset() < 0) {
					ard.setSignInOffsetToTimeDetail(EmployeeAttendanceDateUtil.minuteToTimeDetail(- ard.getSignInOffset()));
				}
			}
			if (ard.getSignOutOffset() != null) {
				if (ard.getSignOutOffset() > 0) {
					ard.setSignOutOffsetTimeDetail("0");
				}
				else if (ard.getSignOutOffset() < 0) {
					ard.setSignOutOffsetTimeDetail(EmployeeAttendanceDateUtil.minuteToTimeDetail(ard.getSignOutOffset()));
				}
				else {
					if (! new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(ard.getAttendanceDate())) {
						ard.setSignOutOffsetTimeDetail("0");
					}
				}
			}
		}
		page.setResults(list);
		return page;
    }


    /**
     * 微信端员工修改个人信息
    * @author 高国藩
    * @date 2016年6月22日 下午2:21:43
    * @param employeeInfo employeeInfo
    * @return             employeeInfo
     */
    @Transactional
    public BaseDto updateStaffInfo(EmployeeInfo employeeInfo) {
        employeeInfoMapper.updateByPrimaryKeySelective(employeeInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "修改成功");
    }
}
