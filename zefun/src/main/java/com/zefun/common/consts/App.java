package com.zefun.common.consts;

import javax.servlet.http.HttpServletResponse;

/**
 * 系统业务常量类
 *
 * @author 小高
 * @date Aug 4, 2015 9:19:50 AM
 */
public interface App {

	/** 系统基础常量 */
	class System {
		/** 所有接口成功时返回的CODE值 */
		public static final int API_RESULT_CODE_FOR_SUCCEES = 0;
		/** 所有接口成功时返回的MSG值 */
		public static final String API_RESULT_MSG_FOR_SUCCEES = "succees";

		/** 所有接口异常情况时返回的CODE值 */
		public static final int API_RESULT_CODE_FOR_FAIL = -1;
		/** 所有接口异常情况时返回的MSG值 */
		public static final String API_RESULT_MSG_FOR_FAIL = "fail";

		/** 分页查询默认的每页显示数量 */
		public static final int API_DEFAULT_PAGE_SIZE = 50;

		/** APP中的角色区分，员工 */
		public static final String APP_USER_ROLE_EMPLOYEE = "employee";
		/** APP中的角色区分，老板 */
		public static final String APP_USER_ROLE_BOSS = "boss";

		/** 服务器域名 */
		public static final String SERVER_HOST = "wang.jobwisdom.cn";
//		public static final String SERVER_HOST = "job.jobwisdom.cn";


		/** 服务器基础地址 */
		public static final String SERVER_BASE_URL = "http://" + SERVER_HOST + "/jobwisdom";

		/** 智放公众号的门店标识 */
		public static final int WECHAT_ZEFUN_STORE_ID = 1137;

		/** 友美公众号的门店标识 */
		public static final int WECHAT_YOUMEI_STORE_ID = 0;

		/**
		 * 智放的专属渠道商id
		 */
		public static final int DEFAULT_RECOMMEND_AGENT_ID = 1;

		/**
		 * 权限不足
		 */
		public static final int ERROR_CODE_FORBIDDEN = HttpServletResponse.SC_FORBIDDEN;
		/**
		 * 会话失效
		 */
		public static final int ERROR_CODE_SESSION_INVALID = 999;

		/** 系统角色：总店负责人 */
		public static final int SYSTEM_ROLE_STORE_MAIN_OWNER = 101;
		
		/** 系统角色：总店负责人 */
        public static final int SYSTEM_ROLE_STORE_MAIN_JOBWISDOM = 100;

		/** 系统角色：连锁分店负责人 */
		public static final int SYSTEM_ROLE_STORE_BRANCH_OWNER = 102;

		/** 系统角色：连锁单店负责人 */
		public static final int SYSTEM_ROLE_STORE_SINGLE_OWNER = 103;

		/** 系统角色：老板 */
		public static final int SYSTEM_ROLE_STORE_BOSS = 1;

		/** 系统角色：门店普通员工 */
		public static final int SYSTEM_ROLE_STORE_EMPLOYEE = 4;
		
		/** 百度语音token*/
		public static final String SYSTEM_VOICE = "24.e6c0a67dfc019a28d4c260a251503afa.2592000.1473834846.282335-7977080";
	}

	/** 队列通道常量 */
	class Queue {
		/** 员工服务通知 */
		public static final String NOTICE_SERVICE_FOR_EMPLOYEE = "queue_notice_service_for_employee";
		/** 业务逻辑通知 */
		public static final String OPERATION_LOGIC_INFORM = "operation_logic_inform";
		/** 聊天类通知 */
		public static final String CHAT_NOTIFY = "queue_chat_notify";
		/** 员工服务通知 */
		public static final String EMPLOYEE_SERVICE_NOTICE = "queue_employee_service_notice";
		/** 会员充值通知 */
		public static final String MEMBER_CHARGE_NOTICE = "queue_member_charge_notice";
		/** 会员交易通知 */
		public static final String MEMBER_TRANSCATION_NOTICE = "queue_member_transcation_notice";
		/** 会员预约申请通知 */
		public static final String APPOINTMENT_APPLY_NOTICE = "queue_appointment_apply_notice";
		/** 预约结果通知 */
		public static final String APPOINTMENT_RESULT_NOTICE = "queue_appointment_result_notice";
		/** 会员奖励通知 */
		public static final String MEMBER_REWARD_NOTICE = "queue_member_reward_notice";
		/** 短信验证码通知 */
		public static final String SMS_TEMPLATE_VERIFYCODE = "queue_sms_template_verifycode";
		/** 图文消息要传入队列,更新复制 */
		public static final String TEST = "coupon_test";
		/** 发送优惠券通知 */
		public static final String SEND_COUPONS = "queue_member_service_coupon";
		/** 指定员工 */
		public static final String APPOINT_EMPLOYEE = "queue_appoint_employee_coupon";
		/** 指定员工消息 */
		public static final String APPOINT_EMPLOYEE_MESSAGE = "queue_appoint_employee_message_coupon";
		/** 等待中订单 */
		public static final String WAIT_ORDER_EMPLOYEE = "wait_order_employee";
		/** 员工接收到订单 */
		public static final String EMPLOYEE_RECEIVE_ORDER = "employee_receive_order";
		/** 自助收银计算提成 */
		public static final String CASHIER_ORDER_COMMISSION = "queue_cashier_order_comission";
		/** 修改订单计算提成 */
		public static final String CASHIER_UPDATE_ORDER_COMMISSION = "queue_cashier_update_order_comission";
	}

	/** redis常量key */
	class Redis {
		/** 门店标识对应友宝售货机编码的 hash key */
		public static final String STORE_ID_TO_UBOX_ID_HASH = "store_id_to_ubox_id_key_hash";
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
		/** 微信openid对应业务类型的hash key */
		public static final String WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH = "wechat_openid_to_business_type_key_hash";
		/** 微信openid对应所属门店的hash key */
		public static final String WECHAT_OPENID_TO_STORE_KEY_HASH = "wechat_openid_to_store_key_hash";
		/** 微信openid对应首次关注奖励的临存记录，set */
		public static final String WECHAT_OPENID_TO_SUBSCRIBE_AWARD_SET = "wechat_openid_to_subscribe_award_set";
		/** 会员标识对应微信openid的hash key */
		public static final String WECHAT_MEMBERID_TO_OPENID_KEY_HASH = "wechat_memberid_to_openid_key_hash";
		/** 员工标识对应微信openid的hash key */
		public static final String WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH = "wechat_employeeid_to_openid_key_hash";
		/** 员工登录后对应的部门标识 */
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

		/** 从redis中获取订单号 */
		public static final String GET_ORDER_CODE = "get_order_code";

		/** APP接口token 与 用户账户ID对应的hash key */
		public static final String APP_TOKEN_USER_KEY_HASH = "app_token_user_key_hash";
		/** APP接口用户账户ID 与 token对应的hash key */
		public static final String APP_USER_TOKEN_KEY_HASH = "app_user_token_key_hash";
		/** APP接口token 与 用户设备类型对应的hash key */
		public static final String APP_TOKEN_DEVICE_TYPE_KEY_HASH = "app_token_device_type_key_hash";
		/** APP接口token 与 用户设备标识对应的hash key */
		public static final String APP_TOKEN_DEVICE_TOKEN_KEY_HASH = "app_token_device_token_key_hash";

		/** 用户和角色关联关系 */
		public static final String PC_USER_ID_ROLE_HASH = "pc_user_id_role_hash";
		/** 角色对应权限列表集合 */
		public static final String AUTHORITY_ACCESS_SET_ROLE_PREFIX = "authority_access_set_role_";

		/** 员工打卡状态hash key，空表示未打卡，1表示已打上班卡，2表示上班下班都已打卡 */
		public static final String EMPLOYEE_ATTENDANCE_STATUS_HASH = "employee_attendance_status_hash";

		/** 门店对应总店的hash key，单店同样 */
		public static final String STORE_BRANCH_TO_MAIN_HASH = "store_branch_to_main_hash";
		/** 门店类型（1:单店，2:连锁总店，3:连锁分店）的hash key */
		public static final String STORE_BUSINESS_TYPE_HASH = "store_business_type_hash";
		/** 连锁总店的分店列表，set */
		public static final String STORE_MAIN_TO_BRANCH_SET = "store_main_to_branch_set";
		/** 聊天室中门店在线用户列表，前缀_门店标识，set key */
		public static final String STORE_TO_CHAT_USER_SET_PRE = "store_to_chat_user_set_";
		/** 聊天室,用户标识对应聊天标识,hash key */
		public static final String CHAT_USER_TO_SOCKET_HASH = "chat_user_to_socket_hash";
		/** 聊天室,聊天标识对应用户标识，hash key */
		public static final String CHAT_SOCKET_TO_USER_HASH = "chat_socket_to_user_hash";

		/** 门店服务交接模版hash key */
		public static final String WECHAT_TEMPLATE_SERVICE_TURN_HASH = "wechat_template_service_turn_hash";
		/** 门店服务计划推送模版hash key */
        public static final String WECHAT_TEMPLATE_SERVICE_TOPIC_HASH = "wechat_template_service_topic_hash";
		/** 门店预约申请模版hash key */
		public static final String WECHAT_TEMPLATE_APPOINTMENT_APPLY_HASH = "wechat_template_appointment_apply_hash";
		/** 门店预约结果模版hash key */
		public static final String WECHAT_TEMPLATE_APPOINTMENT_RESULT_HASH = "wechat_template_appointment_result_hash";
		/** 门店预约到时提醒模板的hash key */
		public static final String WECHAT_TEMPLATE_APPOINTMENT_REMIND_HASH = "wechat_template_appointment_remind_hash";
		/** 门店通知会员结账模版hash key */
		public static final String WECHAT_TEMPLATE_PAYMENT_HASH = "wechat_template_payment_hash";
		/** 门店会员充值通知模版 */
		public static final String WECHAT_TEMPLATE_MEMBER_CHARGE_HASH = "wechat_template_member_charge_hash";
		/** 门店优惠券过期提醒模版 */
		public static final String WECHAT_TEMPLATE_COUPON_OVERDUE_HASH = "wechat_template_coupon_overdue_hash";
		/** PC端验证码图片hash */
		public static final String WEB_PC_YZM_PAGE_HASH = "web_pc_yzm_page_hash";
		/** PC端验证码图片set */
		public static final String WEB_PC_YZM_PAGE_SET = "web_pc_yzm_page_set";
	}

	/** session常量key */
	class Session {
		/** 头部一级菜单 */
		public static final String SYSTEM_HEAD_MENU = "session_key_system_head_menu";
		/** 侧边二级菜单 */
		public static final String SYSTEM_LEFT_SUB_MENU = "session_key_system_left_submenu";

		/** 当前用户的openid */
		public static final String WECHAT_OPEN_ID = "session_key_wechat_open_id";
		/** 当前用户微信支付授权的openid */
		public static final String WECHAT_PAY_FOR_ZEFUN_OPEN_ID = "session_key_wechat_pay_for_zefun_open_id";
		/** 当前用户微信支付授权的openid */
		public static final String WECHAT_PAY_FOR_YOUMEI_OPEN_ID = "session_key_pay_for_youmei_open_id";
		/** 当前用户的userid */
		public static final String USER_ID = "session_key_user_id";
		/** session中企业代号*/
        public static final String STORE_ACCOUNT = "session_key_store_account";
		/** 当前用户的roleid */
		public static final String ROLE_ID = "session_key_role_id";
		/** 当前用户的roleid */
		public static final String ACCOUNT_ROLE_ID = "session_key_account_role_id";
		/** 当前用户所在门店id */
		public static final String STORE_ID = "session_key_store_id";
		/** 当前用户所在门店名称 */
		public static final String STORE_NAME = "session_key_store_name";
		/** 当前登录人员信息 */
		public static final String USER_INFO = "session_key_user_info";
		/** 第一次登录标识 */
		public static final String ONE_LOGIN_TIME = "session_key_one_login_time";
		/** 微信操作业务类型(1:会员,2:员工) */
		public static final String WECHAT_BUSINESS_TYPE = "session_wechat_business_type";

		/** 会员信息 */
		public static final String SESSION_KEY_MEMBER_INFO = "session_key_member_info";
		/** 订单类型(1:项目,2:商品,3:疗程) */
		public static final String SESSION_KEY_ORDER_TYPE = "session_key_order_type";
		/** 订单项目标识 */
		public static final String SESSION_KEY_ORDER_PROJECT_ID = "session_key_order_project_id";
		/** 订单项目名称 */
		public static final String SESSION_KEY_ORDER_PROJECT_NAME = "session_key_order_project_name";
		/** 订单项目价格 */
		public static final String SESSION_KEY_ORDER_PROJECT_PRICE = "session_key_order_project_price";
		/** 订单项目数量 */
		public static final String SESSION_KEY_ORDER_PROJECT_COUNT = "session_key_order_project_count";
		/** 订单项目图片 */
		public static final String SESSION_KEY_ORDER_PROJECT_IMAGE = "session_key_order_project_image";

	}

	/** 微信常量 */
	class Wechat {
		/** 临时 ACCESS_TOKEN ,后续将存入库中,定时更新 */
		public static final String ACCESS_TOKEN = "GJwb5yQ2VDkoIxvJ2LskPEPXZYq8Fanbm_hzvd5oURfYevEkwt6-ljth3rvS"
				    + "nPFEqUqTlUys5DIj3KeMwZLasyDhk5YFVYDBqwm_3PehPY4";

		/** 获取用户授权access_token的url */
		public static final String AUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				    + "appid=%s&secret=%s&code=%s&grant_type=authorization_code";

		/** 获取关注用户的信息 */
		public static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

		/** 微信基础授权，获取openid */
		public static final String AUTH_REDIRECT_BASE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				    + "appid={app_id}&redirect_uri=http%3A%2F%2F" + App.System.SERVER_HOST
				    + "%2Fjobwisdom%2Fwechat%2Fcallback%2F{openid_key}"
				    + "%3Fscope%3Dsnsapi_base%26redirect%3D{redirect_uri}&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

		/** 微信高级授权，获取用户信息 */
		public static final String AUTH_REDIRECT_INFO_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				    + "appid={app_id}&redirect_uri=http%3A%2F%2F" + App.System.SERVER_HOST
				    + "%2Fzefun%2Fwechat%2Fcallback%3Fscope%3Dsnsapi_userinfo%26"
				    + "redirect%3D{redirect_uri}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

		/** 获取微信素材资源 */
		public static final String FETCH_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";

		/** 智放微信应用ID */
		public static final String PAY_APP_KEY_ZEFUN = "wx287441046c9f38b2";
		/** 智放微信支付商户号 */
		public static final String MCH_ID_ZEFUN = "1335522901";
		/** 智放微信支付API密钥 */
		public static final String MCH_PAY_KEY_ZEFUN = "123456789qwasdfghjklertyuiopzxcv";

//		/** 智放微信应用ID */
//		public static final String PAY_APP_KEY_YOUMEI = "wx287441046c9f38b2";
//		/** 智放微信支付商户号 */
//		public static final String MCH_ID_YOUMEI = "1335522901";
//		/** 智放微信支付API密钥 */
//		public static final String MCH_PAY_KEY_YOUMEI = "123456789qwasdfghjklertyuiopzxcv";

		/** 币种 */
		public static final String FEE_TYPE = "CNY";
		/** 签名方式 */
		public static final String SIGN_TYPE = "MD5";

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
	}

	/**
	 * 数据字典常量
	 * 
	 * @author 高国藩
	 * @date 2015年10月22日 上午10:04:56
	 */
	class CodeLibary {
		/** 商品品牌typeNo */
		public static final Integer GOODS_TYPE_NO = 205;
	}

	/**
	 * 会员数据
	 * 
	 * @author 高国藩
	 * @date 2015年12月17日 下午9:15:22
	 */
	class Member {
		/** 会员导入,流水更改描述 */
		public static final String IMPORT_MONEY_DECS = "会员导入,流水动态";
		/** 会员迁移,流水更改描述 */
		public static final String MOVE_MONEY_DECS = "会员迁移,流水动态";
	}

	/**
	 * 友宝常量类
	 * 
	 * @author 小高
	 * @date Jan 28, 2016 6:06:39 PM
	 */
	class Ubox {
		/** 友宝测试应用ID */
		// public static final String APP_ID =
		// "5586bd041f7dd84ac8018a4f6c96edac";
		// /** 友宝测试应用密钥 */
		// public static final String APP_SECRET =
		// "3a6ee102c4a1061f9d1c632fbefb9182";
		/** 友宝正式应用ID */
		public static final String APP_ID = "e73509c6f10572d0b6ac42d4c50f4daa";
		/** 友宝正式应用密钥 */
		public static final String APP_SECRET = "8118227ba9c33650ab8361e99492b02b";

		/** 友宝API接口成功返回码 */
		public static final int API_REQUEST_SUCCESS_CODE = 200;
		/** 友宝测试API路径 */
		// public static final String API_REQUEST_URL =
		// "http://monk.dev.uboxol.com/";
		/** 友宝正式API路径 */
		public static final String API_REQUEST_URL = "http://uboxapi.ubox.cn/";

		/** 出货状态：出货失败 */
		public static final int API_DELIVER_CODE_FAIL = 500;
		/** 出货状态：出货成功 */
		public static final int API_DELIVER_CODE_SUCCESS = 200;
		/** 出货状态：出货确认中 */
		public static final int API_DELIVER_CODE_CONFIRMING = 202;
	}
}
