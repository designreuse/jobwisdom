package com.lives.manage.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.Url;
import com.lives.manage.common.consts.View;
import com.lives.manage.common.util.DateUtils;
import com.lives.manage.common.util.StringUtils;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.User;
import com.lives.manage.web.service.SalesmanService;
import com.lives.manage.web.service.UserService;

/**
 * 业务员管理
* @author chendb
* @date 2015年10月30日 下午4:11:51
 */
@Controller
@RequestMapping("/salesman")
public class SalesmanController{
    private static final Logger logger = Logger.getLogger(MenuController.class);
    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private UserService userService;
   
    /** 用户业务员*/
    @RequestMapping(value = Url.Salesman.QUERYSALESMAN)
    @ResponseBody
    public JSONObject queryUser(@RequestBody Map<String ,Object> map,String creatorId) {
        try {
          JSONObject jsonobject=new JSONObject();
          map.put("creatorId", creatorId);
          List<User> querychannelUser =salesmanService.querychannelUser(map);
          int count =salesmanService.countchannelUser(map);
          jsonobject.put("total", count);
          jsonobject.put("rows", querychannelUser);
          return jsonobject;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryUser error : ", e);
        }
        return null;
    }
    
    
    /** 新增业务员*/
    @RequestMapping(value = Url.Salesman.ADDSALESMAN)
    @ResponseBody
    public BaseDto addUser(HttpSession session, String userName, String province, String city, String area, String realName
            ,String companyName, String addr, String phone, String fixedNum) {
        UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
        User user = new User();
        try {
         // 账户表信息录入
            user.setUserName(userName);
            // 密码加密获取盐值
            // 默认生成密码密码123456
            String password = "123456";
            String hash = StringUtils.encryptPwd(password);
            password = hash.split(":")[0];
            String salt = hash.split(":")[1];
            user.setSalt(salt);
            user.setPassWord(password);
            user.setProvince(province);
            user.setCity(city);
            user.setArea(area);
            user.setAddr(addr);
            user.setCompanyName(companyName);
            user.setPhone(phone);
            user.setFixedNum(fixedNum);
            user.setRoleId(3);//初始化设置只是业务员角色
            user.setRealName(realName);
            user.setCreatorId(userInfoDto.getUserId());
            user.setCreateTime(DateUtils.getCurTime());
            int result=userService.addUser(user);
            if(result==1){
                return new BaseDto(-1, "后台帐号已经存在了 请重新输入！");
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            
            logger.error("queryUser error : ", e);
        }
        return new BaseDto(0, "新增成功！！");
    }
    
    
    @RequestMapping(value = Url.Salesman.UPDATESALESMAN)
    @ResponseBody
    public BaseDto updateUser(HttpSession session,Integer userId, String userName,String province,String city,String area,String realName
            ,String companyName,String addr,String phone,String fixedNum) {
        User user = new User();
        UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
        try {
            // 账户表信息录入
            user.setUserId(userId);
            user.setUserName(userName);
            user.setRealName(realName);
            user.setProvince(province);
            user.setCity(city);
            user.setArea(area);
            user.setPhone(phone);
            user.setFixedNum(fixedNum);
            user.setRoleId(3);
            user.setAddr(addr);
            user.setCompanyName(companyName);
            user.setUpdatorId(userInfoDto.getUserId());
            user.setUpdateTime(DateUtils.getCurTime());
            // 用户表信息录入
            int result=userService.updateUser(user);
            if(result==1){
                return new BaseDto(-1, "后台帐号已经存在了 请重新输入！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updateUser error : ", e);
            return new BaseDto(-1, "updateUser error");
        }
        return new BaseDto(0, "ok");
    }
    
    /**
     * 业务管理页面
    * @author chendb
    * @date 2015年10月30日 下午4:17:57
    * @return
     */
    @RequestMapping("view/salesman")
    public ModelAndView publicquery(HttpSession session) {
        ModelAndView model = new ModelAndView();
        UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
        try {
            model.addObject("userId",userInfoDto.getUserId());
            model.setViewName(View.Salesman.QUERY);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("publicquery error : ", e);
        }
        return model;
    }
    

}
