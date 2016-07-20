package com.zefun.web.entity;

/**
 * 角色信息表
* @author 高国藩
* @date 2015年9月19日 上午10:50:41
 */
public class RoleInfo {
    
    /**角色ID*/
    private Integer roleId;
    /**角色名称*/
    private String roleName;
    /**排序*/
    private Integer roleSort;
    
    /**一级菜单*/
    private String fristMenu;
    /**二级菜单*/
    private String secondMenu;
    
   
    public String getFristMenu() {
        return fristMenu;
    }

    public void setFristMenu(String fristMenu) {
        this.fristMenu = fristMenu;
    }

    public String getSecondMenu() {
        return secondMenu;
    }

    public void setSecondMenu(String secondMenu) {
        this.secondMenu = secondMenu;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }
}