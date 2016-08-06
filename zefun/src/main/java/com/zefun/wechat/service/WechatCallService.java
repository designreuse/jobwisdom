package com.zefun.wechat.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.HttpClientUtil;
import com.zefun.common.utils.SignUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.common.utils.XmlUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EnterpriseInfoDto;
import com.zefun.web.entity.EnterpriseAccount;
import com.zefun.web.entity.EnterpriseAccountFlow;
import com.zefun.web.entity.EnterpriseInfo;
import com.zefun.web.entity.RechargeRecord;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.entity.TransactionInfo;
import com.zefun.web.entity.UboxTransaction;
import com.zefun.web.mapper.EnterpriseAccountFlowMapper;
import com.zefun.web.mapper.EnterpriseAccountMapper;
import com.zefun.web.mapper.EnterpriseInfoMapper;
import com.zefun.web.mapper.RechargeRecordMapper;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.web.mapper.TransactionInfoMapper;
import com.zefun.web.mapper.UboxTransactionMapper;
import com.zefun.web.service.QiniuService;

import net.sf.json.JSONObject;

/**
 * 微信相关api操作业务逻辑类
* @author 高国藩
* @date Aug 13, 2015 2:30:27 PM 
*/
@Service
public class WechatCallService {
    /** 日志记录对象 */
    private final Logger logger = Logger.getLogger(WechatCallService.class);

    /** 七牛api服务类 */
    @Autowired
    private QiniuService qiniuService;

    /** 交易信息操作对象 */
    @Autowired
    private TransactionInfoMapper transactionInfoMapper;

    /** 友宝交易信息服务对象 */
    @Autowired
    private UboxTransactionMapper uboxTransactionMapper;
    /** 门店充值交易 */
    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;
    /** 企业信息表操作*/
    @Autowired
    private EnterpriseInfoMapper enterpriseInfoMapper;
    /** 企业信息表操作*/
    @Autowired
    private EnterpriseAccountFlowMapper enterpriseAccountFlowMapper;
    /** 企业信息表操作*/
    @Autowired
    private EnterpriseAccountMapper enterpriseAccountMapper;
    /** 微信门店设置*/
    @Autowired
    private StoreWechatMapper storeWechatMapper;
    
    

    /**
     *  微信授权回调处理
    * @author 高国藩
    * @date Aug 17, 2015 3:54:23 PM
    * @param redirect       重定向地址
    * @param code           微信返回，用于获取授权的access token
    * @param state          随机字符，用作校验
    * @param scope          应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
    *                       snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。
    *                           并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
    * @param openidKey      存储openid的session key                          
    * @param storeId        门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param appId          微信公众号id
    * @param appSecret      微信公众号密钥
    * @param request        请求对象
    * @param response       返回对象
     * @throws IOException  重定向失败时抛出的异常 
     * @throws ServletException 
     */
    public void callback(String redirect, String code, String state,
            String scope, String openidKey, String storeId, int businessType,
            String appId, String appSecret, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        redirect = redirect.replace("__", "&");
        logger.info("weixin callback redirect -->" + redirect);

        if (StringUtils.isEmpty(code)) {
            response.sendRedirect(redirect);
        }

        String accessTokenRes = HttpClientUtil
                .sendGetReq(
                        String.format(App.Wechat.AUTH_ACCESS_TOKEN_URL,
                                new Object[] { appId, appSecret, code }),
                        "utf-8");
        JSONObject accessTokenJson = JSONObject.fromObject(accessTokenRes);

        // 如果授权失败，跳转到重定向页面
        if (accessTokenJson.containsKey("errcode")) {
            String errcode = accessTokenJson.get("errcode").toString();
            String errmsg = accessTokenJson.get("errmsg").toString();
            logger.error("use weixin login err, code " + code + ", errcode " + errcode + ", errmsg " + errmsg);
            response.sendRedirect(redirect);
            return;
        }

        String openId = accessTokenJson.getString("openid");
        logger.info("wechat auth callback, openid is : " + openId);

        request.getSession().setAttribute(openidKey, openId);
        response.sendRedirect(redirect);
    }

    /**
     * 微信支付回调处理
    * @author 高国藩
    * @date Sep 23, 2015 5:23:58 PM
    * @param data   支付结果
    * @return       业务处理成功返回SUCCESS
     */
    @RequestMapping(value = Url.Wechat.CREATE_PAY, method = RequestMethod.POST)
    @ResponseBody
    public String payCallback(String data) {
        data = data.replace("<![CDATA[", "").replace("]]>", "");
        String returnCode = data.substring(data.indexOf("<return_code>") + 13,
                data.indexOf("</return_code>"));
        String resultCode = data.substring(data.indexOf("<result_code>") + 13,
                data.indexOf("</result_code>"));

        logger.info("payCallback return_code : " + returnCode
                + ", result_code : " + resultCode);
        String totalFee = data.substring(data.indexOf("<total_fee>") + 11,
                data.indexOf("</total_fee>"));
        String tradeNo = data.substring(data.indexOf("<out_trade_no>") + 14,
                data.indexOf("</out_trade_no>"));
        String endTime = data.substring(data.indexOf("<time_end>") + 10,
                data.indexOf("</time_end>"));
        String outTradeNo = data.substring(
                data.indexOf("<transaction_id>") + 16,
                data.indexOf("</transaction_id>"));

        String transactionId = tradeNo.substring(0, 11);
        String sequenceNo = tradeNo.substring(11);
        BigDecimal amount = (new BigDecimal(totalFee))
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        totalFee = amount.toString();

        logger.info("total_fee : " + totalFee + ", trade_no : " + tradeNo
                + ", end_time : " + endTime + ", out_trade_no : " + outTradeNo
                + ", transactionId : " + transactionId + ", sequenceNo : "
                + sequenceNo);

        return "SUCCESS";
    }

    /**
     * 系统服务模块的微信支付
    * @author 高国藩
    * @date Jan 16, 2016 9:07:46 PM
    * @param storeAccount storeAccount
    * @param storeId    门店标识
    * @param goodsType  商品类型(1、门店开通，2、门店续费，3、短息购买，4、商品购买，5、参加会议)
    * @param goodsId    商品标识
    * @param goodsName  商品名称
    * @param totalFee   支付金额(单位：分)
    * @param openId     微信用户标识
    * @param callback   回调地址
    * @param request    请求对象
    * @return   微信支付所属参数对象
     */
    public BaseDto wepayForZefun(String storeAccount, Integer storeId, int goodsType,
            Integer goodsId, String goodsName, Integer totalFee, String openId,
            String callback, HttpServletRequest request) {
        String transactionId = StringUtil.getKey();
        callback = callback.replace("{transactionId}", transactionId);
        BaseDto result = pay(storeAccount, goodsName, totalFee, openId, transactionId, callback, request);
        if (result.getCode() == App.System.API_RESULT_CODE_FOR_SUCCEES) {
            // 新建订单
            TransactionInfo transactionInfo = new TransactionInfo();
            transactionInfo.setTransactionId(transactionId);
            transactionInfo.setTransactionAmount(totalFee);
            transactionInfo.setGoodsType(goodsType);
            transactionInfo.setGoodsId(goodsId);
            transactionInfo.setGoodsName(goodsName);
            transactionInfo.setOpenId(openId);
            transactionInfo.setStoreId(storeId);
            transactionInfo.setPayChannel(1);
            transactionInfo.setPayStatus(1);
            transactionInfo.setCreateTime(DateUtil.getCurTime());
            transactionInfoMapper.insert(transactionInfo);
        }
        return result;
    }

    /**
     * 
    * @author 小高
    * @date Sep 23, 2015 5:45:28 PM
    * @param goodsName      商品名称
    * @param totalFee       支付金额(单位：分)
    * @param openId         支付用户标识
    * @param outTradeNo     支付订单号
    * @param callBackUrl    支付成功回调接口
    * @param request        请求对象
    * @param storeAccount   企业标示
    * @return               支付申请结果
     */
    public BaseDto pay(String storeAccount, String goodsName, Integer totalFee,
            String openId, String outTradeNo, String callBackUrl,
            HttpServletRequest request) {
        
        StoreWechat storeWechat = storeWechatMapper.selectByPrimaryKey(storeAccount);
        String appId = storeWechat.getAppId();
        String mchId = storeWechat.getMchId();
        String mchKey = storeWechat.getMchKey();
        
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(mchId) || StringUtils.isEmpty(mchKey)){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该商户未设置支付信息, 请到店体验.");
        }

        String deviceInfo = "xxoo";

        String spbillCreateIp = StringUtil.getIpAddr(request);
        String uuid = UUID.randomUUID().toString().replace("-", "");

        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appId);
        parameters.put("mch_id", mchId);
        parameters.put("device_info", deviceInfo);
        parameters.put("nonce_str", uuid);
        parameters.put("body", goodsName);
        parameters.put("out_trade_no", outTradeNo);
        parameters.put("total_fee", String.valueOf(totalFee));
        parameters.put("spbill_create_ip", spbillCreateIp);
        parameters.put("notify_url", App.System.SERVER_BASE_URL + callBackUrl.replace("{transactionId}", outTradeNo));
        parameters.put("trade_type", "JSAPI");
        parameters.put("openid", openId);
        String mySign = SignUtil.createSign("UTF-8", parameters, mchKey);
        parameters.put("sign", mySign);

        logger.info("sign result : " + parameters);
        String preXml = "";
        try {
            preXml = wxUnifiedorderPost(parameters);
        } 
        catch (IOException e) {
            logger.error("wx_unifiedorder_post error : ", e);
        }
        logger.info("weixin pay request result : " + preXml);

        preXml = preXml.replace("<![CDATA[", "").replace("]]>", "");
        String returnCode = preXml.substring(preXml.indexOf("<return_code>") + 13, preXml.indexOf("</return_code>"));
        String resultCode = preXml.substring(preXml.indexOf("<result_code>") + 13, preXml.indexOf("</result_code>"));

        Map<String, String> payMap = new HashMap<String, String>();
        if (preXml.contains("<result_code>") && returnCode.equals("SUCCESS") && resultCode.equals("SUCCESS")) {
            String prepayId = preXml.substring(preXml.indexOf("<prepay_id>") + 11, preXml.indexOf("</prepay_id>"));
            String xpackage = "prepay_id=" + prepayId;
            String ts = Long.toString(System.currentTimeMillis());

            SortedMap<Object, Object> parameters2 = new TreeMap<Object, Object>();
            parameters2.put("appId", appId);
            parameters2.put("timeStamp", ts);
            parameters2.put("nonceStr", uuid);
            parameters2.put("signType", App.Wechat.SIGN_TYPE);
            parameters2.put("package", xpackage);
            String paySign = SignUtil.createSign("UTF-8", parameters2, mchKey); // SignUtil.paySign(singMap2, mchKey);

            payMap.put("resultCode", "0");
            payMap.put("appId", appId);
            payMap.put("timeStamp", ts);
            payMap.put("nonceStr", uuid);
            payMap.put("signType", App.Wechat.SIGN_TYPE);
            payMap.put("paySign", paySign);
            payMap.put("package", xpackage);
            payMap.put("transactionId", outTradeNo);
        } 
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }

        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, payMap);
    }

    /**
     * NATIVE 原生支付
    * @author 高国藩
    * @date 2016年5月10日 下午3:58:03
    * @param goodsName goodsName
    * @param totalFee  goodsName
    * @param outTradeNo goodsName
    * @param callBackUrl goodsName
    * @param request goodsName
    * @return goodsName
     */
    public String payByQrCode(String goodsName, Integer totalFee,
            String outTradeNo, String callBackUrl, HttpServletRequest request) {
        String appId = App.Wechat.PAY_APP_KEY_ZEFUN;
        String mchId = App.Wechat.MCH_ID_ZEFUN;
        String mchKey = App.Wechat.MCH_PAY_KEY_ZEFUN;
        String deviceInfo = "WEB";
        String spbillCreateIp = SignUtil.localIp(); //StringUtil.getIpAddr(request);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appId);
        parameters.put("mch_id", mchId);
        parameters.put("device_info", deviceInfo);
        parameters.put("nonce_str", uuid);
        parameters.put("body", goodsName);
        parameters.put("out_trade_no", outTradeNo);
        parameters.put("total_fee", String.valueOf(totalFee));
        parameters.put("spbill_create_ip", spbillCreateIp);
        parameters.put("notify_url", App.System.SERVER_BASE_URL + callBackUrl);
        parameters.put("trade_type", "NATIVE");
        parameters.put("product_id", "123ed");
        String mySign = SignUtil.createSign("UTF-8", parameters, mchKey);
        parameters.put("sign", mySign);
        String preXml = "";
        try {
            preXml = wxQrUnifiedorderPost(parameters);
            Document dt = DocumentHelper.parseText(preXml);
            Element root = dt.getRootElement();
            Element codeUrl = root.element("code_url");
            String qrUrl = codeUrl.getText();
            return qrUrl;
        } 
        catch (Exception e) {
            logger.error("wx_unifiedorder_post error : ", e);
        }
        logger.info("weixin pay request result : " + preXml);

        return null;
    }
    
    /**
     * MICROPAY 刷卡支付
    * @author 高国藩
    * @date 2016年5月10日 下午3:58:03
    * @param goodsName goodsName
    * @param totalFee  goodsName
    * @param outTradeNo outTradeNo
    * @param request goodsName
    * @param authCode  授权码
    * @return goodsName
     * @throws Exception Exception
     */
    public String payByMicro(String goodsName, Integer totalFee, String authCode, String outTradeNo, HttpServletRequest request) throws Exception {
        String appId = App.Wechat.PAY_APP_KEY_ZEFUN;
        String mchId = App.Wechat.MCH_ID_ZEFUN;
        String mchKey = App.Wechat.MCH_PAY_KEY_ZEFUN;
        String spbillCreateIp = SignUtil.localIp(); //StringUtil.getIpAddr(request);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appId);
        parameters.put("mch_id", mchId);
        parameters.put("auth_code", authCode);
        parameters.put("nonce_str", uuid);
        parameters.put("body", goodsName);
        parameters.put("out_trade_no", outTradeNo);
        parameters.put("total_fee", String.valueOf(totalFee));
        parameters.put("spbill_create_ip", spbillCreateIp);
        String mySign = SignUtil.createSign("UTF-8", parameters, mchKey);
        parameters.put("sign", mySign);
        return wxPayMicroPayPost(parameters);
    }
    
    /**
     * MICROPAY 刷卡支付api结果
    * @author 高国藩
    * @date 2016年8月5日 下午3:30:34
    * @param parameters  parameters
    * @return  String
    * @throws Exception Exception
     */
    private String wxPayMicroPayPost(SortedMap<Object, Object> parameters) throws Exception {
        StringBuffer xml = new StringBuffer();
        xml.append("<xml>").append("<appid>").append(parameters.get("appid")).append("</appid>")
        .append("<body><![CDATA[").append(parameters.get("body")).append("]]></body>")
        .append("<auth_code><![CDATA[").append(parameters.get("auth_code")).append("]]></auth_code>")
        .append("<mch_id>").append(parameters.get("mch_id")).append("</mch_id>")
        .append("<nonce_str>").append(parameters.get("nonce_str")).append("</nonce_str>")
        .append("<out_trade_no>").append(parameters.get("out_trade_no")).append("</out_trade_no>").append("<spbill_create_ip>")
        .append(parameters.get("spbill_create_ip")).append("</spbill_create_ip>")
        .append("<total_fee>").append(parameters.get("total_fee")).append("</total_fee>")
        .append("<sign><![CDATA[").append(parameters.get("sign")) .append("]]></sign>")
        .append("</xml>");
        StringBuffer resultXml = new StringBuffer();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Url.AppPay.WECHAT_MICROPAY_API);
            StringEntity myEntity = new StringEntity(xml.toString(), "utf-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(myEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    InputStream is = resEntity.getContent();
                    BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        resultXml.append(line);
                    }
                }
            } 
            finally {
                response.close();
            }
        } 
        finally {
            httpclient.close();
        }
        return resultXml.toString();
    }

    /**
     *微信统一下单api，获取预付单信息
    * @author 高国藩
    * @date Sep 23, 2015 7:31:07 PM
    * @param xo                         订单信息
    * @return                           预付单信息
    * @throws ClientProtocolException   客户端协议异常
    * @throws IOException               返回结果读取异常
     */
    private static String wxUnifiedorderPost(SortedMap<Object, Object> xo)
            throws ClientProtocolException, IOException {
        StringBuffer xml = new StringBuffer();
        xml.append("<xml>").append("<appid>").append(xo.get("appid"))
                .append("</appid>").append("<body><![CDATA[")
                .append(xo.get("body")).append("]]></body>")
                .append("<device_info>").append(xo.get("device_info"))
                .append("</device_info>").append("<mch_id>")
                .append(xo.get("mch_id")).append("</mch_id>")
                .append("<nonce_str>").append(xo.get("nonce_str"))
                .append("</nonce_str>").append("<notify_url>")
                .append(xo.get("notify_url")).append("</notify_url>")
                .append("<out_trade_no>").append(xo.get("out_trade_no"))
                .append("</out_trade_no>").append("<spbill_create_ip>")
                .append(xo.get("spbill_create_ip"))
                .append("</spbill_create_ip>").append("<total_fee>")
                .append(xo.get("total_fee")).append("</total_fee>")
                .append("<trade_type>").append(xo.get("trade_type"))
                .append("</trade_type>").append("<openid>")
                .append(xo.get("openid")).append("</openid>")
                .append("<sign><![CDATA[").append(xo.get("sign"))
                .append("]]></sign>").append("</xml>");

        StringBuffer resultXml = new StringBuffer();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(
                    "https://api.mch.weixin.qq.com/pay/unifiedorder");
            StringEntity myEntity = new StringEntity(xml.toString(), "utf-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(myEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (response.getStatusLine()
                        .getStatusCode() == HttpStatus.SC_OK) {
                    InputStream is = resEntity.getContent();
                    BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        resultXml.append(line);
                    }
                }
            } 
            finally {
                response.close();
            }
        } 
        finally {
            httpclient.close();
        }
        return resultXml.toString();
    }

    /**
     *微信统一下单api，获取预付单信息
    * @author 高国藩
    * @date Sep 23, 2015 7:31:07 PM
    * @param xo                         订单信息
    * @return                           预付单信息
    * @throws ClientProtocolException   客户端协议异常
    * @throws IOException               返回结果读取异常
     */
    private static String wxQrUnifiedorderPost(SortedMap<Object, Object> xo)
            throws ClientProtocolException, IOException {
        StringBuffer xml = new StringBuffer();
        xml.append("<xml>").append("<appid>").append(xo.get("appid"))
                .append("</appid>").append("<body><![CDATA[")
                .append(xo.get("body")).append("]]></body>")
                .append("<device_info>").append(xo.get("device_info"))
                .append("</device_info>").append("<mch_id>")
                .append(xo.get("mch_id")).append("</mch_id>")
                .append("<nonce_str>").append(xo.get("nonce_str"))
                .append("</nonce_str>").append("<notify_url>")
                .append(xo.get("notify_url")).append("</notify_url>")
                .append("<out_trade_no>").append(xo.get("out_trade_no"))
                .append("</out_trade_no>").append("<spbill_create_ip>")
                .append(xo.get("spbill_create_ip"))
                .append("</spbill_create_ip>").append("<total_fee>")
                .append(xo.get("total_fee")).append("</total_fee>")
                .append("<trade_type>").append(xo.get("trade_type"))
                .append("</trade_type>").append("<product_id>")
                .append(xo.get("product_id")).append("</product_id>")
                .append("<sign><![CDATA[").append(xo.get("sign"))
                .append("]]></sign>").append("</xml>");

        StringBuffer resultXml = new StringBuffer();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Url.AppPay.WECHAT_SERVER_API);
            StringEntity myEntity = new StringEntity(xml.toString(), "utf-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(myEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    InputStream is = resEntity.getContent();
                    BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        resultXml.append(line);
                    }
                }
            } 
            finally {
                response.close();
            }
        } 
        finally {
            httpclient.close();
        }
        return resultXml.toString();
    }
    

    /**
     * 微信退款操作
    * @author 高国藩
    * @date Mar 11, 2016 4:39:12 PM
    * @param transactionId     交易标识
    * @return   退款结果
     */
    public String refund(String transactionId) {
        CloseableHttpClient httpclient = null;
        try {
            String path = Thread.currentThread().getContextClassLoader()
                    .getResource("").getPath() + "cert/wechat/";
            String appid = App.Wechat.PAY_APP_KEY_ZEFUN;
            String mchId = App.Wechat.MCH_ID_ZEFUN;
            String payKey = App.Wechat.MCH_PAY_KEY_ZEFUN;
            String keyPath = path + "apiclient_cert_youmei.p12";

            UboxTransaction uboxTransaction = uboxTransactionMapper
                    .selectByPrimaryKey(transactionId);
            Map<String, String> params = new HashMap<>();
            params.put("appid", appid);
            params.put("mch_id", mchId);
            params.put("nonce_str",
                    UUID.randomUUID().toString().replace("-", ""));
            params.put("out_trade_no", transactionId);
            params.put("out_refund_no", StringUtil.getKey());
            params.put("total_fee",
                    uboxTransaction.getTransactionAmount().toString());
            params.put("refund_fee",
                    uboxTransaction.getTransactionAmount().toString());
            params.put("op_user_id", mchId);

            String sign = SignUtil.paySign(params, payKey);
            params.put("sign", sign);

            String xml = XmlUtil.getXmlFromMap(params);

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(keyPath));
            keyStore.load(instream, mchId.toCharArray());
            instream.close();

            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, mchId.toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf)
                    .build();

            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            StringEntity myEntity = new StringEntity(xml, "utf-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(myEntity);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        } 
        catch (KeyManagementException | UnrecoverableKeyException
                | KeyStoreException | NoSuchAlgorithmException
                | CertificateException | ParseException | IOException e) {
            logger.error("wechat refund error : ", e);
        } 
        finally {
            // 关闭连接，释放资源
            try {
                httpclient.close();
            } 
            catch (Exception e) {
                e.printStackTrace();
                httpclient = null;
            }
        }
        return null;
    }

    /**
     * 
    * @author 高国藩
    * @date Aug 22, 2015 11:56:02 AM
    * @param mediaid        微信的资源id
    * @param key            七牛目标地址
    * @param accessToken    微信api访问口令
    * @return               成功返回码0,失败返回其他错误码
     */
    public BaseDto uploadMediaToQiniu(String mediaid, String key, String accessToken) {
        String mediaUrl = String.format(App.Wechat.FETCH_MEDIA_URL, new Object[] { accessToken, mediaid });
        return qiniuService.fetch(mediaUrl, key);
    }

    /**
     * 门店充值,生成单据
    * @author 高国藩
    * @date 2016年5月23日 上午11:50:02
    * @param storeAccount storeAccount
    * @param openId       充值人员
    * @param totalFee     金额
    * @param outTradeNo   32Str
    * @param i            0:入单,1:改单
     */
    @Transactional
    public void updateRechargeRecord(String storeAccount, Integer totalFee, String outTradeNo, Integer i, String openId) {
        if (i == 0){
            EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
            enterpriseInfo.setStoreAccount(storeAccount);
            
            EnterpriseInfoDto enterpriseInfoDto = enterpriseInfoMapper.selectByProperties(enterpriseInfo);
            
            RechargeRecord rechargeRecord = new RechargeRecord();
            rechargeRecord.setCreateTime(DateUtil.getCurDate());
            rechargeRecord.setEnterpriseAccountId(enterpriseInfoDto.getEnterpriseInfoId());
            rechargeRecord.setStatus(0);
            rechargeRecord.setOutTradeNo(outTradeNo);
            rechargeRecord.setRechargeAmount(new BigDecimal(totalFee));
            rechargeRecordMapper.insert(rechargeRecord);
        }
        if (i == 1){
            RechargeRecord rechargeRecord2 = new RechargeRecord();
            rechargeRecord2.setOutTradeNo(outTradeNo);
            RechargeRecord rechargeRecord = rechargeRecordMapper.selectByTradeNo(rechargeRecord2);
            rechargeRecord.setOpenId(openId);
            rechargeRecord.setStatus(1);
            rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
            
            
            EnterpriseInfo enterpriseInfo = enterpriseInfoMapper.selectByPrimaryKey(rechargeRecord.getEnterpriseAccountId());
            EnterpriseAccount enterpriseAccount = enterpriseAccountMapper.selectByPrimaryKey(enterpriseInfo.getEnterpriseInfoId());
            enterpriseAccount.setBalanceAmount(enterpriseAccount.getBalanceAmount().
                    add(new BigDecimal(rechargeRecord.getRechargeAmount().intValue())));
            enterpriseAccount.setTotalAmount(enterpriseAccount.getTotalAmount().add(new BigDecimal(rechargeRecord.getRechargeAmount().intValue())));
            enterpriseAccountMapper.updateByPrimaryKeySelective(enterpriseAccount);
            
            EnterpriseAccountFlow enterpriseAccountFlow = new EnterpriseAccountFlow();
            enterpriseAccountFlow.setBusinessType("充值");
            enterpriseAccountFlow.setFlowType(2);
            enterpriseAccountFlow.setCreateTime(DateUtil.getCurTime());
            enterpriseAccountFlow.setEnterpriseAccountId(rechargeRecord.getEnterpriseAccountId());
            enterpriseAccountFlow.setBalanceAmount(enterpriseAccount.getBalanceAmount());
            enterpriseAccountFlow.setFlowAmount(rechargeRecord.getRechargeAmount());
            enterpriseAccountFlowMapper.insertSelective(enterpriseAccountFlow);
            
        }
    }
    
}