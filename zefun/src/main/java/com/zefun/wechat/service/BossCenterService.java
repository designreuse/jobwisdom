package com.zefun.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.service.RedisService;
import com.zefun.web.service.StoreInfoService;

/**
 * 老板模块中心服务类
* @author 张进军
* @date Mar 1, 2016 3:19:09 PM
 */
@Service
public class BossCenterService {

    /** 门店信息服务对象 */
    @Autowired
    private StoreInfoService storeInfoService;
    
    /** 员工信息操作对象 */
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /** redis api服务对象 */
    @Autowired
    private RedisService redisService;
    
    /**
     * 访问老板模块首页
    * @author 张进军
    * @date Mar 1, 2016 3:22:56 PM
    * @param storeId        门店标识
    * @param employeeId     员工标识
    * @return   老板模块首页
     */
    public ModelAndView homeView(int storeId, int employeeId) {
        ModelAndView mav = new ModelAndView(View.BossPage.HOME);
        StoreInfo storeInfo = storeInfoService.getByStoreId(storeId);
        EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeId);
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("employeeInfo", employeeInfo);
        mav.addObject("signStatus", redisService.hget(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, employeeId));
        return mav;
    }
}
