package com.zefun.wechat.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.EntityJsonConverter;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.DeptProjectBaseDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.dto.ShiftMahjongProjectStepDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.GoodsDiscount;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.ProjectDiscount;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ProjectStep;
import com.zefun.web.entity.ShiftMahjong;
import com.zefun.web.entity.ShiftMahjongEmployee;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.entity.WechatEmployee;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GoodsDiscountMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectCommissionMapper;
import com.zefun.web.mapper.ProjectDiscountMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ProjectStepMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.mapper.WechatEmployeeMapper;
import com.zefun.web.service.EmployeeService;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.ProjectService;
import com.zefun.web.service.RedisService;
import com.zefun.web.service.ShiftMahjongService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 员工端service
* @author 王大爷
* @date 2015年8月20日 下午5:37:24
 */
@Service
public class StaffService {
    /** 项目信息*/
    @Autowired private ProjectInfoMapper projectInfoMapper;
    
    /** 商品信息*/
    @Autowired private GoodsInfoMapper goodsInfoMapper;
    
    /** 员工信息*/
    @Autowired private EmployeeInfoMapper employeeInfoMapper;
    
    /** 套餐信息*/
    @Autowired private ComboInfoMapper comboInfoMapper;

    /** 订单信息*/
    @Autowired private OrderInfoMapper orderInfoMapper;
    
    /** 订单明细*/
    @Autowired private OrderDetailMapper orderDetailMapper;
    
    /** 项目步骤*/
    @Autowired private ProjectStepMapper projectStepMapper;
    
    /** 轮牌项目步骤关系*/
    @Autowired private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
    
    /** 轮牌员工*/
    @Autowired private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
    
    /** 轮牌信息*/
    @Autowired private ShiftMahjongMapper shiftMahjongMapper;
    
    /** 项目会员折扣*/
    @Autowired private ProjectDiscountMapper projectDiscountMapper;
    
    /**项目服务操作对象*/
    @Autowired private ProjectService projectService;
    
    /**商品服务操作对象*/
    @Autowired private GoodsInfoService goodsInfoService;
    
    /** redis操作类 */
    @Autowired
    private RedisService redisService;
    
    /** 登录*/
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    /** 微信*/
    @Autowired
    private WechatEmployeeMapper wechatEmployeeMapper;
    
    /** 商品*/
    @Autowired private GoodsDiscountMapper goodsDiscountMapper;
    
    /** 轮牌*/
    @Autowired private ShiftMahjongService shiftMahjongService;
    
    /** 会员等级*/
    @Autowired private MemberLevelMapper memberLevelMapper;
    /** 会员*/
    @Autowired private MemberInfoMapper memberInfoMapper;
    
    /** 项目员工提成*/
    @Autowired private ProjectCommissionMapper projectCommissionMapper;
    /**    */
    @Autowired private StaffOrderService staffOrderService;
    /**  */
    @Autowired private DeptInfoMapper deptInfoMapper;
    
    /**员工信息服务对象*/
    @Autowired private EmployeeService employeeService;
    
    /**会员信息服务对象*/
    @Autowired private MemberInfoService memberInfoService;

    /** 微信api服务对象 */
    @Autowired
    private WeixinMessageService weixinMessageService;
    
    /**
     * 查看员工主页
    * @author 王潇
    * @date Aug 19, 2015 4:21:25 PM
    * @param employeeId       员工标识
    * @return           员工主页面
     */
    public ModelAndView homeView(Integer employeeId){
        Map<String, Object> employeeMap = employeeInfoMapper.getDetail(employeeId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("employeeDto", employeeMap);
        return mav;
    }
    
    /**
     * 员工登录
    * @author 张进军
    * @date Aug 19, 2015 7:43:51 PM
    * @param storeId        微信对应门店标识
    * @param phone          手机号
    * @param password       密码
    * @param openId         微信id
    * @param request        请求对象
    * @return               成功返回码0，返回值为跳转地址；失败返回其他错误码，返回值为提示语
     */
    @Transactional
    public BaseDto login(String storeId, String phone, String password, String openId, HttpServletRequest request){
        Map<String, String> params = new HashMap<>();
        params.put("userName", phone);
        UserAccount userAccount = userAccountMapper.selectByUserName(params);
        if (userAccount == null) {
            return new BaseDto(9001, "账号不存在或已被删除");
        }
        
        //检查用户密码
        if (!StringUtil.md5(password + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }
        
        BaseDto baseDto = new BaseDto();
        baseDto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
        
        Integer employeeId = userAccount.getUserId();
        //检查是否已经绑定员工，如果已绑定，直接提示错误
        if (StringUtils.isNotBlank(redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId))) {
            return new BaseDto(9003, "该员工已经绑定了其他微信号");
        }
        
//        Map<String, Object> employeeMap = employeeInfoMapper.getDetail(employeeId);
        //建立微信的关联关系
        wechatEmployeeMapper.deleteByPrimaryKey(openId);
        WechatEmployee wechatEmployee = new WechatEmployee();
        wechatEmployee.setOpenId(openId);
        wechatEmployee.setEmployeeId(employeeId);
        wechatEmployee.setIsSubscribe(isSubscribe(openId));
        String curTime = DateUtil.getCurTime();
        wechatEmployee.setCreateTime(curTime);
        wechatEmployee.setUpdateTime(curTime);
        wechatEmployeeMapper.insert(wechatEmployee);
        
        //将该用户移动到微信员工组中，刷新个性菜单
        int roleId = userAccount.getRoleId();
        if (roleId == App.System.SYSTEM_ROLE_STORE_BOSS) {
            weixinMessageService.moveGroupByGroupType(storeId, 3, openId);
        }
        else {
            weixinMessageService.moveGroupByGroupType(storeId, 2, openId);
        }
        
        redisService.hset(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId, employeeId);
        redisService.hset(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId, openId);
        redisService.hset(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId, 2);
        request.getSession().setAttribute(App.Session.ROLE_ID, roleId);
        
        return baseDto;
    }
    
    
    /**
     * 员工注销登录
    * @author 张进军
    * @date Dec 11, 2015 11:22:04 PM
    * @param storeId    微信对应门店标识
    * @param employeeId 员工标识
    * @param request    请求对象
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto logout(String storeId, int employeeId, HttpServletRequest request){
        //将该用户移动到微信未绑定组中，刷新个性菜单
        String openId = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId);
        weixinMessageService.moveGroupByGroupType(storeId, 4, openId);
        
        employeeService.wipeCache(employeeId);
        request.getSession().invalidate();
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 员工接待页面
    * @author 王大爷
    * @date 2016年1月12日 上午9:50:00
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView receptionView(Integer storeId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(View.StaffPage.RECEPTION);
        return mav;
    }
    
    /**
     * 查询会员信息、项目类别、商品类别
    * @author 王大爷
    * @date 2015年8月20日 下午5:40:29
    * @param storeId 门店标识
    * @param employeeId 员工标识
    * @param memberBaseDto 会员信息
    * @param orderId 订单标识
    * @return ModelAndView
     */
    public ModelAndView selectBaseInfo(Integer storeId, Integer employeeId, MemberBaseDto memberBaseDto, Integer orderId){
        
        ModelAndView mav = new ModelAndView();
        Integer employeeDeptId = employeeService.getDeptIdByEmployeeId(employeeId);
        mav.addObject("memberBaseDto", memberBaseDto);
        List<DeptInfo> deptList = deptInfoMapper.getDeptIdAndNameByStoreId(storeId);
        
        List<Map<String, Object>> dtoList = new ArrayList<Map<String, Object>>();
        
        for (DeptInfo dept : deptList) {
            Integer deptId = dept.getDeptId();
            
            if (employeeDeptId.intValue() == deptId.intValue()) {
                mav.addObject("employeeDeptName", dept.getDeptName());
            }
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptId", deptId);
            
            DeptProjectBaseDto deptProjectBaseDto = projectService.getDeptProjectByDeptId(deptId);
            map.put("project", deptProjectBaseDto.getProjectCategoryList());
            DeptGoodsBaseDto deptGoodsBaseDto = goodsInfoService.getDeptGoodsByDeptId(deptId);
            map.put("goods", deptGoodsBaseDto.getGoodsCategoryBaseDto());
            List<ComboInfo> comboInfoList = comboInfoMapper.getComboInfo(deptId);
            map.put("combo", comboInfoList);
            dtoList.add(map);
        }
        //常用项目
//        List<ProjectInfo> projectInfoListOrder = projectInfoMapper.selectDeptIdOrder(deptId);
//        mav.addObject("projectInfoListOrder", projectInfoListOrder);
        
        /*ProjectCategory projectCategory = new ProjectCategory();
        projectCategory.setStoreId(storeId);
        //固定值
        projectCategory.setDeptId(deptId);*/
        
//        List<ProjectCategoryDto> projectCategoryDtoList = projectCategoryMapper.selectByProjectCategory(projectCategory);
        
/*        mav.addObject("projectCategoryDtoList", deptProjectBaseDto.getProjectCategoryList());*/
        /*GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setStoreId(storeId);
        goodsCategory.setDeptId(deptId);*/
        
//        List<GoodsCategoryDto> goodsCategoryDtoList = goodsCategoryMapper.selectByGoodsCategory(goodsCategory);
        
/*        mav.addObject("goodsCategoryDtoList", deptGoodsBaseDto.getGoodsCategoryBaseDto());

        
        mav.addObject("comboInfoList", comboInfoList);*/
        mav.addObject("dtoList", dtoList);
        mav.addObject("deptList", deptList);
        mav.addObject("employeeDeptId", employeeDeptId);
        mav.addObject("orderId", orderId);
        
        mav.setViewName(View.StaffPage.PROJECT_CATEGORY);
        return mav;
    }
    
    
    /**
     * 开单
    * @author 王大爷
    * @date 2015年10月27日 上午11:06:17
    * @param objString 订单明细obj
    * @param employeeObj 指定人员
    * @param nextProjectObj 项目下一步服务步骤
    * @param memberId 会员标识
    * @param sex    消费者性别
    * @param storeId 门店标识
    * @param lastOperatorId 最后操作者
    * @param isAppointment 是否预约
    * @param orderId 订单标识
    * @return ModelAndView
     */
    @Transactional
    public BaseDto addOrder(String objString, String employeeObj, String nextProjectObj, Integer memberId, String sex, Integer storeId, 
            Integer lastOperatorId, Integer isAppointment, Integer orderId) {
        MemberBaseDto memberBaseDto = memberInfoService.getMemberBaseInfo(memberId, false);

        Integer levelId = null;
        if (memberBaseDto != null) {
            levelId = memberInfoMapper.selectByPrimaryKey(memberId).getLevelId();
        }
        if (orderId == null) {
            String orderCode = getOrderCode("order_info", storeId);
            //保存订单信息
            orderId = addOrderInfo(orderCode, memberId, storeId, sex, DateUtil.getCurTime(), lastOperatorId, "");
        }
        
        Integer markTypeInteger = addDetail(orderId, employeeObj, objString, nextProjectObj, storeId, memberId, levelId, 
                isAppointment, lastOperatorId, memberBaseDto);
        
        orderInfoMapper.updateTotalPrice(orderId);
        
        //查询该明细对应的订单中存在的未完成的项目明细
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByNotOverByOrderId(orderId);
        if (orderDetailList.size() == 0) {
            //修改订单信息状态
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(orderId);
            orderInfo.setOrderStatus(2);
            orderInfoMapper.updateByPrimaryKey(orderInfo);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("markTypeInteger", markTypeInteger);
        map.put("storeId", storeId);
        map.put("orderId", orderId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }
    
    /**
     * 添加明细，步骤，及修改轮牌信息
    * @author 王大爷
    * @date 2015年11月5日 上午10:41:08
    * @param orderId 订单标识
    * @param employeeObj 指定员工
    * @param objString 选择的项目
    * @param nextProjectObj 项目下一步服务步骤
    * @param storeId 门店标识
    * @param memberId 会员标识
    * @param levelId 会员级别
    * @param isAppointment 是否预约
    * @param lastOperatorId 操作人
    * @param memberBaseDto 会员基本信息
    * @return Integer
     */
    public Integer addDetail(Integer orderId, String employeeObj, String objString, String nextProjectObj, Integer storeId, Integer memberId, 
            Integer levelId, Integer isAppointment, Integer lastOperatorId, MemberBaseDto memberBaseDto) {

        Integer markTypeInteger = 0;
        
        //获取项目下一步
        JSONArray nextProjectArray =JSONArray.fromObject(nextProjectObj);
        
        List<Integer> nextList = new ArrayList<Integer>(nextProjectArray.size());
        
        for (int j = 0; j < nextProjectArray.size(); j++) {
            JSONObject jsonNext = nextProjectArray.getJSONObject(j);
            nextList.add(jsonNext.getInt("projectNum"), jsonNext.getInt("orderNum"));
        }
        
        //获取项目指定轮牌员工
        JSONArray empjsonArray =JSONArray.fromObject(employeeObj);
        List<Map<String, Integer>> assignList = new ArrayList<Map<String, Integer>>(); 
        for (int j = 0; j < empjsonArray.size(); j++) {
            JSONObject jsonEmployee = empjsonArray.getJSONObject(j);
            //对应订单编号
            Integer assignProjectNum = jsonEmployee.getInt("projectNum");
            //项目步骤
            Integer assignOrderNum = jsonEmployee.getInt("orderNum");
            //指定员工标识
            Integer assignProjectId = jsonEmployee.getInt("projectId");
            //指定员工对应轮牌员工标识
            Integer assignShiftMahjongEmployeeId = jsonEmployee.getInt("shiftMahjongEmployeeId");
            //指定员工标识
            Integer assignEmployeeId = jsonEmployee.getInt("employeeId");
            //类型
            Integer isType = jsonEmployee.getInt("isType");
            
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("assignProjectNum", assignProjectNum);
            map.put("assignOrderNum", assignOrderNum);
            map.put("assignProjectId", assignProjectId);
            map.put("assignShiftMahjongEmployeeId", assignShiftMahjongEmployeeId);
            map.put("assignEmployeeId", assignEmployeeId);
            map.put("isType", isType);
            assignList.add(map);
        }
        
        JSONArray objjsonArray =JSONArray.fromObject(objString);
        
        for (int i = 0; i < objjsonArray.size(); i++) {
            JSONObject jsonObject = objjsonArray.getJSONObject(i);
            //订单明细类型
            Integer orderType = jsonObject.getInt("orderType");
            //项目标识
            Integer projectId = jsonObject.getInt("projectId");
            //项目名称
            String projectName = jsonObject.getString("projectName");
            //项目价格
            String  projectPriceStr = jsonObject.getString("projectPriceStr");
            BigDecimal  projectPrice = new BigDecimal(projectPriceStr);
            
            //项目数量
            Integer projectCount = jsonObject.getInt("projectCount");
            //项目头像
            String projectImage = jsonObject.getString("projectImage");
            
            //保存订单明细
            String detailCode = null;
            
            if (orderType == 1) {
                //选多个项目时，保存多条订单明细
                for (int k = 0; k < projectCount; k++) {
                    detailCode = getOrderCode("order_detail", storeId);
                    Integer detailId = addOrderDetail(detailCode, orderId, memberId, levelId, orderType, projectId, 
                            projectName, projectPrice, projectCount, projectImage, isAppointment, DateUtil.getCurTime(), storeId, lastOperatorId);
                    
                    addShiftMahjongProjectStep(projectId, detailId, lastOperatorId);
                    //移除指定信息的记录
                    int a = -1;
                    //判断是否指定第一步
                    int b = 0;
                    //获取项目指定轮牌员工
                    for (int j = 0; j < assignList.size(); j++) {
                        
                        Map<String, Integer> assignmap = assignList.get(j);
                        
                        if (String.valueOf(assignmap.get("assignProjectId")).equals(String.valueOf(projectId))) {
                            if (a == -1) {
                                a = assignmap.get("assignProjectNum");
                                //获取该项目对应执行步骤
                                Integer nextNum = nextList.get(a);
                                
                                Integer nextType = 0;
                                
                                if (assignmap.get("assignOrderNum") == nextNum) {
                                    b = 1;
                                    nextType = 1;
                                    
                                    for (int h = 0; h < nextProjectArray.size(); h++) {
                                        JSONObject jsonNext = nextProjectArray.getJSONObject(h);
                                        Integer nextProjectId = jsonNext.getInt("projectId");
                                        Integer nextOrderNum = jsonNext.getInt("orderNum");
                                        
                                        if (projectId == nextProjectId && nextOrderNum == nextNum) {
                                            nextProjectArray.remove(h);
                                        }
                                    }
                                }
                                
                                Integer type = invokingShift(assignmap, memberBaseDto, storeId, detailId, isAppointment, lastOperatorId, nextType);
                                if (type == 1) {
                                    markTypeInteger = 1;
                                }
                                
                                assignList.remove(j);
                                j--;
                            }
                            else {
                                if (a == assignmap.get("assignProjectNum")) {
                                    //获取该项目对应执行步骤
                                    Integer nextNum = nextList.get(a);
                                    
                                    Integer nextType = 0;
                                    
                                    if (assignmap.get("assignOrderNum") == nextNum) {
                                        b = 1;
                                        nextType = 1;
                                        
                                        for (int h = 0; h < nextProjectArray.size(); h++) {
                                            JSONObject jsonNext = nextProjectArray.getJSONObject(h);
                                            Integer nextProjectId = jsonNext.getInt("projectId");
                                            Integer nextOrderNum = jsonNext.getInt("orderNum");
                                            
                                            if (projectId == nextProjectId && nextOrderNum == nextNum) {
                                                nextProjectArray.remove(h);
                                            }
                                        }
                                    }
                                    Integer type = invokingShift(assignmap, memberBaseDto, storeId, detailId, isAppointment, 
                                            lastOperatorId, nextType);
                                    if (type == 1) {
                                        markTypeInteger = 1;
                                    }
  
                                    assignList.remove(j);
                                    j--;
                                }
                            }
                            
                        }
                    }
                    //如果第一步没有指定，就从轮牌中取
                    if (b == 0) {
                        Integer orderNum = null;
                        for (int h = 0; h < nextProjectArray.size(); h++) {
                            JSONObject jsonNext = nextProjectArray.getJSONObject(h);
                            Integer nextProjectId = jsonNext.getInt("projectId");
                            
                            if (projectId.intValue() == nextProjectId.intValue()) {
                                orderNum = jsonNext.getInt("orderNum");
                                nextProjectArray.remove(h);
                                break;
                            }
                        }
                        //根据项目步骤标识、订单明细查询轮牌项目步骤关系（第一步）
                        ShiftMahjongProjectStepDto shiftMahjongProjectStepDto = selectShiftMahjongStep(projectId, detailId, orderNum);
                        ShiftMahjongEmployee shiftMahjongEmployee 
                                = selectShiftMahjongOneEmployee(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                        if (shiftMahjongEmployee == null) {
                            //转到等待中心
                            redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                                     Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                            
                            ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                            shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                            shiftMahjongProjectStep.setIsCurrent(1);
                            shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                        }
                        else {
                            //调整员工在轮牌的位置及修改状态
                            moveEmployee(shiftMahjongEmployee.getShiftMahjongEmployeeId(), false);
                            //轮牌项目步骤关系指定人员
                            updateShiftMahjongProjectStep(shiftMahjongEmployee.getEmployeesId(), 
                                    shiftMahjongProjectStepDto.getShiftMahjongStepId(), 0, 0, detailId, isAppointment);
                            if (shiftMahjongEmployee.getEmployeesId().intValue() == lastOperatorId.intValue()) {
                                markTypeInteger = 1;
                            }
                            else {
                                //发送模版消息
                                staffOrderService.senMessage(shiftMahjongEmployee.getEmployeesId(), null,
                                        shiftMahjongProjectStepDto.getShiftMahjongStepId(), storeId, 2, lastOperatorId);
                            }
                        }
                    }
                    
                }
            }
            else {
                for (int k = 0; k < projectCount; k++) {
                    addOrderDetail(detailCode, orderId, memberId, levelId, orderType, projectId, projectName, 
                            projectPrice, 1, projectImage, isAppointment, DateUtil.getCurTime(), storeId, lastOperatorId);
                }
            }
            
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        //状态进行中
        orderInfo.setOrderStatus(1);
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        
        return markTypeInteger;
    }
    
    /**
     * 保存订单信息
    * @author 王大爷
    * @date 2015年9月19日 下午2:01:33
    * @param orderCode 订单号
    * @param memberId 会员信息标识
    * @param storeId 门店标识
    * @param openOrderDate 补单时间
    * @param sex 性别
    * @param employeeId 操作员工
    * @param handOrderCode 手工单号
    * @return 订单标识
     */
    @Transactional
    public Integer addOrderInfo(String orderCode, Integer memberId, Integer storeId, String sex, 
    		  String openOrderDate, Integer employeeId, String handOrderCode){
        //保存订单信息
        int deptId = employeeService.getDeptIdByEmployeeId(employeeId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderCode(orderCode);
        if (memberId != null) {
            orderInfo.setMemberId(memberId);
        }
        orderInfo.setSex(sex);
        orderInfo.setDeptId(deptId);
        orderInfo.setStoreId(storeId);
        orderInfo.setCreateTime(openOrderDate);
        orderInfo.setLastOperatorId(employeeId);
        orderInfo.setHandOrderCode(handOrderCode);
        orderInfoMapper.insert(orderInfo);
        return orderInfo.getOrderId();
    }
    
    /**
     * 保存订单明细
    * @author 王大爷
    * @date 2015年9月19日 下午3:50:07
    * @param detailCode 订单明细编号
    * @param orderId 订单标识
    * @param memberId 会员信息标识
    * @param levelId 会员等级标识
    * @param orderType 订单类型(1:项目,2:商品,3:套餐)
    * @param projectId 项目标识
    * @param projectName 项目名称
    * @param projectPrice 项目价格
    * @param projectCount 项目数量
    * @param projectImage 项目图片
    * @param isAppointment 是否预约
    * @param openOrderDate 补单时间
    * @param storeId 门店标识
    * @param lastOperatorId 操作员工
    * @return 订单明细标识
     */
    public Integer addOrderDetail(String detailCode, Integer orderId, Integer memberId, Integer levelId, 
            Integer orderType, Integer projectId, String projectName, 
            BigDecimal projectPrice, Integer projectCount, String projectImage, Integer isAppointment, String openOrderDate,
            Integer storeId, Integer lastOperatorId) {
    	
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        
        //项目
        if (orderType == 1) {
            ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
            //添加明细部门选择
            orderDetail.setDeptId(projectInfo.getDeptId());
            BigDecimal discountAmount = projectInfo.getProjectPrice();
            BigDecimal rate = new BigDecimal(100);
            
            //计算折扣价
            if (memberId != null) {
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
                
                //记录预约优惠金额
                if (isAppointment == 1) {
                    orderDetail.setAppointOff(projectInfo.getAppointmentPrice());
                    orderDetail.setIsAppoint(isAppointment);
                }
            }
            
            orderDetail.setDiscountAmount(discountAmount);
            orderDetail.setOrderStatus(1);
        }
        
        //商品
        else if (orderType == 2) {
            GoodsInfoDto goodsInfo = goodsInfoMapper.selectByPrimaryKey(projectId);
            //添加明细部门选择
            orderDetail.setDeptId(goodsInfo.getDeptId());
            BigDecimal discountAmount = goodsInfo.getGoodsPrice();
            BigDecimal rate = new BigDecimal(100);
            
            //计算折扣价
            if (memberId != null) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("levelId", levelId);
                map.put("goodsId", projectId);
                GoodsDiscount obj = goodsDiscountMapper.selectDiscountGoodsIdAndLevelId(map);
                
                //该商品对应该会员的会员等级不存在特定价格
                if (obj == null) {
                	Map<String, Integer> memberMap = new HashMap<>();
                	map.put("storeId", storeId);
                	map.put("levelId", levelId);
                    //计算会员折扣价
                    MemberLevelDto memberLevel = memberLevelMapper.selectByEnterprise(memberMap);
                    discountAmount = goodsInfo.getGoodsPrice().multiply(new BigDecimal(memberLevel.getProjectDiscount()).divide(rate));
                }
                //该商品对应该会员的会员等级存在特定价格
                else {
                    discountAmount = obj.getDiscountAmount();
                }
            } 
            
            orderDetail.setDiscountAmount(discountAmount);
            orderDetail.setOrderStatus(3);
        } 
        else {
        	ComboInfo comboInfo = comboInfoMapper.selectByPrimaryKey(projectId);
        	//添加明细部门选择
            orderDetail.setDeptId(comboInfo.getDeptId());
            orderDetail.setDiscountAmount(projectPrice);
            orderDetail.setOrderStatus(3);
        }
        
        orderDetail.setDetailCode(detailCode);
        orderDetail.setOrderType(orderType);
        orderDetail.setIsAssign(0);
        orderDetail.setProjectId(projectId);
        orderDetail.setProjectName(projectName);
        orderDetail.setProjectPrice(projectPrice);
        orderDetail.setProjectCount(1);
        orderDetail.setProjectImage(projectImage);
        orderDetail.setStoreId(storeId);
        orderDetail.setCreateTime(openOrderDate);
        orderDetail.setLastOperatorId(lastOperatorId);
        
        orderDetailMapper.insert(orderDetail);
        
        return orderDetail.getDetailId();
    }
    
    /**
     * 根据项目步骤标识、订单明细查询轮牌项目步骤关系(第一步)
    * @author 王大爷
    * @date 2015年9月28日 下午2:13:24
    * @param projectId 项目标识
    * @param detailId 订单明细标识
    * @param projectStepOrder 项目顺序
    * @return List<ShiftMahjongProjectStepDto>
     */
    public ShiftMahjongProjectStepDto selectShiftMahjongStep(Integer projectId, Integer detailId , Integer projectStepOrder){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("detailId", detailId);
        map.put("projectId", projectId);
        map.put("projectStepOrder", projectStepOrder);
        ShiftMahjongProjectStepDto objDto = shiftMahjongProjectStepMapper.selectShiftMahjongStep(map);
        return objDto;
    }
    
    /**
     * 保存轮牌步骤
    * @author 王大爷
    * @date 2015年9月19日 下午4:40:41
    * @param projectId 项目标识
    * @param detailId 订单明细
    * @param lastOperatorId 操作人员
     */
    public void addShiftMahjongProjectStep(Integer projectId, Integer detailId, Integer lastOperatorId){
        List<ProjectStep> list = projectStepMapper.queryProjectStepByPIdList(projectId);
        /*//修改项目前的没有删除的步骤
        List<ShiftMahjongProjectStep> objList = shiftMahjongProjectStepMapper.selectStepByDetailId(detailId);*/
        for (int i = 0; i < list.size(); i++) {
            /*Integer projectStepId = list.get(i).getProjectStepId();
            for (ShiftMahjongProjectStep obj : objList) {
                if (obj.getProjectStepId().intValue() == projectStepId.intValue()) {
                    
                }
            }*/
            ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
            shiftMahjongProjectStep.setProjectStepId(list.get(i).getProjectStepId());
            shiftMahjongProjectStep.setDetailId(detailId);
            shiftMahjongProjectStep.setIsOver(2);
            shiftMahjongProjectStep.setCreateTime(DateUtil.getCurTime());
            shiftMahjongProjectStep.setLastOperatorId(lastOperatorId);
            
            shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep);
        }
    }
    
    /**
     * 查询项目对应轮牌员工
    * @author 王大爷
    * @date 2015年9月19日 下午5:19:42
    * @param projectId 项目标识
    * @return List<ShiftMahjongEmployee>
     */
    public List<ShiftMahjongDto> selectShiftProjectEmployee(Integer projectId){
                
        List<ShiftMahjong> list = shiftMahjongMapper.selectByProjectId(projectId);
                
        ShiftMahjongDto shiftMahjongDto = null;
        
        List<ShiftMahjongDto> shiftMahjongDtoList= new ArrayList<ShiftMahjongDto>();
        
        for (int i = 0; i < list.size(); i++) {
            
            ShiftMahjong shiftMahjong = list.get(i);
            shiftMahjongDto = new ShiftMahjongDto();
            shiftMahjongDto.setShiftMahjongId(shiftMahjong.getShiftMahjongId());
            shiftMahjongDto.setShiftMahjongName(shiftMahjong.getShiftMahjongName());
            Integer num = i + 1;
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("projectId", projectId);
            map.put("num", num);
            shiftMahjongDto.setStepNum(num);
            List<ShiftMahjongEmployee> shiftMahjongEmployeeList = shiftMahjongEmployeeMapper.selectShiftEmployeeList(map);
            shiftMahjongDto.setShiftMahjongEmployeeList(shiftMahjongEmployeeList);
            
            shiftMahjongDtoList.add(shiftMahjongDto);
        }
        return shiftMahjongDtoList;
    }
    
    /**
     * 根据项目标识和步骤查询对应轮牌信息
    * @author 王大爷
    * @date 2015年9月21日 下午5:45:04
    * @param projectId 项目标识
    * @param num 步骤
    * @return List<ShiftMahjongEmployee>
     */
    public List<ShiftMahjongEmployee> selectShiftEmployeeList(Integer projectId, Integer num){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("projectId", projectId);
        map.put("num", num);
        List<ShiftMahjongEmployee> list = shiftMahjongEmployeeMapper.selectShiftEmployeeList(map);
        return list;
    }
    
    /**
     * 根据轮牌项目步骤关系标识查询轮牌员工（满足项目中设定级别的排到第一个）
    * @author 王大爷
    * @date 2015年9月28日 下午4:45:53
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @return 满足项目中设定级别的排到第一个
     */
    public ShiftMahjongEmployee selectShiftMahjongOneEmployee(Integer shiftMahjongStepId){
        return shiftMahjongEmployeeMapper.selectShiftMahjongOneEmployee(shiftMahjongStepId);
    }
    
    /**
     * 指定时员工
    * @author 王大爷
    * @date 2015年9月21日 下午5:59:12
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @param employeeId 员工标识
    * @param shiftMahjongEmployeeId 轮牌员工标识
     */
    public void updateShiftMahjongProjectStepInfo(Integer shiftMahjongStepId, Integer employeeId, Integer shiftMahjongEmployeeId){
        
        //修改轮牌项目步骤关系
        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
        shiftMahjongProjectStep.setEmployeeId(employeeId);
        shiftMahjongProjectStep.setIsAppoint(1);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
        
        //修改轮牌员工状态为：指定服务
        ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
        shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
        shiftMahjongEmployee.setState(4);
        shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
    }
    
    /**
     * 指定人员
    * @author 王大爷
    * @date 2015年9月28日 上午11:19:38
    * @param employeeId 员工id
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @param isAssign 是否指定
    * @param detailId 订单明细标识
    * @param isAppoint 是否预约
    * @param isDesignate 是否指派
     */
    public void updateShiftMahjongProjectStep(Integer employeeId, Integer shiftMahjongStepId, Integer isAssign, Integer isDesignate, 
            Integer detailId, Integer isAppoint){
        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
        shiftMahjongProjectStep.setEmployeeId(employeeId);
        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
        shiftMahjongProjectStep.setBeginTime(DateUtil.getCurTime());
        shiftMahjongProjectStep.setIsOver(1);
        shiftMahjongProjectStep.setIsAssign(isAssign);
        shiftMahjongProjectStep.setIsDesignate(isDesignate);
        shiftMahjongProjectStep.setIsAppoint(isAppoint);
        shiftMahjongProjectStep.setIsCurrent(1);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
        
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(detailId);
        orderDetail.setOrderStatus(2);
        orderDetailMapper.updateByPrimaryKey(orderDetail);
    }
    
    /**
     * 员工接单后，是否做出位置调整
    * @author 王大爷
    * @date 2015年9月22日 上午11:28:25
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @param type 是否指定
     */
    public void moveEmployee(Integer shiftMahjongEmployeeId, boolean type){
        ShiftMahjong shiftMahjong = shiftMahjongMapper.selectByShiftMahjongEmployeeId(shiftMahjongEmployeeId);
        if (type) {
            if (shiftMahjong.getShiftMahjongRule() == 2) {
                //更新轮牌位置
                ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
                shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
                shiftMahjongEmployee.setShiftMahjongId(shiftMahjong.getShiftMahjongId());
                shiftMahjongEmployeeMapper.updateByToEnd(shiftMahjongEmployee);
                shiftMahjongEmployeeMapper.updateByUpwardAllCount(shiftMahjongEmployee);
            }
            shiftMahjongService.updateState(shiftMahjongEmployeeId, 4, null);
        }
        else {
            //更新轮牌位置
            ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
            shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
            shiftMahjongEmployee.setShiftMahjongId(shiftMahjong.getShiftMahjongId());
            shiftMahjongEmployeeMapper.updateByToEnd(shiftMahjongEmployee);
            shiftMahjongEmployeeMapper.updateByUpwardAllCount(shiftMahjongEmployee);
            shiftMahjongService.updateState(shiftMahjongEmployeeId, 0, null);
        }
    }
    
    /**
     * 修改上个轮牌员工状态
    * @author 王大爷
    * @date 2015年10月24日 下午2:48:47
    * @param shiftMahjongStepId 步骤标识
    * @param detailId 明细标识
    * @param storeId 门店标识
     */
    public void updateShiftLastEmployeeState(Integer shiftMahjongStepId, Integer detailId, Integer storeId){
        
        //查询上个步骤对应轮牌员工标识（以修改）
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftMahjongLastEmployee(detailId);
        
        updateShiftEmployeeState(shiftMahjongEmployee.getShiftMahjongEmployeeId(), detailId, storeId);
            
        ShiftMahjongProjectStep obj = shiftMahjongProjectStepMapper.selectShiftMahjongLastStep(shiftMahjongStepId);
        
        //修改上个步骤
        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
        shiftMahjongProjectStep.setShiftMahjongStepId(obj.getShiftMahjongStepId());
        shiftMahjongProjectStep.setFinishTime(DateUtil.getCurTime());
        shiftMahjongProjectStep.setIsOver(3);
        shiftMahjongProjectStep.setIsCurrent(0);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
        
        //修改服务时长
        orderDetailMapper.updateServiceLength(shiftMahjongStepId);
    }
    
    /**
     * 根据步骤修改轮牌员工状态
    * @author 王大爷
    * @date 2015年10月24日 下午2:48:47
    * @param shiftMahjongEmployeeId 轮牌员工标识标识
    * @param detailId 明细标识
    * @param storeId 门店标识
     */
    public void updateShiftEmployeeState(Integer shiftMahjongEmployeeId, Integer detailId, Integer storeId){
        
        OrderDetail detailObj  = orderDetailMapper.selectByPrimaryKey(detailId);
        
        if (detailObj.getOrderStatus() != 4) {
            ShiftMahjongEmployee record = new ShiftMahjongEmployee();
            record.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
            record.setState(1);
            //修改员工轮牌状态
            shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(record);
            
            //自动获取项目
            selfMotionExecute(shiftMahjongEmployeeId, storeId);
        }    
    }
    
    /**
     * 通过订单明细标识、步骤顺序、项目标识查询轮牌项目步骤关系标识
    * @author 王大爷
    * @date 2015年9月25日 下午4:17:05
    * @param detailId 订单明细标识
    * @param projectStepOrder 步骤顺序
    * @param projectId 项目标识
    * @return 轮牌项目步骤关系标识
     */
    public Integer selectShiftMahjongStepId(Integer detailId, Integer projectStepOrder, Integer projectId){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("detailId", detailId);
        map.put("projectStepOrder", projectStepOrder);
        map.put("projectId", projectId);
        return shiftMahjongProjectStepMapper.selectShiftMahjongStepId(map);
    }
    
    /**
     * 查询员工当前状态
    * @author 王大爷
    * @date 2015年9月25日 下午4:27:55
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @return 员工状态（0：工作中、1：空闲中、2：暂时离开、3：离开、4、指定服务）
     */
    public Integer selectState(Integer shiftMahjongEmployeeId) {
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
        return shiftMahjongEmployee.getState();
    }
    
    /**
     * 查询订单明细
    * @author 王大爷
    * @date 2015年9月22日 下午2:07:34
    * @param employeesId 员工标识
    * @return List<ShiftMahjongProjectStepDto>
     */
    public List<ShiftMahjongProjectStepDto> serveOrder(Integer employeesId){
        return shiftMahjongProjectStepMapper.selectByEmployeeId(employeesId);
    }
    
    /**
     * 新增项目是操作
    * @author 王大爷
    * @date 2015年10月17日 下午3:06:24
    * @param assignmap assignmap
    * @param memberDto 会员信息
    * @param storeId 门店标识
    * @param detailId 订单明细标识
    * @param lastOperatorId 操作人
    * @param isAppoint 是否预约
    * @param nextType 执行类型
    * @return Integer
     */
    public Integer invokingShift(Map<String, Integer> assignmap, MemberBaseDto memberDto, Integer storeId, Integer detailId, 
            Integer isAppoint, Integer lastOperatorId, Integer nextType) {
        
        Integer assignOrderNum =  assignmap.get("assignOrderNum");
        
        Integer assignShiftMahjongEmployeeId =  assignmap.get("assignShiftMahjongEmployeeId");
        
        Integer assignProjectId =  assignmap.get("assignProjectId");
        
        Integer assignEmployeeId = assignmap.get("assignEmployeeId");
        
        Integer isType = assignmap.get("isType");
        
        Integer markTypeInteger = 0;
        
        //根据项目步骤标识、订单明细查询轮牌项目步骤关系（第一步）
        ShiftMahjongProjectStepDto shiftMahjongProjectStepDto = selectShiftMahjongStep(assignProjectId, detailId, assignOrderNum);
        //当为步骤一时执行
        if (nextType == 1) {
            //判断当前员工状态
            Integer state = selectState(assignShiftMahjongEmployeeId);

            //如果该员工忙，则将订单步骤转等待
            if (state != 1) {
                
                //转等待中心
                redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                        Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                //向队列中插入一条指定消息
                /*rabbitService.sendAppointEmployeeMessage(shiftMahjongProjectStepDto.getProjectStep().getProjectStepName(),
                        name, phone, assignEmployeeId);*/
                //增加指定人
                shiftMahjongEmployeeMapper.updateAddAppointNumber(assignShiftMahjongEmployeeId);
                
                ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                shiftMahjongProjectStep.setEmployeeId(assignEmployeeId);
                
                if (isType == 1) {
                    shiftMahjongProjectStep.setIsAssign(1);
                    shiftMahjongProjectStep.setIsDesignate(0);
                }
                else if (isType == 2){
                    shiftMahjongProjectStep.setIsAssign(0);
                    shiftMahjongProjectStep.setIsDesignate(1);
                }
                shiftMahjongProjectStep.setIsCurrent(nextType);
                shiftMahjongProjectStep.setIsAppoint(isAppoint);
                shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
                shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                
                if (assignEmployeeId.intValue() == lastOperatorId.intValue()) {
                    markTypeInteger = 1;
                }
                else {
                    //发送模版消息
                    staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 
                            storeId, 7, lastOperatorId);
                }
            }
            else {
                if (isType == 1) {
                    //调整员工在轮牌走哦那个的位置
                    moveEmployee(assignShiftMahjongEmployeeId, true);
                    //轮牌项目步骤关系指定人员 ,修改订单明细状态
                    updateShiftMahjongProjectStep(assignEmployeeId, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 1, 0, detailId, isAppoint);
                }
                else if (isType == 2){
                    //调整员工在轮牌走哦那个的位置
                    moveEmployee(assignShiftMahjongEmployeeId, false);
                    //轮牌项目步骤关系指定人员 ,修改订单明细状态
                    updateShiftMahjongProjectStep(assignEmployeeId, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 0, 1, detailId, isAppoint);
                }
                
                if (assignEmployeeId.intValue() == lastOperatorId.intValue()) {
                    markTypeInteger = 1;
                }
                else {
                    staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 
                            storeId, 1, lastOperatorId);
                }
            }
        }
        else {
            //将员工与订单明细关系保存到数据库
            /*redisService.zadd(App.Queue.APPOINT_EMPLOYEE + String.valueOf(assignEmployeeId), 
                    Double.valueOf(System.currentTimeMillis()), String.valueOf(shiftMahjongProjectStepDto.getShiftMahjongStepId()));*/
            ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
            shiftMahjongProjectStep.setEmployeeId(assignEmployeeId);
            
            if (isType == 1) {
                shiftMahjongProjectStep.setIsAssign(1);
                shiftMahjongProjectStep.setIsDesignate(0);
            }
            else if (isType == 2){
                shiftMahjongProjectStep.setIsAssign(0);
                shiftMahjongProjectStep.setIsDesignate(1);
            }
            shiftMahjongProjectStep.setIsCurrent(0);
            shiftMahjongProjectStep.setIsAppoint(isAppoint);
            shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
            shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongProjectStepDto.getShiftMahjongStepId());
            shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
            //向队列中插入一条指定消息
            /*rabbitService.sendAppointEmployeeMessage(projectStepName, name, phone, assignEmployeeId);*/
            //增加指定人
            shiftMahjongEmployeeMapper.updateAddAppointNumber(assignShiftMahjongEmployeeId);
            
            //发送模版消息
            staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), storeId, 7, lastOperatorId);
        }
        return markTypeInteger;
    }
    
    /**
     * 判断该明细是否被修改
    * @author 王大爷
    * @date 2015年12月23日 下午2:57:42
    * @param detailId 明细标识
    * @return BaseDto
     */
    public BaseDto selectProjectIsUpdate(Integer detailId) {
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        if (orderDetail.getIsUpdate() == 1) {
            return new BaseDto(1001, "项目已修改过，不能再次修改！");
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
    }
    
    /**
     * 修改项目
    * @author 王大爷
    * @date 2015年10月22日 下午5:51:54
    * @param storeId 门店标识
    * @param employeeId 员工标识
    * @param detailId 明细标识
    * @return ModelAndView
     */
    public ModelAndView updateProjectList(Integer storeId, Integer employeeId, Integer detailId) {
        
        ModelAndView mav = new ModelAndView(View.StaffPage.CHANGE_PROJECT);
        
        int deptId = projectInfoMapper.selectByDetailId(detailId);
        
        DeptProjectBaseDto deptProjectBaseDto = projectService.getDeptProjectByDeptId(deptId);
        mav.addObject("projectCategoryDtoList", deptProjectBaseDto.getProjectCategoryList());
        
        OrderDetailDto orderDetailDto = orderDetailMapper.selectByDetailBaseDto(detailId);
        
        mav.addObject("orderDetailDto", orderDetailDto);
        
        mav.addObject("detailId", detailId);
        
        mav.addObject("projectId", orderDetailDto.getProjectId());
        return mav;
    }
    
    /**
     * 步骤合并
    * @author 王大爷
    * @date 2015年12月23日 下午5:28:09
    * @param detailId 明细标识
    * @param projectInfo 项目信息
    * @return ModelAndView
     */
    public ModelAndView projectCombine(Integer detailId, String projectInfo) {
        ModelAndView mav = new ModelAndView(View.StaffPage.COMBINE_STEP);
        
        OrderDetailDto orderDetailDto = orderDetailMapper.selectByDetailBaseDto(detailId);
        OrderDetailDto oldOrderDetailDto = null;
        try {
            oldOrderDetailDto = (OrderDetailDto)orderDetailDto.deepClone();
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(orderDetailDto.getStepList());
        
        ProjectInfo project = EntityJsonConverter.json2Entity(projectInfo, ProjectInfo.class);
        
        List<ProjectStep> projectStepList = projectStepMapper.queryProjectStepByPIdList(project.getProjectId());
        
        projectStepList = updateStepNameList(projectStepList);
        
        mav.addObject("projectInfo", projectInfo);
        mav.addObject("detailId", detailId);
        mav.addObject("projectStepList", projectStepList);
        mav.addObject("orderDetailDto", orderDetailDto);
        mav.addObject("oldOrderDetailDto", oldOrderDetailDto);
        mav.addObject("project", project);
        return mav;
    }
    
    /**
     * 修改项目步骤名称为轮牌名称
    * @author 王大爷
    * @date 2015年12月28日 下午2:48:43
    * @param list 项目步骤集合
    * @return List<ProjectStep>
     */
    public List<ProjectStep> updateStepNameList (List<ProjectStep> list) {
        for (ProjectStep projectStep : list) {
            ShiftMahjong shiftMahjong = shiftMahjongMapper.selectByPrimaryKey(projectStep.getShiftMahjongId());
            projectStep.setProjectStepName(shiftMahjong.getShiftMahjongName());
        }
        return list;
    }
    
    /**
     * 检验合并步骤是否满足（进行中）
    * @author 王大爷
    * @date 2015年12月28日 上午11:15:10
    * @param chooseStep 合并步骤选择
    * @return BaseDto
     */
    public BaseDto checkoutStep(String chooseStep) {
        JSONArray chooseStepArray =JSONArray.fromObject(chooseStep);
        for (int j = 0; j < chooseStepArray.size(); j++) {
            JSONObject jsonChooseStep = chooseStepArray.getJSONObject(j);
            Integer projectStepId = jsonChooseStep.getInt("projectStepId");
            
            Integer employeeId = jsonChooseStep.getInt("employeeId");
            
            Integer isOver = jsonChooseStep.getInt("isOver");
            
            Integer oldShiftMahjongStepId = jsonChooseStep.getInt("shiftMahjongStepId");
            
            if (isOver == 1) {
                
                ShiftMahjongEmployee oldShiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(oldShiftMahjongStepId);
                
                Map<String, Integer> employeeMap = new HashMap<String, Integer>();
                employeeMap.put("projectStepId", projectStepId);
                employeeMap.put("employeeId", employeeId);
                ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectEmployeeByStepId(employeeMap);
                
                if (shiftMahjongEmployee == null 
                        || oldShiftMahjongEmployee.getShiftMahjongEmployeeId().intValue() 
                        != shiftMahjongEmployee.getShiftMahjongEmployeeId().intValue()) {
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该进行中步骤，不满足合并步骤条件，请修改！");
                }
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 修改项目选择轮牌
    * @author 王大爷
    * @date 2015年10月23日 下午4:49:52
    * @param detailId 明细标识
    * @param projectInfo 项目信息
    * @return ModelAndView
     */
    public ModelAndView confirmUpdateProject(Integer detailId, String projectInfo){
        
        ModelAndView mav = new ModelAndView(View.StaffPage.UPDATE_SHIFTMAHJONG_SERVE);
        ProjectInfo project = EntityJsonConverter.json2Entity(projectInfo, ProjectInfo.class);
        
        List<ShiftMahjongDto> shiftMahjongDtoList = selectShiftProjectEmployee(project.getProjectId());
        
        OrderDetailDto orderDetailDto = orderDetailMapper.selectByDetailBaseDto(detailId);
        mav.addObject("shiftMahjongDtoList", shiftMahjongDtoList);
        mav.addObject("orderDetailDto", orderDetailDto);
        mav.addObject("project", project);
        mav.addObject("projectInfo", projectInfo);
        mav.addObject("detailId", detailId);
        
        return mav;
    }
    
    /**
     * 修改项目保存
    * @author 王大爷
    * @date 2015年10月23日 下午7:43:02
    * @param detailId 明细标识
    * @param projectInfo 项目信息
    * @param employeeObj 指定员工
    * @param nextProjectObj 项目下一步服务步骤
    * @param lastOperatorId 修改员工标识
    * @param storeId 门店标识
    * @return ModelAndView
     */
    @Transactional
    public BaseDto updateSaveDetail (Integer detailId, String projectInfo, String employeeObj, String nextProjectObj, 
            Integer lastOperatorId, Integer storeId) {
        //获取原订单
        OrderInfo orderInfoOld = orderInfoMapper.selectByDetailId(detailId);
        
        List<ShiftMahjongProjectStep> shiftMahjongProjectStepList = shiftMahjongProjectStepMapper.selectStepByDetailId(detailId);
                
        for (int i = 0; i < shiftMahjongProjectStepList.size(); i++) {
            ShiftMahjongProjectStep shiftMahjongProjectStep = shiftMahjongProjectStepList.get(i);
            if (shiftMahjongProjectStep.getIsOver() != 3) {
                if (shiftMahjongProjectStep.getEmployeeId() != null && shiftMahjongProjectStep.getIsOver() == 2) {
                    ShiftMahjongEmployee shiftMahjongEmployee 
                             = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongProjectStep.getShiftMahjongStepId());
                    
                    shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployee.getShiftMahjongEmployeeId());
                    
                    if (shiftMahjongProjectStep.getIsCurrent() == 1) {
                        redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), detailId);
                    }
                }
                else if (shiftMahjongProjectStep.getIsOver() == 1){
                    //查询上个明细对应轮牌员工标识（以修改）
                    ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftMahjongLastEmployee(detailId);
                    
                    if (shiftMahjongEmployee != null) {
                        updateShiftEmployeeState(shiftMahjongEmployee.getShiftMahjongEmployeeId(), detailId, storeId);
                    }
                }
                ShiftMahjongProjectStep record = new ShiftMahjongProjectStep();
                record.setShiftMahjongStepId(shiftMahjongProjectStep.getShiftMahjongStepId());
                record.setIsDeleted(1);
                shiftMahjongProjectStepMapper.updateByPrimaryKey(record);
            }
        }
        
        //修改订单明细状态
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(detailId);
        orderDetail.setDiscountAmount(new BigDecimal(0));
        orderDetail.setRealPrice(new BigDecimal(0));
        orderDetail.setFreeAmount("0.00");
        orderDetail.setOrderRemark("");
        orderDetail.setOrderStatus(3);
        orderDetail.setIsUpdate(1);
        orderDetail.setLastOperatorId(lastOperatorId);
        orderDetailMapper.updateByPrimaryKey(orderDetail);
        
        //查询会员信息
        MemberBaseDto memberBaseDto = new MemberBaseDto();
        if (orderInfoOld.getMemberId() != null) {
            memberBaseDto = memberInfoMapper.selectMemberBaseInfo(orderInfoOld.getMemberId());
        }
        Integer memberId = null;
        Integer levelId = null;
        if (memberBaseDto.getMemberId() != null && memberBaseDto.getMemberId() != 0) {
            memberId = memberBaseDto.getMemberId();
            
            levelId = memberInfoMapper.selectByPrimaryKey(memberId).getLevelId();
        }
        
        //需要修改的项目
        ProjectInfo project = EntityJsonConverter.json2Entity(projectInfo, ProjectInfo.class);
        
        Integer orderId = orderInfoOld.getOrderId();
        
        List<Map<String, Object>> blist = new ArrayList<Map<String, Object>>();
        Map<String, Object> bmap = new HashMap<String, Object>();
        bmap.put("orderType", 1);
        bmap.put("projectId", project.getProjectId());
        bmap.put("projectName", project.getProjectName());
        bmap.put("projectPriceStr", project.getProjectPrice());
        bmap.put("projectCount", 1);
        bmap.put("projectImage", project.getProjectImage());
        blist.add(bmap);
        String objString = JSONArray.fromObject(blist).toString();
        
        addDetail(orderId, employeeObj, objString, nextProjectObj, storeId, memberId, levelId, 0, lastOperatorId, memberBaseDto);
        
        //查询该明细对应的订单中存在的未完成的项目明细
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByNotOverOrderDetail(detailId);
        if (orderDetailList.size() == 0) {
            //修改订单信息状态
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(orderId);
            orderInfo.setOrderStatus(2);
            orderInfoMapper.updateByPrimaryKey(orderInfo);
        }
        
        orderInfoMapper.updateTotalPrice(orderId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        
    }
    
    /**
     * 附加项目（或商品、套餐）
    * @author 王大爷
    * @date 2015年11月5日 上午11:25:27
    * @param orderId 订单标识
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return ModelAndView
     */
    public ModelAndView appendDetail(Integer orderId, Integer storeId, Integer lastOperatorId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        MemberBaseDto memberBaseDto = null;
        if (orderInfo.getMemberId() != null) {
            memberBaseDto = memberInfoMapper.selectMemberBaseInfo(orderInfo.getMemberId());
        }
        else {
            memberBaseDto = new MemberBaseDto();
            memberBaseDto.setName("散客");
            memberBaseDto.setSex(orderInfo.getSex());
        }
        return selectBaseInfo(storeId, lastOperatorId, memberBaseDto, orderId);
    }
    
    /**
     * 查询全部轮牌
    * @author 王大爷
    * @date 2015年11月12日 下午7:44:11
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView selectAllShiftMahjong(Integer storeId){
        ModelAndView mav =  new ModelAndView(View.StaffPage.ALL_SHIFTMAHJONG);
        List<DeptInfoDto> deptList = deptInfoMapper.selectByshiftMahjong(storeId);
        mav.addObject("deptList", deptList);
        mav.addObject("deptListJson", JSONArray.fromObject(deptList).toString());
        if (deptList.size() > 0) {
            mav.addObject("deptId", deptList.get(0).getDeptId());
            mav.addObject("typeNumber", deptList.size());
        }
        return mav;
    }
    
    /**
     * 查询订单详情
    * @author 王大爷
    * @date 2015年11月13日 下午6:23:27
    * @param orderId 订单标识
    * @return BaseDto
     */
    public BaseDto selectOrderInfo(Integer orderId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderInfo);
    }
    
    /**
     * 查询明细详情
    * @author 王大爷
    * @date 2015年12月3日 下午2:27:08
    * @param detailId 明细标识
    * @return BaseDto
     */
    public BaseDto selectOrderDetail (Integer detailId) {
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderDetail);
    }
    
    /**
     * 保存挂账
    * @author 王大爷
    * @date 2015年11月14日 上午11:33:06
    * @param orderId 订单标识
    * @param amount 金额
    * @param remark 备注
    * @param type 类型（1、签单，2、挂账）
    * @return BaseDto
     */
    public BaseDto saveDebtInfo(Integer orderId, BigDecimal amount, String remark, Integer type) {
        
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        BigDecimal discountAmount = null;
        OrderInfo record = new OrderInfo();
        record.setOrderId(orderId);
        if (type == 1) {
            /*record.setFreeAmount(amount);
            record.setOrderRemark(remark);
            discountAmount = orderInfo.getDiscountAmount().subtract((amount.subtract(orderInfo.getFreeAmount())));*/
        }
        else {
            record.setDebtAmount(amount);
            record.setOrderEvaluate(remark);
            discountAmount = orderInfo.getDiscountAmount().subtract((amount.subtract(orderInfo.getDebtAmount())));
        }
        
        BigDecimal zore = new BigDecimal(0);
        if (discountAmount.compareTo(zore) == -1) {
            discountAmount = zore;
        }
        
        record.setDiscountAmount(discountAmount);
        
        orderInfoMapper.updateByPrimaryKey(record);
        
        OrderInfo newOrderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, newOrderInfo.getDiscountAmount().toString());
    }
    
    /**
     * 保存签单
    * @author 王大爷
    * @date 2015年11月14日 上午11:33:06
    * @param detailId 订单标识
    * @param discountAmount 折扣金额
    * @param amount 金额
    * @param remark 备注
    * @return BaseDto
     */
    public BaseDto saveFreeInfo(Integer detailId, BigDecimal discountAmount, String amount, String remark) {
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        OrderDetail record = new OrderDetail();
        record.setDetailId(detailId);
        record.setFreeAmount(amount);
        record.setOrderRemark(remark);

        
        BigDecimal zore = new BigDecimal(0);
        if (discountAmount.compareTo(zore) == -1) {
            discountAmount = zore;
        }
        
        record.setDiscountAmount(discountAmount);
        
        orderDetailMapper.updateByPrimaryKey(record);
        
        //汇总签单总金额
        orderInfoMapper.updateFreeAmount(detailId);
        
        //汇总变更后总价
        orderInfoMapper.updateTotalPrice(orderDetail.getOrderId());
        
        OrderInfo newOrderInfo = orderInfoMapper.selectByPrimaryKey(orderDetail.getOrderId());
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, newOrderInfo.getDiscountAmount().toString());
    }
    
    /**
     * 根据门店标识查询该门店会员信息
    * @author 王大爷
    * @date 2016年1月24日 下午5:02:16
    * @param storeId 门店标识
    * @return BaseDto
     */
    public BaseDto selectMemberList (Integer storeId) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("type", 1);
    	map.put("storeId", storeId);
        List<Map<String, Object>> memberList = memberInfoMapper.selectStoreNamePhone(map);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, memberList);
    }
    
    /**
     * 当员工做完一个项目、更换了项目、更换了人员时员工状态为闲时
    * @author 王大爷
    * @date 2015年10月27日 下午5:51:44
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @param storeId 门店标识
     */
    public void selfMotionExecute(Integer shiftMahjongEmployeeId, Integer storeId){
        List<ShiftMahjongProjectStep> shiftMahjongProjectStepList = shiftMahjongProjectStepMapper.selectAppoint(shiftMahjongEmployeeId);
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
        Set<String> tup = redisService.zrange(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 0, -1);
        ShiftMahjongProjectStep shiftMahjongProjectStep = null;
        Integer isDetailId = null;
        for (Iterator<String> iterator = tup.iterator(); iterator.hasNext();) {
            String waitDetailId = iterator.next();
            for (int i = 0; i < shiftMahjongProjectStepList.size(); i++) {
                //如果为指定
                if (waitDetailId.equals(shiftMahjongProjectStepList.get(i).getDetailId().toString())) {
                    shiftMahjongProjectStep = shiftMahjongProjectStepList.get(i);
                    break;
                }
            }
            if (shiftMahjongProjectStep != null) {
                break;
            }
        }
        
        if (shiftMahjongProjectStep == null) {
            for (Iterator<String> iterator = tup.iterator(); iterator.hasNext();) {
                String waitDetailId = iterator.next();
                OrderDetailStepDto orderDetailStepDto 
                                         = shiftMahjongProjectStepMapper.selectOrderStepByCurrent(Integer.valueOf(waitDetailId));
                if (orderDetailStepDto == null) {
                    redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), Integer.valueOf(waitDetailId));
                    continue;
                }
                List<Integer> levelIdList = projectCommissionMapper.selectLevelIdList(orderDetailStepDto.getProjectStepId());
                //判断明细是否被指定，指定的明细，不能被接受
                if (orderDetailStepDto.getEmployeeInfo() == null  
                        && orderDetailStepDto.getShiftMahjongId().intValue() == shiftMahjongEmployee.getShiftMahjongId().intValue()
                        && levelIdList.contains(shiftMahjongEmployee.getLevelId())) {
                    isDetailId = Integer.valueOf(waitDetailId);
                    break;
                }
            }
        }
        else {
            redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), shiftMahjongProjectStep.getDetailId());
            
            if (shiftMahjongProjectStep.getIsDesignate() == 1) {
                //调整员工在轮牌走哦那个的位置
                moveEmployee(shiftMahjongEmployeeId, false);
                //轮牌项目步骤关系指定人员 ,修改订单明细状态
                updateShiftMahjongProjectStep(shiftMahjongProjectStep.getEmployeeId(), 
                        shiftMahjongProjectStep.getShiftMahjongStepId(), 0, 1, shiftMahjongProjectStep.getDetailId(), 0);
            }
            else {
                //调整员工在轮牌走哦那个的位置
                moveEmployee(shiftMahjongEmployeeId, true);
                //轮牌项目步骤关系指定人员 ,修改订单明细状态
                updateShiftMahjongProjectStep(shiftMahjongProjectStep.getEmployeeId(), 
                        shiftMahjongProjectStep.getShiftMahjongStepId(), 1, 0, shiftMahjongProjectStep.getDetailId(), 0);
            }
            
            //减小指定人数
            shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployeeId);
            
            staffOrderService.senMessage(shiftMahjongEmployee.getEmployeesId(), null, shiftMahjongProjectStep.getShiftMahjongStepId(), 
                    storeId, 1, null);
        }
        
        if (isDetailId != null) {
            redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), isDetailId);
            OrderDetailStepDto orderDetailStepDto = shiftMahjongProjectStepMapper.selectOrderStepByCurrent(isDetailId);
            //调整员工在轮牌走哦那个的位置
            moveEmployee(shiftMahjongEmployeeId, false);
            //轮牌项目步骤关系指定人员 ,修改订单明细状态
            updateShiftMahjongProjectStep(shiftMahjongEmployee.getEmployeesId(), orderDetailStepDto.getShiftMahjongStepId(), 0, 0, isDetailId, 0);
            
            staffOrderService.senMessage(shiftMahjongEmployee.getEmployeesId(), null, orderDetailStepDto.getShiftMahjongStepId(), storeId, 2, null);
        }
    }
    
    /**
     * 检查微信id是否关注了公众号
    * @author 张进军
    * @date Aug 19, 2015 9:14:43 PM
    * @param openId     微信id
    * @return           1：关注，0：未关注
     */
    private int isSubscribe(String openId){
        String subscribe = redisService.hget(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, openId);
        if ("1".equals(subscribe)) {
            return 1;
        }
        return 0;
    }
    
    /**
     * 
    * @author 王大爷
    * @date 2015年9月18日 下午8:19:46
    * @param field field
    * @param storeId storeId
    * @return String
     */
    public String getOrderCode(String field, Integer storeId){
        String numString = redisService.hget(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), field);
        for (int i = 0 ; i < 1 ;) {
            if (numString == null) {
                i = 1;
                String val = DateUtil.getCurDateString() + "0001";
                redisService.hset(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), "order_info", val);
                redisService.hset(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), "order_detail", "0");
                return val;
            } 
            else {
                String val = redisService.watchSet(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), storeId, field);
                if (val != null) {
                    i = 1;
                    return val;
                }
            }
        }
        return null;
    }
}
