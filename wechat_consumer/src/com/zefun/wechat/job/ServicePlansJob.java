package com.zefun.wechat.job;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.ServicePlanInfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.wechat.service.RedisService;

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
    
    
    
    /** 会员标识对应微信openid的hash key */
    public static final String WECHAT_MEMBERID_TO_OPENID_KEY_HASH = "wechat_memberid_to_openid_key_hash";
    
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
	            
	            Set<Integer> sendsIds = new HashSet<Integer>();
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
                    logger.info(openId);
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
     * 发送短信验证码
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
 
}

