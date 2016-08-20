package com.zefun.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.ChatDataDto;
import com.zefun.web.dto.ChatNoticeDto;

import net.sf.json.JSONObject; 


/**
 * 开卡充值Test
* @author 王大爷
* @date 2015年8月11日 下午3:52:35
 */
@Component
public class RabbitServerTest extends BaseTest{
    /**日志*/
    public static Logger log = Logger.getLogger(RabbitServerTest.class);
    /**
     * 水电费
     */
    /** rabbit队列模版方法 */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 声音推送
    * @author 高国藩
    * @date 2016年8月20日 上午11:35:18
     */
    @Test
    public void send(){
        ChatNoticeDto chat = new ChatNoticeDto();
        chat.setFid(2);
        chat.setFromUser("0");
        ChatDataDto data = new ChatDataDto();
        data.setType(2);
        data.setMsg("1120" + "号，您有新预约，请与顾客确认");
        data.setCreateTime(DateUtil.getCurTime());
        chat.setData(data);
        chat.setToUser("2085");
        rabbitTemplate.convertAndSend(App.Queue.CHAT_NOTIFY, JSONObject.fromObject(chat).toString());
    }
    
    
}