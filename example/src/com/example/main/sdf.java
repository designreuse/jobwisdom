package com.example.main;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class sdf {


    public static void main(String[] args) throws ParseException {
        String str = "2016-03-05 00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Date date = sdf.parse(str);
        System.out.println(date.getDate());

    }
}
