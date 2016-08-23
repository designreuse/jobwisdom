package com.zefun.web.entity;

import java.math.BigDecimal;

public class MemberComboProject {
    /**
     *  详情标识
     */
    private Integer detailId;

    /**
     *  记录标识
     */
    private Integer recordId;

    /**
     *  套餐标识
     */
    private Integer comboId;

    /**
     *  项目标识
     */
    private Integer projectId;

    /**
     *  项目名称
     */
    private String projectName;

    /**
     *  项目价格
     */
    private BigDecimal projectPrice;

    /**
     *  项目图片
     */
    private String projectImage;

    /**
     *  项目数量
     */
    private Integer projectCount;

    /**
     *  剩余数量
     */
    private Integer remainingCount;

    /**
     *  创建时间
     */
    private String createTime;

    /**
     *  是否删除(0:未删除,1:已删除)
     */
    private Integer isDeleted;

    /**
     *  修改时间
     */
    private String updateTime;

    /**
     *  最后操作人标识
     */
    private Integer lastOperatorId;

    /**
     *  是否已过期（0未过期，1已过期）
     */
    private Integer isTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.detail_id
     *
     * @return the value of member_combo_project.detail_id
     *
     * @mbggenerated
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.detail_id
     *
     * @param detailId the value for member_combo_project.detail_id
     *
     * @mbggenerated
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.record_id
     *
     * @return the value of member_combo_project.record_id
     *
     * @mbggenerated
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.record_id
     *
     * @param recordId the value for member_combo_project.record_id
     *
     * @mbggenerated
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.combo_id
     *
     * @return the value of member_combo_project.combo_id
     *
     * @mbggenerated
     */
    public Integer getComboId() {
        return comboId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.combo_id
     *
     * @param comboId the value for member_combo_project.combo_id
     *
     * @mbggenerated
     */
    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.project_id
     *
     * @return the value of member_combo_project.project_id
     *
     * @mbggenerated
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.project_id
     *
     * @param projectId the value for member_combo_project.project_id
     *
     * @mbggenerated
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.project_name
     *
     * @return the value of member_combo_project.project_name
     *
     * @mbggenerated
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.project_name
     *
     * @param projectName the value for member_combo_project.project_name
     *
     * @mbggenerated
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.project_price
     *
     * @return the value of member_combo_project.project_price
     *
     * @mbggenerated
     */
    public BigDecimal getProjectPrice() {
        return projectPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.project_price
     *
     * @param projectPrice the value for member_combo_project.project_price
     *
     * @mbggenerated
     */
    public void setProjectPrice(BigDecimal projectPrice) {
        this.projectPrice = projectPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.project_image
     *
     * @return the value of member_combo_project.project_image
     *
     * @mbggenerated
     */
    public String getProjectImage() {
        return projectImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.project_image
     *
     * @param projectImage the value for member_combo_project.project_image
     *
     * @mbggenerated
     */
    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage == null ? null : projectImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.project_count
     *
     * @return the value of member_combo_project.project_count
     *
     * @mbggenerated
     */
    public Integer getProjectCount() {
        return projectCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.project_count
     *
     * @param projectCount the value for member_combo_project.project_count
     *
     * @mbggenerated
     */
    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.remaining_count
     *
     * @return the value of member_combo_project.remaining_count
     *
     * @mbggenerated
     */
    public Integer getRemainingCount() {
        return remainingCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.remaining_count
     *
     * @param remainingCount the value for member_combo_project.remaining_count
     *
     * @mbggenerated
     */
    public void setRemainingCount(Integer remainingCount) {
        this.remainingCount = remainingCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.create_time
     *
     * @return the value of member_combo_project.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.create_time
     *
     * @param createTime the value for member_combo_project.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.is_deleted
     *
     * @return the value of member_combo_project.is_deleted
     *
     * @mbggenerated
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.is_deleted
     *
     * @param isDeleted the value for member_combo_project.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.update_time
     *
     * @return the value of member_combo_project.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.update_time
     *
     * @param updateTime the value for member_combo_project.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.last_operator_id
     *
     * @return the value of member_combo_project.last_operator_id
     *
     * @mbggenerated
     */
    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.last_operator_id
     *
     * @param lastOperatorId the value for member_combo_project.last_operator_id
     *
     * @mbggenerated
     */
    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_combo_project.is_time
     *
     * @return the value of member_combo_project.is_time
     *
     * @mbggenerated
     */
    public Integer getIsTime() {
        return isTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_combo_project.is_time
     *
     * @param isTime the value for member_combo_project.is_time
     *
     * @mbggenerated
     */
    public void setIsTime(Integer isTime) {
        this.isTime = isTime;
    }
}