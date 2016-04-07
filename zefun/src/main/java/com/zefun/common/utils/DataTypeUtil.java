package com.zefun.common.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 * 数据类型工具类
 * 
 * @author lzc
 *
 */
public class DataTypeUtil {

	/**
	 * 判断该字符是否是整数
	 * @param str 字符参数
	 * @return true：是整数，false：不是整数
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 使用NumberFormat,保留小数点后两位
	 * @author DavidLiang
	 * @date 2016年2月18日 上午11:50:58
	 * @param value  double类型小数
	 * @return  保留两位后的小数
	 */
	public static String formatDataReserveTwoDecimal(double value) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		/*
		 * setMinimumFractionDigits设置成2 如果不这么做，那么当value的值是100.00的时候返回100
		 * 而不是100.00
		 */
		nf.setMinimumFractionDigits(2);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		/*
		 * 如果想输出的格式用逗号隔开，可以设置成true
		 */
		nf.setGroupingUsed(false);
		return nf.format(value);
	}

}
