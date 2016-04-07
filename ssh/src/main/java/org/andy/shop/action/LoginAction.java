package org.andy.shop.action;

import org.andy.shop.dao.impl.UserInfoDaoImpl;
import org.andy.shop.entity.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sun.net.httpserver.Authenticator.Success;

@Component
public class LoginAction implements ModelDriven<UserInfo>,Preparable{

    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    @Autowired
    private UserInfoDaoImpl userInfoDaoImpl;
    /**
     * 给action中的对象赋值:
     * 1)可以使用ModelDriven<T>配合Preparable来使用,下面给出了实例
     * 2)还可以在jsp中将input的name设置为userInfo.id来进行赋值
     */
    private UserInfo userInfo;
    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * 下面方法为xml方式进行请求struts,不适用注解方式
     * 请求方式:http://localhost:8080/ssh-2/login/loginAction_viewAction.action
    * @author 高国藩
    * @date 2015年10月23日 下午8:59:20
    * @return
     */
    public String viewAction() {
        LOGGER.info("viewAction | 通配符请求方法");
//        Integer.parseInt("throw one exception ....");
        return "view";
    }
    
    public String saveUserInfo(){
        System.out.println(this.userInfo.toString());
        Integer userId = userInfoDaoImpl.save(userInfo);
        System.out.println(userId);
        return "success";
    }
    
    /**
     * 为每一个方法定制一个prepar_MethodName,它会为saveUserInfo准备一个model对象,当ModelDriven调用getModelf
    * @author 高国藩
    * @date 2015年11月4日 下午1:33:49
     */
    public void prepareSaveUserInfo(){
        this.userInfo = new UserInfo();
    }
    
    public void prepare() throws Exception {
        System.out.println("prepare ...");
    }
    /**
     * 为ModelDriven返回一个实例
     */
    public UserInfo getModel() {
        return userInfo;
    }
}
