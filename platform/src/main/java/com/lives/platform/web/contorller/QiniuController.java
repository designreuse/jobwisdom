package com.lives.platform.web.contorller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lives.platform.web.dto.BaseDto;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

/**
 * 七牛云存储相关api
 * @author 张进军
 * @date Jul 10, 2015 8:44:50 PM 
 */
@Controller
public class QiniuController {
	private static final String QINIU_AK = "y6orG-wW5CzUSJ_E0kk_jfCkEW16qZAu7ihg2g_n";

	private static final String QINIU_SK = "czEgmRjAh_MFLEmLB6Qien41FxgbAZV5u8Bwvm7j";

	private static final String QINIU_SCOPE = "lives";
	
	@RequestMapping("qiniu/test")
	public String test(){
		return "upload/test";
	}
	
	@RequestMapping(value = "/qiniu/uptoken")
	public @ResponseBody Map<String, String> qiniuUptoken() throws Exception {
		Mac mac = new Mac(QINIU_AK, QINIU_SK);
        PutPolicy putPolicy = new PutPolicy(QINIU_SCOPE);
		String uptoken = putPolicy.token(mac);
		Map<String, String> r = new HashMap<String, String>();
		r.put("uptoken", uptoken);
		return r;
	}
	
	@RequestMapping(value = "/qiniu/delete")
	public BaseDto qinniuDelete(String key) throws Exception{
		Mac mac = new Mac(QINIU_AK, QINIU_SK);
        RSClient client  = new RSClient(mac);
        CallRet ret = client.delete(QINIU_SCOPE, key);
        if(ret == null){
        	return new BaseDto(0,"ok");
        }else{
        	return new BaseDto(-1,"no");
        }
	}
	
	
	public static void main(String[] args) {
		Mac mac = new Mac(QINIU_AK, QINIU_SK);
        RSClient client  = new RSClient(mac);
        CallRet ret = client.delete(QINIU_SCOPE, "lives/idCard/1436880245123");
        System.out.println(ret);
	}
}
