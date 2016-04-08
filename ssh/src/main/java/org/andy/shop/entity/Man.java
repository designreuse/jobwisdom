package org.andy.shop.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "man", catalog = "springtest")
public class Man {
    private int id;
    private String name;
    private Set<Women> womens;

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
     * 1.如果两者之间有关联的时候,对于A B 两个 如果默认,那么就是两者都要保存一次,设置了cascade.ALL所有的dml都会设置了级连接;
     * 2.CascadeType.ALL 任何dml;CascadeType.PERSIST 任何保存;CascadeType.REMOVE 任何删除时候,执行关联;
     * 3.cascade在Man中设置了,只对Man有用,当保存womens的时候,相关联的Man不会存入;
     * 4.@JoinTable 配置中间表的信息,name是中间表的名称,前后分别是两个字段的名称
     * 5.women类中必须要写(mappedBy="womens"),如果这个注解要分开写,不能写在配置中间表的类中.
     */
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "center_table", joinColumns = { @JoinColumn(name = "man_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "women_id", referencedColumnName = "id") })
    public Set<Women> getWomens() {
        return womens;
    }

    public void setWomens(Set<Women> womens) {
        this.womens = womens;
    }
}
