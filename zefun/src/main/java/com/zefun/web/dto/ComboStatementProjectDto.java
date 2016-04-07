package com.zefun.web.dto;

/**
 * 套餐项目提成Dto
* @author 洪秋霞
* @date 2015年9月25日 下午2:04:14
 */
public class ComboStatementProjectDto {
    /** 项目Id */
    private Integer projectId;
    
    /** 项目名称 */
    private String projectName;
    
    /** 项目数量*/
    private Integer projectCount;

    /** 新增次数*/
    private Integer newAddTime;
    
    /** 新增消费消费次数*/
    private Integer consumeTime;
    
    /** 剩余次数*/
    private Integer residueTime;

    /** 消费总次数*/
    private Integer consumeTatailTime;
    
    
    public Integer getConsumeTatailTime() {
        return consumeTatailTime;
    }

    public void setConsumeTatailTime(Integer consumeTatailTime) {
        this.consumeTatailTime = consumeTatailTime;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getNewAddTime() {
        return newAddTime;
    }

    public void setNewAddTime(Integer newAddTime) {
        this.newAddTime = newAddTime;
    }

    public Integer getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Integer consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Integer getResidueTime() {
        return residueTime;
    }

    public void setResidueTime(Integer residueTime) {
        this.residueTime = residueTime;
    }

}
