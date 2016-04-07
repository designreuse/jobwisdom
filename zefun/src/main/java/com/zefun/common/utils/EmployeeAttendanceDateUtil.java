package com.zefun.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zefun.web.entity.EmployeeAttendance;

/**
 * 员工考勤时间工具类
* @author DavidLiang
* @date 2016年3月4日 下午8:07:29
 */
public class EmployeeAttendanceDateUtil {
	
	/** 地球半径(km) */
	private static final double EARTH_RADIUS = 6378137;
	
	/**
	 * 判断是否重复签到
	* @author DavidLiang
	* @date 2016年3月4日 下午8:37:52
	* @param employeeAttendance  考勤记录
	* @param shiftBeginTime  班次开始时间(HH:mm)
	* @param shiftEndTime  班次结束时间(HH:mm)
	* @return  true:重复，false:不重复
	 */
	public static boolean isRepeatSign(EmployeeAttendance employeeAttendance, String shiftBeginTime, String shiftEndTime) {
		if (employeeAttendance == null || employeeAttendance.getSignInTime() == null) {
			return true;
		}
		String signInTime = employeeAttendance.getSignInTime();
		try {
			Date signInDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(signInTime);
			Date now = new Date();
			//if 当前时间毫秒 - 签到时间毫秒  在十分钟之内
			if (now.getTime() - signInDate.getTime() < 10*60*1000) {
				return true;
			}
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据数学符号 π 转换
	* @author DavidLiang
	* @date 2016年3月14日 上午10:46:21
	* @param d 参数
	* @return  转换后的数值
	 */
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	
	/**
	 * 两经纬度计算距离
	* @author DavidLiang
	* @date 2016年3月14日 上午10:47:00
	* @param lat1  纬度1
	* @param lng1  经度1
	* @param lat2  纬度2
	* @param lng2  经度2
	* @return  两经纬度之间距离
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				  Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
//		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	/**
	 * 请假计算小时
	* @author DavidLiang
	* @date 2016年3月21日 下午4:05:47
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param dateFormat  时间格式(eg: yyyy-MM-dd HH:mm:ss)
	* @return  小时
	 */
	public static int holidayCalcHours(String startTime, String endTime, String dateFormat) {
		SimpleDateFormat sd = new SimpleDateFormat(dateFormat);
		long nh = 1000 * 60 * 60; // 一小时的毫秒数
		long diff;
		long diffHour = 0;
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			diffHour = diff / nh; // 计算差多少小时
			if (diff % nh == 0) {
				diffHour = diff / nh;
			} 
			else {
				diffHour = diff / nh + 1;
			}
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(String.valueOf(diffHour));
	}
	
	/**
     * 根据分钟转换成小时和分钟详情
     * @param minutes  带状态的分钟
     * @return  时间详情
     */
    public static String minuteToTimeDetail(int minutes) {
    	StringBuffer timeDetail = new StringBuffer();
    	if (minutes < 0) {
    		minutes = -minutes;
    	}
    	int hour = minutes / 60;
    	int minute = minutes % 60;
    	return timeDetail.append(hour).append("小时").append(minute).append("分钟").toString();
    }

}
