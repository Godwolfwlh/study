package com.basic.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.common.BaseEntity;
public class CustomerBean extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 2803151134876027018L;

	private String custId;

    private String custName;

    private String cardNumber;

    private String linkPhone;

    private String gender;
    
    private String genderName;

    private String birthDate;

    private String age;

    private String address;

    private int leagScore;

    private String isLeaguer;

    private String remark;

    private int orderFlag;
    
    private String orderFlagName;//用于查询

    private int enabledFlag;

    private String createBy;

    private Timestamp createDate;

    private String updateBy;

    private Timestamp updateDate;

    //客户状态：1.正常，2，预定 ,3.入住
    private String custState;
    
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLeagScore() {
        return leagScore;
    }

    public void setLeagScore(int leagScore) {
        this.leagScore = leagScore;
    }

    public String getIsLeaguer() {
        return isLeaguer;
    }

    public void setIsLeaguer(String isLeaguer) {
        this.isLeaguer = isLeaguer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(int orderFlag) {
        this.orderFlag = orderFlag;
    }

    public int getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(int enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

	public String getOrderFlagName() {
		return orderFlagName;
	}

	public void setOrderFlagName(String orderFlagName) {
		this.orderFlagName = orderFlagName;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}
    
}