package com.zefun.test;

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
    /**域名*/
    public static final String DO_MAIN = "http://7xss26.com2.z0.glb.qiniucdn.com";
    
    /**七牛授权对象*/
    private static final Auth auth = Auth.create(QINIU_AK, QINIU_SK);
    
    /**七牛上传管理者*/
    private static final UploadManager uploadManager = new UploadManager();
    
    /**七牛空间管理者*/
    private final BucketManager bucketManager = new BucketManager(auth);
    
//    public static void main(String[] args) throws QiniuException {
//        for (int i = 0; i < 100000; i++) {
//            Response response = uploadManager.put("D:\\20120525022541628.jpg", "jobwisdom/profile/"+i+".jpg", auth.uploadToken(QINIU_SCOPE));
//            String key = response.jsonToMap().get("key").toString();
//            System.out.println(key);
//        }
//    }
}
