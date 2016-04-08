package com.zefun.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zefun.web.editor.DateEditor;
import com.zefun.web.editor.DoubleEditor;
import com.zefun.web.editor.FloatEditor;
import com.zefun.web.editor.IntegerEditor;
import com.zefun.web.editor.LongEditor;

@Controller
public class BaseController {
    
    @InitBinder  
    protected void initBinder(WebDataBinder binder) {  
        //binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
        binder.registerCustomEditor(int.class, new IntegerEditor());  
        binder.registerCustomEditor(long.class, new LongEditor());  
        binder.registerCustomEditor(double.class, new DoubleEditor());  
        binder.registerCustomEditor(float.class, new FloatEditor());
        binder.registerCustomEditor(Date.class, new DateEditor());  
        //下面是spring自定义提供的
        //binder.registerCustomEditor(int.class, new CustomNumberEditor(int.class, true));
        //binder.registerCustomEditor(long.class, new CustomNumberEditor(long.class, true)); 
    } 
    
}
