package com.zefun.test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ZefunRunable implements Runnable{
    
    private Logger logger = Logger.getLogger(ZefunRunable.class);
    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    public void run() {

        String url = "http://112.74.210.155/jobwisdom/summary/view/summary";
        String charset = null;
        if (null == charset) {
            charset = DEFAULT_CHARSET;
        }
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",  "D21FDEC1E6FEA8C58F4D6CA24C5A9F0B");
        cookie.setVersion(0);
        cookie.setDomain("http://112.74.210.155/jobwisdom/");
        cookie.setPath("/");
        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(cookie);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        
        HttpHost proxy = new HttpHost("27.42.188.242", 8080, "http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        
        HttpGet get = new HttpGet(url);
//        get.setConfig(config);
        try {
            // 提交请求并以指定编码获取返回数据
            HttpResponse httpResponse = httpClient.execute(get);
            int statuscode = httpResponse.getStatusLine().getStatusCode();
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header header = httpResponse.getFirstHeader("location");
                if (header != null) {
                    String newuri = header.getValue();
                    if ((newuri == null) || (newuri.equals(""))){
                        newuri = "/";
                    }
                    try {
                        httpClient.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        httpClient = null;
                    }
                    logger.info("重定向地址：" + newuri);
                }
            }
            HttpEntity entity = httpResponse.getEntity();
            String res = EntityUtils.toString(entity, charset);
            logger.info("请求成功,当前线程:"+Thread.currentThread().getName());
//            logger.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine() + "；响应内容：" + res);
        }
        catch (ClientProtocolException e) {
//            logger.error("协议异常,堆栈信息如下", e);
        }
        catch (IOException e) {
//            logger.error("网络异常,堆栈信息如下", e);
        }
        finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                httpClient = null;
            }
        }
    }
    
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 3000, 2000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 100000; i++) {
            threadPool.execute(new ZefunRunable());
        }
    }

}
