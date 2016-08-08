package com.zefun.web.service;

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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rewardId", vo.getRewardId());
        List<EmployeeRewardDto> results = employeeRewardMapper.selectCountRewardByGroupBy(map);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, results.get(0));
    }
    
    
    /**
     * 查询员工奖惩汇总
     * @param vo  员工奖惩vo
     * @param storeAccount  storeAccount
     * @param storeId  storeId
     * @return  ModelAndView
     */
    public ModelAndView findCountEmployeeRewardHome(EmployeeRewardVo vo, String storeAccount, Object storeId) {
        ModelAndView mav = new ModelAndView(View.EmployeeReward.HOME);

        List<StoreInfo> selectByStoreAccount = storeInfoMapper.selectByStoreAccount(storeAccount);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", selectByStoreAccount.get(0).getStoreId());
        if (storeId != null && storeId !=""){
            params.put("storeId", storeId);
            mav.addObject("storeId", storeId);
        }
        else {
            mav.addObject("storeId", 0);
        }
        
        params.put("time", vo.getTime());
        
        Page<EmployeeRewardDto> page = new Page<EmployeeRewardDto>();
        page.setParams(params);
        page.setPageNo(1);
        page.setPageSize(17);
        //全部员工
        List<EmployeeBaseDto> selectEmployeeListByStoreIdAll = employeeInfoMapper.selectEmployeeListByStoreIdAll(params);
        
        //全部奖罚数据分页 
        List<EmployeeRewardDto> list = employeeRewardMapper.selectCountRewardByPage(page);
        page.setResults(list);
        
        //全部奖罚数据 
        List<EmployeeRewardDto> results = employeeRewardMapper.selectCountRewardByGroupBy(params);
        
    	  //处理方式(1:奖励，2:惩罚)
        List<StoreManageRule> storeManageRule = storeManageRuleMapper.selectRuleListByAccountStoreId(params);
        params.put("processType1", 1);
        List<StoreManageRule> ruleList1 = storeManageRuleMapper.selectRuleListByAccountStoreId(params);
        params.put("processType1", 2);
        List<StoreManageRule> ruleList2 = storeManageRuleMapper.selectRuleListByAccountStoreId(params);
        StoreManageRule rule = new StoreManageRule();
        if (ruleList1.size() == 0) {
            ruleList1.add(rule);
        }
        if (ruleList2.size() == 0) {  
            ruleList2.add(rule);
        }
	    JSONArray jsonarray = classify(results, selectEmployeeListByStoreIdAll, ruleList1, ruleList2);
    
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
    public JSONArray classify(List<EmployeeRewardDto> employeeRewardDtoList, List<EmployeeBaseDto> resultsGroup, 
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
                List<EmployeeRewardDto> reward2 = collect.stream().filter(p2 -> p2.getIsReward().equals("2"))
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
                jsono.accumulate("name", em.getName());
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
    * @param type type
    * @return BaseDto
     */
    public BaseDto selectEmployeeByAccount(Integer storeId, Integer type) {
        List<EmployeeInfo> selectEmployeeList = employeeInfoMapper.selectEmployeeList(storeId);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        params.put("processType1", type);
        List<StoreManageRule> ruleList = storeManageRuleMapper.selectRuleListByAccountStoreId(params);
        params.put("emp", selectEmployeeList);
        params.put("rule", ruleList);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, params);
    }


    /**
     *  奖罚明细分页查询
    * @author 骆峰
    * @date 2016年8月6日 下午1:54:32
    * @param pageNo 页数  
    * @param staTime 开始时间
    * @param endTime 结束时间
    * @param storeId 门店标识
    * @param ruleName 奖罚名称
    * @param ruleType 类型
    * @param employee 员工
    * @param pageSize 一页多少条数据
    * @return BaseDto
     */
    public BaseDto selectRuleByPage(Integer pageNo, String staTime,
            String endTime, Integer storeId, String ruleName, Integer ruleType,
            Integer employee, Integer pageSize) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        params.put("ruleName", ruleName);
        params.put("ruleType", ruleType);
        params.put("employee", employee);
        params.put("time1", staTime);
        params.put("time2", endTime);

        Page<EmployeeRewardDto> page = new Page<EmployeeRewardDto>();
        page.setParams(params);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //全部奖罚数据
        List<EmployeeRewardDto> list = employeeRewardMapper.selectCountRewardByPage(page);
        page.setResults(list);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }


    /**
     *  奖罚管理条件查询
    * @author 骆峰
    * @date 2016年8月6日 下午3:19:28
    * @param storeId storeId
    * @param time time
    * @return BaseDto
     */
    public BaseDto changeRule(Integer storeId, String time) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        params.put("time", time);
        
        //全部奖罚数据 
        List<EmployeeRewardDto> results = employeeRewardMapper.selectCountRewardByGroupBy(params);
      
        List<EmployeeBaseDto> selectEmployeeListByStoreIdAll = employeeInfoMapper.selectEmployeeListByStoreIdAll(params);
        //处理方式(1:奖励，2:惩罚)
        params.put("processType1", 1);
        List<StoreManageRule> ruleList1 = storeManageRuleMapper.selectRuleListByAccountStoreId(params);
        params.put("processType1", 2);
        List<StoreManageRule> ruleList2 = storeManageRuleMapper.selectRuleListByAccountStoreId(params);

        StoreManageRule rule = new StoreManageRule();
        if (ruleList1.size() == 0) {
            ruleList1.add(rule);
        }
        if (ruleList2.size() == 0) {  
            ruleList2.add(rule);
        }
        JSONArray jsonarray = classify(results, selectEmployeeListByStoreIdAll, ruleList1, ruleList2);
        
        params.put("jsonarray", jsonarray);
        params.put("ruleList1", ruleList1);
        params.put("ruleList2", ruleList2);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, params);
    }
    

 
    /**
     * 删除奖惩记录
    * @author DavidLiang
    * @date 2016年1月14日 下午8:47:24
    * @param rewardId  奖惩id
    * @return  是否成功
     */
    public boolean deleteEmployeeReward(int rewardId) {
    	return employeeRewardMapper.deleteByPrimaryKey(rewardId) == 1 ? true : false;
    }


    /**
     *  修改
    * @author 骆峰
    * @date 2016年8月8日 上午9:43:32
    * @param vo vo
    * @return BaseDto
     */
    public BaseDto updateEmployeeReward(EmployeeReward vo) {
        vo.setModifytime(DateUtil.getCurDate());
        int ruleId = Integer.parseInt(vo.getType());
        StoreManageRule storeManageRule = storeManageRuleMapper.selectByPrimaryKey(ruleId);
        vo.setNumber(Double.parseDouble(storeManageRule.getProcessMoney().toString()));
        vo.setType(storeManageRule.getRuleName());
        employeeRewardMapper.updateByPrimaryKeySelective(vo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rewardId", vo.getRewardId());
        List<EmployeeRewardDto> results = employeeRewardMapper.selectCountRewardByGroupBy(map);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, results.get(0));
    }

    
}
