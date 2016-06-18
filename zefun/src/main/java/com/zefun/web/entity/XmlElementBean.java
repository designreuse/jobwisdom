package com.zefun.web.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * xml 传输数据,用于微信media素材上传
* @author 高国藩
* @date 2016年6月18日 下午5:02:55
 */
@XmlRootElement
public class XmlElementBean {

    /**id*/
    private int id;
    /**id*/
    private String name;
    /**id*/
    private String email;
    /**id*/
    private String address;
    
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
