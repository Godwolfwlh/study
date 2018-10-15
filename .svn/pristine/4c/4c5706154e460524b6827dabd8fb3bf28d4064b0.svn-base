package com.common;

import java.io.Serializable;
import java.util.List;

public class EasyuiTreeNode implements Serializable{
	private static final long serialVersionUID = -5639711158253628179L;

		// 节点ID，对加载远程数据很重要
		private String id;
		
		// 显示节点文本。
		private String text;
		
		// 节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
		private String state;
		
		// 菜单小图标
		private String iconCls;
		
		// 表示该节点是否被选中。
		private String checked;
		
		// 一个节点数组声明了若干节点。
		private List<EasyuiTreeNode> children;
		
		// 被添加到节点的自定义属性。
		private Object attributes;

		//父节点ID
		private String parentId;
		
		//排序号
		private Integer orderNo;
		
		//排序编号（如：1.2.1）
		private String oderCode;
		
		//是否启用
		private int delFlag;
		
		//使用类型
		private String useType;
		
		private String isQueryList;	//是否显示目录数据在集合里面(0显示/1不显示)
		
		//级别
		private String requestUnit;
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getChecked() {
			return checked;
		}

		public void setChecked(String checked) {
			this.checked = checked;
		}

		public Object getAttributes() {
			return attributes;
		}

		public void setAttributes(Object attributes) {
			this.attributes = attributes;
		}

		public List<EasyuiTreeNode> getChildren() {
			return children;
		}

		public void setChildren(List<EasyuiTreeNode> children) {
			this.children = children;
		}

		public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

		public Integer getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(Integer orderNo) {
			this.orderNo = orderNo;
		}

		public String getIconCls() {
			return iconCls;
		}

		public void setIconCls(String iconCls) {
			this.iconCls = iconCls;
		}

		public int getDelFlag() {
			return delFlag;
		}

		public void setDelFlag(int delFlag) {
			this.delFlag = delFlag;
		}

		public String getOderCode() {
			return oderCode;
		}

		public void setOderCode(String oderCode) {
			this.oderCode = oderCode;
		}

		public String getUseType() {
			return useType;
		}

		public void setUseType(String useType) {
			this.useType = useType;
		}

		public String getIsQueryList() {
			return isQueryList;
		}

		public void setIsQueryList(String isQueryList) {
			this.isQueryList = isQueryList;
		}
		
		public String getRequestUnit() {
			return requestUnit;
		}

		public void setRequestUnit(String requestUnit) {
			this.requestUnit = requestUnit;
		}
}
