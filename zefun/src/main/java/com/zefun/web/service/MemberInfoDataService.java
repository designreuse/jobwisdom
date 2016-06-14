package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberComboDto;
import com.zefun.web.dto.MemberDto;
import com.zefun.web.dto.MemberSubAccountDto;
import com.zefun.web.dto.MoneyFlowDto;
import com.zefun.web.dto.OrderProjectPageDto;
import com.zefun.web.entity.DebtFlow;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.Page;
import com.zefun.web.mapper.DebtFlowMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberSubAccountMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;

/**
 * 会员信息业务逻辑类
* @author 张进军
* @date Aug 19, 2015 4:36:14 PM 
*/
@Service
public class MemberInfoDataService {
    /** 会员信息数据库操作对象 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    /**资金流水*/
    @Autowired
    private MoneyFlowMapper moneyFlowMapper;
    /** 订单明细*/
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    /** 会员套餐*/
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    /** 积分*/
    @Autowired
    private IntegralFlowMapper integralFlowMapper;
    /** 欠款*/
    @Autowired
    private DebtFlowMapper debtFlowMapper;
    /** 礼金*/
    @Autowired
    private GiftmoneyFlowMapper giftmoneyFlowMapper;
    /** 会员子账户信息操作对象 */
    @Autowired
    private MemberSubAccountMapper memberSubAccountMapper;
    /**
     * 根据会员信息标识查询会员信息及账户信息
    * @author 王大爷
    * @date 2015年9月12日 下午4:19:03
    * @param memberId 会员信息标识
    * @param storeId 门店标识
    * @return 会员信息及账户信息
     */
    public BaseDto selectByMemberDto(Integer memberId, Integer storeId){
        MemberDto memberDto = memberInfoMapper
                .selectByMemberResultMap(memberId);
        
        if (memberDto.getBirthday() == null) {
            memberDto.setBirthday("未设置");
        }
        String lastConsumeTime = DateUtil.daysBetween(memberDto.getMemberAccount().getLastConsumeTime(), DateUtil.getCurDate());
        if (lastConsumeTime.equals("暂无数据")){
            memberDto.getMemberAccount().setLastConsumeTime("暂无数据");
        }
        else {
            memberDto.getMemberAccount().setLastConsumeTime(lastConsumeTime+" 天前");
        }
        
        Page<MoneyFlowDto> pageMoneyFlowDto = selectPageForMoneyFlow(1, 5, memberId);
        
        Page<OrderProjectPageDto> pageOrderProjectPageDto = selectPageForOrderProject(1, 5, memberId);
        
        Page<OrderProjectPageDto> pageOrderGoodsPageDto = selectPageForOrderGoods(1, 5, memberId);
        
        Page<MemberComboDto> pageOrderComboPageDto = selectPageForOrderCombo(1, 5, memberId);
        
        Page<IntegralFlow> pageIntegralFlowDto = selectPageForIntegralFlow(1, 5, memberId);
        
        Page<GiftmoneyFlow> pageGiftmoneyFlowDto = selectPageForGiftmoneyFlow(1, 5, memberId);
        
        Page<DebtFlow> pageDebtFlowDto = selectPageForDebtFlow(1, 5, memberId);
        
        Map<String, Integer> selectMap = new HashMap<>();
        selectMap.put("accountId", memberId);
        selectMap.put("storeId", storeId);
        
        List<MemberSubAccountDto> subAccountList = memberSubAccountMapper.selectSubAccountListByAccountId(selectMap);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberDto", memberDto);
        map.put("pageMoneyFlowDto", pageMoneyFlowDto);
        map.put("pageOrderProjectPageDto", pageOrderProjectPageDto);
        map.put("pageOrderGoodsPageDto", pageOrderGoodsPageDto);
        map.put("pageOrderComboPageDto", pageOrderComboPageDto);
        map.put("pageIntegralFlowDto", pageIntegralFlowDto);
        map.put("pageGiftmoneyFlowDto", pageGiftmoneyFlowDto);
        map.put("pageDebtFlowDto", pageDebtFlowDto);
        map.put("subAccountList", subAccountList);
        
        /*ObjectMapper mapper=new ObjectMapper();
        try {
            String jsonString=mapper.writeValueAsString(map);
            System.out.print(jsonString);
        } 
        catch (JsonGenerationException e) {
            e.printStackTrace();
        } 
        catch (JsonMappingException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }*/
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }


    /**
     * 根据会员标识分页查询挂账流水
    * @author 高国藩
    * @date 2015年12月28日 下午9:25:27
    * @param pageNo     页码
    * @param pageSize   显示数
    * @param memberId   会员标识
    * @return           分页数据
     */
    private Page<DebtFlow> selectPageForDebtFlow(Integer pageNo,
            Integer pageSize, Integer memberId) {
        Page<DebtFlow> page = new Page<DebtFlow>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accountId", memberId);
        page.setParams(params);
        List<DebtFlow> list = debtFlowMapper.selectFlowListByAccountIdPage(page);
        page.setResults(list);
        return page;
    }


    /**
     * 根据会员标识分页查询资金流水
    * @author 王大爷
    * @date 2015年9月14日 下午4:59:57
    * @param pageNo 页数
    * @param pageSize 显示数
    * @param memberId 会员标识
    * @param type 分页类型
    * @return BaseDto
     */
    public BaseDto selectPage(Integer pageNo, Integer pageSize,
            Integer memberId, Integer type) {
        //资金流水
        if (type == 4) {
            Page<MoneyFlowDto> pageMoneyFlowDto = selectPageForMoneyFlow(pageNo, pageSize,
                    memberId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, pageMoneyFlowDto);
        }
        //套餐
        else if (type == 2) {
            Page<MemberComboDto> pageMemberComboDto = selectPageForOrderCombo(pageNo, pageSize,
                    memberId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, pageMemberComboDto);
        }
        //积分流水
        else if (type == 5) {
            Page<IntegralFlow> pageIntegralFlowDto = selectPageForIntegralFlow(pageNo, pageSize,
                    memberId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, pageIntegralFlowDto);
        }
        //礼金流水
        else if (type == 6) {
            Page<GiftmoneyFlow> pageGiftmoneyFlowDto = selectPageForGiftmoneyFlow(pageNo, pageSize,
                    memberId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, pageGiftmoneyFlowDto);
        }
        //欠款流水
        else if (type == 7) {
            Page<DebtFlow> pageDebtFlowDto = selectPageForDebtFlow(pageNo, pageSize,
                    memberId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, pageDebtFlowDto);
        }
        //项目
        else if (type == 1) {
            Page<OrderProjectPageDto> pageOrderProjectDto = selectPageForOrderProject(pageNo, pageSize,
                    memberId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, pageOrderProjectDto);
        }
        //商品
        else if (type == 3) {
            Page<OrderProjectPageDto> pageOrderGoodsDto = selectPageForOrderGoods(pageNo, pageSize,
                    memberId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, pageOrderGoodsDto);
        }
        return null;
    }

    /**
     * 流水分页查询
    * @author 王大爷
    * @date 2015年9月14日 下午5:01:04
    * @param pageNo 页数
    * @param pageSize 显示条数
    * @param memberId 会员标识
    * @return Page<MoneyFlow>
     */
    private Page<MoneyFlowDto> selectPageForMoneyFlow(Integer pageNo,
            Integer pageSize, Integer memberId) {
        Page<MoneyFlowDto> page = new Page<MoneyFlowDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        page.setParams(params);
        List<MoneyFlowDto> list = moneyFlowMapper.selectByPage(page);
        page.setResults(list);
        return page;
    }
    
    /**
     * 会员消费套餐分页
    * @author 王大爷
    * @date 2015年11月30日 下午3:58:23
    * @param pageNo 页数
    * @param pageSize 显示条数
    * @param memberId 会员标识
    * @return Page<OrderProjectPageDto>
     */
    private Page<MemberComboDto> selectPageForOrderCombo(Integer pageNo,
            Integer pageSize, Integer memberId) {
        Page<MemberComboDto> page = new Page<MemberComboDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        page.setParams(params);
        List<MemberComboDto> list = memberComboRecordMapper.selectComboListByMemberIdPage(page);
        page.setResults(list);
        return page;
    }
    
    /**
     * 会员积分分页
    * @author 王大爷
    * @date 2015年11月30日 下午3:58:23
    * @param pageNo 页数
    * @param pageSize 显示条数
    * @param memberId 会员标识
    * @return Page<OrderProjectPageDto>
     */
    private Page<IntegralFlow> selectPageForIntegralFlow(Integer pageNo,
            Integer pageSize, Integer memberId) {
        Page<IntegralFlow> page = new Page<IntegralFlow>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accountId", memberId);
        page.setParams(params);
        List<IntegralFlow> list = integralFlowMapper.selectFlowListByAccountIdPage(page);
        page.setResults(list);
        return page;
    }
    
    /**
     * 会员礼金分页
    * @author 王大爷
    * @date 2015年11月30日 下午3:58:23
    * @param pageNo 页数
    * @param pageSize 显示条数
    * @param memberId 会员标识
    * @return Page<OrderProjectPageDto>
     */
    private Page<GiftmoneyFlow> selectPageForGiftmoneyFlow(Integer pageNo,
            Integer pageSize, Integer memberId) {
        Page<GiftmoneyFlow> page = new Page<GiftmoneyFlow>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accountId", memberId);
        page.setParams(params);
        List<GiftmoneyFlow> list = giftmoneyFlowMapper.selectFlowListByAccountIdPage(page);
        page.setResults(list);
        return page;
    }
    
    /**
     * 会员消费项目分页
    * @author 王大爷
    * @date 2015年11月30日 下午3:58:23
    * @param pageNo 页数
    * @param pageSize 显示条数
    * @param memberId 会员标识
    * @return Page<OrderProjectPageDto>
     */
    private Page<OrderProjectPageDto> selectPageForOrderProject(Integer pageNo,
            Integer pageSize, Integer memberId) {
        Page<OrderProjectPageDto> page = new Page<OrderProjectPageDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        page.setParams(params);
        List<OrderProjectPageDto> list = orderDetailMapper.selectByOrderProjectPage(page);
//        List<OrderProjectPageDto> dtoList = processOrderProjectPageDto(list);
        page.setResults(list);
        return page;
    }
    
    /**
     * 会员消费商品分页
    * @author 王大爷
    * @date 2015年11月30日 下午6:06:01
    * @param pageNo 页数
    * @param pageSize 显示条数
    * @param memberId 会员标识
    * @return Page<OrderProjectPageDto>
     */
    private Page<OrderProjectPageDto> selectPageForOrderGoods(Integer pageNo,
            Integer pageSize, Integer memberId) {
        Page<OrderProjectPageDto> page = new Page<OrderProjectPageDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        page.setParams(params);
        List<OrderProjectPageDto> list = orderDetailMapper.selectByOrderGoodsPage(page);
//        List<OrderProjectPageDto> dtoList = processOrderProjectPageDto(list);
        page.setResults(list);
        return page;
    }
    
    /**
     * 组装OrderProjectPageDto
    * @author 王大爷
    * @date 2015年11月30日 下午4:32:39
    * @param list list
    * @return List<OrderProjectPageDto>
     */
    @SuppressWarnings("unused")
    @Deprecated
    private List<OrderProjectPageDto> processOrderProjectPageDto(List<OrderProjectPageDto> list) {
        List<OrderProjectPageDto> dtoList = new ArrayList<OrderProjectPageDto>();
        for (OrderProjectPageDto orderProjectPageDto : list) {
            if (orderProjectPageDto.getOffType() == 1) {
                String comboName = orderDetailMapper.selectComboNameByDetailId(orderProjectPageDto.getDetailId());
                orderProjectPageDto.setPrivilegeInfo(comboName);
                orderProjectPageDto.setPrivilegeMoney(orderProjectPageDto.getDiscountAmount());
            }
            else if (orderProjectPageDto.getOffType() == 2) {
                Map<String, Object> map = orderDetailMapper.selectCouponNameByDetailId(orderProjectPageDto.getDetailId());
                orderProjectPageDto.setPrivilegeInfo(map.get("couponName").toString());
                orderProjectPageDto.setPrivilegeMoney(new BigDecimal(map.get("couponPrice").toString()));
            }
            else if (orderProjectPageDto.getOffType() == 3) {
                orderProjectPageDto.setPrivilegeInfo("礼金抵扣");
                orderProjectPageDto.setPrivilegeMoney(orderProjectPageDto.getDiscountAmount().subtract(orderProjectPageDto.getRealPrice()));
            }
            else {
                orderProjectPageDto.setPrivilegeInfo("");
                orderProjectPageDto.setPrivilegeMoney(new BigDecimal(0));
            }
            dtoList.add(orderProjectPageDto);
        }
        return dtoList;
    }
    
    /**
     * 查询本店会员信息
    * @author 王大爷
    * @date 2016年1月5日 下午6:23:06
    * @param storeId 门店标识
    * @return BaseDto
     */
    public BaseDto selectStoreMemberInfo (Integer storeId) {
    	Map<String, Integer> mapHash = new HashMap<>();
    	mapHash.put("type", 1);
    	mapHash.put("storeId", storeId);
        List<Map<String, Object>> storeMemberList = memberInfoMapper.selectStoreNamePhone(mapHash);
        mapHash.put("type", 2);
        List<Map<String, Object>> enterpriseMemberList = memberInfoMapper.selectStoreNamePhone(mapHash);
        Map<String, Object> map = new HashMap<>();
        map.put("storeMemberList", storeMemberList);
        map.put("enterpriseMemberList", enterpriseMemberList);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }
    
}
    
