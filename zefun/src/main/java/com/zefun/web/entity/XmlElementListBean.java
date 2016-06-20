package com.zefun.web.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * xml 中 集合的使用
* @author 高国藩
* @date 2016年6月18日 下午5:05:47
 */
@XmlRootElement
public class XmlElementListBean {

    /**namg*/
    private String name;
    /**集合*/
    private List<?> list;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElements({
        @XmlElement(name = "xmlElementBean", type = XmlElementBean.class),
        })
    public List<?> getList() {
        return list;
    }
    public void setList(List<?> list) {
        this.list = list;
    }
    
    
    
}
