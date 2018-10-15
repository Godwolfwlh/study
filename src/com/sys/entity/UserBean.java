package com.sys.entity;

import java.sql.Date;
import java.sql.Timestamp;

import com.common.BaseEntity;


/**
 * @类名称：StaffBean
 * @类描述：用户管理表
 * @创建人：$author
 * @创建时间：2016-02-29 13:55:06
 */

public class UserBean extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -4081299405838947945L;
	/**
	 * @字段名称：STAFF_ID
	 * @属性名称：staffid
	 * @字段类型：String
	 * @字段长度：32
	 * @字段描述：用户ID
	 */
	private String staffId;
	/**
	 * @字段名称：STAFF_TYPE
	 * @属性名称：stafftype
	 * @字段类型：String
	 * @字段长度：3
	 * @字段描述：用户类型 1 学生 2 老师
	 */
	private String staffType;
	
	/**
	 * @字段名称：STAFF_NO
	 * @属性名称：staffno
	 * @字段类型：String
	 * @字段长度：30
	 * @字段描述：登陆账号
	 */
	private String staffNo;
	/**
	 * @字段名称：STAFF_NAME
	 * @属性名称：staffname
	 * @字段类型：String
	 * @字段长度：30
	 * @字段描述：操作员名称
	 */
	private String staffName;
	/**
	 * @字段名称：GENDER
	 * @属性名称：gender
	 * @字段类型：String
	 * @字段长度：2
	 * @字段描述：性别 1 男 0 女
	 */
	private String gender;
	/**
	 * @字段名称：POST
	 * @属性名称：post
	 * @字段类型：String
	 * @字段长度：100
	 * @字段描述：职务
	 */
	private String post;
	/**
	 * @字段名称：MOBILE_PHONE
	 * @属性名称：mobilephone
	 * @字段类型：String
	 * @字段长度：30
	 * @字段描述：移动电话
	 */
	private String linkPhone;
	/**
	 * @字段名称：EMAIL_ADDR
	 * @属性名称：emailaddr
	 * @字段类型：String
	 * @字段长度：50
	 * @字段描述：邮箱
	 */
	private String emailAddr;
	/**
	 * @字段名称：STATE
	 * @属性名称：state
	 * @字段类型：String
	 * @字段长度：4
	 * @字段描述：状态 A 生效 X 失效 L 锁定
	 */
	private String staffState;
	/**
	 * @字段名称：PHOTO_PATH
	 * @属性名称：photopath
	 * @字段类型：String
	 * @字段长度：200
	 * @字段描述：图片路径
	 */
	private String photoPath;
	/**
	 * @字段名称：STATE_DATE
	 * @属性名称：statedate
	 * @字段类型：Timestamp
	 * @字段长度：10
	 * @字段描述：状态时间
	 */
	private Timestamp updateDate;
	/**
	 * @字段名称：PWD
	 * @属性名称：pwd
	 * @字段类型：String
	 * @字段长度：128
	 * @字段描述：操作员密码
	 */
	private String pwd;
	/**
	 * @字段名称：SYS_FLAG
	 * @属性名称：sysflag
	 * @字段类型：String
	 * @字段长度：10
	 * @字段描述：系统标示 1 系统管理员 2 机构人员 3 代理机构人员
	 */
	private String sysFlag;
	/**
	 * @字段名称：CREATE_DATE
	 * @属性名称：createdate
	 * @字段类型：Timestamp
	 * @字段长度：10
	 * @字段描述：创建时间
	 */
	private Timestamp createDate;
	/**
	 * @字段名称：REAL_NAME
	 * @属性名称：realname
	 * @字段类型：String
	 * @字段长度：100
	 * @字段描述：真实姓名
	 */
	private String realName;
	/**
	 * @字段名称：BIRTH_DATE
	 * @属性名称：birthdate
	 * @字段类型：Timestamp
	 * @字段长度：10
	 * @字段描述：出生日期
	 */
	private Date birthDate;
	/**
	 * @字段名称：QQ
	 * @属性名称：qq
	 * @字段类型：String
	 * @字段长度：20
	 * @字段描述：QQ号码
	 */
	private String qq;
	/**
	 * @字段名称：WX_CODE
	 * @属性名称：wxcode
	 * @字段类型：String
	 * @字段长度：100
	 * @字段描述：微信号码
	 */
	private String wxCode;
	
	//删除标识
	private String enabledFlag;
	//排序标识
	private int orderFlag;
	//创建人
	private String createBy;
	//修改人
	private String updateBy;
	
	//修改人
	private String education;
	
	
	private String remark;
	
	private String birthDateStr;
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getStaffState() {
		return staffState;
	}
	public void setStaffState(String staffState) {
		this.staffState = staffState;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSysFlag() {
		return sysFlag;
	}
	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWxCode() {
		return wxCode;
	}
	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}
	public String getBirthDateStr() {
		return birthDateStr;
	}
	public void setBirthDateStr(String birthDateStr) {
		this.birthDateStr = birthDateStr;
	}
	public String getEnabledFlag() {
		return enabledFlag;
	}
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
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
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
}
