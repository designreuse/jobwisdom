package com.zefun.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * xml操作工具类
* @author 张进军
* @date Mar 11, 2016 3:31:26 PM
 */
public class XmlUtil {

    
    /**
     * 根据xml字符串获取map对象
    * @author 张进军
    * @date Mar 11, 2016 3:48:00 PM
    * @param xmlString  xml字符串
    * @return   map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getMapFromXML(String xmlString) {
        try {
            Document doc = DocumentHelper.parseText(xmlString); // 将字符串转为XML
            Element root = doc.getRootElement();
            Map<String, String> map = new HashMap<String, String>();
            for (Iterator<Element> iterator = root.elementIterator(); iterator.hasNext();) {
                Element ele = iterator.next();
                map.put(ele.getName(), ele.getTextTrim());
            }
            return map;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 根据map获取xml字符串
    * @author 张进军
    * @date Mar 16, 2016 9:50:13 PM
    * @param map    map对象
    * @return   xml字符串
     */
    public static String getXmlFromMap(Map<String, String> map) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (String key : map.keySet()) {
            xml.append("<" + key + "><![CDATA[");
            xml.append(map.get(key));
            xml.append("]]></" + key + ">");
        }
        xml.append("</xml>");
        return xml.toString();
    }
}
