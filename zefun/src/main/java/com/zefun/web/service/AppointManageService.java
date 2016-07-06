package com.zefun.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.AppointmentBaseDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ProjectCategory;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ProjectStep;
import com.zefun.web.entity.ShiftInfo;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.MemberAppointmentMapper;
import com.zefun.web.mapper.ProjectCategoryMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ProjectStepMapper;
import com.zefun.web.mapper.ShiftMapper;
import com.zefun.wechat.service.MemberCenterService;

/**
 * 预约管理服务类
* @author 张进军
* @date Nov 23, 2015 10:11:22 PM 
*/
@Service
@Transactional
public class AppointManageService {
    
	/** 会员信息映射 */
	/*@Autowired
	private MemberInfoMapper memberInfoMapper;*/
	
    /**预约数据操作对象*/
    @Autowired
    private MemberAppointmentMapper memberAppointmentMapper;
    
    /** 部门信息映射 */
    @Autowired
    private DeptInfoMapper deptInfoMapper;
    
    /** 员工信息映射 */
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /** 班次信息映射 */
    @Autowired
    private ShiftMapper shiftMapper;
    
    /** 项目信息映射 */
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    
    /** 项目系列映射 */
    @Autowired
    private ProjectCategoryMapper projectCategoryMapper;
    
    /** 项目步骤映射 */
    @Autowired
    private ProjectStepMapper projectStepMapper;
    
    /** 会员中心业务服务 */
    @Autowired
    private MemberCenterService memberCenterService;
    /** 会员中心业务服务 */
    @Autowired
    private MemberInfoService memberInfoService;
    /** 队列操作类 */
    @Autowired
    private RedisService redisService;
    /** 队列操作类 */
    @Autowired
    private RabbitService rabbitService;
    
    /** 日志打印 */
    private Logger log = Logger.getLogger(AppointManageService.class);
    
    /**
     * 查看预约列表
    * @author 张进军
    * @date Nov 23, 2015 10:17:35 PM
    * @param storeId    门店标识
    * @return   预约列表
     */
    public ModelAndView appointListView(int storeId){
        Page<AppointmentBaseDto> page = selectPageForAppointList(storeId, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView(View.SelfCashier.APPOINT_LIST);
        mav.addObject("page", page);
    	
    	//滚动日期map
    	Map<String, String> dateMap = DateUtil.getDateBetweenSixty();
    	//该店铺部门列表
    	List<DeptInfo> deptInfoList = deptInfoMapper.selectDeptByStoreId(storeId);
    	
    	mav.addObject("dateMap", dateMap);
    	mav.addObject("deptInfoList", deptInfoList);
        return mav;
    }
    
    
    /**
     * 分页查询某个门店的预约信息
    * @author 张进军
    * @date Nov 23, 2015 10:17:35 PM
    * @param storeId    门店标识
    * @param pageNo     页码
    * @param pageSize   每页显示数
    * @return           预约列表
     */
    public BaseDto listAction(Integer storeId, int pageNo, int pageSize) {
        Page<AppointmentBaseDto> page = selectPageForAppointList(storeId, pageNo,
                pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    
    /**
     * 分页查询某个门店的预约信息
    * @author 张进军
    * @date Nov 23, 2015 10:17:35 PM
    * @param storeId    店铺标识
    * @param pageNo     页码
    * @param pageSize   每页显示数
    * @return Page<MemberLevel>
     */
    private Page<AppointmentBaseDto> selectPageForAppointList(Integer storeId, int pageNo, int pageSize) {
        Page<AppointmentBaseDto> page = new Page<AppointmentBaseDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<AppointmentBaseDto> list = memberAppointmentMapper.selectByPage(page);
        page.setResults(list);
        return page;
    }


    /**
     * 取消预约
    * @author 张进军
    * @date Nov 24, 2015 12:30:03 AM
    * @param appointmentId  预约标识
    * @return   成功返回码为0，失败为其它返回码
    */
    public BaseDto cancelAction(int appointmentId) {
        String curTime = DateUtil.getCurTime();
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setAppointmentId(appointmentId);
        memberAppointment.setCancelReason("PC员工操作");
        memberAppointment.setCancelTime(curTime);
        memberAppointment.setUpdateTime(curTime);
        memberAppointment.setAppointmentStatus(4);
        memberAppointmentMapper.updateByPrimaryKey(memberAppointment);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        
//        AppointmentBaseDto appointmentBaseDto = memberAppointmentMapper.selectAppointmentByAppointmentId(appointmentId);
//        String appointmentTime = appointmentBaseDto.getAppointmentDate() + " " + appointmentBaseDto.getAppointmentTime();
//        int employeeId = appointmentBaseDto.getEmployeeId();
//        int storeId = 0;
//        String projectName = appointmentBaseDto.getProjectInfo().getProjectName();
//        String reason = "";
//        return memberCenterService.cancelAppoinmentAction(appointmentBaseDto.getMemberId(), storeId, 
//        appointmentId, employeeId, projectName, appointmentTime, reason);
    }
    
    
    /**
     * 根据部门查询可预约员工
    * @author DavidLiang
    * @date 2016年2月18日 下午3:21:53
    * @param storeId  门店id
    * @param deptId  部门id
    * @param monthAndDay 月日(eg:02-18)
    * @param weekDay  星期几
    * @param time  时刻(eg:10:30)
    * @return  可预约员工集
     */
    public List<EmployeeInfo> findCanAppointmentEmployeeByDept(int storeId, int deptId, String monthAndDay, String weekDay, String time) {
    	int year = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
    	//是否跨年
    	if (DateUtil.decideIsCrossYear(monthAndDay)) {
    		year += 1;
    	}
    	//预约时间
    	String appointmentYearMonthDay = year + "-" + monthAndDay;  //yyyy-MM-dd
    	List<EmployeeInfo> employeeInfoList = employeeInfoMapper.selectEmployeeByDept(deptId);
    	List<EmployeeInfo> returnEmployeeList = new ArrayList<EmployeeInfo>();
    	//根据班次查找可预约员工
    	returnEmployeeList = findCanAppointEmployeeByShift(appointmentYearMonthDay, weekDay, time, employeeInfoList);
    	//排除该时间段已经预约的员工
    	returnEmployeeList = excludeAlreadyReservedEmployee(storeId, appointmentYearMonthDay, time, returnEmployeeList);
    	
    	return returnEmployeeList;
    }
    
    /**
     * 根据班次查找可预约员工
    * @author DavidLiang
    * @date 2016年2月29日 下午2:43:13
    * @param appointmentYearMonthDay  预约年月日(yyyy-MM-dd)
    * @param weekDay  预约星期几
    * @param time  预约时刻(HH:mm)
    * @param employeeInfoList  该门店下所有员工
    * @return  班次符合的可预约员工
     */
    private List<EmployeeInfo> findCanAppointEmployeeByShift(String appointmentYearMonthDay, String weekDay, 
    		  String time, List<EmployeeInfo> employeeInfoList) {
    	List<EmployeeInfo> returnEmployeeList = new ArrayList<EmployeeInfo>();
    	String shiftId = DateUtil.getShiftIdByWeekDay(weekDay);
    	for (int i=0; i<employeeInfoList.size(); i++) {
    		EmployeeInfo employee = employeeInfoList.get(i);
    		/*
    		 * 查看员工在该天是否有班次，班次时间是否与参数时刻符合(根据班次时间开始时间晚半小时，结束时间早半小时)
    		 */
    		ShiftInfo shiftInfo = shiftMapper.selectShiftByWeekDay(employee.getEmployeeId(), shiftId);
    		if (shiftInfo != null) {
    			String beginTime = shiftInfo.getStartTime();
    			String endTime = shiftInfo.getEndTime();
    			if (beginTime != null && endTime != null) {
    				//如果班次跨日
    				if (DateUtil.decideIsCrossDay(beginTime, endTime)) {
    					endTime = DateUtil.addOneDayCustomize(appointmentYearMonthDay, 1)  + " " + endTime; //yyyy-MM-dd HH:mm
    				}
    				else {
    					endTime = appointmentYearMonthDay + " " + endTime; //yyyy-MM-dd HH:mm
    				}
    				beginTime = appointmentYearMonthDay + " " + beginTime; //yyyy-MM-dd HH:mm
    				try {
						//判断班次时间是否与参数时刻符合(根据班次时间开始时间晚半小时，结束时间早半小时)
						if (DateUtil.dateDiff(beginTime, appointmentYearMonthDay + " " + time, "yyyy-MM-dd HH:mm", "min") >= 30
								  && DateUtil.dateDiff(appointmentYearMonthDay + " " + time, endTime, "yyyy-MM-dd HH:mm", "min") >= 30) {
							returnEmployeeList.add(employee);
						} 
					} 
    				catch (Exception e) {
						continue;
					}
    			}
    		}
    	}
    	return returnEmployeeList;
    }
    
    
    /**
     * 排除该时间段已经预约的员工
    * @author DavidLiang
    * @date 2016年2月29日 下午4:01:21
    * @param storeId  店铺id
    * @param appointmentYearMonthDay  预约年月日(yyyy-MM-dd)
    * @param time  预约时刻(HH:mm)
    * @param returnEmployeeList  需要判断的员工集
    * @return  该时间段没有被预约的员工
     */
    private List<EmployeeInfo> excludeAlreadyReservedEmployee(int storeId, String appointmentYearMonthDay, 
    		  String time, List<EmployeeInfo> returnEmployeeList) {
    	List<MemberAppointment> memberAppointList = memberAppointmentMapper.selectAppointByStoreAndTime(storeId, appointmentYearMonthDay, time);
    	for (int i=0; i<memberAppointList.size(); i++) {
    		Integer employeeId = memberAppointList.get(i).getEmployeeId();
    		for (int j=0; j<returnEmployeeList.size(); j++) {
    			if (returnEmployeeList.get(j).getEmployeeId().equals(employeeId)) {
    				returnEmployeeList.remove(j);
    				//因为remove之后原集合从i后面的元素都会向前移一位(所以从i再查询一遍就OK了)
    				i = i - 1;
    				break;
    			}
    		}
    	}
    	return returnEmployeeList;
    }
    
    /**
     * 根据员工职位查询可预约项目系列集
    * @author DavidLiang
    * @date 2016年2月19日 下午7:54:32
    * @param levelId  员工职位id
    * @return  项目系列集
     */
    public List<ProjectCategory> findProjectCategoryByEmployeeLevel(int levelId) {
    	List<ProjectInfo> projectList = projectInfoMapper.selectCanAppointmentProjectByEmployeeLevelId(levelId);
    	int[] categoryIdArray = new int[projectList.size()];
    	for (int i=0; i<projectList.size(); i++) {
    		categoryIdArray[i] = projectList.get(i).getCategoryId();
    	}
    	if (categoryIdArray != null && categoryIdArray.length != 0) {
    		return projectCategoryMapper.selectProjectCategoryByCategoryIdArray(categoryIdArray);
    	}
    	return null;
    }
    
    /**
     * 根据项目系列id和职位id查询项目
    * @author DavidLiang
    * @date 2016年2月19日 下午8:31:45
    * @param categoryId  项目系列id
    * @param levelId  员工职位id
    * @return  项目集
     */
    public List<ProjectInfo> findProjectByCategoryId(int categoryId, int levelId) {
    	return projectInfoMapper.selectProjectByCategoryIdAndLevelId(categoryId, levelId);
    }
    
    /**
     * 新增会员预约项目
    * @author DavidLiang
    * @date 2016年2月21日 下午9:45:29
    * @param storeAccount       storeAccount
    * @param memberAppointment  会员预约参数
    * @return  预约结果BaseDto
     */
    public BaseDto addAppointProject(String storeAccount, MemberAppointment memberAppointment) {
    	//根据电话查询该会员id(散客也能预约，所以没必要查询是否存在该会员)
    	/*MemberInfo memberInfo = memberInfoMapper.selectMemberByStoreIdAndPhone(
    			  memberAppointment.getStoreId(), memberAppointment.getPhone());
    	if (memberInfo == null) {
    		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "暂无该电话对应会员");
    	}
    	memberAppointment.setMemberId(memberInfo.getMemberId());*/
    	
    	//查询服务步骤序号 和 查询服务轮牌标识
//    	ProjectStep projectStep = projectStepMapper.selectAppointStepByProjectId(memberAppointment.getProjectId());
//    	if (projectStep == null) {
//    		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该项目下无可预约步骤");
//    	}
//    	memberAppointment.setProjectStepOrder(projectStep.getProjectStepOrder());
//    	memberAppointment.setShiftMahjongId(projectStep.getShiftMahjongId());
        
        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberAppointment.getMemberId(), false);
    	
    	//处理预约日期(appointment_date,格式yyyy-MM-dd)
    	int year = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
    	//是否跨年
    	if (DateUtil.decideIsCrossYear(memberAppointment.getAppointmentDate())) {
    		year += 1;
    	}
    	memberAppointment.setAppointmentDate(year + "-" + memberAppointment.getAppointmentDate());
    	
    	memberAppointment.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	//收银员给员工预约直接给状态值为2：确认预约
    	memberAppointment.setAppointmentStatus(2);
    	memberAppointment.setAppointmentWay(1);
    	int result = memberAppointmentMapper.insert(memberAppointment);
    	if (result == 1) {
    	    String openId = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, memberAppointment.getEmployeeId());
            if (StringUtils.isNotBlank(openId)) {
                rabbitService.sendAppointmentApplyNotice(memberAppointment.getStoreId(), storeAccount, App.System.SERVER_BASE_URL 
                        + Url.Staff.VIEW_STAFF_APPOINT.replace("{storeId}", memberAppointment.getStoreId() + "")
                        .replace("{businessType}", "2").replace("{type}", "1"), memberAppointment.getEmployeeId(), 
                        openId, memberInfo.getName(), memberInfo.getLevelName(), "到店体验",
                        memberAppointment.getAppointmentDate() + " " + memberAppointment.getAppointmentTime());
            }
    		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "预约成功");
    	}
    	else {
    		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "预约失败");
    	}
    	
    }
    
    /**
     * 根据日期(yyyy-MM-dd)查询会员预约
    * @author DavidLiang
    * @date 2016年2月22日 下午5:44:59
    * @param date  查询日期
    * @param storeId  店铺id
    * @return  会员预约map
     */
    public Map<String, Object> findMemberAppointByDate(String date, int storeId) {
    	//处理预约日期(appointment_date,格式yyyy-MM-dd)
    	int year = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
    	//是否跨年
    	if (DateUtil.decideIsCrossYear(date)) {
    		year += 1;
    	}
    	date = year + "-" + date;
    	
    	//根据日期(yyyy-MM-dd)查询会员预约汇总
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	returnMap.put("memberAppointmentSumDto", memberAppointmentMapper.selectMemberAppointSumByDate(date, storeId));
    	
    	//根据日期(yyyy-MM-dd)查询被预约员工集
    	returnMap.put("appointEmployeeList", memberAppointmentMapper.selectAppointEmployeeByDate(date, storeId));
    	
    	return returnMap;
    	
    }
    
    /**
     * 取消预约
    * @author DavidLiang
    * @date 2016年2月25日 下午8:39:24
    * @param appointmentId  预约id
    * @return  取消预约结果BaseDto
     */
    public BaseDto cancelAppointment(int appointmentId) {
    	log.info(memberCenterService);
    	MemberAppointment m = memberAppointmentMapper.selectByPrimaryKey(appointmentId);
//    	ProjectInfo p = projectInfoMapper.selectByPrimaryKey(m.getProjectId());
    	m.setMemberId(m.getMemberId());
    	/*
    	 * 如果是散客就给memberId个值，否则调用cancelAppoinmentAction方法时会报空指针，
    	 * 因为cancelAppoinmentAction中的memberId参数是int类型，而m.getMemberId()得到的参数是Integer，
    	 * 当m.getMemberId()为null时，Integer转int会报空指针
    	 */
    	if (m.getMemberId() == null) {
    		m.setMemberId(-1);
    	}
		return memberCenterService.cancelAppoinmentAction(m.getMemberId(), m.getStoreId(), appointmentId, 
  			  m.getEmployeeId(), null, m.getAppointmentTime(), "客户电话通知取消预约");
    }
}
