package com.zefun.sms.utils;



public interface App {
    public static final long DELIVERIED_TAG = -1;

    /** 服务器域名 */
    public static final String SERVER_HOST = "zefun.livesall.com";
    /** 服务器基础地址 */
    public static final String SERVER_BASE_URL = "http://" + SERVER_HOST + "/zefun";

    class MsgErr {
        public static final String MQ_MSG_ERR_RECORD_KEY = "mq_msg_err_record";
    }

    
    /** redis常量key */
    class Redis {
        /** 门店对应微信id的hash key */
        public static final String STORE_WECHAT_APP_ID_KEY_HASH = "store_wechat_app_id_key_hash";
        /** 门店对应微信secret的hash key */
        public static final String STORE_WECHAT_APP_SECRET_KEY_HASH = "store_wechat_app_secret_key_hash";
        /** 门店对应微信accessToken的hash key */
        public static final String STORE_WECHAT_ACCESS_TOKEN_KEY_HASH = "store_wechat_access_token_key_hash";
        /** 门店对应微信JS接口的临时票据 */
        public static final String STORE_WECHAT_JSAPI_TICKET_KEY_HASH = "store_wechat_jsapi_ticket_key_hash";
        
        /** 微信用户关注状态的hash key */
        public static final String WECHAT_SUBSCRIBE_KEY_HASH = "wechat_subscribe_key_hash";
        /** 微信openid对应用户id(包括会员跟员工)的hash key前缀 */
        public static final String WECHAT_OPENID_TO_USERID_KEY_HASH = "wechat_openid_to_userid_key_hash";
        /** 会员标识对应微信openid的hash key */
        public static final String WECHAT_MEMBERID_TO_OPENID_KEY_HASH = "wechat_memberid_to_openid_key_hash";
        /** 员工标识对应微信openid的hash key */
        public static final String WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH = "wechat_employeeid_to_openid_key_hash";
        
        
        /** 会员基本信息的hash key，key为会员标识，value为会员基本信息，对应memberBaseDto */
        public static final String MEMBER_BASE_INFO_KEY_HASH = "member_base_info_key_hash";
        
        /** 手机验证码前缀，后面跟手机号码 */
        public static final String PHONE_VERIFY_CODE_KEY_PRE = "phone_verify_code_key_";
        
        /** 短信服务hash key */
        public static final String SMS_SERVICE_KEY_HASH = "sms_service_key_hash";
        /** 短信API访问口令 */
        public static final String SMS_ACCESS_TOKEN_KEY = "access_token";
        /** 短信API刷新 口令 */
        public static final String SMS_REFRESH_TOKEN_KEY = "refresh_token";
    }
    
    /** 短信服务常量类 */
    class Sms {
        /** 智放短信应用id */
        public static final String APP_ID = "202099220000246359";
        /** 智放短信应用密钥 */
        public static final String APP_SECRET = "67ba2ad4a93c9b26d5e732e90e4a1591";
        /** 获取API 口令的接口地址 */
        public static final String ACCESS_TOKEN_URL = "https://oauth.api.189.cn/emp/oauth2/v3/access_token";
        /** 发送短信的API接口地址 */
        public static final String SEND_SMS_URL = "http://api.189.cn/v2/emp/templateSms/sendSms";
        
        /** 验证码短信模版ID */
        public static final String TEMPLATE_VERIFY_CODE = "91549034";
    }
}
