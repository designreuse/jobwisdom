package com.zefun.web.entity;

/**
* @author 乐建建
* @date 2016年3月10日 下午8:24:30 
*/
public class EmployeeAccount {
    /**员工id*/
    private Integer employeeId;
    
    /**员工账号*/
    private String employeeAccount;
    
    /**员工密码*/
    private String employeePwd;
    
    /**密码盐值*/
    private String pwdSalt;
    
    /**角色标志*/
    private Integer roleId;
    
    /**是否可用*/
    private Integer abledUsed;
    
    /**创建时间*/
    private String createTime;
    
    /**更新时间*/
    private String updateTime;
    
    /**最后操作人标志*/
    private String lastOperatorId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(String employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public String getEmployeePwd() {
        return employeePwd;
    }

    public void setEmployeePwd(String employeePwd) {
        this.employeePwd = employeePwd;
    }

    public String getPwdSalt() {
        return pwdSalt;
    }

    public void setPwdSalt(String pwdSalt) {
        this.pwdSalt = pwdSalt;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAbledUsed() {
        return abledUsed;
    }

    public void setAbledUsed(Integer abledUsed) {
        this.abledUsed = abledUsed;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(String lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
}
