package com.basic.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
/**
 * 收款管理
 */
public class ReceiptBean {
    private String receiptId;

    private BigDecimal deposit;

    private String pancyId;

    private BigDecimal receiptMoney;

    private Timestamp receiptDate;

    private String receiptName;

    private String custId;

    private String roomId;

    private String remark;
    private Integer orderFlag;

    private Integer enabledFlag;

    private String createBy;

    private Timestamp createDate;

    private String updateBy;

    private Timestamp updateDate;
    
  //收费状态：1.预收款、2.已收费、3.已退款
    private String receiptState;
    
    private CustomerBean customer;
    
    private RoomInfoBean roomInfo;
    
    private OccupancyBean occupancy;

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId == null ? null : receiptId.trim();
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getPancyId() {
        return pancyId;
    }

    public void setPancyId(String pancyId) {
        this.pancyId = pancyId == null ? null : pancyId.trim();
    }

    public BigDecimal getReceiptMoney() {
        return receiptMoney;
    }

    public void setReceiptMoney(BigDecimal receiptMoney) {
        this.receiptMoney = receiptMoney;
    }

    public Timestamp getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Timestamp receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName == null ? null : receiptName.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(Integer orderFlag) {
        this.orderFlag = orderFlag;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

	public String getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(String receiptState) {
		this.receiptState = receiptState;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public RoomInfoBean getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(RoomInfoBean roomInfo) {
		this.roomInfo = roomInfo;
	}

	public OccupancyBean getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(OccupancyBean occupancy) {
		this.occupancy = occupancy;
	}
}