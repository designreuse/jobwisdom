package com.example.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Teacher {

    private String name;
    private Integer age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Teacher() {
        // TODO Auto-generated constructor stub
    }
    public Teacher(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }
    
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Optional<Integer> max = list.stream().reduce(Math::max);
        max.ifPresent(value -> System.out.println(value));
        Double avarage = list.stream().collect(Collectors.averagingInt(item -> item));
    }
}
