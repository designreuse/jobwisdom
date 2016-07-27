package com.zefun.web.entity;

/**
 * 方案规则
* @author 老王
* @date 2016年7月27日 下午3:12:08
 */
public class SettingRule {
    /** 方案规则标识 */
    private Integer settingRuleId;

    /** 方案规则归宿（门店或者企业） */
    private String storeIdOrAccount;

    /** 方案规则类型（1：大客户分析）*/
    private Integer ruleType;

    /** 累计账号数量*/
    private String ruleInfo;

    
    
    public String getStoreIdOrAccount() {
		return storeIdOrAccount;
	}

	public void setStoreIdOrAccount(String storeIdOrAccount) {
		this.storeIdOrAccount = storeIdOrAccount;
	}

	public Integer getSettingRuleId() {
        return settingRuleId;
    }

    public void setSettingRuleId(Integer settingRuleId) {
        this.settingRuleId = settingRuleId;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleInfo() {
        return ruleInfo;
    }

    public void setRuleInfo(String ruleInfo) {
        this.ruleInfo = ruleInfo == null ? null : ruleInfo.trim();
    }
}