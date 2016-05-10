package com.zefun.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * QR 生成器
* @author 高国藩
* @date 2016年5月10日 下午4:33:47
 */
public class GenerateQrCodeUtil {
    /**黑白间隔*/
    private static final int WHITE = 0xFFFFFFFF;
    /**黑白间隔*/
    private static final int BLACK = 0xFF000000;
    /**黑白间隔*/
    private static final String UPLOAD = "upload";

    /**
     * 静态生成二维码 存储在磁盘上
    * @param content  //二维码信息
     * @param contextPath //上下文相对路径
     * @param realPath    //磁盘真实路径
     * @param subPath     //子路径
     * @return            文件丼
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String generateQrcode(String content, String contextPath,
            String realPath, String subPath) {
        if (content == null || realPath == null){
            return null;
        }
        String fileName = generateFileName(content.getBytes()) + ".png";
        String url = "/" + UPLOAD + contextPath + "/" + subPath + "/"
                + fileName; // 图片在项目中存储的相对路径
        String filePath = url;
        // 如果是部署在服务器上的情况，则需要到webapps/下面的upload目录
        if (StringUtils.isNotBlank(contextPath) || realPath.endsWith("root")) {
            filePath = ".." + url;
        }
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE,
                    300, 300, hints);
            File file1 = new File(realPath, filePath); // 创建存储图片的文件
            try {
                GenerateQrCodeUtil.writeToFile(bitMatrix, "png", file1); // 存储二维码图片
                return filePath;
            } 
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } 
        catch (WriterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制写入本地文件中
    * @author 高国藩
    * @date 2016年5月10日 下午6:09:21
    * @param matrix matrix
    * @param format format
    * @param file file
    * @throws IOException IOException
     */
    private static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format
                    + " to " + file);
        }
    }

    /**
     * buffer -> image
    * @author 高国藩
    * @date 2016年5月10日 下午6:09:40
    * @param matrix matrix
    * @return BufferedImage
     */
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 加密
    * @author 高国藩
    * @date 2016年5月10日 下午6:09:57
    * @param bytes 被加密二进制
    * @return      密文
     */
    private static String generateFileName(byte[] bytes) {
        return MD5QrUtils.getMD5String(bytes);
    }

    /**
     * 生成二维码图片 不存储 直接以流的形式输出到页面
     * @param content content
     * @param response response
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void encodeQrcode(String content, HttpServletResponse response) {
        if (org.apache.commons.lang.StringUtils.isBlank(content)){
            return ;
        }
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            // 输出二维码图片流
            try {
                ImageIO.write(image, "png", response.getOutputStream());
            } 
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } 
        catch (WriterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}