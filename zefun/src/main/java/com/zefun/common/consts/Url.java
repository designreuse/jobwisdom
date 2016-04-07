package com.zefun.common.consts;

/**
 * 接口地址常量类，定义时使用根目录下的绝对地址
 *
 * @author 张进军
 * @date Aug 4, 2015 9:20:30 AM
 */
public interface Url {

    /** 权限管理 */
    class Authority {
        /** 角色权限管理 */
        public static final String AUTHORITY_VIEW = "authority/view";
        /** 查询权限 */
        public static final String AUTHORITY_SELECT = "authority/select";
        /** 绑定权限 */
        public static final String AUTHORITY_SAVE = "authority/save";
        /** 新增权限 */
        public static final String SAVE_AUTHORITY = "authority/init";
    }

    /** 人脸识别模块 */
    class Face {
        /** 添加face */
        public static final String ACTION_ADD = "face/action/add";
        /** 显示添加face的页面 */
        public static final String VIEW_ADD = "face/view/add";
        /** 根据faceset进行匹配搜索 */
        public static final String ACTION_SEARCH = "face/action/search";
        /** 显示搜索face的页面 */
        public static final String VIEW_SEARCH = "face/view/search";
    }

    /** 系统设置模块 */
    class SystemSetting {
        /** 访问个人设置页面 */
        public static final String VIEW_PERSON_SETTING = "system/view/person";
        /** 个人设置操作 */
        public static final String ACTION_PERSON_SETTING = "system/action/person";
        /** 修改账户密码 */
        public static final String ACTION_UPDATE_PASSWORD = "system/action/updatePwd";
        /**访问门店基础设置页面*/
        public static final String VIEW_BASE_SETTING = "system/view/baseSetting";
        /**门店基础设置操作*/
        public static final String ACTION_BASE_SETTING = "system/action/baseSetting";
        /**分店列表页面*/
        public static final String VIEW_BRANCH_LIST = "system/action/branchList";
        /**门店微信管理页面*/
        public static final String VIEW_STORE_WECHAT = "system/view/storeWechat";
        /**门店微信管理操作*/
        public static final String ACTION_STORE_WECHAT = "system/action/storeWechat";
        /**门店使用状况查询*/
        public static final String VIEW_STORE_USAGE = "system/view/storeUsage";
        /**门店测试数据清除*/
        public static final String ACTION_CLEAN_DATA = "system/action/cleanData";
        /**门店分享机制页面*/
        public static final String VIEW_SHARE = "system/view/share";
        /**保存门店分享机制*/
        public static final String ACTION_SAVE_SHARE = "action/save/share";
    }

    /**
     * 预约管理
     * @author 张进军
     * @date Nov 23, 2015 10:10:37 PM
     */
    class AppointManage {
        /** 查看预约列表 */
        public static final String VIEW_APPOINT_LIST = "appoint/view/list";
        /** 分页查询预约列表 */
        public static final String ACTION_APPOINT_LIST = "appoint/action/list";
        /** 取消预约 */
        public static final String ACTION_APPOINT_CANCEL = "appoint/action/cancel";
        /** 根据部门查询可预约员工 by DavidLiang */
        public static final String ACTION_APPOINT_FIND_EMPLOYEE_BY_DEPT = "appoint/action/findEmployeeByDept";
        /** 根据员工职位查询可预约项目系列集 by DavidLiang */
        public static final String ACTION_PROJECT_CATEGORY_FIND_BY_LEVEL = "appoint/action/findProjectCategoryByEmployeeLevel";
        /** 根据项目系列id查询项目 by DavidLiang */
        public static final String ACTION_PROJECT_BY_CATEGORY_ID = "appoint/action/findProjectByCategoryId";
        /** 新增会员预约项目 by DavidLiang */
        public static final String ACTION_ADD_APPOINT_PROJECT = "appoint/action/addAppointProject";
        /** 根据日期(yyyy-MM-dd)查询会员预约 by DavidLiang */
        public static final String ACTION_FIND_MEMBER_APPOINT_BY_DATE = "appoint/action/findMemberAppointByDate";
        /** web端取消预约 by DavidLiang */
        public static final String ACTION_CANCEL_APPOINTMENT = "appoint/action/cancelAppointment";

    }

    /** 会员等级模块 */
    class MemberLevel {
        /** 添加等级 */
        public static final String ACTION_ADD = "memberLevel/action/add";
        /** 显示会员等级列表的页面 */
        public static final String VIEW_LIST = "memberLevel/view/list";
        /** 分页查询会员等级信息 */
        public static final String ACTION_LIST = "memberLevel/action/list";
        /** 查询会员等级信息 */
        public static final String ACTION_INFO = "memberLevel/action/info";
        /** 删除会员等级信息 */
        public static final String ACTION_DELETE = "memberLevel/action/delete";
        /** 设置默认等级 */
        public static final String ACTION_DEFAULT = "memberLevel/action/default";
        /** 会员卡导入excle */
        public static final String IMPORTEXCLE = "memberLevel/action/importexcle";

    }

    /** 优惠券模块 */
    class Coupon {
        /** 进入优惠券管理页面 */
        public static final String VIEW_COUPONS = "view/coupons";
        /** 新增优惠卷 */
        public static final String ADD_COUPONS = "coupons/add";
        /** 查询套餐商品项目信息 */
        public static final String LIST_USE_COUPONS = "serch/coupons/use";
        /** 发布优惠券 */
        public static final String UPDATE_COUPON_USE = "update/coupons/use";
        /** 下载优惠券 */
        public static final String UPDATE_COUPON_NO_USE = "update/coupons/no/use";
        /** 发送优惠券 */
        public static final String SEND_COUPONS = "coupon/send/coupons";
        /** 删除优惠券 */
        public static final String DELETE_COUPONS = "action/coupons/delete";
        /** 查询分页 */
        public static final String VIEW_COUPONS_BY_PAGE = "view/coupons/by/page";

    }

    /** 会员模块 */
    class Member {
        /** 添加等级 */
        public static final String MEMBER_VIEW_LIST = "member/view/list";
        /** 新增筛选器 */
        public static final String MEMBER_SCREEN_ADD = "member/add/memberScreening";
        /** 会员分组 */
        public static final String VIEW_CENSUS_LIST = "member/view/census/list";
        /** 根据筛选器确定人员 */
        public static final String VIEW_LIST_BY_SCREEN = "member/view/census/member/list";
        /** 通过预设的条件进行查询会员数据 */
        public static final String SERCH_MEMBER_BY_TREM = "member/serch/by/screen";
        /** 校验会员是否存在 */
        public static final String SELECT_MEMBER_BYPHONE = "member/action/selectMemberByPhone";
        /** 根据会员信息标识查询会员信息及账户信息 */
        public static final String SELECTBY_MEMBERDTO = "member/action/selectByMemberDto";
        /** 根据会员标识分页查询资金流水 */
        public static final String SELECT_MONEYFLOW = "member/action/selectMoneyFlow";
        /** 删除会员筛选分组 */
        public static final String DELETE_CENSUS = "member/delete/census";
        /** 根据姓名或者手机号码进行查询会员数据 */
        public static final String SERCH_MEMBER_BY_CONTENT = "member/serch/by/nameOrPhone";
        /** 会员数据导入 */
        public static final String IMPORTEXCLE = "member/action/importexcle";
        /** 会员错误数据展示页面*/
        public static final String VIEW_ERROR_MEMBER = "member/view/error/member/info";
        /** 会员错误数据删除操作 */
        public static final String ACTION_ERROR_MEMBER_DELETE = "member/view/error/member/delete";
        /** 总店会员页面*/
        public static final String VIEW_BASE_MEMBER = "member/base/view/list";
        /** 会员异常数据导出*/
        public static final String DOWN_ERROR_MEMBER = "member/download/error/member";
        /** 修改会员资料*/
        public static final String ACTION_UPDATE_MEMBER = "member/update/member/info";
        /** 会员余额迁移*/
        public static final String MEMBER_BALAND_MOVE = "member/move/baland/info";
        /** 会员套餐迁移*/
        public static final String MEMBER_COMBO_MOVE = "/member/move/combo/info";
        /** 会员套餐迁移全部*/
        public static final String MEMBER_COMBO_MOVE_ALL = "/member/move/combo/info/all";
        /** 会员删除*/
        public static final String MEMBER_DELETED = "member/view/member/delete";
        /** 盛传疗程卡分页查询*/
        public static final String RUMORS_COURSE = "member/view/rumors";
        /** 查询门店内所有会员电话和名称*/
        public static final String SELECT_STORE_MEMBERINFO = "member/action/selectStoreMemberInfo";
        

    }

    /** 七牛图片处理模块 */
    class Qiniu {
        /** 获取token */
        public static final String UPTOKEN = "qiniu/uptoken";
        /** 抓取网络资源上传到七牛 */
        public static final String FETCH = "qiniu/fetch";
        /** 文字转语音 */
        public static final String TEXT_TO_VOICE = "qiniu/textToVoice";
        /** 将base64上传至七牛 */
        public static final String BASE64 = "qiniu/base64";
    }

    /** 发型设置模块 */
    class HairstyleDesign {
        /** 进入发型设置页面 */
        public static final String TO_HAIRSTYLEDESIGN = "hairstyleDesign/view/toHairstyleDesign";
        /** 保存发型类别 */
        public static final String SAVE_HAIRSTYLECATEGORY = "hairstyleDesign/saveHairstyleCategory";
        /** 修改发型类别 */
        public static final String EDIT_HAIRSTYLECATEGORY = "hairstyleDesign/editHairstyleCategory";
        /** 删除发型类别 */
        public static final String DELETE_HAIRSTYLECATEGORY = "hairstyleDesign/deleteHairstyleCategory";
        /** 根据id查询发型 */
        public static final String QUERY_HAIRSTYLEDESIGNBYID = "hairstyleDesign/queryHairstyleDesignById";
        /** 保存发型设置 */
        public static final String SAVE_HAIRSTYLEDESIGN = "hairstyleDesign/saveHairstyleDesign";
    }

    /** 项目模块 */
    class Project {
        /** 进入项目设置页面 */
        public static final String PROJECT_LIST = "project/view/projectList";
        /** 分页查询项目信息 */
        public static final String ACTION_LIST = "project/action/list";
        /** 根据部门Id查询轮牌列表 */
        public static final String QUERY_SHIFTMAHBY_DEPID = "project/queryShiftMahByDepId";
        /** 根据选择的轮牌标识查询对应的会员等级 */
        public static final String QUERY_SHIFTMAH_BYLEVELID = "project/queryShiftMahByLevelId";
        /** 根据项目id查询项目 */
        public static final String QUERY_PROJECTINFO_BYID = "project/queryProjectInfoById";
        /** 添加项目类别 */
        public static final String SAVE_PROJECT_CATEGORY = "project/saveProjectCategory";
        /** 删除项目类别 */
        public static final String DELETE_PROJECT_CATEGORY = "project/deleteProjectCategory";
        /** 编辑项目类别 */
        public static final String EDIT_PROJECT_CATEGORY = "project/editProjectCategory";
        /** 保存项目 */
        public static final String SAVE_PROJECT = "project/saveProject";
        /** 编辑项目 */
        public static final String EDIT_PROJECT_INFO = "project/editProjectInfo";
        /** 删除项目 */
        public static final String DELETE_PROJECT = "project/deleteProject";
        /** 是否微信显示 */
        public static final String IS_WECHATSELL = "project/isWechatSell";
        /** 是否禁用 */
        public static final String IS_DISABLE = "project/isDisable";
        /** 批量新增类别 */
        public static final String SAVE_PROJECT_CATEGORY_LIST = "project/saveProjectCategorys";
        /** 批量新增项目 */
        public static final String SAVE_PROJECT_LIST = "project/saveProjects";
        /** 上传文件-项目 */
        public static final String UPLOAD_PROJECT = "project/upload";
    }

    /** 套餐模块 */
    class ComboInfo {
        /** 进入套餐页面 */
        public static final String COMBOINFO_LIST = "comboInfo/view/comboInfoList";
        /** 分页查询套餐信息 */
        public static final String ACTION_LIST = "comboInfo/action/list";
        /** 保存套餐 */
        public static final String SAVE_COMBOINFO = "comboInfo/saveComboInfo";
        /** 批量保存套餐 */
        public static final String SAVE_COMBOINFO_LIST = "comboInfo/saveComboInfos";
        /** 根据套餐id查询 */
        public static final String QUERY_COMBOINFO_BYID = "comboInfo/queryComboInfoById";
        /** 删除套餐 */
        public static final String DELETE_COMBOINFO = "comboInfo/deleteComboInfo";
        /** 根据项目id查询提成职位列表 */
        public static final String QUERY_COMMISSIONBY_PROJECTID = "comboInfo/queryCommissionByProjectId";
        /** 根据商品id查询提成职位列表 */
        public static final String QUERY_COMMISSIONBY_GOODSID = "comboInfo/queryCommissionByGoodsId";
        /** 是否微信显示 */
        public static final String IS_WECHATSELL = "comboInfo/isWechatSell";
        /** 是否禁用 */
        public static final String IS_DISABLE = "comboInfo/isDisable";
    }

    /** 商品模块 */
    class GoodsInfo {
        /** 进入商品页面 */
        public static final String GOODSINFO_LIST = "goodsInfo/view/goodsInfoList";
        /** 进入商品库存页面 */
        public static final String GOODSSTOCK_LIST = "goodsInfo/view/goodsStockList";
        /** 分页查询商品信息 */
        public static final String ACTION_LIST = "goodsInfo/action/list";
        /** 保存商品类别 */
        public static final String SAVE_GOODS_CATEGORY = "goodsInfo/saveGoodsCategory";
        /** 批量保存商品类别 */
        public static final String SAVE_GOODS_CATEGORY_LIST = "goodsInfo/saveGoodsCategorys";
        /** 编辑商品类别 */
        public static final String EDIT_GOODS_CATEGORY = "/goodsInfo/editGoodsCategory";
        /** 删除商品类别 */
        public static final String DELETE_GOODS_CATEGORY = "/goodsInfo/deleteGoodsCategory";
        /** 保存品牌 */
        public static final String SAVE_GOODS_BRAND = "goodsInfo/saveGoodsBrand";
        /** 编辑品牌 */
        public static final String EDIT_GOODS_BRAND = "/goodsInfo/editGoodsBrand";
        /** 删除品牌 */
        public static final String DELETE_GOODS_BRAND = "/goodsInfo/deleteGoodsBrand";
        /** 保存商品 */
        public static final String SAVE_GOODS_INFO = "goodsInfo/saveGoodsInfo";
        /** 根据id查询商品 */
        public static final String QUERY_GOODSINFO_BYID = "goodsInfo/queryGoodsInfoById";
        /** 删除商品 */
        public static final String DELETE_GOODSINFO = "goodsInfo/deleteGoodsInfo";
        /** 30天销售量 */
        public static final String QUERY_BYGOODSSALE = "goodsInfo/queryByGoodsSale";
        /** 根据部门查询商品类别 */
        public static final String SELECT_CATEGORY_BY_DEPT = "goodsInfo/selectCategory/by/dept";
        /** 批量保存商品 */
        public static final String SAVE_GOODS_INFO_LIST = "goodsInfo/saveGoodsInfos";
        /** 出货记录页面 */
        public static final String VIEW_SHIPMENT_RECORD = "goodsInfo/view/shipment/record";
        /** 新增出货记录 */
        public static final String SAVE_SHIPMENT_RECORD = "goodsInfo/save/shipment/record";
        /** 出货记录分页查询 */
        public static final String SERCH_SHIPMENT_RECORD = "goodsInfo/serch/shipment/record";
        /** 品牌管理页面 */
        public static final String VIEW_BRAND = "goodsInfo/view/brand";
        /** 保存品牌 */
        public static final String SAVE_BRAND = "goodsInfo/save/brand";
        /** 分页查询品牌 */
        public static final String SERCH_BRAND = "goodsInfo/serch/brand";
        /** 商品进货管理页面 */
        public static final String GOODS_PURCHASE_RECORDS = "goodsInfo/purchase/records";
    }

    /** 供应商模块 */
    class SupplierInfo {
        /** 进入供应商页面 */
        public static final String SUPPLIERINFO_LIST = "supplierInfo/view/supplierInfoList";
        /** 分页查询供应商信息 */
        public static final String ACTION_LIST = "supplierInfo/action/list";
        /** 保存供应商信息 */
        public static final String SAVE_SUPPLIERINFO = "supplierInfo/saveSupplierInfo";
        /** 根据id查询供应商信息 */
        public static final String QUERY_SUPPLIERINFO_BYID = "supplierInfo/querySupplierInfoById";
        /** 编辑供应商信息 */
        public static final String EDIT_SUPPLIERINFO = "supplierInfo/editSupplierInfo";
        /** 删除供应商信息 */
        public static final String DELETE_SUPPLIERINFO = "supplierInfo/deleteSupplierInfo";
    }

    /** 进货记录模块 */
    class GoodsPurchaseRecord {
        /** 分页查询进货记录 */
        public static final String ACTION_LIST = "goodsPurchaseRecord/action/list";
        /** 保存进货记录 */
        public static final String SAVE_PURCHASERECORDS = "goodsPurchaseRecord/savePurchaseRecords";
    }

    /** 岗位信息模块 */
    class Position {
        /** 显示岗位信息页面 */
        public static final String VIEW_QUERY = "position/view/positioninfo";
        /** 查询职位列表信息 */
        public static final String ACTION_LIST = "position/action/list";
        /** 新增岗位信息 */
        public static final String ADD = "position/action/add";
        /** 修改岗位信息 */
        public static final String UPDATE = "position/action/update";
        /** 删除岗位信息 */
        public static final String DELETE = "position/action/delete";
        /** 获取岗位下拉框信息 */
        public static final String QUERYPOSITION = "position/action/queryposition";
        /** 获取岗位详情 */
        public static final String POSITIONDETAIL = "position/action/positiondetail";

    }

    /** 职位信息模块 */
    class EmployeeLevel {
        /** 显示职位信息页面 */
        public static final String VIEW_QUERY = "employeelevel/view/employeelevelinfo";
        /** 新增职位信息 */
        public static final String ADD = "employeelevel/action/add";
        /** 修改职位信息 */
        public static final String UPDATE = "employeelevel/action/update";
        /** 查询职位信息 */
        // public static final String QUERY = "employeelevel/query";
        /** 删除职位信息 */
        public static final String DELETE = "employeelevel/action/delete";
        /** 下拉框查询职位 */
        public static final String QUERTLEVEL = "employeelevel/action/querylevel";
        /** 查询职位列表信息 */
        public static final String ACTION_LIST = "employeelevel/action/list";
        /** 查询职位详情 */
        public static final String LEVELDETAIL = "employeelevel/action/leveldetail";
        /** 根据岗位信息获取职位 */
        public static final String QUERTLEVELINFO = "employeelevel/action/querylevelInfo";
        /** 根据职位 获取人员 */
        public static final String GETLEVELEMPLOYEE = "employeelevel/action/getlevelemployee";

    }

    /** 收银记账 */
    class KeepAccounts {
        /* 开卡记账 */
        /** 初始化开卡记账页面 */
        public static final String INITIALIZESTOREFLOW = "KeepAccounts/initializeStoreFlow";
        /** 读取上传excle */
        public static final String READEXCLE = "KeepAccounts/readExcle";
        /** 导出excle */
        public static final String DOWNLOAD_EXCLE = "KeepAccounts/downloadExcle";
        /** 分页 */
        public static final String STOREFLOW_LIST = "KeepAccounts/storeFlowList";
        /** 新增开支记账 */
        public static final String ADD_STOREFLOW = "KeepAccounts/addStoreFlow";
        /** 删除开支记录 */
        public static final String DELETE_STOREFLOW = "KeepAccounts/deleteStoreFlow";
        /** 修改开支记账 */
        public static final String UPDATE_STOREFLOW = "KeepAccounts/updateStoreFlow";
        /** 动态生成项目类别 */
        public static final String TREND_CODELIBRARY = "KeepAccounts/trendCodeLibrary";

        /* 轮值排班 */
        /** 初始化轮值排班界面 */
        public static final String INITIALIZE_SHIFT_MAHJONG = "KeepAccounts/initializeShiftMahjong";
        /** 设置轮牌 */
        public static final String SET_SHIFTMAHJONG = "KeepAccounts/setShiftMahjong";
        /** 初始化轮牌model */
        public static final String INITIALIZE_MODEL = "KeepAccounts/initializeModel";
        /** 轮牌上移 */
        public static final String UPWARD_IMG = "KeepAccounts/upwardIMG";
        /** 轮牌下移 */
        public static final String NEXT_IMG = "KeepAccounts/nextIMG";
        /** 轮牌置顶*/
        public static final String TO_TOP_IMG = "KeepAccounts/toTopIMG";
        /** 轮牌置低*/
        public static final String TO_END_IMG = "KeepAccounts/toEndIMG";
        /** 新增或修改轮牌信息 */
        public static final String ADDUPDATE_SHIFTMAHJONG = "KeepAccounts/addUpdateShiftMahjong";
        /** 删除轮牌信息 */
        public static final String DELETE_SHIFTMAHJONG = "KeepAccounts/deleteShiftMahjong";
        /** 根据部门标识查询轮牌信息 */
        public static final String SELECT_SHIFTMAHJONG = "staff/action/selectshiftMahjong";
        /** 修改轮牌员工状态 */
        public static final String UPDATE_STATE = "KeepAccounts/updateState";

        /** 初始化开卡充值页面 */
        public static final String INITIALIZE_OPENCARD = "KeepAccounts/initializeOpenCard";
        /** 开卡 */
        public static final String ADD_MEMBERINFO = "KeepAccounts/addMemberInfo";
        /** 升级会员 */
        public static final String UPGRADE_MEMBERINFO = "KeepAccounts/upgradeMemberInfo";
        /** 充值 */
        public static final String RECHARGE_MEMBERINFO = "KeepAccounts/rechargeMemberInfo";
        /** 还款*/
        public static final String REFUND_MEMBERINFO = "KeepAccounts/refundMemberinfo";
        /** 转账 */
        public static final String CHECKOUT_ACCOUNT = "KeepAccounts/checkoutAccount";
        /** 会员优惠赠送 */
        public static final String PRESENT_GIFT = "KeepAccounts/presentGift";
        /** 根据会员标识查询转出记录 */
        public static final String SELECT_ZCDETAIL = "KeepAccounts/selectZcDetail";
        /** 根据会员标识查询充值记录 */
        public static final String SELECT_CZDETAIL = "KeepAccounts/selectCzDetail";
        /** 校验手机号码是否存在 */
        public static final String IS_CHECK_ACCOUNT = "KeepAccounts/isCheckAccount";

        /** 初始化手工开单界面 */
        public static final String INITIALIZE_MANUALLY_OPEN_ORDER = "KeepAccounts/initializeManuallyOpenOrder";
        /** 根据项目标识查询想轮牌信息及步骤对应员工 */
        public static final String SELECT_PROJECT_SHIFT_STEP = "KeepAccounts/selectProjectShiftStep";
        /** 手动开单 */
        public static final String MANUALLY_OPEN_ORDER_SAVE = "KeepAccounts/manuallyOpenOrderSave";
        /** 刷新轮牌*/
        public static final String REFRESH_SHIFTMAHJONG_EMPLOYEE = "staff/action/refreshShiftMahjongEmployee";
    }

    /** 微信接口 */
    class Wechat {
        /** 微信验证及消息回复 */
        public static final String CHART = "coreServlet";
        /** 微信上传获得永久thumb_media_id */
        public static final String GETTHUMBID = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
        /** 微信上传获得上传图片URL */
        public static final String GETPICURL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
        /** 根据openId群发消息 */
        public static final String SEND_MESSAGEBYID = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
        /** 上传图文素材获得medaiId */
        public static final String GETMEDIAID = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
        /** 菜单创建-微信接口 */
        public static final String CREATE_NORMAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        /** 个性化菜单创建-微信接口 */
        public static final String CREATE_CONDITIONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
        /** 菜单删除-微信接口 */
        public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
        /** 创建分组-微信接口 */
        public static final String CREATE_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
        /** 移动用户分组-微信接口 */
        public static final String MOVE_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
        /** 获取accessToken */
        public static final String GET_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?gra"
                + "nt_type=client_credential&appid=APPID&secret=APPSECRET";
        /** 获取图文消息列表 */
        public static final String GET_THUMB = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
        /** 微信更新图文消息 */
        public static final String UPDATE_WECHAT = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
        /** 微信删除图文消息 */
        public static final String DELETE_WECHAT = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
        /** 微信发送模板消息 */
        public static final String SEND_COUPON = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        /** 微信图文消息预览*/
        public static final String ITEM_PRE_VIEW = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";

        /** 微信七牛上传图片存储图片,用于图片库 */
        public static final String QINNIU_UPLOAD = "wechat/qiniu/upload";
        /** 新增图文消息 */
        public static final String UPLOADNEWS = "uploadnews";
        /** 菜单新增 */
        public static final String ADD_MENU = "add/menu";
        /** 获得菜单 */
        public static final String GETMENU = "getMenu";
        /** 跳转至菜单设置页面 */
        public static final String VIEW_LIST_MENU = "wechat/menu";
        /** 删除菜单 */
        public static final String DELETE_MENU = "delete/menu";
        /** 店铺菜单设置页面 */
        public static final String VIEW_LIST_STORE_MENU = "wechat/store/menu";
        /** 改变菜单链接地址 */
        public static final String SET_MENU_URL = "set/menu/url";
        /** 获得单一菜单元素 */
        public static final String GET_ONE_MENU = "get/one/menu";
        /** 根据openId发送图文消息 */
        public static final String SEND_MESSAGE_ITEM = "send/item/openId";
        /** 根据openId发送文本消息 */
        public static final String SEND_MESSAGE_TEXT = "send/text/openId";

        /** 微信授权回调接口 */
        public static final String CALL_BACK = "/wechat/callback/{openidKey}";
        /** 发起微信支付 */
        public static final String CREATE_PAY = "/wechat/pay/create";
        /** 微信支付回调接口 */
        public static final String CALL_BACK_PAY = "/wechat/pay/callback";
        /** 提取微信素材资源 */
        public static final String FETCH_MEDIA = "wechat/fetch/media";

        /** 为了textarea中的图片上传做的地址 */
        public static final String UPLOAD_AREA = "wechat/upload/img/textarea";
        /** 进入新增图文消息页面 */
        public static final String ARTIC_MANAGER = "/artic/manager";
        /** 图文消息展示已新增的图文消息 */
        public static final String ITEMS_MANAGE = "wechat/items/manage";
        /** 跳转某个图文消息修改页面，并展示其信息 */
        public static final String SEND_UPDATE_ITEM = "wechat/send/update/item";
        /** 更新thumbId */
        public static final String CHANGE_THUMB_ID = "wechat/update/thumb/id";
        /** 更新图文消息，接口，开始实际修改图文消息 */
        public static final String UPDATE_ITEM = "wechat/items/update";
        /** 删除图文消息 */
        public static final String DELETE_ITEM = "wechat/items/delete";
        /** 用于预览 */
        public static final String GET_ITEM_BY_MEDIA_ID = "wechat/get/item/by/mediaId";
        /** 上传图片,生成图片库 */
        public static final String UPDATE_IMG = "wechat/update/img";
        /** 删除图片 */
        public static final String DELETE_IMG = "wechat/delete/img";
        /** 进入图文消息发送页面 */
        public static final String SEND_ITEMS = "wechat/send/items";
        /** 进入图文发送统计 */
        public static final String VIEW_ITEMS_STATUS = "wechat/items/status";
        /** 进入自动回复设置页面 */
        public static final String VIEW_AUTO_REPLY = "wechat/view/auto/reply";
        /** 设置关注回复内容 */
        public static final String SET_FOLLOW_REPLY = "wechat/set/follow/repy";
        /** 设置消息回复内容 */
        public static final String SET_TEXT_REPLY = "wechat/set/text/repy";
        /** 删除消息回复 */
        public static final String DELETE_MSG_AUTO = "wechat/delete/auto/repy";
        /** 更新开发商图文消息 */
        public static final String UPLOAD_ITEMS = "wechat/upload/items";
        /** 阅读原文微信授权 */
        public static final String WARRANT = "wechat/shouquan";
        /** 自身通过验证而获得优惠券 */
        public static final String SELF_GET_COUPONS = "wechat/coupon/get/by/self";
        /** 复制菜单信息 */
        public static final String COPY_MENUS = "wechat/copy/menus";
        /** 删除复制菜单 */
        public static final String DELETE_COPY_MENUS = "wechat/delete/copy/menus";
        /** 复制单个图文 */
        public static final String COPY_ITEMS_ZHIFANG = "wechat/items/copy/zhifang";
        /** 查询出本月次数为0的会员 */
        public static final String CHECK_WECHAT_COUNT = "wechat/check/member/count";
        /** 根据关键字查询图文消息 */
        public static final String ACTION_SERCH_ITEMS = "wechat/serch/items";
        /** 点赞图文操作 */
        public static final String PRAISE_ITEMS = "wechat/praise/store/wechat";
        /** 图文预览*/
        public static final String ITEM_PRER_VIEW = "wechat/item/prev";
        
    }

    /** 微信端 会员中心 */
    class MemberCenter {
        /** 微信WIFI主页面 */
        public static final String VIEW_WECHAT_WIFI_HOME = "/memberCenter/view/wifiHome/{storeId}";
        /** 会员中心主页面 */
        public static final String VIEW_HOME = "/memberCenter/view/home/{storeId}/{businessType}";
        /** 会员等级信息 */
        public static final String VIEW_LEVEL_INFO = "/memberCenter/view/levelInfo";
        /** 个人资料页面 */
        public static final String VIEW_INFO = "/memberCenter/view/info";
        /** 修改个人资料 */
        public static final String ACTION_UPDATE_INFO = "/memberCenter/action/updateInfo";
        /** 查看密码设置页面 */
        public static final String VIEW_SET_PASSWORD = "/memberCenter/view/setPwd";
        /** 设置个人密码 */
        public static final String ACTION_SET_PASSWORD = "/memberCenter/action/setPwd";
        /** 修改支付密码 */
        public static final String ACTION_UPDATE_PAYCODE = "/memberCenter/action/updatePaycode";
        /** 会员注册页面 */
        public static final String VIEW_REGISTER = "/memberCenter/view/register/{storeId}";
        /** 获取验证码 */
        public static final String ACTION_GET_VERIFYCODE = "/memberCenter/action/getVerifyCode";
        /** 完善会员信息页面 */
        public static final String VIEW_REGISTER_INFO = "/memberCenter/view/registerInfo";
        /** 完善会员信息 */
        public static final String ACTION_REGISTER_INFO = "/memberCenter/action/registerInfo";
        /** 会员注册操作 */
        public static final String ACTION_REGISTER = "/memberCenter/action/register";
        /** 会员注销操作 */
        public static final String ACTION_LOGOUT = "/memberCenter/action/logout";
        /** 访问分享发型页面 */
        public static final String VIEW_SHARE = "/memberCenter/view/share";
        /** 进行发型分享操作 */
        public static final String ACTION_SHARE = "/memberCenter/action/share";
        /** 分享信息页面 */
        public static final String VIEW_SHARE_INFO = "/memberCenter/view/shareInfo";
        /** 确认预约 */
        public static final String ACTION_ORDER_APPOINTMENT = "/memberCenter/action/orderAppointment";
        /** 预约页面 */
        public static final String VIEW_ORDER_APPOINTMENT = "/memberCenter/view/orderAppointment/{storeId}/{businessType}";
        /** 预约时间页面 */
        public static final String VIEW_DATE_APPOINTMENT = "/memberCenter/view/dateAppointment";
        /** 查看项目详情 */
        public static final String VIEW_PROJECT_DETAIL = "/memberCenter/view/projectDetail";
        /** 会员充值页面 */
        public static final String VIEW_ACCOUNT = "/memberCenter/view/account";
        /** 积分流水记录页面 */
        public static final String VIEW_INTEGRAL_FLOW_LIST = "/memberCenter/view/integralFlow";
        /** 卡金流水记录页面 */
        public static final String VIEW_CARD_MONEY_FLOW_LIST = "/memberCenter/view/cardmoneyFlow";
        /** 礼金流水记录页面 */
        public static final String VIEW_GIFT_MONEY_FLOW_LIST = "/memberCenter/view/giftmoneyFlow";
        /** 积分商城页面 */
        public static final String VIEW_SHOP_CENTER = "/memberCenter/view/shopCenter/{storeId}/{businessType}";
        /** 兑换优惠券 */
        public static final String ACTION_EXCHANGE_COUPON = "/memberCenter/action/exchangeCoupon";
        /** 会员优惠券页面 */
        public static final String VIEW_MEMBER_COUPON = "/memberCenter/view/memberCoupon";
        /** 店铺信息页面 */
        public static final String VIEW_STORE_INFO = "/memberCenter/view/storeInfo/{storeId}/{businessType}";
        /** 店铺展示页面 */
        public static final String VIEW_STORE_SHOW = "/memberCenter/view/storeShow";
        /** 会员预约列表页面 */
        public static final String VIEW_APPOINTMENT_LIST = "/memberCenter/view/appointmentList/{storeId}/{businessType}";
        /** 会员订单列表页面 */
        public static final String VIEW_ORDER_LIST = "/memberCenter/view/orderList";
        /** 会员订单付款页面 */
        public static final String VIEW_ORDER_PAY = "/memberCenter/view/orderPay/{storeId}/{businessType}";
        /** 会员订单付款操作 */
        public static final String ACTION_ORDER_PAY = "/memberCenter/action/orderPay";
        /** 会员订单支付明细页面 */
        public static final String VIEW_PAYMENT_DETAIL = "/memberCenter/view/paymentDetail/{storeId}/{businessType}";
        /** 会员订单评价页面 */
        public static final String VIEW_ORDER_EVALUATE = "/memberCenter/view/orderEvaluate";
        /** 会员订单评价操作 */
        public static final String ACTION_ORDER_EVALUATE = "/memberCenter/action/orderEvaluate";
        /** 会员套餐列表页面 */
        public static final String VIEW_COMBO_LIST = "/memberCenter/view/comboList";
        /** 会员取消预约 */
        public static final String ACTION_CANCEL_APPOINTMENT = "/memberCenter/view/cancelAppointment";
        /** 访问门店列表 */
        public static final String VIEW_STORE_LIST = "/memberCenter/view/storeList";

        /** 新用户获取分享奖励 */
        public static final String ACTION_GET_REWARD = "/memberCenter/action/getRewardAction";
        /** 员工信息 */
        public static final String VIEW_EMPLOYEE_INFO = "/memberCenter/view/employeeInfo";
        /** 员工项目详情 */
        public static final String VIEW_EMPLOYEE_PROJECT = "/memberCenter/view/employeeProject";
    }
    
    /** 友宝商城模块 */
    class UboxMall {
        /** 商品详情页面 */
        public static final String VIEW_GOODS_INFO = "mobile/view/pay/goodsInfo";
        /** 商品支付 */
        public static final String ACTION_GOODS_PAY = "/uboxMall/action/goodsPay";
        /** 商品支付取消 */
        public static final String ACTION_GOODS_PAY_CANCEL = "/uboxMall/action/goodsPayCancel";
        /** 商品支付异步回调接口 */
        public static final String ACTION_GOODS_PAY_CALLBACK = "/uboxMall/action/goodsPayCallback/{transactionId}";
        /** 商品支付同步回调接口 */
        public static final String VIEW_GOODS_PAY_CALLBACK = "/uboxMall/view/goodsPayCallback/{transactionId}";
        /** 订单列表 */
        public static final String VIEW_ORDER_LIST = "/uboxMall/view/orderList/{storeId}";
        /** 交易详情 */
        public static final String VIEW_PAYMENT_INFO = "/uboxMall/view/paymentInfo";
        /** 设置服务员工 */
        public static final String ACTION_SET_SERVER = "/uboxMall/view/setServer";
        /** 友宝回调接口 */
        public static final String ACTION_CALLBACK = "/callback/ubox";
    }

    /** 员工模块 */
    class Employee {
        /** 显示人员信息页面 */
        public static final String VIEW_QUERY = "employee/view/employee";
        /** 查询人员列表信息 */
        public static final String ACTION_LIST = "employee/action/list";
        /** 根据条件(姓名/工号/手机号)全查询员工信息 */
        public static final String ACTION_LISTALL = "employee/action/listAll";
        /** 新增人员信息 */
        public static final String ADD = "employee/action/add";
        /** 获取人员详情信息 */
        public static final String GET_DETAIL = "employee/action/getdetail";
        /** 修改人员信息 */
        public static final String UPDATE = "employee/action/update";
        /** 删除人员信息 */
        public static final String DELETE = "employee/action/delete";
        /** 新增人员培训信息 */
        public static final String ADD_PX = "employee/action/pxadd";
        /** 查询人员培训信息 */
        public static final String QUERY_PX = "employee/action/pxquery";
        /** 新增人员工作经验信息 */
        public static final String ADD_GZJY = "employee/action/gzjyadd";
        /** 查询人员工作经验信息 */
        public static final String QUERT_GZJY = "employee/action/gzjyquery";
        /** 新增人员擅长信息 */
        public static final String ADD_SC = "employee/action/scadd";
        /** 查询人员擅长信息 */
        public static final String QUERT_SC = "employee/action/scquery";
        /** 获取人员关系 */
        public static final String QUERT_REGX = "employee/action/queryrygx";
        /** 根据部门获取员工信息 */
        public static final String GETDEPTEMPLOYEE = "employee/action/getDeptEmployee";
        /** 公共展示员工详情信息 */
        public static final String EMPLOYEEDETAILSHOW = "employee/action/employeedetailshow";
        /** 派遣 提交 */
        public static final String ADDDISPATCH = "employee/action/adddispatch";
        /** 获取人员的派遣信息 */
        public static final String GETPQLIST = "employee/action/getpqlist";
        /** 导出 */
        public static final String DOWNLOADEXCLE = "employee/action/downloadExcle";
        /** 导入模板下载 */
        public static final String DOWNLOADIMPORT = "employee/action/downloadImport";
        /** 导入 */
        public static final String IMPORTEXCLE = "employee/action/importExcle";
        /** 个人简介 */
        public static final String SAVEDESC = "employee/action/savedesc";
        /** 根据店铺查询员工 */
        public static final String ACTION_FIND_EMPLOYEE_BY_STORE = "employee/action/findEmployeeByStoreId";
        /** 下载员工信息导入模板 */
        public static final String ACTION_DOWNLOAD_IMPORTMODEL_EMPLOYEEINFO = "/importModelOfEmployeeInfo/action/download";

    }

    /**
     * 移动端员工
     * @author 王大爷
     * @date 2015年8月17日 下午1:52:45
     */
    class Staff {
        /** 员工中心主页面 */
        public static final String VIEW_HOME = "/staff/view/home/{storeId}/{businessType}";
        /** 员工注册页面 */
        public static final String VIEW_REGISTER = "/staff/view/register";
        /** 员工登录操作 */
        public static final String ACTION_LOGIN = "/staff/action/login";
        /** 员工注销操作*/
        public static final String ACTION_LOGOUT = "/staff/action/logout";
        /** 查询所有订单 */
        public static final String VIEW_ALL_ORDER = "/staff/view/order/all";
        /** 通知会员买单 */
        public static final String ACTION_ORDER_NOTITY = "/staff/action/order/notify";
        /** 查询我的预约 */
        public static final String VIEW_STAFF_APPOINT = "/staff/view/staffAppoint/{storeId}/{businessType}/{type}";
        /** 员工拒绝预约 */
        public static final String ACTION_REFUSE_APPOINT = "/staff/action/refuseAppoint";
        /** 员工预约开单 */
        public static final String ACTION_START_APPOINT = "/staff/action/startAppoint";
        /** 员工预约操作 */
        public static final String ACTION_APPOINT_OPERATE = "/staff/action/appointOperate";
        /** 员工打卡 */
        public static final String ACTION_SIGN_OPERATE = "/staff/action/signOperate";
        /** 员工中心页面 */
        public static final String VIEW_STAFF_CENTER = "/staff/view/staffCenter/{storeId}/{businessType}";
        /** 员工信息页面 */
        public static final String VIEW_STAFF_INFO = "/staff/view/staffInfo";
        /** 员工密码页面 */
        public static final String VIEW_UPDATE_PWD = "/staff/view/updatePwd";
        /** 修改员工密码 */
        public static final String ACTION_UPDATE_PWD = "/staff/action/updatePwd";
        /** 员工更多页面 */
        public static final String VIEW_STAFF_MORE = "/staff/view/more";
        /** 员工业绩排行页面 */
        public static final String VIEW_ALL_EARNING = "/staff/view/allEarning";
        /** 员工个人业绩 */
        public static final String VIEW_STAFF_EARNING = "/staff/view/staffEarning";
        /** 员工个人表现页面 by DavidLiang */
        public static final String VIEW_INDIVIDUAL_PERFORMANCE = "/staff/view/individualPerformance";
        /** 根据时间查询员工个人表现 by DavidLiang */
        public static final String ACTION_FIND_INDIVIDUAL_PERFORMANCE_BY_TIME = "/staff/action/findIndividualPerformanceByTime";

        /** 校验用户密码是否正确 */
        public static final String ACTION_CHECK_USERINFO = "staff/action/checkUserInfo";
        /** 根据手机号码查询会员信息 */
        public static final String ACTION_SELECT_BASEINFO = "staff/action/selectBaseInfo";
        /** 根据会员标识查询会员信息 */
        public static final String ACTION_SELECT_MEMBER_INFO = "staff/action/selectMemberInfo";
        /** 选择项目 */
        public static final String VIEW_SELECT_CATEGORY = "staff/view/selectCategory";
        /** 轮牌指定 */
        public static final String VIEW_MEMBER_SHIFTMAHJONG_SERVE = "staff/view/memberShiftMahjongServe";
        /** 选择项目列表 */
        public static final String VIEW_SELECT_PROJECTLIST = "staff/view/selectProjectList";
        /** 服务 */
        public static final String ACTION_SENDSERVICE_NOTICE = "staff/action/sendServiceNotice";
        /** 开单 */
        public static final String ACTION_ADD_ORDER = "staff/action/addOrder";
        /** 查询员工个人订单 */
        public static final String VIEW_EMPLOYEE_ORDER = "/staff/view/employeeOrderView/{storeId}/{businessType}";
        /** 接待中心*/
        public static final String VIEW_RECEPTION = "/staff/view/reception";
        /** 服务交接*/
        public static final String VIEW_SERVER_ASSOCIATE = "staff/view/serverAssociate";
        /** 确认完成订单*/
        public static final String ACTION_FINISH_ORDER_DETAIL = "staff/action/finishOrderDetail";
        /** 服务交接轮牌*/
        public static final String ACTION_SERVER_ASSOCIATE_SHIFTMAHJONG = "staff/action/serverAssociateShiftMahjong";
        /** 修改项目*/
        public static final String VIEW_UPDATE_PROJECT_LIST = "staff/view/updateProjectList";
        /** 确定修改的项目*/
        public static final String VIEW_CONFIRM_UPDATE_PROJECT = "staff/view/confirmUpdateProject";
        /** 保存修改的项目*/
        public static final String ACTION_UPDATE_SAVE_DETAIL = "staff/action/updateSaveDetail";
        /** 等待中心*/
        public static final String VIEW_WAITING_CENTRE = "staff/view/waitingCentre";
        /** 进入等待中心轮牌*/
        public static final String VIEW_WAITING_CENTRE_SHIFT_MAHJONG = "staff/view/waitingCentreShiftMahjong";
        /** */
        public static final String ACTION_WAITING_ASSOCIATE_SHIFTMAHJONG = "staff/action/waitingAssociateShiftMahjong";
        /** 附加项目、商品、套餐*/
        public static final String VIEW_APPEND_DETAIL = "staff/view/appendDetail";
        /** 订单详情*/
        public static final String VIEW_SELECT_ORDER_DETAIL = "/staff/view/selectOrderDetail/{storeId}/{businessType}";
        /** 删除订单明细*/
        public static final String ACTION_DELETE_ORDER_DETAIL = "staff/action/deleteOrderDetail";
        /** 删除订单*/
        public static final String ACTION_DELETE_ORDERINFO = "staff/view/deleteOrderInfo";
        /** 查询所有轮牌*/
        public static final String SELECT_ALL_SHIFTMAHJONG = "staff/view/selectAllShiftMahjong";
        /** 查询订单详情*/
        public static final String SELECT_ORDERINFO = "staff/action/selectOrderInfo";
        /** 保存挂账信息*/
        public static final String SAVE_DEBT_INFO = "staff/action/saveDebtInfo";
        /** 我的轮牌 */
        public static final String VIEW_MY_SHIFTMAHJONG = "staff/view/myShiftMahjong";
        /** 查询明细详情*/
        public static final String SELECT_ORDER_DETAIL = "staff/action/selectOrderDetail";
        /** 保存签单信息*/
        public static final String SAVE_FREE_INFO = "staff/action/saveFreeInfo";
        /** 挂起*/
        public static final String ACTION_HANGUP_ORDER = "staff/action/hangUpOrder";
        /** 上移*/
        public static final String STAFF_UPWARD_IMG = "staff/action/upwardIMG";
        /** 下移*/
        public static final String STAFF_NEXT_IMG = "staff/action/nextIMG";
        /** 置顶*/
        public static final String STAFF_TO_TOP_IMG = "staff/action/toTopIMG";
        /** 置低*/
        public static final String STAFF_TO_END_IMG = "staff/action/toEndIMG";
        /** 查询明细是否修改*/
        public static final String SELECT_PROJECT_ISUPDATE = "staff/action/selectProjectIsUpdate";
        /** 合并步骤*/
        public static final String VIEW_PROJECT_COMBINE = "staff/view/projectCombine";
        /** 检验合并步骤是否满足*/
        public static final String ACTION_CHECKOUT_STEP = "staff/action/checkoutStep";
        /** 我的提成*/
        public static final String VIEW_SELECT_COMMISSION_INFO = "staff/action/selectCommissionInfo";
        /** 选择时间类型查看提成*/
        public static final String ACTION_SELECT_COMMISSION_DATE_TYPE = "staff/action/selectCommissionDateType";
        /** 修改轮牌员工状态 */
        public static final String UPDATE_STATE = "staff/action/updateState";
        /** 根据门店标识查询该门店会员信息*/
        public static final String SELECT_MEMBER_LIST = "staff/action/selectMemberList";
        /** 业绩排行*/
        public static final String ACTION_SELECT_EMPLOYEE_DATA = "staff/action/selectEmployeeData";
        /** 员工查询我的考勤首页  by DavidLiang */
        public static final String VIEW_MY_ATTENDANCE_HONE = "staff/view/myAttendanceHome";
        /** 员工分页查询我的考勤 by DavidLiang */
        public static final String ACTION_FIND_MY_ATTENDANCE_BY_PAGE = "staff/action/findMyAttendanceByPage";
        
    }

    /**
     * 移动端老板
     * 前缀增加/
    * @author 王大爷
    * @date 2016年1月15日 下午3:11:19
     */
    class Boss {
        /** 业绩报表*/
        public static final String VIEW_BOSS_OBJECTIVE = "/boss/view/bossObjective/{storeId}/{businessType}";
        /** 现金业绩*/
        public static final String ACTION_CASH_AMOUNT_SERVICE = "/boss/view/cashAmountService";
        /** 客情分析*/
        public static final String VIEW_CUSTOMER_ANALYSIS = "/boss/view/coutomer/{storeId}/{businessType}";
        /** 客情分析查询(年月日)*/
        public static final String SERCH_CUSTOMER_ANALYSIS = "/boss/view/serch/coutomer";
        /** 去往老板端员工业绩页面 by DavidLiang */
        public static final String VIEW_HOME_EMPLOYEE_COMMISSION = "/boss/view/employeeCommissionHome/{storeId}/{businessType}";
        /** 根据条件查询员工业绩 by DavidLiang */
        public static final String ACTION_FIND_EMPLOYEE_COMMISSION_BY_CONDITION = "/boss/action/findEmployeeCommissionByCondition";
        /** 营业分析*/
        public static final String VIEW_BUSINESS_ANALYSIS = "/boss/view/business/{storeId}/{businessType}";
        /** 营业分析查询*/
        public static final String SERCH_BUSINESS_ANALYSIS = "/boss/view/serch/business";
        /** 营业分析查询(项目下支出明细)*/
        public static final String SERCH_BUSINESS_PROJECT = "/boss/view/serch/project";
        /** 去往老板端员工业绩详情页面 by DavidLiang */
        public static final String VIEW_HOME_EMPLOYEE_COMMISSION_DETAIL = "/boss/view/employeeCommissionDetailHome";
        
        /** 老板模块首页 */
        public static final String VIEW_BOSS_HOME = "/boss/view/home/{storeId}/{businessType}";
    }
    
    /**
     * 移动端渠道会议模块
    * @author 高国藩
    * @date 2016年1月8日 上午10:02:15
     */
    class Conference {

        /** 访问渠道会议申请页面 */
        public static final String VIEW_CONFERENCE = "conference/view/conference";
        /** 申请注册渠道会议 */
        public static final String SAVE_CONFERENCE = "conference/save/conference";
        /** 会议列表*/
        public static final String VIEW_CONFERENCE_LIST = "conference/view/list";
        /** 会议详情*/
        public static final String VIEW_CONFERENCE_INFO = "conference/view/info";
        /** 会议修改页面*/
        public static final String UPDATE_CONFERENCE_INFO_VIEW = "conference/view/update";
        /** 修改会议信息*/
        public static final String UPDATE_CONFERENCE = "conference/update/conference";
        /** 分享信息链接*/
        public static final String VIEW_SHARE_CONFERENCE = "mobile/view/pay/conference";
        /** 参加报名*/
        public static final String JOIN_CONFERENCE = "conference/join/conference";
        /** 查看报名情况*/
        public static final String VIEW_CONFERENCE_REGIST = "conference/view/regist";
        /** 查看费用详情*/
        public static final String VIEW_CONFERENCE_DETAILS = "conference/view/details";
        /** 支付回调接口*/
        public static final String WECHAT_CALLBACK_CONFERENCE_PAY = "conference/pay/callback/{personnelId}/{conferenceId}";
        /** 发起报名支付*/
        public static final String CONFERENCE_CREATE_PAY = "conference/init/pay";
        /** 查看到场情况*/
        public static final String VIEW_CONFERENCE_ADDMISSION = "conference/view/admission";
        /** 对人员入场签到*/
        public static final String ACTION_CONFERENCE_ADDMISSION = "conference/action/admission";
        
    }

    /** 人员目标模块 */
    class Objective {
        /** 显示目标信息页面 */
        public static final String VIEW_QUERY = "objective/view/objective";
        /** 查询目标列表信息 */
        public static final String ACTION_LIST = "objective/action/list";
        /** 新增目标信息 */
        public static final String ADD = "objective/action/add";
        /** 获取目标详情 */
        public static final String QUERY_DETAIL = "objective/action/detail";
        /** 修改目标信息 */
        public static final String UPDATE = "objective/action/update";
        /** 删除目标信息 */
        public static final String DELETE = "objective/action/delete";
        /** 统计月份的相关 */
        public static final String QUERYSUM = "objective/action/querysum";
        /** 导出 */
        public static final String DOWNLOADEXCLE = "objective/action/downloadExcle";
        /** 下载导入模板 */
        public static final String DOWNLOADIMPORT = "objective/action/downloadImport";
        /** 导入 */
        public static final String IMPORTEXCLE = "objective/action/importExcle";
    }

    /** 班次 */
    class Shift {
        /** 班次页面 */
        public static final String VIEW_QUERY = "shift/view/shift";
        /** 查询目标列表信息 */
        public static final String ACTION_LIST = "shift/action/list";
        /** 新增人员班次信息 */
        public static final String ADDEMPLOYEESHIFT = "shift/action/addemployeeshift";
        /** 获取人员班次信息 */
        public static final String GETEMPLOYEESHIFT = "shift/action/getemployeeshift";
        /** 修改人员班次信息 */
        public static final String UPDATEEMPLOYEESHIFT = "shift/action/updateemployeeshift";
        /** 删除人员班次信息 */
        public static final String DELETEEMPLOYEESHIFT = "shift/action/deleteemployeeshift";
        /** 修改班次信息 */
        public static final String UPDATESHIFT = "shift/action/updateshift";
        /** 新增班次信息 */
        public static final String ADDSHIFT = "shift/action/addshift";
        /** 获取班次信息 */
        public static final String GETSHIFTINFO = "shift/action/getshift";
        /** 导入 */
        public static final String IMPORTEXCLE = "shift/action/importExcle";
        /** 下载员工班次信息导入模板 */
        public static final String ACTION_DOWNLOAD_IMPORTMODEL_SHIFTINFO = "/importModelOfShiftInfo/action/download";
    }

    /** 人员目标考核模块 */
    class Objectiverule {
        /** 显示目标信息页面 */
        public static final String VIEW_QUERY = "objectiverule/view/objectiverule";
        /** 查询目标列表信息 */
        public static final String ACTION_LIST = "objectiverule/action/list";
        /** 新增考核目标 */
        public static final String ADD_OBJECTIVERULE = "objectiverule/action/addObjectiverule";
        /** 查询考核目标详情 */
        public static final String GET_RULEDETAIL = "objectiverule/action/getruledetail";
        /** 修改考核目标 */
        public static final String UPDATE_OBJECTIVERULE = "objectiverule/action/updateObjectiverule";
        /** 修改考核目标 */
        public static final String DELETE_OBJECTIVERULE = "objectiverule/action/deleteObjectiverule";
    }

    /** 部门模块 */
    class Dept {
        /** 新增部门 */
        public static final String ADD_DEPT = "dept/action/adddept";
        /** 修改部门 */
        public static final String UPDATE_DEPT = "dept/action/updatedept";
        /** 删除部门 */
        public static final String DELETE_DEPT = "dept/action/deletedept";
        /** 获取部门 信息 */
        public static final String GETDEPTINFO = "dept/action/getDeptInfo";
        /** 部门架构导入 */
        public static final String IMPORTEXCLE = "dept/action/importexcle";
    }

    /** 门店制度管理模块 */
    class StoreManageRule {
        /**查看管理制度主页*/
        public static final String VIEW_HOME = "storeManageRule/view/home";
        /**修改规则信息*/
        public static final String ACTION_UPDATE = "storeManageRule/action/update";
    }

    /** 短信模块 */
    class Sms {
        /** 短信授权回调接口 */
        public static final String AUTH_CALLBACK = "/sms/auth/callback";
    }

    /** 用户登陆模块 */
    class UserLogin {
        /** 登陆 */
        public static final String LOGIN = "/user/login";
        /** 伪登录 */
        public static final String INDEX = "/";
        /** 退出 */
        public static final String LOGOUT = "/user/logout";
    }

    /** APP相关接口 */
    class App {
        /** 登陆 */
        public static final String LOGIN = "/app/login";
        /**获取所用用户*/
        public static final String GET_USER_LIST = "/app/userList";
        /**员工个人页面*/
        public static final String STAFF_INFO = "app/staffInfo";
        /** 员工打卡 */
        public static final String SIGN_CHECK = "app/signCheck";
        /** 修改员工密码 */
        public static final String UPDATE_EMP_PWD = "app/updateEmpPwd";
        /** 员工预约*/
        public static final String STAFF_APPOINT = "app/staffAppoint";
    }

    /**
     * 门店信息
     *
     * @author 张进军
     * @date Nov 9, 2015 11:14:30 AM
     */
    class StoreInfo {
        /** 增加门店 */
        public static final String ACTION_ADD_STORE = "/storeinfo/action/addstore";
        /** 显示店铺设置页面 */
        public static final String VIEW_STORE_SETTING = "/storeinfo/view/storeSetting";
        /** 店铺设置操作 */
        public static final String ACTION_STORE_SETTING = "/storeinfo/action/storeSetting";
        /** 店铺信息编辑操作 */
        public static final String ACTION_STORE_EDITOR = "/storeinfo/action/storeEditor";
        /** 门店初始化*/
        public static final String ACTION_STORE_INITIALIZE = "/storeinfo/action/initialize";
        /** 门店数据复制*/
        public static final String ACTION_STORE_COPY = "/storeinfo/action/copy";
    }

    /**
     * 自助收银
     * @author luhhwen
     * @date 2015年10月21日 下午3:09:34
     */
    class SelfCashier {
        /** 订单汇总 */
        public static final String VIEW_HOME = "selfcashier/view/list";

        /** 订单信息 */
        public static final String ACTION_ORDER_INFO = "selfcashier/action/orderinfo";

        /** 会员信息 */
        public static final String ACTION_MEMBER_INFO = "selfcashier/action/memberinfo";

        /** 修改订单的会员 */
        public static final String ACTION_CHANGE_MEMBER = "selfcashier/action/changemember";

        /** 提交订单 */
        public static final String ACTION_SUBMIT_ORDER = "selfcashier/action/submitorder";
        
        /** 取消订单*/
        public static final String ACTION_DELETE_ORDERINFO = "selfcashier/action/deleteOrderInfo";
    }

    /**
     * 营业汇总
     * @author chendb
     * @date 2015年10月24日 上午11:09:24
     */
    class Businessreport {
        /** 营业汇总 */
        public static final String SUMMARY = "summary/view/summary";
        /** 现金 */
        public static final String CASHRECEIPTS = "cashreceipts/view/cashreceipts";
        /** 卡销售 */
        public static final String CARDSALES = "cardsales/view/cardsales";
        /** 劳动业绩 */
        public static final String LABORPERFORMANCE = "laborperformance/view/laborperformance";
        /** 划卡消费 */
        public static final String CARDCONSUMPTION = "cardconsumption/view/cardconsumption";
        /** 套餐消费 */
        public static final String PACKAGESALES = "packagesales/view/packagesales";
        /** 商品消费 */
        public static final String COMMODITYSALES = "commoditysales/view/commoditysales";
        /**项目劳动业绩分部门排行*/
        public static final String LABORPERFORMANCERANKBYDEPT="laborperformance/view/laborperformance/GoodsRankByDate";
        /**项目劳动业绩分销量(销售额)排行*/
        public static final String LABORPERFORMANCERANKBYNUM="laborperformance/view/laborperformance/GoodsRankByNum";
        /**套餐分部门排行*/
        public static final String COMBORANKBYDEPT="packagesales/view/packagesales/rankComboByDept";
        /**项目套餐收入分销量(销售额)排行*/
        public static final String COMBORANKBYNUMORSALES="packagesales/view/packagesales/rankComboByNumOrSales";
        /**商品销售分部门排行*/
        public static final String GOODSRANKBYDEPT="commoditysales/view/commoditysales/searchGoodsByDept";
        /**商品销售按照销量或者销售额排行*/
        public static final String GOODSRANKBYCNTORAMT="commoditysales/view/commoditysales/searchGoodsByCntOrAmt";
        /**跨店对账*/
        public static final String CROSSSHOPRECONCILIATIONBYMONTH="reconciliation/view/crossReconciliation";
        /**跨店消费明细数据*/
        public static final String CROSSSHOPCONSUMEDETAIL="reconciliation/view/crossReconciliationdetail";
        /**用户更新跨店消费明细数据*/
        public static final String CROSSSHOPDETAILUPDATE="reconciliation/view/crossReconciliationupdate";
        /**单个门店进行对账*/
        public static final String VIEW_CHECK_RECONCILIATION="reconciliation/view/checkReconciliation";
    }

    /**
     * 经营分析
    * @author 张进军
    * @date Jan 13, 2016 8:36:36 PM
     */
    class BusinessAnalysis {
        /** 客情分析 */
        public static final String VIEW_ANALYSIS_CUSTOMER = "businessAnalysis/view/customer";
        /** 预约分析 */
        public static final String VIEW_ANALYSIS_APPOINTMENT = "businessAnalysis/view/appointment";
        /** 工资单 */
        public static final String VIEW_ANALYSIS_PAYROLL = "businessAnalysis/view/payroll";
        /** 员工分析 */
        public static final String VIEW_ANALYSIS_EMPLOYEE = "businessAnalysis/view/employee";
        /** 营业分析 */
        public static final String VIEW_ANALYSIS_BUSINESS = "businessAnalysis/view/business";
    }

    /**
     * 流水查詢
     *
     * @author luhhwen
     * @date 2015年11月05日 下午2:23:39
     */
    class DayBook {
        /** 汇总 */
        public static final String VIEW_HOME = "daybook/daybook";

        /** 通过订单标识查询订单及明细步骤*/
        public static final String SELECT_ORDER_BY_UPDATE = "daybook/selectOrderByUpdate";
        /** 确定修改订单*/
        public static final String ORDER_BY_UPDATE = "daybook/orderByUpdate";
        /** 平台删除订单*/
        public static final String ELEMENT_DELETE_ORDERID = "daybook/elementDeleteOrderId";
        /** 修改会员等级*/
        public static final String CHANGE_LEVELID = "daybook/changeLevelId";
    }

    /**
     *
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
     *         2015年11月26日
     */
    class StoreApply {
        /** 店铺申请页面 */
        public static final String VIEW_STORE_APPLY = "/storeapply/view/storeApply";
        /** 店铺申请 */
        public static final String ACTION_STORE_APPLY = "/storeapply/action/storeApply";
    }

    /**
     *
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
     *         2015年11月26日
     */
    class AgentApply {
        /** 渠道申请页面 */
        public static final String VIEW_AGENT_APPLY = "/agentapply/view/agentApply";
        /** 渠道申请 */
        public static final String ACTION_AGENT_APPLY = "/agentapply/action/agentApply";
    }

    /**
    *
    * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
    *         2015年11月26日
    */
    class WechatCommon {

        /** 发送验证码 */
        public static final String VC = "/wechat/common/vc";

    }

    /**
     *  门店自助模块
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
     *         2015年11月26日
     */
    class StoreDetail {

        /** 门店首页页面 */
        public static final String VIEW_DETAIL_INDEX = "/storedetail/view/index";

        /** 门店信息页面 */
        public static final String VIEW_DETAIL_INFO = "/storedetail/view/info";

        /** 分店列表页面 */
        public static final String VIEW_DETAIL_HQ_CHAINS = "/storedetail/view/chains";
        
        /** 开通系统页面 */
        public static final String VIEW_DETAIL_OPEN_SYS = "/mobile/view/pay/openSys";
        
        /** 开通系统操作 */
        public static final String ACTION_DETAIL_OPEN_SYS = "/storedetail/action/openSys";
        
        /** 开通系统的微信支付回调 */
        public static final String ACTION_CALLBACK_SYS_OPEN = "/storedetail/callback/sysOpen/{storeId}/{sysType}/{smsType}";
        
        /** 系统续费/短信购买的微信支付回调 */
        public static final String ACTION_CALLBACK_SYS_CHARGE = "/storedetail/callback/sysCharge/{storeId}/{businessType}/{productType}";

        /** 访问系统充值/短信购买页面 */
        public static final String VIEW_DETAIL_CHARGE_SYS = "/mobile/view/pay/chargeSys";

        /** 系统充值/短信购买的支付操作 */
        public static final String ACTION_DETAIL_CHARGE_SYS = "/storedetail/action/chargeSys";
        
        /** 系统开通/充值、短信购买成功页面*/
        public static final String VIEW_DETAIL_CHARGE_INFO = "/storedetail/view/chargeInfo";

    }

    /**
     *  渠道自助模块
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
     *         2015年11月26日
     */
    class AgentDetail {

        /** 渠道首页页面 */
        public static final String VIEW_DETAIL_INDEX = "/agentdetail/view/index";

        /** 渠道信息页面 */
        public static final String VIEW_DETAIL_INFO = "/agentdetail/view/info";

        /** 新申请列表页面 */
        public static final String VIEW_DETAIL_NEW_STORE = "/agentdetail/view/newstore";

        /**  */
        public static final String VIEW_DETAIL_NEW_STORE_OTHER = "/agentdetail/view/newstore/other";

        /** 渠道商查看"我的门店" */
        public static final String VIEW_DETAIL_STORE_NORMAL = "/agentdetail/view/store";

        /**  */
        public static final String VIEW_DETAIL_STORE_RENEW = "/agentdetail/view/store/renew";

        /**  */
        public static final String VIEW_DETAIL_STORE_OVER = "/agentdetail/view/store/over";

        /** 我推荐的门店 */
        public static final String VIEW_DETAIL_SHARE_STORE = "/agentdetail/view/share";

        /** 我推荐的渠道 */
        public static final String VIEW_DETAIL_SHARE_AGENT = "/agentdetail/view/share/agent";

        /** 推荐给我的门店 */
        public static final String VIEW_DETAIL_SHARED_STORE = "/agentdetail/view/shared/store";

        /**  */
        public static final String VIEW_DETAIL_STAT = "/agentdetail/view/stat";

        /**  */
        public static final String VIEW_DETAIL_INCOME = "/agentdetail/view/income";

    }

    /**
     * 员工设置：考勤记录
     * @author DavidLiang
     *
     */
    class AttendanceRecord {
        /** 考勤首页 */
        public static final String VIEW_ATTENDANCE_RECORD_HOME = "/attendance/view/attendance";
        /** 分页查询员工考勤记录 */
        public static final String ACTION_LIST_EMPLOYEE_ATTENDANCE = "/employeeAttendance/action/list";
        /** 根据主键查询员工考勤记录 */
        public static final String ACTION_FIND_EMPLOYEE_ATTENDANCE_BYPRIMARY = "/employeeAttendance/action/findEmployeeAttendanceByPrimary";
        /** 修改考勤记录 */
        public static final String ACTION_EDIT_EMPLOYEE_ATTENDANCE = "/employeeAttendance/action/edit";
        /** 删除考勤记录 */
        public static final String ACTION_DELETE_EMPLOYEE_ATTENDANCE = "/employeeAttendance/action/delete";

    }

    /**
     * 员工奖惩
     * @author DavidLiang
     *
     */
    class EmployeeReward {
        /** 员工奖惩首页 */
        public static final String VIEW_EMPLOYEE_REWARD_HOME = "/rewards/view/home";
        /** 全查询考勤规则奖惩汇总情况 */
        public static final String ACTION_LISTALL_ATTENDANCE = "/attendance/action/listAll";
        /** 分页查询考勤规则奖惩汇总情况 */
        public static final String ACTION_LIST_ATTENDANCE = "/attendance/action/list";
        /** 分页查询行为规范奖惩汇总情况 */
        public static final String ACTION_LIST_BEHAVIOUR = "/behaviour/action/list";
        /** 分页查询服务表现奖惩汇总情况 */
        public static final String ACTION_LIST_SERVICE = "/service/action/list";
        /** 分页查询奖惩详细 */
        public static final String ACTION_REWARD_DETAIL = "/rewardsDetail/action/list";
        /** 添加员工奖惩 */
        public static final String ACTION_ADD_REWARD = "/rewards/action/add";
        /** 删除员工奖惩 */
        public static final String ACTION_DELETE_REWARD = "/rewards/action/delete";
    }

    /**
     * 业务员模块
     * @author DavidLiang
     *
     */
    class Salesman {
    	/** 渠道(代理)商查询门下所有业务员 */
    	public static final String VIEW_SALESMAN_LISTALL = "/salesman/view/listAll";
    	/** 查看业务员首页(首页渠道(代理)商分页查询门下业务员) */
    	public static final String VIEW_SALESMAN_HOME = "/salesman/view/home";
    	/** ajax分页查询业务员 */
    	public static final String ACTION_LIST_SALESMAN = "/salesman/action/list";
    	/** 去往业务员新增页面 */
    	public static final String VIEW_TOADD_SALESMAN = "/salesman/view/toAdd";
    	/** 新增业务员 */
    	public static final String ACTION_ADD_SALESMAN = "/salesman/action/add";
    	/** 停用业务员 */
    	public static final String ACTION_DEACTIVATE_SALESMAN = "/salesman/action/deactivate";
    	/** 重新启用业务员 */
    	public static final String ACTION_REENABLE_SALESMAN = "/salesman/action/reEnable";
    	/** 删除业务员 */
    	public static final String ACTION_DELETE_SALESMAN = "/salesman/action/delete";
    	/** 业务员登录 */
    	public static final String ACTION_LOGIN_SALESMAN = "/salesman/action/login";
    	/** 业务员账户 */
    	public static final String VIEW_ACCOUNT_SALESMAN = "/salesman/view/account";
    	/** 业务员推荐状态正常门店(试运营, 正常运营) */
        public static final String VIEW_SALESMAN_STORE_NORMAL = "/salesman/view/seeSalesmanRecommendStoreNormal";
        /** 业务员推荐状态续费提醒门店(续费提醒) */
        public static final String VIEW_SALESMAN_STORE_RENEW = "/salesman/view/seeSalesmanRecommendStoreRenew";
        /** 业务员推荐状态已过期门店(已过期) */
        public static final String VIEW_SALESMAN_STORE_OVER = "/salesman/view/seeSalesmanRecommendStoreOver";
        /** 业务员去往登录 */
        public static final String ACTION_TOLOGIN_SALESMAN = "/salesman/action/tologin";
        /** 业务员查看门下客户列表 */
        public static final String VIEW_LIST_CUSTOMER = "/salesman/view/customerList";
        /** 渠道商查看某个业务员详细信息 */
        public static final String VIEW_SALESMAN_INFO = "/salesman/view/salesmanInfo";
    }
    
    /**
     * 渠道跟踪记录
    * @author DavidLiang
    * @date 2016年1月19日 下午3:21:23
     */
    class AgentFollow {
    	/** 添加渠道跟踪记录 */
        public static final String ACTION_ADD_AGENTFOLLOW = "/agentFollow/action/add";
        /** 删除渠道跟踪记录 */
        public static final String ACTION_DELETE_AGENTFOLLOW = "/agentFollow/action/delete";
    }
    
    /**
     * 友宝商城
    * @author 张进军
    * @date Jan 28, 2016 5:09:16 PM
     */
    class Ubox {
        /** 门店机器配置操作 */
        public static final String ACTION_MACHINE_MANAGE = "ubox/machine/action/manage";
        /**访问商品列表页面*/
        public static final String VIEW_GOODS_LIST = "ubox/goods/view/list";
        /**访问商品编辑页面*/
        public static final String VIEW_GOODS_EDIT = "ubox/goods/view/edit";
        /**商品编辑操作*/
        public static final String ACTION_GOODS_EDIT = "ubox/goods/action/edit";
        /**商品价格、奖励配置操作*/
        public static final String ACTION_GOODS_REWARDS = "ubox/goods/action/rewards";
    }

}
