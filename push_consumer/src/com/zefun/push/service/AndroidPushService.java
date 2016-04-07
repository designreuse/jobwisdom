package com.zefun.push.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.zefun.push.utils.App;

/**
* @author 张进军
* @date Sep 20, 2015 9:08:53 AM 
*/
@Service
public class AndroidPushService {
    private Logger logger = Logger.getLogger(AndroidPushService.class);
    
    private IIGtPush push = new IGtPush(App.GeTui.API_HOST, App.GeTui.APP_KEY, App.GeTui.MASTER_SECRET);
    
    public static void main(String[] args) {
        String zefunCid = "05266048254d92212f421a818d18d7cb";
        new AndroidPushService().pushSingle(zefunCid, "推送标题", "推送内容", "{type:1,orderid:201509210001,msg:'您预约的洗剪吹已被刘德华接收，届时请准时赴约哦～'}");
    }
    
    /**
     * 给指定用户发送推送
    * @author 张进军
    * @date Sep 20, 2015 5:00:56 PM
    * @param clientId               客户推送标识
    * @param title                  推送标题
    * @param content                推送内容
    * @param transmissionContent    透传内容
    * @return
     */
    public boolean pushSingle(String clientId, String title, String content, String transmissionContent){
        NotificationTemplate template = notificationTemplate(title, content, transmissionContent);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(App.GeTui.OFF_LINE_EXPIRE_TIME);
        message.setData(template);
        message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        
        Target target = new Target();
        target.setAppId(App.GeTui.APP_ID);
        target.setClientId(clientId);
        
        IPushResult ret = null;
        try{
            ret = push.pushMessageToSingle(message, target);
        } catch(RequestException e){
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        return checkResult(ret.getResponse().toString());
    }
    
    
    /**
     * 给所有用户推送消息
    * @author 张进军
    * @date Sep 20, 2015 4:38:21 PM
    * @return
     */
    public boolean pushAll(){
        //透传模板
        TransmissionTemplate template = transmissionTemplate();
        AppMessage message = new AppMessage();
        message.setData(template);
        //设置消息离线，并设置离线时间
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(App.GeTui.OFF_LINE_EXPIRE_TIME);
        //设置推送目标条件过滤
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(App.GeTui.APP_ID);
        
        List<String> tagList = new ArrayList<String>();
        //设置标签内容
        tagList.add("开心");
        message.setAppIdList(appIdList);
        message.setTagList(tagList);
        IPushResult ret = push.pushMessageToApp(message);
        return checkResult(ret.getResponse().toString());
    }
    
    
    /**
     * 检查推送结果
    * @author 张进军
    * @date Sep 20, 2015 4:09:05 PM
    * @param str
    * @return
     */
    private boolean checkResult(String str){
        logger.info("push result : " + str);
        if(str.contains("result=ok")){
            return true;
        }
        return false;
    }
    
    
    /**
     * 点击通知打开应用模板
    * @author 张进军
    * @date Sep 20, 2015 3:53:47 PM
    * @return
     */
    public NotificationTemplate notificationTemplate(String title, String content, String transmissionContent) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(App.GeTui.APP_ID);
        template.setAppkey(App.GeTui.APP_KEY);
        // 设置通知栏标题与内容
        template.setTitle(title);
        template.setText(content);
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(transmissionContent);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
    
    
    /**
     * 点击通知打开网页模板
    * @author 张进军
    * @date Sep 20, 2015 3:54:29 PM
    * @return
     */
    public LinkTemplate linkTemplate() {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(App.GeTui.APP_ID);
        template.setAppkey(App.GeTui.APP_KEY);
        // 设置通知栏标题与内容
        template.setTitle("请输入通知栏标题");
        template.setText("请输入通知栏内容");
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 设置打开的网址地址
        template.setUrl("www.baidu.com");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
    
    
    /**
     * 点击通知弹窗下载模板
    * @author 张进军
    * @date Sep 20, 2015 3:55:00 PM
    * @return
     */
    public NotyPopLoadTemplate notyPopLoadTemplate() {
        NotyPopLoadTemplate template = new NotyPopLoadTemplate();
        // 设置APPID与APPKEY
        template.setAppId(App.GeTui.APP_ID);
        template.setAppkey(App.GeTui.APP_KEY);
        // 设置通知栏标题与内容
        template.setNotyTitle("请输入通知栏标题");
        template.setNotyContent("请输入通知栏内容");
        // 配置通知栏图标
        template.setNotyIcon("icon.png");
        // 设置通知是否响铃，震动，或者可清除
        template.setBelled(true);
        template.setVibrationed(true);
        template.setCleared(true);
       
        // 设置弹框标题与内容
        template.setPopTitle("弹框标题");
        template.setPopContent("弹框内容");
        // 设置弹框显示的图片
        template.setPopImage("");
        template.setPopButton1("下载");
        template.setPopButton2("取消");
       
        // 设置下载标题
        template.setLoadTitle("下载标题");
        template.setLoadIcon("file://icon.png");
        //设置下载地址        
        template.setLoadUrl("");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
    
    
    /**
     * 透传消息模板
    * @author 张进军
    * @date Sep 20, 2015 3:55:54 PM
    * @return
     */
    public TransmissionTemplate transmissionTemplate() {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(App.GeTui.APP_ID);
        template.setAppkey(App.GeTui.APP_KEY);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("请输入需要透传的内容");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
    
}
