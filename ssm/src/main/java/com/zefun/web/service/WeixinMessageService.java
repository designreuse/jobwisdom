package com.zefun.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.MessageUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.WechatMenuDto;
import com.zefun.web.entity.AutomaticKey;
import com.zefun.web.entity.AutomaticReply;
import com.zefun.web.entity.Menu;
import com.zefun.web.entity.MsgReply;
import com.zefun.web.mapper.AutomaticKeyMapper;
import com.zefun.web.mapper.AutomaticReplyMapper;
import com.zefun.web.mapper.MenuMapper;
import com.zefun.web.mapper.MsgReplyMapper;
import com.zefun.web.wechat.dto.ArticleDto;
import com.zefun.web.wechat.dto.ButtonDto;
import com.zefun.web.wechat.dto.CommonButtonDto;
import com.zefun.web.wechat.dto.ComplexButtonDto;
import com.zefun.web.wechat.dto.MenuDto;
import com.zefun.web.wechat.dto.NewsMessageDto;
import com.zefun.web.wechat.dto.TextMessageDto;
import com.zefun.web.wechat.dto.ViewButtonDto;

/**
 * 微信端消息service
* @author 高国藩
* @date 2015年8月11日 上午11:47:56
 */
@Service
public class WeixinMessageService {
    /**
     * 
     */
	@Autowired
	private WeixinUploadService weixinUploadService;
    /**
     * 菜单自动回复信息
     */
	@Autowired
	private AutomaticReplyMapper automaticReplyMapper;
    /**
     * 菜单点击匹配想
     */
	@Autowired
	private AutomaticKeyMapper automaticKeyMapper;
    /**
     * 菜单处理
     */
	@Autowired
	private MenuMapper menuMapper;
    /**
     * 自动回复信息
     */
	@Autowired
	private MsgReplyMapper msgReplyMapper;
	/**
	 * 日志
	 */
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
			logger.info("请求信息:"+respMessage);
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			/**消息类型 */
			String msgType = requestMap.get("MsgType");
            /**事件推送*/
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");
				/**订阅*/
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					/**注意********这里通过发送者的openId,获取出他是那个店的会员,在通过门店信息查询出回复信息 -- msgReply*/
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("storeId", 1);
					map.put("msgStatus", 1);
					MsgReply msgReply =  msgReplyMapper.selectReplyByParam(map);
					/**判断回复类型进行回复*/
					if (msgReply!=null&&msgReply.getMsgType().equals("text")) {
						return replyTextMessage(msgReply.getMsgText(), fromUserName, toUserName);
					} 
					else if (msgReply!=null&&msgReply.getMsgType().equals("news")) {
						/**回复图文消息*/
						return replyNewsMessage(msgReply.getMediaId(), fromUserName, toUserName);
					}
				}
				/**自定义菜单点击事件*/
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					/**事件KEY值，与创建自定义菜单时指定的KEY值对应*/
					String eventKey = requestMap.get("EventKey");
					/**此处用key去查找回复类型,如果是1说明文字回复,如果是2,查找图文消息素材*/
					AutomaticKey automaticKey = automaticKeyMapper.selectRespByKey(eventKey);
					if (automaticKey.getAutomaticType()==1){
						/**回复文本消息*/
						String respContent = automaticKey.getAutomaticText();
						return replyTextMessage(respContent, fromUserName, toUserName);
					}
					else {
						/**回复图文消息*/
						return replyNewsMessage(automaticKey.getMediaId(), fromUserName, toUserName);
					}
				}
			}
			/**消息回复信息*/
			else if (msgType.equals("text")||msgType.equals("voice")||msgType.equals("image")){
				/**注意********这里通过发送者的openId,获取出他是那个店的会员,在通过门店信息查询出回复信息 -- msgReply*/
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("storeId", 1);
				map.put("msgStatus", 2);
				MsgReply msgReply =  msgReplyMapper.selectReplyByParam(map);
				/**判断回复类型进行回复*/
				if (msgReply!=null&&msgReply.getMsgType().equals("text")){
					return replyTextMessage(msgReply.getMsgText(), fromUserName, toUserName);
				}
				else if (msgReply!=null&&msgReply.getMsgType().equals("news")){
					/**回复图文消息*/
					return replyNewsMessage(msgReply.getMediaId(), fromUserName, toUserName);
				}
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
	 * 新增菜单-本地库存
	* @author 高国藩
	* @date 2015年8月11日 上午11:34:35
	* @param menu 菜单
	* @return 状态信息
	 */
	public BaseDto addMenu(Menu menu) {
		menu.setStoreId(1);
		int ok = menuMapper.insert(menu);
		if (ok>0){
		    return new BaseDto(0, menu);
		}
		else {
		    return new BaseDto(0, "失败");
		}
	}

	/**
	 * 获得全部菜单-准备上传至微信,也可用于菜单信息的展示
	* @author 高国藩
	* @date 2015年8月11日 上午11:37:15
	* @param storeId 门店ID
	* @return 返回菜单信息
	 */
	public List<WechatMenuDto> getAllMenu(Integer storeId) {
		return menuMapper.selectAllMenu(storeId);
	}
	
	/**
	 * 1.微信上传菜单-调用微信接口将菜单信息上传
	* @author 高国藩
	* @date 2015年8月6日 下午3:59:54
	* @param menu 菜单
	* @param ACCESS_TOKEN 微信认证
	* @return 状态信息
	 */
	public BaseDto updateCreateMenu(MenuDto menu, String ACCESS_TOKEN) {
		String url = Url.Wechat.CREATE_MENU.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		String jsonMenu = JSONObject.fromObject(menu).toString();
		JSONObject jsonObject = WeixinUploadService.httpRequest(url, "POST", jsonMenu);
		int ok = jsonObject.getInt("errcode");
		if (ok==0){
		    return new BaseDto(0, "成功");
		}
		else {
		    return new BaseDto(-1, "失败");
		}
	}
	
	/**
	 * 2.微信上传菜单-将从库中的菜单信息上传至微信
	* @author 高国藩
	* @date 2015年8月11日 上午11:38:50
	* @param storeId 門店信息
	* @param ACCESS_TOKEN 微信認證
	* @return 状态信息
	 */
	public BaseDto createMenu(Integer storeId, String ACCESS_TOKEN) {
		MenuDto menuDto = new MenuDto();
		List<ButtonDto> buttonDtos = new ArrayList<ButtonDto>();
		/**从数据库中获取菜单信息*/
		List<WechatMenuDto> ls = this.getAllMenu(storeId);
		for (int i = 0; i < ls.size(); i++) {
		    WechatMenuDto menu = ls.get(i);
			List<WechatMenuDto> menus = menu.getMenus();
			/**如果一级菜单没有二级菜单*/
			if (menus==null||menus.size()<=0){
				if (menu.getMenuType()==null||menu.getMenuType().equals("click")){
					/**保存一个点击按钮*/
					CommonButtonDto commonButtonDto = new CommonButtonDto();
					commonButtonDto.setName(menu.getMenuName());
					commonButtonDto.setType("click");
					commonButtonDto.setKey(menu.getMenuId().toString());
					buttonDtos.add(commonButtonDto);
				}
				else {
					/**保存一个链接按钮*/
					ViewButtonDto viewButtonDto = new ViewButtonDto();
					viewButtonDto.setName(menu.getMenuName());
					viewButtonDto.setType("view");
					viewButtonDto.setUrl(menu.getMenuUrl());
					buttonDtos.add(viewButtonDto);
				}
			}
			else {
				/**一级菜单有二级菜单,新增一个复杂按钮,里面包裹普通按钮*/
				ComplexButtonDto complexButtonDto = new ComplexButtonDto();
				complexButtonDto.setName(menu.getMenuName());
				List<ButtonDto> button2 = new ArrayList<ButtonDto>();
				for (int j = 0; j < menus.size(); j++) {
				    WechatMenuDto menu2 = menus.get(j);
					if (menu2.getMenuType()==null||menu2.getMenuType().equals("click")){
						CommonButtonDto commonButtonDto = new CommonButtonDto();
						commonButtonDto.setName(menu2.getMenuName());
						commonButtonDto.setType("click");
						commonButtonDto.setKey(menu2.getMenuId().toString());
						button2.add(commonButtonDto);
					}
					else {
						ViewButtonDto viewButtonDto = new ViewButtonDto();
						viewButtonDto.setName(menu2.getMenuName());
						viewButtonDto.setType("view");
						viewButtonDto.setUrl(menu2.getMenuUrl());
						button2.add(viewButtonDto);
					}
				}
				complexButtonDto.setSub_button(button2);
				buttonDtos.add(complexButtonDto);
			}
		}
		menuDto.setButton(buttonDtos);
		logger.info("菜单信息:"+JSONObject.fromObject(menuDto).toString());
		this.updateCreateMenu(menuDto, App.Wechat.ACCESS_TOKEN);
		return null;
	}

	/**
	 * 菜单设置页面 
	* @author 高国藩
	* @date 2015年8月11日 上午11:40:21
	* @param request 请求封装
	* @return 返回页面
	 */
	public ModelAndView sendViewPage(HttpServletRequest request) {
		Integer storeId = 1;
		/**菜单信息*/
		List<WechatMenuDto> menus = this.getAllMenu(storeId);
		/**图文消息链接信息*/
		List<AutomaticReply> automaticReplies = automaticReplyMapper.selectByStoreId(storeId);
		for (int i = 0; i < automaticReplies.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			String createTime = sdf.format(new Date(Long.parseLong(automaticReplies.get(i).getCreateTime())));
			automaticReplies.get(i).setCreateTime(createTime);
		}
		ModelAndView view = new ModelAndView(View.Wechat.MENU);
		view.addObject("menus", menus);
		view.addObject("automaticReplies", automaticReplies);
		return view;
	}

	/**
	 * 删除菜单
	* @author 高国藩
	* @date 2015年8月11日 上午11:40:43
	* @param menuId 菜单ID
	* @param storeId 门店ID
	* @return 状态信息
	 */
	public BaseDto deleteMenuAction(Integer menuId, Integer storeId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("storeId", storeId);
		map.put("menuId", menuId);
		int ok = menuMapper.deleteByParam(map);
		if (ok>0){
		    return new BaseDto(0,"成功");
		}
		else {
		    return new BaseDto(-1,"失败");
		}
	}

	/**
	 * 修改菜单链接
	* @author 高国藩
	* @date 2015年8月11日 上午11:41:42
	* @param menu 菜单
	* @return 状态信息
	 */
	public BaseDto setMenuUrl(Menu menu) {
		menu.setMenuType("view");
		int ok = menuMapper.updateByPrimaryKeySelective(menu);
		if (ok>0){
		    return new BaseDto(0, "成功");
		}
		else {
		    return new BaseDto(-1, "失败");
		}
	}

	/**
	 * 获取单一菜单元素
	* @author 高国藩
	* @date 2015年8月11日 上午11:42:39
	* @param menuId 菜单ID
	* @return 返回菜单
	 */
	public Menu getMenu(Integer menuId) {
		return menuMapper.selectByPrimaryKey(menuId);
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
	
}
