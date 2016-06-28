package com.zefun.web.entity;

/**
 * 企业信息表
* @author 老王
* @date 2016年5月21日 下午6:43:21
 */
public class EnterpriseInfo {
	/**
	 * 账户标识
	 */
    private Integer enterpriseInfoId;

    /**
     * 企业代号
     */
    private String storeAccount;

    /**
     * 门店联系人
     */
    private String enterpriseLinkname;

    /**
     * 门店联系电话
     */
    private String enterpriseLinkphone;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 企业所属省份
     */
    private String enterpriseProvince;

    /**
     * 企业地址
     */
    private String enterpriseAddress;

    /**
     * 企业所属城市
     */
    private String enterpriseCity;

    /**
     * 企业电话
     */
    private String enterpriseTel;

    /**
     * 企业状态(1:正常，2:欠费)
     */
    private Integer enterpriseStatus;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后操作人标识
     */
    private Integer lastOperatorId;

    public Integer getEnterpriseInfoId() {
        return enterpriseInfoId;
    }

    public void setEnterpriseInfoId(Integer enterpriseInfoId) {
        this.enterpriseInfoId = enterpriseInfoId;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount == null ? null : storeAccount.trim();
    }

    public String getEnterpriseLinkname() {
        return enterpriseLinkname;
    }

    public void setEnterpriseLinkname(String enterpriseLinkname) {
        this.enterpriseLinkname = enterpriseLinkname == null ? null : enterpriseLinkname.trim();
    }

    public String getEnterpriseLinkphone() {
        return enterpriseLinkphone;
    }

    public void setEnterpriseLinkphone(String enterpriseLinkphone) {
        this.enterpriseLinkphone = enterpriseLinkphone == null ? null : enterpriseLinkphone.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getEnterpriseProvince() {
        return enterpriseProvince;
    }

    public void setEnterpriseProvince(String enterpriseProvince) {
        this.enterpriseProvince = enterpriseProvince == null ? null : enterpriseProvince.trim();
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress == null ? null : enterpriseAddress.trim();
    }

    public String getEnterpriseCity() {
        return enterpriseCity;
    }

    public void setEnterpriseCity(String enterpriseCity) {
        this.enterpriseCity = enterpriseCity == null ? null : enterpriseCity.trim();
    }

    public String getEnterpriseTel() {
        return enterpriseTel;
    }

    public void setEnterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel == null ? null : enterpriseTel.trim();
    }

    public Integer getEnterpriseStatus() {
        return enterpriseStatus;
    }

    public void setEnterpriseStatus(Integer enterpriseStatus) {
        this.enterpriseStatus = enterpriseStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
    
    /**
     * 无惨构造
    * @author 王大爷
    * @date 2016年6月27日 上午11:08:38
     */
    public EnterpriseInfo() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 参数构造
    * @author 王大爷
    * @date 2016年6月27日 上午11:09:12
    * @param storeAccount storeAccount
     */
    public EnterpriseInfo(String storeAccount) {
        this.storeAccount = storeAccount;
    }
    
}