package com.zefun.web.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.EntityJsonConverter;
import com.zefun.common.utils.ExcleUtils;
import com.zefun.common.utils.HttpClientUtil;
import com.zefun.common.utils.MessageUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberGroupDto;
import com.zefun.web.dto.MemberInfoDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.MemberOrderDto;
import com.zefun.web.dto.MemberSubAccountDto;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.ComboProject;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.DebtFlow;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.GoodsDiscount;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberComboProject;
import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberErrorBk;
import com.zefun.web.entity.MemberErrorGi;
import com.zefun.web.entity.MemberErrorGy;
import com.zefun.web.entity.MemberErrorHc;
import com.zefun.web.entity.MemberErrorHt;
import com.zefun.web.entity.MemberErrorHtmy;
import com.zefun.web.entity.MemberErrorLd;
import com.zefun.web.entity.MemberErrorMb;
import com.zefun.web.entity.MemberErrorSc;
import com.zefun.web.entity.MemberErrorXsl;
import com.zefun.web.entity.MemberErrorZy;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberLevelDiscount;
import com.zefun.web.entity.MemberScreening;
import com.zefun.web.entity.MemberSubAccount;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ProjectDiscount;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.RumorsCourse;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.TempTableSc;
import com.zefun.web.entity.TempTableZy;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.DebtFlowMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.GoodsDiscountMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberComboProjectMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberErrorBkMapper;
import com.zefun.web.mapper.MemberErrorGiMapper;
import com.zefun.web.mapper.MemberErrorGyMapper;
import com.zefun.web.mapper.MemberErrorHcMapper;
import com.zefun.web.mapper.MemberErrorHtMapper;
import com.zefun.web.mapper.MemberErrorHtmyMapper;
import com.zefun.web.mapper.MemberErrorLdMapper;
import com.zefun.web.mapper.MemberErrorMbMapper;
import com.zefun.web.mapper.MemberErrorScMapper;
import com.zefun.web.mapper.MemberErrorXslMapper;
import com.zefun.web.mapper.MemberErrorZyMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.MemberSubAccountMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectDiscountMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.RumorsCourseMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.TempTableZyMapper;
import com.zefun.web.mapper.WechatMemberMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 会员信息业务逻辑类
* @author 张进军
* @date Aug 19, 2015 4:36:14 PM 
*/
@Service
@Transactional
@SuppressWarnings("unused")
public class MemberInfoService {
    /** 会员信息数据库操作对象 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    /** redis操作类 */
    @Autowired
    private RedisService redisService;
    /**筛选器*/
    @Autowired
    private MemberScreeningMapper memberScreeningMapper;
    /**会员等级*/
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    /**会员账务表*/
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    /**会员数据临时表数据-左右*/
    @Autowired
    private TempTableZyMapper tempTableXyMapper;
    /**会员数据临时表数据-左右*/
    @Autowired
    private MemberErrorZyMapper memberErrorZyMapper;
    /**会员数据临时表数据-盛传*/
    @Autowired
    private MemberErrorScMapper memberErrorScMapper;
    /**门店设置*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;
    /**处理云浩错误数据的导入*/
    @Autowired
    private MemberErrorHtMapper memberErrorHtMapper;
    /**处理华佗错误数据的导入*/
    @Autowired
    private MemberErrorHtmyMapper memberErrorHtmyMapper;
    /**处理博卡错误数据的导入*/
    @Autowired
    private MemberErrorBkMapper memberErrorBkMapper;
    /**查询门店信息*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    /**余额流水表*/
    @Autowired
    private MoneyFlowMapper moneyFlowMapper;
    /**余额流水表*/
    @Autowired
    private IntegralFlowMapper integralFlowMapper;
    /**礼金流水*/
    @Autowired
    private GiftmoneyDetailMapper giftmoneyDetailMapper;
    /**礼金流水*/
    @Autowired
    private GiftmoneyFlowMapper giftmoneyFlowMapper;
    /**会员优惠券操作对象*/
    @Autowired
    private MemberCouponMapper memberCouponMapper;
    /**项目*/
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    /**项目*/
    @Autowired
    private MemberComboProjectMapper memberComboProjectMapper;
    /**项目*/
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    /**疗程*/
    @Autowired
    private ComboInfoMapper comboInfoMapper;
    /**疗程查询类*/
    @Autowired
    private ComboInfoService comboInfoService;
    /**订单查询类*/
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    /**欠款还款流水*/
    @Autowired
    private DebtFlowMapper debtFlowMapper;
    /**微信用户表*/
    @Autowired
    private WechatMemberMapper wechatMemberMapper;
    /**盛传疗程卡导入*/
    @Autowired
    private RumorsCourseMapper rumorsCourseMapper;
    /**耕宇会员数据导入导入*/
    @Autowired
    private MemberErrorGyMapper memberErrorGyMapper;
    /**蓝蝶会员数据导入导入*/
    @Autowired
    private MemberErrorLdMapper memberErrorLdMapper;
    /**共赢会员数据导入导入*/
    @Autowired
    private MemberErrorGiMapper memberErrorGiMapper;
    /**模板会员数据导入导入*/
    @Autowired
    private MemberErrorMbMapper memberErrorMbMapper;
    /**西沙龙会员数据导入导入*/
    @Autowired
    private MemberErrorXslMapper memberErrorXslMapper;
    /**华彩会员数据导入导入*/
    @Autowired
    private MemberErrorHcMapper memberErrorHcMapper;
    /**会员导入自相*/
    @Autowired
    private MemberSubAccountMapper subAccountMapper;
    /**会员等级*/
    @Autowired
    private MemberLevelService memberLevelService;
    /**优惠券*/
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    /** 订单明细*/
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    /** 项目提成业绩*/
    @Autowired
    private ProjectDiscountMapper projectDiscountMapper;
    /** 商品提成业绩*/
    @Autowired
    private GoodsDiscountMapper goodsDiscountMapper;
    /** 商品信息*/
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    
    /**日志记录对象*/
    private static Logger log = Logger.getLogger(MemberLevelService.class);

    /**
     * 根据会员标识查询会员基本信息
    * @author 张进军
    * @date Aug 19, 2015 5:42:21 PM
    * @param memberId   会员标识
    * @param hasConsume	是否包含消费信息
    * @return           会员基本信息
     */
    public MemberBaseDto getMemberBaseInfo(Integer memberId, boolean hasConsume) {
        if (memberId == null) {
            return null;
        }
        String memberBaseInfoJson = redisService.hget(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberId);
        MemberBaseDto memberBaseInfo = null;
        // 首先从缓存中获取，如果缓存中不存在，则从数据库查出并缓存
        if (StringUtils.isBlank(memberBaseInfoJson)) {
            memberBaseInfo = memberInfoMapper.selectMemberBaseInfo(memberId);
            redisService.hset(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberId, EntityJsonConverter.entity2Json(memberBaseInfo));
        }
        // 缓存中存在则直接转换为对象
        else {
            memberBaseInfo = EntityJsonConverter.json2Entity(memberBaseInfoJson, MemberBaseDto.class);
        }
        if (hasConsume) {
        	memberBaseInfo = packageMemberBaseDtoInfoBaseDto(memberBaseInfo);
        }
        return memberBaseInfo;
    }


    /**
     * 检查是否有存在该手机号的会员
    * @author 张进军
    * @date Aug 19, 2015 7:54:03 PM
    * @param phone      手机号码
    * @param storeAccount 门店标识
    * @return           true:存在,false:不存在
     */
    public boolean isExists(String phone, String storeAccount) {
        Integer result = selectMemberIdByPhone(phone, storeAccount);
        if (result == null) {
            return false;
        }
        return true;
    }

    /**
     * 根据手机号码查询会员标识
    * @author 老高
    * @date Aug 19, 2015 7:49:56 PM
    * @param phone  手机号码
    * @param storeAccount 门店标识
    * @return       如果存在结果返回会员标识，不存在返回null
     */
    public Integer selectMemberIdByPhone(String phone, String storeAccount) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setPhone(phone);
//        memberInfo.setStoreId(storeId);
        memberInfo.setStoreAccount(storeAccount);
        return memberInfoMapper.selectMemberIdByPhone(memberInfo);
    }

    /**
     * 根据手机号码查询会员基本信息
    * @author 王大爷
    * @date Aug 19, 2015 7:49:56 PM
    * @param phone  手机号码
    * @param storeId 门店标识
    * @return       如果存在结果返回会员基本信息，不存在返回null
     */
    public MemberBaseDto selectMemberInfoByPhone(String phone,
            Integer storeId) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setPhone(phone);
        memberInfo.setStoreId(storeId);
        MemberBaseDto dto = memberInfoMapper.selectMemberInfoByPhone(memberInfo);
        
        if (dto != null) {
        	Map<String, Integer> map = new HashMap<>();
        	map.put("accountId", dto.getMemberId());
        	map.put("storeId", storeId);
            List<MemberSubAccountDto> subAccountDtoList = subAccountMapper.selectSubAccountListByAccountId(map);
            dto.setSubAccountDtoList(subAccountDtoList);
        }
        
        dto = packageMemberBaseDtoInfoBaseDto(dto);
        return dto;
    }

    /**
     * 组装会服务项目及步骤
    * @author 王大爷
    * @date 2016年1月9日 下午5:30:41
    * @param dto dto
    * @return MemberBaseDto
     */
    public MemberBaseDto packageMemberBaseDtoInfoBaseDto(MemberBaseDto dto) {
    	if (dto == null) {
    		return null;
    	}
    	
        List<MemberOrderDto> orderDtoList = orderInfoMapper.selectOrderListByMemberId(dto.getMemberId());
        
        String daysBetween = DateUtil.daysBetween(dto.getLastConsumeTime(), DateUtil.getCurTime());
        dto.setLastDayNumber(daysBetween);
        
        String lastProjectName = "暂无数据";
        
        List<Map<String, Object>> projectStepList = new ArrayList<Map<String, Object>>();
        
        /*if (orderDtoList != null && !orderDtoList.isEmpty()) {
            
            for (MemberOrderDto memberOrderDto : orderDtoList) {
                //过滤正在进行中的订单 
                if (memberOrderDto.getOrderStatus() == 1 || memberOrderDto.getOrderStatus() == 2 || memberOrderDto.getOrderStatus() == 5) {
                    continue;
                }
                
                List<OrderDetailDto> detailList = memberOrderDto.getDetailList();
                
                BigDecimal projectPrice = new BigDecimal(0);
                
                OrderDetailDto chooseOrderDetailDto = null;
                for (OrderDetailDto orderDetailDto : detailList) {
                    if (orderDetailDto.getOrderType() == 1 && orderDetailDto.getIsUpdate() == 0) {
                        if (orderDetailDto.getProjectPrice().compareTo(projectPrice) == 1) {
                            chooseOrderDetailDto = orderDetailDto;
                        }
                    }
                }
                
                if (chooseOrderDetailDto != null) {
                    lastProjectName = chooseOrderDetailDto.getProjectName();
                    List<OrderDetailStepDto> stepDtoList = chooseOrderDetailDto.getStepList();
                    for (OrderDetailStepDto orderDetailStepDto : stepDtoList) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("shiftMahjongName", orderDetailStepDto.getShiftMahjongName());
                        EmployeeInfo employeeInfo = orderDetailStepDto.getEmployeeInfo();
                        if (employeeInfo == null) {
                        	map.put("employeeName", "无服务人员");
                            map.put("employeeCode", "");
                        }
                        else {
                        	map.put("employeeName", orderDetailStepDto.getEmployeeInfo().getName());
                            map.put("employeeCode", orderDetailStepDto.getEmployeeInfo().getEmployeeCode());
                            map.put("employeeAssign", orderDetailStepDto.getIsAssign());
                        }
                        projectStepList.add(map);
                    }
                    dto.setLastProjectName(lastProjectName);
                    dto.setProjectStepList(projectStepList);
                    return dto;
                }
            }
        }*/
        
        dto.setLastProjectName(lastProjectName);
        /*dto.setProjectStepList(projectStepList);*/
        
        return dto;
    }
    
    /**
     * 查询所有会员
    * @author 高国藩
    * @date 2015年9月8日 下午6:01:15
    * @param storeId  门店标示
    * @return         跳转
     */
    public ModelAndView selectMemberByStoreId(Integer storeId) {
        Page<MemberInfoDto> page = new Page<MemberInfoDto>();
        page.setPageNo(1);
        page.setPageSize(11);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        page.setParams(map);
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberInfosByPage(page);
        page.setResults(memberInfoDtos);
        List<MemberLevelDto> levels = memberLevelMapper.selectByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.MemberInfo.MEMBER_LIST_VIEW);
        view.addObject("page", page);
        view.addObject("levels", levels);
        MemberInfoDto dto = memberInfoMapper.selectStoreMemberAmount(storeId);
        List<MemberInfoDto> infoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        view.addObject("storeMember", dto);
        view.addObject("infoDtos", JSONArray.fromObject(infoDtos).toString().trim());
        return view;
    }

    /**
     * 新增一个筛选器
    * @author 高国藩
    * @date 2015年9月8日 下午8:14:18
    * @param storeId         门店
    * @param memberScreening 实体
    * @param branchOffice    总店查询分店
    * @return                状态
     */
    public BaseDto addMemberScreening(Integer storeId, MemberScreening memberScreening, String branchOffice) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = sdf.format(new Date());
        memberScreening.setCreateTime(createTime);
        memberScreening.setStoreId(storeId);
        if (branchOffice!=null&&!branchOffice.equals("null")){
            memberScreening.setBranchStoreIds(branchOffice);
        }
        else if (branchOffice!=null&&branchOffice.equals("null")){
            List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
            List<Integer> stores = new ArrayList<>();
            if (storeList!=null&&storeList.size()>0){
                for (int i = 0; i < storeList.size(); i++) {
                    stores.add(storeList.get(i).getStoreId());
                }
            }
            memberScreening.setBranchStoreIds(stores.toString().substring(1, stores.toString().length()-1));
        }
        else if (branchOffice==null){
            //这里是空的话呢,就说明是单点的分组信息
            memberScreening.setBranchStoreIds(null);
        }

        int ok = memberScreeningMapper.insertSelective(memberScreening);
        if (ok > 0) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                    App.System.API_RESULT_MSG_FOR_SUCCEES);
        } 
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL,
                    App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 进入会员分组页面
    * @author 高国藩
    * @date 2015年9月11日 上午10:13:35
    * @param storeId   门店信息
    * @return          跳转页面
     */
    public ModelAndView viewCensusList(Integer storeId) {
        StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
        if (storeInfo.getStoreType()!=2){
            List<ScreeningDto> dtos = memberScreeningMapper.selectDtosByStoreId(storeId);
            List<MemberGroupDto> groupDtos = memberInfoMapper.selectGroupDtos(storeId);
            for (int i = 0; i < dtos.size(); i++) {
                MemberGroupDto groupDto = groupDtos.get(i);
                groupDto.setGroupCondition(dtos.get(i).toString());
                List<Integer> memberIdList = groupDto.getMemberIds();
                groupDto.setMemberCount(groupDto.getMemberIds().size()+"");
                // 根据当前时间判断该月份新增的人数
                Map<String, Object> dateMap = new HashMap<String, Object>();
                dateMap.put("startDate", MessageUtil.getMinMonthDate());
                dateMap.put("stopDate", MessageUtil.getMaxMonthDate());
                if (memberIdList.size() <= 0) {
                    memberIdList.add(0);
                }
                dateMap.put("memberList", memberIdList);
                Integer memberuNewNum = memberInfoMapper.selectMemberCountByDate(dateMap);
                groupDto.setNewMember(memberuNewNum.toString());
            }
            ModelAndView view = new ModelAndView(View.MemberInfo.GROUP_LIST_VIEW);
            view.addObject("groupDtos", groupDtos);
            view.addObject("type", 1);
            return view;
        }
        else {
            List<ScreeningDto> dtos = memberScreeningMapper.selectDtosByStoreId(storeId);
            List<MemberGroupDto> groupDtos = memberInfoMapper.selectGroupDtos(storeId);
            for (int i = 0; i < dtos.size(); i++) {
                MemberGroupDto groupDto = groupDtos.get(i);
                groupDto.setGroupCondition(dtos.get(i).toString());
                groupDto.setMemberCount(groupDto.getMemberIds().size()+"");
            }
            ModelAndView view = new ModelAndView(View.MemberInfo.GROUP_LIST_VIEW);
            view.addObject("groupDtos", groupDtos);
            view.addObject("type", 2);
            return view;
        }
        
    }

    /**
     * 根据选择器来查询会员数据
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param groupId 筛选器
    * @param storeId 门店
    * @param page 分页
    * @return 数据信息
     */
    public BaseDto viewListMemberByScreen(Integer storeId, Integer groupId, Page<MemberInfoDto> page) {
        ScreeningDto dto = memberScreeningMapper.selectByDto(groupId);
        return listMemberInfosByTerm(dto, page, null);
    }

    /**
     * 通过预设的条件查询会员数据
    * @author 高国藩
    * @date 2015年9月12日 上午9:54:15
    * @param screeningDto 查询条件
    * @param page 分页
    * @param branchOffice 分店信息
    * @return 会员数据
     */
    public BaseDto listMemberInfosByTerm(ScreeningDto screeningDto, Page<MemberInfoDto> page, String branchOffice) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (branchOffice!=null&&!"null".equals(branchOffice)){
            List<StoreInfo> storeList = new ArrayList<>();
            for (int i = 0; i < branchOffice.split(",").length; i++) {
                StoreInfo info = new StoreInfo();
                info.setStoreId(Integer.parseInt(branchOffice.split(",")[i]));
                storeList.add(info);
            }
            params.put("storeList", storeList);
            screeningDto.setStoreId(null);
        }
        else if (branchOffice!=null&&"null".equals(branchOffice)){
            List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(screeningDto.getStoreId());
            if (storeList!=null&&storeList.size()>0){
                params.put("storeList", storeList);
                screeningDto.setStoreId(null);
            }
        }
        else if (branchOffice==null&&screeningDto.getBranchStoreIds()!=null){
            //如果screeningDto中的branchStoreIds不是空,那么说明点击分组中的查询进行查询的
            screeningDto.setStoreId(null);
        }
        else if (branchOffice==null&&screeningDto.getBranchStoreIds()==null){
            //这里什么都不做代表的就不是连锁店
        }
        params.put("dto", screeningDto);
        page.setParams(params);
        List<MemberInfoDto> ls = null; 
        if (screeningDto.getStatus() != null && screeningDto.getStatus() == 1){
            ls = memberInfoMapper.selectHasDeletedByPageParams(page);
        }
        else {
            ls = memberInfoMapper.selectByPageParams(page);
        }
        page.setResults(ls);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }

    /**
     * 删除会员分组
    * @author 高国藩
    * @date 2015年9月12日 下午2:35:22
    * @param groupId 分组标示
    * @return 删除状态
     */
    public BaseDto deleteMemberCensus(Integer groupId) {
        int ok = memberScreeningMapper.deleteByPrimaryKey(groupId);
        if (ok > 0) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                    App.System.API_RESULT_MSG_FOR_SUCCEES);
        } 
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL,
                    App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 通过手机或者姓名进行查询会员数据
    * @author 高国藩
    * @date 2015年10月9日 下午8:23:52
    * @param storeId 门店
    * @param sex 性别
    * @param levelId 会员等级
    * @param content 查询条件
    * @param page 分页
    * @return 返回数据
     */
    public BaseDto listMemberInfosByContent(Integer storeId, String content,
            Page<MemberInfoDto> page, String sex, Integer levelId) {
//        List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
        Map<String, Object> params = new HashMap<String, Object>();
//        if (storeList!=null&&storeList.size()>0){
//            params.put("storeList", storeList);
//        }
//        else {
//            params.put("storeId", storeId);
//        }
        params.put("storeId", storeId);
        params.put("content", content);
        params.put("sex", sex);
        params.put("levelId", levelId);
        page.setParams(params);
        List<MemberInfoDto> ls = memberInfoMapper.selectByPageOrderNameOrPhone(page);
        page.setResults(ls);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    
    /**
     * 赠送积分给会员
    * @author 张进军
    * @date Jan 5, 2016 8:15:47 PM
    * @param memberId	会员标识
    * @param integral	积分数量
    * @param flowType   流水类型(1、支出，2、收入)
    * @param desc		赠送说明
    * @param orderId    关联订单标识
     */
    public void changeIntegralToMember(int memberId, int integral, int flowType, String desc, Integer orderId){
    	changeIntegralToMember(memberId, integral, flowType, desc, orderId, null);
    }
    
    
    /**
     * 
    * @author 张进军
    * @date Mar 4, 2016 11:09:16 AM
    * @param memberId   会员标识
    * @param integral   积分数量
    * @param flowType   流水类型(1、支出，2、收入)
    * @param desc       赠送说明
    * @param orderId    关联订单标识
    * @param transactionId  交易标识
     */
    public void changeIntegralToMember(int memberId, int integral, int flowType, String desc, Integer orderId, String transactionId){
        //更新会员积分余额
        Map<String, Object> map = new HashMap<>();
        if (flowType == 1) {
            map.put("memberIntegral", -integral);
        }
        else {
            map.put("memberIntegral", integral);
        }
        map.put("accountId", memberId);
        memberAccountMapper.updateMemberCashier(map);
        wipeCache(memberId);
        
        MemberBaseDto memberInfo = getMemberBaseInfo(memberId, false);
        
        IntegralFlow integralFlow = new IntegralFlow();
        integralFlow.setAccountId(memberId);
        integralFlow.setFlowAmount(integral);
        integralFlow.setBalanceAmount(memberInfo.getBalanceIntegral());
        integralFlow.setBusinessType(desc);
        integralFlow.setBusinessDesc(desc);
        integralFlow.setFlowType(flowType);
        integralFlow.setFlowTime(DateUtil.getCurTime());
        integralFlow.setOrderId(orderId);
        integralFlow.setTransactionId(transactionId);
        integralFlowMapper.insert(integralFlow);
    }
    
    
    /**
     * 赠送优惠券给会员
    * @author 张进军
    * @date Jan 5, 2016 8:15:47 PM
    * @param memberId	会员标识
    * @param couponId	优惠券标识
     */
    @Transactional
    public void presentCouponToMember(int memberId, int couponId){
        CouponInfo couponInfo = couponInfoMapper.selectByPrimaryKey(couponId);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(couponInfo.getCouponStartTime()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stopTime = sdf.format(calendar.getTime()).toString();
        MemberCoupon memberCoupon = new MemberCoupon(couponId, memberId, stopTime, 0);
        memberCouponMapper.insert(memberCoupon);
        couponInfo.setHasSendNum(couponInfo.getHasSendNum()+1);
        couponInfoMapper.updateByPrimaryKeySelective(couponInfo);
    }
    
    
    /**
     * 赠送礼金给会员
    * @author 张进军
    * @date Jan 5, 2016 8:22:49 PM
    * @param memberId	会员标识
    * @param money	赠送金额
    * @param desc	赠送说明
     */
    public void presentGiftmoneyToMember(int memberId, BigDecimal money, String desc){
    	if (money.intValue() < 1) {
    		return;
    	}
    	
        //增加礼金余额
        Map<String, Object> giftParams = new HashMap<String, Object>(5);
        giftParams.put("accountId", memberId);
        giftParams.put("totalGiftmoneyAmount", money);
        giftParams.put("balanceGiftmoneyAmount", money);
        memberAccountMapper.updateGiftmoney(giftParams);
        
        //增加礼金明细
        GiftmoneyDetail giftmoneyDetail = new GiftmoneyDetail();
        giftmoneyDetail.setAccountId(memberId);
        giftmoneyDetail.setTotalAmount(money);
        giftmoneyDetail.setNowMoney(money);
        giftmoneyDetail.setResidueNowMoney(money);
        giftmoneyDetail.setPartNumber(0);
        giftmoneyDetail.setPartType(1);
        giftmoneyDetail.setIsPresent(1);
        giftmoneyDetail.setStartDate(DateUtil.getCurDate());
        giftmoneyDetail.setEndDate("永久");
        giftmoneyDetail.setCreateTime(DateUtil.getCurTime());
        giftmoneyDetail.setIsDeleted(0);
        giftmoneyDetailMapper.insert(giftmoneyDetail);
        
        //增加礼金流水
        GiftmoneyFlow giftmoneyFlow = new GiftmoneyFlow();
        giftmoneyFlow.setAccountId(memberId);
        giftmoneyFlow.setFlowType(2);
        giftmoneyFlow.setFlowAmount(money);
        giftmoneyFlow.setFlowTime(DateUtil.getCurTime());
        giftmoneyFlow.setBusinessType(desc);
        giftmoneyFlow.setBusinessDesc(desc);
        giftmoneyFlow.setIsDeleted(0);
        giftmoneyFlowMapper.insert(giftmoneyFlow);
        
        wipeCache(memberId);
    }
    
    
    /**
     * 清除redis中的缓存信息
    * @param memberId   会员标识
    * @author 张进军
    * @date Nov 21, 2015 5:05:48 PM
     */
    public void wipeCache(int memberId){
        //更新会员缓存信息
        redisService.hdel(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberId);
    }
    
    /**
     * 同步会员的当前等级,级当前会员订单折扣价格
    * @author 张进军
    * @date Mar 21, 2016 12:03:25 PM
    * @param memberId   会员标识
     */
    public void syncLevelId(Integer memberId) {
        List<MemberSubAccount> accountDtos = subAccountMapper.selectListByAccountId(memberId);
        BigDecimal balanceAmount = accountDtos.get(0).getBalanceAmount();
        int levelId = accountDtos.get(0).getLevelId();
        for (int i = 1; i < accountDtos.size(); i++) {
        	MemberSubAccount subAccount = accountDtos.get(i);
            if (balanceAmount.compareTo(subAccount.getBalanceAmount()) < 1) {
                balanceAmount = subAccount.getBalanceAmount();
                levelId = subAccount.getLevelId();
            }
        }
        
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        memberInfo.setLevelId(levelId);
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        
        //查询会员所有的订单
        List<Map<String, Object>> mapList = orderDetailMapper.selectIsNotOverOrderDetail(memberId);
        
        for (Map<String, Object> membermap : mapList) {
			Integer detailId = Integer.valueOf(membermap.get("detailId").toString());
			Integer projectId = Integer.valueOf(membermap.get("projectId").toString());
	
			
			
			Integer storeId = Integer.valueOf(membermap.get("storeId").toString());
			Integer orderType = Integer.valueOf(membermap.get("orderType").toString());
			
			OrderDetail orderDetail = new OrderDetail();
	        orderDetail.setDetailId(detailId);
	        
			//项目
	        if (orderType == 1) {
	            ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);

	            BigDecimal discountAmount = projectInfo.getProjectPrice();
	            BigDecimal rate = new BigDecimal(100);
	            orderDetail.setProjectPrice(projectInfo.getProjectPrice());

                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("levelId", levelId);
                map.put("projectId", projectId);
                ProjectDiscount obj = projectDiscountMapper.selectDiscountPorjectIdAndLevelId(map);
                
                //该项目对应该会员的会员等级不存在特定价格
                if (obj == null) {
                	Map<String, Integer> memberMap = new HashMap<>();
                	memberMap.put("storeId", storeId);
                	memberMap.put("levelId", levelId);
                    //计算会员折扣价
                    MemberLevelDto memberLevel = memberLevelMapper.selectByEnterprise(memberMap);
                    discountAmount = projectInfo.getProjectPrice().multiply(new BigDecimal(memberLevel.getProjectDiscount()).divide(rate));
                }
                //该项目对应该会员的会员等级存在特定价格
                else {
                    discountAmount = obj.getDiscountAmount();
                }
	            
	            orderDetail.setDiscountAmount(discountAmount);
	        }
	        
	        //商品
	        else if (orderType == 2) {
	            GoodsInfoDto goodsInfo = goodsInfoMapper.selectByPrimaryKey(projectId);
	            //添加明细部门选择
	            orderDetail.setDeptId(goodsInfo.getDeptId());
	            BigDecimal discountAmount = goodsInfo.getGoodsPrice();
	            BigDecimal rate = new BigDecimal(100);
	            
	            orderDetail.setProjectPrice(goodsInfo.getGoodsPrice());
	            
	            //计算折扣价
	            if (memberId != null) {
	                Map<String, Integer> map = new HashMap<String, Integer>();
	                map.put("levelId", levelId);
	                map.put("goodsId", projectId);
	                GoodsDiscount obj = goodsDiscountMapper.selectDiscountGoodsIdAndLevelId(map);
	                
	                //该商品对应该会员的会员等级不存在特定价格
	                if (obj == null) {
	                	Map<String, Integer> memberMap = new HashMap<>();
	                	memberMap.put("storeId", storeId);
	                	memberMap.put("levelId", levelId);
	                    //计算会员折扣价
	                    MemberLevelDto memberLevel = memberLevelMapper.selectByEnterprise(memberMap);
	                    discountAmount = goodsInfo.getGoodsPrice().multiply(new BigDecimal(memberLevel.getGoodsDiscount()).divide(rate));
	                }
	                //该商品对应该会员的会员等级存在特定价格
	                else {
	                    discountAmount = obj.getDiscountAmount();
	                }
	            } 
	            
	            orderDetail.setDiscountAmount(discountAmount);
	        }
	        
	        orderDetailMapper.updateByPrimaryKey(orderDetail);
		}
        
        wipeCache(memberId);
    }

    /**
     * 盛传数据格式导入会员数据
    * @author 高国藩
    * @date 2015年10月19日 上午9:37:26
    * @param file           文件
    * @param file2          疗程卡
    * @param storeId        门店
    * @param storeAccount    企业代号
    * @param storeName      原服务商名称
    * @param lastOperatorId 最后操作人员ID
    * @param response       输出流 
    * @return BaseDto
     * @throws Exception 
     */
    @Transactional
    public BaseDto importExcleSc(MultipartFile file, MultipartFile file2, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response, String storeName) throws Exception {
        updateStoreSet(storeId, storeName);
        importRumorsCourse(file2, storeId, lastOperatorId);
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return null;
        }
        boolean isE2007 = false;
        if (name.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = file.getInputStream();
        Workbook wb = null;
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        } 
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        // 会员等级和名称对应,方便取值
        Map<String, Integer> levelMap = packLevelMap(storeId);
        // 将其中的疗程的行数保存下载,用于生成error数据,提供查询
        List<Integer> rowNums = new ArrayList<>();
        List<TempTableSc> tableScs = new ArrayList<>();
        a: for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                MemberInfo memberInfo = new MemberInfo();
                MemberAccount memberAccount = new MemberAccount();
                TempTableSc tableSc = new TempTableSc();
                b: for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    try {
                        Cell cell = xssfRow.getCell(cellNum);
                        String str = ExcleUtils.changeCellToString(cell);
                        // 先封装一层会员数据
                        if (cellNum == 0) {
                            if (hasStr.contains(str)) {
                                rowNums.add(rowNum);
                                log.info(str + " 已存在该手机号码");
                                continue a;
                            } 
                            else {
                                Pattern p1 = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");  
                                Matcher m = p1.matcher(str); 
                                if (m.matches()){
                                    hasStr.add(str);
                                    tableSc.setPhone(str);
                                    memberInfo.setPhone(str);
                                }
                                else {
                                    rowNums.add(rowNum);
                                    log.info(str +" 该手机号码不合法");
                                    continue a;
                                }
                            }
                        }
                        if (cellNum == 1) {
                            tableSc.setName(str);
                            memberInfo.setName(str);
                        }
                        if (cellNum == 2) {
                            tableSc.setSex(str);
                            memberInfo.setSex(str);
                        }
                        if (cellNum == 6) {
                            tableSc.setLevelName(str);
                        }
                        tableSc.setStoreId(storeId);
                        tableSc.setCreateTime(DateUtil.getCurDate());
                        
                        memberInfo.setStoreId(storeId);
                        memberInfo.setCreateTime(DateUtil.getCurDate());
                        memberInfo.setIsDeleted(0);
                        memberInfo.setLastOperatorId(lastOperatorId);
                        //再封装一层会员消费数据
                        if (cellNum == 4) {
                            tableSc.setCreateTime(str);
                            memberAccount.setCreateTime(str);
                        }
                        if (cellNum == 9) {
                            tableSc.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                        }
                        if (cellNum == 10) {
                            tableSc.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                        }
                        if (cellNum == 11) {
                            if (str.indexOf("疗程")!=-1){
                                log.info(" 疗程卡数据,暂不存入系统中 ");
                                continue a;
                            }
                            if (str.indexOf("元")!=-1){
                                str = str.substring(0, str.length()-1);
                            }
                            tableSc.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                        }
                        if (cellNum == 15) {
                            tableSc.setBalanceIntegral(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setBalanceIntegral(Integer.parseInt(ExcleUtils.changeValue(str)));
                        }
                        if (cellNum == 16) {
                            tableSc.setLastConsumeTime(str);
                            memberAccount.setLastConsumeTime(str);
                        }
                        if (cellNum == 16) {
                            tableSc.setLastConsumeTime(str);
                            memberAccount.setLastConsumeTime(str);
                        }
                        if (cellNum == 17) {
                            tableSc.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                        }
                        memberAccount.setLastOperatorId(lastOperatorId);
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                        rowNums.add(rowNum);
                        log.info(" 疗程卡数据,存入异常数据中 ");
                        continue a;
                    }
                }
                memberInfos.add(memberInfo);
                accounts.add(memberAccount);
                tableScs.add(tableSc);
            }
        }
        // 将疗程类型的数据输出为异常会员数据
        if (rowNums.size()>0){
            executeExcleSc(sheet, rowNums, storeId);
        }
        // 插入数据
        Set<String> levels = new HashSet<>();
        for (int i = 0; i < tableScs.size(); i++) {
            if (levelMap.get(tableScs.get(i).getLevelName())==null){
                levels.add(tableScs.get(i).getLevelName());
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        //先插入会员卡等级数据
        for (String level : levels) {
            saveEnterpriseMemberLevel(lastOperatorId, level, storeAccount, storeId);
//            MemberLevel level1 = new MemberLevel();
//            level1.setLevelName(level);
//            level1.setStoreId(hsqStoreId);
//            level1.setIsDeleted(0);
//            memberLevelMapper.insert(level1);
        }
        levelMap = packLevelMap(hsqStoreId);
        for (int k = 0; k < tableScs.size(); k++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(tableScs.get(k).getName());
            memberInfo.setSex(tableScs.get(k).getSex());
            memberInfo.setPhone(tableScs.get(k).getPhone());
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(tableScs.get(k).getLevelName()));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(tableScs.get(k).getBalanceAmount());
            account.setTotalAmount(tableScs.get(k).getTotalAmount());
            account.setTotalConsumeAmount(tableScs.get(k).getTotalConsumeAmount());
            account.setBalanceIntegral(tableScs.get(k).getBalanceIntegral().intValue());
            account.setTotalIntegral(tableScs.get(k).getBalanceIntegral().intValue());
            account.setDebtAmount(tableScs.get(k).getDebtAmount());
            account.setLastConsumeTime(tableScs.get(k).getLastConsumeTime());
            memberAccountMapper.insert(account);
            
            if (account.getBalanceAmount().intValue()>0){
                //余额流水变动
                changeMoneyFlow(memberInfo.getMemberId(), account.getBalanceAmount(), App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
            
            if (account.getBalanceIntegral()>0){
                //积分流水变动
                changeIntegralFlow(memberInfo, account, account.getBalanceIntegral(), App.Member.IMPORT_MONEY_DECS, 7, storeId, 0, lastOperatorId);
            }
            
            if (account.getDebtAmount().intValue()>0){
                //欠款流水变动
                insertDebtFlow(memberInfo.getMemberId(), null, account.getDebtAmount(), 
                        App.Member.IMPORT_MONEY_DECS, 1, lastOperatorId, DateUtil.getCurDate());
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }
    
    /**
     * 盛传疗程会员数据的导入
    * @author 高国藩
    * @date 2015年12月30日 下午1:52:20
    * @param file              疗程卡
    * @param storeId           门店
    * @param lastOperatorId    最后操作人
     * @throws IOException     异常
     */
    public void importRumorsCourse(MultipartFile file, Integer storeId, Integer lastOperatorId) throws IOException{
        String name = file.getOriginalFilename();
        boolean isE2007 = false;
        if (name.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = file.getInputStream();
        Workbook wb = null;
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        } 
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0);
        List<RumorsCourse> courses = new ArrayList<>();
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            RumorsCourse rumorsCourse = new RumorsCourse();
            Row xssfRow = sheet.getRow(rowNum);
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                Cell cell = xssfRow.getCell(cellNum);
                String str = ExcleUtils.changeCellToString(cell);
                if (cellNum == 0){
                    rumorsCourse.setPhone(str);
                }
                if (cellNum == 1){
                    rumorsCourse.setLevelNum(str);
                }
                if (cellNum == 2){
                    rumorsCourse.setName(str);
                }
                if (cellNum == 3){
                    rumorsCourse.setLevelName(str);
                }
                if (cellNum == 4){
                    rumorsCourse.setLevelType(str);
                }
                if (cellNum == 5){
                    rumorsCourse.setCourseDesc(str);
                }
                if (cellNum == 6){
                    rumorsCourse.setResidueDegree(Integer.parseInt(str));
                }
                if (cellNum == 7){
                    rumorsCourse.setResidueAmount(new BigDecimal(str));
                }
            }
            rumorsCourse.setStoreId(storeId);
            rumorsCourse.setIsDeleted(0);
            rumorsCourse.setCreateTime(DateUtil.getCurDate());
            rumorsCourse.setLastOperatorId(lastOperatorId);
            courses.add(rumorsCourse);
        }
        rumorsCourseMapper.insertList(courses);
    }
    
    /**
     * 盛传的会员数据中存在疗程格式的数据
    * @author 高国藩
    * @date 2015年11月19日 下午5:04:58
    * @param sheet  表单数据
    * @param rowNums  满足条件的行数
    * @param storeId  门店
    * @throws Exception 抛出异常
     */
    public void executeExcleSc(Sheet sheet, List<Integer> rowNums, Integer storeId) throws Exception {
        List<MemberErrorSc> errorScs = new ArrayList<>();
        a: for (int i = 0; i < rowNums.size(); i++) {
            MemberErrorSc memberErrorSc = new MemberErrorSc();
            Row xssfRow = sheet.getRow(rowNums.get(i));
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                Cell cell = xssfRow.getCell(cellNum);
                String str = ExcleUtils.changeCellToString(cell);
                // 先封装一层会员数据
                if (cellNum == 0) {
                    memberErrorSc.setPhone(str);
                }
                if (cellNum == 1) {
                    memberErrorSc.setName(str);
                }
                if (cellNum == 2) {
                    memberErrorSc.setSex(str);
                }
                if (cellNum == 4) {
                    memberErrorSc.setCreateTime(str);
                }
                if (cellNum == 5) {
                    memberErrorSc.setLevelNum(str);
                }
                if (cellNum == 6) {
                    memberErrorSc.setLevelName(str);
                }
                if (cellNum == 7) {
                    memberErrorSc.setLevelType(str);
                }
                if (cellNum == 8) {
                    memberErrorSc.setDiscount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 9) {
                    memberErrorSc.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 10) {
                    memberErrorSc.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 11) {
                    if (str.indexOf("疗程")!=-1){
                        memberErrorSc.setLevelType("疗程");
                        str = str.substring(3, str.length());
                        continue a;
                    }
                    if (str.indexOf("元")!=-1){
                        str = str.substring(0, str.length()-1);
                    }
                    memberErrorSc.setBalanceAmount(new BigDecimal(str));
                }
                if (cellNum == 12) {
                    memberErrorSc.setSendAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 13) {
                    memberErrorSc.setAeadTime(str);
                }
                if (cellNum == 14) {
                    memberErrorSc.setConsumeAmount(Integer.parseInt(str));
                }
                if (cellNum == 15) {
                    memberErrorSc.setBalanceIntegral(Integer.parseInt(str));
                }
                if (cellNum == 16) {
                    memberErrorSc.setLastConsumeTime(str);
                }
                if (cellNum == 17) {
                    memberErrorSc.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
            }
            memberErrorSc.setStoreId(storeId);
            errorScs.add(memberErrorSc);
        }
        memberErrorScMapper.insertList(errorScs);
    }
    
    
    /**
     * 左右门店导入数据
    * @author 高国藩
    * @date 2015年10月19日 上午9:37:26
    * @param file           文件
    * @param storeId        门店
    * @param storeAccount    企业代号
    * @param lastOperatorId   lastOperatorId
    * @param storeName      原服务商名称
    * @param response       输出流 
    * @return BaseDto d
     * @throws Exception  d
     */
    @Transactional
    public BaseDto importExcleZy(MultipartFile file, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response, String storeName)
                    throws Exception {
        updateStoreSet(storeId, storeName);
        List<TempTableZy> tableXies = new ArrayList<>();
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        InputStream is = file.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(is, "gb2312");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        log.info(line);
        Document doc = Jsoup.parse(line);
        Elements tr = doc.select("tr");
        // 会员等级和名称对应,方便取值
        Map<String, Integer> levelMap = packLevelMap(storeId);
        // saveMemberLevel(tr, levelMap, storeId);
        // levelMap = packLevelMap(storeId);
        // 将其中的异常数据保存如异常数据表中
        List<Integer> rowNums = new ArrayList<>();
        a: for (int i = 1; i < tr.size(); i++) {
            TempTableZy tableXy = new TempTableZy();
            Elements list = tr.get(i).getElementsByTag("td");
            for (int j = 0; j < list.size(); j++) {
                //姓名
                if (j == 1){
                    String n = list.get(j).text();
                    try {
                        String[] nameSex = n.split("【");
                        String memberName1 = nameSex[1].substring(0, nameSex[1].indexOf("】"));
                        String sex = nameSex[2].substring(0, nameSex[2].indexOf("】"));
                        tableXy.setName(memberName1);
                        tableXy.setSex(sex);
                    } 
                    catch (Exception e) {
                        rowNums.add(i);
                        continue a;
                    }
                }
                //会员卡
                if (j == 2){
                    String[] sf = list.get(j).text().split("【");
                    String df = sf[2].substring(0, sf[2].indexOf("】"));
                    String levleName;
                    if (df.indexOf("(")>0){
                        levleName = df.substring(0, df.indexOf("("));
                    }
                    else {
                        levleName = df; 
                    }
                    //Integer levelId = levelMap.get(levleName);
                    tableXy.setLevelName(levleName);
                }
                //卡金
                if (j == 3){
                    String[] sf1 = list.get(j).text().split(":");
                    String balanceAmount =  sf1[1].split("/")[0];
                    tableXy.setBalanceAmount(new BigDecimal(balanceAmount));
                }
                //电话
                if (j == 4){
                    String phone = list.get(j).text();
                    if (phone==null||phone.equals("")){
                        log.info("phone num is not exsit,this is not a memberInfo....");
                        rowNums.add(i);
                        continue a;
                    }
                    if (hasStr.contains(phone)) {
                        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, phone+"手机号码已存在");
                    } 
                    else {
                        Pattern p1 = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");  
                        Matcher m = p1.matcher(phone); 
                        if (m.matches()){
                            tableXy.setPhone(phone);
                        }
                        else {
                            rowNums.add(i);
                            log.info("phone num is not a true phone Num,this is not a memberInfo....");
                            continue a;
                        }
                    }
                }
            }
            tableXy.setStoreId(storeId);
            tableXies.add(tableXy);
        }
        // 将疗程类型的数据输出为新的excle
        if (rowNums.size()>0){
            errorMemberInsert(tr, rowNums, storeId);
        }
        log.info("成功导入会员:"+tableXies.size()+"会员异常数量"+rowNums.size());
        // 插入数据
        memberInfoMapper.dropTempTableZY();
        memberInfoMapper.updateTempTableZY();
        if (tableXies!=null&&tableXies.size()>0){
            tempTableXyMapper.insertList(tableXies);
        }
        
        List<MemberInfoDto> infoDtos = tempTableXyMapper.selectAllbyStoreId(storeId);
        Set<String> memberLevels = new HashSet<>();
        // 先插入会员等级
        for (int k = 0; k < infoDtos.size(); k++) {
            if (levelMap.get(infoDtos.get(k).getLevelName())==null){
                memberLevels.add(infoDtos.get(k).getLevelName());
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        for (String level : memberLevels) {
            saveEnterpriseMemberLevel(lastOperatorId, level, storeAccount, storeId);
//            MemberLevel level1 = new MemberLevel();
//            level1.setLevelName(level);
//            level1.setStoreId(hsqStoreId);
//            level1.setIsDeleted(0);
//            memberLevelMapper.insert(level1);
        }
        levelMap = packLevelMap(hsqStoreId);
        for (int k = 0; k < infoDtos.size(); k++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(infoDtos.get(k).getName());
            memberInfo.setSex(infoDtos.get(k).getSex());
            memberInfo.setPhone(infoDtos.get(k).getPhone());
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(infoDtos.get(k).getLevelName()));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(infoDtos.get(k).getBalanceAmount());
            account.setTotalAmount(infoDtos.get(k).getBalanceAmount());
            memberAccountMapper.insert(account);
            changeMoneyFlow(memberInfo.getMemberId(), account.getBalanceAmount(), App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
        }
        memberInfoMapper.dropTempTableZY();
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }
    
   
    /**
     * 左右数据中,会员与会员卡同时导入,将会员卡现行插入
    * @author 高国藩
    * @date 2015年11月21日 下午8:18:32
    * @param tr     数据tr
    * @param storeAccount storeAccount
    * @param lastOperatorId lastOperatorId
    * @param levelMap  已存在等级
    * @param storeId   门店
     */
    private void saveMemberLevel(Elements tr, Map<String, Integer> levelMap, Integer storeId, String storeAccount, Integer lastOperatorId) {
        Set<String> levelNames = new HashSet<>();
        for (int i = 0; i < tr.size(); i++) {
            Elements list = tr.get(i).getElementsByTag("td");
            for (int j = 0; j < list.size(); j++) {
                //会员卡
                if (j == 2){
                    String[] sf = list.get(j).text().split("【");
                    String df = sf[2].substring(0, sf[2].indexOf("】"));
                    String levleName;
                    if (df.indexOf("(")>0){
                        levleName = df.substring(0, df.indexOf("("));
                    }
                    else {
                        levleName = df; 
                    }
                    if (levelMap.get(levleName)==null){
                        levelNames.add(levleName);
                    }
                }
            }
        }
        for (String str : levelNames) {
            saveEnterpriseMemberLevel(lastOperatorId, str, storeAccount, storeId);
//            MemberLevel memberLevel = new MemberLevel();
//            memberLevel.setLevelName(str);
//            memberLevel.setStoreId(storeId);
//            log.info(JSONObject.fromObject(memberLevel).toString());
//            memberLevelMapper.insert(memberLevel);
        }
    }

    /**
     * 会员等级和等级名称hash
    * @author 高国藩
    * @date 2015年11月18日 下午8:00:04
    * @param storeId 门店的所属归属门店ID
    * @return        map
     */
    public Map<String, Integer> packLevelMap(Integer storeId) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<MemberLevelDto> levels = memberLevelMapper.selectByStoreId(storeId);
        for (int i = 0; i < levels.size(); i++) {
            map.put(levels.get(i).getLevelName(), levels.get(i).getLevelId());
        }
        return map;
    }

    /**
     * 左右的会员数据中存在疗程格式的数据,将其提取出啦,插入error表中
    * @author 高国藩
    * @date 2015年11月19日 下午5:04:58
    * @param tr  表单数据
    * @param rowNums  满足条件的行数
    * @param storeId  门店
    * @throws Exception 抛出异常
     */
    public void errorMemberInsert(Elements tr, List<Integer> rowNums, Integer storeId) throws Exception {
        List<MemberErrorZy> errorZies = new ArrayList<>();
        a: for (int i = 0; i < rowNums.size(); i++) {
            Elements list = tr.get(rowNums.get(i)).getElementsByTag("td");
            MemberErrorZy errorZy = new MemberErrorZy();
            for (int j = 0; j < list.size(); j++) {
                //姓名和性别
                if (j == 1){
                    String n = list.get(j).text();
                    try {
                        String[] nameSex = n.split("【");
                        String memberName1 = nameSex[1].substring(0, nameSex[1].indexOf("】"));
                        String sex = nameSex[2].substring(0, nameSex[2].indexOf("】"));
                        errorZy.setName(memberName1);
                        errorZy.setSex(sex);
                    } 
                    catch (Exception e) {
                        continue a;
                    }
                }
                //会员卡
                if (j == 2){
                    String[] sf = list.get(j).text().split("【");
                    String df = sf[2].substring(0, sf[2].indexOf("】"));
                    String[] af = sf[1].split("】");
                    String levleName;
                    if (df.indexOf("(")>0){
                        levleName = df.substring(0, df.indexOf("("));
                    }
                    else {
                        levleName = df; 
                    }
                    errorZy.setLevelName(levleName);
                    errorZy.setLevelNum(af[0]);
                }
                //卡金
                if (j == 3){
                    String[] sf1 = list.get(j).text().split(":");
                    String balanceAmount =  sf1[1].split("/")[0];
                    errorZy.setBalanceAmount(new BigDecimal(balanceAmount));
                }
                //电话
                if (j == 4){
                    String phone = list.get(j).text();
                    errorZy.setPhone(phone);
                }
            }
            errorZy.setStoreId(storeId);
            errorZies.add(errorZy);
        }
        if (errorZies!=null&&errorZies.size()>0){
            memberErrorZyMapper.insertList(errorZies);
        }
    }


    /**
     * 修改门店设置中原服务商的名称
    * @author 高国藩
    * @date 2015年12月4日 下午8:32:09
    * @param storeId  门店
    * @param storeName  原服务商名称
     */
    @Transactional
    public void updateStoreSet(Integer storeId, String storeName) {
        StoreSetting setting = storeSettingMapper.selectByPrimaryKey(storeId);
        if (setting==null){
            setting = new StoreSetting();
            setting.setStoreId(storeId);
            setting.setLastFacilitator(storeName);
            storeSettingMapper.insert(setting);
        }
        else {
            setting.setLastFacilitator(storeName);
            storeSettingMapper.updateByPrimaryKey(setting);
        }
    }


    /**
     * 云浩企汇通 会员导入,请注意,文件必须是一个.json结尾的文件
    * @author 高国藩
    * @date 2015年12月6日 下午4:53:17
    * @param file                  文件
    * @param storeId               门店
    * @param storeAccount               门店
    * @param storeName             原服务商名称
    * @param lastOperatorId        最后操作人
    * @param response              结果
    * @return                      状态
     * @throws IOException         异常处理
     */
    public BaseDto importExcleHt(MultipartFile file, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response, String storeName) throws IOException {
        updateStoreSet(storeId, storeName);
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        String fileName = file.getOriginalFilename();
        if (!fileName.endsWith(".json")){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "请保证文件.json格式");
        }
        else {
            List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
            // 已经存在的会员手机号码
            List<String> hasStr = new ArrayList<>();
            for (int i = 0; i < memberInfoDtos.size(); i++) {
                hasStr.add(memberInfoDtos.get(i).getPhone());
            }
            Map<String, Integer> levelMap = packLevelMap(storeId);
            Set<String> initLevel = new HashSet<>();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "utf-8"));
            JSONObject msg = JSONObject.fromObject(bufferedReader.readLine());
            JSONArray list = JSONArray.fromObject(msg.get("list"));
            //第一步循环将新的会员卡存进去
            for (int i = 0; i < list.size(); i++) {
                JSONObject memberInfo = JSONObject.fromObject(list.get(i));
                if (levelMap.get(memberInfo.get("levelName")) == null){
                    initLevel.add(memberInfo.get("levelName").toString());
                }
            }
            for (String levelName : initLevel) {
                saveEnterpriseMemberLevel(lastOperatorId, levelName, storeAccount, storeId);
//                MemberLevel level = new MemberLevel();
//                level.setLevelName(levelName);
//                level.setStoreId(hsqStoreId);
//                level.setIsDeleted(0);
//                memberLevelMapper.insert(level);
            }
            Map<String, String> sexMap = new HashMap<>();
            sexMap.put("1", "男");
            sexMap.put("2", "女");
            levelMap = packLevelMap(hsqStoreId);
            List<MemberInfo> memberInfos = new ArrayList<>();
            List<MemberAccount> accounts = new ArrayList<>();
            List<MemberErrorHt> errorHts = new ArrayList<>();
            a: for (int i = 0; i < list.size(); i++) {
                MemberInfo info = new MemberInfo();
                MemberAccount account = new MemberAccount();
                JSONObject memberInfo = JSONObject.fromObject(list.get(i));
                try {
                    
                    info.setName(memberInfo.get("name").toString());
                    info.setSex(sexMap.get(memberInfo.get("sex").toString()));
                    info.setLevelId(levelMap.get(memberInfo.get("levelName").toString()));
                    info.setStoreId(storeId);
                    info.setIsDeleted(0);
                    info.setLastOperatorId(lastOperatorId);
                    info.setCreateTime(DateUtil.getCurDate());
                    account.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(memberInfo.get("balanceOfCash").toString())));
                    account.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(memberInfo.get("balanceOfCash").toString())));
                    
//                    account.setTotalPresentAmount(new BigDecimal(ExcleUtils.changeValue(memberInfo.get("balanceOfBonus").toString())));
                    
                    account.setBalanceGiftmoneyAmount(new BigDecimal(memberInfo.get("balanceOfBonus").toString()));
                    account.setTotalGiftmoneyAmount(new BigDecimal(memberInfo.get("balanceOfBonus").toString()));
                    
                    account.setBalanceIntegral(Integer.parseInt(ExcleUtils.changeValue(memberInfo.get("totalPoints").toString())));
                    account.setTotalIntegral(Integer.parseInt(ExcleUtils.changeValue(memberInfo.get("totalPoints").toString())));
                    
                    try {
                        account.setLastConsumeTime(memberInfo.get("lastConsumeDate").toString());
                    } 
                    catch (Exception e) {
                        account.setLastConsumeTime("");
                    }
                    
                    //手机号码校验
                    String phone = memberInfo.get("mobilePhone").toString();
                    Pattern p = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");  
                    Matcher m = p.matcher(phone); 
                    if (m.matches()){
                        info.setPhone(phone);
                    }
                    else {
                        errorHts.add(errorMemberInsertHt(memberInfo, sexMap, storeId));
                        continue a;
                    }
                    if (hasStr.contains(phone)) {
                        errorHts.add(errorMemberInsertHt(memberInfo, sexMap, storeId));
                        continue a;
                    } 
                    
                    account.setConsumeCount(Integer.parseInt(ExcleUtils.changeValue(memberInfo.get("totalConsumedTimes").toString())));
                    account.setCreateTime(DateUtil.getCurDate());
                    account.setLastOperatorId(lastOperatorId);
                    memberInfos.add(info);
                    accounts.add(account);
                    hasStr.add(phone);
                } 
                catch (Exception e) {
                    errorHts.add(errorMemberInsertHt(memberInfo, sexMap, storeId));
                    continue a;
                }
            }
            for (int i = 0; i < memberInfos.size(); i++) {
                memberInfoMapper.insert(memberInfos.get(i));
                MemberAccount account = accounts.get(i);
                account.setAccountId(memberInfos.get(i).getMemberId());
                memberAccountMapper.insert(account);
                
                if (account.getBalanceAmount().intValue()>0){
                  //储值余额流水
                    changeMoneyFlow(memberInfos.get(i).getMemberId(), account.getBalanceAmount(), 
                            App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
                }
                //积分余额流水
                if (account.getBalanceIntegral() > 0){
                    changeIntegralFlow(memberInfos.get(i), account, account.getBalanceIntegral(), 
                            App.Member.IMPORT_MONEY_DECS, 7, storeId, 0, lastOperatorId);
                }
                //礼金余额流水
                if (account.getBalanceGiftmoneyAmount().intValue() > 0){
                    changeGiftMoneyFlow(memberInfos.get(i), account, account.getBalanceGiftmoneyAmount(), 
                            App.Member.IMPORT_MONEY_DECS, 7, lastOperatorId);
                }
            }
            if (errorHts.size()>0){
                memberErrorHtMapper.insertList(errorHts);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }
    
    /**
     * 处理错误会员导入-云浩企汇通
    * @author 高国藩
    * @date 2015年12月6日 下午7:28:06
    * @param memberInfo     json
    * @param sexMap         性别
    * @param storeId        门店
    * @return               错误数据类
     */
    public MemberErrorHt errorMemberInsertHt(JSONObject memberInfo, Map<String, String> sexMap, Integer storeId){
        MemberErrorHt info = new MemberErrorHt();
        try {
            info.setPhone(memberInfo.get("mobilePhone").toString());
        } 
        catch (Exception e) {
            info.setPhone("");
        }
        try {
            info.setName(memberInfo.get("name").toString());
        } 
        catch (Exception e) {
            info.setName("");
        }
        try {
            info.setLevelNum(memberInfo.get("cardNumber").toString());
        } 
        catch (Exception e) {
            info.setLevelNum("");
        }
        info.setSex(sexMap.get(memberInfo.get("sex").toString()));
        info.setLevelName(memberInfo.get("levelName").toString());
        info.setStoreId(storeId);
        info.setBalanceAmount(new BigDecimal(memberInfo.get("balanceOfCash").toString()));
        info.setBalanceGiftmoneyAmount(new BigDecimal(memberInfo.get("balanceOfBonus").toString()));
        info.setBalanceIntegral(new BigDecimal(memberInfo.get("balanceOfPoints").toString()));
        try {
            info.setLastConsumeTime(memberInfo.get("lastConsumeDate").toString());
        } 
        catch (Exception e) {
            info.setLastConsumeTime("");
        }
        
        try {
            info.setConsumeCount(Integer.parseInt(memberInfo.get("totalConsumedTimes").toString()));
        } 
        catch (Exception e) {
            info.setConsumeCount(0);
        }
        return info;
    }
    

    /**
     * 进入会员错误数据统计页面 
    * @author 高国藩
    * @date 2015年12月7日 上午10:39:28
    * @param storeId    门店
    * @param response   请求跳转
    * @return           页面
     * @throws IOException 异常
     */
    public ModelAndView viewErrorMember(Integer storeId, HttpServletResponse response) throws IOException {
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        ModelAndView view = new ModelAndView(View.MemberInfo.VEIW_ERROR_MEMBER);
        if (storeSetting == null){
            response.setContentType("text/html; charset=utf-8");
            PrintWriter pw=response.getWriter();
            pw.write("<script language='javascript'>"
                    + "alert('未查询到该门店的设置信息');history.back(-1);</script>");
            return null;
        }
        if ("盛传".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorSc> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorSc> results = memberErrorScMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "盛传");
            view.addObject("page", page);
            MemberErrorSc errorSc = memberErrorScMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorSc);
            List<ProjectInfo> projectInfos = projectInfoMapper.selectByStoreId(storeId);
            view.addObject("projectInfos", projectInfos);
            ComboInfo comboInfo = new ComboInfo();
            comboInfo.setStoreId(storeId);
            List<ComboInfo> comboInfos = comboInfoMapper.selectByProperty(comboInfo);
            view.addObject("comboInfos", comboInfos);
            //盛传疗程卡
            Page<RumorsCourse> pageRumors = new Page<>();
            pageRumors.setPageNo(1);
            pageRumors.setPageSize(50);
            Map<String, Object> mapRumors = new HashMap<>();
            mapRumors.put("storeId", storeId);
            pageRumors.setParams(mapRumors);
            List<RumorsCourse> rumorsCourses = rumorsCourseMapper.selectByPage(pageRumors);
            pageRumors.setResults(rumorsCourses);
            view.addObject("pageRumors", pageRumors);
            RumorsCourse course = rumorsCourseMapper.selectStoreMemberAmount(storeId);
            view.addObject("course", course);
            // 将疗程卡中的所有疗程卡名称取出
            List<String> projectNames = rumorsCourseMapper.selectAllByStoreId(storeId).
                    stream().
                    filter(item -> item.getCourseDesc().split(",")[1].indexOf("项目名称")!=-1).
                    map(item -> item.getCourseDesc().split(",")[1].split(":")[1]).
                    collect(Collectors.toList());
            Set<String> set = new HashSet<>();
            set.addAll(projectNames);
            projectNames = new ArrayList<>();
            projectNames.addAll(set);
            view.addObject("projectNames", projectNames);
            return view;
        } 
        else if ("左右".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorZy> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorZy> results = memberErrorZyMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "左右");
            view.addObject("page", page);
            MemberErrorZy errorZy = memberErrorZyMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorZy);
            return view;
        }
        else if ("云浩企汇通".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorHt> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorHt> results = memberErrorHtMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "云浩企汇通");
            view.addObject("page", page);
            MemberErrorHt errorHt = memberErrorHtMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorHt);
            return view;
        }
        else if ("博卡".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorBk> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorBk> results = memberErrorBkMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "博卡");
            view.addObject("page", page);
            MemberErrorBk errorBk = memberErrorBkMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorBk);
            return view;
        }
        else if ("耕宇".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorGy> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorGy> results = memberErrorGyMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "耕宇");
            view.addObject("page", page);
            MemberErrorGy errorGy = memberErrorGyMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorGy);
            return view;
        }
        else if ("蓝蝶".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorLd> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorLd> results = memberErrorLdMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "蓝蝶");
            view.addObject("page", page);
            MemberErrorLd errorLd = memberErrorLdMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorLd);
            return view;
        }
        else if ("共赢".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorGi> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorGi> results = memberErrorGiMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "共赢");
            view.addObject("page", page);
            MemberErrorGi errorGi = memberErrorGiMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorGi);
            return view;
        }
        else if ("模板".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorMb> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorMb> results = memberErrorMbMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "模板");
            view.addObject("page", page);
            MemberErrorMb errorMb = memberErrorMbMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorMb);
            return view;
        }
        else if ("西沙龙".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorXsl> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorXsl> results = memberErrorXslMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "西沙龙");
            view.addObject("page", page);
            MemberErrorXsl errorXsl = memberErrorXslMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorXsl);
            return view;
        }
        else if ("华拓美业".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorHtmy> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorHtmy> results = memberErrorHtmyMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "华拓美业");
            view.addObject("page", page);
            MemberErrorHtmy errorXsl = memberErrorHtmyMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorXsl);
            return view;
        }
        else if ("华彩".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorHc> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorHc> results = memberErrorHcMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "华彩");
            view.addObject("page", page);
            MemberErrorHc errorXsl = memberErrorHcMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorXsl);
            return view;
        }
        else {
            return view;
        }
    }


    /**
     * 分页查询异常会员数据
    * @author 高国藩
    * @date 2015年12月7日 下午2:30:08
    * @param storeId  门店
    * @param pageNo   页码
    * @param pageSize  液量
    * @param content   搜索条件
    * @return         result
     */
    public BaseDto viewErrorMember(Integer storeId, Integer pageNo,
            Integer pageSize, String content) {
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        BaseDto base = new BaseDto();
        if ("盛传".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorSc> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorSc> results = memberErrorScMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "盛传");
            msg.put("page", page);
            base.setMsg(msg);
            return base;
        } 
        else if ("左右".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorZy> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorZy> results = memberErrorZyMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "左右");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("云浩企汇通".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorHt> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorHt> results = memberErrorHtMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "云浩企汇通");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("博卡".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorBk> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorBk> results = memberErrorBkMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "博卡");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("耕宇".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorGy> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorGy> results = memberErrorGyMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "耕宇");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("蓝蝶".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorLd> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorLd> results = memberErrorLdMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "蓝蝶");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("模板".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorMb> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorMb> results = memberErrorMbMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "模板");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("西沙龙".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorXsl> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorXsl> results = memberErrorXslMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "西沙龙");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("华拓美业".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorHtmy> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorHtmy> results = memberErrorHtmyMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "华拓美业");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("华彩".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorHc> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorHc> results = memberErrorHcMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "华彩");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else {
            base.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            base.setMsg("暂无您的导入数据");
            return base;
        }
        return base;
    }
    
    
    /**
     * 会员错误数据删除操作
    * @author 张进军
    * @date Dec 13, 2015 12:59:05 PM
    * @param type   服务商类型(1:盛传，2:左右，3:云浩)
    * @param id     错误数据标识
    * @param userId 当前操作人
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto deleteErrorMemberAction(Integer type, Integer id, Integer userId){
        switch (type) {
            case 1:
                MemberErrorSc errorSc = new MemberErrorSc();
                errorSc.setLastOperatorId(userId);
                errorSc.setId(id);
                errorSc.setIsDeleted(2);
                errorSc.setUpdateTime(DateUtil.getCurTime());
                memberErrorScMapper.updateByPrimaryKeySelective(errorSc);
                break;
            case 2:
                MemberErrorZy errorZy = new MemberErrorZy();
                errorZy.setLastOperatorId(userId);
                errorZy.setId(id);;
                errorZy.setIsDeleted(2);
                errorZy.setUpdateTime(DateUtil.getCurTime());
                memberErrorZyMapper.updateByPrimaryKeySelective(errorZy);
                break;            
            case 3:
                MemberErrorHt errorHt = new MemberErrorHt();
                errorHt.setId(id);
                errorHt.setLastOperatorId(userId);
                errorHt.setIsDeleted(2);
                errorHt.setUpdateTime(DateUtil.getCurTime());
                memberErrorHtMapper.updateByPrimaryKeySelective(errorHt);
                break;
            case 4:
                MemberErrorBk errorBk = new MemberErrorBk();
                errorBk.setId(id);
                errorBk.setLastOperatorId(userId);
                errorBk.setIsDeleted(2);
                errorBk.setUpdateTime(DateUtil.getCurTime());
                memberErrorBkMapper.updateByPrimaryKeySelective(errorBk);
                break;
            case 5:
                MemberErrorGy errorGy = new MemberErrorGy();
                errorGy.setId(id);
                errorGy.setLastOperatorId(userId);
                errorGy.setIsDeleted(2);
                errorGy.setUpdateTime(DateUtil.getCurTime());
                memberErrorGyMapper.updateByPrimaryKeySelective(errorGy);
                break;
            case 8:
                MemberErrorMb errorMb = new MemberErrorMb();
                errorMb.setId(id);
                errorMb.setLastOperatorId(userId);
                errorMb.setIsDeleted(2);
                errorMb.setUpdateTime(DateUtil.getCurTime());
                memberErrorMbMapper.updateByPrimaryKeySelective(errorMb);
                break;
            case 9:
                MemberErrorXsl errorXsl = new MemberErrorXsl();
                errorXsl.setId(id);
                errorXsl.setLastOperatorId(userId);
                errorXsl.setIsDeleted(2);
                errorXsl.setUpdateTime(DateUtil.getCurTime());
                memberErrorXslMapper.updateByPrimaryKeySelective(errorXsl);
                break;
            case 10:
                MemberErrorHtmy errorHtmy = new MemberErrorHtmy();
                errorHtmy.setId(id);
                errorHtmy.setLastOperatorId(userId);
                errorHtmy.setIsDeleted(2);
                errorHtmy.setUpdateTime(DateUtil.getCurTime());
                memberErrorHtmyMapper.updateByPrimaryKeySelective(errorHtmy);
                break;
            case 11:
                MemberErrorHc errorHc = new MemberErrorHc();
                errorHc.setId(id);
                errorHc.setLastOperatorId(userId);
                errorHc.setIsDeleted(2);
                errorHc.setUpdateTime(DateUtil.getCurTime());
                memberErrorHcMapper.updateByPrimaryKeySelective(errorHc);
                break;
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }


    /**
     * 查询总店下所有分店的信息
    * @author 高国藩
    * @date 2015年12月12日 下午3:35:15
    * @param storeId   总店id
    * @return          总店查询会员的页面
     */
    public ModelAndView viewBaseMember(Integer storeId) {
        ModelAndView view = new ModelAndView(View.MemberInfo.BASE_MEMBER_VIEW);
        List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
        Page<MemberInfoDto> page = new Page<MemberInfoDto>();
        page.setPageNo(1);
        page.setPageSize(50);
        Map<String, Object> map = new HashMap<String, Object>();
        if (storeList!=null&&storeList.size()>0){
            map.put("storeList", storeList);
        }
        else {
            map.put("storeId", storeId);
        }
        page.setParams(map);
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberInfosByPage(page);
        page.setResults(memberInfoDtos);
        List<MemberLevelDto> levels = memberLevelMapper.selectByStoreId(storeId);
        view.addObject("page", page);
        view.addObject("levels", levels);
        view.addObject("storeList", storeList);
        return view;
    }


    /**
     * 导入博卡会员数据
    * @author 高国藩
    * @date 2015年12月16日 下午7:35:56
    * @param multipartFile     消费余额统计表
    * @param multipartFile2    会员信息统计表
    * @param storeId           门店
    * @param storeAccount               门店
    * @param storeName         原服务商名称
    * @param lastOperatorId    最后操作人
    * @param response          response
    * @return                  状态指示
    * @throws IOException      文件流
    * @throws Exception        异常
     */
    public BaseDto importExcleBk(MultipartFile multipartFile,
            MultipartFile multipartFile2, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response, String storeName) throws IOException, Exception {
        updateStoreSet(storeId, storeName);
        Map<String, Integer> levelMap = getLevel(multipartFile.getInputStream(), storeId, storeAccount, lastOperatorId);
        return readInfo(multipartFile2.getInputStream(), levelMap, storeId, lastOperatorId);
    }
    
    /**
     * 对传入的博卡数据进行会员等级的封装
    * @author 高国藩
    * @date 2015年12月16日 下午7:34:43
    * @param is             文件流
    * @param storeId        门店
    * @param storeAccount               门店
    * @param lastOperatorId               门店
    * @return               会员等级hash
    * @throws Exception     异常
     */
    public Map<String, Integer> getLevel(InputStream is, Integer storeId, String storeAccount, Integer lastOperatorId) throws Exception {
        Set<String> set = new HashSet<String>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Map<String, String> levelMap = new HashMap<String, String>();
        // 循环工作表Sheet
        StringBuffer sb = new StringBuffer();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null || hssfRow.getLastCellNum() != 16) {
                    continue;
                }
                //检查首列内容
                String firstContent = ExcleUtils.changeCellToString(hssfRow.getCell(0));
                if (StringUtils.isEmpty(firstContent) || firstContent.startsWith("卡号")) {
                    continue;
                }
                firstContent = firstContent.replace("(", ",").replace(")", "");
                String[] levelArr = firstContent.split(",");
                String card = levelArr[0];
                String level = levelArr[1].replaceAll("()", "");
                levelMap.put(card, level);
                set.add(level);
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        Map<String, Integer> hasLevelMap = packLevelMap(hsqStoreId);
        for (String levelName : set) {
            if (hasLevelMap.get(levelName)==null){
                saveEnterpriseMemberLevel(lastOperatorId, levelName, storeAccount, storeId);
//                MemberLevel level = new MemberLevel();
//                level.setLevelName(levelName);
//                level.setStoreId(hsqStoreId);
//                memberLevelMapper.insert(level);
            }
        }
        List<MemberLevelDto> levels = memberLevelMapper.selectByStoreId(hsqStoreId);
        Map<String, Integer> map = new HashMap<>();
        //循环将会员卡和等级的id绑定好
        for (String key : levelMap.keySet()) {
            for (@SuppressWarnings("rawtypes")
                Iterator iterator = levels.iterator(); iterator.hasNext();) {
                MemberLevel memberLevel = (MemberLevel) iterator.next();
                if (levelMap.get(key).equals(memberLevel.getLevelName())){
                    map.put(key, memberLevel.getLevelId());
                }
            }
        }  
        return map;
    }
    
    /**
     * 对博卡的数据进行组装存入数据库中
    * @author 高国藩
    * @date 2015年12月16日 下午7:33:45
    * @param in             文件流
    * @param levelMap       会员等级的hash
    * @param storeId        门店
    * @param lastOperatorId 最后操作人
    * @return               状态码
    * @throws Exception     异常
     */
    public BaseDto readInfo(InputStream in, Map<String, Integer> levelMap, Integer storeId, Integer lastOperatorId) throws Exception {
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberErrorBk> errorBks = new ArrayList<>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
        // 循环工作表Sheet
        StringBuffer sb = new StringBuffer();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null || hssfRow.getLastCellNum() != 16) {
                    continue;
                }
                
                //检查首行内容
                String firstContent = ExcleUtils.changeCellToString(hssfRow.getCell(0));
                if (StringUtils.isEmpty(firstContent) || firstContent.startsWith("会员编号") || firstContent.startsWith("储值账户")) {
                    continue;
                }
                String card = ExcleUtils.changeCellToString(hssfRow.getCell(0));
                String name = ExcleUtils.changeCellToString(hssfRow.getCell(1));
                String sex = ExcleUtils.changeCellToString(hssfRow.getCell(2));
                String phone = ExcleUtils.changeCellToString(hssfRow.getCell(4));
                String totalMoney = ExcleUtils.changeCellToString(hssfRow.getCell(10));
                String balanceAmount = ExcleUtils.changeCellToString(hssfRow.getCell(11));
                String cousumerAmount = ExcleUtils.changeCellToString(hssfRow.getCell(12));
                String consumerCount = ExcleUtils.changeCellToString(hssfRow.getCell(13));
                String avgConsumerPrice = ExcleUtils.changeCellToString(hssfRow.getCell(14));
                String lastConsumerDate = ExcleUtils.changeCellToString(hssfRow.getCell(15));
                sb.append(card + "\t" + name + "\t" + sex + "\t" + phone + "\t" + totalMoney + "\t" + balanceAmount + "\t" + cousumerAmount
                        + "\t" + consumerCount + "\t" + avgConsumerPrice + "\t" + lastConsumerDate + "\r\n");
                MemberInfo info = new MemberInfo();
                info.setLevelId(levelMap.get(card));
                info.setName(name);
                info.setSex(sex);
                info.setPhone(phone);
                info.setStoreId(storeId);
                MemberAccount account = new MemberAccount();
                account.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(totalMoney)));
                account.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                account.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(cousumerAmount)));
                account.setConsumeCount(Integer.parseInt(ExcleUtils.changeValue(consumerCount)));
                account.setAvgConsumeAmount(new BigDecimal(ExcleUtils.changeValue(avgConsumerPrice)));
                account.setLastConsumeTime(lastConsumerDate);
                if (name==null||"".equals(name)){
                    continue;
                }
                if (!ExcleUtils.isPhone(ExcleUtils.changeCellToString(hssfRow.getCell(4)))||info.getLevelId()==null||hasStr.contains(phone)){
                    MemberErrorBk errorBk = new MemberErrorBk();
                    errorBk.setLevelNum(card);
                    errorBk.setName(name);
                    errorBk.setSex(sex);
                    errorBk.setPhone(phone);
                    errorBk.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(totalMoney)));
                    errorBk.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                    errorBk.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(cousumerAmount)));
                    errorBk.setConsumeCount(Integer.parseInt(ExcleUtils.changeValue(consumerCount)));
                    errorBk.setAvgConsumeAmount(new BigDecimal(ExcleUtils.changeValue(avgConsumerPrice)));
                    errorBk.setLastConsumeTime(lastConsumerDate);
                    errorBk.setStoreId(storeId);
                    errorBk.setIsDeleted(0);
                    errorBks.add(errorBk);
                    continue;
                }
                hasStr.add(phone);
                memberInfos.add(info);
                accounts.add(account);
            }
            for (int i = 0; i < memberInfos.size(); i++) {
                memberInfoMapper.insert(memberInfos.get(i));
                MemberAccount account = accounts.get(i);
                account.setAccountId(memberInfos.get(i).getMemberId());
                memberAccountMapper.insert(account);
                //储值余额流水
                changeMoneyFlow(memberInfos.get(i).getMemberId(), account.getBalanceAmount(), 
                        App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
            if (errorBks.size()>0){
                for (int i = 0; i < errorBks.size(); i++) {
                    if (errorBks.get(i) == null){
                        log.info("第:"+i+"个是空");
                    }
                }
                memberErrorBkMapper.insertList(errorBks);
            }
        } 
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }


    /**
     * 导入异常会员数据
    * @author 高国藩
    * @date 2015年12月17日 下午5:22:55
    * @param storeId       门店
    * @return              导出
    * @throws IOException  异常
     */
    public ResponseEntity<byte[]> downloadErrorMember(Integer storeId) throws IOException {
        StoreSetting setting = storeSettingMapper.selectByPrimaryKey(storeId);
        if (setting.getLastFacilitator()==null){
            return null;
        }
        if (setting.getLastFacilitator().equals("盛传")){
            return downloadErrorMemberSc(storeId);
        }
        else if (setting.getLastFacilitator().equals("左右")){
            return downloadErrorMemberZy(storeId);
        }
        else if (setting.getLastFacilitator().equals("云浩企汇通")){
            return downloadErrorMemberHt(storeId);
        }
        else if (setting.getLastFacilitator().equals("博卡")){
            return downloadErrorMemberBk(storeId);
        }
        else if (setting.getLastFacilitator().equals("耕宇")){
            return downloadErrorMemberGy(storeId);
        }
        else if (setting.getLastFacilitator().equals("蓝蝶")){
            return downloadErrorMemberLd(storeId);
        }
        return null;
        
    }
    
    /**
     * 下载蓝蝶异常会员数据
    * @author 高国藩
    * @date 2016年2月22日 下午5:31:34
    * @param storeId          门店
    * @return                 下载
    * @throws IOException     异常处理
     */
    private ResponseEntity<byte[]> downloadErrorMemberLd(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("卡号");
        cell4.setCellValue("储值余额");
        cell5.setCellValue("可用积分");
        cell6.setCellValue("欠款金额");
        List<MemberErrorLd> bks = memberErrorLdMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            HSSFCell cellValue6 = rowValue.createCell(6);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getLevelNum());
            cellValue4.setCellValue(bks.get(i).getBalanceAmount().toString());
            cellValue5.setCellValue(bks.get(i).getBalanceIntegral().toString());
            cellValue6.setCellValue(bks.get(i).getDebtAmount().toString());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }


    /**
     * 下载耕宇异常会员数据
    * @author 高国藩
    * @date 2016年1月13日 下午7:31:37
    * @param storeId         门店
    * @return                下载留
    * @throws IOException    抛出io异常
     */
    private ResponseEntity<byte[]> downloadErrorMemberGy(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(9);
        HSSFCell cell10 = row.createCell(10);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("卡号");
        cell4.setCellValue("储值总额");
        cell5.setCellValue("储值余额");
        cell6.setCellValue("累计消费总额");
        cell7.setCellValue("累计消费次数");
        cell8.setCellValue("平均消费");
        cell9.setCellValue("欠款金额");
        cell10.setCellValue("积分余额");
        List<MemberErrorGy> bks = memberErrorGyMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            HSSFCell cellValue6 = rowValue.createCell(6);
            HSSFCell cellValue7 = rowValue.createCell(7);
            HSSFCell cellValue8 = rowValue.createCell(8);
            HSSFCell cellValue9 = rowValue.createCell(9);
            HSSFCell cellValue10 = rowValue.createCell(10);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getLevelNum());
            cellValue4.setCellValue(bks.get(i).getTotalAmount().toString());
            cellValue5.setCellValue(bks.get(i).getBalanceAmount().toString());
            cellValue6.setCellValue(bks.get(i).getTotalConsumeAmount().toString());
            cellValue7.setCellValue(bks.get(i).getConsumeCount().toString());
            cellValue8.setCellValue(bks.get(i).getAvgConsumeAmount().toString());
            cellValue9.setCellValue(bks.get(i).getDebtAmount().toString());
            cellValue10.setCellValue(bks.get(i).getBalanceIntegral().toString());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }


    /**
     * 博卡异常会员数据导出
    * @author 高国藩
    * @date 2015年12月17日 下午5:23:31
    * @param storeId         门店
    * @return                导出
    * @throws IOException    异常
     */
    public ResponseEntity<byte[]> downloadErrorMemberBk(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(8);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("卡号");
        cell4.setCellValue("储值总额");
        cell5.setCellValue("储值余额");
        cell6.setCellValue("累计消费总额");
        cell7.setCellValue("累计消费次数");
        cell8.setCellValue("平均消费");
        cell9.setCellValue("最后消费日期");
        List<MemberErrorBk> bks = memberErrorBkMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            HSSFCell cellValue6 = rowValue.createCell(6);
            HSSFCell cellValue7 = rowValue.createCell(7);
            HSSFCell cellValue8 = rowValue.createCell(8);
            HSSFCell cellValue9 = rowValue.createCell(8);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getLevelNum());
            cellValue4.setCellValue(bks.get(i).getTotalAmount().toString());
            cellValue5.setCellValue(bks.get(i).getBalanceAmount().toString());
            cellValue6.setCellValue(bks.get(i).getTotalConsumeAmount().toString());
            cellValue7.setCellValue(bks.get(i).getConsumeCount().toString());
            cellValue8.setCellValue(bks.get(i).getAvgConsumeAmount().toString());
            cellValue9.setCellValue(bks.get(i).getLastConsumeTime());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }

    /**
     * 企汇通异常会员数据导出
    * @author 高国藩
    * @date 2015年12月17日 下午5:23:31
    * @param storeId         门店
    * @return                导出
    * @throws IOException    异常
     */
    public ResponseEntity<byte[]> downloadErrorMemberHt(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(8);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("卡名");
        cell4.setCellValue("卡号");
        cell5.setCellValue("储值余额");
        cell6.setCellValue("礼金储值余额 ");
        cell7.setCellValue("累计消费次数");
        cell8.setCellValue("积分余额");
        cell9.setCellValue("最后消费日期");
        List<MemberErrorHt> bks = memberErrorHtMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            HSSFCell cellValue6 = rowValue.createCell(6);
            HSSFCell cellValue7 = rowValue.createCell(7);
            HSSFCell cellValue8 = rowValue.createCell(8);
            HSSFCell cellValue9 = rowValue.createCell(8);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getLevelName());
            cellValue4.setCellValue(bks.get(i).getLevelNum());
            cellValue5.setCellValue(bks.get(i).getBalanceAmount().toString());
            cellValue6.setCellValue(bks.get(i).getBalanceGiftmoneyAmount().toString());
            cellValue7.setCellValue(bks.get(i).getConsumeCount().toString());
            cellValue8.setCellValue(bks.get(i).getBalanceIntegral().toString());
            cellValue9.setCellValue(bks.get(i).getLastConsumeTime());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }

    /**
     * 左右异常会员数据导出
    * @author 高国藩
    * @date 2015年12月17日 下午5:23:31
    * @param storeId         门店
    * @return                导出
    * @throws IOException    异常
     */
    public ResponseEntity<byte[]> downloadErrorMemberZy(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("卡名");
        cell4.setCellValue("卡号");
        cell5.setCellValue("卡内总余额");
        List<MemberErrorZy> bks = memberErrorZyMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getLevelName());
            cellValue4.setCellValue(bks.get(i).getLevelNum());
            cellValue5.setCellValue(bks.get(i).getBalanceAmount().toString());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }

    /**
     * 盛传异常会员数据导出
    * @author 高国藩
    * @date 2015年12月17日 下午5:23:31
    * @param storeId         门店
    * @return                导出
    * @throws IOException    异常
     */
    public ResponseEntity<byte[]> downloadErrorMemberSc(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(9);
        HSSFCell cell10 = row.createCell(10);
        HSSFCell cell11 = row.createCell(11);
        HSSFCell cell12 = row.createCell(12);
        HSSFCell cell13 = row.createCell(13);
        HSSFCell cell14 = row.createCell(14);
        HSSFCell cell15 = row.createCell(15);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("创建时间");
        cell4.setCellValue("卡号");
        cell5.setCellValue("卡名");
        cell6.setCellValue("卡类型");
        cell7.setCellValue("折扣");
        cell8.setCellValue("储值总额");
        cell9.setCellValue("消费总额");
        cell10.setCellValue("卡内总余额");
        cell11.setCellValue("赠送总余额");
        cell12.setCellValue("失效日期");
        cell13.setCellValue("消费次数");
        cell14.setCellValue("当前积分");
        cell15.setCellValue("最后消费日");
        List<MemberErrorSc> bks = memberErrorScMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            HSSFCell cellValue6 = rowValue.createCell(6);
            HSSFCell cellValue7 = rowValue.createCell(7);
            HSSFCell cellValue8 = rowValue.createCell(8);
            HSSFCell cellValue9 = rowValue.createCell(9);
            HSSFCell cellValue10 = rowValue.createCell(10);
            HSSFCell cellValue11 = rowValue.createCell(11);
            HSSFCell cellValue12 = rowValue.createCell(12);
            HSSFCell cellValue13 = rowValue.createCell(13);
            HSSFCell cellValue14 = rowValue.createCell(14);
            HSSFCell cellValue15 = rowValue.createCell(15);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getCreateTime());
            cellValue4.setCellValue(bks.get(i).getLevelNum());
            cellValue5.setCellValue(bks.get(i).getLevelName());
            cellValue6.setCellValue(bks.get(i).getLevelType());
            cellValue7.setCellValue(bks.get(i).getDiscount().toString());
            cellValue8.setCellValue(bks.get(i).getTotalAmount().toString());
            cellValue9.setCellValue(bks.get(i).getTotalConsumeAmount().toString());
            cellValue10.setCellValue(bks.get(i).getBalanceAmount().toString());
            cellValue11.setCellValue(bks.get(i).getSendAmount().toString());
            cellValue12.setCellValue(bks.get(i).getAeadTime());
            cellValue13.setCellValue(bks.get(i).getConsumeAmount().toString());
            cellValue14.setCellValue(bks.get(i).getBalanceIntegral().toString());
            cellValue15.setCellValue(bks.get(i).getLastConsumeTime());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }
    
    /**
     * 更新储值流水表
    * @author 高国藩
    * @date 2015年12月17日 下午8:23:28
    * @param accountId              账户标识
    * @param flowAmount             流水金额
    * @param desc                   流水业务描述
    * @param businessType           流水类型(7:导入,8:迁移)
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作人员
     */
    public void changeMoneyFlow(int accountId, BigDecimal flowAmount, String desc, Integer businessType, Integer storeId 
            , Integer lastOperatorId){
        MoneyFlow moneyFlow = new MoneyFlow();
        moneyFlow.setAccountId(accountId);
        moneyFlow.setBalanceAmount(new BigDecimal(0));
        moneyFlow.setBusinessType(businessType);
        moneyFlow.setFlowType(2);
        moneyFlow.setFlowAmount(flowAmount);
        moneyFlow.setStoreId(storeId);
        moneyFlow.setIsDeleted(0);
        moneyFlow.setLastOperatorId(lastOperatorId);
        moneyFlow.setFlowTime(DateUtil.getCurDate());
        moneyFlow.setBusinessDesc(desc);
        moneyFlowMapper.insert(moneyFlow);
    }
    /**
     * 更新积分流水表
    * @author 高国藩
    * @date 2015年12月17日 下午8:23:28
    * @param info                   会员信息
    * @param account                会员明细信息
    * @param flowAmount             流水金额
    * @param desc                   流水业务描述
    * @param businessType           流水类型(7:导入,8:迁移)
    * @param storeId                门店信息
    * @param balanceAmount          当前余额(0:导入,迁移要查询)
    * @param lastOperatorId         最后操作人员
     */
    public void changeIntegralFlow(MemberInfo info, MemberAccount account, Integer flowAmount, String desc, Integer businessType, Integer storeId 
            , Integer balanceAmount, Integer lastOperatorId){
        IntegralFlow integralFlow = new IntegralFlow();
        integralFlow.setAccountId(info.getMemberId());
        integralFlow.setFlowAmount(flowAmount);
        integralFlow.setBalanceAmount(balanceAmount);
        integralFlow.setFlowType(2);
        integralFlow.setBusinessType(businessType.toString());
        integralFlow.setFlowTime(DateUtil.getCurDate());
        integralFlow.setBusinessDesc(desc);
        integralFlow.setIsDeleted(0);
        integralFlowMapper.insert(integralFlow);
    }
    
    /**
     * 礼金流水变动
    * @author 高国藩
    * @date 2015年12月17日 下午9:22:19
    * @param info              会员信息
    * @param account           会员数据信息
    * @param desc              流水业务描述
    * @param businessType      流水类型(7:导入,8:迁移)
    * @param giftMoneyAmount   礼金金额
    * @param lastOperatorId    最后操作人员
     */
    public void changeGiftMoneyFlow(MemberInfo info, MemberAccount account, BigDecimal giftMoneyAmount, 
            String desc, Integer businessType, Integer lastOperatorId){
        GiftmoneyDetail giftmoneyDetail = new GiftmoneyDetail();
        giftmoneyDetail.setAccountId(info.getMemberId());
        giftmoneyDetail.setTotalAmount(giftMoneyAmount);
        giftmoneyDetail.setNowMoney(giftMoneyAmount);
        giftmoneyDetail.setResidueNowMoney(giftMoneyAmount);
        giftmoneyDetail.setPartNumber(0);
        giftmoneyDetail.setStartDate(DateUtil.getCurDate());
        giftmoneyDetail.setEndDate("永久");
        giftmoneyDetail.setCreateTime(DateUtil.getCurDate());
        giftmoneyDetail.setIsDeleted(0);
        giftmoneyDetail.setIsPresent(1);
        giftmoneyDetail.setLastOperatorId(lastOperatorId);
        giftmoneyDetailMapper.insertSelective(giftmoneyDetail);
        GiftmoneyFlow giftmoneyFlow = new GiftmoneyFlow();
        giftmoneyFlow.setAccountId(account.getAccountId());
        giftmoneyFlow.setFlowType(2);
        giftmoneyFlow.setFlowAmount(account.getBalanceGiftmoneyAmount());
        giftmoneyFlow.setBusinessType(businessType.toString());
        giftmoneyFlow.setBusinessDesc(desc);
        giftmoneyFlow.setFlowTime(DateUtil.getCurDate());
        giftmoneyFlow.setIsDeleted(0);
        giftmoneyFlowMapper.insertSelective(giftmoneyFlow);
    }
    
    /**
     * 新增挂账欠款流水
    * @author 高国藩
    * @date 2015年12月28日 下午8:13:29
    * @param accountId            会员标示
    * @param orderId              订单标识
    * @param flowAmount           挂账还款金额
    * @param flowDesc             明细描述
    * @param flowType             欠款类型(1:欠款,2:还款)
    * @param lastOperatorId       最后操作人
    * @param flowDebtTime         签单挂账时间
     */
    public void insertDebtFlow(Integer accountId, Integer orderId, BigDecimal flowAmount, 
            String flowDesc, Integer flowType, Integer lastOperatorId, String flowDebtTime){
        DebtFlow debtFlow = new DebtFlow();
        debtFlow.setAccountId(accountId);
        debtFlow.setFlowDesc(flowDesc);
        debtFlow.setFlowType(flowType);
        debtFlow.setInAmount(flowAmount);
        debtFlow.setFlowDebtTime(flowDebtTime);
        debtFlow.setOrderId(orderId);
        debtFlow.setLastOperatorId(lastOperatorId);
        debtFlow.setIsDeleted(0);
        debtFlowMapper.insertSelective(debtFlow);
    }


    /**
     * 修改会员资料
    * @author 高国藩
    * @date 2015年12月18日 下午3:46:00
    * @param memberInfo    会员信息
    * @param storeId       门店信息
    * @return              修改状态
     */
    public BaseDto updateMemberInfo(MemberInfo memberInfo, Integer storeId) {
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasPhones = new ArrayList<>();
        Map<String, Integer> memberPhone =  new HashMap<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasPhones.add(memberInfoDtos.get(i).getPhone());
            memberPhone.put(memberInfoDtos.get(i).getPhone(), memberInfoDtos.get(i).getMemberId());
        }
        if (hasPhones.contains(memberInfo.getPhone())&&(memberInfo.getMemberId().intValue()!=memberPhone.get(memberInfo.getPhone()).intValue())){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该手机号已存在");
        }
        int ok = memberInfoMapper.updateByPrimaryKey(memberInfo);
        // 更新redis会员数据缓存
        MemberBaseDto memberBaseInfo = memberInfoMapper.selectMemberBaseInfo(memberInfo.getMemberId());
        redisService.hset(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberInfo.getMemberId(),   
                EntityJsonConverter.entity2Json(memberBaseInfo));
        switch(ok){
            case 1: return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "更新成功");
            case 0: return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "更新失败");
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "更新失败");
    }

    /**
     * 会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @param type                   类型(1:盛传 2:左右 3:企汇通 4:博卡)
    * @return                       状态提示
     */
    public BaseDto balandMemberMove(Integer storeId, Integer lastOperatorId,
            Integer type, Integer id, Integer memberId) {
        switch(type){
            case 1: return balandMemberMoveSc(storeId, lastOperatorId, id, memberId);
            case 2: return balandMemberMoveZy(storeId, lastOperatorId, id, memberId);
            case 3: return balandMemberMoveHt(storeId, lastOperatorId, id, memberId);
            case 4: return balandMemberMoveBk(storeId, lastOperatorId, id, memberId);
            case 5: return balandMemberMoveGy(storeId, lastOperatorId, id, memberId);
            case 6: return balandMemberMoveLd(storeId, lastOperatorId, id, memberId);
            case 7: return balandMemberMoveGi(storeId, lastOperatorId, id, memberId);
            case 8: return balandMemberMoveMb(storeId, lastOperatorId, id, memberId);
            case 9: return balandMemberMoveXsl(storeId, lastOperatorId, id, memberId);
            case 10: return balandMemberMoveHtmy(storeId, lastOperatorId, id, memberId);
            case 11: return balandMemberMoveHc(storeId, lastOperatorId, id, memberId);
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "迁移失败,请稍后重试");
    }
    
    /**
     * 华彩数据迁移
    * @author 高国藩
    * @date 2016年3月16日 下午3:13:24
    * @param storeId storeId
    * @param lastOperatorId storeId
    * @param id storeId
    * @param memberId storeId
    * @return storeId
     */
    private BaseDto balandMemberMoveHc(Integer storeId, Integer lastOperatorId,
            Integer id, Integer memberId) {
        MemberErrorHc errorXsl = memberErrorHcMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorXsl.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        if (errorXsl.getBalanceAmount().intValue()>0){
            changeMoneyFlow(memberInfo.getMemberId(), errorXsl.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        }
        errorXsl.setUpdateTime(DateUtil.getCurDate());
        errorXsl.setLastOperatorId(lastOperatorId);
        errorXsl.setIsDeleted(1);
        memberErrorHcMapper.updateByPrimaryKeySelective(errorXsl);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     * 华拓美业异常会员迁移
    * @author 高国藩
    * @date 2016年3月2日 下午2:10:21
    * @param storeId         门店
    * @param lastOperatorId  最后操作人
    * @param id              id
    * @param memberId        会员ID
    * @return                状态
     */
    private BaseDto balandMemberMoveHtmy(Integer storeId,
            Integer lastOperatorId, Integer id, Integer memberId) {
        MemberErrorHtmy errorXsl = memberErrorHtmyMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorXsl.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        if (errorXsl.getBalanceAmount().intValue()>0){
            changeMoneyFlow(memberInfo.getMemberId(), errorXsl.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        }
        errorXsl.setUpdateTime(DateUtil.getCurDate());
        errorXsl.setLastOperatorId(lastOperatorId);
        errorXsl.setIsDeleted(1);
        memberErrorHtmyMapper.updateByPrimaryKeySelective(errorXsl);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     * 西沙龙异常会员迁移
    * @author 高国藩
    * @date 2016年3月2日 下午2:10:21
    * @param storeId         门店
    * @param lastOperatorId  最后操作人
    * @param id              id
    * @param memberId        会员ID
    * @return                状态
     */
    private BaseDto balandMemberMoveXsl(Integer storeId, Integer lastOperatorId,
            Integer id, Integer memberId) {
        MemberErrorXsl errorXsl = memberErrorXslMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorXsl.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        if (errorXsl.getBalanceAmount().intValue()>0){
            changeMoneyFlow(memberInfo.getMemberId(), errorXsl.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        }
        errorXsl.setUpdateTime(DateUtil.getCurDate());
        errorXsl.setLastOperatorId(lastOperatorId);
        errorXsl.setIsDeleted(1);
        memberErrorXslMapper.updateByPrimaryKeySelective(errorXsl);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     * 模板异常会员迁移
    * @author 高国藩
    * @date 2016年3月2日 下午2:10:21
    * @param storeId         门店
    * @param lastOperatorId  最后操作人
    * @param id              id
    * @param memberId        会员ID
    * @return                状态
     */
    private BaseDto balandMemberMoveMb(Integer storeId, Integer lastOperatorId,
            Integer id, Integer memberId) {
        MemberErrorMb errorMb = memberErrorMbMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorMb.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        if (errorMb.getBalanceAmount().intValue()>0){
            changeMoneyFlow(memberInfo.getMemberId(), errorMb.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        }
        if (errorMb.getBalanceIntegral().intValue()>0){
            changeIntegralFlow(memberInfo, memberAccount, errorMb.getBalanceIntegral().intValue(), 
                    App.Member.MOVE_MONEY_DECS, 8, storeId, 0, lastOperatorId);
        }
        if (errorMb.getBalanceGiftmoneyAmount().intValue()>0){
            changeGiftMoneyFlow(memberInfo, memberAccount, errorMb.getBalanceGiftmoneyAmount(), App.Member.IMPORT_MONEY_DECS, 8, lastOperatorId);
        }
        errorMb.setUpdateTime(DateUtil.getCurDate());
        errorMb.setLastOperatorId(lastOperatorId);
        errorMb.setIsDeleted(1);
        memberErrorMbMapper.updateByPrimaryKeySelective(errorMb);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     * 共赢异常会员迁移
    * @author 高国藩
    * @date 2016年3月2日 下午2:10:21
    * @param storeId         门店
    * @param lastOperatorId  最后操作人
    * @param id              id
    * @param memberId        会员ID
    * @return                状态
     */
    private BaseDto balandMemberMoveGi(Integer storeId, Integer lastOperatorId,
            Integer id, Integer memberId) {
        MemberErrorGi errorGi = memberErrorGiMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorGi.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo.getMemberId(), errorGi.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        errorGi.setUpdateTime(DateUtil.getCurDate());
        errorGi.setLastOperatorId(lastOperatorId);
        errorGi.setIsDeleted(1);
        memberErrorGiMapper.updateByPrimaryKeySelective(errorGi);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     * 蓝蝶数据迁移
    * @author 高国藩
    * @date 2016年2月22日 下午4:10:05
    * @param storeId             门店信息
    * @param lastOperatorId      最后操作人
    * @param id                  id
    * @param memberId            会员ID
    * @return                    状态
     */
    private BaseDto balandMemberMoveLd(Integer storeId, Integer lastOperatorId,
            Integer id, Integer memberId) {
        MemberErrorLd errorLd = memberErrorLdMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorLd.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo.getMemberId(), errorLd.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        errorLd.setUpdateTime(DateUtil.getCurDate());
        errorLd.setLastOperatorId(lastOperatorId);
        errorLd.setIsDeleted(1);
        memberErrorLdMapper.updateByPrimaryKeySelective(errorLd);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     * 耕宇会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    private BaseDto balandMemberMoveGy(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId) {
        MemberErrorGy errorGy = memberErrorGyMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorGy.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo.getMemberId(), errorGy.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        errorGy.setUpdateTime(DateUtil.getCurDate());
        errorGy.setLastOperatorId(lastOperatorId);
        errorGy.setIsDeleted(1);
        memberErrorGyMapper.updateByPrimaryKeySelective(errorGy);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     * 盛传会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    public BaseDto balandMemberMoveSc(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId){
        MemberErrorSc errorSc = memberErrorScMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorSc.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccount.setBalanceIntegral(memberAccount.getBalanceIntegral()+errorSc.getBalanceIntegral());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo.getMemberId(), errorSc.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        changeIntegralFlow(memberInfo, memberAccount, errorSc.getBalanceIntegral(), App.Member.MOVE_MONEY_DECS, 8, storeId, 0, lastOperatorId);
        errorSc.setUpdateTime(DateUtil.getCurDate());
        errorSc.setLastOperatorId(lastOperatorId);
        errorSc.setIsDeleted(1);
        memberErrorScMapper.updateByPrimaryKeySelective(errorSc);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }
    
    /**
     * 左右会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    public BaseDto balandMemberMoveZy(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId){
        MemberErrorZy errorZy = memberErrorZyMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorZy.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo.getMemberId(), errorZy.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        errorZy.setUpdateTime(DateUtil.getCurDate());
        errorZy.setLastOperatorId(lastOperatorId);
        errorZy.setIsDeleted(1);
        memberErrorZyMapper.updateByPrimaryKeySelective(errorZy);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }
    
    /**
     * 企汇通会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    public BaseDto balandMemberMoveHt(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId){
        MemberErrorHt errorHt = memberErrorHtMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorHt.getBalanceAmount()));
        memberAccount.setBalanceIntegral(memberAccount.getBalanceIntegral()+errorHt.getBalanceIntegral().intValue());
        memberAccount.setBalanceGiftmoneyAmount(memberAccount.getBalanceGiftmoneyAmount().add(errorHt.getBalanceGiftmoneyAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo.getMemberId(), errorHt.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        changeIntegralFlow(memberInfo, memberAccount, errorHt.getBalanceIntegral().intValue(), 
                App.Member.MOVE_MONEY_DECS, 8, storeId, 0, lastOperatorId);
        changeGiftMoneyFlow(memberInfo, memberAccount, errorHt.getBalanceGiftmoneyAmount(), App.Member.IMPORT_MONEY_DECS, 8, lastOperatorId);
        errorHt.setUpdateTime(DateUtil.getCurDate());
        errorHt.setLastOperatorId(lastOperatorId);
        errorHt.setIsDeleted(1);
        memberErrorHtMapper.updateByPrimaryKeySelective(errorHt);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }
    
    /**
     * 博卡会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    public BaseDto balandMemberMoveBk(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId){
        MemberErrorBk errorBk = memberErrorBkMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorBk.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        wipeCache(memberId);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo.getMemberId(), errorBk.getBalanceAmount(), App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        errorBk.setUpdateTime(DateUtil.getCurDate());
        errorBk.setLastOperatorId(lastOperatorId);
        errorBk.setIsDeleted(1);
        memberErrorBkMapper.updateByPrimaryKeySelective(errorBk);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     *  异常会员疗程迁移
    * @author 高国藩
    * @date 2015年12月19日 下午1:47:11
    * @param storeId          门店信息
    * @param storeAccount               门店
    * @param lastOperatorId   最后操作人
    * @param type             类型(1:盛传 2:左右 3:企汇通 4:博卡)
    * @param jsonArray        数据集合
    * @return                 状态
     */
    public BaseDto comboMemberMoveAll(Integer storeId, String storeAccount, Integer lastOperatorId, Integer type, JSONArray jsonArray) {
        switch(type){
            case 1: return comboMemberMoveScAll(storeId, storeAccount, lastOperatorId, type, jsonArray);
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "迁移失败,请稍后重试");
    }
    
    /**
     * 异常会员疗程迁移
    * @author 高国藩
    * @date 2016年3月14日 下午3:43:13
    * @param storeId storeId 
    * @param lastOperatorId lastOperatorId
    * @param type type
    * @param id   id
    * @param memberId memberId
    * @param comboId comboId
    * @param overdueTime overdueTime
    * @param projectCount projectCount
    * @param moveType moveType
    * @return  baseDto
     */
    public BaseDto comboMemberMove(Integer storeId, Integer lastOperatorId,
            Integer type, Integer id, Integer memberId, Integer comboId,
            String overdueTime, Integer[] projectCount, Integer moveType) {
        switch(type){
            case 1: return comboMemberMoveSc(storeId, lastOperatorId, type, id, memberId, comboId, overdueTime, projectCount, moveType);
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "迁移失败,请稍后重试");
    }


    /**
     *  盛传异常会员疗程迁移
    * @author 高国藩
    * @date 2015年12月19日 下午1:47:11
    * @param storeId          门店信息
    * @param storeAccount    企业代号
    * @param lastOperatorId   最后操作人
    * @param type             类型(1:盛传 2:左右 3:企汇通 4:博卡)
    * @param jsonArray        jsonArray
    * @return                 状态
     */
    public BaseDto comboMemberMoveScAll(Integer storeId, String storeAccount, Integer lastOperatorId,  Integer type, JSONArray jsonArray) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String name = object.get("name").toString();
            Integer comboId = Integer.valueOf(object.getString("comboId"));
            map.put(name, comboId);
        }
        List<RumorsCourse> rumorsCourses = rumorsCourseMapper.selectAllByStoreId(storeId);
        for (int i = 0; i < rumorsCourses.size(); i++) {
            RumorsCourse rumorsCourse = rumorsCourses.get(i);
            String projectName = rumorsCourse.getCourseDesc().split(",")[1].split(":")[1];
            Integer comboId = map.get(projectName);
            ComboInfo comboInfo = comboInfoService.queryComboInfoById(comboId);
            List<ComboProject> comboProjectList = comboInfoService.queryComboProject(comboId);
            MemberInfo memberInfo = memberInfoMapper.selectMemberByStoreIdAndPhone(storeId, rumorsCourse.getPhone());
            if (memberInfo!=null){
                saveMemberComboInfo(comboInfo, comboProjectList, rumorsCourse, memberInfo, storeId, lastOperatorId);
            }
            else {
                MemberInfo info = new MemberInfo();
                info.setStoreId(storeId);
                info.setName(rumorsCourse.getName());
                info.setPhone(rumorsCourse.getPhone());
                info.setIsDeleted(0);
                info.setLastOperatorId(lastOperatorId);
                MemberLevel memberLevel = memberLevelMapper.selectMemberLevelBySotreIdAndLevelName(storeAccount, rumorsCourse.getLevelName());
                if (memberLevel!=null){
                    info.setLevelId(memberLevel.getLevelId());
                }
                memberInfoMapper.insert(info);
                MemberAccount memberAccount = new MemberAccount();
                memberAccount.setAccountId(info.getMemberId());
                memberAccount.setBalanceAmount(rumorsCourse.getResidueAmount());
                memberAccountMapper.insert(memberAccount);
                if (memberAccount.getBalanceAmount().intValue()>0){
                    changeMoneyFlow(info.getMemberId(), memberAccount.getBalanceAmount(), 
                            App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
                } 
                saveMemberComboInfo(comboInfo, comboProjectList, rumorsCourse, info, storeId, lastOperatorId);
            }
            rumorsCourse.setUpdateTime(DateUtil.getCurDate());
            rumorsCourse.setLastOperatorId(lastOperatorId);
            rumorsCourse.setIsDeleted(1);
            rumorsCourseMapper.updateByPrimaryKeySelective(rumorsCourse);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
        
    }

    /**
     * 盛传异常会员疗程迁移
    * @author 高国藩
    * @date 2016年3月14日 下午3:47:48
    * @param storeId storeId
    * @param lastOperatorId storeId
    * @param type storeId
    * @param id storeId
    * @param memberId storeId
    * @param comboId storeId
    * @param overdueTime storeId
    * @param projectCount storeId
    * @param moveType storeId
    * @return storeId
     */
    public BaseDto comboMemberMoveSc(Integer storeId, Integer lastOperatorId,
            Integer type, Integer id, Integer memberId, Integer comboId,
            String overdueTime, Integer[] projectCount, Integer moveType) {
        ComboInfo comboInfo = comboInfoService.queryComboInfoById(comboId);
        List<ComboProject> comboProjectList = comboInfoService.queryComboProject(comboId);
        
        for (int i = 0; i < projectCount.length; i++) {
            
            MemberComboRecord memberComboRecord = new MemberComboRecord();
            memberComboRecord.setMemberId(memberId);
            memberComboRecord.setStoreId(storeId);
            memberComboRecord.setComboId(comboId);
            memberComboRecord.setComboName(comboInfo.getComboName());
            memberComboRecord.setComboPrice(comboInfo.getComboSalePrice());
            memberComboRecord.setProjectAmount(comboProjectList.get(i).getProjectPrice());
            memberComboRecord.setRemainingCount(projectCount[i]);
            memberComboRecord.setOverdueTime(overdueTime);
            memberComboRecord.setIsDeleted(0);
            memberComboRecord.setLastOperatorId(lastOperatorId);
            memberComboRecord.setProjectCount(comboProjectList.get(i).getProjectCount());
            memberComboRecord.setOverdueTime(DateUtil.getCurDate());
            memberComboRecord.setLastOperatorId(lastOperatorId);
            memberComboRecord.setCreateTime(DateUtil.getCurDate());
            memberComboRecordMapper.insert(memberComboRecord);
            
            MemberComboProject memberComboProject = new MemberComboProject();
            memberComboProject.setComboId(comboId);
            memberComboProject.setProjectId(comboProjectList.get(i).getProjectId());
            memberComboProject.setRecordId(memberComboRecord.getRecordId());
            memberComboProject.setProjectName(comboProjectList.get(i).getProjectName());
            memberComboProject.setProjectPrice(comboProjectList.get(i).getProjectPrice());
            memberComboProject.setProjectImage(comboProjectList.get(i).getProjectImage());
            memberComboProject.setProjectCount(projectCount[i]);
            memberComboProject.setRemainingCount(projectCount[i]);
            memberComboProject.setCreateTime(DateUtil.getCurDate());
            memberComboProject.setIsDeleted(0);
            memberComboProject.setLastOperatorId(lastOperatorId);
            memberComboProject.setCreateTime(DateUtil.getCurDate());
            memberComboProjectMapper.insert(memberComboProject);
            
        }
        if (moveType == 1){
            MemberErrorSc errorSc = memberErrorScMapper.selectByPrimaryKey(id);
            errorSc.setUpdateTime(DateUtil.getCurDate());
            errorSc.setLastOperatorId(lastOperatorId);
            errorSc.setIsDeleted(1);
            memberErrorScMapper.updateByPrimaryKeySelective(errorSc);
        }
        if (moveType == 2){
            RumorsCourse rumorsCourse = rumorsCourseMapper.selectByPrimaryKey(id);
            rumorsCourse.setUpdateTime(DateUtil.getCurDate());
            rumorsCourse.setLastOperatorId(lastOperatorId);
            rumorsCourse.setIsDeleted(1);
            rumorsCourseMapper.updateByPrimaryKeySelective(rumorsCourse);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
        
    }

    /**
     * 第三方
    * @author 高国藩
    * @date 2016年3月12日 下午5:45:04
    * @param comboInfo comboInfo
    * @param comboProjectList comboInfo
    * @param rumorsCourse comboInfo
    * @param memberInfo comboInfo
    * @param storeId comboInfo
    * @param lastOperatorId comboInfo
     */
    private void saveMemberComboInfo(ComboInfo comboInfo, List<ComboProject> comboProjectList, RumorsCourse rumorsCourse,
            MemberInfo memberInfo, Integer storeId, Integer lastOperatorId) {
        for (int i = 0; i < comboProjectList.size(); i++) {
            
            MemberComboRecord memberComboRecord = new MemberComboRecord();
            memberComboRecord.setMemberId(memberInfo.getMemberId());
            memberComboRecord.setStoreId(storeId);
            memberComboRecord.setComboId(comboInfo.getComboId());
            memberComboRecord.setComboName(comboInfo.getComboName());
            memberComboRecord.setComboPrice(comboInfo.getComboSalePrice());
            memberComboRecord.setProjectAmount(comboProjectList.get(i).getProjectPrice());
            memberComboRecord.setRemainingCount(rumorsCourse.getResidueDegree());
            memberComboRecord.setOverdueTime("永久");
            memberComboRecord.setIsDeleted(0);
            memberComboRecord.setLastOperatorId(lastOperatorId);
            memberComboRecord.setProjectCount(comboProjectList.get(i).getProjectCount());
            memberComboRecord.setOverdueTime(DateUtil.getCurDate());
            memberComboRecord.setLastOperatorId(lastOperatorId);
            memberComboRecord.setCreateTime(DateUtil.getCurDate());
            memberComboRecordMapper.insert(memberComboRecord);
            
            MemberComboProject memberComboProject = new MemberComboProject();
            memberComboProject.setComboId(comboInfo.getComboId());
            memberComboProject.setProjectId(comboProjectList.get(i).getProjectId());
            memberComboProject.setRecordId(memberComboRecord.getRecordId());
            memberComboProject.setProjectName(comboProjectList.get(i).getProjectName());
            memberComboProject.setProjectPrice(comboProjectList.get(i).getProjectPrice());
            memberComboProject.setProjectImage(comboProjectList.get(i).getProjectImage());
            memberComboProject.setProjectCount(rumorsCourse.getResidueDegree());
            memberComboProject.setRemainingCount(rumorsCourse.getResidueDegree());
            memberComboProject.setCreateTime(DateUtil.getCurDate());
            memberComboProject.setIsDeleted(0);
            memberComboProject.setLastOperatorId(lastOperatorId);
            memberComboProject.setCreateTime(DateUtil.getCurDate());
            memberComboProjectMapper.insert(memberComboProject);
            
        }
        
    }


    /**
     * 删除会员数据
    * @author 高国藩
    * @date 2015年12月25日 下午8:34:42
    * @param memberId   会员ID
    * @param userId     当前操作人员
    * @return           页面数据
     */
    public BaseDto deleteMemberAction(Integer memberId, Integer userId) {
        List<OrderInfo> orderInfos =  orderInfoMapper.selectIsCanDeletedMember(memberId);
        if (orderInfos!=null && orderInfos.size()>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该会员存在未结账订单,不可删除");
        }
        else {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setMemberId(memberId);
            memberInfo.setIsDeleted(1);
            memberInfo.setLastOperatorId(userId);
            memberInfo.setUpdateTime(DateUtil.getCurDate());
            memberInfoMapper.updateByPrimaryKey(memberInfo);
            String openId = wechatMemberMapper.selectOpenIdsByMemberId(memberId);
            if (!StringUtils.isEmpty(openId)){
                microChannelBind(openId, memberId);
            }
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "删除成功");
        }
    }
    
    /**
     * 删除会员和微信的绑定关系
    * @author 高国藩
    * @date 2015年12月25日 下午9:02:10
    * @param openId       openId
    * @param memberId     memberId
     */
    public void microChannelBind(String openId, Integer memberId){
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId);
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_STORE_KEY_HASH, openId);
        redisService.hdel(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
        wechatMemberMapper.deleteByPrimaryKey(openId);
    }


    /**
     * 耕宇会员数据导入
    * @author 高国藩
    * @date 2016年1月13日 上午9:58:14
    * @param file               上传文件
    * @param storeId            门店ID
    * @param storeAccount    企业代号
    * @param lastOperatorId     最后操作人
    * @param response           返回
    * @param storeName          原系统名称
    * @return                   导入状态
    * @throws IOException       抛出io读写异常 
     */
    public BaseDto importExcleGy(MultipartFile file, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response,
            String storeName) throws IOException {
        updateStoreSet(storeId, storeName);
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return null;
        }
        boolean isE2007 = false;
        if (name.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = file.getInputStream();
        Workbook wb = null;
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        } 
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        // 会员等级和名称对应,方便取值
        Map<String, Integer> levelMap = packLevelMap(storeId);
        // 将其中的疗程的行数保存下载,用于生成error数据,提供查询
        List<Integer> rowNums = new ArrayList<>();
        List<TempTableSc> tableScs = new ArrayList<>();
        a: for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                MemberInfo memberInfo = new MemberInfo();
                MemberAccount memberAccount = new MemberAccount();
                TempTableSc tableSc = new TempTableSc();
                b: for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    try {
                        Cell cell = xssfRow.getCell(cellNum);
                        String str = ExcleUtils.changeCellToString(cell);
                        // 先封装一层会员数据
                        if (cellNum == 5) {
                            if (hasStr.contains(str)) {
                                rowNums.add(rowNum);
                                log.info(str + " 已存在该手机号码");
                                continue a;
                            } 
                            else {
                                Pattern p1 = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");  
                                Matcher m = p1.matcher(str); 
                                if (m.matches()){
                                    hasStr.add(str);
                                    tableSc.setPhone(str);
                                    memberInfo.setPhone(str);
                                }
                                else {
                                    rowNums.add(rowNum);
                                    log.info(str +" 该手机号码不合法");
                                    continue a;
                                }
                            }
                        }
                        if (cellNum == 3) {
                            tableSc.setName(str);
                            memberInfo.setName(str);
                        }
                        if (cellNum == 4) {
                            if ("女士".equals(str)){
                                tableSc.setSex("女");
                                memberInfo.setSex("女");
                            }
                            else {
                                tableSc.setSex("男");
                                memberInfo.setSex("男");
                            }
                            
                        }
                        if (cellNum == 2) {
                            tableSc.setLevelName(str);
                        }
                        tableSc.setStoreId(storeId);
                        tableSc.setCreateTime(DateUtil.getCurDate());
                        
                        memberInfo.setStoreId(storeId);
                        memberInfo.setCreateTime(DateUtil.getCurDate());
                        memberInfo.setIsDeleted(0);
                        memberInfo.setLastOperatorId(lastOperatorId);
                        //再封装一层会员消费数据
                        if (cellNum == 6) {
                            tableSc.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                            memberAccount.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        if (cellNum == 11) {
                            tableSc.setBalanceIntegral(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setBalanceIntegral(Integer.parseInt(ExcleUtils.changeValue(str)));
                        }
                        if (cellNum == 15) {
                            tableSc.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                        }
                        if (cellNum == 21) {
                            tableSc.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                            memberAccount.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        if (cellNum == 23) {
                            tableSc.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                            memberAccount.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        if (cellNum == 24) {
                            tableSc.setConsumeCount(Integer.parseInt(ExcleUtils.changeValue(str)));
                            memberAccount.setConsumeCount(Integer.parseInt(ExcleUtils.changeValue(str)));
                        }
                        if (cellNum == 25) {
                            tableSc.setAvgConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                            memberAccount.setAvgConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        memberAccount.setLastOperatorId(lastOperatorId);
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                        rowNums.add(rowNum);
                        log.info(" 疗程卡数据,存入异常数据中 ");
                        continue a;
                    }
                }
                memberInfos.add(memberInfo);
                accounts.add(memberAccount);
                tableScs.add(tableSc);
            }
        }
        // 将疗程类型的数据输出为异常会员数据
        if (rowNums.size()>0){
            insertErrorGy(sheet, rowNums, storeId, lastOperatorId);
        }
        // 插入数据
        Set<String> levels = new HashSet<>();
        for (int i = 0; i < tableScs.size(); i++) {
            if (levelMap.get(tableScs.get(i).getLevelName())==null){
                levels.add(tableScs.get(i).getLevelName());
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        //先插入会员卡等级数据
        for (String levelName : levels) {
            saveEnterpriseMemberLevel(lastOperatorId, levelName, storeAccount, storeId);
//            MemberLevel level1 = new MemberLevel();
//            level1.setLevelName(level);
//            level1.setStoreId(hsqStoreId);
//            level1.setIsDeleted(0);
//            memberLevelMapper.insert(level1);
        }
        levelMap = packLevelMap(hsqStoreId);
        for (int k = 0; k < tableScs.size(); k++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(tableScs.get(k).getName());
            memberInfo.setSex(tableScs.get(k).getSex());
            memberInfo.setPhone(tableScs.get(k).getPhone());
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(tableScs.get(k).getLevelName()));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(tableScs.get(k).getBalanceAmount());
            account.setBalanceIntegral(tableScs.get(k).getBalanceIntegral().intValue());
            account.setTotalAmount(tableScs.get(k).getTotalAmount());
            account.setDebtAmount(tableScs.get(k).getDebtAmount());
            account.setConsumeCount(tableScs.get(k).getConsumeCount());
            account.setTotalConsumeAmount(tableScs.get(k).getTotalConsumeAmount());
            account.setAvgConsumeAmount(tableScs.get(k).getAvgConsumeAmount());
            account.setLastOperatorId(lastOperatorId);
            memberAccountMapper.insert(account);
            
            if (account.getBalanceAmount().intValue()>0){
                //余额流水变动
                changeMoneyFlow(memberInfo.getMemberId(), account.getBalanceAmount(), App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
            if (account.getBalanceIntegral()>0){
                //积分流水变动
                changeIntegralFlow(memberInfo, account, account.getBalanceIntegral(), App.Member.IMPORT_MONEY_DECS, 7, storeId, 0, lastOperatorId);
            }
            if (account.getDebtAmount().intValue()>0){
                //欠款流水变动
                insertDebtFlow(memberInfo.getMemberId(), null, account.getDebtAmount(), App.Member.IMPORT_MONEY_DECS, 1, 
                        lastOperatorId, DateUtil.getCurDate());
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }

    /**
     * 耕宇异常会员数据插入
    * @author 高国藩
    * @date 2016年1月13日 上午10:00:34
    * @param sheet                   表格
    * @param rowNums                 异常xls行数
    * @param storeId                 门店ID
    * @param lastOperatorId          最后操作人
     */
    private void insertErrorGy(Sheet sheet, List<Integer> rowNums, Integer storeId, Integer lastOperatorId) {
        List<MemberErrorGy> errorGys = new ArrayList<>();
        a: for (int i = 0; i < rowNums.size(); i++) {
            MemberErrorGy memberErrorGy = new MemberErrorGy();
            Row xssfRow = sheet.getRow(rowNums.get(i));
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                Cell cell = xssfRow.getCell(cellNum);
                String str = ExcleUtils.changeCellToString(cell);
                // 先封装一层会员数据
                if (cellNum == 1) {
                    memberErrorGy.setLevelNum(str);
                }
                if (cellNum == 2) {
                    memberErrorGy.setLevelName(str);
                }
                if (cellNum == 3) {
                    memberErrorGy.setName(str);
                }
                if (cellNum == 4) {
                    if ("女士".equals(str)){
                        memberErrorGy.setSex("女");
                    }
                    else {
                        memberErrorGy.setSex("男");
                    }
                }
                if (cellNum == 5) {
                    memberErrorGy.setPhone(str);
                }
                if (cellNum == 9) {
                    memberErrorGy.setDiscount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 6) {
                    memberErrorGy.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (cellNum == 11) {
                    memberErrorGy.setBalanceIntegral(Integer.parseInt(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 15) {
                    memberErrorGy.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 21) {
                    memberErrorGy.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (cellNum == 23) {
                    memberErrorGy.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (cellNum == 24) {
                    memberErrorGy.setConsumeCount(Integer.parseInt(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 25) {
                    memberErrorGy.setAvgConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
            memberErrorGy.setStoreId(storeId);
            memberErrorGy.setIsDeleted(0);
            memberErrorGy.setLastOperatorId(lastOperatorId);
            errorGys.add(memberErrorGy);
        }
        memberErrorGyMapper.insertList(errorGys);
    }


    /**
     * 蓝蝶会员数据导入
    * @author 高国藩
    * @date 2016年2月20日 下午3:12:53
    * @param storeId             门店
    * @param storeAccount    企业代号
    * @param lastOperatorId      最后操作人
    * @param response            结果街
    * @param storeName           系统名称
    * @param cookieStore         session信息
    * @return                    结果状态
     */
    public BaseDto importExcleLd(Integer storeId, String storeAccount, Integer lastOperatorId,
            HttpServletResponse response, String storeName, BasicCookieStore cookieStore) {
        updateStoreSet(storeId, storeName);
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        List<MemberErrorLd> errorLds = new ArrayList<>();
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        String customerUrl = "http://vip.landee.com.cn/Customer/Customer/GetList";
        String cardUrl = "http://vip.landee.com.cn/Item/CreditCard/GetList";
        String getCardNameUrl = "http://vip.landee.com.cn/Customer/Customer/CustomerCards";
        Map<String, String> params2 = new HashMap<String, String>();
        params2.put("removed", "0");
        params2.put("_search", "false");
        params2.put("nd", "1455955410984");
        params2.put("rows", "50");
        params2.put("page", "1");
        params2.put("sidx", "CreateDate");
        params2.put("sord", "desc");
        String json5 = HttpClientUtil.sendPostReq(cardUrl, params2, "UTF-8", cookieStore);
        
        JSONArray ad = (JSONArray) JSONObject.fromObject(json5).get("rows");
        Map<String, Integer> levelMap = packLevelMap(storeId);
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        for (int i = 0; i < ad.size(); i++) {
            String memberLevelName = JSONObject.fromObject(ad.get(i)).get("CardName").toString();
            if (levelMap.get(memberLevelName)==null){
                saveEnterpriseMemberLevel (lastOperatorId, memberLevelName, storeAccount, storeId);
//                MemberLevel record = new MemberLevel();
//                record.setStoreId(hsqStoreId);
//                record.setIsDeleted(0);
//                record.setLevelName(memberLevelName);
//                memberLevelMapper.insert(record);
            }
        }
        levelMap = packLevelMap(storeId);
        Map<String, String> params = new HashMap<String, String>();
        params.put("shop", "101");
        params.put("isChineseCalendar", "0");
        params.put("removed", "0");
        params.put("isfrozen", "0");
        params.put("_search", "false");
        params.put("nd", "1455941663594");
        params.put("rows", "500");
        params.put("sidx", "UpdateDate");
        params.put("sord", "desc");
        params.put("page", "1");
        String json1 = HttpClientUtil.sendPostReq(customerUrl, params, "UTF-8", cookieStore);
        params.put("page", "2");
        String json2 = HttpClientUtil.sendPostReq(customerUrl, params, "UTF-8", cookieStore);
        params.put("page", "3");
        String json3 = HttpClientUtil.sendPostReq(customerUrl, params, "UTF-8", cookieStore);
        params.put("page", "4");
        String json4 = HttpClientUtil.sendPostReq(customerUrl, params, "UTF-8", cookieStore);
        JSONArray ja = (JSONArray) JSONObject.fromObject(json1).get("rows");
        ja.addAll(JSONArray.fromObject(JSONObject.fromObject(json2).get("rows")));
        ja.addAll(JSONArray.fromObject(JSONObject.fromObject(json3).get("rows")));
        ja.addAll(JSONArray.fromObject(JSONObject.fromObject(json4).get("rows")));
        for (int i = 0; i < ja.size(); i++) {
            String memberName = null;
            String sex = null;
            String phone = null;
            String balanceAmount = null;
            String pointsBalance = null;
            String clientId = null;
            String levelName = null;
            String cardFaceId = null;
            String debtAmount = null;
            memberName = JSONObject.fromObject(ja.get(i)).get("ClientName").toString();
            sex = JSONObject.fromObject(ja.get(i)).get("Sex").toString();
            phone = JSONObject.fromObject(ja.get(i)).get("TelMobile").toString();
            balanceAmount = JSONObject.fromObject(ja.get(i)).get("CreditCardBalance").toString();
            pointsBalance = JSONObject.fromObject(ja.get(i)).get("PointsBalance").toString();
            clientId = JSONObject.fromObject(ja.get(i)).get("ClientId").toString();
            cardFaceId = JSONObject.fromObject(ja.get(i)).get("CardFaceId").toString();
            debtAmount = JSONObject.fromObject(ja.get(i)).get("CreditCardRecoverArrearsBalance").toString();
            if (debtAmount.indexOf("-")!=-1){
                debtAmount = debtAmount.substring(1, debtAmount.length());
            }
            Map<String, String> params3 = new HashMap<String, String>();
            params3.put("customerId", clientId);
            try {
                levelName = (String) JSONObject.fromObject(JSONArray.fromObject(HttpClientUtil.
                        sendPostReq(getCardNameUrl, params3, "UTF-8", cookieStore)).get(0)).get("PackageCardName");
            } 
            catch (Exception e) {
                MemberErrorLd errorLd = new MemberErrorLd();
                errorLd.setName(memberName);
                errorLd.setSex(sex);
                errorLd.setPhone(phone);
                errorLd.setIsDeleted(0);
                errorLd.setStoreId(storeId);
                errorLd.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                errorLd.setBalanceIntegral(new BigDecimal(ExcleUtils.changeValue(pointsBalance)));
                errorLd.setLastOperatorId(lastOperatorId);
                errorLd.setLevelNum(cardFaceId);
                errorLd.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(debtAmount)));
                errorLds.add(errorLd);
                continue;
            }
            Pattern p1 = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");  
            Matcher m = p1.matcher(phone);
            if (hasStr.contains(phone)){
                log.info("重复手机号");
                MemberErrorLd errorLd = new MemberErrorLd();
                errorLd.setName(memberName);
                errorLd.setSex(sex);
                errorLd.setPhone(phone);
                errorLd.setIsDeleted(0);
                errorLd.setStoreId(storeId);
                errorLd.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                errorLd.setBalanceIntegral(new BigDecimal(ExcleUtils.changeValue(pointsBalance)));
                errorLd.setLastOperatorId(lastOperatorId);
                errorLd.setLevelNum(cardFaceId);
                errorLd.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(debtAmount)));
                errorLds.add(errorLd);
                continue;
            }
            if (!m.matches()){
                log.info("异常手机号");
                MemberErrorLd errorLd = new MemberErrorLd();
                errorLd.setName(memberName);
                errorLd.setSex(sex);
                errorLd.setPhone(phone);
                errorLd.setIsDeleted(0);
                errorLd.setStoreId(storeId);
                errorLd.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                errorLd.setBalanceIntegral(new BigDecimal(ExcleUtils.changeValue(pointsBalance)));
                errorLd.setLastOperatorId(lastOperatorId);
                errorLd.setLevelNum(cardFaceId);
                errorLd.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(debtAmount)));
                errorLds.add(errorLd);
                continue;
            }
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(memberName);
            memberInfo.setSex(sex);
            memberInfo.setPhone(phone);
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(levelName));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
            account.setBalanceIntegral(Integer.valueOf(new BigDecimal(ExcleUtils.changeValue(pointsBalance)).intValue()));
            account.setTotalAmount(new BigDecimal(balanceAmount));
            account.setLastOperatorId(lastOperatorId);
            account.setDebtAmount(new BigDecimal(debtAmount));
            memberAccountMapper.insert(account);
            if (account.getBalanceAmount().intValue()>0){
                //余额流水变动
                changeMoneyFlow(memberInfo.getMemberId(), account.getBalanceAmount(), App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
            if (account.getBalanceIntegral()>0){
                //积分流水变动
                changeIntegralFlow(memberInfo, account, account.getBalanceIntegral(), App.Member.IMPORT_MONEY_DECS, 7, storeId, 0, lastOperatorId);
            }
            if (account.getDebtAmount().intValue()>0){
                //欠款流水变动
                insertDebtFlow(memberInfo.getMemberId(), null, account.getDebtAmount(), App.Member.IMPORT_MONEY_DECS, 1, 
                        lastOperatorId, DateUtil.getCurDate());
            }
            hasStr.add(phone);
        }
        if (errorLds.size()>0){
            memberErrorLdMapper.insertList(errorLds);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }

    /**
     * 共赢会员数据导入
    * @author 高国藩
    * @date 2016年3月2日 下午2:13:49
    * @param file              文件
    * @param storeId           门店
    * @param storeAccount    企业代号
    * @param lastOperatorId    最后操作人
    * @param response          结果流
    * @param storeName         门店名称
    * @return                  状态
    * @throws IOException      异常
     */
    public BaseDto importExcleGi(MultipartFile file, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response, String storeName) throws IOException {
        updateStoreSet(storeId, storeName);
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return null;
        }
        boolean isE2007 = false;
        if (name.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = file.getInputStream();
        Workbook wb = null;
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        } 
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        // 会员等级和名称对应,方便取值
        Map<String, Integer> levelMap = packLevelMap(storeId);
        // 将其中的疗程的行数保存下载,用于生成error数据,提供查询
        List<Integer> rowNums = new ArrayList<>();
        List<TempTableSc> tableScs = new ArrayList<>();
        a: for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                MemberInfo memberInfo = new MemberInfo();
                MemberAccount memberAccount = new MemberAccount();
                TempTableSc tableSc = new TempTableSc();
                b: for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    try {
                        Cell cell = xssfRow.getCell(cellNum);
                        String str = ExcleUtils.changeCellToString(cell);
                        // 先封装一层会员数据
                        if (cellNum == 3) {
                            if (hasStr.contains(str)) {
                                rowNums.add(rowNum);
                                log.info(str + " 已存在该手机号码");
                                continue a;
                            } 
                            else {
                                Pattern p1 = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");  
                                Matcher m = p1.matcher(str); 
                                if (m.matches()){
                                    hasStr.add(str);
                                    tableSc.setPhone(str);
                                    memberInfo.setPhone(str);
                                }
                                else {
                                    rowNums.add(rowNum);
                                    log.info(str +" 该手机号码不合法");
                                    continue a;
                                }
                            }
                        }
                        if (cellNum == 2) {
                            tableSc.setName(str.split("-")[0]);
                            memberInfo.setName(str.split("-")[0]);
                            tableSc.setSex(str.split("-")[1]);
                            memberInfo.setSex(str.split("-")[1]);
                        }
                        if (cellNum == 4) {
                            tableSc.setLevelName(str.split("-")[0]);
                        }
                        tableSc.setStoreId(storeId);
                        tableSc.setCreateTime(DateUtil.getCurDate());
                        
                        memberInfo.setStoreId(storeId);
                        memberInfo.setCreateTime(DateUtil.getCurDate());
                        memberInfo.setIsDeleted(0);
                        memberInfo.setLastOperatorId(lastOperatorId);
                        //再封装一层会员消费数据
                        if (cellNum == 5) {
                            tableSc.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                            memberAccount.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        
                        if (cellNum == 6) {
                            
                            tableSc.setBalanceAmount(new BigDecimal(str.split(" ")[0].
                                    substring(0, str.split(" ")[0].indexOf("("))));
                            memberAccount.setBalanceAmount(new BigDecimal(str.split(" ")[0].
                                    substring(0, str.split(" ")[0].indexOf("("))));
                            tableSc.setBalanceGiftmoneyAmount(new BigDecimal(str.split(" ")[1].
                                    substring(0, str.split(" ")[1].indexOf("("))));
                            memberAccount.setBalanceGiftmoneyAmount(new BigDecimal(str.split(" ")[1].
                                    substring(0, str.split(" ")[1].indexOf("("))));
                        }
                        memberAccount.setLastOperatorId(lastOperatorId);
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                        rowNums.add(rowNum);
                        log.info(" 疗程卡数据,存入异常数据中 ");
                        continue a;
                    }
                }
                memberInfos.add(memberInfo);
                accounts.add(memberAccount);
                tableScs.add(tableSc);
            }
        }
        // 异常会员数据
        if (rowNums.size()>0){
            insertErrorGi(sheet, rowNums, storeId, lastOperatorId);
        }
        // 插入数据
        Set<String> levels = new HashSet<>();
        for (int i = 0; i < tableScs.size(); i++) {
            if (levelMap.get(tableScs.get(i).getLevelName())==null){
                levels.add(tableScs.get(i).getLevelName());
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        //先插入会员卡等级数据
        for (String level : levels) {
            saveEnterpriseMemberLevel (lastOperatorId, level, storeAccount, storeId);
//            MemberLevel level1 = new MemberLevel();
//            level1.setLevelName(level);
//            level1.setStoreId(hsqStoreId);
//            level1.setIsDeleted(0);
//            memberLevelMapper.insert(level1);
        }
        levelMap = packLevelMap(hsqStoreId);
        for (int k = 0; k < tableScs.size(); k++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(tableScs.get(k).getName());
            memberInfo.setSex(tableScs.get(k).getSex());
            memberInfo.setPhone(tableScs.get(k).getPhone());
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(tableScs.get(k).getLevelName()));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(tableScs.get(k).getBalanceAmount());
            account.setTotalConsumeAmount(tableScs.get(k).getTotalConsumeAmount());
            account.setBalanceGiftmoneyAmount(tableScs.get(k).getBalanceGiftmoneyAmount());
            account.setLastOperatorId(lastOperatorId);
            memberAccountMapper.insert(account);
            
            //余额流水变动
            if (account.getBalanceAmount().intValue()>0){
                changeMoneyFlow(memberInfo.getMemberId(), account.getBalanceAmount(), App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
            //礼金余额流水
            if (account.getBalanceGiftmoneyAmount().intValue() > 0){
                changeGiftMoneyFlow(memberInfo, account, account.getBalanceGiftmoneyAmount(), 
                        App.Member.IMPORT_MONEY_DECS, 7, lastOperatorId);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }


    /**
     * 共赢会员数据插入
    * @author 高国藩
    * @date 2016年3月2日 下午2:12:21
    * @param sheet             sheet
    * @param rowNums           rowNums
    * @param storeId           storeId
    * @param lastOperatorId    最后操作人
     */
    private void insertErrorGi(Sheet sheet, List<Integer> rowNums,
            Integer storeId, Integer lastOperatorId) {
        List<MemberErrorGi> errorGis = new ArrayList<>();
        a: for (int i = 0; i < rowNums.size(); i++) {
            MemberErrorGi memberErrorGi = new MemberErrorGi();
            Row xssfRow = sheet.getRow(rowNums.get(i));
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                Cell cell = xssfRow.getCell(cellNum);
                String str = ExcleUtils.changeCellToString(cell);
                if (cellNum == 1) {
                    memberErrorGi.setLevelNum(str);
                }
                if (cellNum == 2) {
                    memberErrorGi.setName(str.split("-")[0]);
                    memberErrorGi.setSex(str.split("-")[1]);
                }
                if (cellNum == 3) {
                    memberErrorGi.setPhone(str);
                }
                if (cellNum == 4) {
                    memberErrorGi.setLevelName(str.split("-")[0]);
                }
                if (cellNum == 5) {
                    memberErrorGi.setTotalConsumeAmount((new BigDecimal(ExcleUtils.changeValue(str))));
                }
                if (cellNum == 6) {
                    memberErrorGi.setBalanceAmount(new BigDecimal(str.split(" ")[0].
                            substring(0, str.split(" ")[0].indexOf("("))));
                    memberErrorGi.setBalanceGiftmoneyAmount(new BigDecimal(str.split(" ")[1].
                            substring(0, str.split(" ")[1].indexOf("("))));
                }
                if (cellNum == 9) {
                    memberErrorGi.setLastConsumeTime(str);
                }
            }
            memberErrorGi.setStoreId(storeId);
            memberErrorGi.setIsDeleted(0);
            memberErrorGi.setLastOperatorId(lastOperatorId);
            errorGis.add(memberErrorGi);
        }
        memberErrorGiMapper.insertList(errorGis);
    }


    /**
     * 模板会员数据导入
    * @author 高国藩
    * @date 2016年3月2日 下午2:13:49
    * @param file              文件
    * @param storeId           门店
    * @param storeAccount    企业代号
    * @param lastOperatorId    最后操作人
    * @param response          结果流
    * @param storeName         门店名称
    * @return                  状态
    * @throws IOException      异常
     */
    public BaseDto importExcleMb(MultipartFile file, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response,
            String storeName) throws IOException {
        updateStoreSet(storeId, storeName);
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return null;
        }
        boolean isE2007 = false;
        if (name.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = file.getInputStream();
        Workbook wb = null;
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        } 
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        // 会员等级和名称对应,方便取值
        Map<String, Integer> levelMap = packLevelMap(storeId);
        // 将其中的疗程的行数保存下载,用于生成error数据,提供查询
        List<Integer> rowNums = new ArrayList<>();
        List<TempTableSc> tableScs = new ArrayList<>();
        a: for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                MemberInfo memberInfo = new MemberInfo();
                MemberAccount memberAccount = new MemberAccount();
                TempTableSc tableSc = new TempTableSc();
                b: for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    try {
                        Cell cell = xssfRow.getCell(cellNum);
                        String str = ExcleUtils.changeCellToString(cell);
                        // 先封装一层会员数据
                        if (cellNum == 1) {
                            if (hasStr.contains(str)) {
                                rowNums.add(rowNum);
                                log.info(str + " 已存在该手机号码");
                                continue a;
                            } 
                            else {
                                Pattern p1 = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");
                                Matcher m = p1.matcher(str); 
                                if (m.matches()){
                                    hasStr.add(str);
                                    tableSc.setPhone(str);
                                    memberInfo.setPhone(str);
                                }
                                else {
                                    rowNums.add(rowNum);
                                    log.info(str +" 该手机号码不合法");
                                    continue a;
                                }
                            }
                        }
                        if (cellNum == 0) {
                            tableSc.setName(str);
                            memberInfo.setName(str);
                        }
                        if (cellNum == 2) {
                            tableSc.setSex(str);
                            memberInfo.setSex(str);
                        }
                        if (cellNum == 3) {
                            tableSc.setLevelName(str);
                        }
                        tableSc.setStoreId(storeId);
                        tableSc.setCreateTime(DateUtil.getCurDate());
                        
                        memberInfo.setStoreId(storeId);
                        memberInfo.setCreateTime(DateUtil.getCurDate());
                        memberInfo.setIsDeleted(0);
                        memberInfo.setLastOperatorId(lastOperatorId);
                        //再封装一层会员消费数据
                        if (cellNum == 5) {
                            tableSc.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                            memberAccount.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        if (cellNum == 6) {
                            tableSc.setBalanceIntegral(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setBalanceIntegral(new BigDecimal(ExcleUtils.changeValue(str)).intValue());
                        }
                        if (cellNum == 7) {
                            tableSc.setBalanceGiftmoneyAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setBalanceGiftmoneyAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                        }
                        if (cellNum == 8) {
                            tableSc.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                            memberAccount.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                        }
                        memberAccount.setLastOperatorId(lastOperatorId);
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                        rowNums.add(rowNum);
                        log.info(" 疗程卡数据,存入异常数据中 ");
                        continue a;
                    }
                }
                memberInfos.add(memberInfo);
                accounts.add(memberAccount);
                tableScs.add(tableSc);
            }
        }
        // 异常会员数据
        if (rowNums.size()>0){
            insertErrorMb(sheet, rowNums, storeId, lastOperatorId);
        }
        // 插入数据
        Set<String> levels = new HashSet<>();
        for (int i = 0; i < tableScs.size(); i++) {
            if (levelMap.get(tableScs.get(i).getLevelName())==null){
                levels.add(tableScs.get(i).getLevelName());
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        //先插入会员卡等级数据
        for (String levelName : levels) {
            saveEnterpriseMemberLevel(lastOperatorId, levelName, storeAccount, storeId);
//            MemberLevel level1 = new MemberLevel();
//            level1.setLevelName(level);
//            level1.setStoreId(hsqStoreId);
//            level1.setIsDeleted(0);
//            memberLevelMapper.insert(level1);
        }
        levelMap = packLevelMap(hsqStoreId);
        for (int k = 0; k < tableScs.size(); k++) {
            
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(tableScs.get(k).getName());
            memberInfo.setSex(tableScs.get(k).getSex());
            memberInfo.setPhone(tableScs.get(k).getPhone());
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(tableScs.get(k).getLevelName()));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(tableScs.get(k).getBalanceAmount());
            account.setBalanceIntegral(tableScs.get(k).getBalanceIntegral().intValue());
            account.setBalanceGiftmoneyAmount(tableScs.get(k).getBalanceGiftmoneyAmount());
            account.setDebtAmount(tableScs.get(k).getDebtAmount());
            account.setLastOperatorId(lastOperatorId);
            memberAccountMapper.insert(account);
            
            if (account.getBalanceAmount().intValue()>0){
                changeMoneyFlow(memberInfo.getMemberId(), account.getBalanceAmount(), App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
            if (account.getBalanceGiftmoneyAmount().intValue() > 0){
                changeGiftMoneyFlow(memberInfo, account, account.getBalanceGiftmoneyAmount(), App.Member.IMPORT_MONEY_DECS, 7, lastOperatorId);
            }
            if (account.getBalanceIntegral()>0){
                changeIntegralFlow(memberInfo, account, account.getBalanceIntegral(), App.Member.IMPORT_MONEY_DECS, 7, storeId, 0, lastOperatorId);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }


    /**
     * 模板会员数据插入
    * @author 高国藩
    * @date 2016年3月2日 下午2:12:21
    * @param sheet             sheet
    * @param rowNums           rowNums
    * @param storeId           storeId
    * @param lastOperatorId    最后操作人
     */
    private void insertErrorMb(Sheet sheet, List<Integer> rowNums, Integer storeId, Integer lastOperatorId) {
        List<MemberErrorMb> errorMbs = new ArrayList<>();
        a: for (int i = 0; i < rowNums.size(); i++) {
            MemberErrorMb memberErrorMb = new MemberErrorMb();
            Row xssfRow = sheet.getRow(rowNums.get(i));
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                Cell cell = xssfRow.getCell(cellNum);
                String str = ExcleUtils.changeCellToString(cell);
                if (cellNum == 0) {
                    memberErrorMb.setName(str);
                }
                if (cellNum == 1) {
                    memberErrorMb.setPhone(str);
                }
                if (cellNum == 2) {
                    memberErrorMb.setSex(str);
                }
                if (cellNum == 3) {
                    memberErrorMb.setLevelName(str);
                }
                if (cellNum == 4) {
                    memberErrorMb.setLevelNum(str);
                }
                if (cellNum == 5) {
                    memberErrorMb.setBalanceAmount((new BigDecimal(ExcleUtils.changeValue(str))));
                }
                if (cellNum == 6) {
                    memberErrorMb.setBalanceIntegral(new BigDecimal(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 7) {
                    memberErrorMb.setBalanceGiftmoneyAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
                if (cellNum == 8) {
                    memberErrorMb.setDebtAmount(new BigDecimal(ExcleUtils.changeValue(str)));
                }
            }
            memberErrorMb.setStoreId(storeId);
            memberErrorMb.setIsDeleted(0);
            memberErrorMb.setLastOperatorId(lastOperatorId);
            errorMbs.add(memberErrorMb);
        }
        memberErrorMbMapper.insertList(errorMbs);
    }


    /**
     * 西沙龙会员数据导入
    * @author 高国藩
    * @date 2016年3月2日 下午2:13:49
    * @param file              文件
    * @param storeId           门店
    * @param storeAccount    企业代号
    * @param lastOperatorId    最后操作人
    * @param response          结果流
    * @param storeName         门店名称
    * @return                  状态
    * @throws Exception      异常
     */
    public BaseDto importExcleXsl(MultipartFile file, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response,
            String storeName) throws Exception{
        updateStoreSet(storeId, storeName);
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return null;
        }
        boolean isE2007 = false;
        if (name.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = file.getInputStream();
        Workbook wb = null;
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        } 
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        // 会员等级和名称对应,方便取值
        Map<String, Integer> levelMap = packLevelMap(storeId);
        // 将其中的疗程的行数保存下载,用于生成error数据,提供查询
        List<Integer> rowNums = new ArrayList<>();
        List<TempTableSc> tableScs = new ArrayList<>();
        a: for (int rowNum = 4; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                MemberInfo memberInfo = new MemberInfo();
                MemberAccount memberAccount = new MemberAccount();
                TempTableSc tableSc = new TempTableSc();
                b: for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    try {
                        Cell cell = xssfRow.getCell(cellNum);
                        String str = ExcleUtils.changeCellToString(cell);
                        // 先封装一层会员数据
                        if (cellNum == 4) {
                            if (str.length()>3){
                                str = str.substring(0, str.length()-3);
                            }
                            else {
                                rowNums.add(rowNum);
                                continue a;
                            }
                            if (hasStr.contains(str)) {
                                rowNums.add(rowNum);
                                log.info(str + " 已存在该手机号码");
                                continue a;
                            } 
                            else {
                                Pattern p1 = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");
                                Matcher m = p1.matcher(str); 
                                if (m.matches()){
                                    hasStr.add(str);
                                    tableSc.setPhone(str);
                                    memberInfo.setPhone(str);
                                }
                                else {
                                    rowNums.add(rowNum);
                                    log.info(str +" 该手机号码不合法");
                                    continue a;
                                }
                            }
                        }
                        if (cellNum == 0) {
                            tableSc.setName(str);
                            memberInfo.setName(str);
                        }
                        if (cellNum == 1) {
                            tableSc.setSex(str);
                            memberInfo.setSex(str);
                        }
                        if (cellNum == 8) {
                            tableSc.setLevelName(str);
                        }
                        tableSc.setStoreId(storeId);
                        tableSc.setCreateTime(DateUtil.getCurDate());
                        
                        memberInfo.setStoreId(storeId);
                        memberInfo.setCreateTime(DateUtil.getCurDate());
                        memberInfo.setIsDeleted(0);
                        memberInfo.setLastOperatorId(lastOperatorId);
                        //再封装一层会员消费数据
                        if (cellNum == 12) {
                            tableSc.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                            memberAccount.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(str)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        memberAccount.setLastOperatorId(lastOperatorId);
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                        rowNums.add(rowNum);
                        log.info(" 疗程卡数据,存入异常数据中 ");
                        continue a;
                    }
                }
                memberInfos.add(memberInfo);
                accounts.add(memberAccount);
                tableScs.add(tableSc);
            }
        }
        // 异常会员数据
        if (rowNums.size()>0){
            insertErrorXsl(sheet, rowNums, storeId, lastOperatorId);
        }
        // 插入数据
        Set<String> levels = new HashSet<>();
        for (int i = 0; i < tableScs.size(); i++) {
            if (levelMap.get(tableScs.get(i).getLevelName())==null){
                levels.add(tableScs.get(i).getLevelName());
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        //先插入会员卡等级数据
        for (String level : levels) {
            saveEnterpriseMemberLevel(lastOperatorId, level, storeAccount, storeId);
//            MemberLevel level1 = new MemberLevel();
//            level1.setLevelName(level);
//            level1.setStoreId(hsqStoreId);
//            level1.setIsDeleted(0);
//            memberLevelMapper.insert(level1);
        }
        levelMap = packLevelMap(hsqStoreId);
        for (int k = 0; k < tableScs.size(); k++) {
            
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(tableScs.get(k).getName());
            memberInfo.setSex(tableScs.get(k).getSex());
            memberInfo.setPhone(tableScs.get(k).getPhone());
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(tableScs.get(k).getLevelName()));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(tableScs.get(k).getBalanceAmount());
            account.setLastOperatorId(lastOperatorId);
            memberAccountMapper.insert(account);
            
            if (account.getBalanceAmount().intValue()>0){
                changeMoneyFlow(memberInfo.getMemberId(), account.getBalanceAmount(), App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }


    /**
     * 西沙龙
    * @author 高国藩
    * @date 2016年3月5日 下午4:14:43
    * @param sheet sheet
    * @param rowNums sheet
    * @param storeId sheet
    * @param lastOperatorId sheet
     */
    private void insertErrorXsl(Sheet sheet, List<Integer> rowNums, Integer storeId, Integer lastOperatorId) {
        List<MemberErrorXsl> errorXsls = new ArrayList<>();
        a: for (int i = 0; i < rowNums.size(); i++) {
            MemberErrorXsl memberErrorXsl = new MemberErrorXsl();
            Row xssfRow = sheet.getRow(rowNums.get(i));
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                Cell cell = xssfRow.getCell(cellNum);
                String str = ExcleUtils.changeCellToString(cell);
                if (cellNum == 0) {
                    memberErrorXsl.setName(str);
                }
                if (cellNum == 4) {
                    if (str.length()>3){
                        str = str.substring(0, str.length()-3);
                    }
                    memberErrorXsl.setPhone(str);
                }
                if (cellNum == 1) {
                    memberErrorXsl.setSex(str);
                }
                if (cellNum == 8) {
                    memberErrorXsl.setLevelName(str);
                }
                if (cellNum == 9) {
                    if (str.length()>3){
                        str = str.substring(0, str.length()-3);
                    }
                    memberErrorXsl.setLevelNum(str);
                }
                if (cellNum == 12) {
                    memberErrorXsl.setBalanceAmount((new BigDecimal(ExcleUtils.changeValue(str))));
                }
            }
            memberErrorXsl.setStoreId(storeId);
            memberErrorXsl.setIsDeleted(0);
            memberErrorXsl.setLastOperatorId(lastOperatorId);
            errorXsls.add(memberErrorXsl);
        }
        memberErrorXslMapper.insertList(errorXsls);
    }


    /**
     * 华佗美业
    * @author 高国藩
    * @date 2016年3月15日 下午2:54:13
    * @param file     file
    * @param storeId  sdfsdf
    * @param storeAccount    企业代号
    * @param lastOperatorId lastOperatorId
    * @param response response
    * @param storeName storeName
    * @return  baseDto
     * @throws Exception  IOException
     */
    public BaseDto importExcleHtmy(MultipartFile file, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response,
            String storeName) throws Exception {
        updateStoreSet(storeId, storeName);
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        String fileName = file.getOriginalFilename();
        if (!fileName.endsWith(".json")){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "请保证文件.json格式");
        }
        else {
            List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
            // 已经存在的会员手机号码
            List<String> hasStr = new ArrayList<>();
            for (int i = 0; i < memberInfoDtos.size(); i++) {
                hasStr.add(memberInfoDtos.get(i).getPhone());
            }
            Map<String, Integer> levelMap = packLevelMap(storeId);
            Set<String> initLevel = new HashSet<>();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "utf-8"));
            JSONObject msg = JSONObject.fromObject(bufferedReader.readLine());
            JSONArray list = JSONArray.fromObject(msg.get("rows"));
            //第一步循环将新的会员卡存进去
            for (int i = 0; i < list.size(); i++) {
                JSONObject memberInfo = JSONObject.fromObject(list.get(i));
                if (levelMap.get(memberInfo.get("S_km")) == null){
                    initLevel.add(memberInfo.get("S_km").toString());
                }
            }
            for (String levelName : initLevel) {
                saveEnterpriseMemberLevel(lastOperatorId, levelName, storeAccount, storeId);
//                MemberLevel level = new MemberLevel();
//                level.setLevelName(levelName);
//                level.setStoreId(hsqStoreId);
//                level.setIsDeleted(0);
//                memberLevelMapper.insert(level);
            }
            levelMap = packLevelMap(hsqStoreId);
            List<MemberInfo> memberInfos = new ArrayList<>();
            List<MemberAccount> accounts = new ArrayList<>();
            List<MemberErrorHtmy> errorHts = new ArrayList<>();
            a: for (int i = 0; i < list.size(); i++) {
                MemberInfo info = new MemberInfo();
                MemberAccount account = new MemberAccount();
                JSONObject memberInfo = JSONObject.fromObject(list.get(i));
                try {
                    
                    info.setName(memberInfo.get("S_name").toString());
                    info.setLevelId(levelMap.get(memberInfo.get("S_km").toString()));
                    info.setStoreId(storeId);
                    info.setIsDeleted(0);
                    info.setLastOperatorId(lastOperatorId);
                    info.setCreateTime(DateUtil.getCurDate());
                    
                    account.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(memberInfo.get("S_kxfk").toString())));
                    account.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(memberInfo.get("S_kxfk").toString())));
                    
                    account.setBalanceGiftmoneyAmount(new BigDecimal(memberInfo.get("S_zs").toString()));
                    account.setTotalGiftmoneyAmount(new BigDecimal(memberInfo.get("S_zs").toString()));
                    
                    account.setBalanceIntegral(Integer.parseInt(ExcleUtils.changeValue(memberInfo.get("S_jf").toString())));
                    account.setTotalIntegral(Integer.parseInt(ExcleUtils.changeValue(memberInfo.get("S_jf").toString())));
                    
                    //手机号码校验
                    String phone = memberInfo.get("S_mobile").toString();
                    Pattern p = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");  
                    Matcher m = p.matcher(phone); 
                    if (m.matches()){
                        info.setPhone(phone);
                    }
                    else {
                        errorHts.add(errorMemberInsertHtmy(memberInfo, storeId));
                        continue a;
                    }
                    if (hasStr.contains(phone)) {
                        errorHts.add(errorMemberInsertHtmy(memberInfo, storeId));
                        continue a;
                    } 
                    
                    account.setCreateTime(DateUtil.getCurDate());
                    account.setLastOperatorId(lastOperatorId);
                    memberInfos.add(info);
                    accounts.add(account);
                    hasStr.add(phone);
                } 
                catch (Exception e) {
                    errorHts.add(errorMemberInsertHtmy(memberInfo, storeId));
                    continue a;
                }
            }
            for (int i = 0; i < memberInfos.size(); i++) {
                memberInfoMapper.insert(memberInfos.get(i));
                MemberAccount account = accounts.get(i);
                account.setAccountId(memberInfos.get(i).getMemberId());
                memberAccountMapper.insert(account);
                
                if (account.getBalanceAmount().intValue()>0){
                  //储值余额流水
                    changeMoneyFlow(memberInfos.get(i).getMemberId(), account.getBalanceAmount(), 
                            App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
                }
                //积分余额流水
                if (account.getBalanceIntegral() > 0){
                    changeIntegralFlow(memberInfos.get(i), account, account.getBalanceIntegral(), 
                            App.Member.IMPORT_MONEY_DECS, 7, storeId, 0, lastOperatorId);
                }
                //礼金余额流水
                if (account.getBalanceGiftmoneyAmount().intValue() > 0){
                    changeGiftMoneyFlow(memberInfos.get(i), account, account.getBalanceGiftmoneyAmount(), 
                            App.Member.IMPORT_MONEY_DECS, 7, lastOperatorId);
                }
            }
            
            for (int i = 0; i < errorHts.size(); i++) {
                if (hasStr.contains(errorHts.get(i).getPhone())){
                    MemberInfo info = memberInfoMapper.selectMemberByStoreIdAndPhone(storeId, errorHts.get(i).getPhone());
                    MemberSubAccount subAccount = new MemberSubAccount();
                    subAccount.setAccountId(info.getMemberId());
                    subAccount.setBalanceAmount(errorHts.get(i).getBalanceAmount());
                    subAccount.setLevelId(info.getLevelId());
                    subAccount.setCreateTime(DateUtil.getCurDate());
                    subAccount.setLastOperatorId(lastOperatorId);
                    subAccount.setTotalAmount(errorHts.get(i).getBalanceAmount());
                    subAccountMapper.insert(subAccount);
                    MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(info.getMemberId());
                    memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(subAccount.getBalanceAmount()));
                    memberAccount.setBalanceGiftmoneyAmount(memberAccount.getBalanceGiftmoneyAmount().
                            add(errorHts.get(i).getBalanceGiftmoneyAmount()));
                    memberAccount.setBalanceIntegral(memberAccount.getBalanceIntegral()+errorHts.get(i).getBalanceIntegral().intValue());
                    memberAccountMapper.updateByPrimaryKey(memberAccount);
                    errorHts.remove(i);
                }
                
            }
            
            if (errorHts.size()>0){
                memberErrorHtmyMapper.insertList(errorHts);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }


    /**
     * 异常会员数据-华拓
    * @author 高国藩
    * @date 2016年3月15日 下午4:34:02
    * @param memberInfo memberInfo
    * @param storeId    storeId
    * @return           数据
     */
    public MemberErrorHtmy errorMemberInsertHtmy(JSONObject memberInfo, Integer storeId){
        MemberErrorHtmy info = new MemberErrorHtmy();
        try {
            info.setPhone(memberInfo.get("S_mobile").toString());
        } 
        catch (Exception e) {
            info.setPhone("");
        }
        try {
            info.setName(memberInfo.get("S_name").toString());
        } 
        catch (Exception e) {
            info.setName("");
        }
        try {
            info.setLevelNum(memberInfo.get("S_kh").toString());
        } 
        catch (Exception e) {
            info.setLevelNum("");
        }
        info.setLevelName(memberInfo.get("S_km").toString());
        info.setStoreId(storeId);
        info.setBalanceAmount(new BigDecimal(memberInfo.get("S_kxfk").toString()));
        info.setBalanceGiftmoneyAmount(new BigDecimal(memberInfo.get("S_zs").toString()));
        info.setBalanceIntegral(new BigDecimal(memberInfo.get("S_jf").toString()));
        info.setIsDeleted(0);
        return info;
    }


    /**
     * 华彩数据导入
    * @author 高国藩
    * @date 2016年3月16日 下午2:51:59
    * @param multipartFile   multipartFile
    * @param multipartFile2  multipartFile2
    * @param storeId         storeId
    * @param storeAccount    企业代号
    * @param lastOperatorId lastOperatorId
    * @param response response
    * @param storeName storeName
    * @return storeName
    * @throws Exception Exception
     */
    public BaseDto importExcleHc(MultipartFile multipartFile,
            MultipartFile multipartFile2, Integer storeId, String storeAccount, 
            Integer lastOperatorId, HttpServletResponse response,
            String storeName) throws Exception {
        updateStoreSet(storeId, storeName);
        Map<String, Integer> levelMap = getLevelHc(multipartFile.getInputStream(), storeId, storeAccount, lastOperatorId);
        return readInfoHc(multipartFile2.getInputStream(), levelMap, storeId, lastOperatorId);
    }


    /**
     * 水电费
    * @author 高国藩
    * @date 2016年3月16日 下午2:47:42
    * @param inputStream inputStream
    * @param levelMap inputStream
    * @param storeId inputStream
    * @param lastOperatorId inputStream
    * @return inputStream
    * @throws IOException inputStream
     */
    private BaseDto readInfoHc(InputStream inputStream, Map<String, Integer> levelMap, Integer storeId,
            Integer lastOperatorId) throws IOException {
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        Map<String, String> sexMap = new HashMap<>();
        sexMap.put("2", "男");
        sexMap.put("1", "女");
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberErrorHc> errorHcs = new ArrayList<>();
        
        InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        while ((str = br.readLine()) != null){
            String re1=".*?";   // Non-greedy match on filler
            String re2="(\\(.*\\))";    // Round Braces 1
            Pattern p = Pattern.compile(re1+re2, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(str);
            if (m.find()){
                String rbraces1=m.group(1);
                String sql = rbraces1.toString();
                sql = sql.substring(1, sql.length());
                sql = sql.substring(0, sql.length()-1);
                String levelMapId = sql.split(",")[3].replaceAll("'", "");
                String balanceAmount = sql.split(",")[4].replaceAll("'", "");
                String levelNum = sql.split(",")[5].replaceAll("'", "");
                String name = sql.split(",")[6].replaceAll("'", "");
                String sex = sql.split(",")[7].replaceAll("'", "");
                String phone = sql.split(",")[12].replaceAll("'", "");
                
                MemberInfo info = new MemberInfo();
                info.setName(name);
                info.setLevelId(levelMap.get(levelMapId));
                info.setSex(sexMap.get(sex));
                info.setStoreId(storeId);
                MemberAccount account = new MemberAccount();
                account.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                
                Pattern p1 = Pattern.compile("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");  
                Matcher m1 = p1.matcher(phone); 
                if (m1.matches()&&!hasStr.contains(phone)){
                    info.setPhone(phone);
                    memberInfos.add(info);
                    accounts.add(account);
                    hasStr.add(phone);
                }
                else if (!m1.matches()){
                    MemberErrorHc errorHc = new MemberErrorHc();
                    errorHc.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                    errorHc.setIsDeleted(0);
                    errorHc.setLastOperatorId(lastOperatorId);
                    errorHc.setLevelName(levelNum);
                    errorHc.setLevelNum(levelNum);
                    errorHc.setName(name);
                    errorHc.setSex(sexMap.get(sex));
                    errorHc.setPhone(phone);
                    errorHc.setStoreId(storeId);
                    errorHcs.add(errorHc);
                }
            }
        }
        for (int i = 0; i < memberInfos.size(); i++) {
            memberInfoMapper.insert(memberInfos.get(i));
            accounts.get(i).setAccountId(memberInfos.get(i).getMemberId());
            memberAccountMapper.insert(accounts.get(i));
            if (accounts.get(i).getBalanceAmount().intValue()>0){
                //储值余额流水
                changeMoneyFlow(memberInfos.get(i).getMemberId(), accounts.get(i).getBalanceAmount(), 
                          App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
        }
        for (int i = 0; i < errorHcs.size(); i++) {
            if (hasStr.contains(errorHcs.get(i).getPhone())){
                MemberInfo info = memberInfoMapper.selectMemberByStoreIdAndPhone(storeId, errorHcs.get(i).getPhone());
                MemberSubAccount subAccount = new MemberSubAccount();
                subAccount.setAccountId(info.getMemberId());
                subAccount.setBalanceAmount(errorHcs.get(i).getBalanceAmount());
                subAccount.setLevelId(info.getLevelId());
                subAccount.setCreateTime(DateUtil.getCurDate());
                subAccount.setLastOperatorId(lastOperatorId);
                subAccount.setTotalAmount(errorHcs.get(i).getBalanceAmount());
                subAccountMapper.insert(subAccount);
                MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(info.getMemberId());
                memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(subAccount.getBalanceAmount()));
                memberAccountMapper.updateByPrimaryKey(memberAccount);
                errorHcs.remove(i);
            }
        }
        if (errorHcs.size()>0){
            memberErrorHcMapper.insertList(errorHcs);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }


    /**
     * 华彩数据-会员等级
    * @author 高国藩
    * @date 2016年3月16日 上午11:36:48
    * @param inputStream inputStream
    * @param storeId     storeId
    * @param storeAccont 企业代号
    * @param userId      操作人
    * @return            数据
    * @throws IOException IOException
     */
    private Map<String, Integer> getLevelHc(InputStream inputStream, Integer storeId, String storeAccont, Integer userId) throws IOException {
        Map<String, Integer> levelMap = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        while ((str = br.readLine()) != null){
            String re1=".*?";   // Non-greedy match on filler
            String re2="(\\(.*\\))";    // Round Braces 1
            Pattern p = Pattern.compile(re1+re2, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(str);
            if (m.find()){
                String rbraces1=m.group(1);
                String sql = rbraces1.toString();
                sql = sql.substring(1, sql.length());
                sql = sql.substring(0, sql.length()-1);
                String levelName = sql.split(",")[1].replaceAll("'", "");
                String levelId = sql.split(",")[0].replaceAll("'", "");
                saveEnterpriseMemberLevel(userId, levelName, storeAccont, storeId);
//                MemberLevel memberLevel = new MemberLevel();
//                memberLevel.setStoreId(storeId);
//                memberLevel.setCreateTime(DateUtil.getCurDate());
//                memberLevel.setLevelName(levelName);
//                memberLevelMapper.insert(memberLevel);
//                levelMap.put(levelId, memberLevel.getLevelId());
            }
        }
        return levelMap;
    }
    
    /**
     * 创建会员卡
    * @author 高国藩
    * @date 2016年6月4日 下午2:49:49
    * @param userId          userId
    * @param levelName       levelName
    * @param storeAccount    storeAccount
    * @param storeId         门店信息
    * @return                BaseDto
     */
    public BaseDto saveEnterpriseMemberLevel (Integer userId, String levelName, String storeAccount, Integer storeId) {
        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setLevelName(levelName);
        memberLevel.setLevelType("折扣卡");
        memberLevel.setLevelLogo("system/profile/vip_card_11.png,system/profile/vip_card_12.png");
        memberLevel.setLevelTemplate(1);
        memberLevel.setLevelNotice("导入会员数据,自动创建会员卡");
        memberLevel.setStoreAccount(storeAccount);
        
        MemberLevelDiscount memberLevelDiscount = new MemberLevelDiscount();
        memberLevelDiscount.setStoreId(storeId);
        memberLevelDiscount.setDiscountId(null);
        memberLevelDiscount.setProjectDiscount(100);
        memberLevelDiscount.setGoodsDiscount(100);
        return memberLevelService.saveEnterpriseMemberLevel(userId, memberLevel, memberLevelDiscount);
    }


    /**
     * 退卡操作
    * @author 高国藩
    * @date 2016年7月7日 下午4:44:49
    * @param subAccountId  子账户
    * @return              BaseDto
     */
    public BaseDto returnCardMember(Integer subAccountId) {
        MemberSubAccount memberSubAccount = subAccountMapper.selectByPrimaryKey(subAccountId);
        BigDecimal returnCardNum = memberSubAccount.getBalanceAmount();
//        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberSubAccount.getAccountId());
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberSubAccount.getAccountId());
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().subtract(memberSubAccount.getBalanceAmount()));
        memberSubAccount.setBalanceAmount(new BigDecimal(0));
        memberSubAccount.setIsDeleted(1);
        subAccountMapper.updateByPrimaryKey(memberSubAccount);
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        return new BaseDto(0, returnCardNum);
    }


    /**
     * 冻结/解冻会员
    * @author 高国藩
    * @date 2016年7月7日 下午5:04:11
    * @param memberId memberId
    * @param isDeleted isDeleted
    * @return         BaseDto
     */
    public BaseDto deletedMemberInfo(Integer memberId, Integer isDeleted) {
        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
        memberInfo.setIsDeleted(isDeleted);
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        return new BaseDto(0, memberInfo);
    }
    
}
    
