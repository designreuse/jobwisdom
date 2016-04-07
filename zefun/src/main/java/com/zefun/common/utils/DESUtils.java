package com.zefun.common.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.lang.StringUtils;

/**
 * 加密解密工具类
* @author 张进军
* @date Jan 16, 2016 8:35:52 PM
 */
public final class DESUtils {
    /**加密类型*/
    private static final String DES_ALGORITHM = "DES";
    /**字符类型*/
    private static final String DEFAULT_CHAR_SET = "utf-8";
    /**密钥*/
    private static final String SEED_KEY = "C3F356AC57C5469C8A279AC2587E2708";

    /**
     * DES加密
     * @param srcStr 原始字符串
     * @param secretKey 密钥
     * @return  加密后的字符串
     * @throws Exception    加密产生的异常
     */
    public static String encryptStr(String srcStr, String secretKey) throws Exception {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(secretKey));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            byte[] buf = cipher.doFinal(srcStr.getBytes(DEFAULT_CHAR_SET));
            return Base64Utils.encode(buf);
        }
        catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception("IllegalBlockSizeException", e);
        }
        catch (BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("BadPaddingException", e);
        }
    }

    /**
     * DES加密(使用默认key)
     * @param srcStr 原始字符串
     * @return  加密后的字符串
     * @throws Exception    加密产生的异常
     */
    public static String encryptStr(Object srcStr) throws Exception {
        if (srcStr == null || StringUtils.isBlank(srcStr.toString())) {
            return null;
        }
        return encryptStr(srcStr.toString(), SEED_KEY);
    }

    /**
     * DES解密
     * @param encryptedStr 加密后的字符串
     * @param secretKey 密钥
     * @return  解密后的字符串
     * @throws Exception    解密产生的异常
     */
    public static String decryptStr(String encryptedStr, String secretKey) throws Exception {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(secretKey));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("NoSuchAlgorithmException", e);
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw new Exception("NoSuchPaddingException", e);
        }
        catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new Exception("InvalidKeyException", e);
        }

        try {
            byte[] buf = cipher.doFinal(Base64Utils.decode(encryptedStr.toCharArray()));
            return new String(buf, DEFAULT_CHAR_SET);
        }
        catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception("IllegalBlockSizeException", e);
        }
        catch (BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("BadPaddingException", e);
        }
    }

    /**
     * DES解密(使用默认key)
     * @param encryptedStr 加密后的字符串
     * @return  解密后的字符串
     * @throws Exception    解密产生的异常
     */
    public static String decryptStr(String encryptedStr) throws Exception {
        return decryptStr(encryptedStr, SEED_KEY);
    }

    /**
     * 获得密钥
     * @param secretKey 密钥
     * @return  密钥
     * @throws Exception    初始化异常
     */
    private static SecretKey generateKey(String secretKey) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        try {
            DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes(DEFAULT_CHAR_SET));
            keyFactory.generateSecret(keySpec);
            return keyFactory.generateSecret(keySpec);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Class Name: Base64Utils
     * Function Description: 字节数组与字符串互转时需要用Base64加、解密
     * Date: 2014-4-14 下午7:49:02
     * Author berry
     * Version: 1.0
     */
    static class Base64Utils {
        /**加密字符数组*/
        private static char[] mAlphabetArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-$_".toCharArray();
        /**初始化*/
        private static byte[] mCodes = new byte[256];

        static {
            for (int i = 0; i < 256; i++) {
                mCodes[i] = -1;
            }
            for (int i = 'A'; i <= 'Z'; i++) {
                mCodes[i] = (byte) (i - 'A');
            }
            for (int i = 'a'; i <= 'z'; i++) {
                mCodes[i] = (byte) (26 + i - 'a');
            }
            for (int i = '0'; i <= '9'; i++) {
                mCodes[i] = (byte) (52 + i - '0');
            }
            mCodes['-'] = 62;
            mCodes['$'] = 63;
        }

        /**
         * 将原始数据编码为base64编码
         * @param data  需编码的字节数组
         * @return base64编码
         */
        public static String encode(byte[] data) {
            char[] out = new char[((data.length + 2) / 3) * 4];
            for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
                boolean quad = false;
                boolean trip = false;
                int val = (0xFF & (int) data[i]);
                val <<= 8;
                if ((i + 1) < data.length) {
                    val |= (0xFF & (int) data[i + 1]);
                    trip = true;
                }
                val <<= 8;
                if ((i + 2) < data.length) {
                    val |= (0xFF & (int) data[i + 2]);
                    quad = true;
                }
                out[index + 3] = mAlphabetArray[(quad ? (val & 0x3F) : 64)];
                val >>= 6;
                out[index + 2] = mAlphabetArray[(trip ? (val & 0x3F) : 64)];
                val >>= 6;
                out[index + 1] = mAlphabetArray[val & 0x3F];
                val >>= 6;
                out[index + 0] = mAlphabetArray[val & 0x3F];
            }

            return new String(out);
        }

        /**
         * 将base64编码的数据解码成原始数据
         * @param data  需解码的字符数组
         * @return base64解码
         * @throws Exception    解码异常
         */
        public static byte[] decode(char[] data) throws Exception {
            int len = ((data.length + 3) / 4) * 3;
            if (data.length > 0 && data[data.length - 1] == '_') {
                --len;
            }
            if (data.length > 1 && data[data.length - 2] == '_') {
                --len;
            }
            byte[] out = new byte[len];
            int shift = 0;
            int accum = 0;
            int index = 0;
            for (int ix = 0; ix < data.length; ix++) {
                int value = mCodes[data[ix] & 0xFF];
                if (value >= 0) {
                    accum <<= 6;
                    shift += 6;
                    accum |= value;
                    if (shift >= 8) {
                        shift -= 8;
                        out[index++] = (byte) ((accum >> shift) & 0xff);
                    }
                }
            }
            if (index != out.length) {
                throw new Exception("Syntax error in parameters");
            }
            return out;
        }
    }
}