package com.zefun.common.utils;

import java.security.MessageDigest;

/**
 * MD5三元加密
* @author 高国藩
* @date 2016年5月10日 下午6:19:15
 */
public class MD5Util {
    
    /**密文字符*/
    private static final String[] HEXDIGHTIS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * b md5加密
    * @author 高国藩
    * @date 2016年5月10日 下午6:19:27
    * @param b b
    * @return  str
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * byte 加密
    * @author 高国藩
    * @date 2016年5月10日 下午6:20:07
    * @param b b
    * @return  str
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEXDIGHTIS[d1] + HEXDIGHTIS[d2];
    }

    /**
     * 使用编码进行加密
    * @author 高国藩
    * @date 2016年5月10日 下午6:20:54
    * @param origin       origin
    * @param charsetname  "UTF-8"
    * @return             密文
     */
    public static String md5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(
                        md.digest(resultString.getBytes()));
            } 
            else {
                resultString = byteArrayToHexString(
                        md.digest(resultString.getBytes(charsetname)));
            }
        } 
        catch (Exception exception) {
        }
        return resultString;
    }

}
