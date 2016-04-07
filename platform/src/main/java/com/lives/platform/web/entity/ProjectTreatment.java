package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class ProjectTreatment {
    private Integer treatmentCourseId;

    private String treatmentName;

    private BigDecimal treatmentPrice;

    private Integer storesId;

    private String createTime;

    public Integer getTreatmentCourseId() {
        return treatmentCourseId;
    }

    public void setTreatmentCourseId(Integer treatmentCourseId) {
        this.treatmentCourseId = treatmentCourseId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName == null ? null : treatmentName.trim();
    }

    public BigDecimal getTreatmentPrice() {
        return treatmentPrice;
    }

    public void setTreatmentPrice(BigDecimal treatmentPrice) {
        this.treatmentPrice = treatmentPrice;
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}