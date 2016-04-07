package com.lives.platform.web.contorller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lives.platform.web.entity.Address;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

/**
 * 七牛云存储相关api
 * @author 张进军
 * @date Jul 10, 2015 8:44:50 PM 
 */
@Controller
public class WeixinController {
	private static final String QINIU_AK = "y6orG-wW5CzUSJ_E0kk_jfCkEW16qZAu7ihg2g_n";

	private static final String QINIU_SK = "czEgmRjAh_MFLEmLB6Qien41FxgbAZV5u8Bwvm7j";

	private static final String QINIU_SCOPE = "lives";
	
	@RequestMapping("weixin/test")
	public String test(){
		return "weixin/upload";
	}
	@RequestMapping("weixin/test1")
	public void test1(Address addresses){
		System.out.println(addresses.getCity().split(",")[0]);
		System.out.println(addresses.getCity().split(",")[1]);
	}
	@RequestMapping(value = "/weixin/uptoken")
	public @ResponseBody Map<String, String> qiniuUptoken() throws Exception {
		Mac mac = new Mac(QINIU_AK, QINIU_SK);
        PutPolicy putPolicy = new PutPolicy(QINIU_SCOPE);
		String uptoken = putPolicy.token(mac);
		Map<String, String> r = new HashMap<String, String>();
		r.put("uptoken", uptoken);
		return r;
	}
	@RequestMapping(value = "/weixin/upload")
	@ResponseBody
	public Map<String, String> upload() throws Exception {
		
		return null;
	}
	
}
