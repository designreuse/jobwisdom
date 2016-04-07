package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* 跨店对账明细订单实体类
* @author 乐建建
* @date 2016年3月3日 下午4:38:00 
*/
public class ReconciliationOrderDetailDto {
    
    /**订单创建时间*/
    private String createTime;
    /**订单号*/
    private String detailId;
    /**会员id*/
    private Integer memberId;
    /**会员姓名*/
    private String memberName;
    /**订单类型*/
    private Integer orderType;
    /**项目名称*/
    private String projectName;
    
    /**实际价格*/
    private BigDecimal realPrice;
    
    /**流水号*/
    private String seriesId;

    public String getCreateTime() {
        return createTime;
    }

    public String getDetailId() {
        return detailId;
    }

    public Integer getMemberId() {
        return memberId;
    }
    public String getMemberName() {
        return memberName;
    }
    public Integer getOrderType() {
        return orderType;
    }
    public String getProjectName() {
        return projectName;
    }
    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public String getSeriesId() {
        return seriesId;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }
    
}
