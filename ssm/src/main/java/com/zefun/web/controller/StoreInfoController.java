package com.zefun.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.Employee;
import com.zefun.web.entity.StoreInfo;

@Controller
public class StoreInfoController{
    
    @RequestMapping(value = Url.StoreInfo.listSave, method = RequestMethod.GET)
    public ModelAndView listStore(Model model){
        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setDate(new Date());
        storeInfo.setId(1);
        model.addAttribute("storeInfo", storeInfo);
        return new ModelAndView("/wechat/store");
    }
    
    /**
     * 数据绑定
    * @author 高国藩
    * @date 2015年11月4日 下午1:13:03
    * @param storeInfo
    * @return
     */
    @RequestMapping(value = Url.StoreInfo.actionSave, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionSave(StoreInfo storeInfo){
        System.out.println(storeInfo.toString());
        System.out.println(storeInfo.getDate());
        return new BaseDto(0, "fail");
    }
    
    /**
     * 测试类型转换器
    * @author 高国藩
    * @date 2015年11月4日 下午1:06:24
    * @param storeInfo
    * @return
     */
    @RequestMapping(value = Url.StoreInfo.employeeSave, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionEmployeeSave(Employee employee){
        System.out.println(employee.toString());
        return new BaseDto(0, "fail");
    }

}
