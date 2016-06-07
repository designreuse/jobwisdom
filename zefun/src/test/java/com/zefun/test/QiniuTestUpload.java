package com.zefun.test;

import java.io.File;
import java.util.Random;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 
 * @author 高国藩
 * @date 2016年4月19日 下午2:21:39
 */
public class QiniuTestUpload {

	/** 七牛应用id */
	private static final String QINIU_AK = "uDB532MrQLEj9JovzDHhfKB8krYbkJYg9ALGY5U0";
	/** 七牛应用密钥 */
	private static final String QINIU_SK = "hzkYEXnTzzB8hqG4QGvsz0hbfBoLGYC7_VHuIy4R";
	/** 空间名称 */
	private static final String QINIU_SCOPE = "jobwisdom";
	/** 域名 */
	public static final String DO_MAIN = "http://7xss26.com1.z0.glb.clouddn.com";

	/** 七牛授权对象 */
	private static final Auth auth = Auth.create(QINIU_AK, QINIU_SK);

	/** 七牛上传管理者 */
	private static final UploadManager uploadManager = new UploadManager();

	/** 七牛空间管理者 */
	private final BucketManager bucketManager = new BucketManager(auth);

	public static Random random = new Random();

	public static int r(int min, int max) {
		int num = 0;
		num = random.nextInt(max - min) + min;
		return num;
	}

	public static void main(String[] args) throws QiniuException {
	    
	    File file = new File("D:\\click_add.png");
	    Response response = uploadManager.put(file,
              "system/profile/click_add.png", auth.uploadToken(QINIU_SCOPE));
          String key = response.jsonToMap().get("key").toString();
          System.out.println(key);
	    
//		for (int j = 0; j < 10000; j++) {
//
//			// TODO Auto-generated method stub
//
//			String value = "";
//			// 在内存中创建一副图片
//			String dateStr = DateUtil.tranStrToDateStrToo();
//			int w = 120;
//			int h = 50;
//			BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB); // 在图片上画一个矩形当背景
//			Graphics g = img.getGraphics();
//			g.setColor(new Color(r(50, 250), r(50, 250), r(50, 250)));
//			g.fillRect(0, 0, w, h);
//			String str = "aqzxswedcfrvgtbhyujklp23456789";
//			for (int i = 0; i < 4; i++) {
//				g.setColor(new Color(r(50, 180), r(50, 180), r(50, 180)));
//				g.setFont(new Font("黑体", Font.PLAIN, 40));
//				char c = str.charAt(r(0, str.length()));
//				value = value + c;
//				g.drawString(String.valueOf(c), 10 + i * 30, r(h - 30, h));
//			}
//
//			// 画随机线
//			for (int i = 0; i < 25; i++) {
//				g.setColor(new Color(r(50, 180), r(50, 180), r(50, 180)));
//				g.drawLine(r(0, w), r(0, h), r(0, w), r(0, h));
//			}
//			// 把内存中创建的图像输出到文件中
//			File file = new File("E:\\jobwisdomPage\\" + dateStr + ".png");
//			try {
//				ImageIO.write(img, "png", file);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			System.out.println("图片输出完成");
//
//			try {
//				Thread.currentThread().sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//			Response response = uploadManager.put("E:\\jobwisdomPage\\" + dateStr + ".png",
//					"yzmpage/profile/" + dateStr + ".png", auth.uploadToken(QINIU_SCOPE));
//			String key = response.jsonToMap().get("key").toString();
//			System.out.println(key);
//
//			// 链接数据库
//			Connection con = null;
//			Statement stmt = null;
//			ResultSet rs = null;
//			try {
//
//				String sql = "insert into yzm_page_qiniu (page_url, page_value) values ('" + key + "', '" + value
//						+ "') ";
//
//				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//				// 注册驱动，说明是什么数据库
//
//				con = (Connection) DriverManager.getConnection(
//						"jdbc:mysql://112.74.210.155:3306/zb?useUnicode=true&characterEncoding=utf-8", "root",
//						"123456");// 取得链接,说明我从哪里查询
//				stmt = (Statement) con.createStatement();// 创建操作对象
//				stmt.executeUpdate(sql);// 执行操作
//				/*
//				 * while (rs.next()) {
//				 * 
//				 * System.out.println(rs.getString("comboId") + ":" +
//				 * rs.getString("goodsName"));// 处理结果
//				 * 
//				 * }
//				 */
//
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				// 关闭资源 释放链接
//				try {
//					if (rs != null) {
//						rs.close();
//						rs = null;
//					}
//					if (stmt != null) {
//						stmt.close();
//						stmt = null;
//					}
//					if (con != null) {
//						con.close();
//						con = null;
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
	}
}
