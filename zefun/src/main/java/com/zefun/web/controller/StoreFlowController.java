package com.zefun.web.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.IncometypeDto;
import com.zefun.web.entity.InitializeInFo;
import com.zefun.web.entity.StoreFlow;
import com.zefun.web.service.StoreFlowService;

/**
 * 收银记账
 * @author laowang
 * @date Jun 30, 2015 4:42:19 PM 
 */
@Controller
public class StoreFlowController extends BaseController{
	
	/**
	 * 开支记账Service
	 */
	@Autowired
	private StoreFlowService storeFlowService;
	


    
    

	
	/**
	 * 新增开支记账
	* @author 王大爷
	* @date Jul 2, 2015 2:58:04 PM
	* @param response 请求
	* @param request 返回
	* @param storeFlow 开支记账
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.ADD_STOREFLOW, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addStoreFlow(HttpServletRequest request, HttpServletResponse response, StoreFlow storeFlow){
		storeFlow.setStoreId(getStoreId(request));
        storeFlow.setFlowTime(DateUtil.getCurDate());
        storeFlow.setOperatorId(85);
        storeFlow.setIsDeleted(0);
        return storeFlowService.addStoreFlow(storeFlow, getUserId(request));
	}
	
	/**
	 * 修改开支记账
	* @author 王大爷
	* @date 2015年8月11日 上午10:50:38
	* @param request 返回
	* @param response 请求
	* @param storeFlow 开支记账
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.UPDATE_STOREFLOW, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto updateStoreFlow(HttpServletRequest request, HttpServletResponse response, StoreFlow storeFlow){
		return storeFlowService.updateStoreFlow(storeFlow);
	}
	
	/**
	 * 初始化记账界面
     * 默认返回该门店最前面20条数据
	* @author 骆峰
	* @date 2016年6月18日 13:35:31
	* @param request 返回
	* @param response 请求
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.KeepAccounts.INITIALIZESTOREFLOW, method = RequestMethod.GET)
    public ModelAndView initializeStoreFlow(HttpServletRequest request, HttpServletResponse response){
	    Integer storeId = getStoreId(request);
	    return storeFlowService.initializeStoreFlow(storeId);
    }
	
	/**
	 * 进入收支类别管理页面
    * @author 骆峰
    * @date 2016年6月17日 09:37:51
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    @RequestMapping(value = Url.KeepAccounts.VIEW_ADD_INITIALIZE_TYPE, method = RequestMethod.GET)
    public ModelAndView viewAddInitialize(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = storeFlowService.viewSelectInComeType(getStoreId(request));
        return view;
    }
    
	
	/**
	 * 动态生成项目类别
	* @author 王大爷
	* @date 2015年8月11日 上午10:52:59
	* @param request 返回
	* @param response 请求
	* @param codeName 数据字典名称
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.TREND_CODELIBRARY, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto trendCodeLibrary(HttpServletRequest request, HttpServletResponse response, String codeName){
		return storeFlowService.trendCodeLibrary(codeName);
	}
	
	/**
	 * 数据导入
	* @author 王大爷
	* @date 2015年8月6日 下午1:52:41
	* @param file fileupload对象
	* @param request 返回
	* @param response 请求
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.READEXCLE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto readExcle(@RequestParam("filevalue") MultipartFile file,
		      HttpServletRequest request, HttpServletResponse response){
		String temp = request.getSession().getServletContext()
		        .getRealPath(File.separator)
		        + "temp"; // 临时目录
		return storeFlowService.insertStoreFlowList(file, temp, getStoreId(request));
	}
	
	/**
	 * 导出excle
	* @author 王大爷
	* @date 2015年8月11日 上午11:00:33
	* @param request 返回
	* @param response 请求
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.DOWNLOAD_EXCLE, method = RequestMethod.GET)
	@ResponseBody
	public BaseDto downloadExcle(HttpServletRequest request, HttpServletResponse response){
		return storeFlowService.downloadExcle(response);
	}
	
	/**
	 * 分页查询某个门店的开卡记账界面
	* @author laowang
	* @date Aug 5, 2015 7:58:53 PM
	* @param pageNo		页码
	* @param pageSize	每页显示数
	* @param beginTime 开始时间
	* @param endTime 结束时间
	* @param request 返回
    * @param response 请求
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.STOREFLOW_LIST, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto storeFlowList(int pageNo, int pageSize, Integer beginTime, Integer endTime, HttpServletRequest request, 
	        HttpServletResponse response){
		return storeFlowService.storeFlowList(getStoreId(request), pageNo, pageSize, beginTime, endTime);
	}
	
	/**
	 * 删除开支记账
	* @author laowang
	* @date 2015年8月6日 下午8:45:22
	* @param flowId 开支记账ID
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.DELETE_STOREFLOW, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deleteStoreFlow(Integer flowId){
		return storeFlowService.deleteStoreFlow(flowId);
	}
	
	
  /**
    * 收支类别新增
    * @author 骆峰
    * @date 2016年6月16日 17:44:54
    * @param request  返回
    * @param response 请求
    * @param incometyped  实体类
    * @return ModelAndView
     */
    @RequestMapping(value = Url.KeepAccounts.VIEW_ADD_INITIALIZE_TYPEADD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto viewAddInType(HttpServletRequest request, HttpServletResponse response,
           @RequestBody IncometypeDto incometyped){
        return storeFlowService.viewAddInComeType(incometyped, getStoreId(request));
    }
	
    /**
     * 收支记账新增
    * @author 骆峰
    * @date 2016年6月18日 下午3:26:33
    * @param request 请求
    * @param initialize 实体
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.INITIALIZESTOREFLOWADD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto initilLize(HttpServletRequest request, @RequestBody InitializeInFo initialize){
        return storeFlowService.viewAddInitilLize(initialize, getStoreId(request));
    }
    
    
    /**
     * 收支记账条件查询分页
    * @author 骆峰
    * @date 2016年6月20日 下午2:52:21
    * @param request request
    * @param pageNo pageNo
    * @param type type
    * @param deptName deptName
    * @param priceName priceName
    * @param date1 date1
    * @param date2 date2
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.INITIALIZESTOREFLOWSELECT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto initilLize(HttpServletRequest request, Integer pageNo, String type, String deptName, String priceName, String date1, String date2){
        return  storeFlowService.viewSelectInitilLize(pageNo, getStoreId(request), type, deptName, priceName, date1, date2);
    }
    
    
    /**
     * 收支记账修改
    * @author 骆峰
    * @date 2016年6月20日 下午8:22:28
    * @param request request
    * @param Initialize Initialize
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.INITIALIZESTOREFLOWUPDATE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateInitilLize(HttpServletRequest request, @RequestBody InitializeInFo  Initialize){
        return  storeFlowService.updateInitilLize(getStoreId(request), Initialize);
    }
}
