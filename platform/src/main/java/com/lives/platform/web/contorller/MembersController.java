//package com.lives.platform.web.contorller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.json.JSONArray;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.lives.platform.common.consts.Url;
//import com.lives.platform.common.consts.View;
//import com.lives.platform.common.util.DateUtils;
//import com.lives.platform.common.util.StringUtils;
//import com.lives.platform.web.dto.BaseDto;
//import com.lives.platform.web.dto.MembersDto;
//import com.lives.platform.web.entity.Employee;
//import com.lives.platform.web.entity.MemberCardInfo;
//import com.lives.platform.web.entity.Members;
//import com.lives.platform.web.entity.MembersCardName;
//import com.lives.platform.web.entity.MembersIntegral;
//import com.lives.platform.web.entity.MembersWater;
//import com.lives.platform.web.entity.VipCardInfo;
//import com.lives.platform.web.mapper.MemberCardInfoMapper;
//import com.lives.platform.web.mapper.VipCardInfoMapper;
//import com.lives.platform.web.service.EmployeeMapperService;
//import com.lives.platform.web.service.MembersCardNameService;
//import com.lives.platform.web.service.MembersCashgiftStagesService;
//import com.lives.platform.web.service.MembersIntegralService;
//import com.lives.platform.web.service.MembersService;
//import com.lives.platform.web.service.MembersWaterService;
//
///**
// * 会员信息
//* @author 洪秋霞
//* @date 2015年7月16日 下午5:28:22 
//*
// */
//@Controller
//public class MembersController {
//	
//	@Autowired private MembersService membersService;
//	
//	@Autowired private MembersCardNameService membersCardNameService;
//	@Autowired private MemberCardInfoMapper memberCardInfoMapper;
//	
//	@Autowired private MembersIntegralService membersIntegralService;
//	@Autowired private MembersCashgiftStagesService membersCashgiftStagesService;
//	@Autowired private MembersWaterService membersWaterService;
//	
//	@Autowired private EmployeeMapperService employeeMapperService;
//	private Integer storesId = 1;
//	
//	/**
//	 * 进入会员列表页面
//	* @author 洪秋霞
//	* @date 2015年7月16日 下午5:40:19
//	* @param request
//	* @param response
//	* @param model
//	* @return
//	 */
//	@RequestMapping(value = Url.Members.TOMEMBERSLIST)
//	public ModelAndView toMembersList(HttpServletRequest request,HttpServletResponse response,ModelAndView model,MembersDto membersDto){
//		try {
//			List<Members> membersList = membersService.selectByMembersSearchCriteria(membersDto);
//			model.addObject("membersList", membersList);
//			//会员名称列表
//			MembersCardName membersCardName = new MembersCardName();
//			membersCardName.setStoresId(storesId);
//			List<MembersCardName> membersCardNameList = membersCardNameService.selectByProperty(membersCardName);
//			model.addObject("membersCardNameList", membersCardNameList);
//			
//			model.setViewName(View.Members.MEMBERSLIST);
//			return model;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//	}
//	
//	/**
//	 * 进入新增会员页面
//	* @author 洪秋霞
//	* @date 2015年7月17日 下午2:25:10
//	* @param request
//	* @param response
//	* @param model
//	* @return
//	 */
//	@RequestMapping(value = Url.Members.TOADDMEMBERS)
//	public ModelAndView toAddMembers(HttpServletRequest request,HttpServletResponse response,ModelAndView model){
//		//会员列表
//		MembersDto membersDto = new MembersDto();
//		membersDto.setStoresId(storesId);
//		List<Members> membersList = membersService.selectByMembersSearchCriteria(membersDto);
//		model.addObject("membersList", JSONArray.fromObject(membersList).toString());
//		
//		//员工列表
//		List<Employee> employeeList = employeeMapperService.selectByStoresId(3);
//		model.addObject("employeeList", JSONArray.fromObject(employeeList).toString());
//		
//		String membersId = request.getParameter("membersId");
//		if(null != membersId && !"".equals(membersId)){
//			Members members = membersService.queryMembersById(Integer.parseInt(membersId));
//			model.addObject("members",members);
//			
//			//直接推荐人
//			MembersIntegral membersIntegral= new MembersIntegral();
//			membersIntegral.setMembersId(members.getDirectReferrer());
//			membersIntegral.setReferrer(1);
//			membersIntegral.setStoresId(storesId);
//			membersIntegral.setMembersOpenCardId(Integer.parseInt(membersId));
//			List<MembersIntegral> membersIntegralList = membersIntegralService.selectByMembersIntegralProperty(membersIntegral);
//			model.addObject("directReferrer", membersIntegralList.get(0));
//			
//			//间接推荐人
//			MembersIntegral membersIntegral2= new MembersIntegral();
//			membersIntegral2.setMembersId(members.getIndirectReferrer());
//			membersIntegral2.setReferrer(2);
//			membersIntegral2.setStoresId(storesId);
//			membersIntegral2.setMembersOpenCardId(Integer.parseInt(membersId));
//			List<MembersIntegral> membersIntegralList2 = membersIntegralService.selectByMembersIntegralProperty(membersIntegral2);
//			model.addObject("indirectReferrer", membersIntegralList2.get(0));
//		}
//		
//		model.setViewName(View.Members.MEMBERSADD);
//		
//		return model;
//	}
//	
//	/**
//	 * 保存会员信息
//	 * @return
//	 */
//	@RequestMapping(value = Url.Members.SAVEMEMBRES)
//	@ResponseBody
//	public BaseDto saveMembres(HttpServletRequest request,HttpServletResponse response,Members members){
//		try {
//			//直接推荐人
//			String openCardIntegral = request.getParameter("card_integral_direct");//开卡送积分
//			String topupIntegral = request.getParameter("topup_integral_direct");//充值送积分
//			String consumptionIntegral = request.getParameter("consume_integral_direct");//消费送积分
//			
//			//间接推荐人
//			String cardIntegralIndirect = request.getParameter("card_integral_indirect");//开卡送积分
//			String topupIntegralIndirect = request.getParameter("topup_integral_indirect");//充值送积分
//			String consumeIntegralIndirect = request.getParameter("consume_integral_indirect");//消费送积分
//			
//			//员工提成
////			String employeeId = request.getParameter("employeeId");
//			
//			//保存会员表
//			membersService.insertMembers(members);
//			
//			//1.查找直接推荐人信息，然后更新数据
//			Integer directReferrerId = members.getDirectReferrer();//直接推荐人id
//			MembersIntegral membersIntegralDirect = new MembersIntegral();
//			membersIntegralDirect.setMembersId(directReferrerId);
//			membersIntegralDirect.setMembersOpenCardId(members.getMembersId());
//			membersIntegralDirect.setReferrer(1);//推荐人，1：直接推荐人，2：间接推荐人
//			membersIntegralDirect.setOpenCardIntegral(Integer.parseInt(openCardIntegral));
//			membersIntegralDirect.setTopupIntegral(Integer.parseInt(topupIntegral));
//			membersIntegralDirect.setConsumptionIntegral(Integer.parseInt(consumptionIntegral));
//			membersIntegralDirect.setStoresId(storesId);
//			membersIntegralDirect.setCreateTime(DateUtils.getCurTime());
//			membersIntegralService.insertMembersIntegral(membersIntegralDirect);
//			
//			//2.查找间接推荐人信息，然后更新数据
//			Integer indirectReferrerId = members.getIndirectReferrer();//间接推荐人id
//			MembersIntegral membersIntegralIndirect = new MembersIntegral();
//			membersIntegralIndirect.setMembersId(indirectReferrerId);
//			membersIntegralIndirect.setMembersOpenCardId(members.getMembersId());
//			membersIntegralIndirect.setReferrer(2);//推荐人，1：直接推荐人，2：间接推荐人
//			membersIntegralIndirect.setOpenCardIntegral(Integer.parseInt(cardIntegralIndirect));
//			membersIntegralIndirect.setTopupIntegral(Integer.parseInt(topupIntegralIndirect));
//			membersIntegralIndirect.setConsumptionIntegral(Integer.parseInt(consumeIntegralIndirect));
//			membersIntegralIndirect.setStoresId(storesId);
//			membersIntegralIndirect.setCreateTime(DateUtils.getCurTime());
//			membersIntegralService.insertMembersIntegral(membersIntegralIndirect);
//			
//			//保存会员流水表
//			MembersWater membersWater = new MembersWater();
//			membersWater.setMembersId(members.getMembersId());
//			membersWater.setType(1);
//			membersWater.setWaterType(1);
//			if(StringUtils.isNotEmpty(openCardIntegral)){
//				membersWater.setGivingIntegral(Integer.parseInt(openCardIntegral));
//			}
//			membersWaterService.insertMembersWater(membersWater);
//			
//			return new BaseDto(0, "ok");
//		} catch (Exception e) {
//			return new BaseDto(-1, "error");
//		}
//	}
//	
//	/**
//	 * 手机号码验证
//	* @author 洪秋霞
//	* @date 2015年7月20日 下午2:46:04
//	* @return
//	 */
//	@RequestMapping(value = Url.Members.VALIDMOBILE)
//	@ResponseBody
//	public BaseDto validMobile(String mobile){
//		try {
//			Members members = new Members();
//			members.setMembersMobile(mobile);
//			List<Members> membersList = membersService.selectByMembersProperty(members);
//			if(membersList.size()>0){
//				for (Members members2 : membersList) {
//					if(members2.getMembersType().intValue() == 1){//1：储值卡 2：疗程卡
//						return new BaseDto(0, "error");
//					}
//				}
//				return new BaseDto(0, "ok");
//			}
//			return new BaseDto(0, "ok");
//		} catch (Exception e) {
//			return new BaseDto(-1, "error");
//		}
//	}
//	
//	/**
//	 * 会员卡类型选择
//	 * @return
//	 */
//	@RequestMapping(value = Url.Members.MEMBERSTYPESEL)
//	@ResponseBody
//	public BaseDto membersTypeSel(Integer type){
//		try {
//			//根据【会员卡类型】判断开卡方式 从表：vip_card_info; -- 会员卡基本信息表 获取数据
//			Map<String, Integer> map = new HashMap<String, Integer>();
//			map.put("storeId", storesId);
//			map.put("cardType", type);
//			List<MemberCardInfo> memberCardInfoList = memberCardInfoMapper.selectVipCardInfos(map);
//			return new BaseDto(0, memberCardInfoList);
//		} catch (Exception e) {
//			return new BaseDto(-1, "error");
//		}
//	}
//	
//	/**
//	 * 类型选择
//	* @author 洪秋霞
//	* @date 2015年7月20日 下午4:42:25
//	* @param cId
//	* @return
//	 */
//	@RequestMapping(value = Url.Members.TYPESELECT)
//	@ResponseBody
//	public BaseDto typeSelect(Integer cardId){
//		try {
//			MemberCardInfo memberCardInfo = memberCardInfoMapper.selectByPrimaryKey(cardId);
//			return new BaseDto(0, memberCardInfo);
//		} catch (Exception e) {
//			return new BaseDto(-1, "error");
//		}
//	}
//	
//	/**
//	 * 推介人信息查询 --暂无使用
//	* @author 洪秋霞
//	* @date 2015年7月20日 上午9:42:40
//	* @param nameOrMobile
//	* @return
//	 */
//	@RequestMapping(value = Url.Members.RECOMMENDINFO)
//	@ResponseBody
//	public BaseDto getRecommendInfo(String nameOrMobile){
//		try {
//			MembersDto membersDto = new MembersDto();
//			membersDto.setStoresId(storesId);
//			membersDto.setMembersName(nameOrMobile);
//			membersDto.setMembersMobile(nameOrMobile);
//			List<Members> membersList = membersService.selectByMembersSearchCriteria(membersDto);
//			return new BaseDto(0,membersList);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new BaseDto(-1,"error");
//		}
//	}
//	
//}
