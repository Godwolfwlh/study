package com.sys.dao;

import java.util.List;
import java.util.Map;

import com.sys.entity.MenuRoleBean;
import com.sys.entity.RoleBean;
import com.sys.entity.UserRoleBean;

/**
 * 菜单分类接口类
 * @author Administrator
 *
 */
public interface RoleMapper {
	//新增
	public int addRole(RoleBean entity);
	
	//修改
	public int updateRole(RoleBean entity);
	
	//物理删除
	public int removeRole(String id);
	
	//删除
	public int deleteRole(String roleId);
	
	//根据ID查找
	public RoleBean findById(String roleId);
	
	//根据所有
	public List<RoleBean> findByList(Map<String,Object> map);
	
	//查询所有
	public List<RoleBean> findRoleByPage(Map<String,Object> map);
	
	//获得条数
	public int getCount(Map<String,Object> map);
	
	//判断中间表是否重复插入
	public int findUserRoleCount(UserRoleBean bean);
	
	//插入角色用户中间表
	public int addUserRole(UserRoleBean bean);
	
	//删除角色用户中间表
	public int deleteUserRole(UserRoleBean bean);
	
	//删除菜单角色中间表
	public int deleteRoleIdMenuId(Map<String,Object> map);
}
