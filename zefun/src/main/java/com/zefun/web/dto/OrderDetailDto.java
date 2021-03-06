package com.zefun.web.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务明细
* @author 张进军
* @date 2015年9月18日 上午10:33:53
 */
public class OrderDetailDto implements Serializable{

    /** @Fields serialVersionUID : */
    private static final long serialVersionUID = 1L;

    /** 明细标识 */
    private Integer detailId;
    
    /** 明细编号*/
    private String detailCode;

    /** 订单标识 */
    private Integer orderId;
    
    /** 部门标识*/
    private Integer deptId;
    
    /** 商品标识*/
    private Integer goodsId;
    
    /**  大项标识*/
    private Integer categoryId;



    
    /** 订单类型(1:项目,2:商品,3:套餐,4:开卡,5:充值,6:升级,7:赠送,8:还款) */
    private Integer orderType;

    /** 是否预约(1:是,2:否) */
    private Integer isAppoint;
    
    /** */
    private BigDecimal appointOff;

    /** 是否指定(1:是,2:否) */
    private Integer isAssign;

    /** 项目标识 */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 项目价格 */
    private BigDecimal projectPrice;

    /** 项目数量 */
    private Integer projectCount;

    /** 项目图片 */
    private String projectImage;

    /** 项目实际金额 */
    private BigDecimal realPrice;
    
    /** 明细业绩值*/
    private Double detailCalculate;

    /** 折扣价格 */
    private BigDecimal discountAmount;
    
    /** 折扣类型(1:疗程，2:优惠券，3:礼金)*/
    private Integer offType;
    
    /** 礼金抵扣*/
    private BigDecimal giftAmount;
    
    /** 签单金额*/
    private String freeAmount;

    /** 疗程标识 */
    private Integer comboId;

    /** 优惠券标识 */
    private Integer couponId;

    /** 服务时长 */
    private Integer serviceLength;

    /** 订单状态(1：等待中、2：服务中、3：已结束) */
    private Integer orderStatus;
    
    /** 签单备注*/
    private String orderRemark;

    /** 修改时间 */
    private String updateTime;
    
    /** 优惠信息*/
    private String privilegeInfo;
    
    /** 部门名称*/
    private String deptName;
    
  

    /** 优惠金额*/
    private BigDecimal privilegeMoney;
    
    /** 是否修改*/
    private Integer isUpdate;
    
    
    /** 步骤集合 */
    private List<OrderDetailStepDto> stepList;
    
    /** 员工提成*/
    private List<EmployeeCommissionDto> commissionList;
    
    /** 1现金，2卡金*/
    private Integer cashCardType;
    
    /** 商品名称*/
    private String goodsName;
    /** 商品大项*/
    private String categoryName;
    /**时间 */
    private String createTime;
    
    /** 疗程名称*/
    private String comboName;
    

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public Integer getCashCardType() {
        return cashCardType;
    }

    public void setCashCardType(Integer cashCardType) {
        this.cashCardType = cashCardType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


	public Double getDetailCalculate() {
        return detailCalculate;
    }

    public void setDetailCalculate(Double detailCalculate) {
        this.detailCalculate = detailCalculate;
    }

    public List<EmployeeCommissionDto> getCommissionList() {
        return commissionList;
    }

    public void setCommissionList(List<EmployeeCommissionDto> commissionList) {
        this.commissionList = commissionList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public BigDecimal getAppointOff() {
		return appointOff;
	}

	public void setAppointOff(BigDecimal appointOff) {
		this.appointOff = appointOff;
	}

	public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public BigDecimal getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(BigDecimal giftAmount) {
        this.giftAmount = giftAmount;
    }

    

    public String getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getOffType() {
        return offType;
    }

    public void setOffType(Integer offType) {
        this.offType = offType;
    }

    public String getPrivilegeInfo() {
        return privilegeInfo;
    }

    public void setPrivilegeInfo(String privilegeInfo) {
        this.privilegeInfo = privilegeInfo;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public BigDecimal getPrivilegeMoney() {
        return privilegeMoney;
    }

    public void setPrivilegeMoney(BigDecimal privilegeMoney) {
        this.privilegeMoney = privilegeMoney;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    /** @param detailId 明细标识 */
    public void setDetailId(Integer detailId){
        this.detailId = detailId;
    }

    /** @return 明细标识 */
    public Integer getDetailId(){
        return detailId;
    }

    /** @param orderId  订单标识 */
    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    /** @return 订单标识 */
    public Integer getOrderId(){
        return orderId;
    }

    /** @param orderType    订单类型(1:项目,2:商品,3:疗程) */
    public void setOrderType(Integer orderType){
        this.orderType = orderType;
    }

    /** @return 订单类型(1:项目,2:商品,3:疗程) */
    public Integer getOrderType(){
        return orderType;
    }

    /** @param isAppoint    是否预约(1:是,2:否) */
    public void setIsAppoint(Integer isAppoint){
        this.isAppoint = isAppoint;
    }

    /** @return 是否预约(1:是,2:否) */
    public Integer getIsAppoint(){
        return isAppoint;
    }

    /** @param isAssign 是否指定(1:是,2:否) */
    public void setIsAssign(Integer isAssign){
        this.isAssign = isAssign;
    }

    /** @return 是否指定(1:是,2:否) */
    public Integer getIsAssign(){
        return isAssign;
    }

    /** @param projectId    项目标识 */
    public void setProjectId(Integer projectId){
        this.projectId = projectId;
    }

    /** @return 项目标识 */
    public Integer getProjectId(){
        return projectId;
    }

    /** @param projectName  项目名称 */
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    /** @return 项目名称 */
    public String getProjectName(){
        return projectName;
    }

    /** @param projectPrice 项目价格 */
    public void setProjectPrice(BigDecimal projectPrice){
        this.projectPrice = projectPrice;
    }

    /** @return 项目价格 */
    public BigDecimal getProjectPrice(){
        return projectPrice;
    }

    /** @param projectCount 项目数量 */
    public void setProjectCount(Integer projectCount){
        this.projectCount = projectCount;
    }

    /** @return 项目数量 */
    public Integer getProjectCount(){
        return projectCount;
    }

    /** @param projectImage 项目图片 */
    public void setProjectImage(String projectImage){
        this.projectImage = projectImage;
    }

    /** @return 项目图片 */
    public String getProjectImage(){
        return projectImage;
    }

    /** @param realPrice    项目实际金额 */
    public void setRealPrice(BigDecimal realPrice){
        this.realPrice = realPrice;
    }

    /** @return 项目实际金额 */
    public BigDecimal getRealPrice(){
        return realPrice;
    }

    /** @param discountAmount   折扣价格 */
    public void setDiscountAmount(BigDecimal discountAmount){
        this.discountAmount = discountAmount;
    }

    /** @return 折扣价格 */
    public BigDecimal getDiscountAmount(){
        return discountAmount;
    }

    /** @param comboId  疗程标识 */
    public void setComboId(Integer comboId){
        this.comboId = comboId;
    }

    /** @return 疗程标识 */
    public Integer getComboId(){
        return comboId;
    }

    /** @param couponId 优惠券标识 */
    public void setCouponId(Integer couponId){
        this.couponId = couponId;
    }

    /** @return 优惠券标识 */
    public Integer getCouponId(){
        return couponId;
    }

    

    public Integer getServiceLength() {
        return serviceLength;
    }

    public void setServiceLength(Integer serviceLength) {
        this.serviceLength = serviceLength;
    }

    /** @param orderStatus  订单状态(1：未评价、2：已评价) */
    public void setOrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
    }

    /** @return 订单状态(1：未评价、2：已评价) */
    public Integer getOrderStatus(){
        return orderStatus;
    }

    /** @param updateTime   修改时间 */
    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    /** @return 修改时间 */
    public String getUpdateTime(){
        return updateTime;
    }



    public List<OrderDetailStepDto> getStepList() {
        return stepList;
    }

    public void setStepList(List<OrderDetailStepDto> stepList) {
        this.stepList = stepList;
    }

    /**
     * 
    * @author 王大爷
    * @date 2015年12月25日 下午4:31:01
    * @return OrderDetailDto
    * @throws IOException IOException
    * @throws ClassNotFoundException ClassNotFoundException
     */
    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bop = new ByteArrayOutputStream();  
    
        ObjectOutputStream oos = new ObjectOutputStream(bop);  
        
        oos.writeObject(this);  
            
        ByteArrayInputStream bis = new ByteArrayInputStream(bop.toByteArray());  
            
        ObjectInputStream ois = new ObjectInputStream(bis);  
            
        return ois.readObject();
    }
}
