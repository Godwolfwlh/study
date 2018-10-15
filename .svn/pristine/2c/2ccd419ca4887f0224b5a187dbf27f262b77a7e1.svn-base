package com.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.basic.entity.RoomInfoBean;
import com.common.EasyuiTreeNode;
import com.common.Page;
import com.common.TreeNode;
import com.common.basedb.impl.MyBatisBaseDaoImpl;
import com.sys.dao.MenuMapper;
import com.sys.dao.MenuTypeMapper;
import com.sys.entity.MenuBean;
import com.sys.entity.MenuRoleBean;
import com.sys.entity.MenuTypeBean;
import com.sys.entity.UserBean;
import com.sys.entity.UserRoleBean;
import com.util.DateTimeUtile;
import com.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class MenuService {
	@Resource
	private MenuMapper menuMapper;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addMenu(ObjectVo vo) throws Exception{
		MenuBean entity=(MenuBean) vo.JsonToBean(MenuBean.class);
		entity.setMenuId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setEnabledFlag(1);
		return menuMapper.addMenu(entity);
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateMenu(ObjectVo vo) throws Exception{
		MenuBean entity=(MenuBean) vo.JsonToBean(MenuBean.class);
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return menuMapper.updateMenu(entity);
	}
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteMenu(ObjectVo vo) throws Exception{
		MenuBean entity=(MenuBean) vo.JsonToBean(MenuBean.class);
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return menuMapper.deleteMenu(entity);
	}
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public Page findMenuByPage(ObjectVo vo) throws Exception{
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		List<MenuBean> list=menuMapper.findMenuByPage(map);
		Page page=new Page<MenuBean>();
		page.setRows(list);
		page.setTotal(this.getCount(map));
		return page;
	}
	
	/**
	 * 查询所有
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<MenuBean> findMenuList(ObjectVo vo){
		//JSONObject json=vo.getFormValue();
		//Map<String,Object> map=vo.jsonObjectConverMap(json);
		List<MenuBean> list=menuMapper.findMenuList(vo.getParameterMap());
		return list;
	}
	
	/**
	 * 树形查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<TreeNode> findTree(ObjectVo vo) throws Exception{
		List<TreeNode> listNode=new ArrayList<TreeNode>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("parentId","-1");
		List<MenuBean> list=menuMapper.findByParentId(map);
		if(list.size()>0 && list!=null) {
			for (MenuBean en : list) {
				TreeNode node=new TreeNode();
				node.setId(en.getMenuId());
				node.setName(en.getMenuName());
				node.setPid("0");
				listNode.add(node);
				//查询子节点
				map.put("parentId",en.getMenuId());
				List<MenuBean> list1=menuMapper.findByParentId(map);
				if(list1.size()>0) {
					for (MenuBean en1 : list1) {
						TreeNode node1=new TreeNode();
						node1.setId(en1.getMenuId());
						node1.setName(en1.getMenuName());
						node1.setPid(en.getMenuId());
						listNode.add(node1);
					}
				}
			}
		}
		return listNode;
	}
	
	/**
	 * 根据parentId查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<MenuBean> findByParentId(Map<String,Object> map) throws Exception{
		List<MenuBean> list=menuMapper.findByParentId(map);
		return list;
	}
	
	/**
	 * 根据parentId查询（操作中间表）
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<MenuBean> findMebuByPid(Map<String,Object> map) throws Exception{
		List<MenuBean> list=menuMapper.findMebuByPid(map);
		return list;
	}
	
	/**
	 * 查询权限菜单
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<MenuBean> findPermissionMenu(ObjectVo vo) throws Exception{
		UserBean user=vo.getCurrentUser();
		List<MenuBean> list=null;
		if(user!=null) {
			if("admin".equals(user.getStaffNo())) {
				list=this.findMenuList(vo);
			}else {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("userId",user.getStaffId());
				list=menuMapper.findPermissionMenu(map);
			}
		}else {
			list=this.findMenuList(vo);
		}
		return list;
	}
	
	/**
	 * 根据ID查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public MenuBean findById(ObjectVo vo) throws Exception{
		MenuBean bean=(MenuBean) vo.JsonToBean(MenuBean.class);
		if(bean!=null && StringUtils.isNotBlank(bean.getMenuId())) {
			bean=menuMapper.findByMenuId(bean.getMenuId());
			return bean;
		}else {
			return null;
		}
	}
	
	/**
	 * 获得条数
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getCount(Map<String,Object> map) throws Exception{
		return menuMapper.getCount(map);
	}
	
	/**
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getMaxOrderFlag(){
		return menuMapper.getMaxOrderFlag();
	}
	
	//根据菜单ID角色ID查询
	public int findMenuRoleCount(String menuId,String roleId) {
		return menuMapper.findMenuRoleCount(menuId,roleId);
		
	}
	
	//插入中间表
	@Transactional(rollbackFor=Exception.class)
	public int addMenuRole(ObjectVo vo) throws Exception {
		MenuRoleBean bean=(MenuRoleBean) vo.JsonToBean(MenuRoleBean.class);
		int result=0;
		if(bean!=null) {
			if(StringUtils.isNotBlank(bean.getMenuId()) && StringUtils.isNotBlank(bean.getRoleId())) {
				String[] menuIds=bean.getMenuId().split(",");
				for(int i=0;i<menuIds.length;i++) {
					int cnt=findMenuRoleCount(menuIds[i],bean.getRoleId());
					if(cnt==0) {
						bean.setMenuRoleId(StringUtils.getUUid());
						bean.setEnabledFlag(1);
						bean.setMenuId(menuIds[i]);
						bean.setCreateBy(vo.getCurrentUser().getStaffId());
						bean.setCreateDate(DateTimeUtile.getTimesTmp());
						bean.setUpdateBy(vo.getCurrentUser().getStaffId());
						bean.setUpdateDate(DateTimeUtile.getTimesTmp());
						result=menuMapper.addMenuRole(bean);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 角色菜单信息
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public Page findMenuByRoleId(ObjectVo vo) throws Exception{
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		List<MenuBean> list=menuMapper.findMenuByRoleId(vo.getParameterMap());
		Page page=new Page<MenuBean>();
		page.setRows(list);
		page.setTotal(this.getMenuRoleCount(map));
		return page;
	}
	
	/**
	 * 角色菜单信息 获得条数
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getMenuRoleCount(Map<String,Object> map) throws Exception{
		return menuMapper.getMenuRoleCount(map);
	}
	
	
	/**
	 * 根据角色ID查询菜单信息
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<MenuBean> queryMenuByRoleId(ObjectVo vo) throws Exception{
		//Map<String,Object> map=vo.getParameterMap();
		JSONObject json=vo.getFormValue();
		Map<String,Object> map=vo.jsonObjectConverMap(json);
		List<MenuBean> list=menuMapper.queryMenuByRoleId(map);
		return list;
	}
}
