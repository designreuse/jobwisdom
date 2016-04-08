package org.andy.shop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person", catalog = "springtest")
public class Person {
    private int id;
    private String name;
    private IdCard idCard;

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
/**
 *    1.@JoinColumn 写入引入外键字段在数据库中的名称,一般来说会默认给,所以写入的时候一定要注意;
 *    3.只要是涉及了外键的引入,就要配置JoinColumn;
 *    2.referencedColumnName 引用的另外一张表的列名,防止不是主建关联的情况,还有就是联合主建;
 */
    @OneToOne
    @JoinColumns( { 
        @JoinColumn(name = "num_id", referencedColumnName = "num"),
            @JoinColumn(name="age_id",referencedColumnName="age") })
    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", idCard=" + idCard + "]";
	}

}