package com.lives.platform.web.contorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.consts.View;
import com.lives.platform.common.exception.ServiceException;
import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.dto.BaseDto;
import com.lives.platform.web.dto.EmployeeDto;
import com.lives.platform.web.dto.PostMessagePositionDto;
import com.lives.platform.web.entity.CodeLibrary;
import com.lives.platform.web.entity.Employee;
import com.lives.platform.web.entity.Page;
import com.lives.platform.web.entity.Position;
import com.lives.platform.web.entity.PostMessage;
import com.lives.platform.web.entity.Role;
import com.lives.platform.web.entity.Stores;
import com.lives.platform.web.entity.UserInfo;
import com.lives.platform.web.service.AddressMapperService;
import com.lives.platform.web.service.CodeLibraryMapperService;
import com.lives.platform.web.service.EmployeeMapperService;
import com.lives.platform.web.service.PositionMapperService;
import com.lives.platform.web.service.PostMessageMapperService;
import com.lives.platform.web.service.RoleMapperService;
import com.lives.platform.web.service.StoresMapperService;
import com.lives.platform.web.service.UserInfoService;
import com.rabbitmq.client.AMQP.Confirm.Select;


/**
 * 员工信息
 * @author 王潇
 * @date 2015年6月8日 下午2:06:08
 *
 */

@Controller
public class StaffEmployeeSettingController {

	@Autowired
	private EmployeeMapperService employeeMapperService;
	@Autowired
	private AddressMapperService addressMapperService;
	
	@Autowired
	private PostMessageMapperService postMessageMapperService;
	
	@Autowired
	private PositionMapperService positionMapperService;
	
	@Autowired
	private StoresMapperService storesMapperService;
	
	@Autowired
	private CodeLibraryMapperService codeLibraryMapperService;
	
	@Autowired
	private RoleMapperService roleMapperService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 初始化岗位设置界面
	* @author 老王
	* @date 2015年7月13日 下午2:48:57
	* @param request
	* @param response
	* @param model
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.STAFFSETTING)
	public ModelAndView staffSetting(HttpServletRequest request,HttpServletResponse response,ModelAndView model) throws ServiceException{
		List<PostMessagePositionDto> plist = postMessageMapperService.selectPostMessagePositionDto(1);
		model.addObject("postMessage",plist);
		List<Position> poList = plist.get(0).getPosition();
		model.addObject("postMessageId",plist.get(0).getPostMessageId());
		if(poList.size() > 0){
			model.addObject("position",poList);
		}else{
			model.addObject("position","");
		}
		model.setViewName(View.StaffEmployeeSetting.STAFFSETTING);
       return model;
	}
	
	/**
	 * 初始化员工设置界面
	* @author 老王
	* @date 2015年7月13日 下午2:49:49
	* @param request
	* @param response
	* @param model
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.EMPLOYEESETTING)
	public ModelAndView employeeSetting(HttpServletRequest request,HttpServletResponse response,ModelAndView model) throws ServiceException{
		List<PostMessagePositionDto> plist = postMessageMapperService.selectPostMessagePositionDto(1);
		model.addObject("postMessage",plist);
		List<Position> poList = plist.get(0).getPosition();
		model.addObject("postMessageId",plist.get(0).getPostMessageId());
		model.addObject("postMessageName",plist.get(0).getPostMessageName());
		if(poList.size() > 0){
			model.addObject("position",poList);
			model.addObject("positionId",poList.get(0).getPositionId());
			model.addObject("positionName",poList.get(0).getPositionName());
		}else{
			model.addObject("position","");
		}
		List<Stores> sList = storesMapperService.selectByStoresId(1);//登录用户storesID
		for(int i = 0;i < sList.size();i++){
			if(1 == sList.get(i).getStoresId()){
				model.addObject("storesId",sList.get(i).getStoresId());
				model.addObject("storesCode",sList.get(i).getStoresCode());
				model.addObject("storesName",sList.get(i).getStoresName());
				if(sList.get(i).getParentId() == 0){
					model.addObject("stores",sList);
					List<Employee> emList = employeeMapperService.selectByParentId(sList.get(i).getStoresId());
					model.addObject("employeeList",JSONArray.fromObject(emList).toString());
				}else{
					List<Stores> slists = new ArrayList<Stores>();
					slists.add(sList.get(i));
					model.addObject("stores",slists);
					List<Employee> emList = employeeMapperService.selectByStoresId(sList.get(i).getStoresId());
					model.addObject("employeeList",JSONArray.fromObject(emList).toString());
				}
				break;
			}
		}
		List<CodeLibrary> bankList =codeLibraryMapperService.selectByTypeName("银行名称");
		List<CodeLibrary> degreeList =codeLibraryMapperService.selectByTypeName("学历");
		List<CodeLibrary> healthList =codeLibraryMapperService.selectByTypeName("健康状况");
		List<Role> roleList = roleMapperService.selectAll();
		model.addObject("bankList",bankList);
		model.addObject("bankName",bankList.get(0).getCodeName());
		model.addObject("degreeList",degreeList);
		model.addObject("degreeName",degreeList.get(0).getCodeName());
		model.addObject("healthList",healthList);
		model.addObject("healthName",healthList.get(0).getCodeName());
		model.addObject("roleList",roleList);
		model.addObject("roleName",roleList.get(0).getRoleName());
		model.addObject("roleId",roleList.get(0).getRoleId());
		model.setViewName(View.StaffEmployeeSetting.EMPLOYEESETTING);
       return model;
	}
	
	/**
	 * 查询员工信息列表
	* @author 老王
	* @date 2015年7月16日 下午2:47:02
	* @return
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.EMPLOYEESELECT)
	@ResponseBody
	public ModelAndView employeeSelect(HttpServletRequest request,HttpServletResponse response,ModelAndView model,String searchEmployee,Page<EmployeeDto> page){

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storesId", 3);
		page.setParams(params);
		List<EmployeeDto> listEmployeeDto = employeeMapperService.selectBySynthetical(page);
		model.addObject("pageNo",page.getPageNo());
		model.addObject("totalPage",page.getTotalPage());
		model.addObject("pageSize",page.getPageSize());
		model.addObject("listEmployeeDto",listEmployeeDto);
		model.setViewName(View.StaffEmployeeSetting.EMPLOYEESELECT);
		return model;
	}
	
	/**
	 * 进入修改员工信息
	* @author 老王
	* @date 2015年7月20日 下午2:36:55
	* @param request
	* @param response
	* @param model
	* @return
	 */
	@RequestMapping(value=Url.StaffEmployeeSetting.EMPLOYEEUPDATE)
	@ResponseBody
	public ModelAndView employeeUpdate(HttpServletRequest request,HttpServletResponse response,ModelAndView model,Integer employeeId){
		EmployeeDto employeeDto = employeeMapperService.selectByEmployeeId(employeeId);
		List<PostMessagePositionDto> plist = postMessageMapperService.selectPostMessagePositionDto(1);
		model.addObject("postMessage",plist);
		
		model.addObject("postMessageId",employeeDto.getPostMessage().getPostMessageId());
		model.addObject("postMessageName",employeeDto.getPostMessage().getPostMessageName());
		
		for(int i=0;i<plist.size();i++){
			if(plist.get(i).getPostMessageId() == employeeDto.getPostMessage().getPostMessageId()){
				List<Position> poList = plist.get(i).getPosition();
				model.addObject("position",poList);
				for(int j =0;j<poList.size();j++){
					if(employeeDto.getPosition().getPositionId() == poList.get(j).getPositionId()){
						model.addObject("positionId",poList.get(j).getPositionId());
						model.addObject("positionName",poList.get(j).getPositionName());
						break;
					}
				}
			}
		}

		List<Stores> sList = storesMapperService.selectByStoresId(1);//登录用户storesID
		for(int i = 0;i < sList.size();i++){
			if(1 == sList.get(i).getStoresId()){
				if(sList.get(i).getParentId() == 0){
					model.addObject("stores",sList);
					List<Employee> emList = employeeMapperService.selectByParentId(sList.get(i).getStoresId());
					model.addObject("employeeList",JSONArray.fromObject(emList).toString());
				}else{
					List<Stores> slists = new ArrayList<Stores>();
					slists.add(sList.get(i));
					model.addObject("stores",slists);
					List<Employee> emList = employeeMapperService.selectByStoresId(sList.get(i).getStoresId());
					model.addObject("employeeList",JSONArray.fromObject(emList).toString());
				}
				break;
			}
		}
		//初始所属门店
		for(int i=0;i < sList.size();i++){
			if(employeeDto.getStoresId() == sList.get(i).getStoresId()){
				model.addObject("storesId",sList.get(i).getStoresId());
				model.addObject("storesCode",sList.get(i).getStoresCode());
				model.addObject("storesName",sList.get(i).getStoresName());
			}
		}
		
		List<CodeLibrary> bankList =codeLibraryMapperService.selectByTypeName("银行名称");
		List<CodeLibrary> degreeList =codeLibraryMapperService.selectByTypeName("学历");
		List<CodeLibrary> healthList =codeLibraryMapperService.selectByTypeName("健康状况");
		model.addObject("bankList",bankList);
		model.addObject("bankName",employeeDto.getBankname());
		model.addObject("degreeList",degreeList);
		model.addObject("degreeName",employeeDto.getDegree());
		model.addObject("healthList",healthList);
		model.addObject("healthName",employeeDto.getHealthState());
		
		
		model.addObject("employeeId",employeeDto.getEmployeeId());
		model.addObject("employeeNo",employeeDto.getEmployeeNo());
		model.addObject("employeeName",employeeDto.getEmployeeName());
		model.addObject("sex",employeeDto.getSex());
		model.addObject("mobile",employeeDto.getMobile());
		model.addObject("card",employeeDto.getCard());
		model.addObject("cardImg",employeeDto.getCardImg());
		model.addObject("birthday",employeeDto.getBirthday());
		model.addObject("height",employeeDto.getHeight());
		model.addObject("weight",employeeDto.getWeight());
		model.addObject("healthProve",employeeDto.getHealthProve());
		model.addObject("healthProveImg",employeeDto.getHealthProveImg());
		model.addObject("bankCard",employeeDto.getBankCard());
		model.addObject("contacts",employeeDto.getContacts());
		model.addObject("contactsMobile",employeeDto.getContactsMobile());
		model.addObject("bossSponsor",employeeDto.getBossSponsor());
		model.addObject("bossSponsorName",employeeDto.getBossSponsorName());
		model.addObject("sponsor",employeeDto.getSponsor());
		model.addObject("sponsorName",employeeDto.getSponsorName());
		model.addObject("workState",employeeDto.getWorkState());
		model.addObject("workTime",employeeDto.getWorkTime());
		model.addObject("headImg",employeeDto.getHeadImg());
		model.addObject("otherImg",employeeDto.getOtherImg());
		
		model.setViewName(View.StaffEmployeeSetting.EMPLOYEESETTING);
		return model;
	}
	
	/**
	 * 删除员工信息
	* @author 老王
	* @date 2015年7月20日 下午6:38:09
	* @return
	 */
	@RequestMapping(value=Url.StaffEmployeeSetting.DELETEEMPLOYEEID)
	@ResponseBody
	public BaseDto deleteEmployeeId(Integer employeeId){
		userInfoService.deleteEmployeeId(employeeId);
		employeeMapperService.deleteEmployee(employeeId);
		return new BaseDto(0, "ok");
	}
	
	/**
	 * 查询所有岗位信息
	* @author 老王
	* @date 2015年7月4日 下午1:48:30
	* @return
	 */
	@RequestMapping(value = Url.Employee.SELECTPOSTMESSAGE)
	@ResponseBody
	public BaseDto selectPostMessage(){
		List<PostMessage> pList = postMessageMapperService.selectAll();
		return new BaseDto(0, pList);
	}
	
	/**
	 * 保存岗位信息
	* @author 老王
	* @date 2015年7月4日 下午1:06:25
	* @param postMessageName
	* @return
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.OPERATIONPOSTMESSAGE)
	@ResponseBody
	public BaseDto operationPostMessage(PostMessage postMessage){
		if(postMessage.getPostMessageId() == null){
			postMessage.setState(1);
			postMessage.setStoresId(1);
			postMessage.setCreationTime(DateUtils.getCurDate());
			postMessage.setCreator(1);
			postMessageMapperService.insertPost(postMessage);
			return new BaseDto(0, postMessage);
		}else{
			postMessageMapperService.updatePost(postMessage);
			return new BaseDto(0, postMessage);
		}
	}
	/**
	 * 删除岗位信息
	* @author 老王
	* @date 2015年7月4日 下午5:22:47
	* @param postMessageId
	* @return
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.DELETEPOSTMESSAGE)
	@ResponseBody
	public BaseDto deletePostMessage(Integer postMessageId){
		postMessageMapperService.deletePost(postMessageId);
		return new BaseDto(0, "ok");
	}
	
	/**
	 * 根据岗位信息id查询职位
	* @author 中文姓名
	* @date 2015年7月4日 下午5:39:46
	* @param postMessageId
	* @return
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.SELECTBYPOSTMESSAGEID)
	@ResponseBody
	public BaseDto selectByPostMessageId(Integer postMessageId){
		Position position = new Position();
		position.setPostMessageId(postMessageId);
		List<Position> pList = positionMapperService.selectPositionByPosition(position);
		return new BaseDto(0, pList);
	}
	
	/**
	 * 操作职位信息
	* @author 老王
	* @date 2015年7月6日 下午2:59:33
	* @param positionName
	* @param positionCommission
	* @param postMessageId
	* @return
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.OPERATIONPOSITION)
	@ResponseBody
	public BaseDto operationPosition(Position position){
		if(position.getPositionId() == null){
			position.setCreateTime(DateUtils.getCurDate());
			position.setStoresId(1);
			positionMapperService.addPosition(position);
			return new BaseDto(0, position.getPositionId());
		}else{
			positionMapperService.UpdatePosition(position);
			return new BaseDto(0, "ok");
		}
	}
	/**
	 * 删除职位信息
	* @author 老王
	* @date 2015年7月6日 下午3:44:53
	* @param positionId
	* @return
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.DELETEPOSITION)
	@ResponseBody
	public BaseDto deletePosition(Integer positionId){
		int i =positionMapperService.deletePosition(positionId);
		if(i == 1){
			return new BaseDto(0, "ok");
		}else{
			return new BaseDto(0, "no");
		}
	}
	
	/**
	 * 保存员工信息
	* @author 老王
	* @date 2015年7月16日 上午10:18:31
	* @param employeeDto
	* @return
	 */
	@RequestMapping(value = Url.StaffEmployeeSetting.SAVAEMPLOYEE)
	public String savaEmployee(EmployeeDto employeeDto,HttpServletRequest request,ModelAndView model){
		if(employeeDto.getEmployeeId() == null){
			Employee employee = new Employee(null, employeeDto.getEmployeeNo(),
							                    employeeDto.getStoresId(), employeeDto.getPostMessageId(),
							                    employeeDto.getPositionId(), employeeDto.getEmployeeName(),
							                    employeeDto.getSex(),employeeDto.getMobile().trim(),
							                    employeeDto.getCard(), employeeDto.getCardImg(),
							                    employeeDto.getBirthday(), employeeDto.getDegree(),
							                    employeeDto.getHeight(), employeeDto.getWeight(),
							                    employeeDto.getHealthProve(), employeeDto.getHealthProveImg(),
							                    employeeDto.getHealthState(), employeeDto.getBankname(),
							                    employeeDto.getBankCard(), employeeDto.getContacts(),
							                    employeeDto.getContactsMobile(), employeeDto.getBossSponsor(),
							                    employeeDto.getSponsor(), employeeDto.getWorkState(),
							                    employeeDto.getWorkTime(), DateUtils.getCurDate(),
							                    employeeDto.getStatus(), employeeDto.getHeadImg(), employeeDto.getOtherImg(),
							                    employeeDto.getBossSponsorName(),employeeDto.getSponsorName());
			employeeMapperService.insertEmployee(employee);
			if(employee.getEmployeeId() != null){
				UserInfo userInfo = new UserInfo();
				userInfo.setEmployeeId(employee.getEmployeeId());
				userInfo.setRoleId(employeeDto.getRoleId());
				userInfo.setStoresId(employeeDto.getStoresId());
				userInfo.setUserInfoName(employeeDto.getUserInfoName());
				userInfo.setUserPassword(employeeDto.getUserPassword());
				userInfoService.insertUserInfo(userInfo);
            }
		}else{
			Employee employee = new Employee(employeeDto.getEmployeeId(), employeeDto.getEmployeeNo(),
                    employeeDto.getStoresId(), employeeDto.getPostMessageId(),
                    employeeDto.getPositionId(), employeeDto.getEmployeeName(),
                    employeeDto.getSex(),employeeDto.getMobile().trim(),
                    employeeDto.getCard(), employeeDto.getCardImg(),
                    employeeDto.getBirthday(), employeeDto.getDegree(),
                    employeeDto.getHeight(), employeeDto.getWeight(),
                    employeeDto.getHealthProve(), employeeDto.getHealthProveImg(),
                    employeeDto.getHealthState(), employeeDto.getBankname(),
                    employeeDto.getBankCard(), employeeDto.getContacts(),
                    employeeDto.getContactsMobile(), employeeDto.getBossSponsor(),
                    employeeDto.getSponsor(), employeeDto.getWorkState(),
                    employeeDto.getWorkTime(), DateUtils.getCurDate(),
                    employeeDto.getStatus(), employeeDto.getHeadImg(), employeeDto.getOtherImg(),
                    employeeDto.getBossSponsorName(),employeeDto.getSponsorName());
			employeeMapperService.updateEmployee(employee);
		}
		return "redirect:" + Url.StaffEmployeeSetting.EMPLOYEESELECT;
	}
	
}
