package org.andy.shop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car", catalog = "springtest")
public class Car {
    private int id;
    private String name;
    private Type type;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "car_type_Id", referencedColumnName = "id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
