package com.lives.platform.web.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class MemberCardCommissionDto {
	private Integer cardId;

    private String commissionType;

    private BigDecimal commissionSingleMoney;

    private String commissionMultiMoney;

    private String rechargeCommissionType;

    private BigDecimal prepaidSingleCommissionMoney;

    private String rechargeRateMoney;
    
    private String cardName;
    
    private BigDecimal ofirst;
	private BigDecimal osecond;
	private BigDecimal othree;
	private BigDecimal ofour;
	
	private BigDecimal tfirst;
	private BigDecimal tsecond;
	private BigDecimal tthree;
	private BigDecimal tfour;
	
	private BigDecimal ofir;
	private BigDecimal osec;
	private BigDecimal othr;
	private BigDecimal ofou;
	
	private BigDecimal tfir;
	private BigDecimal tsec;
	private BigDecimal tthr;
	private BigDecimal tfou;

	
    public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType == null ? null : commissionType.trim();
    }

    public BigDecimal getCommissionSingleMoney() {
        return commissionSingleMoney;
    }

    public void setCommissionSingleMoney(BigDecimal commissionSingleMoney) {
        this.commissionSingleMoney = commissionSingleMoney;
    }

    public String getCommissionMultiMoney() {
    	Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		if(this.ofirst==null)this.ofirst = new BigDecimal(0);
		if(this.osecond==null)this.osecond = new BigDecimal(0);
		if(this.othree==null)this.othree = new BigDecimal(0);
		if(this.ofour==null)this.ofour = new BigDecimal(0);
		map.put("fir", ofirst);
		map.put("sec", osecond);
		map.put("thr", othree);
		map.put("fou", ofour);
		return  JSONObject.fromObject(map).toString();
    }

    public void setCommissionMultiMoney(String commissionMultiMoney) {
        this.commissionMultiMoney = commissionMultiMoney == null ? null : commissionMultiMoney.trim();
    }

    public String getRechargeCommissionType() {
        return rechargeCommissionType;
    }

    public void setRechargeCommissionType(String rechargeCommissionType) {
        this.rechargeCommissionType = rechargeCommissionType == null ? null : rechargeCommissionType.trim();
    }

    public BigDecimal getPrepaidSingleCommissionMoney() {
        return prepaidSingleCommissionMoney;
    }

    public void setPrepaidSingleCommissionMoney(BigDecimal prepaidSingleCommissionMoney) {
        this.prepaidSingleCommissionMoney = prepaidSingleCommissionMoney;
    }

    public String getRechargeRateMoney() {
    	Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		if(this.tfirst==null)this.tfirst = new BigDecimal(0);
		if(this.tsecond==null)this.tsecond = new BigDecimal(0);
		if(this.tthree==null)this.tthree = new BigDecimal(0);
		if(this.tfour==null)this.tfour = new BigDecimal(0);
		map.put("fir", tfirst);
		map.put("sec", tsecond);
		map.put("thr", tthree);
		map.put("fou", tfour);
		return  JSONObject.fromObject(map).toString();
    }

    public void setRechargeRateMoney(String rechargeRateMoney) {
        this.rechargeRateMoney = rechargeRateMoney == null ? null : rechargeRateMoney.trim();
    }
    
    public BigDecimal getOfir() {
		JSONObject json = JSONObject.fromObject(this.commissionMultiMoney);
		return new BigDecimal(json.get("fir").toString());
	}

	public void setOfir(BigDecimal ofir) {
		this.ofir = ofir;
	}

	public BigDecimal getOsec() {
		JSONObject json = JSONObject.fromObject(this.commissionMultiMoney);
		return new BigDecimal(json.get("sec").toString());
	}

	public void setOsec(BigDecimal osec) {
		this.osec = osec;
	}

	public BigDecimal getOthr() {
		JSONObject json = JSONObject.fromObject(this.commissionMultiMoney);
		return new BigDecimal(json.get("thr").toString());
	}

	public void setOthr(BigDecimal othr) {
		this.othr = othr;
	}

	public BigDecimal getOfou() {
		JSONObject json = JSONObject.fromObject(this.commissionMultiMoney);
		return new BigDecimal(json.get("fou").toString());
	}

	public void setOfou(BigDecimal ofou) {
		this.ofou = ofou;
	}

	public BigDecimal getTfir() {
		JSONObject json = JSONObject.fromObject(this.rechargeRateMoney);
		return new BigDecimal(json.get("fir").toString());
	}

	public void setTfir(BigDecimal tfir) {
		this.tfir = tfir;
	}

	public BigDecimal getTsec() {
		JSONObject json = JSONObject.fromObject(this.rechargeRateMoney);
		return new BigDecimal(json.get("sec").toString());
	}

	public void setTsec(BigDecimal tsec) {
		this.tsec = tsec;
	}

	public BigDecimal getTthr() {
		JSONObject json = JSONObject.fromObject(this.rechargeRateMoney);
		return new BigDecimal(json.get("thr").toString());
	}

	public void setTthr(BigDecimal tthr) {
		this.tthr = tthr;
	}

	public BigDecimal getTfou() {
		JSONObject json = JSONObject.fromObject(this.rechargeRateMoney);
		return new BigDecimal(json.get("fou").toString());
	}

	public void setTfou(BigDecimal tfou) {
		this.tfou = tfou;
	}

	public BigDecimal getOfirst() {
		return ofirst;
	}

	public void setOfirst(BigDecimal ofirst) {
		this.ofirst = ofirst;
	}

	public BigDecimal getOsecond() {
		return osecond;
	}

	public void setOsecond(BigDecimal osecond) {
		this.osecond = osecond;
	}

	public BigDecimal getOthree() {
		return othree;
	}

	public void setOthree(BigDecimal othree) {
		this.othree = othree;
	}

	public BigDecimal getOfour() {
		return ofour;
	}

	public void setOfour(BigDecimal ofour) {
		this.ofour = ofour;
	}

	public BigDecimal getTfirst() {
		return tfirst;
	}

	public void setTfirst(BigDecimal tfirst) {
		this.tfirst = tfirst;
	}

	public BigDecimal getTsecond() {
		return tsecond;
	}

	public void setTsecond(BigDecimal tsecond) {
		this.tsecond = tsecond;
	}

	public BigDecimal getTthree() {
		return tthree;
	}

	public void setTthree(BigDecimal tthree) {
		this.tthree = tthree;
	}

	public BigDecimal getTfour() {
		return tfour;
	}

	public void setTfour(BigDecimal tfour) {
		this.tfour = tfour;
	}
	
	
}
