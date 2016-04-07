package com.zefun.wechat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.entity.AgentInfo;
import com.zefun.web.entity.AgentRecommendRecord;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.SalesmanInfo;
import com.zefun.web.entity.SalesmanRecommendRecord;
import com.zefun.web.mapper.AgentRecommendRecordMapper;
import com.zefun.web.mapper.SalesmanInfoMapper;
import com.zefun.web.mapper.SalesmanRecommendRecordMapper;
import com.zefun.web.vo.SalesmanInfoVo;

/**
 * 业务员信息服务
 * @author lzc
 *
 */
@Service
public class SalesmanInfoService {
	
	/** 业务员信息映射 */
	@Autowired
	private SalesmanInfoMapper salesmanInfoMapper;
	
	/** 业务员门店关联映射 */
	@Autowired
	private SalesmanRecommendRecordMapper salesmanRecommendRecordMapper;
	
	/** 代理门店推荐信息映射 */
	@Autowired
	private AgentRecommendRecordMapper agentRecommendRecordMapper;
	
	/**
	 * 根据渠道(代理)商id查询业务员
	 * @param agentInfo  渠道(代理)商
	 * @return  业务员ModelAndView
	 */
	public ModelAndView findSalesmanInfoByAgentId(AgentInfo agentInfo) {
		ModelAndView mav = new ModelAndView(View.Salesman.VIEW_SALESMAN_LIST);
		//该渠道基本信息
		mav.addObject("agentInfo", agentInfo);
		//该渠道下业务员总数(没删除的业务员)
		mav.addObject("salesmanCount", salesmanInfoMapper.selectCountSalesmanByAgentId(agentInfo.getAgentId()));
		//该渠道下业务员集(没删除的业务员)
		List<SalesmanInfo> salesmanInfoList = salesmanInfoMapper.selectSalesmanInfoByAgentId(agentInfo.getAgentId());
		for (int i=0; i<salesmanInfoList.size(); i++) {
			SalesmanInfo salesman = salesmanInfoList.get(i);
			salesman.setCountStoreCompleted(salesmanRecommendRecordMapper.selectCountStoreCompleted(salesman.getSalesmanId()));
			salesman.setCountStoreProcessing(salesmanRecommendRecordMapper.selectCountStoreProcessing(salesman.getSalesmanId()));
		}
		mav.addObject("salesmanInfoList", salesmanInfoList);
		return mav;
	}
	
	/**
	 * 分页查询业务员
	 * @param page 分页信息
	 * @param salesmanInfoVo  查询业务员参数集
	 * @return  业务员集
	 */
	public Page<SalesmanInfo> findSalesmanInfoByPage(Page<SalesmanInfo> page, SalesmanInfoVo salesmanInfoVo) {
		/*if (salesmanInfoVo == null) {
			salesmanInfoVo = new SalesmanInfoVo();
			return null;
		}*/
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("agentId", salesmanInfoVo.getAgentId());
		page.setParams(params);
		List<SalesmanInfo> salesmanInfoList = salesmanInfoMapper.selectSalesmanInfoByPage(page);
		page.setResults(salesmanInfoList);
		return page;
	}
	
	/**
	 * 新增业务员
	 * @param salesmanInfo  业务员实体
	 * @return  是否成功
	 */
	public boolean addSalesmanInfo(SalesmanInfo salesmanInfo) {
		salesmanInfo.setCreateTime(DateUtil.getCurTime());
		int resultNum = salesmanInfoMapper.insertSelective(salesmanInfo);
		return resultNum == 1 ? true : false;
	}
	
	/**
	 * 停用业务员
	 * @param salesmanId  业务员id
	 * @return  是否成功
	 */
	public boolean deactivateSalesman(Integer salesmanId) {
		/*SalesmanInfo salesmanInfo = new SalesmanInfo();
		salesmanInfo.setSalesmanId(salesmanId);
		salesmanInfo.setStatus(1);
		int resultNum = salesmanInfoMapper.updateByPrimaryKeySelective(salesmanInfo);*/
		int resultNum = salesmanInfoMapper.deactivateSalesman(salesmanId);
		return resultNum == 1 ? true : false;
	}
	
	/**
	 * 删除业务员
	 * @param salesmanId  业务员id
	 * @return  是否成功
	 */
	public boolean deleteSalesman(Integer salesmanId) {
		/*SalesmanInfo salesmanInfo = new SalesmanInfo();
		salesmanInfo.setSalesmanId(salesmanId);
		salesmanInfo.setIsDelete(1);
		int resultNum = salesmanInfoMapper.updateByPrimaryKeySelective(salesmanInfo);*/
		int resultNum = salesmanInfoMapper.deleteSalesman(salesmanId);
		return resultNum == 1 ? true : false;
	}
	
	/**
	 * 重新启用业务员
	 * @param salesmanId  业务员id
	 * @return  是否成功
	 */
	public boolean reEnableSalesman(Integer salesmanId) {
		SalesmanInfo salesmanInfo = new SalesmanInfo();
		salesmanInfo.setSalesmanId(salesmanId);
		salesmanInfo.setStatus(0);
		salesmanInfo.setOpenId(null);
		int resultNum = salesmanInfoMapper.updateByPrimaryKeySelective(salesmanInfo);
		return resultNum == 1 ? true : false;
	}
	
	/**
	 * 业务员推荐门店
	 * @param storeId  被推荐门店id
	 * @param salesmanId  业务员id
	 * @param salesmanAgentId  (业务员所属)渠道商id
	 * @param thisStoreCityAgentId  (该门店所在城市/所有者)渠道商id
	 * @param recommendType  推荐类型
	 * @return  推荐是否成功
	 */
	public boolean salesmanRecommendStore(int storeId, int salesmanId, int salesmanAgentId, int thisStoreCityAgentId, int recommendType) {
		int result = 0;
		SalesmanRecommendRecord srr = new SalesmanRecommendRecord();
		srr.setRecommendSalesmanId(salesmanId);
		srr.setRecommendedId(storeId);
		srr.setRecommendType(recommendType);
		//salesman_recommend_record(业务员门店关联)插入记录
		result += salesmanRecommendRecordMapper.insert(srr);
		AgentRecommendRecord arr = new AgentRecommendRecord();
		/*arr.setAgentId(thisStoreCityAgentId);  //该门店所有者
		arr.setRecommendedId(storeId);  //被推荐者还是该门店
		arr.setRecommendId(salesmanAgentId);  //推荐该门店业务员所在渠道商id(该业务员推荐该商户,业务员所在渠道也算推荐了该商户)
		arr.setRecommendType(recommendType);*/
		/**
		 * 如果经上面注释方式存储关系，会出现以下问题：
		 * 如果该门店是业务员推荐
		 * 经渠道商(该业务员在该渠道商门下)从"我的门店"下找不到该业务员推荐的门店，所以修改为以下所示。
		 */
		arr.setRecommendedId(storeId);
		arr.setRecommendType(recommendType);
		arr.setAgentId(salesmanAgentId);  //该门店推荐者业务员，所在的渠道
		/**
		 * 这里的recommendId是"被推荐的门店(暂且定义为门店BBB)的推荐者业务员(业务员BB)的推荐者渠道商(渠道商B)的推荐者渠道商(A)的id"
		 * 上面arr对象中的recommendedId(被推荐者id)就是门店BBB的id
		 * 		arr对象中的recommendType(推荐类型)是门店,即为1
		 * 		arr对象中的agentId(渠道商id)，即为渠道商B的id
		 * 那么arr对象中的recommendId(推荐者id)，即为渠道商A的id
		 * 根据AgentInfoService.addAgentApplyInfo方法最后一点  和  AgentInfoService.recommend方法得知，要得到这里的渠道商A的id，
		 * 查询语句如下: 
		 * select recommend_id from agent_recommend_record where agent_id=0 and recommended_id='渠道商B的id(salesmanAgentId)' and recommend_type=2
		 */
		if (salesmanAgentId != 0) {
			AgentRecommendRecord a = agentRecommendRecordMapper.selectAgentRecommendRecordByRecommendedIdAndTypeAndAgentId(salesmanAgentId, 0, 2);
			if (a != null && a.getRecommendId() == 0) {
				arr.setRecommendId(a.getRecommendId());
			}
			else {
				arr.setRecommendId(App.System.DEFAULT_RECOMMEND_AGENT_ID);
			}
		} 
		else {
			arr.setRecommendId(App.System.DEFAULT_RECOMMEND_AGENT_ID);
		}
		//agent_recommend_record(渠道门店关联)插入记录
		result += agentRecommendRecordMapper.insert(arr);
		return result == 2 ? true : false;
	}
	
	/**
	 * 根据电话查询业务员(判断该电话是否已经注册)(没删除的业务员)
	 * @param phone  电话
	 * @return  true 存在
	 */
	public boolean decidePhone(String phone) {
		if (salesmanInfoMapper.selectSalesmanByPhone(phone) != null) {
			return true;
		}
		return false;
	}

}
