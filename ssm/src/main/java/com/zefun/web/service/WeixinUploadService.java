package com.zefun.web.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.MyX509TrustManagerUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AutomaticReply;
import com.zefun.web.mapper.AutomaticReplyMapper;
import com.zefun.web.wechat.dto.ArticlesDto;
import com.zefun.web.wechat.dto.ItemDto;
import com.zefun.web.wechat.dto.ItmesDto;
import com.zefun.web.wechat.dto.MediaIdDto;
import com.zefun.web.wechat.dto.NewsItemsDto;
import com.zefun.web.wechat.dto.SendMessagesDto;
import com.zefun.web.wechat.dto.ThumbDto;

/**
 * 微信交互信息serivce
* @author 高国藩
* @date 2015年8月11日 上午11:48:24
 */
@Service
public class WeixinUploadService {
    /**
     * 微信认证
     */
	private static String accessToken = App.Wechat.ACCESS_TOKEN;
	/**
	 * 消息服务类
	 */
	@Autowired
	private WeixinMessageService weixinMessageService;
	/**
     * 菜单回复信息
     */
	@Autowired
	private AutomaticReplyMapper automaticReplyMapper;
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(WeixinUploadService.class);

	/**
	 * 根据openId进行发送图文消息
	* @author 高国藩
	* @date 2015年8月10日 下午11:47:39
	* @param touser 接受者
	* @param msgtype 发送消息类型
	* @param mpnews 图文
	* @return 接受信息
	 */
	public static String sendMessageByOpenId(List<String> touser,
			  String msgtype, MediaIdDto mpnews) {
		String url = Url.Wechat.SENDMESSAGEBYID;
		url = url.replace("ACCESS_TOKEN", accessToken);
		SendMessagesDto message = new SendMessagesDto(touser, msgtype, mpnews);
		JSONObject json = httpRequest(url, "POST",
				  JSONObject.fromObject(message).toString());
		return json.getString("errmsg");
	}

	/**
	 * 上传图文素材,获得永久mediaId
	* @author 高国藩
	* @date 2015年8月10日 下午11:47:33
	* @param article 图文消息
	* @param ACCESS_TOKEN 微信认证
	* @return 返回medaiId
	 */
	public static String getMediaId(ArticlesDto article, String ACCESS_TOKEN) {
		String url = Url.Wechat.GETMEDIAID;
		url = url.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		logger.info(JSONObject.fromObject(article).toString());
		JSONObject json = httpRequest(url, "POST",
			  	JSONObject.fromObject(article).toString());
		logger.info(json.toString());
		return json.getString("media_id");
	}

	/**
	 * 上传图片根据url的不同,分别可获得ThumbMediaId或PicUrl
	* @author 高国藩
	* @date 2015年8月10日 下午11:47:25
	* @param url 七牛图片地址
	* @param ACCESS_TOKEN 微信认证
	* @param imgUrl 上传至微信的图片地址
	* @return 返回微信图片地址
	 */
	public static String getThumbMediaUrl(String url, String ACCESS_TOKEN,
		   	String imgUrl) {
		InputStream in = sendGETRequest(imgUrl);
		Map<String, InputStream> fileMap = new HashMap<String, InputStream>();
		fileMap.put("media", in);
		String result = formUpload(url, null, fileMap);
		logger.info(result);
		return JSONObject.fromObject(result).getString("url").toString();

	}

	/**
	 * 获得微信上传的永久素材ThumbMediaId
	* @author 高国藩
	* @date 2015年8月11日 上午11:55:01
	* @param url 微信上传永久素材url
	* @param ACCESS_TOKEN 微信认证
	* @param imgUrl 七牛图片地址
	* @return 返回id
	 */
	public static String getThumbMediaId(String url, String ACCESS_TOKEN,
		  	String imgUrl) {
		InputStream in = sendGETRequest(imgUrl);
		Map<String, InputStream> fileMap = new HashMap<String, InputStream>();
		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("type", "thumb");
		fileMap.put("media", in);
		String result = formUpload(url, null, fileMap);
		return JSONObject.fromObject(result).getString("media_id").toString();

	}

	/**
	 * 获取素材列表 ,offset 从第几个开始 一共取出count个数据
	* @author 高国藩
	* @date 2015年8月10日 下午11:47:17
	* @param offset 偏移量
	* @param count 个数
	* @return 返回图文消息的json数据
	 */
	public static String getNews(String offset, String count) {
		/** 从数据库中查询 media_id 和 标题绑定的信息,根据media_id来查询图文消息的内容 */
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", accessToken);
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "news");
		map.put("offset", offset);
		map.put("count", count);
		JSONObject json = httpRequest(url, "POST", JSONObject.fromObject(map)
			  	.toString());
		ItemDto itme = (ItemDto) JSONObject.toBean(json, ItemDto.class);
		logger.info(itme.toString());
		return null;
	}

	/**
	 * 根据media_id，获得图文素材的信息
	* @author 高国藩
	* @date 2015年8月10日 下午11:47:08
	* @param media_id 图文id
	* @return 返回图文消息类
	 */
	public ItmesDto getNewsByMediaId(String media_id) {
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", accessToken);
		Map<String, String> map = new HashMap<String, String>();
		map.put("media_id", media_id);
		JSONObject json = httpRequest(url, "POST", JSONObject.fromObject(map)
			  	.toString());
		ItmesDto itmes = (ItmesDto) JSONObject.toBean(json, ItmesDto.class);
		return itmes;
	}

	/**
	 * 正式上传图文消息
	* @author 高国藩
	* @date 2015年8月10日 下午11:47:50
	* @param ACCESS_TOKEN 微信认证
	* @param thumb 图文接受form表单请求
	* @param storeId 门店id
	* @return 返回状态
	 */
	public BaseDto uploadNews(String ACCESS_TOKEN, ThumbDto thumb, Integer storeId) {
		/** 更新地址,换区ACCESS_TOKEN */
		String getPicUrl = Url.Wechat.GETPICURL;
		String getThumbMediaIdUrl = Url.Wechat.GETTHUMBID;
		getPicUrl = getPicUrl.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		getThumbMediaIdUrl = getThumbMediaIdUrl.replace("ACCESS_TOKEN",
				ACCESS_TOKEN);
		/** 映射字段，form表单提交 */
		String[] imgUrl = thumb.getImgUrl().split(",");
		String[] author = thumb.getAuthor().split(",");
		String[] title = thumb.getTitle().split(",");
		String[] content = thumb.getContent().split(",");
		String[] digest = thumb.getDigest().split(",");
		String[] description = thumb.getDescription().split(",");
		/** 保存上传的微信永久图片*/
		List<String> autoImgUrl = new ArrayList<String>();
       /** 生成微信图文消息对应字段 */
		ArticlesDto article = new ArticlesDto();
		List<ThumbDto> articles = new ArrayList<ThumbDto>();
		for (int i = 0; i < title.length; i++) {
			/** 微信上传的imgUrl-永久 */
			String picUrl = WeixinUploadService.getThumbMediaUrl(getPicUrl,
			  		ACCESS_TOKEN, imgUrl[i]);
			/** 微信上传的thumb_medai_id-永久 */
			String thumbMediaId = WeixinUploadService.getThumbMediaId(
			  		getThumbMediaIdUrl, ACCESS_TOKEN, picUrl);
			ThumbDto thumbDto = new ThumbDto(thumbMediaId, author[i],
			  		title[i], null, content[i], digest[i], "0");
			articles.add(thumbDto);
			autoImgUrl.add(picUrl);
		}
		article.setArticles(articles);
		/** 生成素材mediaId */
		String mediaId = WeixinUploadService.getMediaId(article, ACCESS_TOKEN);
		logger.info("图文消息素材上传ID:"+mediaId);
		/** 得到MedaiId后,说明上传成功,根据这个MedaiId获得图文消息,然后拉取下来,将其封装成自动回复图文消息的素材保存下来即可 */
		int ok = 0;
		if (mediaId != null && !mediaId.equals("")) {
			ItmesDto itmesDto = this.getNewsByMediaId(mediaId);
			List<NewsItemsDto> newsItem = itmesDto.getNews_item();
			List<AutomaticReply> automaticReplies = new ArrayList<AutomaticReply>();
			for (int i = 0; i < newsItem.size(); i++) {
				/**保存图文素材,用于自动回复图文消息,也可用于选择链接地址*/
				NewsItemsDto newsItemsDto = (NewsItemsDto) JSONObject.toBean(JSONObject.fromObject(newsItem.get(i)), NewsItemsDto.class);
				AutomaticReply automaticReply = new AutomaticReply(mediaId, newsItemsDto.getTitle(),
				        newsItemsDto.getUrl(), description[i], autoImgUrl.get(i), String.valueOf(new Date().getTime()), storeId);
				automaticReplies.add(automaticReply);
			}
			/** 将得到的图文消息保存入数据库中 */
			ok = automaticReplyMapper.insertByList(automaticReplies);
		}
		if (ok>0&&mediaId!=null&&!mediaId.equals("")){
			return new BaseDto(0, "图文素材新增完成");
		}
		else {
			return new BaseDto(0, "图文素材新增失败");
		}
	}

	/**
	 * HTTPS 请求方式
	* @author 高国藩
	* @date 2015年8月10日 下午11:47:59
	* @param requestUrl 请求链接
	* @param requestMethod 请求方式
	* @param outputStr 请求数据
	* @return 返回json
	 */
	public static JSONObject httpRequest(String requestUrl,
		  	String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			TrustManager[] tm = { new MyX509TrustManagerUtil() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
			  		.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)){
			    httpUrlConn.connect();
			}
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
		  			inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
		  			inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} 
		catch (Exception ce) {
			ce.printStackTrace();
		}
		logger.info(jsonObject.toString());
		return jsonObject;
	}

	/**
	 * 上传文件
	* @author 高国藩
	* @date 2015年8月11日 下午12:54:20
	* @param urlStr 请求链接
	* @param textMap 请求文本数据
	* @param fileMap 请求文件数据
	* @return 返回String
	 */
	@SuppressWarnings("unused")
	public static String formUpload(String urlStr, Map<String, String> textMap,
	  		Map<String, InputStream> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String boundary = "------WebKitFormBoundaryZVwkbA47qbv8sxAs";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
			  		"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
			  		"multipart/form-data; boundary=" + boundary);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				@SuppressWarnings("rawtypes")
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					@SuppressWarnings("rawtypes")
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(boundary)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
				  			+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				logger.info(strBuf.toString());
				out.write(strBuf.toString().getBytes());
			}
			if (fileMap != null) {
				@SuppressWarnings("rawtypes")
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					@SuppressWarnings("rawtypes")
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					InputStream inputValue = (InputStream) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(boundary)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
					  		+ "media" + "\"; filename=\"" + new Date() + ".jpg"
						  	+ "\"\r\n");
					strBuf.append("Content-Type:" + "image/png" + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(inputValue);
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
				  	conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	/**
	 * 请求链接转换位流
	* @author 高国藩
	* @date 2015年8月11日 下午12:56:33
	* @param path 请求链接
	* @return 返回流
	 */
	public static InputStream sendGETRequest(String path) {
		try {
			StringBuilder url = new StringBuilder(path);
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
				  	.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				return conn.getInputStream();
			}
		} 
		catch (Exception e) {
		}
		return null;
	}

}
