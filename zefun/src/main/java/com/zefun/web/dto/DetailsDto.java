package com.zefun.web.dto;

/**
 * 渠道会议费用详情
* @author 高国藩
* @date 2016年1月9日 下午7:51:38
 */
public class DetailsDto {

    /**报名id*/
    private Integer id;
    /**报名人员姓名*/
    private String name;
    /**会议名称*/
    private String conferenceName;
    /**一级奖励人员数量*/
    private Integer mainCount;
    /**二级奖励人员数量*/
    private Integer branchCount;
    /**一级奖励人员金额*/
    private Integer mainAmount;
    /**二级奖励人员金额*/
    private Integer branchAmount;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getMainCount() {
        return mainCount;
    }
    public void setMainCount(Integer mainCount) {
        this.mainCount = mainCount;
    }
    public Integer getBranchCount() {
        return branchCount;
    }
    public void setBranchCount(Integer branchCount) {
        this.branchCount = branchCount;
    }
    public Integer getMainAmount() {
        return mainAmount;
    }
    public void setMainAmount(Integer mainAmount) {
        this.mainAmount = mainAmount;
    }
    public Integer getBranchAmount() {
        return branchAmount;
    }
    public void setBranchAmount(Integer branchAmount) {
        this.branchAmount = branchAmount;
    }
    public String getConferenceName() {
        return conferenceName;
    }
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }
   
}
