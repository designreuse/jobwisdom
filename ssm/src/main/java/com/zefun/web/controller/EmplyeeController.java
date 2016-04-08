package com.zefun.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zefun.web.entity.Employee;

@Controller
public class EmplyeeController {
    
    @RequestMapping(value="/save/emp")
    public void saveEmplyee(Employee employee){
        System.out.println(employee.toString());
    }

    /**
     * 数据校验
    * @author 高国藩
    * @date 2015年11月5日 下午8:45:37
    * @param employee
    * @param result
    * @param map
    * url http://localhost:8080/ssm/save/emp/result?lastName=jack
     */
    @RequestMapping(value="/save/emp/result")
    public void saveResultEmplyee(@Valid Employee employee, BindingResult result,   
            Map<String, Object> map){
        System.out.println("save: " + employee);  
        
        if(result.getErrorCount() > 0){  
            System.out.println("出错了!");  
              
            for(FieldError error:result.getFieldErrors()){  
                System.out.println(error.getField() + ":" + error.getDefaultMessage());  
            }  
        }  
    }
    
    @RequestMapping(value="/jsr/view", method = {RequestMethod.GET})
    public String test(Model model){
        if(!model.containsAttribute("employee")){
            model.addAttribute("employee", new Employee());
        }
        return "employee/employee";
    }
}
