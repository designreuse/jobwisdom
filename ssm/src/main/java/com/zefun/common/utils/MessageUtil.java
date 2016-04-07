package com.zefun.common.utils;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zefun.web.wechat.dto.ArticleDto;
import com.zefun.web.wechat.dto.NewsMessageDto;
import com.zefun.web.wechat.dto.TextMessageDto;

/**
 * 消息工具类
* @author 高国藩
* @date 2015年8月6日 上午9:38:53 
*
 */
public class MessageUtil {

    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 解析微信发来的请求（XML）
     * @param request request
     * @return Map<String, String>
     * @throws Exception 异常
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 文本消息对象转换成xml
     * alias 起别名,如果没有,根节点将面为com.pacage.textMessage的形式
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String textMessageToXml(TextMessageDto textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图文消息对象转换成xml
     * 
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(NewsMessageDto newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new ArticleDto().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     * 此处做了CreateTime和ArticleCount字段做特殊处理
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                /** 对所有xml节点的转换都增加CDATA标记*/
                boolean cdata = true;
                String createTime = "";
                String articleCount = "ArticleCount";

                public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                    if (name != null && name.equals("CreateTime")) {
                        createTime = "CreateTime";
                    }
                    else {
                        createTime = name;
                    }
                    if (name != null && name.equals("ArticleCount")) {
                        articleCount = "ArticleCount";
                    }
                    else {
                        articleCount = name;
                    }
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata && !createTime.equals("CreateTime") && !articleCount.equals("ArticleCount")) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                    else {
                        writer.write(text);
                    }
                }
            };
        }
    });

}
