package com.lives.platform.web.contorller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.exception.ServiceException;
import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.entity.Dommission;
import com.lives.platform.web.service.DommissionMapperService;
import com.lives.platform.web.service.PositionMapperService;


/**
 * 提成基础信息
 * @author 王潇
 * @date 2015年6月8日 下午2:06:08
 *
 */

@Controller
public class DommissionController {

	@Autowired
	private DommissionMapperService dommissionService;
	
	@Autowired
	private PositionMapperService positionMapperService;
	
	
	@RequestMapping(value = Url.Dommission.DOMMISSIONLOG)
	public ModelAndView dommissionLog(ModelAndView model,Integer storesId) throws ServiceException{
		model.setViewName("dommission/dommissionView");
        return model;
	}
	
	@RequestMapping("/cesi")
	public String cesiLog() throws ServiceException{
        return "dommission/cesi";
	}
	
	/**
	 * 新增基础信息
	* @author 王潇
	* @date 2015年6月8日 下午2:11:32
	* @param dommission
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Dommission.DOMMISSIONSUBMIT)
	public String dommissionAdd(Dommission dommission) throws ServiceException{
		//组装会员卡号管理表数据
		dommission.setCreateTime(DateUtils.getCurTime());
		Integer type  = dommissionService.insertDommission(dommission);
		
		if(type == 1){
			return "dommission/dommissionView";
		}else{
			return "dommission/dommissionView";
		}

	}
	
	/**
	 * 查询单条记录
	* @author 王潇
	* @date 2015年6月8日 下午2:16:17
	* @param vipId
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Dommission.DOMMISSIONSELECT)
	@ResponseBody
	public Dommission dommissionSelect(Integer projectDommissionId) throws ServiceException{
		//查询会员综合信息
		Dommission dommission = dommissionService.queryDommission(projectDommissionId);
    	return dommission;
	}
	
	/**
	 * 修改记录
	* @author 王潇
	* @date 2015年6月8日 下午2:16:17
	* @param vipId
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Dommission.DOMMISSIONUPDATE)
	@ResponseBody
	public String dommissionUpdate(Dommission dommission) throws ServiceException{
		//查询会员综合信息
		int i = dommissionService.updateDommission(dommission);
		if(i == 0){
			
		}
    	return  "dommission/dommissionView";
	}
	
	/**
	 * 删除提成基础信息
	* @author 王潇
	* @date 2015年6月8日 下午2:56:38
	* @param projectDommissionId
	* @return
	* @throws ServiceException
	 */
	
	@RequestMapping(value = Url.Dommission.DOMMISSIONDELETE)
	public String dommissionDelete(Integer projectDommissionId) throws ServiceException{
		//查询会员综合信息
		int i = dommissionService.deleteDommission(projectDommissionId);
        if(i == 0){
			
		}
    	return  "dommission/dommissionView";
	}
	
	/**
	 * 动态查询提成数据
	* @author 王潇
	* @date 2015年6月8日 下午5:14:04
	* @param type 项目类型
	* @param projectId  项目id
	* @param employeeId  员工id
	* @param storesId  门店id
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Dommission.DOMMISSIONSELECTBYPROPERTY)
	@ResponseBody
	public List<Dommission> DommissionSelectByProperty(Integer type,Integer projectId,Integer employeeId,Integer storesId) throws ServiceException{
		Dommission dommission = new Dommission();
		dommission.setProjectInfoId(projectId);
		dommission.setEmployeeId(employeeId);
		List<Dommission> dommissionlist = dommissionService.selectByProperty(dommission);
  	    return dommissionlist;
	}
	
	/**
	 * 按类型保存
	* @author 王潇
	* @date 2015年6月10日 下午3:46:40
	* @param projectId 项目id
	* @param type  类行
	* @param employeeId 员工id
	* @param storesId 门店id
	* @param fixedCommission 固定提成
	* @param percentageCommission 比例提成
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Dommission.DOMMISSIONALLSUBMIT)
    @ResponseBody
	public int dommissionAllSubmit(HttpServletRequest request,HttpServletResponse response,Integer projectId,Integer type,Integer storesId,String employeeId,String fixedCommission,String percentageCommission) throws ServiceException{
		String[] employeeIds = employeeId.split(",");
		String[] amount = fixedCommission.split(",");
		String[] discount = percentageCommission.split(",");
		List<Dommission> listvoDommissions = new ArrayList<Dommission>();
		for(int i = 0;i < employeeIds.length;i++){
			Dommission dommission = new Dommission();
			dommission.setProjectInfoId(projectId);
			dommission.setEmployeeId(Integer.valueOf(employeeIds[i]));
			dommission.setAmount(new BigDecimal(amount[i]));
//			dommission.setProjectDommissionId(Integer.valueOf(percentageCommissions[i]));
			dommission.setCreateTime(DateUtils.getCurTime());
			listvoDommissions.add(dommission);
		}
		int i = dommissionService.insertPositionList(listvoDommissions);
  	    return i;
	}
	
}
