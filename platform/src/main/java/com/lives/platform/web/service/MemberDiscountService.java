package com.lives.platform.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.entity.MemberDiscount;
import com.lives.platform.web.entity.ProjectInfo;
import com.lives.platform.web.entity.ProjectRelation;
import com.lives.platform.web.mapper.MemberDiscountMapper;
import com.lives.platform.web.mapper.ProjectInfoMapper;
import com.lives.platform.web.mapper.ProjectRelationMapper;

@Service
public class MemberDiscountService {
	
	@Autowired MemberDiscountMapper memberDiscountMapper;
	@Autowired ProjectRelationMapper projectRelationMapper;
	@Autowired ProjectInfoMapper projectInfoMapper;
	/**
	 * 项目会员折扣保存
	* @author 洪秋霞
	* @date 2015年7月7日 下午8:14:34
	 */
	@Transactional
	public void saveMembers(String categoryName,String[] storeIdArr,String selectStoresId,String[] projectInfoIds,String[] projectNo,String[] vipLevelIds,
			String[] fixedPrice, String[] discount, String vipLevelInfo,String vipLevelIdSet){
		StringBuffer vipLevelIdBuf = new StringBuffer();
		for (int j = 0; j < vipLevelIds.length; j++) {
			vipLevelIdBuf.append(vipLevelIds[j] + ",");
		}
		String vipLevelIdStr = vipLevelIdBuf.substring(0, vipLevelIdBuf.length()-1).toString();
		
		for (int i = 0; i < projectInfoIds.length; i++) {
			ProjectInfo pInfo = new ProjectInfo();
			pInfo.setProjectInfoId(Integer.parseInt(projectInfoIds[i].trim()));
			pInfo.setStatusMember(2);
			projectInfoMapper.updateByPrimaryKeySelective(pInfo);
		}
		
		saveMemberDiscountAndRelation(categoryName, storeIdArr, selectStoresId, projectInfoIds, projectNo, vipLevelIds, fixedPrice, discount, vipLevelInfo, vipLevelIdStr);
	}
	
	/**
	 * 保存会员折扣表 项目关系表
	* @author 洪秋霞
	* @date 2015年7月8日 下午7:37:46
	 */
	private void saveMemberDiscountAndRelation(String categoryName,String[] storeIdArr,String selectStoresId,String[] projectInfoIds,String[] projectNo, String[] vipLevelIds,
			String[] fixedPrice, String[] discount, String vipLevelInfo,String vipLevelIdStr){
		
		List<Integer> storesIdList = new ArrayList<Integer>();
		List<Integer> projectNoList = new ArrayList<Integer>();
		for (int i = 0; i < storeIdArr.length; i++) {
			storesIdList.add(Integer.parseInt(storeIdArr[i].trim()));
		}
		for (int i = 0; i < projectNo.length; i++) {
			projectNoList.add(Integer.parseInt(projectNo[i].trim()));
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stroesId", storesIdList);
		paramMap.put("projectNo", projectNoList);
		//根据选择的门店id 和 项目编号 查找项目表 分店的项目列表
		List<ProjectInfo> projectInfoList = projectInfoMapper.selectByStroesIdAndPno(paramMap);
		List<MemberDiscount> memberDiscountList = new ArrayList<MemberDiscount>();
		
		//新增
		for (int i = 0; i < storeIdArr.length; i++) {
			Integer storesId = Integer.parseInt(storeIdArr[i].trim());
			for (ProjectInfo projectInfo : projectInfoList) {
				if(projectInfo.getStroesId().intValue() != storesId.intValue()){
					continue;
				}
				Integer projectInfoId = projectInfo.getProjectInfoId();
				
				//根据门店id 选择项目id 和 会员等级 查找
				MemberDiscount mDiscount = new MemberDiscount();
				mDiscount.setProjectInfoId(projectInfoId);
				mDiscount.setStoresId(storesId);
				List<MemberDiscount> queryMemberDiscountList = memberDiscountMapper.selectByProperty(mDiscount);
				List<Integer> memberDiscountIdList = new ArrayList<Integer>();
				for (MemberDiscount memberDiscount : queryMemberDiscountList) {
					memberDiscountIdList.add(memberDiscount.getMemberDiscountId());
				}
				//先删除会员等级折扣 再新增
				if(memberDiscountIdList.size() > 0){
					memberDiscountMapper.deleteByDiscountIdList(memberDiscountIdList);
				}
				for (int j = 0; j < vipLevelIds.length; j++) {
					Integer cardNameId = Integer.parseInt(vipLevelIds[j].trim());
					
					MemberDiscount memberDiscount = new MemberDiscount();
					memberDiscount.setProjectInfoId(projectInfoId);
					memberDiscount.setCardNameId(cardNameId);//会员卡号名称
					if(!"/".equals(fixedPrice[j])){
						memberDiscount.setAmount(new BigDecimal(fixedPrice[j]));
					}
					if(!"/".equals(discount[j])){
						memberDiscount.setDiscount(Integer.parseInt(discount[j]));
					}
					memberDiscount.setStoresId(storesId);
					memberDiscountList.add(memberDiscount);
				}
				
			}
		}
		if(memberDiscountList.size() > 0){
			memberDiscountMapper.insertmemberDiscountBatch(memberDiscountList);
		}
		
		
		List<ProjectRelation> projectRelationList = new ArrayList<ProjectRelation>();
		for (int p = 0; p < projectInfoIds.length; p++) {
			Integer projectInfoId = Integer.parseInt(projectInfoIds[p].trim());
			//添加会员项目关系
			ProjectRelation projectRelation = new ProjectRelation();
			projectRelation.setCategoryName(categoryName);
			projectRelation.setType(2);
			projectRelation.setProjectInfoId(projectInfoId);
			projectRelation.setStroesIdSet(selectStoresId);
			projectRelation.setPositionInfo(vipLevelInfo);
			projectRelation.setProjectIdSet(vipLevelIdStr);//存会员等级id集合
			projectRelation.setCreateTime(DateUtils.getCurTime());
			projectRelationList.add(projectRelation);
//			projectRelationMapper.insertSelective(projectRelation);
			
			//根据项目id和门店选择id查找 把查询到的数据删除 再新增
			ProjectRelation relation = new ProjectRelation();
			relation.setProjectInfoId(projectInfoId);
			relation.setStroesIdSet(selectStoresId);
			relation.setType(2);
			List<ProjectRelation> relationList = projectRelationMapper.selectByProperty(relation);
			if(relationList.size() > 0){
				List<Integer> projectRelationIdList = new ArrayList<Integer>();
				for (ProjectRelation relation2 : relationList) {
					projectRelationIdList.add(relation2.getProjectRelationId());
				}
				projectRelationMapper.deleteProjectRelationBatch(projectRelationIdList);
			}
		}
		Integer parentStoresId = 1;//从session获取上级id 0 为总店
		//分店的项目列表
		if(0 == parentStoresId.intValue()){
			for (ProjectInfo projectInfo : projectInfoList) {
				Integer projectInfoId = projectInfo.getProjectInfoId();
				//添加会员项目关系
				ProjectRelation projectRelation = new ProjectRelation();
				projectRelation.setCategoryName(categoryName);
				projectRelation.setType(2);
				projectRelation.setProjectInfoId(projectInfoId);
				projectRelation.setStroesIdSet(projectInfo.getStroesId().toString());
				projectRelation.setPositionInfo(vipLevelInfo);
				projectRelation.setProjectIdSet(vipLevelIdStr);//存会员等级id集合
				projectRelation.setCreateTime(DateUtils.getCurTime());
				projectRelationList.add(projectRelation);
				
				//根据项目id和门店选择id查找 把查询到的数据删除 再新增
				ProjectRelation relation = new ProjectRelation();
				relation.setProjectInfoId(projectInfoId);
				relation.setStroesIdSet(projectInfo.getStroesId().toString());
				relation.setType(2);
				List<ProjectRelation> relationList = projectRelationMapper.selectByProperty(relation);
				if(relationList.size() > 0){
					List<Integer> projectRelationIdList = new ArrayList<Integer>();
					for (ProjectRelation relation2 : relationList) {
						projectRelationIdList.add(relation2.getProjectRelationId());
					}
					projectRelationMapper.deleteProjectRelationBatch(projectRelationIdList);
				}
				
				ProjectInfo pInfo = new ProjectInfo();
				pInfo.setProjectInfoId(projectInfoId);
				pInfo.setStatusMember(2);
				projectInfoMapper.updateByPrimaryKeySelective(pInfo);
			}
		}
		projectRelationMapper.insertBatch(projectRelationList);
		
	}
	
	/**
	 * 删除一个指定会员类别
	* @author 洪秋霞
	* @date 2015年7月8日 下午2:20:05
	* @param selectStoresId
	* @param vipLevelIdSet
	 */
	public void deleteMembersLevel(String selectStoresId,String vipLevelIds,String projectInfoIds){
		String[] storeIdArr = selectStoresId.split(",");
		String[] vipLevelIdArr = vipLevelIds.split(",");
		String[] projectInfoIdArr = projectInfoIds.split(",");
		/**
		 * 1.删除会员折扣信息表
		 */
		List<Integer> storeIdList = new ArrayList<Integer>();
		List<Integer> vipLevelIdList = new ArrayList<Integer>();
		for (int i = 0; i < storeIdArr.length; i++) {
			storeIdList.add(Integer.parseInt(storeIdArr[i].trim()));
		}
		for (int i = 0; i < vipLevelIdArr.length; i++) {
			vipLevelIdList.add(Integer.parseInt(vipLevelIdArr[i].trim()));
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberCardNo", vipLevelIdList);//会员等级id
		paramMap.put("storesId", storeIdList);//门店id
		//根据会员等级id和门店id找到会员折扣信息列表
		List<MemberDiscount> memberDiscountList = memberDiscountMapper.selectByCardNoList(paramMap);
		List<Integer> memberDiscountIdList = new ArrayList<Integer>();
		if(memberDiscountList.size() > 0){
			for (int i = 0; i < memberDiscountList.size(); i++) {
				memberDiscountIdList.add(memberDiscountList.get(i).getMemberDiscountId());
			}
			//批量删除会员信息折扣
			memberDiscountMapper.deleteByDiscountIdList(memberDiscountIdList);
		}
		
		/**
		 * 2.删除项目关系表
		 */
		for (int i = 0; i < projectInfoIdArr.length; i++) {
			Integer projectInfoId = Integer.parseInt(projectInfoIdArr[i].trim());
			//删除项目关系表
			ProjectRelation projectRelation = new ProjectRelation();
			projectRelation.setStroesIdSet(selectStoresId);
			projectRelation.setProjectInfoId(projectInfoId);
			projectRelation.setType(2);
			
			List<ProjectRelation> projectRelationList = projectRelationMapper.selectByProperty(projectRelation);
			if(projectRelationList.size() > 0){
				projectRelationMapper.deleteByPrimaryKey(projectRelationList.get(0).getProjectRelationId());
			}
			ProjectInfo poInfo = new ProjectInfo();
			poInfo.setProjectInfoId(projectRelationList.get(0).getProjectInfoId());
			poInfo.setStatusMember(1);
			projectInfoMapper.updateByPrimaryKeySelective(poInfo);
			
			
			//查询该门店下的所有分店的项目
			ProjectInfo projectInfo1 = new ProjectInfo();
			projectInfo1.setParentId(projectInfoId);
			List<ProjectInfo> projectInfoList = projectInfoMapper.selectByProperty(projectInfo1);
			for (ProjectInfo projectInfo : projectInfoList) {
				Integer pInfoId = projectInfo.getProjectInfoId();
				ProjectRelation projectRelation2 = new ProjectRelation();
				projectRelation2.setProjectInfoId(pInfoId);
				projectRelation2.setStroesIdSet(projectInfo.getStroesId().toString());
				projectRelation2.setType(2);
				List<ProjectRelation> projectRelationList2 = projectRelationMapper.selectByProperty(projectRelation2);
				if(projectRelationList2.size()>0){
					projectRelationMapper.deleteByPrimaryKey(projectRelationList2.get(0).getProjectRelationId());
				}
				ProjectInfo poInfo2 = new ProjectInfo();
				poInfo2.setProjectInfoId(pInfoId);
				poInfo2.setStatusMember(1);
				projectInfoMapper.updateByPrimaryKeySelective(poInfo2);
			}
			
		}
	}
	
	
}
