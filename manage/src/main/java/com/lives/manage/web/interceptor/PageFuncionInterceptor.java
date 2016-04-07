package com.lives.manage.web.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.App;
import com.lives.manage.web.dto.UserInfoDto;
public class PageFuncionInterceptor implements HandlerInterceptor {
	private List<String> allowUrl;
	
	/**
	 * 记得配合异常映射
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		if(allowUrl.contains(requestUrl)){
			return true;
		}
		
		HttpSession session = request.getSession();
		UserInfoDto user = (UserInfoDto) session.getAttribute("userInfoDto");
		/**未登录*/
		if(user == null){response.sendRedirect("/manage/login.jsp");}
		/**超级管理员登录*/
		else if(user.getUserName().equals(App.SUPERNAME)){
			return true;
		}else{
		/**人员登录*/
		Object obj = session.getAttribute(App.SESSION_FCUNTION_LIST_KEY);
		if(obj != null){
			@SuppressWarnings("unchecked")
			List<String> functionList = (List<String>) obj;
			if(functionList.contains(requestUrl)){
				return true;
			}else{
				/**权限审核未通过的处理结果*/
				response.sendRedirect("/manage/login.jsp");
			}
		}
		}
		return false;
	}
	//[{"userInfo.jsp":["delete","delete"]},{"cust/query.jsp":["remove"]}]
	@SuppressWarnings("unchecked")
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
	    //元素控制
/*		if(request.getRequestURL().toString().indexOf("login")>0) return;
        if (null != modelAndView && null != modelAndView.getModel()) {
        	String resultJsp = modelAndView.getViewName();
        	//要返回的页面jsp
        	resultJsp = resultJsp.substring(resultJsp.lastIndexOf("/")+1, resultJsp.length());
        	resultJsp = resultJsp + ".jsp";
        	List<JSONObject>  bxl = (List<JSONObject>) request.getSession().getAttribute("ys");
        	if(bxl==null)return;
        	//循环用当前页面取出对应页面上的元素
        	Map<String, String> ysMap = new HashMap<String, String>();
        	for (int i = 0; i < bxl.size(); i++) {
        		JSONObject jsonObject = (JSONObject)bxl.get(i);
        		String jsp = (String) jsonObject.get("jsp");
        		if(jsp.equals(resultJsp)){
        			String classname = (String) jsonObject.get("classname");
        			String key = classname.replaceAll("-","");
        			key = key.replaceAll("_", "");
        			key = key.replaceAll(" ", "");
        			if(classname!=null)ysMap.put(key, classname);
        		}
			}
        	modelAndView.addObject("ys", JSONObject.fromObject(ysMap));
        	System.out.println(JSONObject.fromObject(ysMap).toString());
        }*/
    }

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	public void setAllowUrl(List<String> allowUrl) {
		this.allowUrl = allowUrl;
	}
}
