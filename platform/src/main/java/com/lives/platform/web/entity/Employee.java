package com.lives.platform.web.entity;

public class Employee {
    private Integer employeeId;

    private String employeeNo;

    private Integer storesId;

    private Integer postMessageId;

    private Integer positionId;

    private String employeeName;

    private String sex;

    private String mobile;

    private String card;

    private String cardImg;

    private String birthday;

    private String degree;

    private Integer height;

    private Integer weight;

    private String healthProve;

    private String healthProveImg;

    private String healthState;

    private String bankname;

    private String bankCard;

    private String contacts;

    private Integer contactsMobile;

    private Integer bossSponsor;

    private Integer sponsor;

    private Integer workState;

    private String workTime;

    private String createTime;

    private Integer status;

    private String headImg;

    private String otherImg;

    private String bossSponsorName;
    
    private String sponsorName;
    
    
    public Employee() {
		super();
	}

    

	
	
	public Employee(Integer employeeId, String employeeNo, Integer storesId,
			Integer postMessageId, Integer positionId, String employeeName,
			String sex, String mobile, String card, String cardImg,
			String birthday, String degree, Integer height, Integer weight,
			String healthProve, String healthProveImg, String healthState,
			String bankname, String bankCard, String contacts,
			Integer contactsMobile, Integer bossSponsor, Integer sponsor,
			Integer workState, String workTime, String createTime,
			Integer status, String headImg, String otherImg,
			String bossSponsorName, String sponsorName) {
		super();
		this.employeeId = employeeId;
		this.employeeNo = employeeNo;
		this.storesId = storesId;
		this.postMessageId = postMessageId;
		this.positionId = positionId;
		this.employeeName = employeeName;
		this.sex = sex;
		this.mobile = mobile;
		this.card = card;
		this.cardImg = cardImg;
		this.birthday = birthday;
		this.degree = degree;
		this.height = height;
		this.weight = weight;
		this.healthProve = healthProve;
		this.healthProveImg = healthProveImg;
		this.healthState = healthState;
		this.bankname = bankname;
		this.bankCard = bankCard;
		this.contacts = contacts;
		this.contactsMobile = contactsMobile;
		this.bossSponsor = bossSponsor;
		this.sponsor = sponsor;
		this.workState = workState;
		this.workTime = workTime;
		this.createTime = createTime;
		this.status = status;
		this.headImg = headImg;
		this.otherImg = otherImg;
		this.bossSponsorName = bossSponsorName;
		this.sponsorName = sponsorName;
	}





	public String getBossSponsorName() {
		return bossSponsorName;
	}

	public void setBossSponsorName(String bossSponsorName) {
		this.bossSponsorName = bossSponsorName;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public Integer getPostMessageId() {
        return postMessageId;
    }

    public void setPostMessageId(Integer postMessageId) {
        this.postMessageId = postMessageId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg == null ? null : cardImg.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getHealthProve() {
        return healthProve;
    }

    public void setHealthProve(String healthProve) {
        this.healthProve = healthProve == null ? null : healthProve.trim();
    }

    public String getHealthProveImg() {
        return healthProveImg;
    }

    public void setHealthProveImg(String healthProveImg) {
        this.healthProveImg = healthProveImg == null ? null : healthProveImg.trim();
    }

    public String getHealthState() {
        return healthState;
    }

    public void setHealthState(String healthState) {
        this.healthState = healthState == null ? null : healthState.trim();
    }

    

    public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public Integer getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(Integer contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public Integer getBossSponsor() {
        return bossSponsor;
    }

    public void setBossSponsor(Integer bossSponsor) {
        this.bossSponsor = bossSponsor;
    }

    public Integer getSponsor() {
        return sponsor;
    }

    public void setSponsor(Integer sponsor) {
        this.sponsor = sponsor;
    }

    public Integer getWorkState() {
        return workState;
    }

    public void setWorkState(Integer workState) {
        this.workState = workState;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime == null ? null : workTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getOtherImg() {
        return otherImg;
    }

    public void setOtherImg(String otherImg) {
        this.otherImg = otherImg == null ? null : otherImg.trim();
    }
}