package com.zefun.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

/**
 * 日期工具类
 * 
 */
public class DateUtil {

	/**
	 * 根据时间字符串获取日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dataStr
	 *            时间字符串
	 * @return 日期
	 */
	public static Date tranStrToDate(String dataStr) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.parse(dataStr);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据时间字符串获取日期 yyyy-MM-dd
	 * 
	 * @param dataStr
	 *            时间字符串
	 * @return 日期
	 */
	public static Date tranStrToDateDD(String dataStr) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(dataStr);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据时间字符串获取日期
	 * 
	 * @param dataStr
	 *            时间字符串 1313243564
	 * @return 日期
	 */
	public static String tranStrToDateStr(String dataStr) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(Long.valueOf(dataStr)));
	}

	/**
	 * 根据时间字符串获取日期
	 * @param dataStr 时间字符串 1313243564
	 * @return 日期
	 */
	public static String tranStrToDateStrToo() {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}

	/**
	 * 根据时间字符串获取日期
	 * 
	 * @return 当前日期，格式：yyyy-MM-dd HH:mm
	 */
	public static String getCurDateTrimMinutes() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(new Date());
	}

	/**
	 * 根据时间字符串获取日期
	 * 
	 * @return 当前日期，格式：yyyy年MM月dd日
	 */
	public static String getCurDateChine() {
		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(new Date());
	}

	/**
	 * 根据传入时间转换为毫秒值，附加纳秒后六位
	 * 
	 * @param dateTime
	 *            日期
	 * @return String
	 * @throws ParseException
	 *             异常
	 */
	public static String getTimeToNanos(String dateTime) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime));
		Long curNanos = Math.abs(System.nanoTime());
		String nanos = curNanos.toString();
		return c.getTimeInMillis() + nanos.substring(nanos.length() - 6);
	}

	/**
	 * 根据传入的毫秒值转换为yyyy-MM-dd HH:mm:ss的日期字符串
	 * 
	 * @param dateTime
	 *            日期
	 * @return String
	 */
	public static String getDateStrByTimeMillis(long dateTime) {
		Date date = new Date(dateTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 当前日期，格式：yyyy-MM-dd
	 */
	public static String getCurDate() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	/**
	 * 获取当前月份
	 * 
	 * @return 当前日期，格式：yyyy-MM
	 */
	public static String getCurMonth() {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(new Date());
	}

	/**
	 * 
	 * @author 王大爷
	 * @date 2015年9月18日 下午8:17:52
	 * @return 当前日期，格式：yyyyMMdd
	 */
	public static String getCurDateString() {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前时间，格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurTime() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒)
	 * 
	 * @param timeZoneId
	 *            时区Id
	 * @param time
	 *            时间
	 * @return 系统当前默认时区与指定时区的时间差.(单位:毫秒)
	 */
	public static long getDiffTimeZoneRawOffset(String timeZoneId, long time) {
		long diffTime = TimeZone.getDefault().getRawOffset() - TimeZone.getTimeZone(timeZoneId).getRawOffset();
		long curTime = System.currentTimeMillis();
		return time - curTime + diffTime;
	}

	/**
	 * 获取当天结束时间
	 * 
	 * @return 毫秒数
	 */
	public static Long getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.AM_PM, 0);
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}

	/**
	 * 添加日期
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:07:45
	 * @param dateTime
	 *            时间
	 * @param days
	 *            天
	 * @return String
	 */
	public static String addDays(String dateTime, int days) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar date = Calendar.getInstance();
			date.setTime(sdf.parse(dateTime));
			date.set(Calendar.DATE, date.get(Calendar.DATE) + days);
			return sdf.format(date.getTime());
		} catch (Exception e) {
			return dateTime;
		}
	}

	/**
	 * 当前日期，yyyy-MM
	 * 
	 * @author 张进军
	 * @date Jan 13, 2016 11:47:34 PM
	 * @param date
	 *            date
	 * @param months
	 *            months
	 * @return String
	 */
	public static String addMonth(String date, int months) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(date));
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
			return sdf.format(calendar.getTime());
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 添加秒
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:08:07
	 * @param dateTime
	 *            时间
	 * @param secondes
	 *            秒
	 * @return String
	 */
	public static String addSecondes(String dateTime, int secondes) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar date = Calendar.getInstance();
			date.setTime(sdf.parse(dateTime));
			date.set(Calendar.SECOND, date.get(Calendar.SECOND) + secondes);
			return sdf.format(date.getTime());
		} catch (Exception e) {
			return dateTime;
		}
	}

	/**
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:08:21
	 * @param startTime
	 *            开始时间
	 * @return long
	 */
	public static long getDiffTimeByNow(String startTime) {
		return getDiffTime(startTime, null);
	}

	/**
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:08:38
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return long
	 */
	public static long getDiffTime(String startTime, String endTime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = sdf.parse(startTime);
			Date endDate = null;
			if (StringUtil.isNotEmpty(endTime)) {
				endDate = sdf.parse(endTime);
			} else {
				endDate = new Date();
			}
			return startDate.getTime() - endDate.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:08:54
	 * @param time
	 *            时间
	 * @return String
	 */
	// 支付宝or微信回传的yyyyMMddhhmmss时间格式需要转化为yyyy-MM-dd hh:mm:ss
	public static String timestampFormat(String time) {
		String yyyy = time.substring(0, 4);
		String mm = time.substring(4, 6);
		String dd = time.substring(6, 8);
		String hh = time.substring(8, 10);
		String min = time.substring(10, 12);
		String ss = time.substring(12, 14);
		return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + min + ":" + ss;
	}

	/**
	 * 时间减
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:09:09
	 * @param d
	 *            日期
	 * @param day
	 *            天
	 * @return Date
	 */
	public static Date getDateDaysBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		// now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		now.add(Calendar.DATE, -day);
		return now.getTime();
	}

	/**
	 * 时间减
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:09:09
	 * @param d
	 *            日期
	 * @param day
	 *            天
	 * @return Date
	 */
	public static String getDateDaysBeforeStr(String d, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		Date date;
		try {
			date = sdf.parse(d);
			now.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		now.add(Calendar.DATE, -day);
		return sdf.format(now.getTime());
	}

	/**
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:09:20
	 * @param d
	 *            日期
	 * @param day
	 *            天
	 * @return Date
	 */
	public static Date getDateDaysAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		// now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		now.add(Calendar.DATE, day);
		return now.getTime();
	}

	/**
	 * 
	 * @param dateStr
	 *            日期
	 * @param day
	 *            天数
	 * @return Date
	 * @throws ParseException
	 */
	public static String getDateAfterDaysStr(String dateStr, int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * @param dateStr
	 *            日期
	 * @param months
	 *            月份数
	 * @return Date
	 * @throws ParseException
	 */
	public static String getDateAfterMonthsStr(String dateStr, int months) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:09:32
	 * @param d
	 *            日期
	 * @param month
	 *            月份
	 * @return Date
	 */
	public static Date getDateMonthsBefore(Date d, int month) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		// now.set(Calendar.DATE, now.get(Calendar.MONTH) - month);
		now.add(Calendar.MONTH, -month);
		return now.getTime();
	}

	/**
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:09:45
	 * @param d
	 *            日期
	 * @param month
	 *            月份
	 * @return Date
	 */
	public static Date getDateMonthsAfter(Date d, int month) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		// now.set(Calendar.DATE, now.get(Calendar.MONTH) + month);
		now.add(Calendar.MONTH, month);
		return now.getTime();
	}

	/**
	 * 获得当前周- 周一的日期
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:13:50
	 * @return 周一的日期
	 */
	public static Integer getCurrentMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Integer preMonday = Integer.parseInt(df.format(monday));
		return preMonday;
	}

	/**
	 * 获得当前周- 周一的日期 yyyy-MM-dd
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:13:50
	 * @return 周一的日期
	 */
	public static String getCurrentMondayStr() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得当前周- 周日 的日期
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:14:04
	 * @return 周日 的日期
	 */
	public static Integer getPreviousSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Integer preMonday = Integer.parseInt(df.format(monday));
		return preMonday;
	}

	/**
	 * 获得当前周- 周日 的日期 yyyy-MM-dd
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:14:04
	 * @return 周日 的日期
	 */
	public static String getPreviousSundayStr() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得当前日期与本周一相差的天数
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:12:13
	 * @return 天数
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/**
	 * 获得当前月--开始日期
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:28:53
	 * @return String
	 */
	public static Integer getMinMonthDate() {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return Integer.parseInt(dateFormat.format(calendar.getTime()));
	}

	/**
	 * 获得当前月--开始日期 yyyy-MM-dd
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:28:53
	 * @return String
	 */
	public static String getMinMonthDateStr() {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获得传入--开始日期 yyyy-MM-dd
	 * 
	 * @author 王大爷
	 * @date 2016年1月17日 上午11:34:14
	 * @param date
	 *            参数
	 * @return String
	 */
	public static String getMinMonthDateValue(Date date) {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获得传入月--结束日期
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:33:09
	 * @param date
	 *            参数
	 * @return Integer
	 */
	public static String getMaxMonthDateValue(Date date) {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获得当前月--结束日期
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:33:09
	 * @return Integer
	 */
	public static Integer getMaxMonthDate() {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return Integer.parseInt(dateFormat.format(calendar.getTime()));
	}

	/**
	 * 获得当前月--结束日期 yyyy-MM-dd
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:33:09
	 * @return Date
	 */
	public static Date getMaxMonthDateDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 获得当前月--结束日期
	 * 
	 * @author 王大爷
	 * @date 2015年8月19日 下午7:33:09
	 * @return String
	 */
	public static String getMaxMonthDateStr() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获取当天是星期几
	 * 
	 * @author 王大爷
	 * @date 2015年8月20日 下午2:44:38
	 * @return 当天是星期几
	 */
	public static String getgetWeekOfDate() {
		Date date = new Date();
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		return dateFm.format(date);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @author 王大爷
	 * @date 2015年8月21日 下午5:15:37
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 *             异常
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long betweendays = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(betweendays));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @author 王大爷
	 * @date 2015年8月21日 下午5:16:15
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 *             异常
	 */
	public static String daysBetween(String smdate, String bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long betweendays = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(betweendays)) + "";
		} catch (Exception e) {
			return "0";
		}
	}

	/**
	 * 计算两个日期之间相差的分钟数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差的分钟数
	 * @throws ParseException
	 *             异常
	 */
	public static int daysBetweenMinutes(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long betweendays = (time2 - time1) / (1000 * 60);
		return Integer.parseInt(String.valueOf(betweendays));
	}

	/**
	 * 传入数字时间,获得固定样式时间
	 * 
	 * @author 高国藩
	 * @date 2015年9月7日 下午5:52:02
	 * @param time
	 *            数字时间
	 * @return 时间
	 */
	public static String getDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(Long.valueOf(time));
		return sdf.format(date);
	}

	/**
	 * unix时间戳转指定格式时间字符串
	 * 
	 * @author 张进军
	 * @date Mar 21, 2016 11:48:09 PM
	 * @param unixTime
	 *            unix时间戳
	 * @param format
	 *            时间格式
	 * @return 指定格式时间字符串
	 */
	public static final String getDateTimeByUnixTime(long unixTime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date(unixTime);
		return sdf.format(date);
	}

	/****
	 * 传入具体日期 ，返回具体日期加一个月。
	 * 
	 * @param date
	 *            日期(2014-04-20)
	 * @return 2014-03-20
	 * @throws ParseException
	 */
	public static String subMonth(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = sdf.parse(date);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);

		rightNow.add(Calendar.MONTH, 1);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);

		return reStr;
	}

	/**
	 * 根据周几数字获得汉字
	 * 
	 * @author 张进军
	 * @date Oct 19, 2015 6:40:23 PM
	 * @param weekNumber
	 *            周几数字
	 * @return 周几汉字
	 */
	public static String getWeekName(int weekNumber) {
		String weekName = "";
		switch (weekNumber) {
		case 1:
			weekName = "日";
			break;
		case 2:
			weekName = "一";
			break;
		case 3:
			weekName = "二";
			break;
		case 4:
			weekName = "三";
			break;
		case 5:
			weekName = "四";
			break;
		case 6:
			weekName = "五";
			break;
		case 7:
			weekName = "六";
			break;
		default:
			break;
		}
		return weekName;
	}

	/**
	 * 计算两个(分钟)时间差
	 * 
	 * @param beginTime
	 *            开始时间(eg: "2015-12-19 17:52:03")
	 * @param endTime
	 *            结束时间(eg: "2015-12-19 20:52:03")
	 * @return (分钟)时间差
	 */
	public static int getTwoTimeBetween(String beginTime, String endTime) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long minute = 0;
		try {
			Date beginDate = myFormatter.parse(beginTime);
			Date endDate = myFormatter.parse(endTime);
			minute = (endDate.getTime() - beginDate.getTime()) / (60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(String.valueOf(minute));
	}

	/**
	 * 当前日期加一天
	 * 
	 * @author DavidLiang
	 * @date 2016年2月18日 下午4:56:43
	 * @return 加一天后的日期字符(yyyy-MM-dd)
	 */
	public static String addOneDayAtCur() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		return sf.format(c.getTime());
	}

	/**
	 * 在当前日期指定增加几天
	 * 
	 * @author DavidLiang
	 * @date 2016年2月18日 下午5:06:10
	 * @param appointedDate
	 *            指定日期(eg:2015-12-31)
	 * @param dayNum
	 *            需要添加的天数
	 * @return 添加天数后的日期
	 */
	public static String addOneDayCustomize(String appointedDate, int dayNum) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(appointedDate));
			cd.add(Calendar.DATE, dayNum); // 增加指定天
			// cd.add(Calendar.MONTH, dayNum); //增加指定月
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据日期返回周几
	 * 
	 * @author DavidLiang
	 * @date 2016年1月14日 上午11:50:40
	 * @param date
	 *            日期
	 * @return 星期几(字符串) eg:星期日
	 */
	public static String getWeekOfDate(Date date) {
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("EEEE"); String week =
		 * sdf.format(date); return week;
		 */
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekIndex < 0) {
			weekIndex = 0;
		}
		return weeks[weekIndex];
	}

	/**
	 * 获取今年第一天
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午3:10:08
	 * @return 获取今年第一天 yyyy-mm-dd
	 */
	public static String getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		Date currYearFirst = calendar.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(currYearFirst.getTime());
	}

	/**
	 * 获取今年最后一天
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午3:10:08
	 * @return 获取今年最后一天 yyyy-mm-dd
	 */
	public static String getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(currYearLast.getTime());
	}

	/**
	 * 获取当前时间的上月对应时间
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午3:29:12
	 * @return yyyy-mm-dd
	 */
	public static String getCurrLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(calendar.getTime());
	}

	/**
	 * 获取当前时间的去年对应时间
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午3:29:12
	 * @return yyyy-mm-dd
	 */
	public static String getCurrLastYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(calendar.getTime());
	}

	/**
	 * 获取当前时间的去年对应时间
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午3:29:12
	 * @return yyyy-mm-dd
	 */
	public static String getCurrLastYearLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(calendar.getTime());
	}

	/**
	 * 获取当前时间前一天时间
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午3:34:19
	 * @return yyyy-mm-dd
	 */
	public static String getCurrLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(calendar.getTime());
	}

	/**
	 * 获取当前时间上个月前一天时间
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午3:34:19
	 * @return yyyy-mm-dd
	 */
	public static String getCurrLastMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(calendar.getTime());
	}

	/**
	 * 上一个月的最后一天
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午5:35:08
	 * @return yyyy-mm-dd
	 */
	public static String getLastLastMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.setTime(calendar.getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 定制方法：根据时间类型返回当前时间
	 * 
	 * @author DavidLiang
	 * @date 2016年1月23日 下午3:03:38
	 * @param dateType
	 *            时间类型(year, month, day)
	 * @return 当前时间
	 */
	public static String getCurDateByType(String dateType) {
		String time = null;
		if (dateType == null) {
			return null;
		}
		switch (dateType) {
		case "year":
			time = new SimpleDateFormat("yyyy").format(new Date());
			break;
		case "month":
			time = new SimpleDateFormat("yyyy-MM").format(new Date());
			break;
		case "day":
			time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			break;
		default:
			break;
		}
		return time;
	}

	/**
	 * 传入一个时间,获得本月第一天
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午5:35:08
	 * @param str
	 *            时间
	 * @return yyyy-mm-dd
	 * @throws ParseException
	 */
	public static String getMonthStart(String str) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = dateFormat.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 传入一个时间,获得本月最后一天
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午5:35:08
	 * @param str
	 *            时间
	 * @return yyyy-mm-dd
	 * @throws ParseException
	 */
	public static String getMonthEnd(String str) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = dateFormat.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 传入一个时间,获得上个月第一天
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午5:35:08
	 * @param str
	 *            时间
	 * @return yyyy-mm-dd
	 * @throws ParseException
	 */
	public static String getMonthLastStart(String str) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = dateFormat.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 传入一个时间,获得上个月最后一天
	 * 
	 * @author 高国藩
	 * @date 2016年1月21日 下午5:35:08
	 * @param str
	 *            时间
	 * @return yyyy-mm-dd
	 * @throws ParseException
	 */
	public static String getMonthLastEnd(String str) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = dateFormat.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据员工创建时间计算工龄
	 * 
	 * @author DavidLiang
	 * @date 2016年1月28日 下午8:54:42
	 * @param employeeCreateTime
	 *            员工创建时间
	 * @return 工龄
	 * @throws ParseException
	 *             抛出解析异常
	 */
	public static String caculateSeniority(String employeeCreateTime) throws ParseException {
		if (employeeCreateTime == null || employeeCreateTime.length() == 0) {
			return null;
		}
		Date date = new Date(); // 获得当前日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR); // 获得年份
		int month = cal.get(Calendar.MONTH); // 获得月份
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = bartDateFormat.parse(employeeCreateTime);
		cal.setTime(d);
		int year2 = cal.get(Calendar.YEAR);
		int month2 = cal.get(Calendar.MONTH);
		int t = year - year2; // 得到年差
		int m = java.lang.Math.abs(month - month2); // 得到月差
		return t + "年" + m + "月";
	}

	/**
	 * 得到当前日期前后30天的日期，格式为Map<"yyyy-MM-dd", "EEEE">
	 * 
	 * @author DavidLiang
	 * @date 2016年1月31日 下午8:33:16
	 * @return 当前日期前后30天的日期
	 */
	public static Map<String, String> getDateBetweenSixty() {
		/*
		 * Map<String, String> dateMap = new TreeMap<String, String>();
		 * SimpleDateFormat dayFormat = new SimpleDateFormat("MM-dd");
		 * SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE"); Calendar
		 * calendar = Calendar.getInstance(); calendar.setTime(new Date());
		 * //当前calendar对象(当前日期)加上指定天数 calendar.add(Calendar.DATE, 30); for (int
		 * i=0; i<30; i++) { calendar.add(Calendar.DATE, -1); String customeDay
		 * = dayFormat.format(calendar.getTime()); String customeWeek =
		 * weekFormat.format(calendar.getTime()); dateMap.put(customeDay,
		 * customeWeek); }
		 */
		Map<String, String> dateMap = new TreeMap<String, String>();
		SimpleDateFormat dayFormat = new SimpleDateFormat("MM-dd");
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		// 循环之前先把今天加进来
		String nowDay = dayFormat.format(cal.getTime());
		int nowWeekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (nowWeekIndex < 0) {
			nowWeekIndex = 0;
		}
		dateMap.put(nowDay, weeks[nowWeekIndex]);
		for (int i = 0; i < 29; i++) {
			cal.add(Calendar.DATE, 1);
			int customeWeekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (customeWeekIndex < 0) {
				customeWeekIndex = 0;
			}
			String customeDay = dayFormat.format(cal.getTime());
			dateMap.put(customeDay, weeks[customeWeekIndex]);
		}
		return dateMap;
	}

	/**
	 * 根据星期几返回员工班次id字段 eg:星期一返回"shif_ida"...
	 * 
	 * @author DavidLiang
	 * @date 2016年2月18日 下午3:06:07
	 * @param weekDay
	 *            参数星期几
	 * @return 班次id
	 */
	public static String getShiftIdByWeekDay(String weekDay) {
		String shiftId = null;
		switch (weekDay) {
		case "星期一":
			shiftId = "shif_ida";
			break;
		case "星期二":
			shiftId = "shif_idb";
			break;
		case "星期三":
			shiftId = "shif_idc";
			break;
		case "星期四":
			shiftId = "shif_idd";
			break;
		case "星期五":
			shiftId = "shif_ide";
			break;
		case "星期六":
			shiftId = "shif_idf";
			break;
		case "星期日":
			shiftId = "shif_idg";
			break;
		default:
			break;
		}
		return shiftId;
	}

	/**
	 * 计算两个时间差
	 * 
	 * @author DavidLiang
	 * @date 2016年2月18日 下午4:13:27
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param dateFormat
	 *            时间格式
	 * @param returnFormat
	 *            返回格式(day:返回相隔多少天; hour:返回相隔多少小时; min:返回相隔多少分钟; sec:返回相隔多少秒)
	 * @return 时间差
	 */
	public static Long dateDiff(String startTime, String endTime, String dateFormat, String returnFormat) {
		Long returnValue = null;
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(dateFormat);
		long nd = 1000 * 24 * 60 * 60; // 一天的毫秒数
		long nh = 1000 * 60 * 60; // 一小时的毫秒数
		long nm = 1000 * 60; // 一分钟的毫秒数
		long ns = 1000; // 一秒钟的毫秒数
		long diff;
		long diffDay = 0;
		long diffHour = 0;
		long diffMin = 0;
		long diffSec = 0;
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			diffDay = diff / nd; // 计算差多少天
			/*
			 * diffHour = diff % nd / nh + diffDay * 24; // 计算差多少小时 diffMin =
			 * diff % nd % nh / nm + diffDay * 24 * 60; // 计算差多少分钟 diffSec =
			 * diff % nd % nh % nm / ns; // 计算差多少秒
			 */
			diffHour = diff / nh; // 计算差多少小时
			diffMin = diff / nm; // 计算差多少分钟
			diffSec = diff / ns; // 计算差多少秒
			switch (returnFormat) {
			case "day":
				returnValue = diffDay;
				break;
			case "hour":
				returnValue = diffHour;
				break;
			case "min":
				returnValue = diffMin;
				break;
			case "sec":
				returnValue = diffSec;
				break;
			default:
				break;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * 根据班次判断是否跨日 eg: 23:00, 06:30就涉及跨日
	 * 
	 * @author DavidLiang
	 * @date 2016年2月18日 下午4:45:56
	 * @param beginTime
	 *            班次开始时间
	 * @param endTime
	 *            班次结束时间
	 * @return 是否跨日
	 */
	public static boolean decideIsCrossDay(String beginTime, String endTime) {
		String[] beginTimeArray = beginTime.split(":");
		String[] endTimeArray = endTime.split(":");
		if (Integer.valueOf(beginTimeArray[0]) > Integer.valueOf(endTimeArray[0])) {
			return true;
		}
		return false;
	}

	/**
	 * (预约功能定制)根据给定日期与当前日期判断是否跨年
	 * 
	 * @author DavidLiang
	 * @date 2016年2月18日 下午5:14:20
	 * @param time
	 *            需要判断的日期
	 * @return 是否跨年
	 */
	public static boolean decideIsCrossYear(String time) {
		String nowDate = new SimpleDateFormat("MM-dd").format(new Date());
		String[] nowDateArray = nowDate.split("-");
		String[] timeArray = time.split("-");
		if (Integer.valueOf(nowDateArray[0]) > Integer.valueOf(timeArray[0])) {
			return true;
		}
		return false;
	}
}
