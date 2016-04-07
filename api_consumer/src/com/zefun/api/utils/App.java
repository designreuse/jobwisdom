package com.zefun.api.utils;



public interface App {
    public static final long DELIVERIED_TAG = -1;

    /** 服务器域名 */
    public static final String SERVER_HOST = "www.mockdream.com";
    /** 服务器基础地址 */
    public static final String SERVER_BASE_URL = "http://" + SERVER_HOST + "/zefun";

    class MsgErr {
        public static final String MQ_MSG_ERR_RECORD_KEY = "mq_msg_err_record";
    }

    
    /** redis常量key */
    class Redis {
        /** 百度语音合成AccessToken的key */
        public static final String BAIDU_TEXT_TO_VOICE_ACCESS_TOKEN_KEY = "baidu_text_to_voice_access_token_key";
        /** 门店对应微信id的hash key */
        public static final String STORE_WECHAT_APP_ID_KEY_HASH = "store_wechat_app_id_key_hash";
        /** 门店对应微信secret的hash key */
        public static final String STORE_WECHAT_APP_SECRET_KEY_HASH = "store_wechat_app_secret_key_hash";
        /** 门店对应微信accessToken的hash key */
        public static final String STORE_WECHAT_ACCESS_TOKEN_KEY_HASH = "store_wechat_access_token_key_hash";
        /** 门店对应微信JS接口的临时票据 */
        public static final String STORE_WECHAT_JSAPI_TICKET_KEY_HASH = "store_wechat_jsapi_ticket_key_hash";
        /** 门店对应微信关注图文地址的hash key */
        public static final String STORE_WECHAT_SUBSCRIBE_URL_KEY_HASH = "store_wechat_subscribe_url_key_hash";
        /** 门店对智放图文的点赞操作记录的hash key */
        public static final String STORE_WECHAT_ITEM_PRRISE_KEY_HASH = "store_wechat_item_praise_key_hash";

        /** 微信用户关注状态的hash key */
        public static final String WECHAT_SUBSCRIBE_KEY_HASH = "wechat_subscribe_key_hash";
        /** 微信openid对应用户id(包括会员跟员工)的hash key */
        public static final String WECHAT_OPENID_TO_USERID_KEY_HASH = "wechat_openid_to_userid_key_hash";
        /**微信openid对应业务类型的hash key*/
        public static final String WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH = "wechat_openid_to_business_type_key_hash";
        /**微信openid对应所属门店的hash key*/
        public static final String WECHAT_OPENID_TO_STORE_KEY_HASH = "wechat_openid_to_store_key_hash";
//        /**微信openid对应首次关注优惠券奖励(临存)的hash key*/
//        public static final String WECHAT_OPENID_TO_SUBSCRIBE_COUPON_KEY_HASH = "wechat_openid_to_subscribe_coupon_key_hash";
//        /**微信openid对应首次关注礼金奖励(临存)的hash key*/
//        public static final String WECHAT_OPENID_TO_SUBSCRIBE_GIFT_KEY_HASH = "wechat_openid_to_subscribe_gift_key_hash";
        /**微信openid对应首次关注奖励的临存记录，set*/
        public static final String WECHAT_OPENID_TO_SUBSCRIBE_AWARD_SET = "wechat_openid_to_subscribe_award_set";
        /** 会员标识对应微信openid的hash key */
        public static final String WECHAT_MEMBERID_TO_OPENID_KEY_HASH = "wechat_memberid_to_openid_key_hash";
        /** 员工标识对应微信openid的hash key */
        public static final String WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH = "wechat_employeeid_to_openid_key_hash";
        /** 员工登录后对应的部门标识*/
        public static final String WECHAT_EMPLOYEEID_TO_DEPT = "wechat_employeeid_to_dept";
        /** 会员基本信息的hash key，key为会员标识，value为会员基本信息，对应memberBaseDto */
        public static final String MEMBER_BASE_INFO_KEY_HASH = "member_base_info_key_hash";

        /** 订单基础传输对象的hash key，key为订单编号，value为订单信息，包含订单明细、服务明细 */
        public static final String ORDER_BASE_INFO_KEY_HASH = "order_base_info_key_hash";
        /** 部门的项目信息的hash key，key部门编号，value为项目列表，包含部门信息、项目类别 */
        public static final String DEPT_PROJECT_BASE_INFO_KEY_HASH = "dept_project_base_info_key_hash";
        /** 部门的商品信息的hash key，key部门编号，value为商品列表，包含商品信息、商品类别 */
        public static final String DEPT_GOODS_BASE_INFO_KEY_HASH = "dept_goods_base_info_key_hash";
        /** 部门的轮牌信息的hash key，key部门编号，value为轮牌列表，包括职位列表 */
        public static final String DEPT_PROJECT_MAHJONG_INFO_KEY_HASH = "dept_project_mahjong_info_key_hash";

        /** 手机验证码前缀，后面跟手机号码 */
        public static final String PHONE_VERIFY_CODE_KEY_PRE = "phone_verify_code_key_";
        /** 短信服务hash key */
        public static final String SMS_SERVICE_KEY_HASH = "sms_service_key_hash";
        /** 短信API访问口令 */
        public static final String SMS_ACCESS_TOKEN_KEY = "access_token";
        /** 短信API刷新 口令 */
        public static final String SMS_REFRESH_TOKEN_KEY = "refresh_token";

        /** 内测访问名单 */
        public static final String BETA_ACCESS_USER_SET = "beta_access_user_set";

        /** 从redis中获取订单号*/
        public static final String GET_ORDER_CODE = "get_order_code";


        /** APP接口token 与 用户账户ID对应的hash key */
        public static final String APP_TOKEN_USER_KEY_HASH = "app_token_user_key_hash";
        /** APP接口用户账户ID 与 token对应的hash key */
        public static final String APP_USER_TOKEN_KEY_HASH = "app_user_token_key_hash";
        /** APP接口token 与 用户设备类型对应的hash key */
        public static final String APP_TOKEN_DEVICE_TYPE_KEY_HASH = "app_token_device_type_key_hash";
        /** APP接口token 与 用户设备标识对应的hash key */
        public static final String APP_TOKEN_DEVICE_TOKEN_KEY_HASH = "app_token_device_token_key_hash";

        /**用户和角色关联关系*/
        public static final String PC_USER_ID_ROLE_HASH = "pc_user_id_role_hash";
        /**角色对应权限列表集合*/
        public static final String AUTHORITY_ACCESS_SET_ROLE_PREFIX = "authority_access_set_role_";

        /**员工打卡状态hash key，空表示未打卡，1表示已打上班卡，2表示上班下班都已打卡*/
        public static final String EMPLOYEE_ATTENDANCE_STATUS_HASH = "employee_attendance_status_hash";
        /**门店对应总店的hash key，单店同样*/
        public static final String STORE_BRANCH_TO_MAIN_HASH = "store_branch_to_main_hash";
        /**门店类型（1:单店，2:连锁总店，3:连锁分店）的hash key*/
        public static final String STORE_BUSINESS_TYPE_HASH = "store_business_type_hash";
        /**连锁总店的分店列表，set*/
        public static final String STORE_MAIN_TO_BRANCH_SET = "store_main_to_branch_set";
        /**聊天室中门店在线用户列表，前缀_门店标识，set key*/
        public static final String STORE_TO_CHAT_USER_SET_PRE = "store_to_chat_user_set_";
        /**聊天室,用户标识对应聊天标识,hash key*/
        public static final String CHAT_USER_TO_SOCKET_HASH = "chat_user_to_socket_hash";
        /**聊天室,聊天标识对应用户标识，hash key*/
        public static final String CHAT_SOCKET_TO_USER_HASH = "chat_socket_to_user_hash";
        
        
        /**门店服务交接模版hash key*/
        public static final String WECHAT_TEMPLATE_SERVICE_TURN_HASH = "wechat_template_service_turn_hash";
        /**门店预约申请模版hash key*/
        public static final String WECHAT_TEMPLATE_APPOINTMENT_APPLY_HASH = "wechat_template_appointment_apply_hash";
        /**门店预约结果模版hash key*/
        public static final String WECHAT_TEMPLATE_APPOINTMENT_RESULT_HASH = "wechat_template_appointment_result_hash";
        /**门店预约到时提醒模板的hash key*/
        public static final String WECHAT_TEMPLATE_APPOINTMENT_REMIND_HASH = "wechat_template_appointment_remind_hash";
        /**门店通知会员结账模版hash key*/
        public static final String WECHAT_TEMPLATE_PAYMENT_HASH = "wechat_template_payment_hash";
        /**门店会员充值通知模版*/
        public static final String WECHAT_TEMPLATE_MEMBER_CHARGE_HASH = "wechat_template_member_charge_hash";
        /**门店优惠券过期提醒模版*/
        public static final String WECHAT_TEMPLATE_COUPON_OVERDUE_HASH = "wechat_template_coupon_overdue_hash";
        /** 错订单记录标识*/
        public static final String ERROR_ORDER_ID_MESSAGE_STRING = "error_order_id_message_string";
        /** 错修改明细标识*/
        public static final String ERROR_DETAIL_ID_MESSAGE_STRING = "error_detail_id_message_string";
    }
    
    /** 微信常量 */
    public static class Wechat {
        /** 获取access_token的url */
        public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
                + "grant_type=client_credential&appid=%s&secret=%s";
        
        /** 获取jsapi ticket的url */
        public static final String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
        
        /** 获取关注用户的信息 */
        public static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
        
        /** 获取微信素材资源 */
        public static final String FETCH_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    }
}
