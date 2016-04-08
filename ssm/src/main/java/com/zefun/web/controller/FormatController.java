package com.zefun.web.controller;

import java.math.RoundingMode;
import java.util.Date;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.number.CurrencyFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zefun.web.entity.FormatModel;


@Controller
@RequestMapping(value = "/format")
public class FormatController {
    
    @RequestMapping(value="/test", method = {RequestMethod.GET})
    public String test(Model model) throws NoSuchFieldException, SecurityException{

        if(!model.containsAttribute("contentModel")){
            
            FormatModel formatModel=new FormatModel();

            CurrencyFormatter currencyFormatter = new CurrencyFormatter();  
            currencyFormatter.setFractionDigits(2);//保留2位小数
            currencyFormatter.setRoundingMode(RoundingMode.HALF_UP);//向（距离）最近的一边舍入，如果两边（的距离）是相等的则向上舍入（四舍五入）
            
            DateFormatter dateFormatter=new DateFormatter();
            dateFormatter.setPattern("yyyy-MM-dd HH:mm:ss");
            
            DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();  
            conversionService.addFormatter(currencyFormatter); 
            conversionService.addFormatter(dateFormatter); 
            
            //下面的没有使用DefaultFormattingConversionService,需要手动进行国际化设置
            /*Locale locale=LocaleContextHolder.getLocale();
            formatModel.setMoney(currencyFormatter.print(12345.678, locale));
            formatModel.setDate(dateFormatter.print(new Date(), locale)); */      
            
            
            /*formatModel.setMoney(conversionService.convert(12345.678, String.class));
            formatModel.setDate(conversionService.convert(new Date(), String.class));   */ 
            
            model.addAttribute("contentModel", formatModel);
        }
        return "formattest";
    }
    
}
