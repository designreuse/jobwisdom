package com.zefun.wechat.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.MessageUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.entity.AutomaticKey;
import com.zefun.web.entity.AutomaticReply;
import com.zefun.web.entity.ItemCensus;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberScreening;
import com.zefun.web.entity.MsgReply;
import com.zefun.web.entity.PictureLibrary;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.entity.WechatSubscribe;
import com.zefun.web.mapper.AutomaticKeyMapper;
import com.zefun.web.mapper.AutomaticReplyMapper;
import com.zefun.web.mapper.ItemCensusMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.MsgReplyMapper;
import com.zefun.web.mapper.PictureLibraryMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.web.mapper.WechatGroupInfoMapper;
import com.zefun.web.mapper.WechatMemberMapper;
import com.zefun.web.mapper.WechatSubscribeMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.RedisService;
import com.zefun.wechat.dto.ArticleDto;
import com.zefun.wechat.dto.MediaIdDto;
import com.zefun.wechat.dto.NewsMessageDto;
import com.zefun.wechat.dto.SendMessagesDto;
import com.zefun.wechat.dto.TextMessageDto;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 微信端消息service
* @author 高国藩
* @date 2015年8月11日 上午11:47:56
 */
@Service
public class WeixinMessageService {
    /**微信数据*/
	@Autowired
	private WeixinUploadService weixinUploadService;
    /** 图文消息*/
	@Autowired
	private AutomaticReplyMapper automaticReplyMapper;
	/** 图片库*/
	@Autowired
	private PictureLibraryMapper pictureLibraryMapper;
    /** 菜单点击匹配想*/
	@Autowired
	private AutomaticKeyMapper automaticKeyMapper;
    /** 自动回复信息*/
	@Autowired
	private MsgReplyMapper msgReplyMapper;
	/** 图文消息统计*/
	@Autowired
	private ItemCensusMapper censusMapper;
	/**会员等级*/
	@Autowired
	private MemberLevelMapper memberLevelMapper;
	/**会员信息*/
    @Autowired
    private MemberInfoMapper memberInfoMapper;
	/**筛选器*/
	@Autowired
	private MemberScreeningMapper memberScreeningMapper;
	/**redis*/
	@Autowired
	private RedisService redisService;
	/**微信关注操作对象*/
	@Autowired
	private WechatSubscribeMapper wechatSubscribeMapper;
	/**微信会员映射操作对象*/
	@Autowired
	private WechatMemberMapper wechatMemberMapper;
	/**门店基础数据操作对象*/
	@Autowired
	private StoreSettingMapper storeSettingMapper;
    /**会员信息服务对象*/
    @Autowired
    private MemberInfoService memberInfoService;
    /**门店微信关联操作对象*/
    @Autowired
    private StoreWechatMapper storeWechatMapper;
    /** 微信分组信息操作对象 */
    @Autowired
    private WechatGroupInfoMapper wechatGroupInfoMapper;
	/** 日志*/
	private Logger logger = Logger.getLogger(WeixinMessageService.class);
	
	/**
	 * 处理微信发来的请求
	* @author 高国藩
	* @date 2015年8月11日 上午11:29:11
	* @param request 请求封装
	* @return 返回展示信息
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = "";
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			logger.info("消息关注者 "+fromUserName);
			logger.info("开发者 "+toUserName);
			/**消息类型 */
			String msgType = requestMap.get("MsgType");
            /**事件推送*/
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");
				
				/**订阅*/
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				    /**根据微信开发者id进行查询门店设置内容*/
                    StoreWechat storeWechat = storeWechatMapper.selectByWechatId(toUserName);
                    
				    //如果用户是首次关注，需要查询门店是否有赠送内容
				    WechatSubscribe wechatSubscribe = wechatSubscribeMapper.selectByPrimaryKey(fromUserName);
				    
				    WechatSubscribe ws = new WechatSubscribe();
				    ws.setOpenId(fromUserName);
				    ws.setIsSubscribe(1);
				    String curTime = DateUtil.getCurTime();
				    ws.setUpdateTime(curTime);
				    //首次关注
				    if (wechatSubscribe == null) {
				        ws.setCreateTime(curTime);
				        wechatSubscribeMapper.insert(ws);
				        
				        //先检查该用户是否已注册为会员,如果已注册，查找会员所属门店的奖励，否则先记录奖励，后期注册时再进行赠送
				        String userId = redisService.hget(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, fromUserName);
                        if (StringUtils.isNotBlank(userId)) {
                            int memberId = Integer.parseInt(userId);
                            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
                            StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(memberInfo.getStoreId());
                            //查看是否有优惠券奖励
                            String coupon = storeSetting.getFirstFollowCoupon();
                            String[] couponList = coupon.split(",");
                            if (StringUtils.isNotBlank(coupon)) {
                                for (String c : couponList) {
                                    int couponId = Integer.parseInt(c);
                                    memberInfoService.presentCouponToMember(memberId, couponId);
                                }
                            }
                            
                            //查看是否有礼金奖励
                            int gift = storeSetting.getFirstFollowGift();
                            if (gift > 0) {
                                BigDecimal money = new BigDecimal(gift);
                                memberInfoService.presentGiftmoneyToMember(memberId, money, "首次关注赠送");
                                memberInfoService.wipeCache(memberId);
                            }
                        }
                        else {
                            redisService.sadd(App.Redis.WECHAT_OPENID_TO_SUBSCRIBE_AWARD_SET, fromUserName);
                        }
				    } 
				    //再次关注
				    else {
				        wechatSubscribeMapper.updateByPrimaryKey(ws);
				    }
                    redisService.hset(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, fromUserName, "1");
                    
//					Map<String, Integer> map = new HashMap<String, Integer>();
//					map.put("storeId", storeWechat.getStoreId());
//					map.put("msgStatus", 1);
//					MsgReply msgReply =  msgReplyMapper.selectReplyByParam(map);
//					/**判断回复类型进行回复*/
//					if (msgReply!=null&&msgReply.getMsgType().equals("text")) {
//						return replyTextMessage(msgReply.getMsgText(), fromUserName, toUserName);
//					} 
//					else if (msgReply!=null&&msgReply.getMsgType().equals("news")) {
//						/**回复图文消息*/
//						return replyNewsMessage(msgReply.getMediaId(), fromUserName, toUserName);
//					}
				}
				/** 取消订阅*/
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    WechatSubscribe ws = new WechatSubscribe();
                    ws.setOpenId(fromUserName);
                    ws.setIsSubscribe(0);
                    ws.setUpdateTime(DateUtil.getCurTime());
                    wechatSubscribeMapper.updateByPrimaryKey(ws);
                    redisService.hset(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, fromUserName, "0");
                }
				/**自定义菜单点击事件*/
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					/**事件KEY值，与创建自定义菜单时指定的KEY值对应*/
					String eventKey = requestMap.get("EventKey");
					/**此处用key去查找回复类型,如果是1说明文字回复,如果是2,查找图文消息素材*/
					AutomaticKey automaticKey = automaticKeyMapper.selectRespByKey(eventKey);
					if (automaticKey == null) {
					    return "";
					}
					else if (automaticKey.getAutomaticType()==1){
						/**回复文本消息*/
						String respContent = automaticKey.getAutomaticText();
						return replyTextMessage(respContent, fromUserName, toUserName);
					}
					else {
						/**回复图文消息*/
						return replyNewsMessage(automaticKey.getMediaId(), fromUserName, toUserName);
					}
				}
				/**推送图文消息发送状态*/
				else if (eventType.equals(MessageUtil.EVENT_ITEMS_STATUS)){
				    String msgId = requestMap.get("MsgID");
				    ItemCensus itemCensus = censusMapper.selectByMsgId(msgId);
				    String status = requestMap.get("Status");
				    if (status.equals("send success")) {
				        status = "成功";
				    }
				    String sentCount = requestMap.get("SentCount");
				    String errorCount = requestMap.get("ErrorCount");
				    itemCensus.setSentCount(sentCount);
				    itemCensus.setErrorCount(errorCount);
				    itemCensus.setMsgStatus(status);
				    censusMapper.updateByPrimaryKey(itemCensus);
				    logger.info("发送的图文消息msgId为:"+msgId+",正在更新状态...");
				    /**在redis中注册数据,暂时只是统计了一个mediaId被家发送,但是没有更新门店中的分数*/
				    //redisSentinelService.zadd(itemCensus.getMediaId(), 1, itemCensus.getStoreId().toString());
				}
			}
			/**消息回复信息*/
			else if (msgType.equals("text")||msgType.equals("voice")||msgType.equals("image")){
				/**还没有成为会员,自动回复文字*/
                /**根据微信开发者id进行查询门店设置内容*/
//                StoreWechat storeWechat = storeWechatMapper.selectByWechatId(toUserName);
//                Map<String, Integer> map = new HashMap<String, Integer>();
//                map.put("storeId", storeWechat.getStoreId());
//				map.put("msgStatus", 2);
//				MsgReply msgReply =  msgReplyMapper.selectReplyByParam(map);
//				/**判断回复类型进行回复*/
//				if (msgReply!=null&&msgReply.getMsgType().equals("text")){
//					return replyTextMessage(msgReply.getMsgText(), fromUserName, toUserName);
//				}
//				else if (msgReply!=null&&msgReply.getMsgType().equals("news")){
//					/**回复图文消息*/
//					return replyNewsMessage(msgReply.getMediaId(), fromUserName, toUserName);
//				}
			}
			/**查看响应信息*/
			logger.info("respMessage" + respMessage);
	    } 
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 初始化门店菜单
	* @author 高国藩
	* @date Jan 26, 2016 8:49:07 PM
	* @param storeAccount   门店标识
	* @return  成功返回true，失败返回false
	 */
	public boolean initNormalMenu(String storeAccount){
	    String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, String.valueOf(storeAccount));
        
        JSONObject normalMemu = new JSONObject();
        normalMemu.put("button", getNormalMenuList(storeAccount));
        
        String url = Url.Wechat.CREATE_NORMAL_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject resultJson = WeixinUploadService.httpRequest(url, "POST", normalMemu.toString());
        if (resultJson.containsKey("errcode") && resultJson.getInt("errcode") != 0) {
            return false;
        }
        return true;
    }
	
	
	/**
     * 初始化门店菜单
    * @author 高国藩
    * @date Jan 26, 2016 8:49:07 PM
    * @param storeId    门店标识
    * @param groupType  分组类型(1:会员，2:员工，3:老板，4:无身份)
    * @param groupId    微信分组ID
    * @return  成功返回true，失败返回false
     */
    public boolean initConditionalMenu(String storeId, int groupType, int groupId){
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, String.valueOf(storeId));
        
        JSONObject conditionalMenu = new JSONObject();
        JSONArray buttonList = new JSONArray();
        
        //会员菜单
        if (groupType == 1) {
            
            JSONObject wechatShopButton = new JSONObject();
            wechatShopButton.put("name", "微官网");
            JSONArray wechatShopButtonList = new JSONArray();
            
            JSONObject shopButton = new JSONObject();
            shopButton.put("type", "view");
            shopButton.put("name", "在线商城");
            shopButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_SHOP_CENTER
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "1"));
            wechatShopButtonList.add(shopButton);
            
            JSONObject storeInfoButton = new JSONObject();
            storeInfoButton.put("type", "view");
            storeInfoButton.put("name", "门店介绍");
            storeInfoButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_STORE_INFO
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "1"));
            wechatShopButtonList.add(storeInfoButton);
            wechatShopButton.put("sub_button", wechatShopButtonList);
            buttonList.add(wechatShopButton);
            
            
            //打印照片
//            JSONObject personalButton = new JSONObject();
//            personalButton.put("name", "个人中心");
//            
//            JSONArray personalSubButtons = new JSONArray();
//            
//            JSONObject memberCenterButton = new JSONObject();
//            memberCenterButton.put("type", "view");
//            memberCenterButton.put("name", "会员中心");
//            memberCenterButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_HOME
//                    .replace("{storeId}", String.valueOf(storeId))
//                    .replace("{businessType}", "1"));
//            personalSubButtons.add(memberCenterButton);
//            
//            JSONObject printPhotoButton = new JSONObject();
//            printPhotoButton.put("type", "view");
//            printPhotoButton.put("name", "照片打印");
//            printPhotoButton.put("url", 
//                    "http://mp.weixin.qq.com/s?__biz=MzA3MjU2NTAyNA==&mid=216551477&idx=1&sn=9c0ef19a43b089f234401cdc543c313f#rd");
//            personalSubButtons.add(printPhotoButton);
//            
//            personalButton.put("sub_button", personalSubButtons);
//            buttonList.add(personalSubButtons);
            
            //---------------------------------------------
            
            JSONObject appointmentButton = new JSONObject();
            appointmentButton.put("type", "view");
            appointmentButton.put("name", "在线预约");
            appointmentButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_ORDER_APPOINTMENT
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "1"));
            buttonList.add(appointmentButton);
            
            JSONObject memberCenterButton = new JSONObject();
            memberCenterButton.put("type", "view");
            memberCenterButton.put("name", "会员中心");
            memberCenterButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_HOME
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "1"));
            buttonList.add(memberCenterButton);
        }
        //员工菜单
        else if (groupType == 2) {
            JSONObject staffCenterButton = new JSONObject();
            staffCenterButton.put("type", "view");
            staffCenterButton.put("name", "个人中心");
            staffCenterButton.put("url", App.System.SERVER_BASE_URL + Url.Staff.VIEW_STAFF_CENTER
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "2"));
            buttonList.add(staffCenterButton);
            
            //打印照片
//            JSONObject personalButton = new JSONObject();
//            personalButton.put("name", "个人中心");
//            
//            JSONArray personalSubButtons = new JSONArray();
//            
//            JSONObject memberCenterButton = new JSONObject();
//            memberCenterButton.put("type", "view");
//            memberCenterButton.put("name", "个人中心");
//            memberCenterButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_HOME
//                    .replace("{storeId}", String.valueOf(storeId))
//                    .replace("{businessType}", "1"));
//            personalSubButtons.add(memberCenterButton);
//            
//            JSONObject printPhotoButton = new JSONObject();
//            printPhotoButton.put("type", "view");
//            printPhotoButton.put("name", "照片打印");
//            printPhotoButton.put("url", 
//                    "http://mp.weixin.qq.com/s?__biz=MzA3MjU2NTAyNA==&mid=216551477&idx=1&sn=9c0ef19a43b089f234401cdc543c313f#rd");
//            personalSubButtons.add(printPhotoButton);
//            
//            personalButton.put("sub_button", personalSubButtons);
//            buttonList.add(personalSubButtons);
            //---------------------------------------------
            
            JSONObject receptionButton = new JSONObject();
            receptionButton.put("type", "view");
            receptionButton.put("name", "手机开单");
            receptionButton.put("url", App.System.SERVER_BASE_URL + Url.Staff.VIEW_HOME
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "2"));
            buttonList.add(receptionButton);
            
            JSONObject orderListButton = new JSONObject();
            orderListButton.put("type", "view");
            orderListButton.put("name", "我的订单");
            orderListButton.put("url", App.System.SERVER_BASE_URL + Url.Staff.VIEW_EMPLOYEE_ORDER
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "2"));
            buttonList.add(orderListButton);
        }
        //老板菜单
        else if (groupType == 3) {
            JSONObject storeButton = new JSONObject();
            storeButton.put("type", "view");
            storeButton.put("name", "我的门店");
            storeButton.put("url", App.System.SERVER_BASE_URL + Url.Boss.VIEW_BOSS_HOME
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "2"));
            buttonList.add(storeButton);
            
            JSONObject receptionButton = new JSONObject();
            receptionButton.put("type", "view");
            receptionButton.put("name", "手机开单");
            receptionButton.put("url", App.System.SERVER_BASE_URL + Url.Staff.VIEW_HOME
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "2"));
            buttonList.add(receptionButton);
            
            JSONObject performanceButton = new JSONObject();
            performanceButton.put("type", "view");
            performanceButton.put("name", "业绩报表");
            performanceButton.put("url", App.System.SERVER_BASE_URL + Url.Boss.VIEW_BOSS_OBJECTIVE
                    .replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "2"));
            buttonList.add(performanceButton);
        }
        //未绑定用户菜单
        else if (groupType == 4) {
            buttonList = getNormalMenuList(storeId);
        }
        conditionalMenu.put("button", buttonList);
        
        JSONObject matchrule = new JSONObject();
        matchrule.put("group_id", groupId);
        conditionalMenu.put("matchrule", matchrule);
        
        String url = Url.Wechat.CREATE_CONDITIONAL_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject resultJson = WeixinUploadService.httpRequest(url, "POST", conditionalMenu.toString());
        if (resultJson.containsKey("errcode") && resultJson.getInt("errcode") != 0) {
            return false;
        }
        return true;
    }
    
    
    /**
     * 获取微信默认菜单
    * @author 高国藩
    * @date Jan 27, 2016 11:39:58 AM
    * @param storeId    门店标识
    * @return   微信默认菜单
     */
    private JSONArray getNormalMenuList(String storeId){
        JSONArray buttonList = new JSONArray();
        
        JSONObject wechatShopButton = new JSONObject();
        wechatShopButton.put("name", "微官网");
        JSONArray wechatShopButtonList = new JSONArray();
        
        JSONObject storeInfoButton = new JSONObject();
        storeInfoButton.put("type", "view");
        storeInfoButton.put("name", "门店介绍");
        storeInfoButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_STORE_INFO
                .replace("{storeId}", String.valueOf(storeId))
                .replace("{businessType}", "1"));
        wechatShopButtonList.add(storeInfoButton);
        
        JSONObject shopButton = new JSONObject();
        shopButton.put("type", "view");
        shopButton.put("name", "在线商城");
        shopButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_SHOP_CENTER
                .replace("{storeId}", String.valueOf(storeId))
                .replace("{businessType}", "1"));
        wechatShopButtonList.add(shopButton);
        
        wechatShopButton.put("sub_button", wechatShopButtonList);
        
        buttonList.add(wechatShopButton);
        
        JSONObject appointmentButton = new JSONObject();
        appointmentButton.put("type", "view");
        appointmentButton.put("name", "在线预约");
        appointmentButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_ORDER_APPOINTMENT
                .replace("{storeId}", String.valueOf(storeId))
                .replace("{businessType}", "1"));
        buttonList.add(appointmentButton);
        
        JSONObject personalButton = new JSONObject();
        personalButton.put("name", "个人中心");
        
        JSONArray personalSubButtons = new JSONArray();
        
        JSONObject memberCenterButton = new JSONObject();
        memberCenterButton.put("type", "view");
        memberCenterButton.put("name", "会员中心");
        memberCenterButton.put("url", App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_HOME
                .replace("{storeId}", String.valueOf(storeId))
                .replace("{businessType}", "1"));
        personalSubButtons.add(memberCenterButton);
        
        JSONObject staffCenterButton = new JSONObject();
        staffCenterButton.put("type", "view");
        staffCenterButton.put("name", "员工登录");
        staffCenterButton.put("url", App.System.SERVER_BASE_URL + Url.Staff.VIEW_HOME
                .replace("{storeId}", String.valueOf(storeId))
                .replace("{businessType}", "2"));
        personalSubButtons.add(staffCenterButton);
        
        //打印照片
//        JSONObject printPhotoButton = new JSONObject();
//        printPhotoButton.put("type", "view");
//        printPhotoButton.put("name", "照片打印");
//        printPhotoButton.put("url", 
//                "http://mp.weixin.qq.com/s?__biz=MzA3MjU2NTAyNA==&mid=216551477&idx=1&sn=9c0ef19a43b089f234401cdc543c313f#rd");
//        personalSubButtons.add(printPhotoButton);
        //---------------------------------------------
        
        personalButton.put("sub_button", personalSubButtons);
        buttonList.add(personalButton);
        return buttonList;
    }
    
	
	/**
	 * 删除门店对应微信公众号的所有菜单，含个性化分组菜单
	* @author 高国藩
	* @date Jan 26, 2016 7:25:31 PM
	* @param storeAccount   门店标识
	 */
	public void deleteMenu(String storeAccount){
	    String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, String.valueOf(storeAccount));
	    String url = Url.Wechat.DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
	    WeixinUploadService.httpRequest(url, "GET", null);
	}
	
	
	/**
	 * 创建微信分组
	* @author 高国藩
	* @date Jan 26, 2016 7:16:07 PM
	* @param storeAccount   门店标识
	* @param groupName 分组名称
	* @return  成功返回分组ID，失败返回空
	 */
	public Integer createGroup(String storeAccount, String groupName){
	    String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, String.valueOf(storeAccount));
	    String url = Url.Wechat.CREATE_GROUP_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = new JSONObject();
        JSONObject group = new JSONObject();
        group.put("name", groupName);
        json.put("group", group);
        JSONObject resultJson = WeixinUploadService.httpRequest(url, "POST", json.toString());
        if (resultJson.containsKey("errcode") && resultJson.getInt("errcode") != 0) {
            logger.error("createGroup error : " + resultJson.toString());
            return null;
        }
        return resultJson.getJSONObject("group").getInt("id");
	}
	
	
	/**
	 * 移动用户分组
	* @author 高国藩
	* @date Jan 27, 2016 11:24:47 AM
	* @param storeAccount   门店标识
	* @param groupId        微信分组ID
	* @param openId         微信用户ID
	* @return  成功返回true，失败返回false
	 */
	public boolean moveGroup(String storeAccount, int groupId, String openId){
//	    storeId = storeInfoMapper.selectMainIdByStoreId(storeId);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, String.valueOf(storeAccount));
        String url = Url.Wechat.MOVE_GROUP_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = new JSONObject();
        json.put("to_groupid", groupId);
        json.put("openid", openId);
        JSONObject resultJson = WeixinUploadService.httpRequest(url, "POST", json.toString());
        if (resultJson.containsKey("errcode") && resultJson.getInt("errcode") != 0) {
            logger.error("moveGroup error : " + resultJson.toString());
            return false;
        }
        return true;
    }
	
	
	/**
	 * 根据分组类型移动分组
	* @author 高国藩
	* @date Jan 27, 2016 11:27:27 AM
	* @param storeAccount  门店标识
	* @param groupType     分组类型(1:会员，2:员工，3:老板，4:无身份)
	* @param openId        微信用户ID
	 */
	public void moveGroupByGroupType(String storeAccount, int groupType, String openId){
	    Map<String, Object> map = new HashMap<>();
        map.put("storeAccount", storeAccount);
        map.put("groupType", groupType);
        Integer groupId = wechatGroupInfoMapper.selectGroupIdByStoreIdAndGroupType(map);
        if (groupId != null) {
            moveGroup(storeAccount, groupId, openId);
        }
	}

	
	/**
	 * 获得文本自动回复的信息
	* @author 高国藩
	* @date 2015年8月11日 上午11:42:57
	* @param context 文本内容
	* @param fromUserName 发送者
	* @param toUserName 接受者
	* @return 返回String
	 */
	public String replyTextMessage(String context, String fromUserName, String toUserName){
		/**回复文本消息*/
		TextMessageDto textMessage = new TextMessageDto();
		textMessage.setContent(context);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setMsgType("text");
		return MessageUtil.textMessageToXml(textMessage);
	}
	
	/**
	 * 回复图文消息
	* @author 高国藩
	* @date 2015年8月11日 上午11:47:10
	* @param mediaId 图文消息ID
	* @param fromUserName 发送者
	* @param toUserName 接受者
	* @return 返回String
	 */
	public String replyNewsMessage(String mediaId, String fromUserName, String toUserName){
		List<AutomaticReply> automaticReplies = automaticReplyMapper.selectByMediaId(mediaId);
		NewsMessageDto newsMessage = new NewsMessageDto();
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		List<ArticleDto> articleList = new ArrayList<ArticleDto>();
		for (int i = 0; i < automaticReplies.size(); i++) {
			AutomaticReply automaticReply = automaticReplies.get(i);
			ArticleDto articleDto = new ArticleDto();
			articleDto.setTitle(automaticReply.getTitle());
			articleDto.setDescription(automaticReply.getDescription());
			articleDto.setPicUrl(automaticReply.getPicUrl());
			articleDto.setUrl(automaticReply.getUrl());
			articleList.add(articleDto);
		}
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		newsMessage.setMsgType("news");
		return MessageUtil.newsMessageToXml(newsMessage);
	}

	/**
	 * 群发图文消息
	* @author 高国藩
	* @date 2015年8月13日 上午10:52:45
    * @param level 会员卡等级
    * @param sceening 筛选器
    * @param mediaId 图文
    * @param accessToken 微信认证
    * @param storeId 门店
    * @param fatherMediaId 复制的图文消息
    * @return 接受信息
	 */
    public BaseDto sendMessagesItem(String level, String sceening, String mediaId,
            String accessToken, Integer storeId, String fatherMediaId) {
        String url = Url.Wechat.SEND_MESSAGEBYID;
        url = url.replace("ACCESS_TOKEN", accessToken);
        String[] sceenings = sceening.split(",");
        String[] levels = level.split(",");
        //查询出发送的会员名单
        List<Integer> ls = serchMemberIds(sceenings, levels);
        if (ls==null||ls.size()<=1){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "当前分组不满足发送条件");
        }
        //将会员名单中的发送次数为0的名单去除
        List<Integer> bs = memberScreeningMapper.selectSendItemsWechatCountNotZero(ls);
        //sql 记录本次发送的条件
        StringBuffer sql = new StringBuffer();
        if (sceening!=null&&!sceening.equals("")){
            for (int i = 0; i < sceenings.length; i++) {
                MemberScreening s = memberScreeningMapper.selectByPrimaryKey(Integer.valueOf(sceenings[i]));
                sql.append(s.toString());
            }
        }
        /**此处根据会员等级以及筛选器的名单查询会员信息,并获得openId*/
        List<String> touser = wechatMemberMapper.selectOpenIdsByMemberIdList(bs);
        MediaIdDto mpnews = new MediaIdDto(mediaId);
        SendMessagesDto message = new SendMessagesDto(touser, mpnews);
        JSONObject json = WeixinUploadService.httpRequest(url, "POST",
                  JSONObject.fromObject(message).toString());
        if (json.getString("errcode").equals("0")){
            String mediaIdMpnews = fatherMediaId;
            String msgId = json.getString("msg_id");
            ItemCensus census = new ItemCensus();
            census.setMediaId(mediaIdMpnews);
            census.setMsgId(msgId);
            census.setStoreId(storeId);
            census.setMsgTime(String.valueOf(new Date().getTime()));
            census.setHasGroup(sql.toString());
            censusMapper.insert(census);
            //将刚才发送的人员的微信次数-1
            memberScreeningMapper.updateWechatCountByMemberId(bs);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, WeixinConfigService.getErrorMessage().get(json.getString("errcode"))); 
        }
        if (fatherMediaId != null){
            weixinUploadService.mediaCountItems(storeId, fatherMediaId);
            //redisService.zadd(fatherMediaId, Double.valueOf("1"), storeId.toString());
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 群发文本消息
    * @author 高国藩
    * @date 2015年8月13日 上午11:13:06
    * @param touser 接受消息
    * @param text 文本信息
    * @param accessToken 微信认证
    * @return 状态码
     */
    public BaseDto sendMessagesText(List<String> touser, String text,
            String accessToken) {
        String url = Url.Wechat.SEND_MESSAGEBYID;
        url = url.replace("ACCESS_TOKEN", accessToken);
        SendMessagesDto message = new SendMessagesDto(touser, text);
        JSONObject json = WeixinUploadService.httpRequest(url, "POST",
                  JSONObject.fromObject(message).toString());
        return new BaseDto(0,  json.getString("errmsg"));
    }

    /**
     * 获取已经新增的图文消息
    * @author 高国藩
    * @date 2015年8月28日 下午4:17:07
    * @param storeId 门店信息
    * @param request 请求
    * @param response 返回结果
    * @return xinxi 
     * @throws IOException 
     */
    public ModelAndView getItems(Integer storeId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**智放图文库*/
        List<AutomaticReply> items = automaticReplyMapper.selectItemsByStoreId(App.System.WECHAT_ZEFUN_STORE_ID);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setCreateTime(DateUtil.getDate(items.get(i).getCreateTime()));
            /*Long count = redisService.zcount(items.get(i).getMediaId(), 0l, 1l);
            items.get(i).setZcount(count);*/
        }
        /**我的图文库*/
        List<AutomaticReply> slefItems = automaticReplyMapper.selectItemsByStoreId(storeId); 
        for (int i = 0; i < slefItems.size(); i++) {
            slefItems.get(i).setCreateTime(DateUtil.getDate(slefItems.get(i).getCreateTime()));
        }
        /**图片库*/
        List<PictureLibrary> pictures = pictureLibraryMapper.selectPicturesByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Wechat.SHOW_ITEMS);
        view.addObject("items", items);
        view.addObject("slefItems", slefItems);
        view.addObject("pictures", pictures);
        return view;
    }
    

    /**
     * 根据mideaId查询图文消息
    * @author 高国藩
    * @date 2015年8月31日 上午11:25:57
    * @param mediaId 图文消息
    * @param storeId 门店
    * @return 跳转附带图文消息信息
     */
    public ModelAndView sendUpdateItem(Integer storeId, String mediaId) {
        List<AutomaticReply> ls = automaticReplyMapper.selectByMediaId(mediaId);
        /**图片库*/
        List<PictureLibrary> pictures = pictureLibraryMapper.selectPicturesByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Wechat.CHATE_ITME);
        view.addObject("pictureLibraries", pictures);
        view.addObject("items", ls);
        view.addObject("mediaId", mediaId);
        return view;
    }

    /**
     * 根据mideaId查询图文消息
    * @author 高国藩
    * @date 2015年8月31日 上午11:25:57
    * @param mediaId 图文消息
    * @return 跳转附带图文消息信息
     */
    public BaseDto getItemsByMediaId(String mediaId) {
        List<AutomaticReply> ls = automaticReplyMapper.selectByMediaId(mediaId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
    }

    /**
     * 图文消息状态信息
    * @author 高国藩
    * @date 2015年9月7日 下午4:47:43
    * @param storeId 门店
    * @param mediaId 媒体ID
    * @return 封装数据
     */
    public ModelAndView listItemsStatus(Integer storeId, String mediaId) {
        List<AutomaticReply> ls = automaticReplyMapper.selectByMediaId(mediaId);
//        List<AutomaticReply> ls = automaticReplyMapper.selectItemsByStoreId(storeId);
        for (int i = 0; i < ls.size(); i++) {
            ls.get(i).setCreateTime(DateUtil.getDate(ls.get(i).getCreateTime()));
        }
        List<MemberLevelDto> level = memberLevelMapper.selectByStoreId(storeId);
        List<MemberScreening> screen = memberScreeningMapper.selectByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Wechat.SEND_ITEMS);
        view.addObject("fatherMediaId", ls.get(0).getFatherMediaId());
        view.addObject("mediaId", mediaId);
        view.addObject("items", ls);
        view.addObject("level", level);
        view.addObject("screen", screen);
        return view;
    }

    /**
     * 自动回复页面数据加载
    * @author 高国藩
    * @date 2015年9月10日 上午10:23:27
    * @param storeId 根据门店信息查询已经设置的回复信息
    * @param response 返回数据
    * @return 跳转页面
     * @throws IOException 
     */
    public ModelAndView viewAutoReply(Integer storeId, HttpServletResponse response) throws IOException {
        //查询已经选择的回复信息
        Map<String, Integer> map= new HashMap<String, Integer>();
        map.put("storeId", storeId);
        map.put("msgStatus", 1);
        MsgReply autoReply = msgReplyMapper.selectReplyByParam(map);
        if (autoReply != null && autoReply.getMsgType().equals("news")){
            List<AutomaticReply> item = automaticReplyMapper.selectByMediaId(autoReply.getMediaId());
            autoReply.setAutomaticReply(item.get(0));
        }
        map.put("msgStatus", 2);
        MsgReply textReply = msgReplyMapper.selectReplyByParam(map);
        if (textReply != null && textReply.getMsgType().equals("news")){
            List<AutomaticReply> item = automaticReplyMapper.selectByMediaId(textReply.getMediaId());
            textReply.setAutomaticReply(item.get(0));
        }
        //图文消息封装
        List<AutomaticReply> ls = automaticReplyMapper.selectItemsByStoreId(storeId);
        for (int i = 0; i < ls.size(); i++) {
            ls.get(i).setCreateTime(DateUtil.getDate(ls.get(i).getCreateTime()));
        }
        ModelAndView view = new ModelAndView(View.Wechat.VIEW_AUTO_REPLY);
        view.addObject("automaticReplys", ls);
        view.addObject("autoReply", autoReply);
        view.addObject("textReply", textReply);
        return view;
    }

    /**
     * 设置关注回复信息
    * @author 高国藩
    * @date 2015年9月10日 上午11:38:10
    * @param autoType 类型 text item
    * @param message 回复内容
    * @param storeId 门店信息
    * @return 状态
     */
    public BaseDto actionFollowAutoReply(String autoType, String message, Integer storeId) {
        //回复图文消息
        if (autoType.equals("item")){
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("storeId", storeId);
            map.put("msgStatus", 1);
            MsgReply reply = msgReplyMapper.selectReplyByParam(map);
            //如果不存在,就新增一个
            if (reply == null){
                MsgReply msgReply = new MsgReply();
                msgReply.setMediaId(message);
                msgReply.setMsgText("");
                msgReply.setMsgType("news");
                msgReply.setMsgStatus(1);
                msgReply.setStoreId(storeId);
                msgReplyMapper.insert(msgReply);
            }
            else {
                reply.setMediaId(message);
                reply.setMsgType("news");
                reply.setMsgStatus(1);
                msgReplyMapper.updateByPrimaryKey(reply);
            }
        }
        //回复文本消息
        else {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("storeId", storeId);
            map.put("msgStatus", 1);
            MsgReply reply = msgReplyMapper.selectReplyByParam(map);
            //如果不存在,就新增一个
            if (reply == null){
                MsgReply msgReply = new MsgReply();
                msgReply.setMediaId("");
                msgReply.setMsgText(message);
                msgReply.setMsgType("text");
                msgReply.setStoreId(storeId);
                msgReply.setMsgStatus(1);
                msgReplyMapper.insert(msgReply);
            }
            else {
                reply.setMsgText(message);
                reply.setMsgType("text");
                reply.setMsgStatus(1);
                msgReplyMapper.updateByPrimaryKey(reply);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 设置消息回复信息
    * @author 高国藩
    * @date 2015年9月10日 上午11:38:10
    * @param autoType 类型 text item
    * @param message 回复内容
    * @param storeId 门店信息
    * @return 状态
     */
    public BaseDto actionTextAutoReply(String autoType, String message, Integer storeId) {
        //回复图文消息
        if (autoType.equals("item")){
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("storeId", storeId);
            map.put("msgStatus", 2);
            MsgReply reply = msgReplyMapper.selectReplyByParam(map);
            //如果不存在,就新增一个
            if (reply == null){
                MsgReply msgReply = new MsgReply();
                msgReply.setMediaId(message);
                msgReply.setMsgText("");
                msgReply.setMsgType("news");
                msgReply.setMsgStatus(2);
                msgReply.setStoreId(storeId);
                msgReplyMapper.insert(msgReply);
            }
            else {
                reply.setMediaId(message);
                reply.setMsgType("news");
                reply.setMsgStatus(2);
                msgReplyMapper.updateByPrimaryKey(reply);
            }
        }
        //回复文本消息
        else {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("storeId", storeId);
            map.put("msgStatus", 2);
            MsgReply reply = msgReplyMapper.selectReplyByParam(map);
            //如果不存在,就新增一个
            if (reply == null){
                MsgReply msgReply = new MsgReply();
                msgReply.setMediaId("");
                msgReply.setMsgText(message);
                msgReply.setMsgType("text");
                msgReply.setStoreId(storeId);
                msgReply.setMsgStatus(2);
                msgReplyMapper.insert(msgReply);
            }
            else {
                reply.setMsgText(message);
                reply.setMsgType("text");
                reply.setMsgStatus(2);
                msgReplyMapper.updateByPrimaryKey(reply);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 删除自动回复内容设置
    * @author 高国藩
    * @date 2015年9月10日 下午3:27:47
    * @param msgStatus msgStatus 删除的类型 1代表关注 2代表消息 
    * @param storeId 门店信息
    * @return 状态
     */
    public BaseDto actionDeleteReply(Integer msgStatus, Integer storeId) {
        Map<String, Integer> map= new HashMap<String, Integer>();
        map.put("storeId", storeId);
        map.put("msgStatus", msgStatus);
        int ok = msgReplyMapper.deleteByParam(map);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }


    /**
     * 查询出发送次数为0的人员名单作为提醒
    * @author 高国藩
    * @date 2015年10月7日 下午5:52:27
    * @param level 选择的会员等级
    * @param sceening 筛选器
    * @param storeId 门店信息
    * @return 返回有0的会员列表
     */
    public BaseDto checkWechatCount(String level, String sceening,
            Integer storeId) {
        String[] sceenings = sceening.split(",");
        String[] levels = level.split(",");
        List<Integer> ls = serchMemberIds(sceenings, levels);
        ls.add(0);
        List<MemberInfo> bs = memberInfoMapper.selectMemberInfoByWechatParams(ls);
        if (bs!=null&&bs.size()>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, bs);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "全部满足条件");
    }

    /**
     * 根据关键字查询图文消息
    * @author 高国藩
    * @date 2015年10月8日 上午10:31:20
    * @param content 关键字搜索
    * @param storeId 门店,如果是0,查询智放,否则查询我的图文库
    * @return 将图文消息传出
     */
    public BaseDto actionSerchItems(String content, Integer storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        map.put("content", content);
        List<AutomaticReply> items = automaticReplyMapper.selectMediaIdByContent(map);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, items);
    }
    

    /**
     * 传入分组和会员等级,即可得到所有的会员ids
    * @author 高国藩
    * @date 2015年12月11日 下午2:48:57
    * @param groups    分组
    * @param level     会员等级
    * @return          会员ids
     */
    public List<Integer> serchMemberIds(String[] groups, String[] level){
        Set<Integer> sendsIds = new HashSet<>();
        List<ScreeningDto> dtos = memberScreeningMapper.selectByDtos(Arrays.asList(groups));
        for (int i = 0; i < dtos.size(); i++) {
            List<Integer> memberIds = memberInfoMapper.selectMemberIdsByDtos2(MessageUtil.transBean2Map(dtos.get(i)));
            sendsIds.addAll(memberIds);
        }
        List<String> levels = Arrays.asList(level);
        List<Integer> memberIds = memberInfoMapper.selectMemberIdsByLevelIds(levels);
        sendsIds.addAll(memberIds);
        //查询出发送的会员名单
        List<Integer> ls = new ArrayList<>();
        ls.addAll(sendsIds);
        return ls;
    }

}
