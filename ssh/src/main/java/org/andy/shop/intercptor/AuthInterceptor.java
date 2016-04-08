package org.andy.shop.intercptor;

/**   
* @Title: AuthInterceptor.java 
* @Package com.wonders.stpt.contractReview.interceptor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年8月8日 下午3:25:49 
* @version V1.0   
*/

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/** 
 * @ClassName: AuthInterceptor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年8月8日 下午3:25:49 
 *  
 */
@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor{

    private Logger logger = Logger.getLogger(AuthInterceptor.class);
    /** 
    * @Title: intercept 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param arg0
    * @param @return
    * @param @throws Exception    设定文件 
    * @throws 
    */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        logger.info("进入struts2配置的拦截器,请您放心使用.....");
        // 取得请求相关的ActionContext实例  
        String result = "";
        ActionContext ctx = invocation.getInvocationContext();  
        Map session = ctx.getSession();  
        String user = (String) session.get("user_id");  
        return invocation.invoke();
    }

}
