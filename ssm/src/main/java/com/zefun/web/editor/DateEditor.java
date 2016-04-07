package com.zefun.web.editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class DateEditor extends PropertiesEditor {  
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (text == null || text.equals("")) {  
            text = "0";  
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            setValue(sdf.parse(text));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }  
  
    @Override  
    public String getAsText() {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(getValue());
    }  
} 