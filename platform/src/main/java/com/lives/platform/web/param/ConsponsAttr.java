package com.lives.platform.web.param;

public class ConsponsAttr {
//通过优惠券名称查找所有的优惠券 --coupons_type_name 模糊查询
private String couponsTypeName;
//通过优惠券号码查找优惠券 --- 模糊查询
private String coupons_no;
//通过项目名称模糊查询
private String projectName;
//门店查询----基础
private int storeId;
//通过优惠券类型查找优惠券信息 -id --暂时不用
private Integer couponsType;


public int getStoreId() {
	return storeId;
}

public void setStoreId(int storeId) {
	this.storeId = storeId;
}

public String getProjectName() {
	return projectName;
}

public void setProjectName(String projectName) {
	this.projectName = projectName;
}

public Integer getCouponsType() {
	return couponsType;
}

public void setCouponsType(Integer couponsType) {
	this.couponsType = couponsType;
}

public String getCoupons_no() {
	return coupons_no;
}

public void setCoupons_no(String coupons_no) {
	this.coupons_no = coupons_no;
}

public String getCouponsTypeName() {
	return couponsTypeName;
}

public void setCouponsTypeName(String couponsTypeName) {
	this.couponsTypeName = couponsTypeName;
}

public ConsponsAttr(String couponsTypeName) {
	this.couponsTypeName = couponsTypeName;
}
public ConsponsAttr() {
	// TODO Auto-generated constructor stub
}
}
