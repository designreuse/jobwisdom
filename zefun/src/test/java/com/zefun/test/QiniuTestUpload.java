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
	    
	    File file = new File("D:\\memberTM.xlsx");
	    Response response = uploadManager.put(file,
              "jobwisdom/TM/memberTM.xlsx", auth.uploadToken(QINIU_SCOPE));
          String key = response.jsonToMap().get("key").toString();
          System.out.println(key);
	}
}
