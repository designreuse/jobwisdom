package com.lives.platform.web.contorller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.consts.View;
import com.lives.platform.common.exception.ServiceException;
import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.dto.BaseDto;
import com.lives.platform.web.dto.DommissionEmployeeDto;
import com.lives.platform.web.dto.ProjectClassDto;
import com.lives.platform.web.entity.Employee;
import com.lives.platform.web.entity.EmployeeServeInfo;
import com.lives.platform.web.entity.OrderInfo;
import com.lives.platform.web.entity.OrderInfoDetail;
import com.lives.platform.web.entity.PostMessage;
import com.lives.platform.web.service.DommissionMapperService;
import com.lives.platform.web.service.EmployeeMapperService;
import com.lives.platform.web.service.EmployeeServeInfoMapperService;
import com.lives.platform.web.service.OrderInfoDetailMapperService;
import com.lives.platform.web.service.OrderInfoMapperService;
import com.lives.platform.web.service.PostMessageMapperService;
import com.lives.platform.web.service.ProjectInfoService;



@Controller
public class PaymentController {

/*    @Autowired
	private PostMapperService postMapperService;*/
    
    @Autowired
    private PostMessageMapperService postMessageMapperService;
    
    @Autowired
    private OrderInfoMapperService orderInfoMapperService;
    
    @Autowired
    private ProjectInfoService projectInfoService;
    
    @Autowired
    private OrderInfoDetailMapperService orderInfoDetailMapperService;
    
    @Autowired
    private EmployeeServeInfoMapperService employeeServeInfoMapperService;
    
    @Autowired
    private EmployeeMapperService employeeMapperService;
    
    @Autowired
    private DommissionMapperService dommissionMapperService;
    
    @RequestMapping(value = Url.Payment.VIEW_RETAIL)
	public String shift() throws ServiceException{
       return View.Payment.RETAIL;
	}
    
	@RequestMapping(value = Url.Shift.SETSHIFT)
	public String setShift() throws ServiceException{
       return "shift/setshift";
	}
	
/*	@RequestMapping(value = Url.Shift.SAVEPOST)
	@ResponseBody
	public int savePost(Integer postMessageId,Integer valid) throws ServiceException{
		   Post post = new Post();
		   post.setPostMessageId(postMessageId);
		   post.setValid(valid);
	       return postMapperService.insertPost(post);
	}
	
	@RequestMapping(value = Url.Shift.DELETEPOST)
	@ResponseBody
	public int deletePost(Integer postId) throws ServiceException{
	       return postMapperService.deletePost(postId);
	}
	
	@RequestMapping(value = Url.Shift.UPDATEPOST)
	@ResponseBody
	public int updatePost(Integer postId,Integer postMessageId,Integer valid) throws ServiceException{
		   Post post = new Post();
		   post.setPostId(postId);
		   post.setPostMessageId(postMessageId);
		   post.setValid(valid);
	       return postMapperService.updatePost(post);
	}
	
	*//**
	 * 查询所有岗位
	* @author 老王
	* @date 2015年6月24日 下午3:57:38
	* @return
	* @throws ServiceException
	 *//*
	@RequestMapping(value = Url.Shift.SELECTALLPOST)
	@ResponseBody
	public List<Post> selectAllPost() throws ServiceException{
	       return postMapperService.selectAllPost();
	}*/
	
	/**
	 * 查询所有岗位信息
	* @author 老王
	* @date 2015年6月24日 下午3:57:38
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Shift.SELECTALLPOSTMESSAGE)
	@ResponseBody
	public List<PostMessage> selectAllPostMessage() throws ServiceException{
	       return postMessageMapperService.selectAll();
	}
	
	/**
	 * 模糊查找项目
	* @author 老王
	* @date 2015年6月25日 下午2:21:06
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Payment.SELECTAUTOPROJECT)
	@ResponseBody
	public BaseDto selectAutoProject() throws ServiceException{
	    Integer storesId = 1;
	    List<ProjectClassDto> plist = projectInfoService.selectProjectInfoDto(storesId);
	    return new BaseDto(0, plist);
	}
	
	/**
	 * 根据项目查找员工
	* @author 老王
	* @date 2015年6月30日 下午1:22:19
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Payment.SELECTAUTOEMPLOYEE)
	@ResponseBody
	public BaseDto selectAutoEmployee(Integer projectInfoId) throws ServiceException{
		List<DommissionEmployeeDto> dlist = dommissionMapperService.selectByProjectInfoId(projectInfoId);

		List<Employee> list = new ArrayList<Employee>();
		for(int i = 0;i< dlist.size();i++){
			Employee employee = dlist.get(i).getEmployee();
			list.add(employee);
		}
		return new BaseDto(0, list);
	}

	
	/**
	 * 保存订单
	* @author 老王
	* @date 2015年7月1日 下午8:17:37
	* @return
	 */
	@RequestMapping(value = Url.Payment.SAVEORDERINFO)
	@ResponseBody
	public BaseDto saveOrderInfo(String orderNo,BigDecimal orderAmount,BigDecimal realIncomeAmount,
			BigDecimal saleAmount,BigDecimal qdBill,Integer qdAuthorizationCode,BigDecimal cashBill,
			BigDecimal cardBill,Integer qdEmployeeId){
		OrderInfo orderInfo = new OrderInfo();
		if(orderNo == null){
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			orderInfo.setOrderNo(df.format(new Date()));
		}else{
			orderInfo.setOrderNo(orderNo);
		}
		orderInfo.setOrderAmount(orderAmount);
		orderInfo.setRealIncomeAmount(realIncomeAmount);
		orderInfo.setSaleAmount(saleAmount);
		orderInfo.setQdBill(qdBill);
		orderInfo.setQdAuthorizationCode(qdAuthorizationCode);
		orderInfo.setCashBill(cashBill);
		orderInfo.setCashBill(cashBill);
		orderInfo.setQdEmployeeId(qdEmployeeId);
		orderInfo.setCreateTime(DateUtils.getCurDate());
		orderInfo.setStoresId(1);
		orderInfoMapperService.insert(orderInfo);
		
		return new BaseDto(0, orderInfo.getOrderInfoId());
	}
	
	/**
	 * 保存订单明细
	* @author 老王
	* @date 2015年7月1日 下午8:15:18
	* @param projectMassege
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Payment.GETPROJECTEMPLOYEERELATION)
	@ResponseBody
	public BaseDto getProjectEmployeerelation(String projectMassege) throws ServiceException{
		JSONArray  projson = JSONArray.fromObject(projectMassege);
		for(int i = 0;i < projson.size();i++){
			
			JSONObject jsonobj = (JSONObject)projson.get(i);
			
			if(String.valueOf(jsonobj.get("orderInfoId")).length() > 0 && String.valueOf(jsonobj.get("priid")).length() > 0 
					&& jsonobj.get("orderInfoId") != null && jsonobj.get("priid") != null){
				Integer orderInfoId = jsonobj.getInt("orderInfoId");
				Integer priid = jsonobj.getInt("priid");
				/*Integer prono = jsonobj.getInt("prono");*/
				String proname = jsonobj.getString("proname");
				BigDecimal proprice = new BigDecimal(0);
				BigDecimal procoupon = new BigDecimal(0);
				BigDecimal promoney = new BigDecimal(0);
				
				if(String.valueOf(jsonobj.get("proprice")).length() > 0 && jsonobj.get("proprice") != null){
					proprice = new BigDecimal(jsonobj.getDouble("proprice"));
				}
                if(String.valueOf(jsonobj.get("procoupon")).length() > 0 && jsonobj.get("procoupon") != null){
                	procoupon = new BigDecimal(jsonobj.getDouble("procoupon"));
				}
                if(String.valueOf(jsonobj.get("promoney")).length() > 0 && jsonobj.get("promoney") != null){
                	promoney = new BigDecimal(jsonobj.getDouble("promoney"));
				}
				
				OrderInfoDetail orderInfoDetail = new OrderInfoDetail();
				orderInfoDetail.setOrderInfoId(orderInfoId);
				orderInfoDetail.setRealIncomeAmount(promoney);
				orderInfoDetail.setSaleAmount(procoupon);
				orderInfoDetail.setOrderInfoType(0);
				orderInfoDetail.setCommodityId(priid);
				orderInfoDetail.setCommodityName(proname);
				orderInfoDetail.setCommodityPrice(proprice);
				orderInfoDetail.setCreateTime(DateUtils.getCurDate());
				orderInfoDetailMapperService.insert(orderInfoDetail);
				
				JSONArray sjson = JSONArray.fromObject(jsonobj.get("sarr"));
				for(int j = 0;j < sjson.size();j++){
					JSONObject sjsonobj = (JSONObject)sjson.get(j);
					if(String.valueOf(sjsonobj.get("semployeeid")).length() > 0 && sjsonobj.get("semployeeid") != null){
						Integer semployeeid = sjsonobj.getInt("semployeeid");
						BigDecimal sperformance = new BigDecimal(sjsonobj.getDouble("sperformance"));
						Integer sallot = sjsonobj.getInt("sallot");
						Integer sappoint = sjsonobj.getInt("sappoint");
						
						
						EmployeeServeInfo employeeServeInfo = new EmployeeServeInfo();
						employeeServeInfo.setEmployeeId(semployeeid);
						employeeServeInfo.setSaleRecord(orderInfoDetail.getOrderInfoDetailId());
						employeeServeInfo.setAllot(sallot);
						employeeServeInfo.setIsAppiont(sappoint);
						employeeServeInfo.setStoresId(1);
						employeeServeInfo.setPaidInPerformance(sperformance);
						employeeServeInfo.setCreateTime(DateUtils.getCurDate());
						employeeServeInfoMapperService.insert(employeeServeInfo);
					}
				}
				JSONArray mjson = JSONArray.fromObject(jsonobj.get("marr"));
				for(int j = 0;j < mjson.size();j++){
					JSONObject mjsonobj = (JSONObject)mjson.get(j);
					if(String.valueOf(jsonobj.get("memployeeid")).length() > 0 && jsonobj.get("memployeeid") != null){
						Integer memployeeid = mjsonobj.getInt("memployeeid");
						BigDecimal mperformance = new BigDecimal(mjsonobj.getDouble("mperformance"));
						Integer mallot = mjsonobj.getInt("mallot");
						Integer mappoint = mjsonobj.getInt("mappoint");
						
						EmployeeServeInfo employeeServeInfo = new EmployeeServeInfo();
						employeeServeInfo.setEmployeeId(memployeeid);
						employeeServeInfo.setSaleRecord(orderInfoDetail.getOrderInfoDetailId());
						employeeServeInfo.setAllot(mallot);
						employeeServeInfo.setIsAppiont(mappoint);
						employeeServeInfo.setStoresId(1);
						employeeServeInfo.setPaidInPerformance(mperformance);
						employeeServeInfo.setCreateTime(DateUtils.getCurDate());
						employeeServeInfoMapperService.insert(employeeServeInfo);
					}
				}
				JSONArray ajson = JSONArray.fromObject(jsonobj.get("aarr"));
				for(int j = 0;j < ajson.size();j++){
					JSONObject ajsonobj = (JSONObject)ajson.get(j);
					if(String.valueOf(ajsonobj.get("aemployeeid")).length() > 0 && ajsonobj.get("aemployeeid") != null){
						Integer aemployeeid = ajsonobj.getInt("aemployeeid");
						BigDecimal aperformance = new BigDecimal(ajsonobj.getDouble("aperformance"));
						Integer aallot = ajsonobj.getInt("aallot");
						Integer aappoint = ajsonobj.getInt("aappoint");
						
						
						EmployeeServeInfo employeeServeInfo = new EmployeeServeInfo();
						employeeServeInfo.setEmployeeId(aemployeeid);
						employeeServeInfo.setSaleRecord(orderInfoDetail.getOrderInfoDetailId());
						employeeServeInfo.setAllot(aallot);
						employeeServeInfo.setIsAppiont(aappoint);
						employeeServeInfo.setStoresId(1);
						employeeServeInfo.setPaidInPerformance(aperformance);
						employeeServeInfo.setCreateTime(DateUtils.getCurDate());
						employeeServeInfoMapperService.insert(employeeServeInfo);
					}
				}
			}
			
		}
		return new BaseDto(0, "ok");
	}
	
	/**
	 * 检验签单授权在总点是否存在
	* @author 老王
	* @date 2015年7月2日 下午9:36:06
	* @return
	 */
/*	@RequestMapping(value = Url.Payment.CHECKOUTQDCODE)
	@ResponseBody
	public BaseDto checkoutQDcode(Integer qd_code)throws ServiceException{
        if(qd_code == null){
        	return new BaseDto(0, "no");
        }
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setAuthorizationCode(qd_code);
		employeeDto.setStoresId(1);
		List<EmployeeDto> list = employeeMapperService.selectByProperty(employeeDto);
		if(list.size() > 0){
			return new BaseDto(0, list.get(0).getEmployeeId());
		}else{
			return new BaseDto(0, "no");
		}
	}*/
	
	public static void main(String[] args) {
		String valueString = "[{'priid':'6','prono':'101','proname':'洗吹','proprice':'55','procoupon':'','promoney':'55','orderInfoId':5,'sarr':[{'semployeeid':'','sappoint':'1','sperformance':'','sallot':''}],'marr':[{'memployeeid':'','mappoint':'1','mperformance':'','mallot':''}],'aarr':[{'aemployeeid':'','aappoint':'1','aperformance':'','aallot':''}]}]";
		JSONArray  jsonArray = JSONArray.fromObject(valueString);
        for(int i = 0;i < jsonArray.size();i++){
			
			JSONObject jsonobj = (JSONObject)jsonArray.get(i);
			
			if(jsonobj.get("orderInfoId") != "" && jsonobj.get("priid") != ""){
				BigDecimal procoupon = new BigDecimal(0);
				if(String.valueOf(jsonobj.get("proprice")).length() == 0){
					procoupon = new BigDecimal(jsonobj.getDouble("procoupon"));
				}
			}
        }
	}
}
