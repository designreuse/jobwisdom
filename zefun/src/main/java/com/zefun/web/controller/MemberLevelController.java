package com.zefun.web.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberLevelDiscount;
import com.zefun.web.service.MemberLevelService;

/**
 * 会员等级管理
 * @author 张进军
 * @date Aug 5, 2015 6:42:27 PM 
 */
@Controller
public class MemberLevelController extends BaseController {
    /** 会员等级操作对象 */
	@Autowired
	private MemberLevelService memberLevelService;
	
	/**
	 * 企业查询所有会员卡信息
	* @author 张进军
	* @date Aug 5, 2015 7:58:33 PM
	* @param request 返回
	* @return ModelAndView
	*/
	@RequestMapping(value = Url.MemberLevel.ENTERPRISE_MEMBERLEVEL_LIST, method = RequestMethod.GET)
	public ModelAndView enterpriseMemberLevelList(HttpServletRequest request){
	    String storeAccount = getStoreAccount(request);
		return memberLevelService.enterpriseMemberLevelList(storeAccount);
	}
	
	/**
	 * 企业保存会员卡信息
	* @author 老王
	* @date 2016年5月31日 上午12:09:09 
	* @param levelId 会员等级标识
	* @param levelName 会员等级
	* @param discountId 折扣表
	* @param projectDiscount 项目折扣
	* @param goodsDiscount 商品折扣
	* @param performanceDiscountPercent  业绩折扣比例(0-100)
	* @param sellAmount 售卡开卡金额
	* @param chargeMinMoney 最低充值金额
	* @param cashDiscountType 现金是否打折(0:不打折，1:打折)
	* @param integralUnit 消费积分单位
	* @param integralNumber 单位积分数量
	* @param levelNotice 等级说明
	* @param request 返回
	* @return BaseDto
	 */
	public BaseDto saveEnterpriseMemberLevel (Integer levelId, String levelName, Integer discountId, Integer projectDiscount, Integer goodsDiscount, 
			  Integer performanceDiscountPercent, 
			  BigDecimal sellAmount, BigDecimal chargeMinMoney, Integer cashDiscountType, Integer integralUnit, Integer integralNumber, 
			  String levelNotice, HttpServletRequest request) {
		
		MemberLevel memberLevel = new MemberLevel();
		memberLevel.setLevelId(levelId);
		memberLevel.setLevelName(levelName);
		memberLevel.setLevelNotice(levelNotice);
		
		MemberLevelDiscount memberLevelDiscount = new MemberLevelDiscount();
		memberLevelDiscount.setDiscountId(discountId);
		memberLevelDiscount.setProjectDiscount(projectDiscount);
		memberLevelDiscount.setGoodsDiscount(goodsDiscount);
		memberLevelDiscount.setPerformanceDiscountPercent(performanceDiscountPercent);
		memberLevelDiscount.setSellAmount(sellAmount);
		memberLevelDiscount.setChargeMinMoney(chargeMinMoney);
		memberLevelDiscount.setCashDiscountType(cashDiscountType);
		memberLevelDiscount.setIntegralUnit(integralUnit);
		memberLevelDiscount.setIntegralNumber(integralNumber);
		
		
	}
	
	/**
	 * 为某个门店新增会员等级
	* @author 老王
	* @date 2016年5月20日 下午4:52:08 
	* @param levelId 会员等级标识
	* @param levelName 会员等级
	* @param discountId 折扣表
	* @param projectDiscount 项目折扣
	* @param goodsDiscount 商品折扣
	* @param performanceDiscountPercent  业绩折扣比例(0-100)
	* @param sellAmount 售卡开卡金额
	* @param chargeMinMoney 最低充值金额
	* @param cashDiscountType 现金是否打折(0:不打折，1:打折)
	* @param integralUnit 消费积分单位
	* @param integralNumber 单位积分数量
	* @param levelNoticeArr 等级说明
	* @param request 返回
	* @return BaseDto
	 */
	@RequestMapping(value = Url.MemberLevel.ACTION_ADD, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addAction(Integer levelId, String levelName, Integer discountId, Integer projectDiscount, Integer goodsDiscount, 
			  Integer performanceDiscountPercent, 
			  BigDecimal sellAmount, BigDecimal chargeMinMoney, Integer cashDiscountType, Integer integralUnit, Integer integralNumber, 
			  String[] levelNoticeArr, HttpServletRequest request){
		MemberLevel memberLevel = new MemberLevel();
		memberLevel.setLevelId(levelId);
		memberLevel.setLevelName(levelName);
		memberLevel.setLevelNoticeArr(levelNoticeArr);
		
		MemberLevelDiscount memberLevelDiscount = new MemberLevelDiscount();
		memberLevelDiscount.setDiscountId(discountId);
		memberLevelDiscount.setProjectDiscount(projectDiscount);
		memberLevelDiscount.setGoodsDiscount(goodsDiscount);
		memberLevelDiscount.setPerformanceDiscountPercent(performanceDiscountPercent);
		memberLevelDiscount.setSellAmount(sellAmount);
		memberLevelDiscount.setChargeMinMoney(chargeMinMoney);
		memberLevelDiscount.setCashDiscountType(cashDiscountType);
		memberLevelDiscount.setIntegralUnit(integralUnit);
		memberLevelDiscount.setIntegralNumber(integralNumber);
	    int storeId = getStoreId(request);
	    memberLevelDiscount.setStoreId(storeId);
	    Integer userId = getUserId(request);
		return memberLevelService.addAction(storeId, userId, memberLevel, memberLevelDiscount);
	}
	
	
	/**
	 * 查询某个店铺的会员等级信息
	 * 默认返回该门店最前面10条数据
	* @author 张进军
	* @date Aug 5, 2015 7:58:33 PM
	* @param request 返回
	* @return ModelAndView
	*/
	@RequestMapping(value = Url.MemberLevel.VIEW_LIST, method = RequestMethod.GET)
	public ModelAndView listView(HttpServletRequest request){
	    int storeId = getStoreId(request);
		return memberLevelService.listView(storeId);
	}
	
	
	/**
	 * 分页查询某个门店的会员等级信息
	* @author 张进军
	* @date Aug 5, 2015 7:58:53 PM
	* @param pageNo		页码
	* @param pageSize	每页显示数
	* @param request 返回
	* @return BaseDto
	 */
	@RequestMapping(value = Url.MemberLevel.ACTION_LIST, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto listAction(int pageNo, int pageSize, HttpServletRequest request){
	    int storeId = getStoreId(request);
		return memberLevelService.listAction(storeId, pageNo, pageSize);
	}
	
	/**
	 * 根据等级标识查询等级信息
	* @author 张进军
	* @date Aug 5, 2015 11:45:13 PM
	* @param levelId	会员等级标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.MemberLevel.ACTION_INFO, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto infoAction(Integer levelId){
		return memberLevelService.infoAction(levelId);
	}
	
	
	/**
	 * 根据等级标识删除等级信息
	* @author 张进军
	* @date Aug 5, 2015 11:45:13 PM
	* @param levelId	会员等级标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.MemberLevel.ACTION_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deleteAction(Integer levelId){
		return memberLevelService.deleteAction(levelId);
	}
	
	
	/**
     * 将指定等级标识设为门店的默认等级
    * @author 张进军
    * @date Aug 5, 2015 11:45:13 PM
    * @param levelId    会员等级标识
    * @param request 返回
    * @return           成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.MemberLevel.ACTION_DEFAULT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto defaultAction(Integer levelId, HttpServletRequest request){
        int storeId = getStoreId(request);
        return memberLevelService.defaultAction(storeId, levelId);
    }
    
   
    /**
     * 会员卡导入项设置
    * @author 高国藩
    * @date 2015年11月18日 下午3:41:59
    * @param file           excle文件
    * @param request        请求
    * @param response       相应
    * @param storeName      原服务商
    * @return               状态
    * @throws IOException   提倡处理
     */
    @RequestMapping(value = Url.MemberLevel.IMPORTEXCLE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto importExcle(@RequestParam("file") MultipartFile file, String storeName, 
              HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer storeId=getStoreId(request);
        Integer lastOperatorId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        if ("盛传".equals(storeName)){
            return memberLevelService.importExcle(file, storeId, lastOperatorId); 
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "暂时不支持其他服务商数据导入");
        }
    }
}
