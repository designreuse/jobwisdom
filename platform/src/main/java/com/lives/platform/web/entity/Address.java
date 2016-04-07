package com.lives.platform.web.entity;

public class Address {
    private Integer addressId;

    private String province;

    private String city;

    private String area;

    private String detailAddress;

    private Integer phone;

    private Integer storesId;

    private String createTime;

    
    
    public Address() {
		super();
	}

	public Address(Integer addressId, String province, String city,
			String area, String detailAddress, Integer phone, Integer storesId,
			String createTime) {
		super();
		this.addressId = addressId;
		this.province = province;
		this.city = city;
		this.area = area;
		this.detailAddress = detailAddress;
		this.phone = phone;
		this.storesId = storesId;
		this.createTime = createTime;
	}

	public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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