package com.lives.platform.test.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.lives.platform.common.util.HttpClientUtils;

public class TestSendMessage {

	static String app_id = "app_id";  
    static String app_secret = "app_secret";  
    static String access_token = "";  
    static String randcode="099871";//自定义验证码，6位数字  
    static String phoneNo="15800000000";//自定义手机号，11位数字 
    
    private static String getAccess_Token() throws IOException, JSONException {  
        String postUrl = "https://oauth.api.189.cn/emp/oauth2/v2/access_token?grant_type=client_credentials&app_id="  
                + app_id + "&app_secret=" + app_secret;  
        String str = HttpClientUtils.sendPost(postUrl, "", "utf-8");
        JSONObject json = JSONObject.fromObject(str);
        return json.get("access_token").toString();  
    } 
    
    private static String sendSms(String userPhone) throws Exception {  
        access_token = getAccess_Token();  
        // System.out.println("test0    "+access_token);  
        Date date = new Date();  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String timestamp = dateFormat.format(date);  
        // System.err.println(timestamp);  
        TreeMap<String, String> paramsMap = new TreeMap<String, String>();  
        paramsMap.put("app_id", app_id);  
        paramsMap.put("access_token", access_token);  
        paramsMap.put("timestamp", timestamp);  
  
        String getUrl = "http://api.189.cn/v2/dm/randcode/token?app_id="  
                + app_id + "&access_token=" + access_token + "×tamp="  
                + timestamp + "&sign="  
                + ParamsSign.value(paramsMap, app_secret);  
        // System.err.println("test1  " + getUrl);  
        String str = HttpClientUtils.sendPost(getUrl, "", "utf-8");
        // System.err.println("test2  " + resJson);  
        JSONObject json = JSONObject.fromObject(str);
        // System.out.println("test3    " + json.get("token"));  
  
        TreeMap<String, String> paramsMap1 = new TreeMap<String, String>();  
        paramsMap1.put("app_id", app_id);  
        paramsMap1.put("access_token", access_token);  
        paramsMap1.put("token", json.get("token").toString());  
        paramsMap1.put("phone", userPhone);  
        paramsMap1.put("randcode", randcode);  
        paramsMap1.put("exp_time", "2");  
        paramsMap1.put("timestamp", timestamp);  
  
        String postUrl = "http://api.189.cn/v2/dm/randcode/sendSms";  
        String postEntity = "app_id=" + app_id + "&access_token="  
                + access_token + "&token=" + json.get("token") + "&phone="  
                + userPhone + "&randcode="  
                + randcode  
                + "&exp_time=" + "2" + "×tamp=" + timestamp + "&sign="  
                + ParamsSign.value(paramsMap1, app_secret);  
  
        String resJson1 = HttpClientUtils.sendPost(postUrl, postEntity, "utf-8");
        return resJson1;  
    }  
  
    public static void main(String[] args) throws Exception {  
        System.out.println(sendSms(phoneNo));  
    }  
}
