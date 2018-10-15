package com.sys.dao;

import java.util.List;
import java.util.Map;

import com.sys.entity.UserBean;

public interface UserMapper {
	/**
	 * 新增
	 * @return
	 */
	public int addUser(UserBean bean);
	
	/**
	 * 修改
	 * @return
	 */
	public int updateUser(UserBean bean);
	
	/**
	 * 逻辑删除
	 * @return
	 */
	public int removeUser(String staffId);
	
	/**
	 * 物理删除
	 * @return
	 */
	public int deleteUser(String staffId);
	
	/**
	 * 查询list集合
	 * @return
	 */
	public List<UserBean> findUserList();
	
	/**
	 * 根据ID查询
	 * @return
	 */
	public UserBean findById(String staffId);
	
	/**
	 * 分页查询
	 * @return
	 */
	public List<UserBean> findByPage(Map<String,Object> map);
	
	/**
	 * 查询数量
	 * @return
	 */
	public int getCount(Map<String,Object> map);
	
	/**
	 * 分页查询
	 * @return
	 */
	public List<UserBean> findMenuList(Map<String,Object> map);
	
	/**
	 * 查询数量
	 * @return
	 */
	public int getMaxOrderFlag(Map<String,Object> map);
	
	//系统登录
	public UserBean userlogin(String staffNo);
	
	/**
	 * 查询已选择用户
	 * @param roleId
	 * @return
	 */
	public List<UserBean> findSelectRole(String roleId);
	
	/**
	 * 查询未选择用户
	 * @param roleId
	 * @return
	 */
	public List<UserBean> findNoSelectRole(String roleId);
}
