package com.zefun.common.consts;

/**
 * 视图地址常量类，定义时使用根目录/WEB-INF/view/下对绝对地址
 * 
 * @author 小高
 * @date Aug 4, 2015 9:21:29 AM
 */
public interface View {
	/**
	 * 首页处理
	 * 
	 * @author 高国藩
	 * @date 2015年10月26日 下午4:54:58
	 */
	class Index {
		/** 登陆页面 */
		public static final String LOGIN = "login";
	}

	/**
	 * 角色权限控制
	 * 
	 * @author 高国藩
	 * @date 2015年11月25日 上午11:53:07
	 */
	class Authority {
		/** 角色权限配置页面 */
		public static final String VIEW = "authority/view";
	}

	/**
	 * 企业模块
	* @author 老王
	* @date 2016年5月21日 下午7:14:27
	 */
	class Enterprise {
		/** 新增企业*/
		public static final String ADD_ENTERPRISE = "enterprise/addEnterprise";
	}
	/**
	 * 渠道会议
	 * 
	 * @author 高国藩
	 * @date 2016年1月7日 下午5:40:28
	 */
	class Conference {
		/** 会议申请页面 */
		public static final String CONFERENCE_VIEW = "mobile/conference/applicationMeeting";
		/** 会议列表页面 */
		public static final String CONFERENCE_VIEW_LIST = "mobile/conference/mettingList";
		/** 会议详情页面 */
		public static final String CONFERENCE_VIEW_INFO = "mobile/conference/conferenceInfo";
		/** 会议修改页面 */
		public static final String CONFERENCE_VIEW_UPDATE = "mobile/conference/updateMeeting";
		/** 分享会议页面 */
		public static final String SHARE_CONFERENCE_VIEW = "mobile/conference/share";
		/** 报名情况页面 */
		public static final String CONFERENCE_VIEW_REGIST = "mobile/conference/regist";
		/** 费用详情页面 */
		public static final String VIEW_CONFERENCE_DETAILS = "mobile/conference/details";
		/** 入场情况页面 */
		public static final String CONFERENCE_VIEW_ADMISSION = "mobile/conference/admission";
	}

	
	   
    /**
     *  统计
    * @author 骆峰
    * @date 2016年8月10日 上午10:04:32
     */
    class Statistical{
        /**商品报表展示 */
        public static final  String STORE_ORDER = "order/store_order";
        /**销售展示 */
        public static final  String PROJECT_ORDER = "order/project-order";
        /**销售展示 */
        public static final  String PROJECT_COMBOINFO = "order/comboInfo";
    }
	/**
	 * 门店管理制度模块
	 * 
	 * @author 小高
	 * @date Dec 5, 2015 7:17:45 PM
	 */
	class StoreManageRule {
		/** 管理规则页面 */
		public static final String RULE = "employee/manage/rule";
	}
	
	/**
	 * 营销中心
	* @author 老王
	* @date 2016年6月13日 下午1:42:19
	 */
	class Marketing {
		/** 打转盘*/
		public static final String SHOW_BIG_TURNTABLE = "marketing/bigTurntable";
		/** 微砍价*/
		public static final String SHOW_MIN_BARGAIN = "marketing/minBargain";
		/** 点灯笼*/
		public static final String SHOW_LANTERN = "marketing/lantern";
		/** 微投票*/
		public static final String SHOW_MIN_VOTE = "marketing/minVote";
	}
	
	/**
	 * 设置类模块
	 * 
	 * @author 小高
	 * @date Nov 9, 2015 11:36:48 AM
	 */
	class Setting {
		
		/** 门店列表展示 */
		public static final String STORE_LIST = "setting/storeList";
		/** 店铺设置 */
		public static final String STORE_SETTING = "setting/storeSetting";

		/** 门店基础设置 */
		public static final String BASE_SETTING = "setting/baseSetting";

		/** 个人账户 */
		public static final String PERSON_SETTING = "setting/personSetting";

		/** 分店列表 */
		public static final String BRANCH_LIST = "setting/branchList";

		/** 门店微信设置 */
		public static final String STORE_WECHAT = "setting/storeWechat";

		/** 门店使用状况 */
		public static final String STORE_USAGE = "setting/storeUsage";

		/** 门店分享机制 */
		public static final String SHARE = "setting/shareSetting";
		
		/**提成分配方案*/
        public static final String VIEW_COMMISSION_SCHEME = "setting/commissionScheme";
        
        /**权限分配页面展示*/
        public static final String VIEW_COMMISSION_ROLE = "setting/addRole";
        
        /**权限分配页面展示*/
        public static final String VIEW_ADMINISTRATOR = "setting/administrator";
	}

	/**
	 * 人脸模块相关页面
	 * 
	 * @author 小高
	 * @date Jul 2, 2015 2:57:02 PM
	 */
	class Face {
		/** 添加face页面 */
		public static final String ADD = "face/add";
		/** 搜索face页面 */
		public static final String SEARCH = "face/search";
	}

	/**
	 * 营业报表模块相关页面
	 * 
	 * @author 乐建建
	 * @date 2016年1月25日 上午10:14:14
	 */
	class BusinessReport {
		/** 营业汇总 */
		public static final String BUSINESSSUMMARY = "businessreport/summary";
		/** 现金收入 */
		public static final String CASHRECEIPTS = "businessreport/cashreceipts";
		/** 卡项销售 */
		public static final String CARDSALES = "businessreport/cardsales";
		/** 劳动业绩 */
		public static final String LABORPERFORMANCE = "businessreport/laborperformance";
		/** 划卡消费 */
		public static final String CARDCONSUPTION = "businessreport/cardconsumption";
		/** 疗程销售 */
		public static final String COMBOSALES = "businessreport/packagesales";
		/** 商品销售 */
		public static final String GOODSSALES = "businessreport/commoditysales";
		/** 工资单 */
		public static final String SALARYLIST = "businessreport/payroll";
		/** 跨门店对账 */
		public static final String RECONCILIATION = "businessreport/crossReconciliation";
	}

	/**
	 * 批量设置
	* @author 老王
	* @date 2016年8月11日 下午8:02:00
	 */
	class BatchSet {
		/** 项目批量设置初始化页面*/
		public static final String VIEW_PROJECT_BATCH_SET = "batchSet/projectBatchSet";
	}
	
	/**
	 * 会员等级相关页面
	 * 
	 * @author 小高
	 * @date Aug 5, 2015 7:49:53 PM
	 */
	class MemberLevel {
		/** 企业新增会员卡类别*/
		public static final String ENTERPRISE_MEMBER_LEVEL = "member/memberLevel/enterpriseMemberLevel";
		/** 会员等级列表页面 */
		public static final String LIST = "member/memberLevel/list";
		/** 储值余额走势图*/
        public static final String CARD_SELL_COUNT = "member/memberLevel/cardSell";
	}

	
	   /** 活动模块*/
    class Activity {
        /** 进入活动类别页面 */
        public static final String TO_ACTIVITYSIGN = "keepAccounts/activity-account";
        
        /** 进入门店活动页面 */
        public static final String TO_ACTIVITYSTORE = "keepAccounts/activity-store";
    }
	
	/**
	 * 会员信息页面
	 * 
	 * @author 高国藩
	 * @date 2015年9月8日 下午6:04:08
	 */
	class MemberInfo {
		/** 会员展示 */
		public static final String MEMBER_LIST_VIEW = "member/memberInfo/memberList";
		/** 会员展示 */
		public static final String GROUP_LIST_VIEW = "member/memberInfo/groupList";
		/** 会员导入错误数据展示页面 */
		public static final String VEIW_ERROR_MEMBER = "member/memberInfo/errorMember";
		/** 总店会员页面 */
		public static final String BASE_MEMBER_VIEW = "member/memberInfo/baseMember";
	}

	/**
	 * 微信会员中心相关页面
	 * 
	 * @author 小高
	 * @date Aug 19, 2015 5:46:43 PM
	 */
	class MemberCenter {
		/** 会员主页面 */
		public static final String HOME = "mobile/member/home";
		/** 会员等级页面 */
		public static final String LEVEL_INFO = "mobile/member/levelInfo";
		/** 个人资料 */
		public static final String INFO = "mobile/member/info";
		/** 密码设置页面 */
		public static final String SET_PWD = "mobile/member/setPwd";
		/** 会员注册页面 */
		public static final String REGISTER = "mobile/member/register";
		/** 会员充值页面 */
		public static final String ACCOUNT = "mobile/member/account";
		/** 完善会员信息页面 */
		public static final String REGISTER_INFO = "mobile/member/registerInfo";
		/** 发型分享页面 */
		public static final String SHARE_SHOW = "mobile/member/shareShow";
		/** 分享信息页面 */
		public static final String SHARE_INFO = "mobile/member/shareInfo";
		/** 预约页面 */
		public static final String ORDER_APPOINTMENT = "mobile/member/orderAppointment";
		/** 项目详情 */
		public static final String PROJECT_DETAIL = "mobile/member/projectDetail";
		/** 时间预约 */
		public static final String DATE_APPOINTMENT = "mobile/member/dateAppointment";
		/** 积分流水 */
		public static final String INTEGRAL_FLOW = "mobile/member/integralFlow";
		/** 卡金流水记录页面 */
		public static final String CARD_MONEY_FLOW = "mobile/member/cardmoneyFlow";
		/** 礼金流水记录页面 */
		public static final String GIFT_MONEY_FLOW = "mobile/member/giftmoneyFlow";
		/** 在线商城 */
		public static final String SHOP_CENTER = "mobile/member/shopCenter";
		/** 商城分类 */
        public static final String SHOP_CENTER_LIST = "mobile/member/goodsList";
		/** 会员优惠券 */
		public static final String MEMBER_COUPON = "mobile/member/memberCoupon";
		/** 门店优惠券 */
        public static final String STORE_COUPON = "mobile/member/storeCoupon";
		/** 店铺信息 */
		public static final String STORE_INFO = "mobile/member/storeInfo";
		/** 店铺展示 */
		public static final String STORE_SHOW = "mobile/member/storeShow";
		/** 会员预约列表 */
		public static final String APPOINTMENT_LIST = "mobile/member/appointmentList";
		/** 会员订单列表 */
		public static final String ORDER_LIST = "mobile/member/orderList";
		/** 会员订单确认 */
		public static final String ORDER_PAY = "mobile/member/orderPay";
		/** 会员订单支付明细 */
		public static final String PAYMENT_DETAIL = "mobile/member/paymentDetail";
		/** 会员订单评价 */
		public static final String ORDER_EVALUATE = "mobile/member/orderEvaluate";
		/** 会员疗程列表 */
		public static final String COMBO_LIST = "mobile/member/comboList";
		/** 门店列表 */
		public static final String STORE_LIST = "mobile/member/storeList";
		/** 员工信息 */
		public static final String EMPLOYEE_INFO = "mobile/member/employeeInfo";
		/** 员工项目详情 */
		public static final String EMPLOYEE_PROJECT = "mobile/member/employeeProject";
		/** 门店特色服务*/
        public static final String STORE_SPE = "mobile/member/special";
        /** 门店在线商城*/
        public static final String ONLIONE_SHOP = "setting/storeShop";
	}

	/** 友宝商城模块 */
	class UboxMall {
		/** 商品详情 */
		public static final String GOODS_INFO = "mobile/uboxMall/goodsInfo";
		/** 商品支付详情页面 */
		public static final String PAYMENT_INFO = "mobile/uboxMall/paymentInfo";
		/** 订单列表页面 */
		public static final String ORDER_LIST = "mobile/uboxMall/orderList";
	}

	/**
	 * 员工手机端
	 * 
	 * @author 王大爷
	 * @date 2015年8月21日 上午10:15:25
	 */
	class StaffPage {
		/** 员工登录页面 */
		public static final String STAFF_LOGIN = "mobile/staff/login";
		/** 员工操作中心 */
		public static final String STAFF_CENTER = "mobile/staff/staffCenter";
		/** 员工个人信息 */
		public static final String STAFF_INFO = "mobile/staff/staffInfo";
		/** 员工密码页面 */
		public static final String UPDATE_PWD = "mobile/staff/updatePwd";
		/** 更多操作界面 */
		public static final String STAFF_MORE = "mobile/staff/more";
		/** 员工预约列表 */
		public static final String STAFF_APPOINT = "mobile/staff/staffAppoint";
		/** 员工业绩排行 */
		public static final String ALL_ERANING = "mobile/staff/allEarning";
		/** 员工个人业绩 */
		public static final String STAFF_ERANING = "mobile/staff/staffEarning";
		/** 员工接待页面 */
		public static final String RECEPTION = "mobile/staff/reception";
		/** 选择类别页面 */
		public static final String PROJECT_CATEGORY = "mobile/staff/projectCategory";
		/** 轮牌指定 */
		public static final String MEMBER_SHIFTMAHJONG_SERVE = "mobile/staff/memberShiftMahjongServe";
		/** 项目列表 */
		public static final String PROJECT_LIST = "mobile/staff/projectList";
		/** 会员结账界面 */
		public static final String MEMBER_PAY = "mobile/staff/memberPay";
		/** 员工工资 */
		public static final String STAFF_SALARY = "mobile/staff/staffSalary";
		/** 员工排班查看*/
        public static final String VIEW_SCHEDULING = "mobile/staff/scheduling";
		/** 员工服务界面 */
		public static final String STAFF_SERVE = "mobile/staff/staffServe";
		/** 等待中订单列表 */
		public static final String WAITING_ORDER = "mobile/staff/waitingOrder";
		/** 已完成订单 */
		public static final String ORDER_LIST = "mobile/staff/orderList";
		/** 订单详情 */
		public static final String ORDER_DETAILS = "mobile/staff/orderDetails";
		/** 选择人员页面 */
		public static final String TURN_SHIFTMAHJONG_SERVE = "mobile/staff/turnShiftMahjongServe";
		/** 修改项目 */
		public static final String CHANGE_PROJECT = "mobile/staff/changeProject";
		/** 修改项目轮牌 */
		public static final String UPDATE_SHIFTMAHJONG_SERVE = "mobile/staff/updateShiftMahjongServe";
		/** 等待中心轮牌 */
		public static final String WAITING_SHIFTMAHJONG_SERVE = "mobile/staff/waitingShiftMahjongServe";
		/** 订单明细 */
		public static final String ORDER_DETAIL = "mobile/staff/orderdetail";
		/** 所有轮牌界面 */
		public static final String ALL_SHIFTMAHJONG = "mobile/staff/allShiftMahjong";
		/** 我的轮牌界面 */
		public static final String MY_SHIFTMAHJONG = "mobile/staff/myShiftMahjong";
		/** 修改项目后合并步骤界面 */
		public static final String COMBINE_STEP = "mobile/staff/combineStep";
		/** 我的提成 */
		public static final String SELECT_COMMISSION = "mobile/staff/selectCommission";
		/** 我的订单 */
		public static final String MY_ORDER_LIST = "mobile/staff/myOrderList";
		/** 员工个人表现 by DavidLiang */
		public static final String INDIVIDUAL_PERFORMANCE = "mobile/staff/individualPerformance";
		/** 员工我的考勤 by DavidLiang */
		public static final String MY_ATTENDANCE = "mobile/staff/myAttendance";
		/** 员工在线学习资料*/
        public static final String STUDENT_VIEW = "mobile/staff/student";
	}

	/**
	 * 手机老板端
	 * 
	 * @author 王大爷
	 * @date 2016年1月15日 下午2:53:24
	 */
	class BossPage {
		/** 业绩报表 */
		public static final String BOSS_OBJECTIVE = "mobile/boss/bossobjective";
		/** 客情分析 */
		public static final String CUSTOMER_ANALYSIS = "mobile/boss/customerAnalysis";
		/** 员工绩效页面 by DavidLiang */
		public static final String VIEW_EMPLOYEE_COMMISSION = "mobile/boss/employeeCommission";
		/** 营业分析页面 */
		public static final String BUSINESS_ANALYSIS = "mobile/boss/businessAnalysis";
		/** 员工业绩详情页面 by DavidLiang */
		public static final String VIEW_EMPLOYEE_COMMISSION_DETAIL = "mobile/boss/employeeCommissionDetail";

		/** 老板模块首页 */
		public static final String HOME = "mobile/boss/home";
	}

	/** 发型设置 */
	class HairstyleDesign {
		/** 发型设置页面 */
		public static final String HAIRSTYLEDESIGN = "commodity/hairstyleDesign";
	}

	/**
	 * 项目
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:04:20
	 */
	class Project {
		/** 项目价格设置页面 */
		public static final String PROJECTSETTING = "commodity/projectSetting";
		/**
		 * 
		 */
		public static final String PROJECT_LIST = "commodity/projectInfoList";
		/** 商品项目系列*/
        public static final String CATEGORY = "commodity/category";
	}

	/**
	 * 疗程
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:04:32
	 */
	class ComboInfo {
		/** 疗程设置页面 */
		public static final String COMBOINFO = "commodity/comboInfo";
		/** 疗程设置页面*/
        public static final String COMBO_SETTING = "commodity/comboSetting";
	}

	/**
	 * 商品
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月11日 下午2:04:50
	 */
	class GoodsInfo {
		/** 商品列表页面 */
		public static final String GOODSINFO = "commodity/goodsInfoList";
		/** 设置商品详情 */
        public static final String SETTING_GOODS = "commodity/goodsSetting";
		/** 商品库存页面 */
		public static final String GOODSSTOCK = "commodity/goodsStock";
		/** 商品出货记录 */
		public static final String SHIP_MENT_RECORD = "commodity/shipMentRecord";
		/** 品牌管理页面 */
		public static final String BRAND = "commodity/goodsBrand";
		/** 商品进货页面 */
		public static final String GOODS_PURCHASE_RECORDS = "commodity/purchaseRecords";
		/** 商品设置页面(企业) */
        public static final String GOOD_SETTING = "commodity/addGoodsInfo";
        /** 商品设置页面(门店) */
        public static final String GOOD_SETTING_STORE = "commodity/storeGoodsStock";
        /** 供应商管理 */
        public static final String SUPPLIER = "commodity/supplier";
        /** 商品库存统计*/
        public static final String STOCK_SERCH = "commodity/stockSerch";
	}

	/**
	 * 供应商
	 * 
	 * @author 洪秋霞
	 * @date 2015年8月12日 下午2:40:56
	 */
	class SupplierInfo {
		/** 供应商设置页面 */
		public static final String SUPPLIERINFO = "commodity/supplierInfo";
		/** 进货记录页面 */
		public static final String PURCHASE_RECORDS = "commodity/purchaseRecords";
	}

	/**
	 * 岗位信息
	 * 
	 * @author 陈端斌
	 * @date 2015年8月4日 下午4:32:30
	 */
	class Position {
		/** 岗位信息页面 */
		public static final String POSITION = "employee/positioninfo/positioninfo";
		/** 企业岗位职位*/
        public static final String VIEW_ACCOUNT = "employee/positioninfo/account";
        /** 门店岗位职位*/
        public static final String VIEW_ACCOUNT_STORE = "employee/positioninfo/storeAccountEmps";
	}

	
    /**
     * 工资单
     * 
     * @author 骆峰
     * @date 2016年8月3日10:24:49
     */
	class Wages{
	    /**	工资单     	     */
	    public static final String VIEW_POSITION_LEVEL_WAGES = "employee/wages/wages";
	    /**	工资单     	     */
	    public static final String VIEW_POSITION_STORE_WAGES = "employee/store/wages";
	}
	/**
	 * 职位信息页面
	 * 
	 * @author chendb
	 * @date 2015年8月11日 上午10:10:38
	 */
	class Employeelevel {
		/** 职位信息页面 */
		public static final String EMPLOYEELEVEL = "employee/employeelevel/employeelevel";
		/** 业绩报表*/
        public static final String EARING_REPORT = "employee/report/earing";
        /** 提成报表*/
        public static final String AMOUNT_REPORT = "employee/report/amount";
	}

	/**
	 * 自助收银
	 * 
	 * @author 王大爷
	 * @date 2015年8月11日 上午11:21:37
	 */
	class KeepAccounts {
		/** 开支记账 */
		public static final String STOREFLOW = "keepAccounts/storeFlow";
		/** 企业轮牌 */
		public static final String ENTERPRISE_SHIFT_MAJONG = "keepAccounts/enterpriseShiftMajong";
		/** 门店轮牌 */
		public static final String STORE_SHIFT_MAJONG = "keepAccounts/storeShiftMajong";
		/** 轮职排班 */
		public static final String SHIFT_MAHJONG = "keepAccounts/shiftMahjong";
		/** 开卡充值 */
		public static final String OPEN_CARD = "keepAccounts/openCard";
		/** 手工收银 */
		public static final String MANUALLY_OPEN_ORDER = "keepAccounts/manuallyOpenOrder";
		/**类别管理*/
        public static final String ADD_INITILIZE_TYPE = "keepAccounts/type";
        /**开支记账*/
        public static final String INITIALIZE = "keepAccounts/initillize";
        /** 无纸化开单*/
        public static final String NO_PAPER_OPEN_ORDER = "keepAccounts/noPaperOpenOrder";
	}

	/**
	 * 微信模块
	 * 
	 * @author 高国藩
	 * @date 2015年8月7日 上午10:03:21
	 *
	 */
	class Wechat {
		/** 菜单页面 */
		public static final String MENU = "wechat/menu";
		/** 新增图文消息页面 */
		public static final String ARTIC_MANAGER = "wechat/article-manage";
		/** 展示图文消息 */
		public static final String SHOW_ITEMS = "wechat/items-manage";
		/** 修改摸一个图文消息，展示其中一个 */
		public static final String CHATE_ITME = "wechat/update-article-manage";
		/** 图文消息发送 */
		public static final String SEND_ITEMS = "wechat/send-items2";
		/** 图文消息统计 */
		public static final String ITEMS_STATUS = "wechat/items-msg-status";
		/** 图文消息设置页面 */
		public static final String VIEW_AUTO_REPLY = "wechat/auto-reply";
		/** 门店菜单设置页面 */
		public static final String STORE_MENU = "wechat/store-menu";
	}

	/**
	 * 人员目标
	 * 
	 * @author chendb
	 * @date 2015年8月17日 下午3:21:41
	 */
	class Objective {
		/** 职位信息页面 */
		public static final String OBJECTIVE = "employee/objective/objective";
	}

	/**
	 * 优惠券
	 * 
	 * @author 高国藩
	 * @date 2015年8月18日 上午11:40:55
	 */
	class Coupon {
		/** 优惠券展示页面 */
		public static final String VIEW_COUPON = "coupon/coupon-list";
		
		/** 企业优惠券展示页面 */
        public static final String VIEW_COUPON_LIST = "coupon/coupon";
        
        /** 门店优惠券展示页面 */
        public static final String VIEW_COUPON_STORE = "coupon/coupon-store";
		
	}

	/**
	 * 自助收银
	 * 
	 * @author luhw
	 * @date 2015年10月21日 下午15:27:49
	 */
	class SelfCashier {
		/** 自助收银 */
		public static final String VIEW_SELF_CASHIER = "cashier/payment";
		
		/** 结账 */
		public static final String VIEW_CHECKOUT_ORDER = "cashier/checkoutOrder";

		/** 预约列表 */
		public static final String APPOINT_LIST = "cashier/appointList";
	}

	/** 订单流水 */
	class DayBook {
		/** 订单流水查询页面 */
		public static final String VIEW_DAYBOOK_INDEX = "daybook/view/index";

		/** 订单流水查询 */
		public static final String ACTION_DAYBOOK_LIST = "daybook/action/list";

	}

	/**
	 * 登陆
	 * 
	 * @author 高国藩
	 * @date 2015年9月20日 上午11:21:38
	 */
	class Login {
	}

	/**
	 * 门店
	 * 
	 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
	 *         2015年11月24日
	 */
	class Store {

		/**
		 *
		 */
		public static final String STORE_APPLY = "mobile/store/apply";

	}

	/**
	 *
	 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
	 *         2015年11月26日
	 */
	class Agent {

		/**
		 *
		 */
		public static final String AGENT_APPLY = "mobile/agent/apply";

	}

	/**
	 *
	 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
	 *         2015年11月26日
	 */
	class StoreDetail {

		/**
		 *
		 */
		public static final String SINGLE_STORE = "mobile/store/single";

		/**
		 *
		 */
		public static final String CHAIN_HQ_STORE = "mobile/store/chain_hq";

		/**
		 *
		 */
		public static final String CHAIN_STORE = "mobile/store/chain";

		/**
		 *
		 */
		public static final String SINGLE_STORE_INFO = "mobile/store/single_info";

		/**
		 *
		 */
		public static final String CHAIN_HQ_STORE_INFO = "mobile/store/chain_hq_info";

		/**
		 *
		 */
		public static final String CHAIN_STORE_INFO = "mobile/store/chain_info";

		/**
		 *
		 */
		public static final String CHAIN_STORE_CHAINS = "mobile/store/chain_hq_chains";

		/**
		 *
		 */
		public static final String STORE_OPEN_SYS = "mobile/store/open_sys";

		/**
		*
		*/
		public static final String STORE_CHARGE_SYS = "mobile/store/charge_sys";

		/**
		*
		*/
		public static final String STORE_CHARGE_INFO = "mobile/store/charge_info";
	}

	/**
	 *
	 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
	 *         2015年11月26日
	 */
	class AgentDetail {
		/** 渠道个人账户页面(已审核) */
		public static final String INDEX = "mobile/agent/index";

		/** 渠道个人账户页面(审核中) */
		public static final String APPLY_INFO = "mobile/agent/applyInfo";

		/**
		 *
		 */
		public static final String INFO = "mobile/agent/info";

		/**
		 *
		 */
		public static final String NEW_STORE_SELF = "mobile/agent/new_store_self";

		/**
		 *
		 */
		public static final String NEW_STORE_OTHER = "mobile/agent/new_store_other";

		/**
		 *
		 */
		public static final String STORE_NORMAL = "mobile/agent/store_normal";

		/**
		 *
		 */
		public static final String STORE_RENEW = "mobile/agent/store_renew";

		/**
		 *
		 */
		public static final String STORE_OVER = "mobile/agent/store_over";

		/**
		 *
		 */
		public static final String STORE_MY_RECOMMEND = "mobile/agent/store_my_recommend";

		/**
		 *
		 */
		public static final String AGENT_MY_RECOMMEND = "mobile/agent/agent_my_recommend";

		/**
		 *
		 */
		public static final String STORE_RECOMMEMND_TO_ME = "mobile/agent/store_recommend_me";

		/**
		 *
		 */
		public static final String INCOME = "mobile/agent/income";

		/**
		 *
		 */
		public static final String STORE_REGION_RANK = "mobile/agent/store_region_rank";

	}

	/**
	 * 员工出勤记录
	 * 
	 * @author DavidLiang
	 *
	 */
	class AttendanceRecord {
		/** 员工考勤首页 */
		public static final String HOME = "employee/attendance/attendance";
	}

	/**
	 * 员工奖惩
	 * 
	 * @author DavidLiang
	 *
	 */
	class EmployeeReward {
		/** 员工奖惩首页 */
		public static final String HOME = "employee/rewards/rewards";
	}

	/**
	 * 业务员模块
	 * 
	 * @author DavidLiang
	 */
	class Salesman {
		/** 业务员列表页面 */
		public static final String VIEW_SALESMAN_LIST = "/mobile/salesman/salesmanList";
		/** 业务员新增页面 */
		public static final String VIEW_SALESMAN_TOADD = "/mobile/salesman/salesmanAdd";
		/** 业务员账户 */
		public static final String VIEW_SALESMAN_ACCOUNT = "/mobile/salesman/salesmanAccount";
		/** 业务员推荐状态正常门店页面(试运营, 正常运营) */
		public static final String VIEW_SALESMAN_STORE_NORMAL = "/mobile/salesman/salesmanRecommendStoreNormal";
		/** 业务员推荐状态续费提醒门店页面(续费提醒) */
		public static final String VIEW_SALESMAN_STORE_RENEW = "/mobile/salesman/salesmanRecommendStoreRenew";
		/** 业务员推荐状态已过期门店页面(已过期) */
		public static final String VIEW_SALESMAN_STORE_OVER = "/mobile/salesman/salesmanRecommendStoreOver";
		/** 业务员详细信息 */
		public static final String VIEW_SALESMAN_INFO = "/mobile/salesman/salesmanInfo";
	}

	/**
	 * 营业分析模块
	 * 
	 * @author 小高
	 * @date Jan 13, 2016 7:47:45 PM
	 */
	class BusinessAnalysis {
		/** 客情分析 */
		public static final String CUSTOMER = "businessAnalysis/customer";
		/** 预约分析 */
		public static final String APPOINTMENT = "businessAnalysis/appointment";
		/** 工资单 */
		public static final String PAYROLL = "businessAnalysis/payroll";
		/** 员工分析 */
		public static final String EMPLOYEE = "businessAnalysis/employee";
		/** 营业分析 */
		public static final String BUSINESS = "businessAnalysis/business";
	}
	
	/**
	 * 方案
	* @author 老王
	* @date 2016年7月27日 下午2:19:29
	 */
	class Programme {
		/** 大客户分析*/
		public static final String VIEW_BIG_CUSTOMER_ANALYSIS = "programme/bigCustomerAnalysis";
	}

	/**
	 * 友宝商城
	 * 
	 * @author 小高
	 * @date Jan 28, 2016 5:09:16 PM
	 */
	class Ubox {
		/** 商品列表页面 */
		public static final String GOODS_LIST = "ubox/goodsList";
		/** 商品编辑页面 */
		public static final String GOODS_EDIT = "ubox/editGoods";
	}
	
	/**
     * 进销存
     * @author 小高
     * @date Jan 28, 2016 5:09:16 PM
     */
    class Stock {
        /**企业库存管理*/
        public static final String VIEW_STOCK = "commodity/viewStock";
        /**门店库存管理*/
        public static final String VIEW_STORE_STOCK = "commodity/storeViewStock";
    }
    
    /**
     * 服务计划
    * @author 高国藩
    * @date 2016年6月30日 下午4:44:57
     */
    class ServicePlans {
        /**计划页面*/
        public static final String VIEW_SERVICE_PLAN = "servicePlan/servicePlanView";
        /**模板页面*/
        public static final String VIEW_SERVICE_TEMP = "servicePlan/servicePlanTemp";
    }
}
