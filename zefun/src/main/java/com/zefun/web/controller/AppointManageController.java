package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.service.AppointManageService;

/**
 * 预约管理控制类
* @author 小高
* @date Nov 23, 2015 9:25:48 PM 
*/
@Controller
public class AppointManageController extends BaseController {

    /** 预约管理服务对象 */
    @Autowired
    private AppointManageService appointManageService;
    
    
    /**
     * 查看预约列表
    * @author 小高
    * @date Nov 23, 2015 10:17:35 PM
    * @param request    请求对象
    * @return   预约列表
     */
    @RequestMapping(value = Url.AppointManage.VIEW_APPOINT_LIST, method = RequestMethod.GET)
    public ModelAndView appointListView(HttpServletRequest request){
        int storeId = getStoreId(request);
        return appointManageService.appointListView(storeId);
    }
    
    
    /**
     * 分页查询某个门店的预约信息
    * @author 小高
    * @date Aug 5, 2015 7:58:53 PM
    * @param pageNo     页码
    * @param pageSize   每页显示数
    * @param request    请求对象
    * @return           预约列表
     */
    @RequestMapping(value = Url.AppointManage.ACTION_APPOINT_LIST, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto listAction(int pageNo, int pageSize, HttpServletRequest request){
        int storeId = getStoreId(request);
        return appointManageService.listAction(storeId, pageNo, pageSize);
    }
    
    
    /**
     * 取消预约
    * @author 小高
    * @date Aug 5, 2015 7:58:53 PM
    * @param appointmentId  预约标识
    * @return           预约列表
     */
    @RequestMapping(value = Url.AppointManage.ACTION_APPOINT_CANCEL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto cancelAction(int appointmentId){
        return appointManageService.cancelAction(appointmentId);
    }
    
    /**
     * 根据部门查询可预约员工
    * @author DavidLiang
    * @date 2016年2月18日 下午5:52:02
    * @param request  请求
    * @param deptId  部门id
    * @param monthAndDay  月日(eg:02-18)
    * @param weekDay  星期几
    * @param time  时刻(eg:10:30)
    * @return BaseDto
     */
    @RequestMapping(value = Url.AppointManage.ACTION_APPOINT_FIND_EMPLOYEE_BY_DEPT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto findCanAppointmentEmployeeByDept(HttpServletRequest request, int deptId, String monthAndDay, String weekDay, String time) {
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, 
    			appointManageService.findCanAppointmentEmployeeByDept(getStoreId(request), deptId, monthAndDay, weekDay, time));
    }
    
    /**
     * 根据员工职位查询可预约项目系列集
    * @author DavidLiang
    * @date 2016年2月19日 下午8:16:13
    * @param levelId  员工职位id
    * @return  项目系列集BaseDto
     */
    @RequestMapping(value = Url.AppointManage.ACTION_PROJECT_CATEGORY_FIND_BY_LEVEL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto findProjectCategoryByEmployeeLevel(int levelId) {
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, appointManageService.findProjectCategoryByEmployeeLevel(levelId));
    }
    
    /**
     * 根据项目系列id和职位id查询项目
    * @author DavidLiang
    * @date 2016年2月19日 下午8:33:34
    * @param categoryId  项目系列id
    * @param levelId  员工职位id
    * @return  项目集BaseDto
     */
    @RequestMapping(value = Url.AppointManage.ACTION_PROJECT_BY_CATEGORY_ID, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto findProjectByCategoryId(int categoryId, int levelId) {
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, appointManageService.findProjectByCategoryId(categoryId, levelId));
    }
    
    /**
     * 新增会员预约项目
    * @author DavidLiang
    * @date 2016年2月22日 上午12:10:34
    * @param request  请求
    * @param memberAppointment  会员预约参数
    * @return  预约结果BaseDto
     */
    @RequestMapping(value = Url.AppointManage.ACTION_ADD_APPOINT_PROJECT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addAppointProject(HttpServletRequest request, MemberAppointment memberAppointment) {
    	memberAppointment.setStoreId(getStoreId(request));
    	memberAppointment.setLastOperatorId(getUserId(request));
    	return appointManageService.addAppointProject(memberAppointment);
    }
    
    /**
     * 根据日期(yyyy-MM-dd)查询会员预约
    * @author DavidLiang
    * @date 2016年2月22日 下午6:23:41
    * @param request 请求
    * @param date  查询日期
    * @return  会员预约BaseDto
     */
    @RequestMapping(value = Url.AppointManage.ACTION_FIND_MEMBER_APPOINT_BY_DATE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto findMemberAppointByDate(HttpServletRequest request, String date) {
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, appointManageService.findMemberAppointByDate(date, getStoreId(request)));
    }
    
    /**
     * web端取消预约
    * @author DavidLiang
    * @date 2016年2月25日 下午8:41:06
    * @param appointmentId  预约id
    * @return  取消预约结果BaseDto
     */
    @RequestMapping(value = Url.AppointManage.ACTION_CANCEL_APPOINTMENT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto cancelAppointment(int appointmentId) {
    	return appointManageService.cancelAppointment(appointmentId);
    }
    
}
