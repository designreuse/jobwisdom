package com.zefun.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.dto.ShiftMahjongProjectStepDto;
import com.zefun.web.entity.ShiftMahjongEmployee;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.ShiftMahjongService;
import com.zefun.wechat.service.StaffService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 员工端综合controller
* @author 王大爷
* @date 2015年8月17日 上午10:21:20
 */
@Controller
public class StaffController extends BaseController {
    /** 轮牌Service*/
    @Autowired private ShiftMahjongService shiftMahjongService;

    /** 员工端service*/
    @Autowired private StaffService staffService;
    
    /** 会员*/
    @Autowired private MemberInfoService memberInfoService;
    
//    /**测试门店*/
//    private int storeId = 1005;
//    /**测试员工*/
//    private int employeeId = 235;

    /**
     * 查看员工主页
    * @author 王潇
    * @date Aug 19, 2015 4:21:25 PM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request        请求对象
    * @param response       返回对象
    * @return           员工主页面
     */
    @RequestMapping(value = Url.Staff.VIEW_HOME, method = RequestMethod.GET)
    public ModelAndView homeView(@PathVariable String storeId, @PathVariable int businessType, 
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        ModelAndView mav = staffService.homeView(employeeId);
        return shiftMahjongService.staffPerformance(mav);
    }
    
    /**
     * 直接进入员工接待中心
    * @author 王大爷
    * @date 2015年10月18日 下午2:59:56
    * @param request        请求对象
    * @param response       返回对象
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_RECEPTION, method = RequestMethod.GET)
    public ModelAndView receptionView(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        return staffService.receptionView(storeId);
    }
    
    /**
     * 访问员工注册页面
    * @author 王潇
    * @date Aug 19, 2015 7:08:55 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   会员注册页面
     */
    @RequestMapping(value = Url.Staff.VIEW_REGISTER, method = RequestMethod.GET)
    public ModelAndView registerView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        String storeAccount = getStoreAccount(request);
        return staffService.registerView(storeAccount);
    }

    /**
     * 员工注册
    * @author 王潇
    * @date Aug 19, 2015 7:13:47 PM
    * @param phone          手机号
    * @param password       密码
    * @param request        请求对象
    * @param response       返回对象
    * @return               成功返回码0，返回值为跳转地址；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.Staff.ACTION_LOGIN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto login(String phone, String password, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        String storeAccount = getStoreAccount(request);
//        int storeId = getStoreId(request);
        return staffService.login(storeAccount, phone, password, openId, request);
    }
    
    
    /**
     * 员工注销操作
    * @author 张进军
    * @date Dec 11, 2015 11:25:43 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.Staff.ACTION_LOGOUT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto logout(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
//        int storeId = getStoreId(request);
        Integer employeeId = getUserIdByOpenId(openId);
        String storeAccount = getStoreAccount(request);
        return staffService.logout(storeAccount, employeeId, request);
    }
    
    
    /**
     * 根据手牌号查询订单信息
    * @author 王大爷
    * @date 2015年8月20日 下午4:23:51
    * @param handOrderCode 手牌号
    * @param request        请求对象
    * @param response       返回对象
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.ACTION_SELECT_BASEINFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectBaseInfo(String handOrderCode, HttpServletRequest request, 
            HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        
        return staffService.selectBaseInfo(storeId, handOrderCode);
    }
    
    /**
     * 根据会员标识查询会员信息
    * @author 张进军
    * @date Jan 17, 2016 1:12:54 PM
    * @param detailId   会员标识
    * @param projectId 项目标识
    * @param request    请求对象
    * @param response   响应对象
    * @return   会员信息
     */
    @RequestMapping(value = Url.Staff.ACTION_SETTING_PROJECT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto settingProject(Integer detailId, Integer projectId, HttpServletRequest request, 
            HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffService.settingProject(detailId, projectId);
    }
    

    /**
     * 返回类别信息到页面
    * @author 王大爷
    * @date 2015年8月21日 下午7:33:32
    * @param detailId 明细标识
    * @param request        请求对象
    * @param response       返回对象
    * @param deptId 部门标识
    * @return 返回类别信息到页面
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = Url.Staff.VIEW_SELECT_CATEGORY)
    public ModelAndView selectCategory(Integer detailId, HttpServletRequest request, 
            HttpServletResponse response, Integer deptId) throws UnsupportedEncodingException {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        return staffService.selectCategory(storeId, detailId, deptId);
    }
    
    /**
     * 添加服务
    * @author 老王
    * @date 2016年7月11日 下午3:53:31 
    * @param orderId 订单标识
    * @param request        请求对象
    * @param response       返回对象
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_ADD_DETAIL_SERVER, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addDetailServer (Integer orderId, HttpServletRequest request, HttpServletResponse response) {
    	String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer lastOperatorId = getUserIdByOpenId(openId);
        return staffService.addDetailServer(orderId, storeId, lastOperatorId);
    }
    
    /**
     * 查看排班设置
    * @author 老王
    * @date 2016年7月11日 下午6:13:13 
    * @param request        请求对象
    * @param response       返回对象
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_SELECT_VIEW_SCHEDULING, method = RequestMethod.GET)
    public ModelAndView selectViewScheduling (HttpServletRequest request, HttpServletResponse response) {
    	String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer employee = getUserIdByOpenId(openId); 
        return staffService.selectViewScheduling(employee);
    }
    
    /**
     * 查询当前项目所用到的轮牌信息
    * @author 王大爷
    * @date 2015年9月21日 下午4:38:54
    * @param objString 订单明细obj
    * @param totilString 汇总信息
    * @param memberId 会员标识
    * @param sex    消费者性别
    * @param orderId 订单标识
    * @param request        请求对象
    * @param response       返回对象
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_MEMBER_SHIFTMAHJONG_SERVE)
    public ModelAndView selectShiftProjectEmployee(String objString, String totilString, Integer memberId, String sex, Integer orderId, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        JSONArray jsonArray =JSONArray.fromObject(objString);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> showList = new ArrayList<Map<String, Object>>();
        /*//给项目做编号
        Integer num = 1;*/
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer orderType = jsonObject.getInt("orderType");
            Integer projectId = jsonObject.getInt("projectId");
            String projectName = jsonObject.getString("projectName");
            String  projectPriceStr = jsonObject.getString("projectPriceStr");
            Integer projectCount = jsonObject.getInt("projectCount");
            String projectImage = jsonObject.getString("projectImage");
            
            Map<String, Object> showMap = new HashMap<String, Object>();
            showMap.put("orderType", orderType);
            showMap.put("projectId", projectId);
            showMap.put("projectName", projectName);
            showMap.put("projectPrice", projectPriceStr);
            showMap.put("projectCount", projectCount);
            showMap.put("projectImage", projectImage);
            if (orderType == 1) {
                List<ShiftMahjongDto> shiftMahjongDtoList = staffService.selectShiftProjectEmployee(projectId);
                Map<String, Object> map = new HashMap<String, Object>();
                /*map.put("num", num);*/
                //当选择多个项目的时候，页面必须多加加一条记录
                for (int j = 0; j < projectCount; j++) {
                    map.put("projectId", projectId);
                    map.put("projectName", projectName);
                    map.put("projectPrice", projectPriceStr);
                    map.put("projectImage", projectImage);
                    map.put("shiftMahjongDtoList", shiftMahjongDtoList);
                    list.add(map);
                    /*num++;*/
                }
            }
            showList.add(showMap);
        }
        JSONObject totilObj = JSONObject.fromObject(totilString);
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", list);
        mav.addObject("objString", objString);
        
        //项目个数
        mav.addObject("totilSize", list.size());
        
        //展示订单
        mav.addObject("number", totilObj.getString("number"));
        mav.addObject("totilMoney", totilObj.getString("totilMoney"));
        //展示订单明细
        mav.addObject("showList", showList);

        MemberBaseDto memberBaseDto = memberInfoService.getMemberBaseInfo(memberId, false);
        if (memberBaseDto == null) {
            memberBaseDto = new MemberBaseDto();
            memberBaseDto.setSex(sex);
            memberBaseDto.setName("散客");
            memberBaseDto.setPhone("未知");
        }
        
        mav.addObject("orderId", orderId);
        mav.addObject("memberBaseDto", memberBaseDto);
        mav.setViewName(View.StaffPage.MEMBER_SHIFTMAHJONG_SERVE);
        return mav;
    }
    
    /**
     * 开单
    * @author 王大爷
    * @date 2015年9月19日 下午2:00:39
    * @param request 返回
    * @param response 请求
    * @param objString 订单明细obj
    * @param employeeObj 指定人员
    * @param nextProjectObj 项目下一步服务步骤
    * @param memberId 会员标识
    * @param sex    消费者性别
    * @param orderId 订单标识
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Staff.ACTION_ADD_ORDER)
    @ResponseBody
    public BaseDto addOrder(HttpServletRequest request, HttpServletResponse response, String objString, String employeeObj, String nextProjectObj,
             Integer memberId, String sex, Integer orderId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer lastOperatorId = getUserIdByOpenId(openId);
        return staffService.addOrder(objString, employeeObj, nextProjectObj, memberId, sex, storeId, lastOperatorId, 0, orderId);
    }*/
    
    /**
     * 附加项目（或商品、套餐）
    * @author 王大爷
    * @date 2015年11月5日 上午11:08:50
    * @param request 返回
    * @param response 请求
    * @param orderId 订单标识
    * @return ModelAndView
     */
    /*@RequestMapping(value = Url.Staff.VIEW_APPEND_DETAIL, method = RequestMethod.POST)
    public ModelAndView appendDetail(HttpServletRequest request, HttpServletResponse response, Integer orderId){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer lastOperatorId = getUserIdByOpenId(openId);
        return staffService.appendDetail(orderId, storeId, lastOperatorId);
    }*/
    
    /**
     * 查询明细是否修改
    * @author 王大爷
    * @date 2015年12月23日 下午3:04:11
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.SELECT_PROJECT_ISUPDATE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectProjectIsUpdate (HttpServletRequest request, HttpServletResponse response, Integer detailId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffService.selectProjectIsUpdate(detailId);
    }
    
    /**
     * 修改项目
    * @author 王大爷
    * @date 2015年10月22日 下午5:49:40
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_UPDATE_PROJECT_LIST)
    public ModelAndView updateProjectList(HttpServletRequest request, HttpServletResponse response, Integer detailId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer employeeId = getUserIdByOpenId(openId);
        return staffService.updateProjectList(storeId, employeeId, detailId);
    }
    
    /**
     * 修改项目确认
    * @author 王大爷
    * @date 2015年10月23日 下午3:22:06
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @param projectInfo 项目信息（json）
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_CONFIRM_UPDATE_PROJECT, method = RequestMethod.POST)
    public ModelAndView confirmUpdateProject(HttpServletRequest request, HttpServletResponse response, Integer detailId, 
            String projectInfo){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffService.confirmUpdateProject(detailId, projectInfo);
    }
    
    /**
     * 修改项目保存
    * @author 王大爷
    * @date 2015年10月23日 下午8:36:54
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @param projectInfo 项目信息
    * @param employeeObj 指定员工
    * @param nextProjectObj 项目下一步服务步骤
    * @return ModelAndView
     */
    /*@RequestMapping(value = Url.Staff.ACTION_UPDATE_SAVE_DETAIL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateSaveDetail(HttpServletRequest request, HttpServletResponse response, Integer detailId, String projectInfo, 
            String employeeObj, String nextProjectObj) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        int storeId = getStoreIdByOpenId(openId);
        return staffService.updateSaveDetail(detailId, projectInfo, employeeObj, nextProjectObj, employeeId, storeId);
    }*/
    
    /**
     * 查询员工订单页面
    * @author 王大爷
    * @date 2015年9月29日 下午2:57:39
    * @param employeesId 
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    public ModelAndView serveOrder(Integer employeesId, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        List<ShiftMahjongProjectStepDto> shiftMahjongProjectStepDtoList =  staffService.serveOrder(employeesId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("shiftMahjongProjectStepDtoList", shiftMahjongProjectStepDtoList);
        return mav;
    }
    
    /**
     * 全部轮牌
    * @author 王大爷
    * @date 2015年11月12日 下午7:36:12
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    /*@RequestMapping(value = Url.Staff.SELECT_ALL_SHIFTMAHJONG)
    public ModelAndView selectAllShiftMahjong(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        return staffService.selectAllShiftMahjong(storeId);
    }*/
    
    /**
     * 轮牌上移
    * @author 王大爷
    * @date 2015年8月13日 上午11:48:27
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongEmployee 轮牌员工信息
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.STAFF_UPWARD_IMG, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto upwardIMG(HttpServletRequest request, HttpServletResponse response, ShiftMahjongEmployee shiftMahjongEmployee){
        return shiftMahjongService.upwardIMG(shiftMahjongEmployee);
    }
    
    /**
     * 轮牌下移
    * @author 王大爷
    * @date 2015年8月13日 上午11:48:27
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongEmployee 轮牌员工信息
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.STAFF_NEXT_IMG, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto nextIMG(HttpServletRequest request, HttpServletResponse response, ShiftMahjongEmployee shiftMahjongEmployee){
        return shiftMahjongService.nextIMG(shiftMahjongEmployee);
    }
    
    /**
     * 轮牌置顶
    * @author 王大爷
    * @date 2015年12月22日 下午3:32:33
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongEmployee 轮牌员工信息
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.STAFF_TO_TOP_IMG, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto toTopIMG (HttpServletRequest request, HttpServletResponse response, ShiftMahjongEmployee shiftMahjongEmployee) {
        return shiftMahjongService.toTopIMG(shiftMahjongEmployee);
    }
    
    /**
     * 轮牌置低
    * @author 王大爷
    * @date 2015年12月22日 下午3:32:33
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongEmployee 轮牌员工信息
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.STAFF_TO_END_IMG, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto toEndIMG (HttpServletRequest request, HttpServletResponse response, ShiftMahjongEmployee shiftMahjongEmployee) {
        return shiftMahjongService.toEndIMG(shiftMahjongEmployee);
    }
    
    /**
     * 修改员工状态
    * @author 王大爷
    * @date 2015年9月16日 下午8:25:29
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongEmployeeId 轮牌员工信息标识
    * @param state 状态
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Staff.UPDATE_STATE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateState(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongEmployeeId, Integer state){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        return shiftMahjongService.updateState(shiftMahjongEmployeeId, state, storeId);
    }*/
    
    /**
     *  查询订单详情
    * @author 王大爷
    * @date 2015年11月13日 下午6:27:52
    * @param request 返回
    * @param response 请求
    * @param orderId 订单标识
    * @return  BaseDto
     */
    @RequestMapping(value = Url.Staff.SELECT_ORDERINFO)
    @ResponseBody
    public BaseDto selectOrderInfo(HttpServletRequest request, HttpServletResponse response, Integer orderId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffService.selectOrderInfo(orderId);
    }
    
    /**
     * 保存挂账信息
    * @author 王大爷
    * @date 2015年11月14日 上午11:38:20
    * @param request 返回
    * @param response 请求
    * @param orderId 订单标识
    * @param amount 签单金额
    * @param remark 签单备注
    * @param type 类型（1、签单，2、挂账）
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.SAVE_DEBT_INFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveDebtInfo(HttpServletRequest request, HttpServletResponse response, Integer orderId, BigDecimal amount, 
            String remark, Integer type) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffService.saveDebtInfo(orderId, amount, remark, type);
    }
    
    /**
     * 查询明细详情
    * @author 王大爷
    * @date 2015年12月3日 下午2:28:42
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.SELECT_ORDER_DETAIL)
    @ResponseBody
    public BaseDto selectOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer detailId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffService.selectOrderDetail(detailId);
    }
    
    /**
     * 保存改单信息
    * @author 王大爷
    * @date 2015年11月14日 上午11:38:20
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @param discountAmount 折扣金额
    * @param amount 签单金额
    * @param remark 签单备注
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.SAVE_FREE_INFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveFreeInfo(HttpServletRequest request, HttpServletResponse response, Integer detailId, BigDecimal discountAmount, String amount, 
            String remark) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffService.saveFreeInfo(detailId, discountAmount, amount, remark);
    }
    
    /**
     * 根据门店标识查询该门店会员信息
    * @author 王大爷
    * @date 2016年1月24日 下午5:03:44
    * @param request 返回
    * @param response 请求
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.SELECT_MEMBER_LIST, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectMemberList(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        return staffService.selectMemberList(storeId);
    }
    
    /**
     * 
    * @author 王大爷
    * @date 2015年12月6日 下午3:23:02
    * @param shiftMahjongEmployeeId 员工轮牌标识
    * @param storeId 门店标识
     */
    /*public void selfMotionExecute(Integer shiftMahjongEmployeeId, Integer storeId) {
        staffService.selfMotionExecute(shiftMahjongEmployeeId, storeId);
    }*/
}
