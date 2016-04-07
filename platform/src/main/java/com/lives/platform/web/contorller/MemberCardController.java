package com.lives.platform.web.contorller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.platform.common.consts.Url;
import com.lives.platform.web.dto.BaseDto;
import com.lives.platform.web.dto.MemberCardCommissionDto;
import com.lives.platform.web.dto.MemberCardGiveDto;
import com.lives.platform.web.dto.MemberCardRecommendDto;
import com.lives.platform.web.entity.MemberCardInfo;
import com.lives.platform.web.service.MemberCardService;

@Controller
public class MemberCardController {
	@Autowired
	private MemberCardService memberCardService;

	/**
	 * 进入会员卡规则设置页面,并封装数据
	* @author 高国藩
	* @date 2015年7月16日 上午9:33:35
	* @return
	 */
	@RequestMapping(value = Url.MemberCard.memberCard)
	public ModelAndView sendMemberCard(Integer pageNo,Integer pageSize) {
		return memberCardService.sendMemberCard(pageNo,pageSize);
		
	}
	/**
	 * 进入折扣列表
	* @author 高国藩
	* @date 2015年7月18日 下午4:22:57
	* @param vipPageNo
	* @param vipPageSize
	* @return
	 */
	@RequestMapping(value = Url.MemberCard.cutInfo) 
	public ModelAndView cutInfo(Integer pageNo,Integer pageSize) {
		return memberCardService.selectCutInfo(pageNo,pageSize);
	}
	
	/**
	 * 进入礼金页面
	 */
	@RequestMapping(value = Url.MemberCard.PresentInfo)
	public ModelAndView PresentInfo(Integer pageNo,Integer pageSize) {
		return memberCardService.selectPresentInfo(pageNo,pageSize);
	}
	/**
	 * 进入推荐人页面
	 */
	@RequestMapping(value = Url.MemberCard.RecommendInfo)
	public ModelAndView RecommendInfo(Integer pageNo,Integer pageSize) {
		return memberCardService.selectRecommendInfo(pageNo,pageSize);
	}
	/**
	 * 新增会员卡规则
	* @author 高国藩
	* @date 2015年7月16日 上午9:33:57
	* @param cardInfo
	* @param cutInfo
	* @param presentInfo
	* @param recommendInfo
	* @return
	 */
	@RequestMapping(value = Url.MemberCard.save, method = RequestMethod.POST)
	public String save(MemberCardInfo memberCardInfo, MemberCardCommissionDto memberCardCommissionDto,
			MemberCardGiveDto memberCardGiveDto, MemberCardRecommendDto memberCardRecommendDto) {
		return memberCardService.save(memberCardInfo, memberCardCommissionDto, memberCardGiveDto,
				memberCardRecommendDto);
	}
	
	/**
	 * 删除会员卡规则
	* @author 高国藩
	* @date 2015年7月16日 上午9:34:36
	* @return
	 */
	@RequestMapping(value = Url.MemberCard.deleteCard, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deltetCard(Integer cId){
		return memberCardService.deleteCard(cId);
	}
	
	/**
	 * 修改会员卡规则
	* @author 高国藩
	* @date 2015年7月16日 上午9:34:36
	* @return
	 */
	@RequestMapping(value = Url.MemberCard.updateCard, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateCard(Integer cardId){
		return memberCardService.updateCard(cardId);
	}
	
	@RequestMapping(value = Url.MemberCard.realyUpdate, method = RequestMethod.POST)
	public String realyUpdate(MemberCardInfo memberCardInfo){
		return memberCardService.realyUpdate(memberCardInfo);
	}
	
	/**
	 * 四大分页方法查询
	* @author 高国藩
	* @date 2015年7月18日 下午2:17:22
	* @param cardInfo
	* @param cutInfo
	* @param presentInfo
	* @param recommendInfo
	* @return
	 */
	@RequestMapping(value = Url.MemberCard.selectVipCardsByPage)
	public ModelAndView selectVipCardsByPage(@RequestParam(defaultValue="5")int pageSize,@RequestParam(defaultValue="1")int pageNo){
		return memberCardService.selectVipCardsByPage(pageSize,pageNo);
	}
	@RequestMapping(value = Url.MemberCard.selectCutInfo)
	public ModelAndView selectCutInfo(@RequestParam(defaultValue="5")int pageSize,@RequestParam(defaultValue="1")int pageNo){
		return memberCardService.selectCutInfo(pageSize,pageNo);
	}
	@RequestMapping(value = Url.MemberCard.selectPresentInfo)
	public ModelAndView selectPresentInfo(@RequestParam(defaultValue="5")int pageSize,@RequestParam(defaultValue="1")int pageNo){
		return memberCardService.selectPresentInfo(pageSize,pageNo);
	}
	@RequestMapping(value = Url.MemberCard.selectRecommendInfo)
	public ModelAndView selectRecommendInfo(@RequestParam(defaultValue="5")int pageSize,@RequestParam(defaultValue="1")int pageNo){
		return memberCardService.selectRecommendInfo(pageSize,pageNo);
	}
	
	/**
	 * 其余的三个修改操作
	* @author 高国藩
	* @date 2015年7月20日 下午12:23:14
	* @param pageSize
	* @param pageNo
	* @return
	 */
	@RequestMapping(value = Url.MemberCard.updateCutInfos, method = RequestMethod.POST)
	public String updateCutInfos(String cIds,MemberCardCommissionDto memberCardCommissionDto){
		return memberCardService.updateCutInfos(cIds,memberCardCommissionDto);
	}
	@RequestMapping(value = Url.MemberCard.updatePresentInfos, method = RequestMethod.POST)
	public String updatePresentInfos(String cIds,MemberCardGiveDto memberCardGiveDto){
		return memberCardService.updatePresentInfos(cIds,memberCardGiveDto);
	}
	@RequestMapping(value = Url.MemberCard.updateRecommendInfos, method = RequestMethod.POST)
	public String updateRecommendInfos(String cIds,MemberCardRecommendDto memberCardRecommendDto){
		return memberCardService.updateRecommendInfos(cIds,memberCardRecommendDto);
	}
	
/**********************************************疗程卡**********************************************************/
	
}
