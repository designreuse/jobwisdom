package org.andy.shop.entity;

import java.io.Serializable;

public class IdCardPk implements Serializable {
    private int num;
    private int age;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
