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
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ServicePlanInfoMapper;

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
        view.addObject("servicePlanInfos", servicePlanInfos);
        view.addObject("employeeInfos", employeeInfos);
        view.addObject("level", JSONArray.fromObject(level));
        view.addObject("screen", JSONArray.fromObject(screen));
        view.addObject("projectInfos", projectInfos);
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
    public BaseDto saveServicePlans(ServicePlanInfo servicePlanInfo) throws ParseException {
        // 判断时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MINUTE, 30);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date date = sdf.parse(servicePlanInfo.getTopicTime());
        Calendar calendarTopic = Calendar.getInstance();
        calendarTopic.setTime(date);
        calendarTopic.set(Calendar.SECOND, 0);
        calendarTopic.set(Calendar.MINUTE, 0);
        if (!calendarTopic.after(calendar)){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "为了您的精准推送,请将时间设置为当前时间的30分钟后");
        }
        
        if (servicePlanInfo.getsId() == null){
            servicePlanInfoMapper.insertSelective(servicePlanInfo);
        }
        else {
            servicePlanInfoMapper.updateByPrimaryKeySelective(servicePlanInfo);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, servicePlanInfo);
    }

}
