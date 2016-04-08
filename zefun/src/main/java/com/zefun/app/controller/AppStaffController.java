/**  
 * Project Name:zefun  
 * File Name:StaffCentreController.java  
 * Package Name:com.zefun.app.controller  
 * Date:2016年3月23日上午11:46:02  
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
/**  
 * Project Name:zefun  
 * File Name:StaffCentreController.java  
 * Package Name:com.zefun.app.controller  
 * Date:2016年3月23日上午11:46:02  
 */  
  
package com.zefun.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zefun.app.common.param.SigeCheckParam;
import com.zefun.app.common.param.StaffAppointParam;
import com.zefun.app.common.param.UpdatePwdParam;
import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.EmployeeAttendanceService;
import com.zefun.wechat.service.StaffCentreService;

/**  
 * ClassName:StaffCentreController  
 * Function: TODO ADD FUNCTION.  
 * Reason:   TODO ADD REASON.  
 * Date:     2016年3月23日 上午11:46:02  
 * @author   michael
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
/**  
 * ClassName: StaffController    
 * date: 2016年3月23日 上午11:46:02  
 * @author michael 
 * @version   
 * @since JDK 1.8  
 */
@Controller
@Api(value = "Employee", description = "员工模块", produces=MediaType.APPLICATION_JSON_VALUE)
public class AppStaffController {
    /**
     * 配置员工出勤
     */
	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;
	/**
	 * 员工中心
	 */
	@Autowired
	private StaffCentreService staffCentreService;
	
    /**
     * 
     * signCheck:(这个接口用于员工打卡).   
     * @author michael  
     * @param param SigeCheckParam
     * @return BaseDto
     * @throws Exception  
     * @since JDK 1.8
     */
    @ApiOperation(value ="记录员工打卡", notes ="记录员工打卡", httpMethod ="POST", produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = Url.App.SIGN_CHECK, method = RequestMethod.POST)
    @ApiResponses(value={@ApiResponse(code=App.System.API_RESULT_CODE_FOR_SUCCEES, message="打卡成功", response=String.class),
			        @ApiResponse(code=App.System.API_RESULT_CODE_FOR_FAIL, message="系统错误", response=String.class)})
    @ResponseBody
    public BaseDto signCheck(@ApiParam(value="Json参数", name="signCheck", required=true)@RequestBody SigeCheckParam param) throws Exception{
        return employeeAttendanceService.signCheck(param);
    }
    /**
     * 
     * updateEmpPwd:(用于修改员工密码).   
     * @author michael 
     * @param param  UpdatePwdParam
     * @return BaseDto
     * @throws Exception  
     * @since JDK 1.8
     */
    @ApiOperation(value ="修改员工密码", notes ="修改员工密码", httpMethod ="POST", produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = Url.App.UPDATE_EMP_PWD, method = RequestMethod.POST)
    @ApiResponses(value={@ApiResponse(code=App.System.API_RESULT_CODE_FOR_SUCCEES, message="修改员工密码", response=String.class),
			@ApiResponse(code=App.System.API_RESULT_CODE_FOR_FAIL, message="系统错误", response=String.class)})
    @ResponseBody
    public BaseDto updateEmpPwd(@ApiParam(value="Json参数", name="updateEmpPwd", required=true)@RequestBody UpdatePwdParam param) throws Exception{
        return staffCentreService.updateEmpPwd(param);
    }
    /**
     * 
     * staffAppAppoint:(定义app接口，用户预约).   
     * @author michael 
     * @param param StaffAppointParam
     * @return BaseDto
     * @throws Exception  
     * @since JDK 1.8
     */
    @ApiOperation(value ="员工预约", notes ="员工预约", httpMethod ="POST", produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = Url.App.STAFF_APPOINT, method = RequestMethod.POST)
    @ApiResponses(value={@ApiResponse(code=App.System.API_RESULT_CODE_FOR_SUCCEES, message="员工预约", response=String.class),
			@ApiResponse(code=App.System.API_RESULT_CODE_FOR_FAIL, message="系统错误", response=String.class)})
    @ResponseBody
    public BaseDto staffAppAppoint(@ApiParam(value="Json参数", required=true)@RequestBody StaffAppointParam param) throws Exception{
        return staffCentreService.staffAppAppoint(param);
    }
}
  
