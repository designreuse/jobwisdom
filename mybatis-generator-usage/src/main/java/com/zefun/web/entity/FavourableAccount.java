package com.zefun.web.entity;

public class FavourableAccount {
    /** �Ż����ͱ�ʶ */
    private Integer favourableId;

    /** ��� */
    private String favourableNamber;

    /** �Ż����� */
    private Integer favourableName;

    /** �Ż����� ��0�ۿۣ�1�ֽ�2���飩 */
    private Integer favourableType;

    /** �Żݲ��� */
    private Integer favourableStrategy;

    /** �Ƿ������Ա�ۿۣ�0����1������ */
    private Integer favourableAllow;

    /** ҵ���ۿۣ�0�Żݲ��֣�1�ܼۣ� */
    private String favourablePart;

    /** ����/����ʦ  */
    private String favourableHair;

    /** ��ʦ */
    private String favourableTech;

    /** ���� */
    private String favourableAssistant;

    /** ��ҵid */
    private String storeAccount;

    /** �ŵ�id */
    private Integer storeId;

    /** �Ƿ�ͣ�ã�0������1ͣ�ã� */
    private Integer fullstop;

    /** ��0������1ɾ���� */
    private Integer isDeleted;

    /** ����ʱ�� */
    private String createTime;

    /** �޸�ʱ�� */
    private String updateTime;

    public Integer getFavourableId() {
        return favourableId;
    }

    public void setFavourableId(Integer favourableId) {
        this.favourableId = favourableId;
    }

    public String getFavourableNamber() {
        return favourableNamber;
    }

    public void setFavourableNamber(String favourableNamber) {
        this.favourableNamber = favourableNamber == null ? null : favourableNamber.trim();
    }

    public Integer getFavourableName() {
        return favourableName;
    }

    public void setFavourableName(Integer favourableName) {
        this.favourableName = favourableName;
    }

    public Integer getFavourableType() {
        return favourableType;
    }

    public void setFavourableType(Integer favourableType) {
        this.favourableType = favourableType;
    }

    public Integer getFavourableStrategy() {
        return favourableStrategy;
    }

    public void setFavourableStrategy(Integer favourableStrategy) {
        this.favourableStrategy = favourableStrategy;
    }

    public Integer getFavourableAllow() {
        return favourableAllow;
    }

    public void setFavourableAllow(Integer favourableAllow) {
        this.favourableAllow = favourableAllow;
    }

    public String getFavourablePart() {
        return favourablePart;
    }

    public void setFavourablePart(String favourablePart) {
        this.favourablePart = favourablePart == null ? null : favourablePart.trim();
    }

    public String getFavourableHair() {
        return favourableHair;
    }

    public void setFavourableHair(String favourableHair) {
        this.favourableHair = favourableHair == null ? null : favourableHair.trim();
    }

    public String getFavourableTech() {
        return favourableTech;
    }

    public void setFavourableTech(String favourableTech) {
        this.favourableTech = favourableTech == null ? null : favourableTech.trim();
    }

    public String getFavourableAssistant() {
        return favourableAssistant;
    }

    public void setFavourableAssistant(String favourableAssistant) {
        this.favourableAssistant = favourableAssistant == null ? null : favourableAssistant.trim();
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount == null ? null : storeAccount.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getFullstop() {
        return fullstop;
    }

    public void setFullstop(Integer fullstop) {
        this.fullstop = fullstop;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}