package com.sys.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.common.Page;
import com.sys.dao.MenuMapper;
import com.sys.dao.RoleMapper;
import com.sys.entity.DictypeBean;
import com.sys.entity.MenuBean;
import com.sys.entity.MenuRoleBean;
import com.sys.entity.RoleBean;
import com.sys.entity.UserRoleBean;
import com.util.DateTimeUtile;
import com.util.StringUtils;

import net.sf.json.JSONObject;

@Service
public class RoleService {
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private MenuMapper menuMapper;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addRole(ObjectVo vo) throws Exception{
		RoleBean entity=(RoleBean) vo.JsonToBean(RoleBean.class);
		entity.setRoleId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return roleMapper.addRole(entity);
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateRole(ObjectVo vo) throws Exception{
		RoleBean entity=(RoleBean) vo.JsonToBean(RoleBean.class);
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return roleMapper.updateRole(entity);
	}
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteRole(ObjectVo vo) throws Exception{
		Object roleId=vo.getFormValue().get("roleId");
		if(roleId!=null && StringUtils.isNotBlank(roleId.toString())) {
			return roleMapper.deleteRole(roleId.toString());
		}else {
			return -1;
		}
	}
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Page findRoleByPage(ObjectVo vo) throws Exception{
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",((vo.getPage()-1)*vo.getPageSize()));
		map.put("pageSize",vo.getPageSize());
		List<RoleBean> list=roleMapper.findRoleByPage(map);
		Page<RoleBean> page=new Page<RoleBean>();
		page.setRows(list);
		page.setTotal(this.getCount(map));
		return page;
	}
	
	/**
	 * 根据ID查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public RoleBean findById(ObjectVo vo) throws Exception{
		Object roleId=vo.getFormValue().get("roleId");
		if(roleId!=null && StringUtils.isNotBlank(roleId.toString())) {
			return roleMapper.findById(roleId.toString());
		}else {
			return null;
		}
	}
	
	 /**
	  * 查询所有                                                                                                          
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<RoleBean> findByList(ObjectVo vo) throws Exception{
		return roleMapper.findByList(vo.getParameterMap());
	}
	
	/**
	 * 获得条数
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getCount(Map<String,Object> map) throws Exception{
		return roleMapper.getCount(map);
	}
	
	//判断中间表是否重复插入
	public int findUserRoleCount(UserRoleBean entity) throws Exception {
		return roleMapper.findUserRoleCount(entity);
	}
	
	//删除中间表
	@Transactional(rollbackFor=Exception.class)
	public int deleteUserRole(UserRoleBean entity) throws Exception {
		return roleMapper.deleteUserRole(entity);
	}
	
	//删除角色菜单中间表
	@Transactional(rollbackFor=Exception.class)
	public int deleteRoleIdMenuId(ObjectVo vo) throws Exception {
		JSONObject json=vo.getFormValue();
		
		//如果删除父节点，通过ID查询父节点
		Map<String,Object> map1=vo.jsonObjectConverMap(json);
		List<MenuBean> list=menuMapper.findMebuByPid(map1);
		if(list.size()>0 && list!=null) {
			for(int i=0;i<list.size();i++) {
				map1.put("menuId",list.get(i).getMenuId());
				roleMapper.deleteRoleIdMenuId(map1);
			}
		}
		
		Map<String,Object> map=vo.jsonObjectConverMap(json);
		return roleMapper.deleteRoleIdMenuId(map);
	}
	
	//插入角色用户中间表
	@Transactional(rollbackFor=Exception.class)
	public int addUserRole(ObjectVo vo) throws Exception {
		UserRoleBean entity=(UserRoleBean) vo.JsonToBean(UserRoleBean.class);
		if(entity!=null && StringUtils.hasLength(entity.getUserId())){
			//插入之前先删除
			this.deleteUserRole(entity);
			String[] userIds=entity.getUserId().split(",");
			int result=0;
			for (int i = 0; i < userIds.length; i++) {
				//判断重复添加
				entity.setUserRoleId(StringUtils.getUUid());
				entity.setEnabledFlag(1);
				entity.setUserId(userIds[i]);
				entity.setCreateBy("admin");
				entity.setCreateDate(DateTimeUtile.getTimesTmp());
				entity.setUpdateBy("admin");
				entity.setUpdateDate(DateTimeUtile.getTimesTmp());
				result=roleMapper.addUserRole(entity);
			}
			return result;
		}else {
			return -1;
		}
	}
	
	
}
