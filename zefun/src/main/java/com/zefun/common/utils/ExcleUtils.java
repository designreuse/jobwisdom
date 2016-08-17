package com.zefun.common.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Excle 操作数据
* @author 高国藩
* @date 2015年11月18日 下午3:45:26
 */
public class ExcleUtils {

    /**
     * 
    * @author 高国藩
    * @date 2015年11月18日 下午3:45:38
    * @param cell 表格
    * @return     数据Str
     */
    public static String changeCellToString(Cell cell){
        String returnValue = "";
        if (null != cell){
            switch(cell.getCellType()){
                //数字
                case HSSFCell.CELL_TYPE_NUMERIC:
                    returnValue = new BigDecimal(cell.getNumericCellValue()).toString();
                    break;
                case HSSFCell.CELL_TYPE_STRING:    
                    //字符串
                    returnValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:   
                    //布尔
                    Boolean booleanValue=cell.getBooleanCellValue();
                    returnValue = booleanValue.toString();
                    break;
                // 空值
                case HSSFCell.CELL_TYPE_BLANK:     
                    returnValue = "";
                    break;
                // 公式
                case HSSFCell.CELL_TYPE_FORMULA:   
                    returnValue = String.valueOf(cell.getCellFormula());
                    break;
                // 故障
                case HSSFCell.CELL_TYPE_ERROR:     
                    returnValue = "";
                    break;
                default:
                    break;
            }
        }
        return returnValue;
    }
    
    /**
     * 判断手机号码是否合法
    * @author 高国藩
    * @date 2015年12月16日 下午7:31:24
    * @param phone   电话号码
    * @return        false
     */
    public static boolean isPhone(String phone){
        if (StringUtils.isNotBlank(phone)){
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
            Matcher m = p.matcher(phone); 
            if (m.matches()){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * 将传入的字符串转换位可以进行数据格式化的字符串
    * @author 高国藩
    * @date 2015年12月16日 下午7:32:31
    * @param str     带处理的字符串
    * @return        可以进行数据格式化的字符串
     */
    public static String changeValue(String str){
        if (str!=null&&(str.equals("")||str.equals(" "))){
            return "0"; 
        }
        else if (str==null){
            return "0";
        }
        else {
            try {
                new BigDecimal(str);
            } 
            catch (Exception e) {
                return "0";
            }
            return str;
        }
    }
    
    /**
     * 判断是否为数字
    * @author 高国藩
    * @date 2016年8月12日 下午6:28:43
    * @param number number
    * @return       boolean
     */
    public static boolean isNumber(String number){
        try {
            new BigDecimal(number);
            return true;
        } 
        catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 格式化手机科学记数法
    * @author DavidLiang
    * @date 2016年1月18日 上午11:05:10
    * @param phone  手机号
    * @return  手机号
     */
    public static String phoneScientificNotationToFormat (String phone) {
    	if (phone.contains("E")) {
    		BigDecimal bd = new BigDecimal(phone);
    		phone = bd.toPlainString();
    	}
    	return phone;
    }
    
}
