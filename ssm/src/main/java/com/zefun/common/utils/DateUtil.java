package com.zefun.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * @author 高国藩
 * @date 2014-4-9
 */
public class DateUtil {

    /**
     * 根据时间字符串获取日期
     * @param dataStr	时间字符串
     * @return			日期
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
     * 根据传入时间转换为毫秒值，附加纳秒后六位
     * @param dateTime 日期
     * @return String
     * @throws ParseException 异常
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
     * @param dateTime 日期
     * @return String
     */
    public static String getDateStrByTimeMillis(long dateTime) {
        Date date = new Date(dateTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取当前日期
     * @return		当前日期，格式：yyyy-MM-dd
     */
    public static String getCurDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    /**
     * 获取当前时间
     * @return		当前时间，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getCurTime() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒)
     * @param timeZoneId 时区Id
     * @param time 时间
     * @return 系统当前默认时区与指定时区的时间差.(单位:毫秒)
     */
    public static long getDiffTimeZoneRawOffset(String timeZoneId, long time) {
        long diffTime = TimeZone.getDefault().getRawOffset() - TimeZone.getTimeZone(timeZoneId).getRawOffset();
        long curTime = System.currentTimeMillis();
        return time - curTime + diffTime;
    }

    /**
     * 获取当天结束时间
     * @return	毫秒数
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
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:07:45
    * @param dateTime 时间
    * @param days 天
    * @return String
     */
    public static String addDays(String dateTime, int days) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(sdf.parse(dateTime));
            date.set(Calendar.DATE, date.get(Calendar.DATE) + days);
            return sdf.format(date.getTime());
        }
        catch (Exception e) {
            return dateTime;
        }
    }

    /**
     * 添加秒
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:08:07
    * @param dateTime 时间
    * @param secondes 秒
    * @return String
     */
    public static String addSecondes(String dateTime, int secondes) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(sdf.parse(dateTime));
            date.set(Calendar.SECOND, date.get(Calendar.SECOND) + secondes);
            return sdf.format(date.getTime());
        }
        catch (Exception e) {
            return dateTime;
        }
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:08:21
    * @param startTime 开始时间
    * @return long
     */
    public static long getDiffTimeByNow(String startTime) {
        return getDiffTime(startTime, null);
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:08:38
    * @param startTime 开始时间
    * @param endTime 结束时间
    * @return long
     */
    public static long getDiffTime(String startTime, String endTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = sdf.parse(startTime);
            Date endDate = null;
            if (StringUtil.isNotEmpty(endTime)) {
                endDate = sdf.parse(endTime);
            }
            else {
                endDate = new Date();
            }
            return startDate.getTime() - endDate.getTime();
        }
        catch (Exception e) {
            return 0;
        }
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:08:54
    * @param time 时间
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
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:09:09
    * @param d 日期
    * @param day 天
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
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:09:20
    * @param d 日期
    * @param day 天
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
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:09:32
    * @param d 日期
    * @param month 月份
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
    * @param d 日期
    * @param month 月份
    * @return Date
     */
    public static Date getDateMonthsAfter(Date d, int month) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        // now.set(Calendar.DATE, now.get(Calendar.MONTH) + month);
        now.add(Calendar.MONTH, month);
        return now.getTime();
    }

}
