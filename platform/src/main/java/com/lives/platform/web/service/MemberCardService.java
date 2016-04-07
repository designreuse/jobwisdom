package com.lives.platform.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.consts.View;
import com.lives.platform.web.dto.BaseDto;
import com.lives.platform.web.dto.MemberCardCommissionDto;
import com.lives.platform.web.dto.MemberCardGiveDto;
import com.lives.platform.web.dto.MemberCardRecommendDto;
import com.lives.platform.web.entity.CodeLibrary;
import com.lives.platform.web.entity.MemberCardInfo;
import com.lives.platform.web.entity.Page;
import com.lives.platform.web.mapper.CodeLibraryMapper;
import com.lives.platform.web.mapper.MemberCardInfoMapper;
import com.lives.platform.web.mapper.MemberCardCommissionMapper;
import com.lives.platform.web.mapper.MemberCardGiveMapper;
import com.lives.platform.web.mapper.MemberCardRecommendMapper;

@Service
public class MemberCardService {

	@Autowired
	private CodeLibraryMapper codeLibraryMapper;
	@Autowired
	private MemberCardInfoMapper memberCardInfoMapper;
	@Autowired
	private MemberCardCommissionMapper memberCardCommissionMapper;
	@Autowired
	private MemberCardGiveMapper memberCardGiveMapper;
	@Autowired
	private MemberCardRecommendMapper memberCardRecommendMapper;
	

	public String save(MemberCardInfo memberCardInfo, MemberCardCommissionDto memberCardCommissionDto,MemberCardGiveDto memberCardGiveDto,MemberCardRecommendDto memberCardRecommendDto) {
		String url = "redirect:"+Url.MemberCard.memberCard;
		int ok = 0;
		if(memberCardInfo.getCardIsMessage()==null)memberCardInfo.setCardIsMessage(0);
		memberCardInfo.setStoreId(1);
		memberCardInfo.setCardType(1);
		int i = memberCardInfoMapper.insert(memberCardInfo);
		if(i<=0)return url;
		Integer cardId = memberCardInfo.getCardId();
		memberCardCommissionDto.setCardId(cardId);
		ok = memberCardCommissionMapper.insert(memberCardCommissionDto);
		memberCardGiveDto.setCardId(cardId);
		ok = memberCardGiveMapper.insert(memberCardGiveDto);
		memberCardRecommendDto.setCardId(cardId);
		System.out.println(memberCardRecommendDto.toString());
		ok = memberCardRecommendMapper.insert(memberCardRecommendDto);
		return url;
	}

	public ModelAndView sendMemberCard(Integer pageNo, Integer pageSize) {
		Page<MemberCardInfo> page = new Page<MemberCardInfo>();
		if(pageNo == null)pageNo = 1;
		if(pageSize == null)pageSize = 5;
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		/**数据字典*/
		List<CodeLibrary> bxl = codeLibraryMapper.selectByTypeName("期限");
		List<CodeLibrary> pxl = codeLibraryMapper.selectByTypeName("提成方式");
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardType", 1);
		map.put("storeId", 1);
		page.setParams(map);
		List<MemberCardInfo> ls = memberCardInfoMapper.selectVipCardsByPage(page);
		
		ModelAndView view = new ModelAndView(View.MemberCard.sendMemberCard);
		view.addObject("MemberCardInfo", ls);
		bxl.get(0).setCodeName("一次赠送");
		view.addObject("isUse", bxl);
		view.addObject("pxl", pxl);
		view.addObject("page", page);
		return view;
	}

	public BaseDto deleteCard(Integer cId) {
		int k = 0;
		k = memberCardInfoMapper.deleteByPrimaryKey(cId);
		k = memberCardCommissionMapper.deleteByPrimaryKey(cId);
		k = memberCardGiveMapper.deleteByPrimaryKey(cId);
		k = memberCardRecommendMapper.deleteByPrimaryKey(cId);
		if(k<0)return new BaseDto(0, "删除失败");
		return new BaseDto(0, "删除成功");
	}

	public JSONObject updateCard(Integer cardId) {
		MemberCardInfo memberCardInfo = memberCardInfoMapper.selectByPrimaryKey(cardId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("MemberCardInfo", memberCardInfo);
		jsonObject.put("cardId", cardId);
		return jsonObject;
	}

	public String realyUpdate(MemberCardInfo memberCardInfo) {
		String url = "redirect:"+Url.MemberCard.memberCard;
		memberCardInfo.setCardType(1);
		memberCardInfo.setStoreId(1);
		memberCardInfoMapper.updateByPrimaryKey(memberCardInfo);
		return url;
	}

	public ModelAndView selectVipCardsByPage(int pageSize, int pageNo) {
		Page<MemberCardInfo> page = new Page<MemberCardInfo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardType", 1);
		map.put("storeId", 1);
		page.setParams(map);
		List<MemberCardInfo> ls = memberCardInfoMapper.selectVipCardsByPage(page);
		/**数据字典*/
		List<CodeLibrary> bxl = codeLibraryMapper.selectByTypeName("期限");
		List<CodeLibrary> pxl = codeLibraryMapper.selectByTypeName("提成方式");
		ModelAndView view  = new ModelAndView(View.MemberCard.sendMemberCard);
		view.addObject("MemberCardInfo", ls);
		view.addObject("page", page);
		view.addObject("isUse", bxl);
		view.addObject("pxl", pxl);
		return view;
	}

	public ModelAndView selectCutInfo(Integer pageSize,Integer pageNo) {
		Page<MemberCardCommissionDto> page = new Page<MemberCardCommissionDto>();
		if(pageNo == null)pageNo = 1;
		if(pageSize == null)pageSize = 5;
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardType", 1);
		map.put("storeId", 1);
		page.setParams(map);
		List<MemberCardCommissionDto> al = memberCardCommissionMapper.selectCutInfosByPage(page);
		/**数据字典*/
		List<CodeLibrary> bxl = codeLibraryMapper.selectByTypeName("期限");
		List<CodeLibrary> pxl = codeLibraryMapper.selectByTypeName("提成方式");
		ModelAndView view = new ModelAndView("memberCard/cutInfo");
		view.addObject("MemberCardCommission", al);
		view.addObject("page", page);
		view.addObject("isUse", bxl);
		view.addObject("pxl", pxl);
		return view;
	}

	public ModelAndView selectPresentInfo(Integer pageSize,Integer pageNo) {
		Page<MemberCardGiveDto> page = new Page<MemberCardGiveDto>();
		if(pageNo == null)pageNo = 1;
		if(pageSize == null)pageSize = 5;
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardType", 1);
		map.put("storeId", 1);
		page.setParams(map);
		List<MemberCardGiveDto> al = memberCardGiveMapper.selectPresentInfosByPage(page);
		/**数据字典*/
		List<CodeLibrary> bxl = codeLibraryMapper.selectByTypeName("期限");
		List<CodeLibrary> pxl = codeLibraryMapper.selectByTypeName("提成方式");
		bxl.get(0).setCodeName("一次赠送");
        ModelAndView view = new ModelAndView("memberCard/presentInfo");
		view.addObject("MemberCardGive", al);
		view.addObject("page", page);
		bxl.get(0).setCodeName("一次赠送");
		view.addObject("isUse", bxl);
		view.addObject("pxl", pxl);
		return view;
	}

	public ModelAndView selectRecommendInfo(Integer pageSize,Integer pageNo) {
		Page<MemberCardRecommendDto> page = new Page<MemberCardRecommendDto>();
		if(pageNo == null)pageNo = 1;
		if(pageSize == null)pageSize = 5;
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardType", 1);
		map.put("storeId", 1);
		page.setParams(map);
		List<MemberCardRecommendDto> al = memberCardRecommendMapper.selectRecommendInfosByPage(page);
		List<CodeLibrary> bxl = codeLibraryMapper.selectByTypeName("期限");
		List<CodeLibrary> pxl = codeLibraryMapper.selectByTypeName("提成方式");
        ModelAndView view = new ModelAndView("memberCard/recommendInfo");
		view.addObject("MemberCardRecommend", al);
		view.addObject("page", page);
		view.addObject("isUse", bxl);
		view.addObject("pxl", pxl);
		return view;
	}

	public String updateCutInfos(String cIds, MemberCardCommissionDto memberCardCommissionDto) {
		if(cIds.equals("")){
			return "redirect:/selectCutInfo";
		}else{
			String ids[] = cIds.split("_");
			for (int i = 0; i < ids.length; i++) {
				memberCardCommissionDto.setCardId(Integer.valueOf(ids[i]));
				memberCardCommissionMapper.updateByPrimaryKeySelective(memberCardCommissionDto);
			}
			return "redirect:/selectCutInfo";
		}
	}

	public String updatePresentInfos(String cIds,MemberCardGiveDto memberCardGiveDto) {
		if(cIds.equals("")){
			return "redirect:/selectPresentInfo";
		}else{
			String ids[] = cIds.split("_");
			for (int i = 0; i < ids.length; i++) {
				memberCardGiveDto.setCardId(Integer.valueOf(ids[i]));
				memberCardGiveMapper.updateByPrimaryKeySelective(memberCardGiveDto);
			}
			return "redirect:/selectPresentInfo";
		}
	}

	public String updateRecommendInfos(String cIds,MemberCardRecommendDto memberCardRecommendDto) {
		if(cIds.equals("")){
			return "redirect:/selectRecommendInfo";
		}else{
			String ids[] = cIds.split("_");
			for (int i = 0; i < ids.length; i++) {
				memberCardRecommendDto.setCardId(Integer.valueOf(ids[i]));
				memberCardRecommendMapper.updateByPrimaryKeySelective(memberCardRecommendDto);
			}
			return "redirect:/selectRecommendInfo";
		}
	}

}
