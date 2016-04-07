package com.zefun.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zefun.common.consts.App;

/**
 * 友宝签名工具类
* @author 张进军
* @date Jan 21, 2016 2:15:16 PM
 */
public class UboxSignUtil {
    
    
    /**
     * 签名
    * @author 张进军
    * @date Jan 21, 2016 2:14:23 PM
    * @param params 请求参数
    * @return   签名
     */
    public static final String sign(Map<String, String> params){
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            
            if (StringUtils.isBlank(value) || key.equalsIgnoreCase("sign")) {
                continue;
            }

            prestr = prestr + key + "=" + value;
        }
        prestr += "_" + App.Ubox.APP_SECRET;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(prestr.getBytes());
            String sign = byteToStr(digest).toLowerCase();
            params.put("sign", sign);
            return sign;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 将字节数组转换为十六进制字符串
     * @param byteArray 数组
     * @return String
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    

    /**
     * 将字节转换为十六进制字符串
     * @param mByte b
     * @return String
     */
    private static String byteToHexStr(byte mByte) {
        char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
