package com.zefun.wechat.dto;

/**
 * 渠道地区排行
* @author 张进军
* @date Mar 20, 2016 11:08:42 AM
 */
public class RegionCountDto {

    /**省份*/
    private String province;
    /**城市*/
    private String city;
    /**数量*/
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
