package com.zefun.web.entity;

import java.util.List;

/**
 * 库存流水
* @author 高国藩
* @date 2016年5月31日 下午5:11:44
 */
public class StockFlow {
    /** 库存流水表 */
    private Integer stockFlowId;

    /** 库存流水方式(1:入库,2:出库,3:调拨) */
    private Integer stockType;

    /** [入库{1:正常入库,2:客户退货},出库{1:销售出库,2:供应商退货,3:损坏,4:赠送,5:领用}] */
    private String flowType;

    /** 数量(,分割) */
    private String stockCount;

    /** 流水时间 */
    private String createTime;

    /** 货源门店ID */
    private Integer fromStore;

    /** 走货门店ID */
    private Integer toStore;

    /** 企业商品ID集合(,分割) */
    private String aIds;

    /** 操作人标示(0标示企业,其余为员工id) */
    private Integer operationStaff;

    /** 出库员工 */
    private Integer libraryObject;
    
    /** 出库员工 */
    private String employeeName;

    /** 备注信息 */
    private String stockDesc;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 企业代号 */
    private String storeAccount;
    
    /** 出库门店 */
    private String fromStoreName;
    
    /** 入库门店 */
    private String toStoreName;

    /** 商品信息*/
    private List<AccountGoods> accountGoods;
    
    /** 流水开始时间*/
    private String flowStartDate;
    
    /** 流水结束时间*/
    private String flowStopDate;
    
    /** 流水单号*/
    private String flowNumber;
    
    /**一比流水中包含多少数量商品*/
    private Integer count;
    
    /**一比流水中包含多少金额*/
    private Long goodsAmount;
    
    
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Long goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getFlowNumber() {
        return flowNumber;
    }

    public void setFlowNumber(String flowNumber) {
        this.flowNumber = flowNumber;
    }

    public String getFlowStartDate() {
        return flowStartDate;
    }

    public void setFlowStartDate(String flowStartDate) {
        this.flowStartDate = flowStartDate;
    }

    public String getFlowStopDate() {
        return flowStopDate;
    }

    public void setFlowStopDate(String flowStopDate) {
        this.flowStopDate = flowStopDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<AccountGoods> getAccountGoods() {
        return accountGoods;
    }

    public void setAccountGoods(List<AccountGoods> accountGoods) {
        this.accountGoods = accountGoods;
    }

    public String getFromStoreName() {
        return fromStoreName;
    }

    public void setFromStoreName(String fromStoreName) {
        this.fromStoreName = fromStoreName;
    }

    public String getToStoreName() {
        return toStoreName;
    }

    public void setToStoreName(String toStoreName) {
        this.toStoreName = toStoreName;
    }

    public Integer getStockFlowId() {
        return stockFlowId;
    }

    public void setStockFlowId(Integer stockFlowId) {
        this.stockFlowId = stockFlowId;
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

    public String getStockCount() {
        return stockCount;
    }

    public void setStockCount(String stockCount) {
        this.stockCount = stockCount == null ? null : stockCount.trim();
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
     * sdf
    * @author 高国藩
    * @date 2016年6月1日 下午3:08:18
    * @return sdf 
     */
    public String getaIds() {
        return aIds;
    }

    /**
     * sdf
    * @author 高国藩
    * @date 2016年6月1日 下午3:08:25
    * @param aIds sdf
     */
    public void setaIds(String aIds) {
        this.aIds = aIds == null ? null : aIds.trim();
    }

    public Integer getOperationStaff() {
        return operationStaff;
    }

    public void setOperationStaff(Integer operationStaff) {
        this.operationStaff = operationStaff;
    }

    public Integer getLibraryObject() {
        return libraryObject;
    }

    public void setLibraryObject(Integer libraryObject) {
        this.libraryObject = libraryObject;
    }

    public String getStockDesc() {
        return stockDesc;
    }

    public void setStockDesc(String stockDesc) {
        this.stockDesc = stockDesc == null ? null : stockDesc.trim();
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