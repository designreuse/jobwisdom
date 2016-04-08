package org.andy.shop.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "id_card", catalog = "springtest")
@IdClass(IdCardPk.class)
public class IdCard {
    private int num;
    private int age;
    private Person person;

    @Id
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Id
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 在双向关联中肯定有一处是写入了mappedBy,一般是在没有引用外键即被引用的对象中写入;
     */
    @OneToOne(mappedBy = "idCard")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
