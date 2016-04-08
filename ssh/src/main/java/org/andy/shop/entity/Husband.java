package org.andy.shop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "husband", catalog = "springtest")
public class Husband {
    private int id;
    private String name;
    private int age;
    private Wife wife;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "husband_Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "husband", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public Husband() {
        // TODO Auto-generated constructor stub
    }

    public Husband(String name, int age, Wife wife) {
        this.name = name;
        this.age = age;
        this.wife = wife;
    }

}
