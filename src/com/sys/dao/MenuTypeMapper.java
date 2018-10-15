package com.sys.dao;

import java.util.List;
import java.util.Map;

import com.ajax.ObjectVo;
import com.common.Page;
import com.sys.entity.MenuTypeBean;

/**
 * 菜单分类接口类
 * @author Administrator
 *
 */
public interface MenuTypeMapper {
	//新增
	public int addMenuType(MenuTypeBean entity);
	
	//修改
	public int updateMenuType(MenuTypeBean entity);
	
	//物理删除
	public int removeMenuType(String id);
	
	//删除
	public int deleteMenuType(MenuTypeBean entity);
	
	//根据ID查找
	public MenuTypeBean findById(String typeId);
	
	//根据所有
	public List<MenuTypeBean> findByList();
	
	//查询所有
	public List<MenuTypeBean> findMenuTypeByList(Page page,Map<String,Object> map);
	
	//获得条数
	public int getCount(Map<String,Object> map);
}
