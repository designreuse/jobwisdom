package com.zefun.web.entity;

/**
 * 
* @author 骆峰
* @date 2016年7月20日 下午7:53:33
 */
public class AccountRoleInfo {
    /** 新建角色标识 */
    private Integer accountRoleId;

    /** 名称 */
    private String accountRoleName;

    /** 引用角色标识 */
    private Integer roleId;
    
    /** 一级菜单 */
    private String fristMenu;

    /** 二级菜单 */
    private String secondMenu;
    
    /** 门店id */
    private Integer storeId;

    /** 企业id */
    private String storeAccount;

    /** (0正常，1删除) */
    private Integer isDeleted;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }


    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getFristMenu() {
        return fristMenu;
    }

    public void setFristMenu(String fristMenu) {
        this.fristMenu = fristMenu == null ? null : fristMenu.trim();
    }

    public String getSecondMenu() {
        return secondMenu;
    }

    public void setSecondMenu(String secondMenu) {
        this.secondMenu = secondMenu == null ? null : secondMenu.trim();
    }

    public Integer getAccountRoleId() {
        return accountRoleId;
    }

    public void setAccountRoleId(Integer accountRoleId) {
        this.accountRoleId = accountRoleId;
    }

    public String getAccountRoleName() {
        return accountRoleName;
    }

    public void setAccountRoleName(String accountRoleName) {
        this.accountRoleName = accountRoleName == null ? null : accountRoleName.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}