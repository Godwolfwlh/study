package com.sys.dao;

import java.util.List;
import java.util.Map;

import com.common.Page;
import com.sys.entity.MenuBean;
import com.sys.entity.MenuRoleBean;
/**
 * 菜单分类接口类
 * @author Administrator
 *
 */
public interface MenuMapper {
	//新增
	public int addMenu(MenuBean entity);
	
	//修改
	public int updateMenu(MenuBean entity);
	
	//物理删除
	public int removeMenu(String id);
	
	//删除
	public int deleteMenu(MenuBean entity);
	
	//根据ID查找
	public MenuBean findByMenuId(String typeId);
	
	//根据ID查找
	public List<MenuBean> findMenuList(Map<String,Object> map);
	
	//分页查询
	public List<MenuBean> findMenuByPage(Map<String,Object> map);
	
	//查询树形所有
	public List<MenuBean> findTreeByMenuId(MenuBean entity);
	
	//角色菜单信息
	public List<MenuBean> findMenuByRoleId(Map<String,Object> map);
	
	//根据角色ID查询菜单信息 
	public List<MenuBean> queryMenuByRoleId(Map<String,Object> map);
	
	//根据父节点ID查询
	public List<MenuBean> findByParentId(Map<String,Object> map);
	
	//根据父节点ID查询（操作中间表）
	public List<MenuBean> findMebuByPid(Map<String,Object> map);
	
	//获得条数
	public int getCount(Map<String,Object> map);
	
	//获得条数角色和菜单
	public int getMenuRoleCount(Map<String,Object> map);
	
	//获得最大序列号
	public int getMaxOrderFlag();
	
	//根据菜单ID角色ID查询
	public int findMenuRoleCount(String menuId,String roleId);
	
	//插入中间表
	public int addMenuRole(MenuRoleBean bean);
	
	//权限查询菜单
	public List<MenuBean> findPermissionMenu(Map<String,Object> map);
	
}
