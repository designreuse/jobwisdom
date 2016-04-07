package com.example.main;

import java.util.Arrays;
import java.util.List;

public class Stream {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(new Integer[]{1,2,3,null,4,null,5});
        System.out.println(nums.stream().filter(num -> num!=null).count());
    }
}
