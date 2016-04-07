package com.example.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.StringUtils;

/**
 * @author 张进军
 * @date 2015年6月7日 下午5:29:26
 */
public class Main {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://rdsyvf3ii0q5sy1qr9u0public.mysql.rds.aliyuncs.com:3306/zefun";
	private static final String DB_NAME = "zefun";
	private static final String USER_NAME = "lives";
	private static final String PASS_WORD = "livesALL2015";
	
	private static final String ENTITY_PKG_PATH = "com/zefun/entity";
	private static final String MAPPER_PKG_PATH = "com/zefun/mapper";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
	}
	
	public static void main(String[] args) {
		readDB();
	}
	
	public static final void readDB(){
		Connection conn = null;
		PreparedStatement tableStmt = null;
		PreparedStatement columnStmt = null;
		ResultSet tableRs = null;
		ResultSet columnRs = null;
		
		FileWriter entityWriter = null;
		FileWriter mapperWriter = null;
		FileWriter xmlWriter = null;
		try {
			conn = getConnection();
			tableStmt = conn.prepareStatement("select table_name,table_comment from Information_schema.tables where table_schema = ?");
			tableStmt.setString(1, DB_NAME);
			tableRs = tableStmt.executeQuery();
			
			columnStmt = conn.prepareStatement("select column_name,column_comment,data_type from Information_schema.columns  where table_Name = ?");
			
			String basePath = System.getProperty("user.dir") + "/src/";
			String entityPath = basePath + ENTITY_PKG_PATH + "/";
			String mapperPath = basePath + MAPPER_PKG_PATH + "/";
			checkdir(entityPath);
			checkdir(mapperPath);
			String currentTime = getCurTime();
			while (tableRs.next()) {
				String tableName = tableRs.getString("table_name");
				String tableComment = tableRs.getString("table_comment");
				
				System.out.println(tableName + "----" + tableComment);
				
				String calssName = getClassName(tableName);
				
				entityWriter = new FileWriter(new File(entityPath + calssName + ".java"));
//				mapperWriter = new FileWriter(new File(mapperPath + calssName + "Mapper.java"));
//				xmlWriter = new FileWriter(new File(entityPath + calssName + ".xml"));
				
				Set<String> typeSet = new HashSet<String>();
				Set<String> columnSet = new HashSet<String>();
				StringBuffer entityBuffer = new StringBuffer();
				StringBuffer entityMethodBuffer = new StringBuffer();
				StringBuffer mapperBuffer = new StringBuffer();
				StringBuffer xmlBuffer = new StringBuffer();
				
				columnStmt.setString(1, tableName);
				columnRs = columnStmt.executeQuery();
				while (columnRs.next()) {
					String columnName = columnRs.getString("column_name");
					columnName = underlineToCamel(columnName);
					String columnComment = columnRs.getString("column_comment");
					String columnType = columnRs.getString("data_type");
					columnType = dbTypeToJavaType(columnType);
					
					typeSet.add(columnType);
					
					if(!StringUtils.isNullOrEmpty(columnComment)){
						entityBuffer.append("\t/** " + columnComment + " */\r\n");
					}
					entityBuffer.append("\tprivate " + columnType + " " + columnName + ";\r\n\r\n");
					
					if(!StringUtils.isNullOrEmpty(columnComment)){
					    entityMethodBuffer.append("\t/** @param " + columnName + "\t" + columnComment + " */\r\n");
                    }
					entityMethodBuffer.append("\tpublic void set" + getMethodName(columnName) + "(" + columnType + " " + columnName + "){\r\n");
					entityMethodBuffer.append("\t\tthis." + columnName + " = " + columnName + ";\r\n\t}\r\n\r\n");
					
					if(!StringUtils.isNullOrEmpty(columnComment)){
                        entityMethodBuffer.append("\t/** @return\t" + columnComment + " */\r\n");
                    }
					entityMethodBuffer.append("\tpublic " + columnType + " get" + getMethodName(columnName) + "(){\r\n");
					entityMethodBuffer.append("\t\treturn " + columnName + ";\r\n\t}\r\n\r\n");
					
					System.out.println(columnName + "----" + columnComment + "----" + columnType);
				}
				System.out.println(entityBuffer.toString());
				
				entityWriter.write("package " + ENTITY_PKG_PATH.replace("/", ".") + ";\r\n\r\n");
				entityWriter.write(getImportPkg(typeSet));
				entityWriter.write("\r\n/**\r\n * @author 张进军\r\n * @date " + currentTime + "\r\n */\r\n");
				entityWriter.write("public class " + calssName + " {\r\n");
				entityWriter.write(entityBuffer.toString());
				entityWriter.write(entityMethodBuffer.toString());
				entityWriter.write("}");
				entityWriter.close();
				System.out.println();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (tableRs != null) {
					tableRs.close();
				}
				if (tableStmt != null) {
					tableStmt.close();
				}
				if (columnRs != null) {
					columnRs.close();
				}
				if (columnStmt != null) {
					columnStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (entityWriter != null) {
					entityWriter.close();
				}
				if (mapperWriter != null) {
					mapperWriter.close();
				}
				if (xmlWriter != null) {
					xmlWriter.close();
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getMethodName(String columnName){
		String tmpStr = columnName.substring(0,1).toUpperCase();
		columnName = tmpStr + columnName.substring(1);
		return columnName;
	}
	
	public static String getClassName(String tableName){
		tableName = tableName.toLowerCase();
		if(tableName.startsWith("m_")){
			tableName = tableName.replace("m_", "");
		}else if(tableName.startsWith("pt_")){
			tableName = tableName.replace("pt_", "");
		}
		String[] tmpArr = tableName.split("_");
		StringBuffer sb = new StringBuffer();
		for (String s : tmpArr) {
			String tmpStr = s.substring(0,1).toUpperCase();
			sb.append(tmpStr);
			sb.append(s.substring(1));
		}
		return sb.toString();
	}
	
	public static String getImportPkg(Set<String> set){
		StringBuffer sb = new StringBuffer("");
		for (String s : set) {
			if("BigDecimal".equals(s)){
				sb.append("import java.math.BigDecimal;\r\n");
			}else if("BigInteger".equals(s)){
				sb.append("import java.math.BigInteger;\r\n");
			}else if("Date".equals(s)){
				sb.append("import java.sql.Date;\r\n");
			}else if("Timestamp".equals(s)){
				sb.append("import java.sql.Timestamp;\r\n");
			}else if("Time".equals(s)){
				sb.append("import java.sql.Time;\r\n");
			}else if("Time".equals(s)){
				sb.append("import java.sql.Time;\r\n");
			}
		}
		return sb.toString();
	}
	
	public static String dbTypeToJavaType(String type){
		type = type.toUpperCase();
		if("VARCHAR".equals(type) || "CHAR".equals(type) || "TEXT".equals(type) 
				|| "LONGTEXT".equals(type) || "MEDIUMTEXT".equals(type)){
			type = "String";
		}else if("TINYINT".equals(type) || "SMALLINT".equals(type) || "MEDIUMINT".equals(type) || "INT".equals(type)){
			type = "Integer";
		}else if("INTEGER".equals(type)){
			type = "Long";
		}else if("FLOAT".equals(type)){
			type = "Float";
		}else if("DOUBLE".equals(type)){
			type = "Double";
		}else if("DECIMAL".equals(type)){
			type = "BigDecimal";
		}else if("BIGINT".equals(type)){
			type = "BigInteger";
		}else if("BIT".equals(type) || "BOOLEAN".equals(type)){
			type = "Boolean";
		}else if("DATE".equals(type) || "YEAR".equals(type)){
			type = "Date";
		}else if("DATETIME".equals(type) || "TIMESTAMP".equals(type)){
			type = "Timestamp";
		}else if("TIME".equals(type)){
			type = "Time";
		}else if("BLOB".equals(type) || "LONGBLOB".equals(type)){
			type = "byte[]";
		}
		return type;
	}
	
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	} 
	
	public static void checkdir(String path){
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	public static String getCurTime() {
		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 a HH:mm:ss");
		return format.format(new Date());
	}

}
