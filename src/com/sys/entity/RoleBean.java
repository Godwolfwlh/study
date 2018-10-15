package com.sys.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.common.BaseEntity;
/**
 * 角色信息
 * @author Administrator
 *
 */
public class RoleBean extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 2726991328938276046L;
	
	private String roleId;
	private String roleName;
	//排序标识
	private int orderFlag;
	
	//备注
	private String remark;
	
	//是否可用 1可用 0不可用
	private int enabledFlag;
	
	//是否可用 1可用 0不可用
	private int orderFlage;
	
	//创建人
	private String createBy;
	
	//创建日期
	private Timestamp createDate;
	
	//修改人
	private String updateBy;
	
	//修改日期
	private Timestamp updateDate;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public int getOrderFlage() {
		return orderFlage;
	}

	public void setOrderFlage(int orderFlage) {
		this.orderFlage = orderFlage;
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
