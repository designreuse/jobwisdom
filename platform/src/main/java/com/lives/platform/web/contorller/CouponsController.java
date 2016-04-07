package com.lives.platform.web.contorller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.consts.View;
import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.entity.Coupons;
import com.lives.platform.web.entity.CouponsInfo;
import com.lives.platform.web.entity.SankerCoupons;
import com.lives.platform.web.service.CouponsService;

/**
 * 优惠券请求业务层
* @author 高国藩
* @date 2015年6月8日 上午11:21:49 
*
 */
@Controller
public class CouponsController {

	@Autowired
	private CouponsService couponsServicel;
	/**
	 * 增加优惠劵基础类型
	* @author 中文姓名
	* @date 2015年6月8日 下午3:47:04
	* @param coupons
	* @param project
	* @return
	 */
	@RequestMapping(value=Url.CouponsUrl.addType,method=RequestMethod.POST)
	public String initCouponsType(Coupons coupons,String[] project){
		StringBuffer pro = new StringBuffer();
		for (int i = 0; i < project.length; i++) {
			if(i==project.length-1){
				pro.append(project[i]);
			}else{
				pro.append(project[i]);pro.append(",");
			}
		}
		coupons.setCreateTime(DateUtils.getCurDate());
		coupons.setProjectId(pro.toString());
	    couponsServicel.initCouponsType(coupons);
		return "";
	}
	/**
	 * 增加散客优惠劵
	* @author 中文姓名
	* @date 2015年6月8日 下午3:47:04
	* @param coupons
	* @param project
	* @return
	 */
	@RequestMapping(value=Url.CouponsUrl.addSanker,method=RequestMethod.POST)
	public String initSankerCoupons(SankerCoupons coupons){
		int result = couponsServicel.initSankerCoupons(coupons);
		System.out.println(result);
		return "";
	}
	/**
	 * 查询得出所有的优惠券基础,进行分配
	* @author 中文姓名
	* @date 2015年6月8日 下午4:05:27
	* @param map
	* @return
	 */
	@RequestMapping(value=Url.CouponsUrl.serch,method=RequestMethod.GET)
	public String serch(Map<String, List<Coupons>> map){
		List<Coupons> coupons = couponsServicel.serch();
		System.out.println(coupons.get(0).getCouponsType());
		map.put("coupons", couponsServicel.serch());
		return View.Project.COUPONSLIST;
	}
	/**
	 * 赠送会员
	* @author 中文姓名
	* @date 2015年6月8日 下午4:35:50
	* @param couponsInfo
	* @param couponsId 通过id查询到基础表,将其中的字段赋值出来
	* @return 
	 */
	@RequestMapping(value=Url.CouponsUrl.sendtoVip,method=RequestMethod.POST)
	public String sendtoVip(CouponsInfo couponsInfo,HttpSession session,Integer couponsId,String sourseform){
		Coupons coupons = couponsServicel.getCoupons(couponsId);
		couponsInfo.setCouponsNo(RandomStringUtils.randomAlphanumeric(12));
		couponsInfo.setStartEnd(coupons.getStartEnd());
		couponsInfo.setEndDate(coupons.getEndDate());
		couponsInfo.setAmount(coupons.getAmount());
		couponsInfo.setProjectId(coupons.getProjectId());
		couponsInfo.setSourse(sourseform);
		couponsInfo.setIsPayment(coupons.getIsRecord());
		couponsServicel.sendtoVip(couponsInfo);
		return View.Project.COUPONSLIST;
	}
	/**
	 * 查询所有的散客优惠券
	* @author 中文姓名
	* @date 2015年6月8日 下午6:34:44
	* @param map
	* @return
	 */
	 @RequestMapping(value=Url.CouponsUrl.serchSanker,method=RequestMethod.GET)
	 public String serchSanker(Map<String, List<SankerCoupons>> map){
		 map.put("SankerCoupons",couponsServicel.getSankerCoupons());
		 return View.Project.COUPONSLIST;
	 }
}
