package com.zefun.wechat.job;

import com.zefun.wechat.utils.HttpClientUtil;

public class BaiduTextToSpeech {

    public static void main(String[] args) {
        String baidu = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id=29N3a7RVWn0Ad7GVNgtuxQh2&client_secret=f2b837233a75ec5777507f9acf55944b";
        String accessToken = HttpClientUtil.sendGetReq(baidu, "UTF-8");
        System.out.println(accessToken);
    }
}
