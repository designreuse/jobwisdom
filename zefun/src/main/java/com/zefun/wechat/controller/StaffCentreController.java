package com.zefun.wechat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.service.EmployeeAttendanceService;
import com.zefun.wechat.service.BossOfEmployeeCommissionService;
import com.zefun.wechat.service.StaffCentreService;

/**
 * 移动端员工中心逻辑类
* @author 王大爷
* @date Oct 13, 2015 9:29:53 PM 
*/
@Controller
public class StaffCentreController extends BaseController{
	
    /** 员工中心*/
    @Autowired
    private StaffCentreService staffCentreService;
    
    /** 员工考勤服务对象 */
    @Autowired 
    private EmployeeAttendanceService employeeAttendanceService;
    
    /** Boss端员工业绩服务 */
	@Autowired
	private BossOfEmployeeCommissionService bossOfEmployeeCommissionService;
	
	/** 员工服务提成映射 */
	@Autowired
	private EmployeeCommissionMapper employeeCommissionMapper;
	
    
    /**
     * 个人中心
    * @author 王大爷
    * @date 2015年10月18日 下午2:18:55
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_CENTER)
    public ModelAndView staffCenter(@PathVariable String storeId, @PathVariable int businessType,
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.staffCenter(employeeId);
    }
    
    /**
     * 打卡操作，包括签到、签退
    * @author 张进军
    * @date Oct 28, 2015 6:10:05 PM
    * @param request 返回
    * @param response 请求
    * @return   打卡成功返回0；失败返回其它错误码。
     */
    @RequestMapping(value = Url.Staff.ACTION_SIGN_OPERATE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto signOperate(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        int storeId = getStoreIdByOpenId(openId);
        /*//首先根据经纬度判断该员工是否在店铺附近
        StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
        if (storeInfo == null || storeInfo.getLatitude() == null || storeInfo.getLongitude() == null) {
        	return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您所在店铺还未设置坐标，暂无法签到");
        }
        double distance = EmployeeAttendanceDateUtil.getDistance(latitude, longitude, 
        		  Double.parseDouble(storeInfo.getLatitude()), Double.parseDouble(storeInfo.getLongitude()));
        if (SIGN_IN_DISTANCE < distance) {
        	return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "请在店铺内进行签到");
        }*/
        return employeeAttendanceService.signOperate(storeId, employeeId);
    }
    
    
    /**
     * 员工个人信息页面
    * @author 张进军
    * @date Dec 11, 2015 9:40:36 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工个人信息页面
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_INFO)
    public ModelAndView staffInfo(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        setJsapiSignData(getStoreAccount(request), request);
        return staffCentreService.staffInfo(employeeId);
    }
    
    /**
     * 微信端修改员工资料
    * @author 高国藩
    * @date 2016年6月22日 下午2:19:26
    * @param request    request
    * @param response   response
    * @param headImage  headImage
    * @return           BaseDto
     */
    @RequestMapping(value = Url.Staff.UPDATE_STAFF_INFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateStaffInfo(HttpServletRequest request, HttpServletResponse response, String headImage){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setEmployeeId(employeeId);
        employeeInfo.setHeadImage(headImage);
        return staffCentreService.updateStaffInfo(employeeInfo);
    }
    
    
    /**
     * 进入修改密码页面
    * @author 张进军
    * @date Dec 11, 2015 10:01:09 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   修改密码页面
     */
    @RequestMapping(value = Url.Staff.VIEW_UPDATE_PWD)
    public ModelAndView updatePwd(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return new ModelAndView(View.StaffPage.UPDATE_PWD);
    }
    
    
    /**
     * 修改员工密码
    * @author 张进军
    * @date Dec 11, 2015 9:56:32 PM
    * @param oldPwd     旧密码
    * @param newPwd     新密码
    * @param request    请求对象
    * @param response   响应对象
    * @return   成功返回码为0，失败为其他错误码
     */
    @RequestMapping(value = Url.Staff.ACTION_UPDATE_PWD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updatePwd(String oldPwd, String newPwd,
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.updatePwd(employeeId, oldPwd, newPwd);
    }
    
    
    /**
     * 员工业绩排行
    * @author 张进军
    * @date Oct 28, 2015 7:56:49 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工业绩排行页面
     */
    @RequestMapping(value = Url.Staff.VIEW_ALL_EARNING)
    public ModelAndView allEarning(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.allEarning(employeeId);
    }
    
    /**
     * 
     * @param request    请求对象
    * @param response   响应对象
     * @param chooseType 时间类型
     * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_SELECT_EMPLOYEE_DATA, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectEmployeeData(HttpServletRequest request, HttpServletResponse response, Integer chooseType) {
    	String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        Map<String, Object> objMap = staffCentreService.selectEmployeeData(employeeId, chooseType);
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, objMap);
    }
    
    /**
     * 员工更多页面
    * @author 张进军
    * @date Oct 28, 2015 7:56:49 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工更多页面
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_MORE)
    public ModelAndView more(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return new ModelAndView(View.StaffPage.STAFF_MORE);
    }
    
    
    /**
     * 员工个人业绩
    * @author 王大爷
    * @date Oct 28, 2015 7:56:49 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工个人业绩页面
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_EARNING)
    public ModelAndView staffEarning(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.staffEarning(employeeId);
    }
    
    /**
     * 我的轮牌
    * @author 王大爷
    * @date 2015年11月23日 上午10:36:42
    * @param request 请求对象
    * @param response 响应对象
    * @return 我的轮牌
     */
    @RequestMapping(value = Url.Staff.VIEW_MY_SHIFTMAHJONG)
    public ModelAndView myShiftMahjong(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.myShiftMahjong(employeeId);
    }
    
    /**
     * 员工个人预约列表
    * @author 张进军
    * @date Oct 28, 2015 7:56:49 PM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param type       预约类型(1:预约中，2:已确认，3:已取消)
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工个人预约页面
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_APPOINT)
    public ModelAndView staffAppoint(@PathVariable String storeId, @PathVariable int businessType, @PathVariable int type,
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.staffAppoint(employeeId, type);
    }
    
    
    /**
     * 同意/拒绝预约
    * @author 张进军
    * @date Nov 4, 2015 10:38:27 AM
    * @param type           操作类型(1:同意，2:拒绝)
    * @param appointmentId  预约标识
    * @param memberId       会员标识
    * @param projectName    项目名称
    * @param appointTime    预约时间
    * @param reason         取消原因
    * @param request    请求对象
    * @param response   响应对象
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.Staff.ACTION_APPOINT_OPERATE)
    @ResponseBody
    public BaseDto appointOperate(int type, int appointmentId, int memberId, String projectName, String appointTime, String reason,
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        int storeId = getStoreIdByOpenId(openId);
        return staffCentreService.appointOperate(type, storeId, employeeId, appointmentId, memberId, projectName, appointTime, reason);
    }
    
    /**
     * 启动预约
    * @author 王大爷
    * @date 2015年11月4日 上午11:30:59
    * @param request 请求对象
    * @param response 响应对象
    * @param appointmentId 预约标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_START_APPOINT)
    @ResponseBody
    public BaseDto startAppoint(HttpServletRequest request, HttpServletResponse response, Integer appointmentId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer employeeId = getUserIdByOpenId(openId);
        return staffCentreService.startAppoint(appointmentId, storeId, employeeId);
    }
    
    
    /**
     * 我的提成
    * @author 王大爷
    * @date 2015年12月30日 下午4:08:38
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_SELECT_COMMISSION_INFO)
    public ModelAndView selectCommissionInfo(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        int storeId = getStoreIdByOpenId(openId);
        return staffCentreService.selectCommissionInfo(storeId, employeeId);
    }
    
    /**
     * 我的提成
    * @author 王大爷
    * @date 2015年12月30日 下午4:08:38
    * @param request 返回
    * @param response 请求
    * @param dateType 查询类型
    * @param dateTime  查询时间
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.ACTION_SELECT_COMMISSION_DATE_TYPE)
    @ResponseBody
    public BaseDto selectCommissionDateType(HttpServletRequest request, HttpServletResponse response, Integer dateType, String dateTime) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        int storeId = getStoreIdByOpenId(openId);
        
        return staffCentreService.selectCommissionDateType(storeId, employeeId, dateType, dateTime);
    }
    
    /**
     * 员工查询个人表现首页
    * @author DavidLiang
    * @date 2016年3月5日 上午11:52:17
    * @param request  请求
    * @param response  响应
    * @return  个人表现首页
     * @throws ParseException 
     */
    @RequestMapping(value = Url.Staff.VIEW_INDIVIDUAL_PERFORMANCE, method = RequestMethod.GET)
    public ModelAndView findIndividualPerformanceHome(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    	String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
		int ownerStoreId = getStoreIdByOpenId(openId);
		int employeeId = getUserIdByOpenId(openId);
		//默认查询当日
    	ModelAndView mav = new ModelAndView(View.StaffPage.INDIVIDUAL_PERFORMANCE);
		mav.addObject("employeeInfo", bossOfEmployeeCommissionService.findEmployeeInfoOfCommissionDetail(employeeId));
    	//当日/当月提成
		Double employeeCommissionSum = employeeCommissionMapper.selectEmployeeCommissionByTimePeriod(employeeId, DateUtil.getCurDate());
		if (employeeCommissionSum != null) {
			mav.addObject("employeeCommissionSum", employeeCommissionSum);
		}
		else {
			mav.addObject("employeeCommissionSum", 0);
		}
		mav.addObject("employeeCommissionDetailOfBossDto", bossOfEmployeeCommissionService.
				  findEmployeeCommissionDetailHome(ownerStoreId, employeeId, DateUtil.getCurDate()));
		return mav;
    }
    
    /**
     * 根据时间ajax查询员工个人表现
    * @author DavidLiang
    * @date 2016年3月5日 下午2:11:27
    * @param request  请求
    * @param response  响应
    * @param employeeId  员工id
    * @param time  查询时间
    * @return  封装员工个人表现map的BaseDto
     * @throws ParseException 
     */
    @RequestMapping(value = Url.Staff.ACTION_FIND_INDIVIDUAL_PERFORMANCE_BY_TIME, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto findIndividualPerformanceByTime(HttpServletRequest request, HttpServletResponse response, 
			     Integer employeeId, String time) throws ParseException {
    	String openId = getOpenId(2, request, response);
        if (openId == null || time == null) {
            return null;
        }
		int ownerStoreId = getStoreIdByOpenId(openId);
    	if (time != null && "month".equals(time)) {
    		time = DateUtil.getCurMonth();
    	} 
    	else if (time != null && "day".equals(time)) {
    		time = DateUtil.getCurDate();
    	}
    	Map<String, Object> individualPerformanceMap = new HashMap<String, Object>();
    	//individualPerformanceMap.put("employeeInfo", bossOfEmployeeCommissionService.findEmployeeInfoOfCommissionDetail(employeeId));
    	//当日/当月提成
    	Double employeeCommissionSum = employeeCommissionMapper.selectEmployeeCommissionByTimePeriod(employeeId, time);
		if (employeeCommissionSum != null) {
			individualPerformanceMap.put("employeeCommissionSum", employeeCommissionSum);
		}
		else {
			individualPerformanceMap.put("employeeCommissionSum", 0);
		}
    	individualPerformanceMap.put("employeeCommissionDetailOfBossDto", bossOfEmployeeCommissionService.
				     findEmployeeCommissionDetailHome(ownerStoreId, employeeId, time));
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, individualPerformanceMap);
    }
    
    /**
     * 员工查询我的考勤主页
    * @author DavidLiang
    * @date 2016年3月23日 下午2:50:04
    * @param employeeId  员工id
    * @return  员工考勤主页
     */
    public ModelAndView findMyAttendanceHome(Integer employeeId) {
    	ModelAndView mav = new ModelAndView(View.StaffPage.MY_ATTENDANCE);
    	mav.addObject("page", staffCentreService.myAttendancdFindByPage(1, App.System.API_DEFAULT_PAGE_SIZE, employeeId));
    	return mav;
    }
    
    /**
     * 员工分页查询我的考勤
    * @author DavidLiang
    * @date 2016年3月23日 下午2:54:11
    * @param page  页码
    * @param pageSize  页容量
    * @param employeeId  员工id
    * @return  分页考勤
     */
    public BaseDto findMyAttendanceByPage(int page, int pageSize, Integer employeeId) {
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, 
    			staffCentreService.myAttendancdFindByPage(page, pageSize, employeeId));
    }
    
    /**
     * 员工在线学习
    * @author 高国藩
    * @date 2016年7月14日 上午11:55:13
    * @param request               request
    * @param response              response
    * @throws IOException          IOException
    * @return return
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_STUDENT, produces="text/html", method = RequestMethod.GET)
    @ResponseBody
    public String staffStudentCenter(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return "<hr><div style='background-color:red;'>敬请期待</div></hr>";
    }
    
}
