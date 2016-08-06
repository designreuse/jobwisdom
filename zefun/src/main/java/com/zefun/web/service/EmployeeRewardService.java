package com.zefun.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.EmployeeRewardDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EmployeeReward;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeRewardMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreManageRuleMapper;
import com.zefun.web.vo.EmployeeRewardVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 员工奖惩记录service
 * @author lzc
 *
 */
@Service
@Transactional
public class EmployeeRewardService {
	
	/** 员工奖惩映射 */
    @Autowired
    private EmployeeRewardMapper employeeRewardMapper;
    
    /** 门店管理制度映射 */
    @Autowired
    private StoreManageRuleMapper storeManageRuleMapper;
    /** 门店 */
    @Autowired
    private  StoreInfoMapper  storeInfoMapper;
    /** 员工 */
    @Autowired
    private  EmployeeInfoMapper  employeeInfoMapper;
    
    
    
    
    /**
     * 添加奖惩

     * @param vo  添加参数详情
     * @return  添加结果
     */
    public BaseDto addEmployeeReward(EmployeeReward vo) {
        vo.setModifytime(DateUtil.getCurDate());
        int ruleId = Integer.parseInt(vo.getType());
        StoreManageRule storeManageRule = storeManageRuleMapper.selectByPrimaryKey(ruleId);
        vo.setNumber(Double.parseDouble(storeManageRule.getProcessMoney().toString()));
        vo.setType(storeManageRule.getRuleName());
        employeeRewardMapper.insertSelective(vo);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, vo);
    }
    
    
    /**
     * 查询员工奖惩汇总
     * @param vo  员工奖惩vo
     * @param storeAccount  storeAccount
     * @param storeId  storeId
     * @return  ModelAndView
     */
    public ModelAndView findCountEmployeeRewardHome(EmployeeRewardVo vo, String storeAccount, Integer storeId) {
        ModelAndView mav = new ModelAndView(View.EmployeeReward.HOME);
   
        ArrayList<Integer> lists = new ArrayList<Integer>();
        List<StoreInfo> selectByStoreAccount = storeInfoMapper.selectByStoreAccount(storeAccount);
        for (StoreInfo storeInfo : selectByStoreAccount) {
            lists.add(storeInfo.getStoreId());
        }
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", null);
        params.put("storeIds", lists);
        params.put("time", vo.getTime());
        Page<EmployeeRewardDto> page = new Page<EmployeeRewardDto>();
        page.setParams(params);
        page.setPageNo(1);
        page.setPageSize(17);
        //全部员工
        List<EmployeeBaseDto> selectEmployeeListByStoreIdAll = employeeInfoMapper.selectEmployeeListByStoreIdAll(params);
        
        //全部奖罚数据
        List<EmployeeRewardDto> list = employeeRewardMapper.selectCountRewardByPage(page);
        page.setResults(list);
        
        //全部奖罚数据  员工group 
        List<EmployeeRewardDto> resultsGroup = employeeRewardMapper.selectCountRewardByGroupBy(params);
        
    	  //处理方式(1:奖励，2:惩罚)
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeIds", lists);
        List<StoreManageRule> storeManageRule = storeManageRuleMapper.selectRuleListByAccountStoreId(map);
        map.put("processType1", 1);
        List<StoreManageRule> ruleList1 = storeManageRuleMapper.selectRuleListByAccountStoreId(map);
        map.put("processType1", 2);
        List<StoreManageRule> ruleList2 = storeManageRuleMapper.selectRuleListByAccountStoreId(map);
        StoreManageRule rule = new StoreManageRule();
        if (ruleList1.size() == 0) {
            ruleList1.add(rule);
        }
        if (ruleList2.size() == 0) {  
            ruleList2.add(rule);
        }
//        ruleList1.clear();
//        ruleList2.clear();
	    JSONArray jsonarray = classify(list, resultsGroup, ruleList1, ruleList2);
    
	    mav.addObject("page", page);
	    mav.addObject("selectEmployeeListByStoreIdAll", selectEmployeeListByStoreIdAll);
	    mav.addObject("selectByStoreAccount", selectByStoreAccount);
	    mav.addObject("storeManageRule", storeManageRule);
        mav.addObject("ruleList1", ruleList1);
        mav.addObject("ruleList2", ruleList2);
        mav.addObject("ruleList1Str", JSONArray.fromObject(ruleList1).toString());
        mav.addObject("ruleList2Str", JSONArray.fromObject(ruleList2).toString());
    	mav.addObject("jsonarray", jsonarray);
    	return mav;
    }
    
    
    /**
     * 给查询的奖惩总数归类
     * @param employeeRewardDtoList  奖罚数据
     * @param resultsGroup  员工
     * @param ruleList1  奖励
     * @param ruleList2  惩罚
     * @return  汇总好的JSONArray
     */
    public JSONArray classify(List<EmployeeRewardDto> employeeRewardDtoList, List<EmployeeRewardDto> resultsGroup, 
            List<StoreManageRule> ruleList1, List<StoreManageRule> ruleList2) {
      
        
        JSONArray jsona = new JSONArray();
        JSONObject jsono = new JSONObject();
        JSONArray jsonjl = new JSONArray();
        JSONArray jsoncl = new JSONArray();
        
        resultsGroup.stream().forEach(em ->{
                jsono.clear();
                List<EmployeeRewardDto> collect = employeeRewardDtoList.parallelStream()
                        .filter(p -> p.getEmployeeId().intValue() == em.getEmployeeId().intValue()).collect(Collectors.toList());
                
                List<EmployeeRewardDto> reward1 = collect.stream().filter(p1 -> p1.getIsReward().equals("1"))
                        .collect(Collectors.toList());
                List<EmployeeRewardDto> reward2 = collect.stream().filter(p2 -> p2.getIsReward().equals("0"))
                        .collect(Collectors.toList());
                double sum1 = 0.0;
                double sum2 = 0.0;
                if (reward1.size() != 0) {
                    sum1 =  reward1.parallelStream().mapToDouble(EmployeeRewardDto::getNumber).sum();
                }
                if (reward2.size() != 0) {
                    sum2 = reward2.parallelStream().mapToDouble(EmployeeRewardDto::getNumber).sum(); // 可以简写为.sum()
                }
                jsono.accumulate("sum1", sum1);
                jsono.accumulate("sum2", sum2);
                jsono.accumulate("total", sum1- sum2);
                jsono.accumulate("code", em.getEmployeeCode());
                jsono.accumulate("name", em.getEmployeeName());
                jsonjl.clear();
                for (int i = 0; i < ruleList1.size(); i++) {
                    String name = ruleList1.get(i).getRuleName();
                    double price = reward1.parallelStream().filter(f1 ->f1.getType().equals(name)).mapToDouble(EmployeeRewardDto::getNumber).sum();
                    jsonjl.add(price>0?price:0);
                }
                jsono.accumulate("jsonjl", jsonjl);
                jsoncl.clear();
                for (int i = 0; i < ruleList2.size(); i++) {
                    String name = ruleList2.get(i).getRuleName();
                    double price = reward2.parallelStream().filter(f2 ->f2.getType().equals(name)).mapToDouble(EmployeeRewardDto::getNumber).sum();
                    jsoncl.add(price>0?price:0);
                }
                jsono.accumulate("jsoncl", jsoncl);
                jsona.add(jsono);
            });
        return jsona;
    }


    /**
     *  门店下的员工
    * @author 骆峰
    * @date 2016年8月5日 下午6:41:44
    * @param storeId storeId
    * @return BaseDto
     */
    public BaseDto selectEmployeeByAccount(Integer storeId) {
        List<EmployeeInfo> selectEmployeeList = employeeInfoMapper.selectEmployeeList(storeId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, selectEmployeeList);
    }
    

    /**
     * 运功几个
     * @param vo  员工奖惩vo
     * @param storeAccount  storeAccount
     * @param lists  lists
     * @return Page<EmployeeRewardDto>
     */
//    public Page<EmployeeRewardDto> findCountEmployeegroup(EmployeeRewardVo vo, String storeAccount,  ArrayList<Integer> lists) {
//     
//        //给查询的奖惩总数归类
//        page.setResults(list);
//        return page;
//    }
    
    /**
     * 员工奖惩记录汇总分页查询
     * @param vo  员工奖惩vo
     * @param storeAccount  storeAccount
     * @param lists  lists
     * @return Page<EmployeeRewardDto>
     */
//    public Page<EmployeeRewardDto> findCountEmployeeRewardByPage(EmployeeRewardVo vo, String storeAccount, ArrayList<Integer> lists) {
//     
//        Page<EmployeeRewardDto> page = new Page<EmployeeRewardDto>();
//        page.setPageNo(vo.getPageNo());
//        page.setPageSize(vo.getPageSize());
//    
//        //给查询的奖惩总数归类
//        page.setResults(list); 
//        return page;
//    }
//
//    /**
//     * 全查询员工奖惩汇总
//    * @author DavidLiang
//    * @date 2016年1月16日 下午8:44:44
//    * @param vo  查询条件
//    * @return  奖惩汇总
//     */
//    public BaseDto findALlListAction(EmployeeRewardVo vo) {
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("storeId", vo.getStoreId());
//		params.put("rewardType", vo.getRule());
//		params.put("time", vo.getTime());
//		params.put("employeeName", vo.getEmployeeName());
//		List<EmployeeRewardDto> list = employeeRewardMapper.selectAllCountReward(params);
//		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, classify(list));
//    }
//    
//    /**
//     * 分页查询员工奖惩汇总
//     * @param page 分页
//     * @param vo 查询条件
//     * @return 奖惩汇总
//     */
//    public BaseDto findListAction(Page<EmployeeRewardDto> page, EmployeeRewardVo vo) {
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("storeId", vo.getStoreId());
//		params.put("rewardType", vo.getRule());
//		params.put("time", vo.getTime());
//		params.put("employeeName", vo.getEmployeeName());
//		page.setParams(params);
//		List<EmployeeRewardDto> list = employeeRewardMapper.selectCountRewardByPage(page);
//		page.setResults(classify(list));
//		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
//    }
//    
//    /**
//     *  员工奖惩记录汇总全查询
//    * @author DavidLiang
//    * @date 2016年1月17日 下午3:37:08
//    * @param vo  员工奖惩vo
//    * @return  员工奖惩记录
//     */
//    public List<EmployeeRewardDto> findAllCountEmployeeReward(EmployeeRewardVo vo) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("storeId", vo.getStoreId());
//		params.put("rewardType", vo.getRule());
//		params.put("time", vo.getTime());
//		params.put("employeeName", vo.getEmployeeName());
//		List<EmployeeRewardDto> rewardCountList = employeeRewardMapper.selectAllCountReward(params);
//		//给查询的奖惩总数归类
//        return classify(rewardCountList);
//    }
// 
//    
//    
//  
//    
//    /**
//     * 分页查询奖惩详细action
//     * @param page  页码页距查询条件
//     * @param vo  其他查询条件
//     * @return  带状态奖惩分页查询结果
//     */
//    public BaseDto findEmployeeRewardByPageAction(Page<EmployeeReward> page, EmployeeRewardVo vo) {
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("type", vo.getType());
//		params.put("employeeId", vo.getEmployeeId());
//		params.put("time", vo.getTime());
//		page.setParams(params);
//		List<EmployeeReward> list = employeeRewardMapper.selectRewardByPage(page);
//		page.setResults(list);
//		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
//    }
//    
//    /**
//     * 分页查询奖惩详细服务
//     * @param vo  查询条件
//     * @return  奖惩分页内容
//     */
//    public Page<EmployeeReward> findEmployeeRewardByPage(EmployeeRewardVo vo) {
//    	Page<EmployeeReward> page = new Page<EmployeeReward>();
//    	page.setPageNo(vo.getPageNo());
//		page.setPageSize(vo.getPageSize());
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("type", vo.getType());
//		params.put("employeeId", vo.getEmployeeId());
//		params.put("time", vo.getTime());
//		page.setParams(params);
//		List<EmployeeReward> list = employeeRewardMapper.selectRewardByPage(page);
//		page.setResults(list);
//		return page;
//    }
//    
//
//    
//    /**
//     * 删除奖惩记录
//    * @author DavidLiang
//    * @date 2016年1月14日 下午8:47:24
//    * @param rewardId  奖惩id
//    * @return  是否成功
//     */
//    public boolean deleteEmployeeReward(int rewardId) {
//    	return employeeRewardMapper.deleteByPrimaryKey(rewardId) == 1 ? true : false;
//    }
//    
//  
//    
//    /**
//     * 填充数据
//     * @param queryDto  数据库查询出的dto
//     * @param tempDto  临时(转移)存放dto
//     * @param exist  true存在，false不存在
//     * @return  临时(转移)存放dto(tempDto)
//     */
//    private EmployeeRewardDto dataFill(EmployeeRewardDto queryDto, EmployeeRewardDto tempDto, boolean exist) {
//    	if (!exist) {
//    		tempDto = new EmployeeRewardDto();
//    	}
//    	if (queryDto.getType() != null && queryDto.getCount() != null) {
//    		if (queryDto.getType().equals("1")) {
//    			tempDto.setCountOfLate(queryDto.getCount());
//    			if (queryDto.getMoney() != null) {
//    				tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//    		} 
//    		else if (queryDto.getType().equals("2")) {
//    			tempDto.setCountOfEarlyLeave(queryDto.getCount());
//    			if (queryDto.getMoney() != null) {
//    				tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//    		}
//			else if (queryDto.getType().equals("3")) {
//				tempDto.setCountOfHoliday(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//			}
//			else if (queryDto.getType().equals("4")) {
//				tempDto.setCountOfAbsenteeism(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//			}
//			else if (queryDto.getType().equals("5")) {
//				tempDto.setCountOfAttendance(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//    				tempDto.setMoneyOfReward(queryDto.getMoney() + tempDto.getMoneyOfReward());
//    			}
//			}
//			else if (queryDto.getType().equals("6")) {
//				tempDto.setCountOfSmallMistake(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//			}
//			else if (queryDto.getType().equals("7")) {
//				tempDto.setCountOfSeriousMistake(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//			}
//			else if (queryDto.getType().equals("8")) {
//				tempDto.setCountOfWarning(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//			}
//			else if (queryDto.getType().equals("9")) {
//				tempDto.setCountOfFavourable(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//					tempDto.setMoneyOfReward(queryDto.getMoney() + tempDto.getMoneyOfReward());
//    			}
//			}
//			else if (queryDto.getType().equals("10")) {
//				tempDto.setCountOfBadpost(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//			}
//			else if (queryDto.getType().equals("11")) {
//				tempDto.setCountOfComplaint(queryDto.getCount());
//				if (queryDto.getMoney() != null) {
//					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
//    			}
//			}
//    	}
//    	if (!exist) {
//    		tempDto.setEmployeeId(queryDto.getEmployeeId());
//    		//tempDto.setType(queryDto.getType());
//    		if (queryDto.getIsReward() != null) {
//        		tempDto.setIsReward(queryDto.getIsReward());
//        	}
//        	if (queryDto.getNumber() != null) {
//        		tempDto.setNumber(queryDto.getNumber());
//        	}
//        	if (queryDto.getModifyer() != null) {
//        		tempDto.setModifyer(queryDto.getModifyer());
//        	}
//        	if (queryDto.getModifytime() != null) {
//        		tempDto.setModifytime(queryDto.getModifytime());
//        	}
//        	if (queryDto.getReasons() != null) {
//        		tempDto.setReasons(queryDto.getReasons());
//        	}
//        	if (queryDto.getEmployeeName() != null) {
//        		tempDto.setEmployeeName(queryDto.getEmployeeName());
//        	}
//        	if (queryDto.getEmployeeCode() != null) {
//        		tempDto.setEmployeeCode(queryDto.getEmployeeCode());
//        	}
//    	}
//    	return tempDto;
//    }
//    
//    /**
//     * 根据奖惩类型判断是否奖励
//     * @param type  奖惩类型
//     * @return  "1"(奖励), "0"(惩罚)
//     */
//    private String decideIsReward(String type) {
//    	if (type.equals(String.valueOf(EmployeeRewardTypeEnum.FAVOURABLE.getEmployeeRewardType())) 
//    			  || type.equals(String.valueOf(EmployeeRewardTypeEnum.ATTENDANCE.getEmployeeRewardType()))) {
//    		return "1";
//    	}
//    	return "0";
//    }
//    
//    /**
//     * 根据奖惩类型查询店铺规
//     * @param type  奖惩类型
//     * @param storeId  店铺id
//     * @return  该店铺规则
//     */
//    private StoreManageRule getMoneyByType(String type, int storeId) {
//    	String ruleName = null;
//    	if ("1".equals(type)) {
//    		ruleName = "迟到";
//    	}
//    	else if ("2".equals(type)) {
//    		ruleName = "早退";
//    	}
//    	else if ("3".equals(type)) {
//    		ruleName = "请假";
//    	}
//    	else if ("4".equals(type)) {
//    		ruleName = "旷工";
//    	}
//    	else if ("5".equals(type)) {
//    		ruleName = "全勤";
//    	}
//    	else if ("6".equals(type)) {
//    		ruleName = "小过";
//    	}
//    	else if ("7".equals(type)) {
//    		ruleName = "大过";
//    	}
//    	else if ("8".equals(type)) {
//    		ruleName = "警告";
//    	}
//    	else if ("9".equals(type)) {
//    		ruleName = "好评";
//    	}
//    	else if ("10".equals(type)) {
//    		ruleName = "差评";
//    	}
//    	else if ("11".equals(type)) {
//    		ruleName = "投诉";
//    	}
//    	return storeManageRuleMapper.selectRuleByRuleNameAndStoreId(storeId, ruleName);
//    }
    
}
