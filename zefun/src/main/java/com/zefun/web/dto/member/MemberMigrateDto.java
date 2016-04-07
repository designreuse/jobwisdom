package com.zefun.web.dto.member;

import java.math.BigDecimal;

/**
 * 会员移植数据传输对象
* @author 张进军
* @date Mar 21, 2016 5:33:09 PM
 */
public class MemberMigrateDto {

    /** 会员标识 */
    private Integer memberId;
    
    /** 卡号 */
    private String cardNo;

    /** 门店标识 */
    private Integer storeId;
    
    /** 等级标识 */
    private Integer levelId;

    /** 级别名称 */
    private String levelName;

    /** 姓名 */
    private String name;

    /** 性别 */
    private String sex;

    /** 生日 */
    private String birthday;

    /** 手机号码 */
    private String phone;
    
    /** 创建时间 */
    private String createTime;
    
    /** 支付密码 */
    private String payPassword;

    /** 密码盐值 */
    private String passwordSalt;
    
    /** 储值总额 */
    private BigDecimal totalAmount;
    
    /** 储值余额 */
    private BigDecimal balanceAmount;
    
    /** 礼金储值总额 */
    private BigDecimal totalGiftmoneyAmount;
    
    /** 礼金储值余额 */
    private BigDecimal balanceGiftmoneyAmount;
    
    /** 积分总额 */
    private Integer totalIntegral;

    /** 积分余额 */
    private Integer balanceIntegral;
    
    /** 欠款金额*/
    private BigDecimal debtAmount;
    
    /** 默认构造函数 */
    public MemberMigrateDto(){
        
    }

    /**
     * 
    * @author 张进军
    * @date Mar 21, 2016 7:09:44 PM
    * @param cardNo                 会员卡号
    * @param storeId                门店标识
    * @param levelName              级别名称
    * @param name                   姓名
    * @param sex                    性别
    * @param birthday               生日
    * @param phone                  手机号码
    * @param createTime             开卡时间
    * @param payPassword            密码
    * @param passwordSalt           密码盐值
    * @param totalAmount            储值总额
    * @param balanceAmount          储值余额
    * @param totalGiftmoneyAmount   礼金总额
    * @param balanceGiftmoneyAmount 礼金余额
    * @param totalIntegral          积分总额
    * @param balanceIntegral        积分余额
    * @param debtAmount             挂账金额
     */
    public MemberMigrateDto(String cardNo, Integer storeId, String levelName, String name, String sex, String birthday, String phone,
            String createTime, String payPassword, String passwordSalt, BigDecimal totalAmount, BigDecimal balanceAmount,
            BigDecimal totalGiftmoneyAmount, BigDecimal balanceGiftmoneyAmount, Integer totalIntegral, Integer balanceIntegral,
            BigDecimal debtAmount) {
        super();
        this.cardNo = cardNo;
        this.storeId = storeId;
        this.levelName = levelName;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.createTime = createTime;
        this.payPassword = payPassword;
        this.passwordSalt = passwordSalt;
        this.totalAmount = totalAmount;
        this.balanceAmount = balanceAmount;
        this.totalGiftmoneyAmount = totalGiftmoneyAmount;
        this.balanceGiftmoneyAmount = balanceGiftmoneyAmount;
        this.totalIntegral = totalIntegral;
        this.balanceIntegral = balanceIntegral;
        this.debtAmount = debtAmount;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getTotalGiftmoneyAmount() {
        return totalGiftmoneyAmount;
    }

    public void setTotalGiftmoneyAmount(BigDecimal totalGiftmoneyAmount) {
        this.totalGiftmoneyAmount = totalGiftmoneyAmount;
    }

    public BigDecimal getBalanceGiftmoneyAmount() {
        return balanceGiftmoneyAmount;
    }

    public void setBalanceGiftmoneyAmount(BigDecimal balanceGiftmoneyAmount) {
        this.balanceGiftmoneyAmount = balanceGiftmoneyAmount;
    }

    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public Integer getBalanceIntegral() {
        return balanceIntegral;
    }

    public void setBalanceIntegral(Integer balanceIntegral) {
        this.balanceIntegral = balanceIntegral;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }
    
}
