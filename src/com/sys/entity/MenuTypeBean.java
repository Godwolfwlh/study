package com.sys.entity;

import java.sql.Timestamp;

/**
 * 菜单分类
 * @author Administrator
 *
 */
public class MenuTypeBean {
	//id编号
	private String typeId;
	
	//分类名称
	private String typeName;
	
	//排序标识
	private int orderFlag;
	
	//备注
	private String remark;
	
	//是否可用 1可用 0不可用
	private int enabledFlag;
	
	//创建人
	private String createBy;
	
	//创建日期
	private Timestamp createDate;
	
	//修改人
	private String updateBy;
	
	//修改日期
	private Timestamp updateDate;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(int orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
}
