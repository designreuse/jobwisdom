package com.zefun.common.consts;

/**
 * 接口地址常量类，定义时使用根目录下的绝对地址
* @author 高国藩
* @date Aug 4, 2015 9:20:30 AM
 */
public interface Url {
    /** 微信接口 */
    public static class Wechat {
        /**微信验证及消息回复*/
        public static final String CHART = "/coreServlet";
        /**微信上传获得永久thumb_media_id*/
        public static final String GETTHUMBID = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
        /**微信上传获得上传图片URL*/
        public static final String GETPICURL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
        /**根据openId群发消息*/
        public static final String SENDMESSAGEBYID = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
        /**上传图文素材获得medaiId*/
        public static final String GETMEDIAID = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
        /**菜单创建-微信接口*/
        public static final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        /**微信七牛上传图片存储图片,用于图片库*/
        public static final String QINNIU_UPLOAD = "/wechat/qiniu/upload";
        /**新增图文消息*/
        public static final String UPLOADNEWS = "/uploadnews";
        /**菜单新增*/
        public static final String ADD_MENU = "/add/menu";
        /**获得菜单*/
        public static final String GETMENU = "/getMenu";
        /**跳转至菜单设置页面*/
        public static final String VIEW_LIST_MENU = "/wechat/menu";
        /**删除菜单*/
        public static final String DELETE_MENU = "/delete/menu";
        /**改变菜单链接地址*/
        public static final String SET_MENU_URL = "/set/menu/url";
        /**获得单一菜单元素*/
        public static final String GET_ONE_MENU = "/get/one/menu";

    }

    public static class StoreInfo {

        public static final String actionSave = "/store/action/save";
        public static final String listSave = "/store/action/list";
        public static final String employeeSave = "/employee/action/save";
        
    }
}
