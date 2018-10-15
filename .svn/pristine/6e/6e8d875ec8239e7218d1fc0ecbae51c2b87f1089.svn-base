package com.basic.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.common.BaseEntity;

public class OccupancyBean extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 3767342895362251636L;

	//ID
    private String pancyId;

    //客户ID
    private String custId;

    //房间ID
    private String roomId;

    //入住时间
    private Timestamp checkInDate;
    
    //退房时间
    private Timestamp checkOutDate;

    //应付金额
    private BigDecimal prepaidAmount;

    //预住天数
    private int prepaidNumber;

    //房间价格
    private BigDecimal housePrice;

    //折扣价
    private Float discount;
    
    //押金
    private BigDecimal deposit;

    //实收金额
    private BigDecimal amountCollect;
    
    //欠款金额
    private BigDecimal amountOwe;
    
    //付款方式
    private String paidType;
    
    //收费状态：1.预收款、2.已收费、3.已退款
    private String payState;
    
    //订单状态: 1：入住,2：预订，3.换房，4：退房,5：取消,0：结束
    private String pancyState;

    private String remark;

    private int orderFlag;

    private int enabledFlag;

    private String createBy;

    private Timestamp createDate;

    private String updateBy;

    private Timestamp updateDate;
    
    //客户信息
    private CustomerBean customer;
    //房间信息
    private RoomInfoBean roomInfo;
    
    
    //页面显示
    private String checkInDateView;
    private String checkOutDateView;
    private String createDateView;
    private String updateDateView;

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

	public String getPancyId() {
        return pancyId;
    }

    public void setPancyId(String pancyId) {
        this.pancyId = pancyId == null ? null : pancyId.trim();
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

    public Timestamp getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Timestamp checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BigDecimal getPrepaidAmount() {
        return prepaidAmount;
    }

    public void setPrepaidAmount(BigDecimal prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public int getPrepaidNumber() {
        return prepaidNumber;
    }

    public void setPrepaidNumber(int prepaidNumber) {
        this.prepaidNumber = prepaidNumber;
    }

    public BigDecimal getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(BigDecimal housePrice) {
        this.housePrice = housePrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public BigDecimal getAmountCollect() {
        return amountCollect;
    }

    public void setAmountCollect(BigDecimal amountCollect) {
        this.amountCollect = amountCollect;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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


	public String getPancyState() {
		return pancyState;
	}

	public void setPancyState(String pancyState) {
		this.pancyState = pancyState;
	}

	public String getPaidType() {
		return paidType;
	}

	public void setPaidType(String paidType) {
		this.paidType = paidType;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public BigDecimal getAmountOwe() {
		return amountOwe;
	}
	
	public void setAmountOwe(BigDecimal amountOwe) {
		this.amountOwe = amountOwe;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public String getCheckInDateView() {
		return checkInDateView;
	}

	public void setCheckInDateView(String checkInDateView) {
		this.checkInDateView = checkInDateView;
	}

	public String getCheckOutDateView() {
		return checkOutDateView;
	}

	public void setCheckOutDateView(String checkOutDateView) {
		this.checkOutDateView = checkOutDateView;
	}

	public String getCreateDateView() {
		return createDateView;
	}

	public void setCreateDateView(String createDateView) {
		this.createDateView = createDateView;
	}

	public String getUpdateDateView() {
		return updateDateView;
	}

	public void setUpdateDateView(String updateDateView) {
		this.updateDateView = updateDateView;
	}
	
	
}