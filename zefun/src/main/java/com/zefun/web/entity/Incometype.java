package com.zefun.web.entity;

/**
 *收支类型
* @author 骆峰
* @date 2016年6月16日 下午8:35:19
 */
public class Incometype {
    /** 主键id */
    private Integer incometypeId;

    /** 名称 */
    private String name;

    /** 1收入2支出 */
    private Integer type;

    /** 0正常1删除 */
    private Integer isDeleted;

    /** 门店识别 */
    private Integer storeId;

    public Integer getIncometypeId() {
        return incometypeId;
    }

    public void setIncometypeId(Integer incometypeId) {
        this.incometypeId = incometypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}