package com.example.main;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExamoleImp implements Examole{

    @Override
    public void getCount(String str) {
        System.out.println(str);
    }
    
    public static void system(ExamoleImp imp){
        imp.getCount(null);
    }
    
    public static void main(String[] args) {
        ExamoleImp examoleImp = new ExamoleImp();
        Set<Integer> set = Stream.of("1", "2", "3", "4").map(Integer::valueOf).collect(Collectors.toSet());
        System.out.println(set);
    }

    private void system(Object imp) {
        // TODO Auto-generated method stub
        
    }

}
