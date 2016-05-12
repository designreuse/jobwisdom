package com.zefun.common.utils;

import java.io.File;   
import java.io.FileInputStream;   
import java.io.IOException;   
import java.nio.MappedByteBuffer;   
import java.nio.channels.FileChannel;   
import java.security.MessageDigest;   
import java.security.NoSuchAlgorithmException;   

/**
 * md5工具列
* @author 高国藩
* @date 2016年5月10日 下午6:10:44
 */
public class MD5QrUtils {

    /***/
    protected static char[] hexDigits = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /***/
    protected static MessageDigest messagedigest = null;

    /** 
     * MessageDigest初始化 
     *  
     * @author 高焕杰 
     */
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对文件进行MD5加密 
    * @author 高国藩
    * @date 2016年5月10日 下午6:15:12
    * @param file    file
    * @return         String
    * @throws IOException IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        @SuppressWarnings("resource")
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }

    /**
     * 对str进行MD5加密 
    * @author 高国藩
    * @date 2016年5月10日 下午6:15:12
    * @param s    s
    * @return         String
    * @throws IOException IOException
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * 对byte类型的数组进行MD5加密 
    * @author 高国藩
    * @date 2016年5月10日 下午6:15:12
    * @param bytes    bytes
    * @return         String
    * @throws IOException IOException
     */
    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    /**
     * 对bytes进行MD5加密 
    * @author 高国藩
    * @date 2016年5月10日 下午6:15:12
    * @param bytes    bytes
    * @return         String
    * @throws IOException IOException
     */
    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    /**
     * 对str进行MD5加密 
    * @author 高国藩
    * @date 2016年5月10日 下午6:15:12
    * @param bytes    bytes
    * @param m    m
    * @param n    n
    * @return         String
    * @throws IOException IOException
     */
    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            char c0 = hexDigits[(bytes[l] & 0xf0) >> 4];
            char c1 = hexDigits[bytes[l] & 0xf];
            stringbuffer.append(c0);
            stringbuffer.append(c1);
        }
        return stringbuffer.toString();
    }
    
}
