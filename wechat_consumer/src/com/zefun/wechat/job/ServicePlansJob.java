package com.zefun.wechat.job;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.ServicePlanInfo;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.ServicePlanInfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.wechat.service.RedisService;
import com.zefun.wechat.utils.App;
import com.zefun.wechat.utils.HttpClientUtil;

import net.sf.json.JSONObject;

/**
 * 
* @author 高国藩
* @date 2016年6月30日 上午10:56:21
 */
public class ServicePlansJob {
    /** 日志对象 */
    private Logger logger = Logger.getLogger(ServicePlansJob.class);
    /** 阿里请求接口 */
    private static String tbClientUrl = "http://gw.api.taobao.com/router/rest";
    /** 阿里请求接口 */
    private static String appKey = "23341881";
    /** 阿里请求接口 */
    private static String appSecret = "ebfd3e013a89513d4e64f7b56b801ae9";
    /** 阿里签名*/
    private static String idiograph = "门店提醒";
    /** 阿里基础api对象 */
    private static TaobaoClient tbClient = new DefaultTaobaoClient(tbClientUrl, appKey, appSecret);
    /**redisService*/
    @Autowired private RedisService redisService;
    /**servicePlanInfoMapper*/
    @Autowired private ServicePlanInfoMapper servicePlanInfoMapper;
    /** 会员分组 */
    @Autowired private MemberScreeningMapper memberScreeningMapper;
    /** 会员信息 */
    @Autowired private MemberInfoMapper memberInfoMapper;
    /** 门店信息 */
    @Autowired private StoreInfoMapper storeInfoMapper;
    /** 门店微信信息 */
    @Autowired private StoreWechatMapper storeWechatMapper;
    /** 会员标识对应微信openid的hash key */
    public static final String WECHAT_MEMBERID_TO_OPENID_KEY_HASH = "wechat_memberid_to_openid_key_hash";
    /** 模板url配置信息 */
    private static final String serverPost = "http://job.jobwisdom.cn/jobwisdom/memberCenter/view/orderAppointment/";
    
    /**
     * 针对门店设置的服务计划进行会员实施的推送
    * @author 高国藩
    * @date Aug 23, 2015 9:04:47 PM
     */
    @Transactional
	public void execute() {
	    logger.info("ServicePlansJob execute start... ");
	    try {
	        logger.info("服务计划已启动");
	        
	        // 第一步 取得当前时间下的服务计划列表,并把所有的会员id,电话取出来,进行下列的推送,短信推送校验,是否需要短信推送
	        List<ServicePlanInfo> servicePlanInfos = servicePlanInfoMapper.selectPlanIsOk();
	        for (int i = 0; i < servicePlanInfos.size(); i++) {
	            ServicePlanInfo servicePlanInfo = servicePlanInfos.get(i);
	            StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(servicePlanInfo.getStoreId());
	            StoreWechat storeWechat = storeWechatMapper.selectByPrimaryKey(storeInfo.getStoreAccount());
	            Set<Integer> sendsIds = new HashSet<Integer>();
	            // 从会员卡1, 分组2 两者这选择出发送的会员数据
	            if (servicePlanInfo.getSendMemberType() == 1){
	                List<String> levels = Arrays.asList(servicePlanInfo.getMemberId().toString());
	                List<Integer> memberIds = memberInfoMapper.selectMemberIdsByLevelIds(levels);
	                sendsIds.addAll(memberIds);
	            }
                if (servicePlanInfo.getSendMemberType() == 2){
                    List<String> groups = Arrays.asList(servicePlanInfo.getMemberId().toString());
                    List<ScreeningDto> dtos = memberScreeningMapper.selectByDtos(groups);
                    for (int j = 0; j < dtos.size(); j++) {
                        List<Integer> memberIds = memberInfoMapper.selectMemberIdsByDtos2(com.zefun.wechat.utils.MessageUtil.transBean2Map(dtos.get(i)));
                        sendsIds.addAll(memberIds);
                    }
                }
                Iterator<Integer> it = sendsIds.iterator();
                while (it.hasNext()){
                    Integer memberId = it.next();
                    String openId = redisService.hget(WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
                    this.sendCouponTempleMsg(servicePlanInfo.getServiceTime(), servicePlanInfo.getServiceProjectName(), openId, serverPost+storeWechat.getStoreAccount()+"/1", storeWechat.getTmServiceTopic(), storeWechat.getStoreAccount());
                    // 如果允许短信发送,进行短息的发送
                    if (servicePlanInfo.getIsSms() == 1 && storeInfo.getBalanceSms() >= sendsIds.size()){
                        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
                        sendVerifyCode(memberInfo.getPhone(), memberInfo.getName(), servicePlanInfo.getServiceProjectName(), storeInfo.getStoreName(), servicePlanInfo.getServiceTime());
                    }
                }
                // 第三部 ,发送成功后 如果是短信进行的推送, 将门店下的短信数量进行一次减法操作
                if (servicePlanInfo.getIsSms() == 1 && storeInfo.getBalanceSms() >= sendsIds.size()){
                    Integer smsNum = sendsIds.size();
                    storeInfo.setBalanceSms(storeInfo.getBalanceSms()-smsNum);
                    storeInfoMapper.updateByPrimaryKey(storeInfo);
                }
                servicePlanInfo.setIsDeleted(1);
                servicePlanInfoMapper.updateByPrimaryKeySelective(servicePlanInfo);
            }
	        
        } catch (Exception e) {
            logger.info("ServicePlansJob execute Exception! ");
        }
		logger.info("ServicePlansJob execute finish! ");
	}
	
	/**
     * 发送短信推送信息
    * @author 高国藩
    * @date Feb 26, 2016 9:00:49 PM
    * @param storeId    门店标识 
    * @param phone  手机号码
    * @param code   验证码
    * @param desc   描述信息
    * @return   成功返回true，失败返回false
     */
    public boolean sendVerifyCode(String phone, String name, String project, String storeName, String date) {
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        JSONObject params = new JSONObject();
        params.put("name", name);
        params.put("date", date);
        params.put("store", storeName);
        params.put("project", project);
        req.setSmsType("normal");
        req.setSmsFreeSignName(idiograph);
        req.setSmsParam(params.toString());
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_11380274");
        try {
            AlibabaAliqinFcSmsNumSendResponse response = tbClient.execute(req);
            logger.info(response.getBody());
            return true;
        }
        catch (ApiException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 模板发送优惠券
    * @author 高国藩
    * @date 2015年9月15日 下午4:21:35
    * @param title 标题
    * @param name 优惠券名称
    * @param price 优惠券价格
    * @param use 优惠券适用
    * @param stopTime 截止日期
    * @param openId 发送对象
    * @param url 点击跳转链接
    * @param storeId 门店
    * @param tempId 微信模板ID
     */
    public void sendCouponTempleMsg(String dateTime, String projectName, String openId, String url, String tempId, String storeAccount) {
        Map<String, Object> map = new HashMap<String, Object>();
        // data 数据
        Map<String, Object> data = new HashMap<String, Object>();

        // 提示语句
        Map<String, String> first = new HashMap<String, String>();
        first.put("value", "服务计划提醒");
        first.put("color", "#173177");
        
        // 时间
        Map<String, String> date = new HashMap<String, String>();
        date.put("value", dateTime);
        date.put("color", "#173177");

        // 内容
        Map<String, String> desc = new HashMap<String, String>();
        desc.put("value", projectName);
        desc.put("color", "#173177");

        // 建议
        Map<String, String> advice = new HashMap<String, String>();
        advice.put("value", "针对您的消费记录, 为您推荐了("+projectName+")"+"服务项目");
        advice.put("color", "#173177");
        
        //结束语
        Map<String, String> remark = new HashMap<String, String>();
        remark.put("value", "感谢您的光临, 欢迎您的惠顾");
        remark.put("color", "#173177");
        
        data.put("first", first);
        data.put("keyword1", date);
        data.put("keyword2", desc);
        data.put("keyword3", advice);
        data.put("remark", remark);

        map.put("data", data);
        map.put("touser", openId);
        map.put("template_id", tempId);
        map.put("url", url);
        map.put("topcolor", "#FF0000");

        HttpClientUtil.httpRequest(getTemplSendUrl(storeAccount), "POST", JSONObject.fromObject(map).toString());
    }
    
    private String getTemplSendUrl(String storeAccount) {
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeAccount);
        return "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
    }
 
}

