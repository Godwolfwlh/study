package com.common;

public class BaseEntity {
	//人员名称
	private String createByName;
	//创建时间
	private String createDateStr;
	//人员修改名称
	private String updateByName;
	//修改时间
	private String updateDateStr;
	
	//删除标识
	private String enabledFlagStr;
	
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getUpdateByName() {
		return updateByName;
	}
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
	public String getUpdateDateStr() {
		return updateDateStr;
	}
	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}
	public String getEnabledFlagStr() {
		return enabledFlagStr;
	}
	public void setEnabledFlagStr(String enabledFlagStr) {
		this.enabledFlagStr = enabledFlagStr;
	}
}
