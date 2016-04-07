package com.zefun.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.AppointDateDto;
import com.zefun.web.mapper.MemberAppointmentMapper;
import com.zefun.web.mapper.ShiftMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 预约测试
* @author 张进军
* @date Feb 19, 2016 10:11:04 AM
 */
public class AppointTest extends BaseTest{
    /** 员工班次信息操作对象 */
    @Autowired
    private ShiftMapper shiftMapper;
    
    /** 会员预约记录操作对象 */
    @Autowired
    private MemberAppointmentMapper memberAppointmentMapper;
    
    /** 日志记录对象 */
    private Logger logger = Logger.getLogger(AppointTest.class);

    /**
     * 预约时间查询
    * @author 张进军
    * @date Feb 19, 2016 10:14:06 AM
     */
    @Test
    public void selectAppointDate() {
        int employeeId = 525;
        //查询员工的班次
        Map<Integer, String> map = shiftMapper.selectShiftByEmployeeId(employeeId);
        //检查员工是否有设置班次
        if (map == null || map.isEmpty()) {
            return;
        }
        //TODO 查询员工当前的考勤情况
        
        //查询员工的预约情况
        List<String> appointList = memberAppointmentMapper.selectAppointDateByEmployee(employeeId);
        
        List<AppointDateDto> dateList = new ArrayList<AppointDateDto>();
        Map<String, List<Map<String, Integer>>> timeListMap = new HashMap<String, List<Map<String, Integer>>>();
        
        Calendar calendar = Calendar.getInstance();
        Integer week = 0;
        String workTime = "";
        String appointDate = "";
        String appointTime = "";
        String[] workTimeArr = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFmt = new SimpleDateFormat("MM-dd");
        
        for (int i = 0; i < 4; i++) {
            week = calendar.get(Calendar.DAY_OF_WEEK);
            appointDate = dateFmt.format(calendar.getTime());
            AppointDateDto appointDateDto = new AppointDateDto();
            appointDateDto.setDate(appointDate);
            appointDateDto.setWeekNumber(week);
            appointDateDto.setWeekName(DateUtil.getWeekName(week));
            
            //获取当天的班次
            workTime = map.get(week + ""); 
            if (StringUtils.isBlank(workTime)) {
                continue;
            }
            workTimeArr = workTime.split(",");
            
            logger.info(appointDate + "----" + JSONArray.fromObject(workTimeArr).toString());
            
            int bh = Integer.parseInt(workTimeArr[0].split(":")[0]);
            int bm = Integer.parseInt(workTimeArr[0].split(":")[1]);
            int eh = Integer.parseInt(workTimeArr[1].split(":")[0]);
            int em = Integer.parseInt(workTimeArr[1].split(":")[1]);
            
            Calendar workStartCalendar = Calendar.getInstance();
            workStartCalendar.setTime(calendar.getTime());
            
            Calendar workEndCalendar = Calendar.getInstance();
            workEndCalendar.setTime(calendar.getTime());
            
            workStartCalendar.set(Calendar.HOUR_OF_DAY, bh);
            workStartCalendar.set(Calendar.MINUTE, bm);
            
            workEndCalendar.set(Calendar.HOUR_OF_DAY, eh);
            workEndCalendar.set(Calendar.MINUTE, em);
            
            workStartCalendar.add(Calendar.MINUTE, 0);
            workEndCalendar.add(Calendar.MINUTE, 0);
            
            if (workStartCalendar.after(workEndCalendar)) {
                workEndCalendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            
            workStartCalendar.add(Calendar.MINUTE, 30);
            workEndCalendar.add(Calendar.MINUTE, -29);
            
            List<Map<String, Integer>> timeList = timeListMap.get(appointDate);
            if (timeList == null) {
                timeList = new ArrayList<Map<String, Integer>>();
                timeListMap.put(appointDate, timeList);
            }
            boolean isJump = false;
            while (workStartCalendar.before(workEndCalendar)) {
                //检查是否超过一天
                if (workStartCalendar.get(Calendar.DAY_OF_MONTH) != calendar.get(Calendar.DAY_OF_MONTH)) {
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    isJump = true;
                    appointDate = dateFmt.format(calendar.getTime());
                    timeList = new ArrayList<Map<String, Integer>>();
                    timeListMap.put(appointDate, timeList);
                }
                
                appointTime = sdf.format(workStartCalendar.getTime());
                
                Map<String, Integer> timeMap = new HashMap<String, Integer>();
                timeMap.put(appointTime, 1);
                
                //如果是当天的话，检查是否超过当前时间
                String tempDateTime = calendar.get(Calendar.YEAR) + "-" + appointDate + " " + appointTime;
                if (i == 0 && workStartCalendar.before(calendar)) {
                    timeMap.put(appointTime, 2);
                }
                //检查该时间是否已被预约
                else if (appointList.contains(tempDateTime)) {
                    timeMap.put(appointTime, 2);
                }
                
                timeList.add(timeMap);
                workStartCalendar.add(Calendar.MINUTE, 30);
            }
            dateList.add(appointDateDto);
            
            if (!isJump) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
        for (AppointDateDto appoint : dateList) {
            List<Map<String, Integer>> timeList = timeListMap.get(appoint.getDate());
            appoint.setTimeList(timeList);
        }
        
        logger.info("map : " + JSONObject.fromObject(timeListMap).toString());
        logger.info(JSONArray.fromObject(dateList).toString());
    }
}
