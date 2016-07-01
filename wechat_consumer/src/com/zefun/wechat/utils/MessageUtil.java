package com.zefun.wechat.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息工具类
* @author 高国藩
* @date 2015年8月6日 上午9:38:53 
*
 */
public class MessageUtil {

    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 图文消息发送状态
     */
    public static final String EVENT_ITEMS_STATUS = "MASSSENDJOBFINISH";

    
    /**
     * 获得当前月--开始日期
    * @author 王大爷
    * @date 2015年8月19日 下午7:28:53
    * @return String
     */
    public static String getMinMonthDate() {   
        Calendar calendar = Calendar.getInstance();   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); 
        return dateFormat.format(calendar.getTime());
    }


    /**
     * 获得当前月--结束日期
    * @author 高国藩
    * @date 2015年8月19日 下午7:33:09
    * @return String
     */
    public static String getMaxMonthDate(){   
        Calendar calendar = Calendar.getInstance();   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTime());
    }
    
    /**
     * javaBean转Map
    * @author 高国藩
    * @date 2015年12月11日 下午2:21:51
    * @param obj  与转换对象
    * @return     map
     */
    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null){
            return null;
        }        
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
