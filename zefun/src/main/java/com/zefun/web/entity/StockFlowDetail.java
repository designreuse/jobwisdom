package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 商品进出库明细
* @author 高国藩
* @date 2016年8月10日 下午2:32:15
 */
public class StockFlowDetail {
    
    /** 明细ID */
    private Integer flowDetailId;

    /** 单号 */
    private String flowNumber;

    /** 库存流水方式(1:入库,2:出库,3:调拨) */
    private Integer stockType;

    /** [入库{1:正常入库,2:客户退货,3:商品调拨},出库{1:正常出库,2:供应商退货,3:损坏,4:赠送,5:领用}] */
    private String flowType;

    /** 发生该事件，原商品库存 */
    private Integer goodsStockCount;

    /** 商品进出口数量 */
    private Integer flowCount;

    /** 金额 */
    private BigDecimal flowAmount;

    /** 商品成本 */
    private BigDecimal costPrice;

    /** 事件时间 */
    private String createTime;

    /** 出库门店 */
    private Integer fromStore;

    /** 入库门店 */
    private Integer toStore;

    /** 企业商品ID */
    private Integer aId;

    /** 操作人ID */
    private Integer employeeId;
    
    /** 员工名称 */
    private String employeeName;

    /** 该明细是否删除(默认0) */
    private Integer isDeleted;
    
    /** 日期区间 */
    private String detailStartDate;
    
    /** 日期区间 */
    private String detailStopDate;

    /** 企业代号 */
    private String storeAccount;
    
    /** 企业商品 */
    private AccountGoods accountGoods;
    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public AccountGoods getAccountGoods() {
        return accountGoods;
    }

    public void setAccountGoods(AccountGoods accountGoods) {
        this.accountGoods = accountGoods;
    }

    public String getDetailStartDate() {
        return detailStartDate;
    }

    public void setDetailStartDate(String detailStartDate) {
        this.detailStartDate = detailStartDate;
    }

    public String getDetailStopDate() {
        return detailStopDate;
    }

    public void setDetailStopDate(String detailStopDate) {
        this.detailStopDate = detailStopDate;
    }

    public Integer getFlowDetailId() {
        return flowDetailId;
    }

    public void setFlowDetailId(Integer flowDetailId) {
        this.flowDetailId = flowDetailId;
    }

    public String getFlowNumber() {
        return flowNumber;
    }

    public void setFlowNumber(String flowNumber) {
        this.flowNumber = flowNumber == null ? null : flowNumber.trim();
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType == null ? null : flowType.trim();
    }

    public Integer getGoodsStockCount() {
        return goodsStockCount;
    }

    public void setGoodsStockCount(Integer goodsStockCount) {
        this.goodsStockCount = goodsStockCount;
    }

    public Integer getFlowCount() {
        return flowCount;
    }

    public void setFlowCount(Integer flowCount) {
        this.flowCount = flowCount;
    }

    public BigDecimal getFlowAmount() {
        return flowAmount;
    }

    public void setFlowAmount(BigDecimal flowAmount) {
        this.flowAmount = flowAmount;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getFromStore() {
        return fromStore;
    }

    public void setFromStore(Integer fromStore) {
        this.fromStore = fromStore;
    }

    public Integer getToStore() {
        return toStore;
    }

    public void setToStore(Integer toStore) {
        this.toStore = toStore;
    }

    /**
     * id
    * @author 高国藩
    * @date 2016年8月10日 下午2:32:46
    * @return id
     */
    public Integer getaId() {
        return aId;
    }

    /**
     * id
    * @author 高国藩
    * @date 2016年8月10日 下午2:32:53
    * @param aId id
     */
    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount == null ? null : storeAccount.trim();
    }
}