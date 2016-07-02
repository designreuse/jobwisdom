package com.zefun.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.MemberScreening;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ServicePlanInfo;
import com.zefun.web.entity.ServicePlanTemp;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ServicePlanInfoMapper;
import com.zefun.web.mapper.ServicePlanTempMapper;

import net.sf.json.JSONArray;


/**
 * 服务计划安排
* @author 骆峰
* @date 2016年6月21日 下午4:57:52
 */
@Service
@Transactional
public class ServicePlansService {

    /** 服务计划表 */
    @Autowired private ServicePlanInfoMapper servicePlanInfoMapper;
    /** 服务计划表 */
    @Autowired private EmployeeInfoMapper employeeInfoMapper;
    /** 服务计划表 */
    @Autowired private MemberLevelMapper memberLevelMapper;
    /** 服务计划表 */
    @Autowired private MemberScreeningMapper memberScreeningMapper;
    /** 服务计划表 */
    @Autowired private ProjectInfoMapper projectInfoMapper;
    /** 服务计划模板 */
    @Autowired private ServicePlanTempMapper servicePlanTempMapper;
    
    /**
     * 服务计划页面
    * @author 高国藩
    * @date 2016年6月30日 下午4:49:23
    * @param storeId storeId
    * @return        ModelAndView
     */
    public ModelAndView viewServicePlans(Integer storeId) {
        ModelAndView view = new ModelAndView(View.ServicePlans.VIEW_SERVICE_PLAN);
        ServicePlanInfo servicePlanInfo = new ServicePlanInfo();
        servicePlanInfo.setStoreId(storeId);
        List<ServicePlanInfo> servicePlanInfos = servicePlanInfoMapper.selectByProperites(servicePlanInfo);
        List<EmployeeInfo> employeeInfos = employeeInfoMapper.selectEmployeeByStoreId(storeId);
        List<MemberLevelDto> level = memberLevelMapper.selectByStoreId(storeId);
        List<MemberScreening> screen = memberScreeningMapper.selectByStoreId(storeId);
        List<ProjectInfo> projectInfos = projectInfoMapper.selectByStoreId(storeId);
        ServicePlanTemp servicePlanTemp = new ServicePlanTemp();
        servicePlanTemp.setStoreId(storeId);
        servicePlanTemp.setIsDeleted(0);
        List<ServicePlanTemp> servicePlanTemps = servicePlanTempMapper.selectByProperties(servicePlanTemp);
        view.addObject("servicePlanTemps", servicePlanTemps);
        view.addObject("servicePlanInfos", servicePlanInfos);
        view.addObject("employeeInfos", employeeInfos);
        view.addObject("level", JSONArray.fromObject(level));
        view.addObject("screen", JSONArray.fromObject(screen));
        view.addObject("projectInfos", projectInfos);
        view.addObject("servicePlanTemps", servicePlanTemps);
        return view;
    }

    /**
     * 新增或修改
    * @author 高国藩
    * @date 2016年6月30日 下午6:41:12
    * @param servicePlanInfo  servicePlanInfo
    * @return      BaseDto
     * @throws ParseException 
     */
    @Transactional
    public BaseDto saveServicePlans(ServicePlanInfo servicePlanInfo) throws ParseException {
        // 判断时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MINUTE, 30);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date date = sdf.parse(servicePlanInfo.getTopicTime());
        // 提醒时间,第一判断设定的提醒时间是否在30分钟后,第二,判断提醒时间是否在服务时间之前
        Calendar calendarTopic = Calendar.getInstance();
        calendarTopic.setTime(date);
        calendarTopic.set(Calendar.SECOND, 0);
        calendarTopic.set(Calendar.MINUTE, 0);
        
        Calendar isOk = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date serviceTime = sdf2.parse(servicePlanInfo.getServiceTime());
        isOk.setTime(serviceTime);
//        // 判断设置的时间实在在当前时间之后
//        if (Calendar.getInstance().after(isOk)){
//            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "不可设置过期的时间,请将时间设置在当前时间之后");
//        }
        // 判断服务的时间在提醒时间之后
        if (isOk.after(calendarTopic)){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "服务的时间要在提醒的时间之后啊,要不咋提醒啊");
        }
        if (!calendarTopic.after(calendar)){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "为了您的精准推送,请将时间设置为当前时间的30分钟后");
        }
        
        if (servicePlanInfo.getsId() == null){
            servicePlanInfoMapper.insertSelective(servicePlanInfo);
        }
        else {
            servicePlanInfoMapper.updateByPrimaryKeySelective(servicePlanInfo);
        }
        List<ServicePlanInfo> servicePlanInfos = servicePlanInfoMapper.selectByProperites(servicePlanInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, servicePlanInfos.get(0));
    }
    

    /**
     * 删除服务计划
    * @author 高国藩
    * @date 2016年7月1日 上午11:00:41
    * @param servicePlanInfo servicePlanInfo
    * @return                BaseDto
     */
    @Transactional
    public BaseDto deleteServicePlans(ServicePlanInfo servicePlanInfo) {
        servicePlanInfoMapper.deleteByPrimaryKey(servicePlanInfo.getsId());
        return new BaseDto(0, null);
    }

    /**
     * 服务计划模板页面
    * @author 高国藩
    * @date 2016年7月1日 下午12:03:48
    * @param storeId storeId
    * @return        ModelAndView
     */
    public ModelAndView viewServiceTemp(Integer storeId) {
        ModelAndView view = new ModelAndView(View.ServicePlans.VIEW_SERVICE_TEMP);
        ServicePlanTemp servicePlanTemp = new ServicePlanTemp();
        servicePlanTemp.setStoreId(storeId);
        servicePlanTemp.setIsDeleted(0);
        List<ServicePlanTemp> servicePlanTemps = servicePlanTempMapper.selectByProperties(servicePlanTemp);
        List<ProjectInfo> projectInfos = projectInfoMapper.selectByStoreId(storeId);
        view.addObject("projectInfos", projectInfos);
        view.addObject("servicePlanTemps", servicePlanTemps);
        return view;
    }

    /**
     * 新增或修改操作
    * @author 高国藩
    * @date 2016年7月1日 下午5:40:23
    * @param servicePlanTemps  servicePlanTemps
    * @param storeId           storeId
    * @return                  BaseDto
     */
    @Transactional
    public BaseDto saveServicePlansTemp(List<ServicePlanTemp> servicePlanTemps, Integer storeId) {
        for (int i = 0; i < servicePlanTemps.size(); i++) {
            ServicePlanTemp servicePlanTemp = servicePlanTemps.get(i);
            servicePlanTemp.setIsDeleted(0);
            servicePlanTemp.setStoreId(storeId);
            if (servicePlanTemp.gettId() != null){
                servicePlanTempMapper.updateByPrimaryKeySelective(servicePlanTemp);
            }
            else {
                ServicePlanTemp query = new ServicePlanTemp();
                query.setStoreId(storeId);
                if (servicePlanTempMapper.selectByProperties(query).size() >= 5){
                    return new BaseDto(-1, "模板最多可添加五个, 请注意上限");
                }
                else {
                    servicePlanTempMapper.insertSelective(servicePlanTemp);
                }
            }
        }
        return new BaseDto(0, servicePlanTemps);
    }

    /**
     * 搜索模板
    * @author 高国藩
    * @date 2016年7月1日 下午6:41:24
    * @param tId tId
    * @return    BaseDto
     */
    @Transactional
    public BaseDto selectServicePlansTemp(Integer tId) {
        ServicePlanTemp servicePlanTemp = new ServicePlanTemp();
        servicePlanTemp.settId(tId);
        List<ServicePlanTemp> servicePlanTemps = servicePlanTempMapper.selectByProperties(servicePlanTemp);
        ServicePlanTemp planTemp = servicePlanTemps.get(0);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, planTemp.getTopicDay());
        calendar.add(Calendar.HOUR_OF_DAY, planTemp.getTopicHoure());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        ServicePlanInfo servicePlanInfo = new ServicePlanInfo();
        servicePlanInfo.setTopicTime(sdf.format(calendar.getTime()));
        servicePlanInfo.setTheme(planTemp.getTheme());
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, planTemp.getServiceDay());
        servicePlanInfo.setServiceTime(sdf.format(calendar.getTime()));
        servicePlanInfo.setServiceProjectName(planTemp.getServiceProjectName());
        servicePlanInfo.setIsSms(planTemp.getIsSms());
        return new BaseDto(0, servicePlanInfo);
    }

    /**
     * 删除模板
    * @author 高国藩
    * @date 2016年7月2日 下午2:20:13
    * @param tId  tId
    * @return     BaseDto
     */
    @Transactional
    public BaseDto deleteServicePlansTemp(Integer tId) {
        servicePlanTempMapper.deleteByPrimaryKey(tId);
        return new BaseDto(0, null);
    }

}
