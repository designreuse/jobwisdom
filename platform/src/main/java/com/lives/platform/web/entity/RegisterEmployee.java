package com.lives.platform.web.entity;

public class RegisterEmployee {
    private Integer registerEmployeeId;

    private Integer postRegisterId;

    private Integer employeeId;

    private String creationTime;

    private Integer creator;

    public Integer getRegisterEmployeeId() {
        return registerEmployeeId;
    }

    public void setRegisterEmployeeId(Integer registerEmployeeId) {
        this.registerEmployeeId = registerEmployeeId;
    }

    public Integer getPostRegisterId() {
        return postRegisterId;
    }

    public void setPostRegisterId(Integer postRegisterId) {
        this.postRegisterId = postRegisterId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime == null ? null : creationTime.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
}