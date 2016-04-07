package com.zefun.push.utils;



public interface App {
    public static final long DELIVERIED_TAG = -1;

    /** 服务器域名 */
    public static final String SERVER_HOST = "zefun.livesall.com";
    /** 服务器基础地址 */
    public static final String SERVER_BASE_URL = "http://" + SERVER_HOST + "/zefun";

    class MsgErr {
        public static final String MQ_MSG_ERR_RECORD_KEY = "mq_msg_err_record";
    }
    
    class GeTui {
        public static final String API_HOST = "http://sdk.open.api.igexin.com/apiex.htm";
        public static final String APP_ID = "udv3gUsfjc5CyFr9DAuSL7";
        public static final String APP_KEY = "uAsSGqTkwd8mTAUg4hfHP1";
//        public static final String APP_SECRET = "GOCTwZl77FA6aHNiXNIJY";
        public static final String MASTER_SECRET = "Trb0jehZve8PupL1zjYqZ1";
        public static final long OFF_LINE_EXPIRE_TIME = 30 * 60 * 60 * 1000;
    }
}
