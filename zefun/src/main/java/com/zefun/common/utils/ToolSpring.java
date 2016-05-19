package com.zefun.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 获取spring信息的工具类
 * @author Administrator
 *
 */
public final class ToolSpring implements ApplicationContextAware{
	/**
	 * 
	 */
    private static ApplicationContext applicationContext = null;
	@Override
	public  void setApplicationContext(ApplicationContext applicationContext)
	throws BeansException {
		if (ToolSpring.applicationContext == null) {
			ToolSpring.applicationContext  = applicationContext;
		}
	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * 
	* @author 老王
	* @date 2016年5月17日 下午6:54:57 
	* @param name bean名称
	* @return Object
	 */
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}
}