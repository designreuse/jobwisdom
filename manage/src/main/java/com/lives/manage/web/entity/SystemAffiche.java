package com.lives.manage.web.entity;

public class SystemAffiche {
    private Integer afficheId;				//公告编号

    private String afficheTitle;			//公告标题

    private String afficheContent;			//公告内容	

    private String afficheRole;				//通知角色集合(多个,分割)

    private Integer creatorId;				//创建者编号

    private String createTime;				//发布时间
    	
    private String afficheClasses;			//公告类别

    private String afficheStatus;			//发布状态

    private String updateTime;				//更新时间
    
    private String afficheTime;				//公告时间
    private String stateName;
    


    public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getAfficheId() {
        return afficheId;
    }

    public void setAfficheId(Integer afficheId) {
        this.afficheId = afficheId;
    }

    public String getAfficheTitle() {
        return afficheTitle;
    }

    public void setAfficheTitle(String afficheTitle) {
        this.afficheTitle = afficheTitle == null ? null : afficheTitle.trim();
    }

    public String getAfficheContent() {
        return afficheContent;
    }

    public void setAfficheContent(String afficheContent) {
        this.afficheContent = afficheContent == null ? null : afficheContent.trim();
    }

    public String getAfficheRole() {
        return afficheRole;
    }

    public void setAfficheRole(String afficheRole) {
        this.afficheRole = afficheRole == null ? null : afficheRole.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getAfficheClasses() {
        return afficheClasses;
    }

    public void setAfficheClasses(String afficheClasses) {
        this.afficheClasses = afficheClasses == null ? null : afficheClasses.trim();
    }

    public String getAfficheStatus() {
        return afficheStatus;
    }

    public void setAfficheStatus(String afficheStatus) {
        this.afficheStatus = afficheStatus == null ? null : afficheStatus.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getAfficheTime() {
        return afficheTime;
    }

    public void setAfficheTime(String afficheTime) {
        this.afficheTime = afficheTime == null ? null : afficheTime.trim();
    }
}