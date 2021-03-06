package com.zefun.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.service.SystemSettingService;

import net.sf.json.JSONObject;

/**
 * 系统设置控制类
* @author 张进军
* @date Nov 20, 2015 7:05:45 PM 
*/
@Controller
public class SystemSettingController extends BaseController{
    
    /** 系统设置服务对象 */
    @Autowired
    private SystemSettingService systemSettingService;
    
    
    /**
     * 个人设置操作
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   个人设置页面
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_PERSON_SETTING)
    public ModelAndView personSettingView(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView(View.Setting.PERSON_SETTING);
    }
    
    
    /**
     * 个人设置操作
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param employeeInfo   员工资料
    * @param request        请求对象
    * @param response       返回对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_PERSON_SETTING, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto personSettingAction(EmployeeInfo employeeInfo, 
            HttpServletRequest request, HttpServletResponse response){
        int userId = getUserId(request);
        employeeInfo.setEmployeeId(userId);
        return systemSettingService.personSettingAction(employeeInfo, request);
    }
    
    /**
     * 修改账户密码
    * @author 张进军
    * @date Nov 20, 2015 10:05:26 PM
    * @param oldPwd     原密码
    * @param newPwd     新密码
    * @param request        请求对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_UPDATE_PASSWORD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updatePwdAction(String oldPwd, String newPwd,
            HttpServletRequest request){
        int userId = getUserId(request);
        return systemSettingService.updatePwdAction(userId, oldPwd, newPwd);
    }
    
    
    /**
     * 访问门店基础设置页面
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_BASE_SETTING)
    public ModelAndView baseSettingView(HttpServletRequest request, HttpServletResponse response){
        int storeId = getStoreId(request);
        return systemSettingService.baseSettingView(storeId);
    }
    
    
    /**
     * 门店基础设置操作
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param request        请求对象
    * @param storeSetting   门店基础数据
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_BASE_SETTING, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto baseSettingAction(HttpServletRequest request, StoreSetting storeSetting){
        int storeId = getStoreId(request);
        storeSetting.setStoreId(storeId);
        return systemSettingService.baseSettingAction(storeSetting);
    }
    
    
    /**
     * 我的分店列表页面
    * @author 张进军
    * @date Dec 14, 2015 10:56:29 PM
    * @param request    请求对象
    * @return   分店列表页面
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_BRANCH_LIST)
    public ModelAndView branchListView(HttpServletRequest request){
        int storeId = getStoreId(request);
        return systemSettingService.branchListView(storeId);
    }
    
    
    /**
     * 查看门店微信设置页面
    * @author 张进军
    * @date Dec 25, 2015 1:56:02 PM
    * @param request	请求对象
    * @return	门店微信设置页面
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_STORE_WECHAT)
    public ModelAndView storeWechatView(HttpServletRequest request){
        String storeAccount = getStoreAccount(request);
        return systemSettingService.storeWechatView(storeAccount);
    }
    
    
    /**
     * 门店微信设置操作
    * @author 张进军
    * @date Dec 25, 2015 2:45:48 PM
    * @param storeWechat	门店微信关联信息
    * @param request	请求对象
    * @return	成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_STORE_WECHAT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto storeWechatAction(StoreWechat storeWechat, HttpServletRequest request){
        String storeAccount = getStoreAccount(request);
    	return systemSettingService.storeWechatAction(storeWechat, storeAccount);
    }
    
    
    /**
     * 门店系统使用状态查询
    * @author 张进军
    * @date Dec 25, 2015 5:29:48 PM
    * @param request	请求对象
    * @return	门店系统使用状态页面
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_STORE_USAGE)
    public ModelAndView storeUsageView(HttpServletRequest request){
    	int storeId = getStoreId(request);
    	return systemSettingService.storeUsageView(storeId);
    }
    
    
    /**
     * 清除测试数据，切换到正式营业状态
    * @author 张进军
    * @date Dec 25, 2015 8:00:51 PM
    * @param request	请求对象
    * @return	成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_CLEAN_DATA, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto cleanDataAction(HttpServletRequest request){
    	int storeId = getStoreId(request);
    	return systemSettingService.cleanDataAction(storeId);
    }
    
    /**
     * 访问门店分享设置页面
    * @author 高国藩
    * @date 2016年1月7日 下午3:26:22
    * @param request   请求
    * @return          页面
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_SHARE)
    public ModelAndView viewShare(HttpServletRequest request){
        int storeId = getStoreId(request);
        return systemSettingService.viewShare(storeId);
    }
    
    /**
     * 保存门店分享设置
    * @author 高国藩
    * @date 2016年1月7日 下午3:26:47
    * @param request          请求
    * @param shareReward      设置内容
    * @return                 请求状态
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_SAVE_SHARE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionSaveShareSetting(HttpServletRequest request, @RequestBody JSONObject shareReward){
        int storeId = getStoreId(request);
        return systemSettingService.actionUpdateShare(storeId, shareReward);
    }
    
    
    /**
     *   权限页面
    * @author 骆峰
    * @date 2016年7月21日 下午3:47:27
    * @param request request
    * @return ModelAndView
     */
    @RequestMapping(value = Url.SystemSetting.SYSTEM_VIEW_SHOWROLE)
    @ResponseBody
    public ModelAndView viewRole(HttpServletRequest request){
        String storeAccount = getStoreAccount(request);
        return systemSettingService.viewRole(storeAccount);
    }
    
    /**
     * 查询系统角色
    * @author 骆峰
    * @date 2016年7月21日 下午5:16:56
    * @param request request
    * @param roleId roleId
    * @param accountRoleId accountRoleId
    * @return BaseDto
     */
    @RequestMapping(value = Url.SystemSetting.SYSTEM_VIEW_SELECTROLE)
    @ResponseBody
    public BaseDto viewAddRole(HttpServletRequest request, Integer roleId, Integer accountRoleId){
        String storeAccount = getStoreAccount(request);
        return systemSettingService.viewAddRole(storeAccount, roleId, accountRoleId);
    }
    
    /**
     *   保存角色
    * @author 骆峰
    * @date 2016年7月22日 上午10:32:13
    * @param request request
    * @param fristMemu fristMemu
    * @param secendMemu secendMemu
    * @param accountRoleName accountRoleName
    * @param roleId roleId
    * @param accountRoleId accountRoleId
    * @return BaseDto
     */
    @RequestMapping(value = Url.SystemSetting.SYSTEM_VIEW_SAVEROLE)
    @ResponseBody
    public BaseDto viewSaveRole(HttpServletRequest request, String [] fristMemu, String []  secendMemu,
            String accountRoleName, Integer roleId, Integer accountRoleId){
        String storeAccount = getStoreAccount(request);
        return systemSettingService.viewSaveRole(storeAccount, fristMemu, secendMemu, accountRoleName, roleId, accountRoleId);
        
    }
    
    
    /**
     *  查询系统角色
    * @author 骆峰
    * @date 2016年7月22日 下午12:05:06
    * @param request request
    * @param accountRoleId accountRoleId
    * @return BaseDto
     */
    @RequestMapping(value = Url.SystemSetting.SYSTEM_VIEW_DELETEROLE)
    @ResponseBody
    public BaseDto viewDeleteRole(HttpServletRequest request, Integer accountRoleId){
        String storeAccount = getStoreAccount(request);
        return systemSettingService.viewDeleteRole(storeAccount, accountRoleId);
    }
    
}
