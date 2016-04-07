package org.andy.shop.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "women", catalog = "springtest")
public class Women {
    private int id;
    private String name;
    private Set<Man> mans;

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

    @ManyToMany(mappedBy = "womens", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    public Set<Man> getMans() {
        return mans;
    }

    public void setMans(Set<Man> mans) {
        this.mans = mans;
    }
}
