package org.andy.shop.action;

import java.util.List;

import org.andy.shop.entity.UserInfo;
import org.andy.shop.service.UserInfoService;
import org.andy.shop.utils.AjaxUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @ParentPackage : 对应于 struts.xml 常量配置项的 <constant name="struts.convention.default.parent.package" value="xx"/> 默认值是 convention-default,
 * 拦截器就是在xml中配置好以后才会在这里引用的
 */
@ParentPackage(value="custom-default") 

/**
 * @Namespace 一旦配置,将会在我们配置的根目录(默认为content,也可用@ResultPath来配置)下,找 'user/' 目录下的jsp
 */
@Namespace("/user") 

/**
 * @ResultPath : 对应于 struts.xml 常量配置项的 <constant name="struts.convention.result.path" value="xx" /> 默认值是 /WEB-INF/content/
 */
@ResultPath(value="/WEB-INF/content/")

/**
 * 引入拦截器,要预先在xml中配置好
 */
@InterceptorRef(value="mydefault") 

/**
 * 全局返回
 */
@Result(name = "error_exception",location = "result_false.jsp")
public class UserinfoAction extends ActionSupport  {
    
	private static final long serialVersionUID = -2301203156032690317L;

	private static final Logger LOGGER = Logger.getLogger(UserinfoAction.class);
	private Integer id;
	private UserInfo userInfo;
	private List<UserInfo> userInfos;

	@Autowired
	private UserInfoService userInfoService;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public UserInfo getModel() {
		if (null != id) {
			userInfo = userInfoService.get(id);
		} else {
			userInfo = new UserInfo();
		}
		return userInfo;
	}

	/**
	 * 请求链接:http://localhost:8080/spring_struts2_Hibernate_demo/user/userinfo.action
	 */
	@Override
	public String execute() throws Exception {
		LOGGER.info("查询所有用户");
		userInfos = userInfoService.findAll();
		/**如果是SUCCESS,那么就是说,返回的view视图为:action的名字,userinfo+jsp*/
		return SUCCESS;
		
	}

	/**
     * 请求链接:http://localhost:8080/spring_struts2_Hibernate_demo/user/userinfo!detail.action?id=1
     */
	public void detail() {
		String id = ServletActionContext.getRequest().getParameter("id");
		LOGGER.info("查看用户详情：" + id);
		userInfo = userInfoService.get(Integer.valueOf(id));
		AjaxUtil.ajaxJSONResponse(userInfo);

	}

	/**
	 * 请求链接为:http://localhost:8080/spring_struts2_Hibernate_demo/user/doIt.action
	* @author 高国藩
	* @date 2015年10月23日 下午5:28:34
	* @return
	* @throws Exception
	 */
    @Action(value = "doIt", 
	         results = {
	             @Result(name = "isTrue", location = "result_true.jsp"),
	             @Result(name = "isFalse", location = "result_false.jsp")
	         }
	     )
	public String doIt() throws NumberFormatException {
		LOGGER.info("view action has been call ...");
		return "isTrue";
	}
    
}
