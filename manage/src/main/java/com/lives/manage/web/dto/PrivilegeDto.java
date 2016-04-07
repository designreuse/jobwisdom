package com.lives.manage.web.dto;

import java.util.List;

public class PrivilegeDto {
    private Integer id;
    private String type;
    private String text;
    private String url;
    private String classname;
    private Integer refId;
    private List<PrivilegeDto> nodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public List<PrivilegeDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<PrivilegeDto> nodes) {
        this.nodes = nodes;
    }

    public PrivilegeDto() {
        // TODO Auto-generated constructor stub
    }

    public PrivilegeDto(String text, List<PrivilegeDto> nodes) {
        super();
        this.text = text;
        this.nodes = nodes;
    }

}
