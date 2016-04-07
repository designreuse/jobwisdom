package com.zefun.wechat.dto;

public class RegionCountRankDto {

    private String province;
    private String city;
    private Integer total;
    private Integer currMonth;
    private Integer wechat;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrMonth() {
        return currMonth;
    }

    public void setCurrMonth(Integer currMonth) {
        this.currMonth = currMonth;
    }

    public Integer getWechat() {
        return wechat;
    }

    public void setWechat(Integer wechat) {
        this.wechat = wechat;
    }

}
