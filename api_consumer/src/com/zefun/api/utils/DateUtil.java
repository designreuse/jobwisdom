package com.zefun.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 */
public class DateUtil {
    
    /**
     * 根据时间字符串获取日期
     * @return          当前日期，格式：yyyy-MM-dd HH:mm
     */
    public static String getCurDateMinStr() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date());
    }
    
    /**
     * 根据时间字符串获取日期
     * @return          当前日期，格式：yyyy-MM-dd HH:mm
     */
    public static String getCurTimeStr() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
    
    /**
     * 获取当前日期
     * @return      当前日期，格式：yyyy-MM-dd
     */
    public static String getCurDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
    
    /**
     * 计算两个日期之间相差的天数 
    * @author 王大爷
    * @date 2015年8月21日 下午5:16:15
    * @param smdate 较小的时间 
    * @param bdate 较大的时间 
    * @return 相差天数 
    * @throws ParseException 异常
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long betweendays = (time2-time1)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(betweendays));     
    }
    
    /**
     * 计算两个(分钟)时间差
     * @param beginTime  开始时间
     * @param endTime  结束时间
     * @return (分钟)时间差
     */
    public static int getTwoTimeBetween(String beginTime, String endTime) {
    	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long minute = 0;
    	try {
			Date beginDate = myFormatter.parse(beginTime);
			Date endDate = myFormatter.parse(endTime);
			minute = (endDate.getTime() - beginDate.getTime()) / (60*1000);
		}
    	catch (ParseException e) {
			e.printStackTrace();
		}
    	return Integer.parseInt(String.valueOf(minute));
    }
    
    /**
     * 当前日期加一天
     * @return 加一天后的日期字符(yyyy-MM-dd)
     */
    public static String addOneDay() {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return sf.format(c.getTime());
    }
    
    /**
     * 判断当前时间是否23
     * @return true 是，false 否
     */
    public static boolean isSmallHours() {
    	if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23){
    	    return true;
    	}
    	return false;
    }
}
