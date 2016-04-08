package com.example.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NameAlreadyBoundException;

/**
* @author 张进军
* @date Nov 23, 2015 4:54:21 PM 
*/
public class FileUtil {
    public static void main2(String[] args) {
        String path = "/Users/smallpang/Downloads/zefun图库";
        File file = new File(path);
        File[] tempList = file.listFiles();
        System.out.println("该目录下对象个数：" + tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            File[] fileList = file.listFiles();
            for (int j = 0; j < fileList.length; j++) {
                File f = fileList[0];
                
            }
        }
    }
    public static void main(String[] args) {
        Teacher t1 = new Teacher("小胖", 1);
        Teacher t2 = new Teacher("小高", 4);
        List<Teacher> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        List<String> hasNames = list.stream().map(t -> t.getName()).collect(Collectors.toList());
        List<String> names = new ArrayList<>();
        names.add("小胖");
        names.add("小高");
        names.add("小静");
        names.add("小妹");
        List<Teacher> noNames = names
                .stream()
                .filter(str -> !hasNames.contains(str))
                .map(str -> new Teacher(str, 0)).collect(Collectors.toList());
        System.out.println(noNames);
    }
}
