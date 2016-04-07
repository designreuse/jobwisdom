package com.lives.manage.web.entity;

public class Privilege {
    private Integer privilegeId;

    private int privilegeType;

    private String privilegeName;

    private String privilegeValue;

    private Integer refPrivilegeId;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

   

    public int getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(int privilegeType) {
		this.privilegeType = privilegeType;
	}

	public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName == null ? null : privilegeName.trim();
    }

    public String getPrivilegeValue() {
        return privilegeValue;
    }

    public void setPrivilegeValue(String privilegeValue) {
        this.privilegeValue = privilegeValue == null ? null : privilegeValue.trim();
    }

    public Integer getRefPrivilegeId() {
        return refPrivilegeId;
    }

    public void setRefPrivilegeId(Integer refPrivilegeId) {
        this.refPrivilegeId = refPrivilegeId;
    }

	@Override
	public String toString() {
		return "Privilege [privilegeId=" + privilegeId + ", privilegeType="
				+ privilegeType + ", privilegeName=" + privilegeName
				+ ", privilegeValue=" + privilegeValue + ", refPrivilegeId="
				+ refPrivilegeId + "]";
	}
    
}