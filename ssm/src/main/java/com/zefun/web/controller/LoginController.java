package com.zefun.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zefun.web.entity.UserInfo;
import com.zefun.web.mapper.UserInfoMapper;

@Controller
public class LoginController {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public static void main(String[] args) throws IOException {
        Properties prop =  new  Properties();    
        FileInputStream fin = new FileInputStream("src/main/resources/properties/url.properties");
        InputStream in = fin;
        prop.load(in);    
    }
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(UserInfo userInfo,HttpServletRequest request) throws Exception{
         Properties prop =  new  Properties();    
         InputStream in = request.getSession().getServletContext().getResourceAsStream("WEB-INF/classes/properties/url.properties");
         prop.load(in);    
         String url = prop.getProperty("url"); 
         UserInfo user =  userInfoMapper.selectUserByParam(userInfo);
         if(user!=null){
             return  "redirect:"+url;
         }else{
             return "redirect:http://localhost:8080/SpringMvc_Mybatis";
         }
    }
}
