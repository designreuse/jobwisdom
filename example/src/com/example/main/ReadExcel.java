package com.example.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mysql.jdbc.StringUtils;

/**
 * @author 张进军
 * @date 2015年6月7日 下午5:29:26
 */
public class ReadExcel {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://rdsyvf3ii0q5sy1qr9u0public.mysql.rds.aliyuncs.com:3306/zefun";
	private static final String USER_NAME = "lives";
	private static final String PASS_WORD = "livesALL2015";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
	}
	
	public static void main(String[] args) throws Exception {
//	    readInfo();
	    getLevel();
    }
	
	public static Map<String, String> getLevel() throws Exception {
	    InputStream is = new FileInputStream("C:\\Users\\Lzh\\Desktop\\消费余额统计表.xls");
	    Set<String> set = new HashSet<String>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Map<String, String> levelMap = new HashMap<String, String>();
        // 循环工作表Sheet
        StringBuffer sb = new StringBuffer();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null || hssfRow.getLastCellNum() != 16) {
                    continue;
                }
                //检查首列内容
                String firstContent = getValue(hssfRow.getCell(0));
                if (StringUtils.isNullOrEmpty(firstContent) || firstContent.startsWith("卡号")) {
                    continue;
                }
                firstContent = firstContent.replace("(", ",").replace(")", "");
                String[] levelArr = firstContent.split(",");
                String card = levelArr[0];
                String level = levelArr[1].replaceAll("()", "");
                levelMap.put(card, level);
                set.add(level);
            }
        } 
        System.out.println(set.toString());
        return levelMap;
	}
	
	public static void readInfo() throws Exception {
	    InputStream is = new FileInputStream("C:\\Users\\Lzh\\Desktop\\会员资料统计表.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // 循环工作表Sheet
        StringBuffer sb = new StringBuffer();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null || hssfRow.getLastCellNum() != 16) {
                    continue;
                }
                
                //检查首行内容
                String firstContent = getValue(hssfRow.getCell(0));
                if (StringUtils.isNullOrEmpty(firstContent) || firstContent.startsWith("会员编号") || firstContent.startsWith("储值账户")) {
                    continue;
                }
                
                String card = getValue(hssfRow.getCell(0));
                String name = getValue(hssfRow.getCell(1));
                String sex = getValue(hssfRow.getCell(2));
                String phone = getValue(hssfRow.getCell(4));
                String totalMoney = getValue(hssfRow.getCell(10));
                String balanceAmount = getValue(hssfRow.getCell(11));
                String cousumerAmount = getValue(hssfRow.getCell(12));
                String consumerCount = getValue(hssfRow.getCell(13));
                String avgConsumerPrice = getValue(hssfRow.getCell(14));
                String lastConsumerDate = getValue(hssfRow.getCell(15));
                sb.append(card + "\t" + name + "\t" + sex + "\t" + phone + "\t" + totalMoney + "\t" + balanceAmount + "\t" + cousumerAmount
                        + "\t" + consumerCount + "\t" + avgConsumerPrice + "\t" + lastConsumerDate + "\r\n");
                System.out.println(sb.toString());
            }
        } 
        FileWriter fileWriter = new FileWriter(new File("member.txt"));
        fileWriter.write(sb.toString());
        fileWriter.close();
	}
 
 
    /**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell cell) {
//        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
//            // 返回布尔类型的值
//            return String.valueOf(hssfCell.getBooleanCellValue());
//        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
//            // 返回数值类型的值
//            return String.valueOf(hssfCell.getNumericCellValue());
//        } else {
//            // 返回字符串类型的值
//            return String.valueOf(hssfCell.getStringCellValue());
//        }
        String returnValue = "";
        if (cell == null) {
            return returnValue;
        }
        switch(cell.getCellType()){
        //数字
        case HSSFCell.CELL_TYPE_NUMERIC:
            Double doubleValue = cell.getNumericCellValue();
            returnValue = new java.text.DecimalFormat("0").format(doubleValue.doubleValue()).toString();
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
            returnValue = cell.getCellFormula();
            break;
        // 故障
        case HSSFCell.CELL_TYPE_ERROR:     
            returnValue = "";
            break;
        default:
            break;
    }
        return returnValue;
    }

}
