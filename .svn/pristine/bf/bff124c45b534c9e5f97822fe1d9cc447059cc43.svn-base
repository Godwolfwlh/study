package com.basic.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.common.BaseEntity;
/**
 * 订单
 * @author Administrator
 *
 */
public class OrderBean extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -3533801054766443960L;
	
	//单号id
	private String orderId;

	//客户ID
    private String custId;

    //房间id
    private String roomId;

    //入住日期
    private Timestamp checkInDate;

    //退房日期
    private Timestamp checkOutDate;

    //入住天数
    private Integer prepaidNumber;

    //状态: 1：预订， 2：入住，3：换房 4：退房 5,：取消， 6,：完成
    private String orderState;

    //支付状态
    private String payState;

    //备注
    private String remark;

    private Integer orderFlag;

    private Integer enabledFlag;

    private String createBy;

    private Timestamp createDate;

    private String updateBy;

    private Timestamp updateDate;
    
    private String checkInDateStr;
    
    private String checkOutDateStr;
    
    //客户信息
    private CustomerBean customerBean;
    
    //房间信息
    private RoomInfoBean roomInfoBean;
    
    //收款信息
    private CaseBean caseBean;
    
    public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

    public Integer getPrepaidNumber() {
        return prepaidNumber;
    }

    public void setPrepaidNumber(Integer prepaidNumber) {
        this.prepaidNumber = prepaidNumber;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState == null ? null : payState.trim();
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

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public RoomInfoBean getRoomInfoBean() {
		return roomInfoBean;
	}

	public void setRoomInfoBean(RoomInfoBean roomInfoBean) {
		this.roomInfoBean = roomInfoBean;
	}

	public String getCheckInDateStr() {
		return checkInDateStr;
	}

	public void setCheckInDateStr(String checkInDateStr) {
		this.checkInDateStr = checkInDateStr;
	}

	public String getCheckOutDateStr() {
		return checkOutDateStr;
	}

	public void setCheckOutDateStr(String checkOutDateStr) {
		this.checkOutDateStr = checkOutDateStr;
	}

	public CaseBean getCaseBean() {
		return caseBean;
	}

	public void setCaseBean(CaseBean caseBean) {
		this.caseBean = caseBean;
	}
}